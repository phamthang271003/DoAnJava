/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelDeliveryBill;
import Restaurant.Model.ModelDeliveryBillInfo;
import Restaurant.Model.ModelEmployee;
import Restaurant.Model.ModelReceipt;
import Restaurant.Model.ModelReceiptInfo;
import Restaurant.Model.Modelngredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author quangthang
 */
public class ServiceStaffWarehouse {

    private final Connection con;

    public ServiceStaffWarehouse() {
        DatabaseConnection.getInstance().connectToDatabase();
        con = DatabaseConnection.getInstance().getConnection();
    }

    //Lấy thông tin nhân viên từ ID người dùng
    public ModelEmployee getStaff(int userID) throws SQLException {
        ModelEmployee data = null;
        String sql = "SELECT * FROM Employee WHERE ID_Emp=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, userID);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id_NV = r.getInt("ID_Emp");
            String tenNV = r.getString("Name");
            String ngayVL = r.getString("DateOfWork");
            Date dateOfWork = null;
            try {
                dateOfWork = new SimpleDateFormat("yyyy-MM-dd").parse(ngayVL);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String sdt = r.getString("PhoneNumber");
            String chucvu = r.getString("Position");
            int id_User = r.getInt("ID_User");
            int id_NQL = r.getInt("ID_Manager");
            String status = r.getString("Status");
            data = new ModelEmployee(id_NV, tenNV, dateOfWork, sdt, chucvu, id_User, id_NQL, status);
        }
        r.close();
        p.close();
        return data;
    }

    //Lấy toàn bộ danh sách nguyên liệu
    public ArrayList<Modelngredient> MenuIngr() throws SQLException {
        ArrayList<Modelngredient> list = new ArrayList<>();
        String sql = "SELECT * FROM [ingredient] ORDER BY ID_Ingr";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int iD_Ingr = r.getInt(1);  //Mã nguyên liệu
            String nameIngre = r.getString(2); //Tên nguyên liệu
            int price = r.getInt(3); //Đơn giá nhập nguyên liệu
            String unit = r.getString(4); //Đơn vị tính của nguyên liệu
            int quantityInstock = r.getInt(5);
            Modelngredient data = new Modelngredient(iD_Ingr, nameIngre, price, unit, quantityInstock);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }

    //Lấy thông tin của nguyên liệu theo ID
    public Modelngredient getNLbyID(int idNL) throws SQLException {
        Modelngredient data = null;
        String sql = "SELECT * FROM [ingredient] WHERE ID_Ingr=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, idNL);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt(1);  //Mã nguyên liệu
            String tenNL = r.getString(2); //Tên nguyên liệu
            int donGia = r.getInt(3); //Đơn giá nhập nguyên liệu
            String dvt = r.getString(4); //Đơn vị tính của nguyên liệu
            int slTon = r.getInt(5);
            data = new Modelngredient(id, tenNL, donGia, dvt, slTon);
        }
        r.close();
        p.close();
        return data;
    }

    //Lấy ID của Nguyên Liệu tiếp theo được thêm
    public int getNextID_NL() throws SQLException {
        int nextID = 0;
        String sql = "SELECT MAX(ID_Ingr)  FROM [ingredient]";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            nextID = r.getInt(1) + 1;
        }
        r.close();
        p.close();
        return nextID;
    }

    //Thêm một nguyên liệu mới
    public void InsertNL(Modelngredient data) throws SQLException {
        String sql = "INSERT INTO ingredient(ID_Ingr,NameIngre,Price,unit,QuantityInStock) VALUES(?,?,?,?,?)";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, data.getiD_Ingr());
        p.setString(2, data.getNameIngre());
        p.setInt(3, data.getPrice());
        p.setString(4, data.getUnit());
        p.setInt(5, data.getQuantityInStock());
        p.execute();
        p.close();
    }

    //Xóa một nguyên liệu
    public void DeleteNL(Modelngredient data) throws SQLException {
        //Xóa nguyên liệu đó khỏi KHO
        String sql = "DELETE FROM FoodIngredient WHERE ID_Ingr = ?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, data.getiD_Ingr());
        p.execute();
        //Xóa nguyên liệu đó khỏi bảng NGUYENLIEU
        sql = "DELETE FROM ingredient WHERE ID_Ingr = ?";
        p = con.prepareStatement(sql);
        p.setInt(1, data.getiD_Ingr());
        p.execute();
        p.close();
    }

    //Sửa một nguyên liệu
    public void UpdateNL(Modelngredient data) throws SQLException {
        String sql = "UPDATE [ingredient] SET NameIngre = ?, Price = ?, unit = ?, QuantityInStock = ? WHERE ID_Ingr = ?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, data.getNameIngre());
        p.setInt(2, data.getPrice());
        p.setString(3, data.getUnit());
        p.setInt(4, data.getQuantityInStock());
        p.setInt(5, data.getiD_Ingr());
        p.execute();
        p.close();
    }

    //Lấy số lượng món ăn đang kinh doanh
    public int getNumberFood_inBusiness() throws SQLException {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Food WHERE Status='Con hang'";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            number = r.getInt(1);
        }
        return number;
    }

    //Lấy số lượng tồn trong kho
    public int getCountIngredient_QuantityInStock() throws SQLException {
        int sl = 0;
        String sql = "SELECT COUNT(*) FROM [ingredient] WHERE QuantityInStock>0";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            sl = r.getInt(1);
        }
        return sl;
    }
//Tính tổng tiền nhập kho

    public int getTotalPriceReceipt() throws SQLException {
        int totalPrice = 0;
        String sql = "SELECT SUM(TotalPrice) FROM [receipt]";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            totalPrice = r.getInt(1);
        }
        return totalPrice;
    }

    //Lấy thông tin phiếu nhập kho
    public ArrayList<ModelReceipt> MenuReceipt() throws SQLException {
        ArrayList<ModelReceipt> list = new ArrayList<>();
        String sql = "SELECT * FROM [receipt] ORDER BY ID_Rec";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int iD_Rec = r.getInt(1);
            int iD_Emp = r.getInt(2);
            String inputDayString = r.getString(3);
            int totalPrice = r.getInt(4);
            Date inputDay = null;
            try {
                inputDay = new SimpleDateFormat("yyyy-MM-dd").parse(inputDayString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ModelReceipt data = new ModelReceipt(iD_Rec, iD_Emp, inputDay, totalPrice);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }
//Lấy thông tin phiếu xuất kho
        public ArrayList<ModelDeliveryBill> MenuDelivery() throws SQLException {
        ArrayList<ModelDeliveryBill> list = new ArrayList<>();
        String sql = "SELECT * FROM [DeliveryBill] ORDER BY ID_Del";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int iD_Del = r.getInt(1);
            int iD_Emp = r.getInt(2);
            String inputDayString = r.getString(3);
            Date inputDay = null;
            try {
                inputDay = new SimpleDateFormat("yyyy-MM-dd").parse(inputDayString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ModelDeliveryBill data = new ModelDeliveryBill(iD_Del, iD_Emp, inputDay);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }
    //Lấy ID của Phiếu nhập kho tiếp theo được thêm
    public int getNextID_NK() throws SQLException {
        int nextID = 0;
        String sql = "SELECT MAX(ID_Rec)  FROM [receipt]";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            nextID = r.getInt(1) + 1;
        }
        r.close();
        p.close();
        return nextID;
    }
     //Lấy ID của Phiếu xuất  kho tiếp theo được thêm
    public int getNextID_XK() throws SQLException {
        int nextID = 0;
        String sql = "SELECT MAX(ID_Del)  FROM [DeliveryBill]";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            nextID = r.getInt(1) + 1;
        }
        r.close();
        p.close();
        return nextID;
    }

  
    //Thêm phiếu nhập kho và chi tiết Nhập kho
    public void InsertPNK_CTNK(ModelReceipt pnk, ArrayList<Modelngredient> list) throws SQLException {
        java.sql.Date sqlDate = new java.sql.Date(pnk.getInputDay().getTime());
        // Insert phiếu nhập kho
        String sql = "INSERT INTO receipt(ID_Rec,ID_Emp,InputDay) VALUES (?,?,?)";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, pnk.getID_Rec());
        p.setInt(2, pnk.getID_Emp());
        p.setDate(3, sqlDate);
        p.execute();

        // Insert chi tiết nhập kho
        String sql_ct;
        for (Modelngredient data : list) {
            if (data.getQuantityInStock() > 0) {
                sql_ct = "INSERT INTO[receiptInfo](ID_Rec,ID_Ingr,Count) VALUES (?,?,?)";
                PreparedStatement p_ct = con.prepareStatement(sql_ct);
                p_ct.setInt(1, pnk.getID_Rec());
                p_ct.setInt(2, data.getiD_Ingr());
                p_ct.setInt(3, data.getQuantityInStock());
                p_ct.execute();
                p_ct.close();
            }
        }
        p.close();
    }
  //Thêm phiếu  xuất kho và chi tiết xuất kho
    public void InsertPXK_CTXK(ModelDeliveryBill pxk, ArrayList<Modelngredient> list) throws SQLException {
        java.sql.Date sqlDate = new java.sql.Date(pxk.getInputDay().getTime());
        // Insert phiếu nhập kho
        String sql = "INSERT INTO DeliveryBill(ID_Del,ID_Emp,InputDay) VALUES (?,?,?)";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, pxk.getID_Del());
        p.setInt(2, pxk.getId_Emp());
        p.setDate(3, sqlDate);
        p.execute();

        // Insert chi tiết xuất kho
        String sql_ct;
        for (Modelngredient data : list) {
            if (data.getQuantityInStock() > 0) {
                sql_ct = "INSERT INTO [DeliveryBillInfo](ID_Del,ID_Ingr,Count) VALUES (?,?,?)";
                PreparedStatement p_ct = con.prepareStatement(sql_ct);
                p_ct.setInt(1, pxk.getID_Del());
                p_ct.setInt(2, data.getiD_Ingr());
                p_ct.setInt(3, data.getQuantityInStock());
                p_ct.execute();
                p_ct.close();
            }
        }
        p.close();
    }
    //Lấy thông tin của Phiếu nhập kho theo ID
    public ModelReceipt getPNKbyID(int id) throws SQLException, ParseException {
        ModelReceipt data = null;
        String sql = "SELECT ID_Rec,ID_Emp,InputDay AS Ngay,TotalPrice FROM receipt  WHERE ID_Rec=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int idNK = r.getInt(1);
            int idNV = r.getInt(2);
            String ngayNK = r.getString(3);
            int tongTien = r.getInt(4);

            // Parsing the string date to a Date object
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date inputDay = sdf.parse(ngayNK);

            data = new ModelReceipt(idNK, idNV, inputDay, tongTien);
        }
        r.close();
        p.close();
        return data;
    }
     //Lấy thông tin của Phiếu xuất kho theo ID
    public ModelDeliveryBill getPXKbyID(int id) throws SQLException, ParseException {
        ModelDeliveryBill data = null;
        String sql = "SELECT ID_Del,ID_Emp,InputDay AS Ngay FROM DeliveryBill  WHERE ID_Del=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int idXK = r.getInt(1);
            int idNV = r.getInt(2);
            String ngayNK = r.getString(3);

            // Parsing the string date to a Date object
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date inputDay = sdf.parse(ngayNK);

            data = new ModelDeliveryBill(idXK, idNV, inputDay);
        }
        r.close();
        p.close();
        return data;
    }

    //Lấy danh sách chi tiết nhập kho theo mã nhập kho
    public ArrayList<ModelReceiptInfo> getCTNK(int idnk) throws SQLException {
        ArrayList<ModelReceiptInfo> list = new ArrayList<>();
        String sql = "SELECT ID_Rec,rec.ID_Ingr, NameIngre, unit, Count, TotalPrice FROM receiptInfo rec "
                + "JOIN ingredient ing ON ing.ID_Ingr=rec.ID_Ingr WHERE ID_Rec=? ORDER BY ID_Rec";

        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, idnk);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int ID_NK = r.getInt(1);
            int ID_NL = r.getInt(2);
            String tenNL = r.getString(3);
            String dvt = r.getString(4);
            int soluong = r.getInt(5);
            int thanhTien = r.getInt(6);
            ModelReceiptInfo data = new ModelReceiptInfo(ID_NK, ID_NL, tenNL, dvt, soluong, thanhTien);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }
     //Lấy danh sách chi tiết xuất kho theo mã xuất kho
    public ArrayList<ModelDeliveryBillInfo> getCTXK(int idxk) throws SQLException {
        ArrayList<ModelDeliveryBillInfo> list = new ArrayList<>();
        String sql = "SELECT ID_Del,del.ID_Ingr, NameIngre, unit, Count FROM DeliveryBillInfo del "
                + "JOIN ingredient ing ON ing.ID_Ingr=del.ID_Ingr WHERE ID_Del=? ORDER BY ID_Del";

        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, idxk);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int ID_XK = r.getInt(1);
            int ID_NL = r.getInt(2);
            String tenNL = r.getString(3);
            String dvt = r.getString(4);
            int soluong = r.getInt(5);
            ModelDeliveryBillInfo data = new ModelDeliveryBillInfo(ID_XK, ID_NL, tenNL, dvt, soluong);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }
}
