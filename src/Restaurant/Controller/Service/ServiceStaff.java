/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author quangthang
 */
public class ServiceStaff {

    private final Connection con;

    public ServiceStaff() {
        DatabaseConnection.getInstance().connectToDatabase();
        con = DatabaseConnection.getInstance().getConnection();
    }
//Lấy danh sách bàn theo tầng
    public ArrayList<ModelTable> listTable(String floor) throws SQLException {
        ArrayList<ModelTable> list = new ArrayList<>();
        String sql = "SELECT ID_Table, TableName, Location, Status FROM [Table] WHERE Location = ? ";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, floor);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int ID_Table = r.getInt("ID_Table");
            String TableName = r.getString("TableName");
            String Location = r.getString("Location");
            String Status = r.getString("Status");
            ModelTable data = new ModelTable(ID_Table, TableName, Location, Status);
            list.add(data);
        }

        r.close();
        p.close();
        return list;
    }
    //Điều chỉnh trạng thái bàn thành Đã đặt trước sau khi nhân viên xác nhận

    public void setTableReserve(int idBan) throws SQLException {
        String sql = "UPDATE [Table] SET Status = 'Da dat truoc' WHERE ID_Table=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, idBan);
        p.execute();
        p.close();
    }

    //Hủy trạng thái bàn đã Đặt trước trước thành Còn trống
    public void CancelTableReserve(int idBan) throws SQLException {
        String sql = "UPDATE [Table] SET Status = 'Con trong' WHERE ID_Table=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, idBan);
        p.execute();
        p.close();
    }
        //Tìm hóa đơn có trạng thái Chưa thanh toán  dựa vào trạng mã Bàn
//    public ModelHoaDon FindHoaDonbyID_Ban(ModelBan table) throws SQLException {
//        ModelHoaDon hoadon = null;
//        String sql = "SELECT ID_HoaDon,ID_KH,ID_Ban,to_char(NgayHD,'dd-mm-yyyy') AS Ngay,TienMonAn,Code_Voucher,TienGiam,Tongtien,Trangthai FROM HoaDon "
//                + "WHERE ID_Ban=? AND Trangthai='Chua thanh toan'";
//        PreparedStatement p = con.prepareStatement(sql);
//        p.setInt(1, table.getID());
//        ResultSet r = p.executeQuery();
//        while (r.next()) {
//            int idHoaDon = r.getInt(1);
//            int idKH = r.getInt(2);
//            int idBan = r.getInt(3);
//            String ngayHD = r.getString(4);
//            int tienMonAn = r.getInt(5);
//            String code_voucher = r.getString(6);
//            int tienGiam = r.getInt(7);
//            int tongtien = r.getInt(8);
//            String trangthai = r.getString(9);
//            hoadon = new ModelHoaDon(idHoaDon, idKH, idBan, ngayHD, tienMonAn, code_voucher, tienGiam, tongtien, trangthai);
//        }
//        r.close();
//        p.close();
//        return hoadon;
//    }
//
//    //Cập nhật trạng thái Hóa đơn thành Đã thanh toán khi Nhân viên xác nhận thanh toán
//    public void UpdateHoaDonStatus(int idHD) throws SQLException {
//        String sql = "UPDATE HoaDon SET TrangThai = 'Da thanh toan' WHERE ID_HoaDon=?";
//        PreparedStatement p = con.prepareStatement(sql);
//        p.setInt(1, idHD);
//        p.execute();
//        p.close();
//    }
}
