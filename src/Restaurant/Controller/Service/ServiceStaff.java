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

     public ArrayList<ModelTable> listMenu() throws SQLException {
        ArrayList<ModelTable> list = new ArrayList<>();
        String sql = "SELECT ID_Table, TableName, Location, Status FROM [Table]";
        try (PreparedStatement p = con.prepareStatement(sql);
             ResultSet r = p.executeQuery()) {
            while (r.next()) {
                int ID_Table = r.getInt("ID_Table");
                String TableName = r.getString("TableName");
                String Location = r.getString("Location");
                String Status = r.getString("Status");
                ModelTable data = new ModelTable(ID_Table, TableName, Location, Status);
                list.add(data);
            }
        }
        return list;
    }

}
