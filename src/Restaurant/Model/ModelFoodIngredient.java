/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author HELO
 */
public class ModelFoodIngredient {
    
    private int ID_Food;
    private int ID_Ingr;
    private int QuantityIngredient;//so luong tphan
    private String Unit; // don vi
    
    public ModelFoodIngredient(int ID_Food,int ID_Ingr,int QuantityIngredient,String Unit)
    {
        this.ID_Food=ID_Food;
        this.ID_Ingr=ID_Ingr;
        this.QuantityIngredient=QuantityIngredient;
        this.Unit=Unit;
        
    }
    
    public int getID_Food()
    {
        return ID_Food;
    }
    
    public int getID_Ingr()
    {
        return ID_Ingr;
    }
    
    public int getQuantityIngredient()
    {
        return QuantityIngredient;
    }
    
    public String getUnit()
    {
        return Unit;
    }
    
    public void setID_Food(int ID_Food)
    {
        this.ID_Food=ID_Food;
    }
    
    public void setID_Ingr(int ID_Ingr)
    {
        this.ID_Ingr=ID_Ingr;
    }
    
    public int setQuantityIngredient (int QuantityIngredient)
    {
        return this.QuantityIngredient=QuantityIngredient;
    }
    
    public String setUnit(String Unit)
    {
        return this.Unit=Unit;
    }
}

