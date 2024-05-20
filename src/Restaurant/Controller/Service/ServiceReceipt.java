
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelReceipt1;
import Restaurant.Model.ModelReceiptInfo1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ServiceReceipt {
     private final Connection con;
     //private Connection connection;
     public ServiceReceipt(){
           DatabaseConnection.getInstance().connectToDatabase(); 
          con = DatabaseConnection.getInstance().getConnection();
     }
     public ArrayList<ModelReceipt1> DSKho() throws SQLException {
        ArrayList<ModelReceipt1> list = new ArrayList<>();
        String sql = "SELECT ID_Rec,ID_Emp,InputDay,TotalPrice FROM [receipt] ORDER BY ID_Rec";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id_rec = r.getInt(1);  //Mã kho
            int id_emp = r.getInt(2);  //Mã nhan vien        
            Date day = r.getDate(3); // Ngay nhap
            double price = r.getDouble(4);//tong tien
            ModelReceipt1 data = new ModelReceipt1(id_rec,id_emp,day,price);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }
     
     public ArrayList<ModelReceipt1> receiptOfMonth(Date month) throws SQLException {
         ArrayList<ModelReceipt1> list = new ArrayList<>();
    
    String sql = "SELECT ID_Rec,ID_Emp,InputDay,TotalPrice FROM [receipt] WHERE MONTH(InputDay) = MONTH(?)";
    PreparedStatement p = con.prepareStatement(sql);
    
    p.setDate(1, new java.sql.Date(month.getTime()));
    ResultSet r = p.executeQuery();
    while (r.next()) {
        int id_rec = r.getInt(1);  // Mã kho
        int id_emp = r.getInt(2);  // Mã nhan vien        
        Date day = r.getDate(3); // Ngay nhap
        double price = r.getDouble(4); // Tong tien
        ModelReceipt1 data = new ModelReceipt1(id_rec, id_emp, day, price);
        list.add(data);
    }
    r.close();
    p.close();
    return list;
    }
     
     
    public ArrayList<ModelReceipt1> receiptToDay() throws SQLException {
    ArrayList<ModelReceipt1> list = new ArrayList<>();
    LocalDate today = LocalDate.now();
    
    // Định dạng ngày tháng sang "yyyy-MM-dd"
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = today.format(formatter);
    
    String sql = "SELECT ID_Rec, ID_Emp, InputDay, TotalPrice FROM [receipt] WHERE InputDay = ?";
    PreparedStatement p = con.prepareStatement(sql);
    
    // Sử dụng java.sql.Date để đặt giá trị tham số trong câu truy vấn
    p.setDate(1, java.sql.Date.valueOf(formattedDate));
    
    ResultSet r = p.executeQuery();
    while (r.next()) {
        int id_rec = r.getInt(1);  // Mã kho
        int id_emp = r.getInt(2);  // Mã nhân viên        
        Date day = r.getDate(3);   // Ngày nhập
        double price = r.getDouble(4); // Tổng tiền
        ModelReceipt1 data = new ModelReceipt1(id_rec, id_emp, day, price);
        list.add(data);
    }
    r.close();
    p.close();
    return list;
}

public ArrayList<ModelReceiptInfo1> showReceiptInfo(int rec) throws SQLException {
    ArrayList<ModelReceiptInfo1> list = new ArrayList<>();
    String sql = "SELECT ID_Rec, NameIngre, Count, TotalPrice FROM receiptInfo,ingredient where receiptInfo.ID_Ingr = ingredient.ID_Ingr and ID_Rec = ?";
    PreparedStatement p = con.prepareStatement(sql);
    
    p.setInt(1, rec);
    ResultSet r = p.executeQuery();
    while (r.next()) {
        int id_rec = r.getInt(1);  // Mã hóa đơn
        String name = r.getString(2);  // Tên nguyên liệu        
        int count = r.getInt(3); // Số lượng
        double price = r.getDouble(4); // Tổng giá
        ModelReceiptInfo1 data = new ModelReceiptInfo1(id_rec, name, count, price);
        list.add(data);
    }
    r.close();
    p.close();
    return list;
}

     
     
     
}
