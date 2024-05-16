/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Event;

import java.sql.SQLException;
import Restaurant.Controller.Service.ServiceStaffWarehouse;
import Restaurant.Model.Modelngredient;
import Restaurant.View.Component.WarehouseStaff.FormWareHouseStaff;
import Restaurant.View.Dialog.MS_DeleteIngr;
import Restaurant.View.Form.WarehouseStaff.Table_InsertAndUpdate_Ingredient;
import Restaurant.View.Form.WarehouseStaff.Table_Receipt;
import Restaurant.View.Form.WarehouseStaff.Table_lngredientInfo;
import Restaurant.View.MainFrame.Main_Warehouse_Staff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author quangthang
 */
public class Menu_StaffWarehouse implements ActionListener {

    private Table_lngredientInfo table_lngredientInfo;
    private Table_Receipt table_ReceiptInfo;
    private Table_InsertAndUpdate_Ingredient table_InsertAndUpdate_Ingredient;
    private MS_DeleteIngr deleteIngr;
    private ServiceStaffWarehouse service;
    private Modelngredient data;

    public Menu_StaffWarehouse(Table_lngredientInfo table_lngredientInfo) {
        this.table_lngredientInfo = table_lngredientInfo;
        this.deleteIngr = new MS_DeleteIngr(Main_Warehouse_Staff.getFrames()[0], true);
        this.service = new ServiceStaffWarehouse();
        this.data = new Modelngredient();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == table_lngredientInfo.getBtnThem()) {
            handleAddButton();
        } else if (e.getSource() == table_lngredientInfo.getBtnXoa()) {
            handleDeleteButton();
        } else if (e.getSource() == table_lngredientInfo.getBtnSua()) {
            handleEditButton();
        }
       else if (e.getSource() == table_ReceiptInfo.getBtnThemPNK()) {
            System.out.println("Ban da nhan nut them phieu nhap kho");
        }
    }

    private void handleAddButton() {
        FormWareHouseStaff.showForm(new Table_InsertAndUpdate_Ingredient(null));
    }

    private void handleDeleteButton() {
        try {
            int row = table_lngredientInfo.getTable().getSelectedRow();
            if (row != -1) {
                int id = (int) table_lngredientInfo.getTable().getValueAt(row, 0);
                //Lấy thông tin nguyên liệu theo id
                Modelngredient data = service.getNLbyID(id);
                if (data != null && deleteIngr.Staff_ConfirmDelete(data)) {
                    DefaultTableModel model = (DefaultTableModel) table_lngredientInfo.getTable().getModel();
                    model.removeRow(row);
                    //Vẽ lại bảng
                    table_lngredientInfo.getTable().repaint();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xóa: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void handleEditButton() {
        try {
            int row = table_lngredientInfo.getTable().getSelectedRow();
            if (row != -1) {
                int id = (int) table_lngredientInfo.getTable().getValueAt(row, 0);
                Modelngredient data = service.getNLbyID(id);
                if (data != null) {
                    table_InsertAndUpdate_Ingredient = new Table_InsertAndUpdate_Ingredient(data);
                    table_InsertAndUpdate_Ingredient.setData(data);
                    FormWareHouseStaff.showForm(table_InsertAndUpdate_Ingredient);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để cập nhật.");
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi cập nhật: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
