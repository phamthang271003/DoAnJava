package Restaurant.View.Component.Staff;


import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import raven.drawer.component.DrawerBuilder;
import raven.drawer.component.DrawerPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author quangthang
 */
public class MenuStaff extends JPanel{
       private final DrawerBuilder drawerBuilder;

    public DrawerBuilder getDrawerBuilder() {
        return drawerBuilder;
    }

    public MenuStaff(DrawerBuilder drawerBuilder) {
        this.drawerBuilder = drawerBuilder;
        init();
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Drawer.background");

        setLayout(new MigLayout("wrap,fill", "[fill," + drawerBuilder.getDrawerWidth() + "!]", "[fill]"));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    FormStaff.hideMenu();
                }
            }
        });
        DrawerPanel drawerPanel = new DrawerPanel(drawerBuilder);
        drawerPanel.addMouseListener(new MouseAdapter() {
        });
        drawerBuilder.build(drawerPanel);
        add(drawerPanel);
    }
}
