/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author HELO
 */
public class ModelBillInfo {
     private int ID_BillInfo;
    private String NameFood;
    private int Count;
    private double Price;

    public ModelBillInfo(int ID_BillInfo,String NameFood,int Count,double Price) {
        this.ID_BillInfo = ID_BillInfo ;
        this.NameFood = NameFood;
        this.Count = Count;
        this.Price = Price;
       
    }

    public int getID_BillInfo() {
        return ID_BillInfo;
    }

    public void setID_BillInfo(int ID_BillInfo) {
        this.ID_BillInfo = ID_BillInfo;
    }

    public String getNameFood() {
        return NameFood;
    }

    public void setFoodName(String NameFood) {
        this.NameFood = NameFood;
    }

    public double getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

   

}
