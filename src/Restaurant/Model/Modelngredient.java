/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author quangthang
 */
public class Modelngredient {

    private int iD_Ingr;
    private String nameIngre;
    private int price;
    private String unit;
    private float quantityInKitchen;
    private float quantityInStock;

    public Modelngredient() {
    }

    public Modelngredient(int iD_Ingr, String nameIngre, int price, String unit, float quantityInKitchen, float quantityInStock) {
        this.iD_Ingr = iD_Ingr;
        this.nameIngre = nameIngre;
        this.price = price;
        this.unit = unit;
        this.quantityInKitchen = quantityInKitchen;
        this.quantityInStock = quantityInStock;
    }

    public int getiD_Ingr() {
        return iD_Ingr;
    }

    public void setiD_Ingr(int iD_Ingr) {
        this.iD_Ingr = iD_Ingr;
    }

    public String getNameIngre() {
        return nameIngre;
    }

    public void setNameIngre(String nameIngre) {
        this.nameIngre = nameIngre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getQuantityInKitchen() {
        return quantityInKitchen;
    }

    public void setQuantityInKitchen(float quantityInKitchen) {
        this.quantityInKitchen = quantityInKitchen;
    }

    public float getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(float quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

  
}
