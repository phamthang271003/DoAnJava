package Restaurant.View.Component.WarehouseStaff;

import Restaurant.Controller.Service.ServiceSignInUp;
import Restaurant.Model.ModelUser;
import Restaurant.View.Form.WarehouseStaff.DashboardForm;
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
import Restaurant.View.Form.WarehouseStaff.Table_Delivery;
import Restaurant.View.Form.WarehouseStaff.Table_Receipt;
import Restaurant.View.Form.WarehouseStaff.Table_lngredientInfo;
import raven.swing.AvatarIcon;

/**
 *
 * @author Raven
 */
public class MyDrawerBuilder extends SimpleDrawerBuilder {

    private ServiceSignInUp service;
    private final ThemesChange themesChange;
    private Table_Receipt table_ReceiptInfo;

    public MyDrawerBuilder() {
        themesChange = new ThemesChange();
    }

    @Override
    public Component getFooter() {
        return themesChange;
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        service = new ServiceSignInUp();
        String email = service.Email;
        String emailName = service.EmailName;
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
            new Item.Label("MAIN"),
            new Item("Dashboard", "dashboard.svg"),
            new Item.Label("WEB APP"),
            new Item("Quản lý nguyên liệu", "chat.svg"),
            new Item("Quản lý kho", "email.svg")
            .subMenu("Nhập kho")
            .subMenu("Xuất kho"),
            //            .subMenu(
            //            new Item("Group Read")
            //            .subMenu("Read 1")
            //            .subMenu("Read 2")
            //            .subMenu(
            //            new Item("Group Item")
            //            .subMenu("Item 1")
            //            .subMenu("Item 2")
            //            .subMenu("Item 3")
            //            .subMenu("Item 4")
            //            .subMenu("Item 5")
            //            .subMenu("Item 6")
            //            )
            //            .subMenu("Read 3")
            //            .subMenu("Read 4")
            //            .subMenu("Read 5")
            //            )
            //            .subMenu("Compost"),

            new Item.Label("Thông tin cá nhân"),
            new Item("Tài khoản", "ui.svg"),
            //            .subMenu("Cropper")
            //            .subMenu("Owl Carousel")
            //            .subMenu("Sweet Alert"),
            new Item("Đăng xuất", "forms.svg"), //            .subMenu("Basic Elements")
        //            .subMenu("Advanced Elements")
        //            .subMenu("SEditors")
        //            .subMenu("Wizard"),
        //            new Item.Label("OTHER"),
        //            new Item("Charts", "chart.svg")
        //            .subMenu("Apex")
        //            .subMenu("Flot")
        //            .subMenu("Sparkline"),
        //            new Item("Icons", "icon.svg")
        //            .subMenu("Feather Icons")
        //            .subMenu("Flag Icons")
        //            .subMenu("Mdi Icons"),
        //            new Item("Special Pages", "page.svg")
        //            .subMenu("Blank page")
        //            .subMenu("Faq")
        //            .subMenu("Invoice")
        //            .subMenu("Profile")
        //            .subMenu("Pricing")
        //            .subMenu("Timeline")
        };

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
                        FormWareHouseStaff.showForm(new DashboardForm());
                    }
                    if (index[0] == 1) {
                        FormWareHouseStaff.showForm(new Table_lngredientInfo());
                    }
               
                } else if (index.length == 2) {

                    if (index[0] == 2) {
                        if (index[1] == 0) {
                            // FormWareHouseStaff.showForm(new InboxForm());
                            ModelUser modelUser = new ModelUser();
                            FormWareHouseStaff.showForm(new Table_Receipt(modelUser));
                        } else if (index[1] == 1) {
                             ModelUser modelUser = new ModelUser();
                            FormWareHouseStaff.showForm(new Table_Delivery(modelUser));
                        } else if (index[1] == 2) {

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
