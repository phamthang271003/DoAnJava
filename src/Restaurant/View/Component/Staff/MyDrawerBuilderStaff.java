/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.View.Component.Staff;

import Restaurant.Controller.Service.ServiceSignInUp;
import Restaurant.View.Form.Staff.DashboardFormStaff;
import Restaurant.View.Form.Staff.Table;
import Restaurant.View.Form.Staff.TableFood;
import Restaurant.View.Form.Staff.Table_CustomersInfo;

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
public class MyDrawerBuilderStaff extends SimpleDrawerBuilder {

    private final ThemesChange themesChange;
    private ServiceSignInUp service;
    

    public MyDrawerBuilderStaff() {
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
                .setTitle(emailName)
                .setDescription(email)
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
            new Item.Label("MAIN STAFF"),
            new Item("Dashboard", "dashboard.svg"),
            new Item.Label("WEB APP"),
            new Item("Menu", "email.svg")
            .subMenu("Tầng 1")
            .subMenu("Tầng 2")
            .subMenu("Tầng 3")
            .subMenu("Tầng 4"),
            new Item("Quản lý bàn  ", "email.svg")
            .subMenu("Tầng 1")
            .subMenu("Tầng 2")
            .subMenu("Tầng 3")
            .subMenu("Tầng 4"),
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
                        FormStaff.showForm(new DashboardFormStaff());
                    }

                    if (index[0] == 3) {
                        FormStaff.showForm(new Table_CustomersInfo());
                    }
                } else if (index.length == 2) {

                    if (index[0] == 1) {
                        if (index[1] == 0) {
                            FormStaff.showForm(new TableFood());
                        } else if (index[1] == 1) {

                        } else if (index[1] == 2) {

                        }
                    }
                    //Tạo mảng floors chứa 4 tầng
                    String[] floors = {"Tang 1", "Tang 2", "Tang 3", "Tang 4"};
                     //Nếu vị trí được chọn trên menu là 2 và các meu con nằm ở vị trí từ 0 -> 3 thì showform table theo tầng
                    if (index[0] == 2 && index[1] >= 0 && index[1] < floors.length) {
                        FormStaff.showForm(new Table(floors[index[1]]));
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
