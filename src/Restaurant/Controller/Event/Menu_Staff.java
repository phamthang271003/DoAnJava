/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Event;

import Restaurant.Controller.Service.ServiceStaff;
import Restaurant.Model.ModelFood;
import Restaurant.View.Component.Staff.FormStaff;
import Restaurant.View.Dialog.MS_CancelReserve;
import Restaurant.View.Dialog.MS_ConfirmReserve;
import Restaurant.View.Form.Staff.TableFood;
import Restaurant.View.Form.Staff.TableS;
import Restaurant.View.MainFrame.Main_Staff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.System.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JComboBox;

/**
 *
 * @author quangthang
 */
public class Menu_Staff implements ActionListener, ItemListener {

    
    private ServiceStaff service;
    
    private TableFood tb;
    private TableS tableS;
    private MS_ConfirmReserve book;
    private MS_CancelReserve cancel;

    public Menu_Staff(TableS tableS, TableFood tb) {
        this.tableS = tableS;
        this.tb=tb;
        this.book = new MS_ConfirmReserve(Main_Staff.getFrames()[0], true);
        this.cancel = new MS_CancelReserve(Main_Staff.getFrames()[0], true);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tableS.getBtnDatBan()) {   
             handleReservation();
        
        } else if (e.getSource() == tableS.getBtnGoiMon()) {
            Menu_Staff menuStaff = new Menu_Staff(tableS, tb); // Ví dụ về cách tạo đối tượng Menu_Staff
            TableFood tableFood = null; 
           
            try {
                tableFood = new TableFood(menuStaff);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Menu_Staff.class.getName()).log(Level.SEVERE, null, ex);
            }
           
             FormStaff.showForm(tableFood);
             
             System.out.printf("Mã bàn:  "+ tableS.getTable().getID_Table());
             tableFood.setMaBanText(" "+ tableS.getTable().getID_Table());
             tableFood.setTenBanText(" " + tableS.getTable().getTableName());
             tableFood.setTangText(" "+tableS.getTable().getLocation());
        }
       
    }
    
   public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
        JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
        int selectedIndex = comboBox.getSelectedIndex();
        System.out.println("Selected index: " + selectedIndex);
        
        // Không cần tạo một đối tượng mới của TableFood, sử dụng đối tượng hiện tại (this.tb)
        if (this.tb != null && this.tb.getCboLoaiTA() != null) {
            String selectedFoodCategory = this.tb.getCBOSelection(selectedIndex);
            String[] split = selectedFoodCategory.split("\\.");
            ArrayList<ModelFood> list;
            try {
                // Gọi phương thức setTable trên đối tượng hiện tại để cập nhật bảng
                this.service = new ServiceStaff();
                System.out.println("SelectedItem: " + split[0]);
                list = this.service.listFoodFromCate(split[0]);
                this.tb.setTable(list);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Menu_Staff.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    } 
    
  private void handleReservation() {
        String status = tableS.getTable().getStatus();
        if (status.equals("Con trong")) {
            book.ConfirmReserve(tableS.getTable(),tableS);
        } else if (status.equals("Da dat truoc")) {
            cancel.CancelReserve(tableS.getTable(),tableS);
        }
    }


}
