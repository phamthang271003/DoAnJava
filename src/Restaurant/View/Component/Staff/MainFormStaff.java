package Restaurant.View.Component.Staff;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import Restaurant.View.Swing.Dashboard.PanelSlider;
import Restaurant.View.Swing.Dashboard.SimpleTransition;

/**
 *
 * @author Raven
 */
public class MainFormStaff extends JPanel {

    public MainFormStaff() {
        init();
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "arc:30");
        setLayout(new MigLayout("wrap,fillx", "[fill]", ""));
        header = createHeader();
        panelSlider = new PanelSlider();
        JScrollPane scroll = new JScrollPane(panelSlider);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "width:10");
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        add(header);
        add(scroll);
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new MigLayout("insets 3"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null");

        cmdMenu = createButton(new FlatSVGIcon("Icons/Warehouse/menu.svg"));
        cmdUndo = createButton(new FlatSVGIcon("Icons/Warehouse/undo.svg"));
        cmdRedo = createButton(new FlatSVGIcon("Icons/Warehouse/redo.svg"));
        cmdRefresh = createButton(new FlatSVGIcon("Icons/Warehouse/refresh.svg"));
        cmdMenu.addActionListener(e -> {
            FormStaff.showMenu();
        });
        cmdUndo.addActionListener(e -> {
            FormStaff.undo();
        });
        cmdRedo.addActionListener(e -> {
            FormStaff.redo();
        });
        cmdRefresh.addActionListener(e -> {
            FormStaff.refresh();
        });

        panel.add(cmdMenu);
        panel.add(cmdUndo);
        panel.add(cmdRedo);
        panel.add(cmdRefresh);
        return panel;
    }

    private JButton createButton(Icon icon) {
        JButton button = new JButton(icon);
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Button.toolbar.background;"
                + "arc:10;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        return button;
    }

    public void showForm(Component component) {
        checkButton();
        panelSlider.addSlide(component, SimpleTransition.getDefaultTransition(false));
        revalidate();
    }

    public void setForm(Component component) {
        checkButton();
        panelSlider.addSlide(component, null);
    }

    private void checkButton() {
        cmdUndo.setEnabled(FormStaff.getForms().isUndoAble());
        cmdRedo.setEnabled(FormStaff.getForms().isRedoAble());
        cmdRefresh.setEnabled(FormStaff.getForms().getCurrent() != null);
    }

    private JPanel header;
    private JButton cmdMenu;
    private JButton cmdUndo;
    private JButton cmdRedo;
    private JButton cmdRefresh;
    private PanelSlider panelSlider;
}
