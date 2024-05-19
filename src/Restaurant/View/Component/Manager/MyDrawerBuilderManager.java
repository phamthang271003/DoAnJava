/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.View.Component.Manager;

import Restaurant.Controller.Service.ServiceSignInUp;
import Restaurant.View.Form.Manager.DashboardFormManager;
import Restaurant.View.Form.Manager.Table_BillInfo;
import Restaurant.View.Form.Manager.Table_CustomerInfo;
import Restaurant.View.Form.Manager.Table_PersonnelInfo;
import Restaurant.View.Form.Manager.Table_menuInfo;
import Restaurant.View.Form.Manager.Table_receipt;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import raven.drawer.component.DrawerPanel;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.header.SimpleHeaderStyle;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.drawer.component.menu.SimpleMenuStyle;
import raven.drawer.component.menu.data.Item;
import raven.drawer.component.menu.data.MenuItem;
import raven.swing.AvatarIcon;

/**
 *
 * @author quangthang
 */
public class MyDrawerBuilderManager extends SimpleDrawerBuilder {

    private final ThemesChange themesChange;

    private ServiceSignInUp service;
    
    public MyDrawerBuilderManager() {
        themesChange = new ThemesChange();
    }

    @Override
    public Component getFooter() {
        return themesChange;
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        service=new ServiceSignInUp();
        String email=service.Email;
        String emailName=service.EmailName;
        AvatarIcon icon = new AvatarIcon(getClass().getResource("/Icons/Warehouse/profile.png"), 60, 60, 999);
        icon.setBorder(2);
        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle(email)
                .setDescription(emailName)
                .setHeaderStyle(new SimpleHeaderStyle() {

                    @Override
                    public void styleTitle(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#FAFAFA");
                    }

                    @Override
                    public void styleDescription(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#E1E1E1");
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData();
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {

        MenuItem items[] = new MenuItem[]{
            new Item.Label("MAIN MANAGER"),
            new Item("Báo cáo doanh thu", "dashboard.svg"),
            new Item.Label("WEB APP"),
            
             new Item("Quản lý thực đơn", "calendar.svg"),
              new Item("Quản lý nhân sự", "calendar.svg"),
            
            new Item("Thống kê  ", "email.svg")
            .subMenu("Hóa đơn")
            .subMenu("Nhập kho"),
         
            new Item("Thông tin khách hàng", "calendar.svg"),
            new Item.Label("Thông tin cá nhân"),
            new Item("Tài khoản", "ui.svg"),
            new Item("Đăng xuất", "forms.svg"),};

        SimpleMenuOption simpleMenuOption = new SimpleMenuOption() {
            @Override
            public Icon buildMenuIcon(String path, float scale) {
                FlatSVGIcon icon = new FlatSVGIcon(path, scale);
                FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
                colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
                icon.setColorFilter(colorFilter);
                return icon;
            }
        };

        simpleMenuOption.setMenuStyle(new SimpleMenuStyle() {
            @Override
            public void styleMenuItem(JButton menu, int[] index) {
                menu.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:#FAFAFA;"
                        + "arc:10");
            }

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, ""
                        + "background:$Drawer.background");
            }

            @Override
            public void styleLabel(JLabel label) {
                label.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:darken(#FAFAFA,15%);"
                        + "[dark]foreground:darken($Label.foreground,30%)");
            }
        });
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int[] index) {
                if (index.length == 1) {

                    if (index[0] == 0) {
                        FormManager.showForm(new DashboardFormManager());
                    }
                      if (index[0] == 1) {
                     FormManager.showForm(new Table_menuInfo());
                    }
                    if (index[0] == 2) {
                     FormManager.showForm(new Table_PersonnelInfo());
                    }
                       if (index[0] == 4) {
                         FormManager.showForm(new Table_CustomerInfo());
                    }
                } else if (index.length == 2) {

                    if (index[0] == 3) {
                        if (index[1] == 0) {
                             FormManager.showForm(new Table_BillInfo());

                        } else if (index[1] == 1) {
                          FormManager.showForm(new Table_receipt());
                        }
                     
                    }

                }
            }
        });

        simpleMenuOption.setMenus(items)
                .setBaseIconPath("Icons/Warehouse")
                .setIconScale(0.45f);
        return simpleMenuOption;
    }

    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Drawer.background");
    }

    @Override
    public int getDrawerWidth() {
        return 270;
    }
}
