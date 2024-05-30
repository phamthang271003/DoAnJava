/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelFoodIngredient;
import Restaurant.Model.ModelIngr;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HELO
 */
public class ServiceFoodIngredient {
     private final Connection con;
       private int ID_Category;

     
    public ServiceFoodIngredient() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }
    
    
 public ArrayList<ModelFoodIngredient> listInrFood(int ID_Food) throws SQLException {
    ArrayList<ModelFoodIngredient> listInrFood = new ArrayList<>();
    //String sql = "SELECT * FROM FoodIngredient WHERE ID_Food = ?";
    
    String query = "{call DSNguyenLieuMon(?)}"; 
        //Statement statement = conn.createStatement();
         CallableStatement cstmt = con.prepareCall(query);      
         cstmt.setInt(1, ID_Food);        
        ResultSet rs = cstmt.executeQuery();

        // Duyệt qua từng dòng dữ liệu trong ResultSet và thêm vào bảng
        while (rs.next()) {
            int Food = rs.getInt("ID_Food");
                int ID_Ingr = rs.getInt("ID_Ingr");
                int QuantityIngredient = rs.getInt("QuantityIngredient");
                String Unit = rs.getString("unit");
    
   
                ModelFoodIngredient data = new ModelFoodIngredient(Food, ID_Ingr, QuantityIngredient, Unit);
                listInrFood.add(data);
            }
        
    return listInrFood;
}

 
 public ArrayList<ModelIngr> getID_NameIngr() throws SQLException {
         ArrayList<ModelIngr> list = new ArrayList<>();
    
    String sql = "SELECT ID_Ingr,NameIngre FROM [ingredient]";
    PreparedStatement p = con.prepareStatement(sql);
    
    ResultSet r = p.executeQuery();
    while (r.next()) {
       int id = r.getInt(1);
       String name = r.getString(2);
       ModelIngr data = new ModelIngr(id,name);
        list.add(data);
    }
    r.close();
    p.close();
    return list;
    }

 public void addIngredient(int foodId, int ingredientId, int quantity, String unit) throws SQLException {
    // Kết nối với cơ sở dữ liệu và thực hiện câu lệnh SQL để thêm nguyên liệu
    String query = "INSERT INTO FoodIngredient (ID_Food, ID_Ingr, QuantityIngredient, unit) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, foodId);
        stmt.setInt(2, ingredientId);
        stmt.setInt(3, quantity);
        stmt.setString(4, unit);
        stmt.executeUpdate();
    }
}
 public void deleteIngredient(int foodId, int ingredientId) throws SQLException {
    // Kết nối với cơ sở dữ liệu và thực hiện câu lệnh SQL để xóa nguyên liệu
    String query = "DELETE FROM FoodIngredient WHERE ID_Food = ? AND ID_Ingr = ?";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, foodId);
        stmt.setInt(2, ingredientId);
        stmt.executeUpdate();
    }
}


}
