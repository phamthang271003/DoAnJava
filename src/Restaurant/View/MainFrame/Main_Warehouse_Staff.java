package Restaurant.View.MainFrame;

import Restaurant.View.Component.Dashboard.Background;
import Restaurant.View.Component.WarehouseStaff.FormWareHouseStaff;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;
import Restaurant.View.Form.WarehouseStaff.DashboardForm;
import raven.popup.GlassPanePopup;

/**
 *
 * @author Raven
 */
public class Main_Warehouse_Staff extends JFrame {

    public Main_Warehouse_Staff() {
        init();
//        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
//        dbConnection.connectToDatabase();
//
//        if (dbConnection.getConnection() != null) {
//            System.out.println("Kết nối SQL đã được thiết lập.");
//        } else {
//            System.out.println("Đã xảy ra lỗi khi kết nối đến cơ sở dữ liệu.");
//        }
    }

    private void init() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(1366, 768)));
        setLocationRelativeTo(null);
        setContentPane(new Background());
        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        GlassPanePopup.install(this);
        FormWareHouseStaff.install(this);
        FormWareHouseStaff.showForm(new DashboardForm());
        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

//    public static void main(String[] args) {
//        FlatRobotoFont.install();
//        FlatLaf.registerCustomDefaultsSource("Restaurant.Themes");
//        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
//        //FlatMacLightLaf.setup();
//        FlatDarkLaf.setup();
//        EventQueue.invokeLater(() -> new Main_Warehouse_Staff().setVisible(true));
//
//    }

}
