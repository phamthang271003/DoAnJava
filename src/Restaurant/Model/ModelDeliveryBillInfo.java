/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;


/**
 *
 * @author quangthang
 */
public class ModelDeliveryBillInfo {
    private int ID_Del;
    private int id_Ingr;
   private int Count;

    public int getID_Del() {
        return ID_Del;
    }

    public void setID_Del(int ID_Del) {
        this.ID_Del = ID_Del;
    }

    public int getId_Ingr() {
        return id_Ingr;
    }

    public void setId_Ingr(int id_Ingr) {
        this.id_Ingr = id_Ingr;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public ModelDeliveryBillInfo() {
    }

    public ModelDeliveryBillInfo(int ID_Del, int id_Ingr, int Count) {
        this.ID_Del = ID_Del;
        this.id_Ingr = id_Ingr;
        this.Count = Count;
    }
}
