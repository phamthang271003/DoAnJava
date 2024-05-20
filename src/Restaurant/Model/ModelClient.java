
package Restaurant.Model;

import java.util.Date;

public class ModelClient {
    
     private int ID_Client;
    private String Name;
    private Date DateJoin;
    private int ID_User;
   

    public ModelClient(int ID_Client, String Name, Date DateJoin, int ID_User) {
        this.ID_Client = ID_Client;
        this.Name = Name;
        this.DateJoin = DateJoin;
        this.ID_User = ID_User;
     
    }

    public int getID_Client() {
        return ID_Client;
    }

    public void setID_Client(int ID_Client) {
        this.ID_Client = ID_Client;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Date getDateJoin() {
        return DateJoin;
    }

    public void setDateJoin(Date DateJoin) {
        this.DateJoin = DateJoin;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }   
}
