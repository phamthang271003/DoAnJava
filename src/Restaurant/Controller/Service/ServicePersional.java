package Restaurant.Controller.Service;
import Restaurant.Controller.Connection.DatabaseConnection;
import Restaurant.Model.ModelPersional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ServicePersional {
     private final Connection con;
     //private Connection connection;
     public ServicePersional(){
           DatabaseConnection.getInstance().connectToDatabase(); 
          con = DatabaseConnection.getInstance().getConnection();
     }
     
     
    public void addNewPersional(String nameEmp, Date dateofwork, String phoneNumber, String position,int ID_Manager, String status) throws SQLException {
    String sqlSelectLastID = "SELECT TOP 1 ID_Emp FROM Employee ORDER BY ID_Emp DESC";
    PreparedStatement pSelect = con.prepareStatement(sqlSelectLastID);
    ResultSet r = pSelect.executeQuery();
    int lastID = 0;
    if (r.next()) {
        lastID = r.getInt(1);
    }
    r.close();
    pSelect.close();
    
    int newID = lastID + 1;

    String sql = "INSERT INTO Employee (ID_Emp, Name, DateOfWork, PhoneNumber, Position,ID_Manager, Status) VALUES (?, ?, ?, ?, ?, ?,?)";
    PreparedStatement p = con.prepareStatement(sql);
    p.setInt(1, newID);
    p.setString(2, nameEmp);
    p.setDate(3, new java.sql.Date(dateofwork.getTime()));
    p.setString(4, phoneNumber);
    p.setString(5, position);
    p.setInt(6, ID_Manager);
    p.setString(7, status);
    p.executeUpdate();
    p.close();
}

     
     
     
         //Lấy toàn bộ danh sách nguyên liệu
    public ArrayList<ModelPersional> listEmp() throws SQLException {
        ArrayList<ModelPersional> list = new ArrayList<>();
        String sql = "SELECT ID_Emp,Name,DateOfWork,PhoneNumber,Position,Status FROM [Employee] ORDER BY ID_Emp";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int iD_Emp = r.getInt(1);  //Mã nhan vien           
            String nameEmp = r.getString(2); //Tên nhan vien
            Date dateofwork = r.getDate(3); // Ngay vao lam
            String phoneNumber = r.getString(4);// SDT
            String position = r.getString(5);// vi tri lam
            String status = r.getString(6); //tinh trang lam
            ModelPersional data = new ModelPersional(iD_Emp, nameEmp,dateofwork,phoneNumber,position,status);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }
    
    
    public ArrayList<ModelPersional> listEmp2(int maNV) throws SQLException {
    ArrayList<ModelPersional> list = new ArrayList<>();
    String sql = "SELECT ID_Emp, Name, DateOfWork, PhoneNumber, Position, Status FROM [Employee] WHERE ID_Emp = ?";
    PreparedStatement p = null;
    ResultSet r = null;
    
    try {
        p = con.prepareStatement(sql);
        p.setInt(1, maNV); // Truyền tham số maNV vào câu lệnh SQL
        r = p.executeQuery();
        
        while (r.next()) {
            int iD_Emp = r.getInt(1);  // Mã nhân viên
            String nameEmp = r.getString(2); // Tên nhân viên
            Date dateofwork = r.getDate(3); // Ngày vào làm
            String phoneNumber = r.getString(4); // Số điện thoại
            String position = r.getString(5); // Vị trí làm việc
            String status = r.getString(6); // Tình trạng làm việc

            ModelPersional data = new ModelPersional(iD_Emp, nameEmp, dateofwork, phoneNumber, position, status);
            list.add(data);
        }
    } finally {
        if (r != null) {
            r.close();
        }
        if (p != null) {
            p.close();
        }
    }
    
    return list;
}

    
    public void deleteEmployee(int employeeID) throws SQLException {
    // Chuỗi truy vấn SQL để xóa nhân viên từ bảng Employee với điều kiện là ID_Emp
    String query = "DELETE FROM Employee WHERE ID_Emp = ?";
    
    
    try (PreparedStatement statement = con.prepareStatement(query)) {
        statement.setInt(1, employeeID);
        
        statement.executeUpdate();
    }
}
    public void updateEmployee(int employeeID, String nameEmp, Date dateOfWork, String phoneNumber, String position,int ID_Manager, String status) throws SQLException {
    
    String query = "UPDATE Employee SET Name = ?, DateOfWork = ?, PhoneNumber = ?, Position = ?,ID_Manager = ?, Status = ? WHERE ID_Emp = ?";
    
    
    try (PreparedStatement statement = con.prepareStatement(query)) {
        statement.setString(1, nameEmp);
        statement.setDate(2, new java.sql.Date(dateOfWork.getTime()));
        statement.setString(3, phoneNumber);
        statement.setString(4, position);
        statement.setInt(5, ID_Manager);
        statement.setString(6, status);
        statement.setInt(7, employeeID);
        
        // Thực hiện truy vấn cập nhật
        statement.executeUpdate();
    }
}

}
