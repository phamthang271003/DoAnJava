/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author quangthang
 */
public class ModelReceiptInfo {

    private int ID_Rec;
    private int ID_Ingr;
    private String Name_ingre;
    private String Unit;
    private int Count;
    private int TotalPrice;

    public int getID_Rec() {
        return ID_Rec;
    }

    public void setID_Rec(int ID_Rec) {
        this.ID_Rec = ID_Rec;
    }

    public int getID_Ingr() {
        return ID_Ingr;
    }

    public void setID_Ingr(int ID_Ingr) {
        this.ID_Ingr = ID_Ingr;
    }

  

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public ModelReceiptInfo() {
    }

    public ModelReceiptInfo(int ID_Rec, int ID_Ingr, String Name_ingre,String Unit, int Count, int TotalPrice) {
        this.ID_Rec = ID_Rec;
        this.ID_Ingr = ID_Ingr;
        this.Name_ingre = Name_ingre;
        this.Unit = Unit;
        this.Count = Count;
        this.TotalPrice = TotalPrice;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public String getName_ingre() {
        return Name_ingre;
    }

    public void setName_ingre(String Name_ingre) {
        this.Name_ingre = Name_ingre;
    }

}
