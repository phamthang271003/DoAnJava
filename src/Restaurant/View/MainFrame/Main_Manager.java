/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.View.MainFrame;

import Restaurant.View.Component.Dashboard.Background;
import Restaurant.View.Component.Manager.FormManager;
import Restaurant.View.Form.Manager.DashboardFormManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;
import raven.popup.GlassPanePopup;

/**
 *
 * @author quangthang
 */
public class Main_Manager extends JFrame{
    public Main_Manager(){
        init();
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
        FormManager.install(this);
        FormManager.showForm(new DashboardFormManager());
       
        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }
    public static void main(String[] args) {
       FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("Restaurant.Themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        //FlatMacLightLaf.setup();
        FlatDarkLaf.setup();
        EventQueue.invokeLater(() -> new Main_Manager().setVisible(true));
    }
}
