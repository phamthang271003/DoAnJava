/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelIDFood;
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


public class ServiceMenu {
     private final Connection con;
       private int ID_Category;

     
    public ServiceMenu() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }

    // Lấy danh sach
    public ArrayList<ModelIDFood> listFood()  throws SQLException {
        ArrayList<ModelIDFood> list = new ArrayList<>();
        String sql = "SELECT ID_Food, FoodName, FoodPrice, CategoryName, Status FROM Food, FoodCategory where FoodCategory.ID_FoodCategory = Food.ID_Category";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery(); 
             while (r.next()) {
                    int ID_Food = r.getInt(1);
                    String FoodName = r.getString(2);
                    double FoodPrice = r.getDouble(3);
                    String Name = r.getString(4);
                    String Status = r.getString(5);
                    ModelIDFood data = new ModelIDFood(ID_Food, FoodName, FoodPrice, Name, Status);
                    list.add(data);
                }
        r.close();
        p.close();
        return list;
    }
    public int countAvailableFood() throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM Food WHERE Status = 'Con hang'";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            count = r.getInt("count");
        }
        r.close();
        p.close();
        return count;
    }
    
    
          public void addNewMenu(String FoodName,  double FoodPrice , int ID_Catelory , String status) throws SQLException {
    String sqlSelectLastID = "SELECT TOP 1 ID_Food FROM Food ORDER BY ID_Food DESC";
    PreparedStatement pSelect = con.prepareStatement(sqlSelectLastID);
    ResultSet r = pSelect.executeQuery();
    int lastID = 0;
    if (r.next()) {
        lastID = r.getInt(1);
    }
    r.close();
    pSelect.close();
    
    int newID = lastID + 1;

    String sql = "INSERT INTO Food (ID_Food, FoodName, FoodPrice, ID_Category, Status) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement p = con.prepareStatement(sql);
    p.setInt(1, newID);
    p.setString(2, FoodName);
    p.setDouble(3, (FoodPrice));
    p.setInt(4, ID_Catelory);
    p.setString(5, status);
    p.executeUpdate();
    p.close();
}     
      public void DeleteMenu(int IDMenu) throws SQLException {
    // Chuỗi truy vấn
    String query = "DELETE FROM Food WHERE ID_Food = ?";
    
    
    try (PreparedStatement statement = con.prepareStatement(query)) {
        statement.setInt(1, IDMenu);
        
        statement.executeUpdate();
    }
}
      
      
     public void updateEmployee(int IDFood, String FoodName,  Double Price,String Name, String status) throws SQLException {
            if(Name.equalsIgnoreCase("Com"))
                  {
                      ID_Category = 1;
                  }
                  else if (Name.equalsIgnoreCase("Thịt bê"))
                  {
                      ID_Category = 2;

                  }
          else if (Name.equalsIgnoreCase("Súp"))
                  {
                     ID_Category = 3;

                  }
          else if (Name.equalsIgnoreCase("Lẩu"))
                  {
                    ID_Category = 4;

                  }
          else
                   {     
                        ID_Category = 5;  
                    }                              
         
    String query = "UPDATE Food SET FoodName = ?, FoodPrice = ?,ID_Category = ? , Status = ? WHERE ID_Food = ?";
    
    
    try (PreparedStatement statement = con.prepareStatement(query)) {
        statement.setString(1, FoodName);
        statement.setDouble(2, Price);
        statement.setInt(3, ID_Category);
        statement.setString(4, status);
        statement.setInt(5, IDFood);
        
        // Thực hiện truy vấn cập nhật
        statement.executeUpdate();
    }
}           
          
          
}
