
package Restaurant.Model;

import java.util.Date;


public class ModelReceipt1 {
    private int ID_Rec;
    private int ID_Emp;
    private Date inputDay;
    private double totalPrice;
    
    public ModelReceipt1()
    {
        
    }
    
    public ModelReceipt1(int ID_Rec,int ID_Emp,Date inputDay, double totalPrice)
    {
        this.ID_Rec = ID_Rec;
        this.ID_Emp = ID_Emp;
        this.inputDay = inputDay;
        this.totalPrice=totalPrice;
    }
    
    public int getID_Rec()
    {
        return ID_Rec;
    }
    
   public int getID_Emp()
    {
        return ID_Emp;
    }
   
    public Date getDay()
    {
        return inputDay;
    }
    
    public double getPrice()
    {
        return totalPrice;
    }
    
    
    
    
    public void setID_Rec(int ID_Rec)
    {
        this.ID_Rec = ID_Rec;
    }
    
    public void setID_Emp(int ID_Emp)
    {
        this.ID_Emp = ID_Emp;
    }
    
    
     public void setDay(Date day)
    {
        this.inputDay = day;
    }
     
      public void setPrice(double price)
    {
        this.totalPrice = price;
    }
}


