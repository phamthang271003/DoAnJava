
package Restaurant.Model;

import java.util.Date;

public class ModelPersional {

    private int iD_Emp;
    private String nameEmp;
    private Date dateofwork;
    private String phoneNumber;
    private String position;
    private String status;

    public ModelPersional() {
    }

    public ModelPersional(int iD_NV, String nameNV, Date date,String phone,String pos,String stt) {
        this.iD_Emp = iD_NV;
        this.nameEmp = nameNV;
        this.dateofwork = date;
        this.phoneNumber = phone;
        this.position=pos;
        this.status=stt;
    }

    public int getiD_Emp() {
        return iD_Emp;
    }

    public String getNameEmp() {
        return nameEmp;
    }

    public Date getDate() {
        return dateofwork;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }
    
      public String getPos() {
        return position;
    }
      
        public String getStatus() {
        return status;
    }

    public void setiD_Emp(int iD_Emp) {
        this.iD_Emp = iD_Emp;
    }

    public void setNameEmp(String name) {
        this.nameEmp = name;
    }

    public void setDate(Date date) {
        this.dateofwork = date;
    }

    public void setPhone(String phone) {
        this.phoneNumber = phone;
    }
    
     public void setPos(String pos) {
        this.position = pos;
    }

      public void setSTT(String stt) {
        this.status = stt;
    }
}

