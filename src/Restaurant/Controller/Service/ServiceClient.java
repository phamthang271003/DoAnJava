/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;
import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelClient;
import Restaurant.Model.ModelIDFood;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import java.sql.Connection;

/**
 *
 * @author HELO
 */
public class ServiceClient {
    private final Connection con;
    
    
      public ServiceClient() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }
      //lay ds kh
    public ArrayList<ModelClient> listKH()  throws SQLException {
        ArrayList<ModelClient> list = new ArrayList<>();
        String sql = "SELECT *from Client";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery(); 
             while (r.next()) {
                    int ID_Client = r.getInt(1);
                    String Name = r.getString(2);
                    Date DateJoin = r.getDate(3);
                    int ID_User = r.getInt(4);
                    ModelClient data = new ModelClient(ID_Client, Name, DateJoin, ID_User);
                    list.add(data);
                }
        r.close();
        p.close();
        return list;
    }
}
