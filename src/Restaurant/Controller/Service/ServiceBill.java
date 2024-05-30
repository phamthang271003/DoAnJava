
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelBill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import Restaurant.Model.ModelBillInfo;



public class ServiceBill {
     private final Connection con;
       private int ID_Category;

     
     
     
    public ServiceBill() 
    
    {
        this.con = DatabaseConnection.getInstance().getConnection();
    }
    
    

    // Lấy danh sách thực phẩm theo ID của loại thực phẩm
    public ArrayList<ModelBill> listBill()  throws SQLException {
        ArrayList<ModelBill> list = new ArrayList<>();
        String sql = "SELECT ID_Bill,TableName, DateCheckIn,TotalPrice, Bill.Status   FROM [Bill], [Table] where [Bill].ID_Table = [Table].ID_Table;";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery(); 
             while (r.next()) {
                    int ID_Bill = r.getInt(1);
                    String Name = r.getString(2);

                    Date DateChekIn = r.getDate(3);                 

                    
                    //double Discount = r.getDouble(4);
                    double TotalPrice = r.getDouble(4);
                    String Status = r.getString(5);
                    ModelBill data = new ModelBill(ID_Bill, Name, DateChekIn,TotalPrice,Status);
                    list.add(data);
                }
        r.close();
        p.close();
        return list;
 
    }
    
    
     public ArrayList<ModelBill> BillToDay() throws SQLException {
    ArrayList<ModelBill> list = new ArrayList<>();
    LocalDate today = LocalDate.now();
    
    // Định dạng ngày tháng sang "yyyy-MM-dd"
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = today.format(formatter);
    
    String sql = "SELECT ID_Bill,TableName, DateCheckIn,TotalPrice, Bill.Status   FROM [Bill], [Table]  where [Bill].ID_Table = [Table].ID_Table  and DateCheckIn = ?";
    PreparedStatement p = con.prepareStatement(sql);
    
    // Sử dụng java.sql.Date để đặt giá trị tham số trong câu truy vấn
    p.setDate(1, java.sql.Date.valueOf(formattedDate));
    
    ResultSet r = p.executeQuery();
    while (r.next()) {
        int ID_Bill = r.getInt(1);  // Mã kho
        String NameTable = r.getString(2);         
        Date DateChekIn = r.getDate(3);   // Ngày nhập
        double TotalPrice = r.getDouble(4);
        String Status = r.getString(5);
        ModelBill data = new ModelBill(ID_Bill, NameTable, DateChekIn,TotalPrice,Status);
        list.add(data);
    }
    r.close();
    p.close();
    return list;
}

    
    
    
    
     public ArrayList<ModelBillInfo> listBillInfo(int ID_Bill)  throws SQLException {
        ArrayList<ModelBillInfo> list = new ArrayList<>();
        String sql = "SELECT ID_Bill,FoodName,Count,Price  FROM Food, BillInfo where Food.ID_Food = BillInfo.ID_Food and ID_Bill = ?;";
        PreparedStatement p = con.prepareStatement(sql);
         p.setInt(1, ID_Bill);
        ResultSet r = p.executeQuery();
             while (r.next()) {
                 
                    int ID = r.getInt(1);
                    String NameFood = r.getString(2);
                    int Count = r.getInt(3);
                    double Price = r.getDouble(4);
                   
                    ModelBillInfo data = new ModelBillInfo(ID, NameFood, Count, Price);
                    list.add(data);
                }
        r.close();
        p.close();
        return list;
 
    }
    
}
