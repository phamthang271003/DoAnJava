/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Restaurant.View.Form.WarehouseStaff;

import Restaurant.Controller.Service.ServiceStaffWarehouse;
import Restaurant.Model.ModelDeliveryBill;
import Restaurant.Model.ModelDeliveryBillInfo;
import Restaurant.Model.ModelReceipt;
import Restaurant.Model.ModelReceiptInfo;
import Restaurant.Model.ModelUser;
import Restaurant.View.Component.WarehouseStaff.SimpleForm;
import Restaurant.View.Component.Dashboard.SearchOptinEvent;
import Restaurant.View.Component.Dashboard.SearchOption;
import Restaurant.View.Component.WarehouseStaff.FormWareHouseStaff;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author quangthang
 */
public class Table_DeliveryBillInfo extends SimpleForm {

    private ServiceStaffWarehouse serviceWarehouse;
     private ArrayList<ModelDeliveryBillInfo> listDeliveryInfo;
    private ModelDeliveryBill  modelDeliveryBill;
    private ModelUser modelUser;
    private Table_Delivery  table_Delivery;
    private SimpleDateFormat simpleDateFormat;
    DecimalFormat df;

    /**
     * Creates new form Table
     */
    public Table_DeliveryBillInfo(ModelUser modelUser, ModelDeliveryBill  modelDeliveryBill,Table_Delivery table_Delivery) {

        this.modelUser = modelUser;
        this.modelDeliveryBill = modelDeliveryBill;
        this.table_Delivery = table_Delivery;
        initComponents();
        serviceWarehouse = new ServiceStaffWarehouse();
        simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        df = new DecimalFormat("#,###");
        initTable();
        setCurrentDate();

        //Search
        txt.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txt.setHint("Search by " + option.getName() + "...");
            }
        });
        txt.addOption(new SearchOption("Name", new ImageIcon(getClass().getResource("/Icons/Search/user.png"))));
        txt.addOption(new SearchOption("Tel", new ImageIcon(getClass().getResource("/Icons/Search/tel.png"))));
        txt.addOption(new SearchOption("Email", new ImageIcon(getClass().getResource("/Icons/Search/email.png"))));
        txt.addOption(new SearchOption("Address", new ImageIcon(getClass().getResource("/Icons/Search/address.png"))));

        //Table
        table.setDefaultRenderer(Object.class, new TableGradientCell());
        jPanel2.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background");
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:3,0,3,0,$Table.background,10,10");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverTrackColor:null");
    }

    public void setCurrentDate() {
        lbDate.setText("Ngày hiện tại: " + simpleDateFormat.format(new Date()));
    }

    public void initTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            listDeliveryInfo = serviceWarehouse.getCTXK(modelDeliveryBill.getID_Del());
     
            for (ModelDeliveryBillInfo data : listDeliveryInfo) {
                model.addRow(new Object[]{data.getID_Ingr(), data.getName_ingre(), data.getUnit(), data.getCount()});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txt = new Restaurant.View.Component.Dashboard.TextFieldSearchOption();
        jPanel2 = new javax.swing.JPanel();
        lbDate = new javax.swing.JLabel();
        lbTitle1 = new javax.swing.JLabel();
        btnThemPNK = new Restaurant.View.Swing.Login.ButtonOutLine();
        btnHuy = new Restaurant.View.Swing.Login.ButtonOutLine();

        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma NL", "Tên nguyên liệu", "Đơn vị tính", "Số lượng "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setEditingRow(1);
        table.setRowHeight(40);
        scroll.setViewportView(table);

        txt.setText("textFieldSearchOption1");

        lbDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbDate.setForeground(new java.awt.Color(108, 91, 123));
        lbDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbTitle1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTitle1.setForeground(new java.awt.Color(108, 91, 123));
        lbTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle1.setText("Chi tiết phiếu xuất kho");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(429, 429, 429)
                .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(lbTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1104, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbDate)
                .addContainerGap(244, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(lbTitle1)
                    .addContainerGap(223, Short.MAX_VALUE)))
        );

        btnThemPNK.setBackground(new java.awt.Color(17, 153, 142));
        btnThemPNK.setForeground(new java.awt.Color(108, 91, 123));
        btnThemPNK.setText("Thêm PNK");
        btnThemPNK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemPNK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPNKActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(237, 33, 58));
        btnHuy.setForeground(new java.awt.Color(108, 91, 123));
        btnHuy.setText("Hủy");
        btnHuy.setActionCommand("Hủy");
        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemPNK, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(417, 417, 417))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemPNK, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemPNKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPNKActionPerformed

        FormWareHouseStaff.showForm((table_Delivery));
    }//GEN-LAST:event_btnThemPNKActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        FormWareHouseStaff.showForm((table_Delivery));
    }//GEN-LAST:event_btnHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Restaurant.View.Swing.Login.ButtonOutLine btnHuy;
    private Restaurant.View.Swing.Login.ButtonOutLine btnThemPNK;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbTitle1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private Restaurant.View.Component.Dashboard.TextFieldSearchOption txt;
    // End of variables declaration//GEN-END:variables
}
