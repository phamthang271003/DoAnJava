/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.View.Form.Staff.TableFood;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class ServiceSignInUp {
    
    private final Connection con;

    private TableFood tb;
    
    public static String ID_Client;
    public static String Email;
    public static String EmailName;
    
    public ServiceSignInUp() {
        DatabaseConnection.getInstance().connectToDatabase();
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public boolean checkEmail(String Email)
    {
        String sql="SELECT * FROM [USER] WHERE Email=?";
        try(PreparedStatement statement=con.prepareStatement(sql))
        {
            statement.setString(1,Email );
            try(ResultSet result=statement.executeQuery())
            {
                if(result.next())
                {
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
   
       
    
    public int checkRole(String Email)
    {
        String sql="SELECT Role FROM [User] WHERE Email=?";
        String role="";
        try(PreparedStatement statement=con.prepareStatement(sql))
        {
            statement.setString(1, Email);
            ResultSet r=statement.executeQuery();
            while(r.next())
            {
                role=r.getString("Role");
            }
            
            if(role.equals("Nhan Vien"))
            {
                return 1;
            }
            else if(role.equals("Nhan Vien Kho"))
            {
                return 2;
            }
            else if(role.equals("Quan Ly"))
            {
                return 3;
            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int insertUser(String Email, String Password)
    {
       String sql = "INSERT INTO [User] (ID_User, Email, Password, Role)" +
             "VALUES ((SELECT ISNULL(MAX(ID_User), 0) + 1 FROM [User]), ?, ?, 'Nhan Vien')";

        try (PreparedStatement statement = con.prepareStatement(sql)) 
        {
            statement.setString(1, Email);
            statement.setString(2, Password);
            statement.executeUpdate();
            return 1;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int LayMaNV(String email) throws SQLException
    {
        String sql="SELECT ID_Emp FROM Employee,[User] WHERE Employee.ID_User = [User].ID_User and Email=?";
        int ID =0;
        try(PreparedStatement statement=con.prepareStatement(sql))
        {
            statement.setString(1, Email);
            ResultSet r=statement.executeQuery();
            while(r.next())
            {
                ID=r.getInt("ID_Emp");
            
                
            }
            return ID;
    }
    }
    public boolean checkLogin(String Email,String Password,String EmailName)
    {
String sql="SELECT * FROM [User] WHERE Email = ? AND Password = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, Email);
            statement.setString(2, Password);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                // Nếu resultSet có ít nhất một dòng dữ liệu, có nghĩa là tài khoản tồn tại
                if (resultSet.next()) {
                    this.EmailName=EmailName;
                    this.Email=Email;
                    this.ID_Client=resultSet.getString("ID_User");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}