/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
