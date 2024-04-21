/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author nguyendien
 */
public class ModelUser {
    private int ID_User;
    private String Email;
    private String Password;
    private String Role;
    
    public ModelUser(int ID_User,String Email,String Password,String Role)
    {
        this.ID_User=ID_User;
        this.Email=Email;
        this.Password=Password;
        this.Role=Role;
    }
    
    public int getID_User()
    {
        return ID_User;
    }
    
    public String getEmail()
    {
        return Email;
    }
    
    public String getPassword()
    {
        return Password;
    }
    
    public String getRole()
    {
        return Role;
    }
    
    public void setID_Table(int ID_Table)
    {
        this.ID_User=ID_Table;
    }
    
    public void setEmail(String Email)
    {
        this.Email=Email;
    }
    
    public void setPassword(String Password)
    {
        this.Password=Password;
    }
    
    public void setRole(String Role)
    {
        this.Role=Role;
    }
}
