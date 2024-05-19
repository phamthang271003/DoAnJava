/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Restaurant.View.Form.WarehouseStaff;

import Restaurant.Controller.Service.ServiceStaffWarehouse;
import Restaurant.Model.ModelEmployee;
import Restaurant.Model.ModelReceipt;
import Restaurant.Model.ModelUser;
import Restaurant.Model.Modelngredient;
import Restaurant.View.Component.WarehouseStaff.SimpleForm;
import Restaurant.View.Component.Dashboard.SearchOptinEvent;
import Restaurant.View.Component.Dashboard.SearchOption;
import Restaurant.View.Component.WarehouseStaff.FormWareHouseStaff;
import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author quangthang
 */
public class Table_InsertReceipt extends SimpleForm {

    private ServiceStaffWarehouse serviceWarehouse;
    private ArrayList<Modelngredient> list;
    private ModelEmployee modelEmployee;
    private ModelUser modelUser;
    private Table_Receipt receiptInfo;
    private com.raven.datechooser.DateChooser dateChooser;
    private SimpleDateFormat simpleDateFormat;
    DecimalFormat df;

    /**
     * Creates new form Table
     */
    public Table_InsertReceipt(ModelUser modelUser, ModelEmployee modelEmployee, Table_Receipt receiptInfo) {

        this.modelUser = modelUser;
        this.modelEmployee = modelEmployee;
        this.receiptInfo = receiptInfo;
        list = new ArrayList<>();
        initComponents();
        serviceWarehouse = new ServiceStaffWarehouse();
        simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        df = new DecimalFormat("#,###");
        initTable();
        initPNK();
        setCurrentDate();

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column > -1) {
                    TableModel model = (TableModel) e.getSource();
                    int sl;
                    sl = Integer.parseInt((String) model.getValueAt(row, column));
                    int id = (int) model.getValueAt(row, 0); // Assuming ID is in the first column
                    for (Modelngredient x : list) {
                        if (x.getiD_Ingr() == id) {
                            x.setQuantityInStock(sl);
                            break;
                        }
                    }
                }
            }
        });

        //DateChooser
        dateChooser = new com.raven.datechooser.DateChooser();
        dateChooser.setTextRefernce(txtDate1);
        dateChooser.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {
                System.out.println(date.getDay() + "-" + date.getMonth() + "-" + date.getYear());
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    dateChooser.hidePopup();
                }
            }
        });
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

    public void initPNK() {
        try {
            txtMaNK.setText(serviceWarehouse.getNextID_NK() + "");
            txtMaNV.setText(modelEmployee.getID_Emp() + "");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setCurrentDate() {
        lbDate.setText("Ngày hiện tại: " + simpleDateFormat.format(new Date()));
    }

        public void initTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            list = serviceWarehouse.MenuIngr();
            for (Modelngredient data : list) {
                data.setQuantityInStock(0);
                model.addRow(new Object[]{data.getiD_Ingr(), data.getNameIngre(), data.getUnit(), data.getQuantityInStock()});
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
        lbMessage3 = new javax.swing.JLabel();
        txtMaNK = new Restaurant.View.Swing.Dashboard.MyTextField();
        lbMessage4 = new javax.swing.JLabel();
        txtMaNV = new Restaurant.View.Swing.Dashboard.MyTextField();
        lbMessage1 = new javax.swing.JLabel();
        txtDate1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        lbTitle1 = new javax.swing.JLabel();
        btnThemPNK = new Restaurant.View.Swing.Login.ButtonOutLine();
        btnHuy = new Restaurant.View.Swing.Login.ButtonOutLine();

        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma NL", "Tên nguyên liệu", "Đơn vị tính", "Số lượng nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
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
        lbDate.setForeground(new java.awt.Color(255, 255, 255));
        lbDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbMessage3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMessage3.setForeground(new java.awt.Color(255, 255, 255));
        lbMessage3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage3.setText("Mã nhập kho");

        txtMaNK.setEditable(false);
        txtMaNK.setEnabled(false);
        txtMaNK.setOpaque(true);

        lbMessage4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMessage4.setForeground(new java.awt.Color(255, 255, 255));
        lbMessage4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage4.setText("Mã nhân viên");

        lbMessage1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMessage1.setForeground(new java.awt.Color(255, 255, 255));
        lbMessage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage1.setText("Ngày nhập kho");

        jButton5.setText("...");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        lbTitle1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTitle1.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle1.setText("Thêm phiếu nhập kho");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMessage3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMessage4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMessage1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaNK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbMessage3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaNK, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMessage4, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMessage1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(56, 56, 56))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(lbTitle1)
                    .addContainerGap(223, Short.MAX_VALUE)))
        );

        btnThemPNK.setBackground(new java.awt.Color(17, 153, 142));
        btnThemPNK.setForeground(new java.awt.Color(255, 255, 255));
        btnThemPNK.setText("Thêm PNK");
        btnThemPNK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemPNK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPNKActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(237, 33, 58));
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
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
        int idNK = Integer.parseInt(txtMaNK.getText());
        int idNV = Integer.parseInt(txtMaNV.getText());
        SelectedDate selectedDate = dateChooser.getSelectedDate();
        String ngayNK = String.format("%02d-%02d-%04d", selectedDate.getDay(), selectedDate.getMonth(), selectedDate.getYear());

        // Convert ngayNK String to Date object
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date ngayNKDate = null;
        try {
            ngayNKDate = simpleDateFormat.parse(ngayNK);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        ModelReceipt pnk = new ModelReceipt(idNK, idNV, ngayNKDate);
        try {
            serviceWarehouse.InsertPNK_CTNK(pnk, list);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        FormWareHouseStaff.showForm((receiptInfo));
        receiptInfo.refreshData();
    }//GEN-LAST:event_btnThemPNKActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        FormWareHouseStaff.showForm((receiptInfo));
    }//GEN-LAST:event_btnHuyActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dateChooser.showPopup();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Restaurant.View.Swing.Login.ButtonOutLine btnHuy;
    private Restaurant.View.Swing.Login.ButtonOutLine btnThemPNK;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbMessage1;
    private javax.swing.JLabel lbMessage3;
    private javax.swing.JLabel lbMessage4;
    private javax.swing.JLabel lbTitle1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private Restaurant.View.Component.Dashboard.TextFieldSearchOption txt;
    private javax.swing.JTextField txtDate1;
    private Restaurant.View.Swing.Dashboard.MyTextField txtMaNK;
    private Restaurant.View.Swing.Dashboard.MyTextField txtMaNV;
    // End of variables declaration//GEN-END:variables
}
