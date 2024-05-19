
package Restaurant.Model;




public class ModelReceiptInfo1 {
    private int ID_Rec;
    private String NameIngr;
    private int count;
    private double totalPrice;
    
    public ModelReceiptInfo1()
    {
        
    }
    
    public ModelReceiptInfo1(int ID_Rec,String Name,int Count, double totalPrice)
    {
        this.ID_Rec = ID_Rec;
        this.NameIngr = Name;
        this.count = Count;
        this.totalPrice=totalPrice;
    }
    
    public int getID_Rec()
    {
        return ID_Rec;
    }
    
   public String getName()
    {
        return NameIngr;
    }
   
    public int getCount()
    {
        return count;
    }
    
    public double getPrice()
    {
        return totalPrice;
    }
    
    
    
    
    public void setID_Rec(int ID_Rec)
    {
        this.ID_Rec = ID_Rec;
    }
    
    public void setNameIngr(String name)
    {
        this.NameIngr = name;
    }
    
    
     public void setcount(int count)
    {
        this.count = count;
    }
     
      public void setPrice(double price)
    {
        this.totalPrice = price;
    }
}

