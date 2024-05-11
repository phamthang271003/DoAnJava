/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author admin
 */
public class ModelFood {
    
    private int ID_Food;
    private String FoodName;
    private int FoodPrice;
    private int ID_Category;
    private String Status;
    
    public ModelFood(int ID_Food,String FoodName,int FoodPrice,int ID_Category,String status)
    {
        this.ID_Food=ID_Food;
        this.FoodName=FoodName;
        this.FoodPrice=FoodPrice;
        this.ID_Category=ID_Category;
        this.Status=status;
    }
    
    public int getID_Food()
    {
        return ID_Food;
    }
    
    public String getFoodName()
    {
        return FoodName;
    }
    
    public int getFoodPrice()
    {
        return FoodPrice;
    }
    
    public int getID_Category()
    {
        return ID_Category;
    }
    
    public String getStatus()
    {
        return Status;
    }
    
    public void setID_Food(int ID_Food)
    {
        this.ID_Food=ID_Food;
    }
    
    public void setFoodName(String FoodName)
    {
        this.FoodName=FoodName;
    }
    
    public void setFoodPrice(int FoodPrice)
    {
        this.FoodPrice=FoodPrice;
    }
    
    public int setID_Category(int ID_Category)
    {
        return this.ID_Category=ID_Category;
    }
    
    public String setStatus(String Status)
    {
        return this.Status=Status;
    }
}
