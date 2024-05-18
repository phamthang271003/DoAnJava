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
    private int ID_Ingr;
    private String Name_ingre;
    private String Unit;
    private int Count;

    public ModelDeliveryBillInfo() {
    }

    public ModelDeliveryBillInfo(int ID_Del, int ID_Ingr, String Name_ingre, String Unit, int Count) {
        this.ID_Del = ID_Del;
        this.ID_Ingr = ID_Ingr;
        this.Name_ingre = Name_ingre;
        this.Unit = Unit;
        this.Count = Count;
    }

    public int getID_Del() {
        return ID_Del;
    }

    public void setID_Del(int ID_Del) {
        this.ID_Del = ID_Del;
    }

    public int getID_Ingr() {
        return ID_Ingr;
    }

    public void setID_Ingr(int ID_Ingr) {
        this.ID_Ingr = ID_Ingr;
    }

    public String getName_ingre() {
        return Name_ingre;
    }

    public void setName_ingre(String Name_ingre) {
        this.Name_ingre = Name_ingre;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }
    
}
