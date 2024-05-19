/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Restaurant.View.Form.WarehouseStaff;

import Restaurant.Controller.Event.Menu_StaffWarehouse_Delivery;
import Restaurant.Controller.Service.ServiceSignInUp;
import Restaurant.Controller.Service.ServiceStaffWarehouse;
import Restaurant.Model.ModelDeliveryBill;
import Restaurant.Model.ModelEmployee;
import Restaurant.Model.ModelUser;
import Restaurant.View.Component.Dashboard.SearchOptinEvent;
import Restaurant.View.Component.Dashboard.SearchOption;
import Restaurant.View.Component.Dashboard.UWPButton;
import Restaurant.View.Component.WarehouseStaff.SimpleForm;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;

/**
 *
 * @author quangthang
 */
public class Table_Delivery extends SimpleForm {

    private ServiceStaffWarehouse service;
    private ArrayList<ModelDeliveryBill> list;
    private ModelEmployee modelEmployee;
    private ModelUser modelUser;
    private ServiceSignInUp ser;
    DecimalFormat df;
    SimpleDateFormat dateFormat;

    public ModelEmployee getModelEmployee() {
        return modelEmployee;
    }

    public void setModelEmployee(ModelEmployee modelEmployee) {
        this.modelEmployee = modelEmployee;
    }

    public ModelUser getModelUser() {
        return modelUser;
    }

    public void setModelUser(ModelUser modelUser) {
        this.modelUser = modelUser;
    }

    /**
     * Creates new form Table
     */
    public Table_Delivery(ModelUser modelUser) {
        this.modelUser = modelUser;
        initComponents();
        service = new ServiceStaffWarehouse();

        try {
            ser = new ServiceSignInUp();
            int id_user = Integer.parseInt(ser.ID_Client);
            modelEmployee = service.getStaff(id_user);
            System.out.println(modelUser.getID_User());
            System.out.println(modelEmployee);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        df = new DecimalFormat("#,###");
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        getCountDeli();
        initTable();

        initialize();
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
        jPanel1.putClientProperty(FlatClientProperties.STYLE, ""
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

    public void initTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            list = service.MenuDelivery();
            for (ModelDeliveryBill data : list) {
                model.addRow(new Object[]{data.getID_Del(), data.getId_Emp(), dateFormat.format(data.getInputDay())});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void refreshData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            list = service.MenuDelivery();
            for (ModelDeliveryBill data : list) {
                model.addRow(new Object[]{data.getID_Del(), data.getId_Emp(), dateFormat.format(data.getInputDay())});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public UWPButton getBtnChiTietPXK() {
        return btnChiTietPXK;
    }

    public void setBtnChiTietPXK(UWPButton btnChiTietPXK) {
        this.btnChiTietPXK = btnChiTietPXK;
    }

    public UWPButton getBtnThemPXK() {
        return btnThemPXK;
    }

    public void setBtnThemPXK(UWPButton btnThemPXK) {
        this.btnThemPXK = btnThemPXK;
    }

    public void getCountDeli() {
        try {
            txtSLPhieuXuat.setText(service.getSLPXK() + "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void initialize() {

        Menu_StaffWarehouse_Delivery menu_StaffWarehouse_Delivery = new Menu_StaffWarehouse_Delivery(this);
        btnThemPXK.addActionListener(menu_StaffWarehouse_Delivery);
        btnChiTietPXK.addActionListener(menu_StaffWarehouse_Delivery);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnThemPXK = new Restaurant.View.Component.Dashboard.UWPButton();
        btnChiTietPXK = new Restaurant.View.Component.Dashboard.UWPButton();
        txt = new Restaurant.View.Component.Dashboard.TextFieldSearchOption();
        jLabel1 = new javax.swing.JLabel();
        txtSLPhieuXuat = new javax.swing.JTextField();

        jPanel1.setLayout(new java.awt.BorderLayout());

        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma XK", "Ma NV", "Ngày XK"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(40);
        scroll.setViewportView(table);

        btnThemPXK.setText("Thêm PXK");
        btnThemPXK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPXKActionPerformed(evt);
            }
        });

        btnChiTietPXK.setText("Chi tiết PXK");
        btnChiTietPXK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietPXKActionPerformed(evt);
            }
        });

        txt.setText("textFieldSearchOption1");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Số phiếu xuất kho hôm nay");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThemPXK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChiTietPXK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1212, 1212, 1212)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(txtSLPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSLPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemPXK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChiTietPXK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemPXKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPXKActionPerformed

    }//GEN-LAST:event_btnThemPXKActionPerformed

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    private void btnChiTietPXKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietPXKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChiTietPXKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Restaurant.View.Component.Dashboard.UWPButton btnChiTietPXK;
    private Restaurant.View.Component.Dashboard.UWPButton btnThemPXK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private Restaurant.View.Component.Dashboard.TextFieldSearchOption txt;
    private javax.swing.JTextField txtSLPhieuXuat;
    // End of variables declaration//GEN-END:variables
}
