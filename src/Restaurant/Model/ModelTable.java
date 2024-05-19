/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author quangthang
 */
public class ModelTable {

    private int ID_Table;
    private String TableName;
    private String Location;
    private String Status;

    public ModelTable(int ID_Table, String TableName, String Location, String Status) {
        this.ID_Table = ID_Table;
        this.TableName = TableName;
        this.Location = Location;
        this.Status = Status;
    }

    public int getID_Table() {
        return ID_Table;
    }

    public String getTableName() {
        return TableName;
    }

    public String getLocation() {
        return Location;
    }

    public String getStatus() {
        return Status;
    }

    public void setID_Table(int ID_Table) {
        this.ID_Table = ID_Table;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}
