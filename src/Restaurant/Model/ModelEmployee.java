/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

import java.util.Date;

/**
 *
 * @author quangthang
 */
public class ModelEmployee {
    private int  ID_Emp;
    private String Name;

    public int getID_Emp() {
        return ID_Emp;
    }

    public void setID_Emp(int ID_Emp) {
        this.ID_Emp = ID_Emp;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Date getDateOfWork() {
        return DateOfWork;
    }

    public void setDateOfWork(Date DateOfWork) {
        this.DateOfWork = DateOfWork;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getPositon() {
        return Positon;
    }

    public void setPositon(String Positon) {
        this.Positon = Positon;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public int getID_Manager() {
        return ID_Manager;
    }

    public void setID_Manager(int ID_Manager) {
        this.ID_Manager = ID_Manager;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public ModelEmployee() {
    }

    public ModelEmployee(int ID_Emp, String Name, Date DateOfWork, String PhoneNumber, String Positon, int ID_User, int ID_Manager, String Status) {
        this.ID_Emp = ID_Emp;
        this.Name = Name;
        this.DateOfWork = DateOfWork;
        this.PhoneNumber = PhoneNumber;
        this.Positon = Positon;
        this.ID_User = ID_User;
        this.ID_Manager = ID_Manager;
        this.Status = Status;
    }
    private Date DateOfWork;
    private String PhoneNumber;
    private String Positon;
    private int ID_User;
    private int ID_Manager;
    private String Status;
}
