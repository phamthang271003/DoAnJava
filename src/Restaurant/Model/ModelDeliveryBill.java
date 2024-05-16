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
public class ModelDeliveryBill {
    private int ID_Del;
    private int id_Emp;
   private Date InputDay;
   
    public int getID_Del() {
        return ID_Del;
    }

    public void setID_Del(int ID_Del) {
        this.ID_Del = ID_Del;
    }

    public int getId_Emp() {
        return id_Emp;
    }

    public void setId_Emp(int id_Emp) {
        this.id_Emp = id_Emp;
    }

    public Date getInputDay() {
        return InputDay;
    }

    public void setInputDay(Date InputDay) {
        this.InputDay = InputDay;
    }

    public ModelDeliveryBill() {
    }

    public ModelDeliveryBill(int ID_Del, int id_Emp, Date InputDay) {
        this.ID_Del = ID_Del;
        this.id_Emp = id_Emp;
        this.InputDay = InputDay;
    }
 
}
