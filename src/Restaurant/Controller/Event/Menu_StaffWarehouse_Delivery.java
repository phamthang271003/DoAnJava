/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Event;

import Restaurant.Controller.Service.ServiceStaffWarehouse;
import Restaurant.Model.ModelDeliveryBill;
import Restaurant.View.Component.WarehouseStaff.FormWareHouseStaff;
import Restaurant.View.Form.WarehouseStaff.Table_Delivery;
import Restaurant.View.Form.WarehouseStaff.Table_DeliveryBillInfo;
import Restaurant.View.Form.WarehouseStaff.Table_InsertDelivery;
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
public class Menu_StaffWarehouse_Delivery implements ActionListener {

    private Table_Delivery table_Delivery;
    private ServiceStaffWarehouse service;
    private Table_DeliveryBillInfo table_DeliveryBillInfo;

    public Menu_StaffWarehouse_Delivery(Table_Delivery table_Delivery) {
        this.table_Delivery = table_Delivery;
        this.service = new ServiceStaffWarehouse();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == table_Delivery.getBtnThemPXK()) {
            Table_InsertDelivery insertDelivery = new Table_InsertDelivery(table_Delivery.getModelUser(), table_Delivery.getModelEmployee(), table_Delivery);
            FormWareHouseStaff.showForm((insertDelivery));
        } else if (e.getSource() == table_Delivery.getBtnChiTietPXK()) {
            try {
                int row = table_Delivery.getTable().getSelectedRow();
                if (row != -1) {
                    int id = (int) table_Delivery.getTable().getValueAt(row, 0);
                    ModelDeliveryBill data = service.getPXKbyID(id);
                    if (data != null) {
                        table_DeliveryBillInfo = new Table_DeliveryBillInfo(table_Delivery.getModelUser(), data, table_Delivery);
                        FormWareHouseStaff.showForm(table_DeliveryBillInfo);
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
