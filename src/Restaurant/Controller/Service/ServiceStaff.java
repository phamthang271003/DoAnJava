/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.Modelngredient;
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
     public ServiceStaff(){
           DatabaseConnection.getInstance().connectToDatabase(); 
          con = DatabaseConnection.getInstance().getConnection();
     }
         //Lấy toàn bộ danh sách nguyên liệu
    public ArrayList<Modelngredient> MenuIngr() throws SQLException {
        ArrayList<Modelngredient> list = new ArrayList<>();
        String sql = "SELECT ID_Ingr,NameIngre,Price,unit FROM [ingredient] ORDER BY ID_Ingr";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int iD_Ingr = r.getInt(1);  //Mã nguyên liệu
            String nameIngre = r.getString(2); //Tên nguyên liệu
            int price = r.getInt(3); //Đơn giá nhập nguyên liệu
            String unit = r.getString(4); //Đơn vị tính của nguyên liệu
            Modelngredient data = new Modelngredient(iD_Ingr, nameIngre, price, unit);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }
}
