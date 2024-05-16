/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelEmployee;
import Restaurant.Model.ModelFood;
import Restaurant.Model.ModelTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author quangthang
 */
public class ServiceStaff {

    private final Connection con;

    public ServiceStaff() {
        DatabaseConnection.getInstance().connectToDatabase();
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public void checkStatusFood() {
        String sqlSelect = "SELECT f.ID_Food, f.FoodName, fi.ID_Ingr, fi.QuantityIngredient, i.QuantityInStock " +
                           "FROM Food f " +
                           "LEFT JOIN FoodIngredient fi ON f.ID_Food = fi.ID_Food " +
                           "LEFT JOIN Ingredient i ON fi.ID_Ingr = i.ID_Ingr";

        String sqlUpdate = "UPDATE Food SET Status = 'Het hang' WHERE ID_Food = ?";

        try (PreparedStatement statementSelect = con.prepareStatement(sqlSelect);
             PreparedStatement statementUpdate = con.prepareStatement(sqlUpdate);
             ResultSet resultSet = statementSelect.executeQuery()) {

            while (resultSet.next()) {
                int ID_Food = resultSet.getInt("ID_Food");
                double QuantityIngredient = resultSet.getInt("QuantityIngredient");
                double QuantityInStock = resultSet.getInt("QuantityInStock");

                // Kiểm tra xem nguyên liệu còn đủ hay không
                if (QuantityIngredient / 1000 > QuantityInStock) {
                    // Nếu không đủ, cập nhật thuộc tính Status cho món ăn hết hàng
                    statementUpdate.setInt(1, ID_Food);
                    statementUpdate.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ SQL
        }
    }

    public void deleteIngreOrderFood(ArrayList<String[]> list) {
        String sqlSelect = "SELECT ID_Ingr, QuantityInStock FROM Ingredient WHERE ID_Ingr = ?";
        String sqlUpdate = "UPDATE Ingredient SET QuantityInStock = ? WHERE ID_Ingr = ?";

        try (PreparedStatement statementSelect = con.prepareStatement(sqlSelect);
             PreparedStatement statementUpdate = con.prepareStatement(sqlUpdate)) {

            for (String[] ingredient : list) {
                int ID_Ingr = Integer.parseInt(ingredient[1]); // Lấy ID của nguyên liệu từ danh sách

                // Lấy thông tin về nguyên liệu từ bảng Ingredient
                statementSelect.setInt(1, ID_Ingr);
                ResultSet resultSet = statementSelect.executeQuery();

                if (resultSet.next()) {
                    double currentQuantityInStock = resultSet.getDouble("QuantityInStock");
                    double quantityToRemove = Double.parseDouble(ingredient[2]) / 1000.0; // Chuyển đổi về đơn vị kg

                    // Cập nhật lại lượng nguyên liệu còn lại trong kho
                    double newQuantityInStock = currentQuantityInStock - quantityToRemove;

                    // Đảm bảo lượng nguyên liệu không âm
                    if (newQuantityInStock >= 0) {
                        statementUpdate.setDouble(1, newQuantityInStock);
                        statementUpdate.setInt(2, ID_Ingr);
                        statementUpdate.executeUpdate();
                    } else {
                        // Xử lý trường hợp lượng nguyên liệu còn lại âm
                        System.out.println("Không đủ nguyên liệu trong kho");
                        // Có thể thêm xử lý khác tùy theo yêu cầu của ứng dụng
                    }
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ SQL
        }
    }
    
    
    
     public ArrayList<String[]> getIngredient(int ID_Food,int SL)
    {
        ArrayList<String[]> ingredientList = new ArrayList<>();
        String sql = "SELECT ID_Food, ID_Ingr, QuantityIngredient, Unit FROM FoodIngredient WHERE ID_Food = ?";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, ID_Food);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] ingredient = new String[4];
                ingredient[0] = String.valueOf(resultSet.getInt("ID_Food"));
                ingredient[1] = String.valueOf(resultSet.getInt("ID_Ingr"));
                int Quantity=Integer.parseInt(String.valueOf(resultSet.getInt("QuantityIngredient")));
                Quantity=Quantity*SL;
                ingredient[2] = ""+Quantity;
                ingredient[3] = resultSet.getString("Unit");
                ingredientList.add(ingredient);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ SQL
        }

        return ingredientList;
    }
    
     public void insertBillInfo(int ID_Bill, ArrayList<String[]> list) {
        String sql = "INSERT INTO BillInfo (ID_Bill, ID_Food, Count, Price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            for (String[] row : list) {
                int ID_Food = Integer.parseInt(row[0]);
                int count = Integer.parseInt(row[3]);
                int price = Integer.parseInt(row[4]);

                statement.setInt(1, ID_Bill);
                statement.setInt(2, ID_Food);
                statement.setInt(3, count);
                statement.setInt(4, price);

                ArrayList<String[]> listIngre= getIngredient(ID_Food, count);
                
                deleteIngreOrderFood(listIngre);
                statement.addBatch(); // Thêm câu lệnh vào batch để thực thi một lần duy nhất
            }
            statement.executeBatch(); // Thực thi tất cả các câu lệnh trong batch
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ SQL
        }
    }
    
     public void insertBill(int ID_Bill, String ID_Client, String ID_Table, int TotalPrice, String Status) {
        String sql = "INSERT INTO Bill (ID_Bill, ID_Client, ID_Table, DateCheckIn, TotalPrice, Status) VALUES (?, ?, ?, GETDATE(), ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, ID_Bill);
            preparedStatement.setString(2, ID_Client);
            preparedStatement.setString(3, ID_Table);
            preparedStatement.setInt(4, TotalPrice);
            preparedStatement.setString(5, Status);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int theLastIdInBill() {
        int lastId = 0;
        String sql = "SELECT MAX(ID_Bill) AS LastID FROM Bill";

        try (PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                lastId = resultSet.getInt("LastID");
            }
        } catch (SQLException ex) {
            // Xử lý ngoại lệ SQL
            ex.printStackTrace(); // Bạn có thể muốn ghi log ngoại lệ
        }

        // Trả về ID cuối cùng, nếu không có dòng nào thì trả về 1
        return lastId > 0 ? lastId : 0;
    }
    
     public String[] getFoodCategory() throws SQLException {
        ArrayList<String> foodNames = new ArrayList<>();

        String sql = "SELECT * FROM FoodCategory";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            
            String CategoryName=resultSet.getString("CategoryName");
            int ID=resultSet.getInt("ID_FoodCategory");
            String foodName = ID+"."+CategoryName;
            foodNames.add(foodName);
        }

        resultSet.close();
        statement.close();

        return foodNames.toArray(new String[0]);
    }

     public ArrayList<ModelFood> listFoodFromCate(String idCate) throws SQLException {
        ArrayList<ModelFood> list = new ArrayList<>();
        String sql = "SELECT * FROM Food WHERE ID_Category = ? ";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, idCate);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int ID_Food = r.getInt("ID_Food");
            String FoodName = r.getString("FoodName");
            int FoodPrice = r.getInt("FoodPrice");
            int ID_Category = r.getInt("ID_Category");
            String Status = r.getString("Status");
            ModelFood data = new ModelFood(ID_Food, FoodName, FoodPrice,ID_Category, Status);
            list.add(data);
        }

        r.close();
        p.close();
        return list;
    }
//Lấy danh sách bàn theo tầng
    public ArrayList<ModelTable> listTable(String floor) throws SQLException {
        ArrayList<ModelTable> list = new ArrayList<>();
        String sql = "SELECT ID_Table, TableName, Location, Status FROM [Table] WHERE Location = ? ";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, floor);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int ID_Table = r.getInt("ID_Table");
            String TableName = r.getString("TableName");
            String Location = r.getString("Location");
            String Status = r.getString("Status");
            ModelTable data = new ModelTable(ID_Table, TableName, Location, Status);
            list.add(data);
        }

        r.close();
        p.close();
        return list;
    }
    //Điều chỉnh trạng thái bàn thành Đã đặt trước sau khi nhân viên xác nhận

    public void setTableReserve(int idBan) throws SQLException {
        String sql = "UPDATE [Table] SET Status = 'Da dat truoc' WHERE ID_Table=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, idBan);
        p.execute();
        p.close();
    }

    //Hủy trạng thái bàn đã Đặt trước trước thành Còn trống
    public void CancelTableReserve(int idBan) throws SQLException {
        String sql = "UPDATE [Table] SET Status = 'Con trong' WHERE ID_Table=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, idBan);
        p.execute();
        p.close();
    }

    //Tìm kiếm theo tên bàn 
    public ArrayList<ModelTable> searchTable(String where, Object... searchParams) throws SQLException {
        ArrayList<ModelTable> resultList = new ArrayList<>();
        String sql = "SELECT * FROM [Table] " + where;

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Bind parameters
            for (int i = 0; i < searchParams.length; i++) {
                statement.setObject(i + 1, searchParams[i]);
            }

            // Execute query
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int ID_Table = resultSet.getInt("ID_Table");
                    String TableName = resultSet.getString("TableName");
                    String Location = resultSet.getString("Location");
                    String Status = resultSet.getString("Status");
                    ModelTable table = new ModelTable(ID_Table, TableName, Location, Status);
                    resultList.add(table);
                }
            }
        } catch (SQLException ex) {
            // Handle SQL exception
            ex.printStackTrace(); // You may want to log the exception instead
            throw ex;
        }

        return resultList;
    }

}
