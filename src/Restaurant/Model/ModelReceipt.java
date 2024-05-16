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
public class ModelReceipt {
    private int ID_Rec;
    private int ID_Emp;
    private   Date InputDay;
    private int TotalPrice;

    public ModelReceipt() {
    }

    public ModelReceipt(int ID_Rec, int ID_Emp, Date InputDay) {
        this.ID_Rec = ID_Rec;
        this.ID_Emp = ID_Emp;
        this.InputDay = InputDay;
    }

    public ModelReceipt(int ID_Rec, int ID_Emp, Date InputDay, int TotalPrice) {
        this.ID_Rec = ID_Rec;
        this.ID_Emp = ID_Emp;
        this.InputDay = InputDay;
        this.TotalPrice = TotalPrice;
    }

    public int getID_Rec() {
        return ID_Rec;
    }

    public int getID_Emp() {
        return ID_Emp;
    }

    public Date getInputDay() {
        return InputDay;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setID_Rec(int ID_Rec) {
        this.ID_Rec = ID_Rec;
    }

    public void setID_Emp(int ID_Emp) {
        this.ID_Emp = ID_Emp;
    }

    public void setInputDay(Date InputDay) {
        this.InputDay = InputDay;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }
    
}
