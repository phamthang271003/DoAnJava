
package Restaurant.Model;

import java.util.Date;

public class ModelBill {
       private int ID_Bill;
    private String Table;
    private Date DateChekIn ;
    private double TotalPrice;
    private String Status;

    public ModelBill(int ID_Bill,String Table,Date DateChekIn,double TotalPrice,String Status) {
        this.ID_Bill = ID_Bill ;
        this.Table = Table;
        this.DateChekIn = DateChekIn;
        this.TotalPrice = TotalPrice;
        this.Status= Status;
       
    }

    public int getID_Bill() {
        return ID_Bill;
    }

    public void setID_Bill(int ID_Bill) {
        this.ID_Bill = ID_Bill;
    }

    public String getTable() {
        return Table;
    }

    public void setTable(String Table) {
        this.Table = Table;
    }

    public Date getDateChekIn() {
        return DateChekIn;
    }

    public void setDateChekIn(Date DateChekIn) {
        this.DateChekIn = DateChekIn;
    }

     public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getStatus() {
        return Status;
    }

    public void setTotalPrice(String Status) {
        this.Status = Status;
    }

}


