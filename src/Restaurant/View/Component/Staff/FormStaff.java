/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.View.Component.Staff;



import Restaurant.View.Swing.Dashboard.PanelSlider;
import Restaurant.View.Swing.Dashboard.SimpleTransition;
import Restaurant.View.Swing.Dashboard.UndoRedo;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author quangthang
 */
public class FormStaff {
      private static FormStaff instance;
    private final JFrame frame;

    private final UndoRedo<SimpleFormStaff> forms = new UndoRedo<>();

    private boolean menuShowing = true;
    private final PanelSlider panelSlider;
    private final MainFormStaff mainForm;
    private final MenuStaff menu;

    public static void install(JFrame frame) {
       if (instance == null) {
            instance = new FormStaff(frame);
        }
    }

    private FormStaff(JFrame frame) {
        this.frame = frame;
        panelSlider = new PanelSlider();
        mainForm = new MainFormStaff();
        menu = new MenuStaff(new MyDrawerBuilderStaff());
        this.frame.getContentPane().add(panelSlider);
    }

    public static void showMenu() {
        instance.menuShowing = true;
        instance.panelSlider.addSlide(instance.menu, SimpleTransition.getShowMenuTransition(instance.menu.getDrawerBuilder().getDrawerWidth()));
    }

    public static void showForm(SimpleFormStaff component) {
        if (isNewFormAble()) {
            instance.forms.add(component);
            if (instance.menuShowing == true) {
                instance.menuShowing = false;
                Image oldImage = instance.panelSlider.createOldImage();
                instance.mainForm.setForm(component);
                instance.panelSlider.addSlide(instance.mainForm, SimpleTransition.getSwitchFormTransition(oldImage, instance.menu.getDrawerBuilder().getDrawerWidth()));
            } else {
                instance.mainForm.showForm(component);
            }
            instance.forms.getCurrent().formInitAndOpen();
        }
    }

    public static void hideMenu() {
        instance.menuShowing = false;
        instance.panelSlider.addSlide(instance.mainForm, SimpleTransition.getHideMenuTransition(instance.menu.getDrawerBuilder().getDrawerWidth()));
    }

    public static void undo() {
        if (isNewFormAble()) {
            if (!instance.menuShowing && instance.forms.isUndoAble()) {
                instance.mainForm.showForm(instance.forms.undo());
                instance.forms.getCurrent().formOpen();
            }
        }
    }

    public static void redo() {
        if (isNewFormAble()) {
            if (!instance.menuShowing && instance.forms.isRedoAble()) {
                instance.mainForm.showForm(instance.forms.redo());
                instance.forms.getCurrent().formOpen();
            }
        }
    }

    public static void refresh() {
        if (!instance.menuShowing) {
            instance.forms.getCurrent().formRefresh();
        }
    }

    public static UndoRedo<SimpleFormStaff> getForms() {
        return instance.forms;
    }

    public static boolean isNewFormAble() {
        return instance.forms.getCurrent() == null || instance.forms.getCurrent().formClose();
    }

    public static void updateTempFormUI() {
        for (SimpleFormStaff f : instance.forms) {
            SwingUtilities.updateComponentTreeUI(f);
        }
    }
}
