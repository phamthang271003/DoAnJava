/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author HELO
 */
public class ModelIDFood {
    
  private int ID_Food;
    private String FoodName;
    private double FoodPrice;
    private String  NameCatelory;
    private String Status;

    public ModelIDFood(int ID_Food, String FoodName, double FoodPrice, String Name, String Status) {
        this.ID_Food = ID_Food;
        this.FoodName = FoodName;
        this.FoodPrice = FoodPrice;
        this.NameCatelory =  Name;
        this.Status = Status;
    }

    public int getID_Food() {
        return ID_Food;
    }

    public void setID_Food(int ID_Food) {
        this.ID_Food = ID_Food;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String FoodName) {
        this.FoodName = FoodName;
    }

    public double getFoodPrice() {
        return FoodPrice;
    }

    public void setFoodPrice(double FoodPrice) {
        this.FoodPrice = FoodPrice;
    }

    public String getNameCategory() {
         return NameCatelory;
    }

    public void setNameCatelory(String NameCatelory) {
        this.NameCatelory = NameCatelory;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}