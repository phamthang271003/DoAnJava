
package Restaurant.Model;

/**
 *
 * @author HELO
 */
public class ModelIngr {
    private int ID_Ingr;
    private String NameIngre;
  
    
     
    
    public ModelIngr(int id,String name)
    {
        this.ID_Ingr=id;

        this.NameIngre=name;
       
    }
    
    public int getID_Ingr()
    {
        return ID_Ingr;
    }
    
    public String getNameIngre()
    {
        
        return NameIngre;
    }
    
    public void setID_Ingr(int id)
    {
        this.ID_Ingr=id;
    }
    
      public void setNameIngre(String nameIngr)
    {
        this.NameIngre=nameIngr;
    }
}
