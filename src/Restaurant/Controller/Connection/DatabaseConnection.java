/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author quangthang
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    //rtả về instance duy nhất của lớp
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
//khong cho tao instance moi tu ben ngoai
    private DatabaseConnection() {

    }
//Thực hiện kết nối tới Database

    public void connectToDatabase() {
        String strServer = "LAPTOP-5KVBJE6O\\SQLEXPRESS";
        String strDatabase = "QLNH";

        String userName = "sa";
        String passWord = "123";
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driver);
            String connectionURL = "jdbc:sqlserver://" + strServer + ":1433;databaseName=" + strDatabase
                    + ";encrypt=true;trustServerCertificate=true";
            connection = DriverManager.getConnection(connectionURL, userName, passWord);
   
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
//  	Connection connection = null;
//	Statement statement = null;
//	public DatabaseConnection() {
//	
//		String strServer = "LAPTOP-5KVBJE6O\\SQLEXPRESS";
//		String strDatabase = "QLNH";
//		String userName = "sa";
//		String passWord = "123";
//		try {
//			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//			Class.forName(driver);
//			String connectionURL = "jdbc:sqlserver://" + strServer + ":1433;databaseName=" + strDatabase
//					+ ";encrypt=true;trustServerCertificate=true";
//			connection = DriverManager.getConnection(connectionURL,userName,passWord);
//			String sql = "select * from FoodCategory";
//			statement = connection.createStatement();
//			
//			ResultSet rs = statement.executeQuery(sql);
//			while(rs.next()) {
//				System.out.println(rs.getString(1));
//				System.out.println(rs.getString(2));
//				
//			}
//			if (connection != null) {
//				System.out.println("Kết nối cơ sở dữ liệu đã được thiết lập.");
//			} else {
//				System.out.println("Đã xảy ra lỗi khi kết nối đến cơ sở dữ liệu.");
//			}
//		} catch (ClassNotFoundException | SQLException ex) {
//			ex.printStackTrace();
//		}
//
//	}
}
