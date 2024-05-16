/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Event;

import Restaurant.Controller.Service.ServiceStaffWarehouse;
import Restaurant.Model.ModelReceipt;
import Restaurant.View.Component.WarehouseStaff.FormWareHouseStaff;
import Restaurant.View.Form.WarehouseStaff.Table_InsertReceipt;
import Restaurant.View.Form.WarehouseStaff.Table_Receipt;
import Restaurant.View.Form.WarehouseStaff.Table_ReceiptInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;

/**
 *
 * @author quangthang
 */
public class Menu_StaffWarehouse_Receipt implements ActionListener {

    private Table_Receipt table_Receipt;
    private ServiceStaffWarehouse service;
    private Table_ReceiptInfo tableReceiptInfo;

    public Menu_StaffWarehouse_Receipt(Table_Receipt table_Receipt) {
        this.table_Receipt = table_Receipt;
        this.service = new ServiceStaffWarehouse();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == table_Receipt.getBtnThemPNK()) {
            Table_InsertReceipt insertReceiptInfo = new Table_InsertReceipt(table_Receipt.getModelUser(), table_Receipt.getModelEmployee(), table_Receipt);
            FormWareHouseStaff.showForm((insertReceiptInfo));
        } else if (e.getSource() == table_Receipt.getBtnChiTietPNK()) {
            try {
                int row = table_Receipt.getTable().getSelectedRow();
                if (row != -1) {
                    int id = (int) table_Receipt.getTable().getValueAt(row, 0);
                    ModelReceipt data = service.getPNKbyID(id);
                    if (data != null) {
                        tableReceiptInfo = new Table_ReceiptInfo(table_Receipt.getModelUser(), data, table_Receipt);
                        FormWareHouseStaff.showForm(tableReceiptInfo);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để cập nhật.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi cập nhật: " + ex.getMessage());
                ex.printStackTrace();
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(null, "Lỗi xảy ra khi phân tích: " + parseException.getMessage());
                parseException.printStackTrace();
            }
        }
    }

}
