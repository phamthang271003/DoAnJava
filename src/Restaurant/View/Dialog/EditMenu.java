/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Restaurant.View.Dialog;

import Restaurant.View.Form.Manager.Table_menuInfo;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import Restaurant.Controller.Service.ServiceMenu;


/**
 *
 * @author HELO
 */


public class EditMenu extends javax.swing.JFrame {
    private int ID_Food;
    
    /**
     * Creates new form EditMenu
     */
//    public EditMenu() {
//        initComponents();
//    }
private Table_menuInfo parentForm; 
    private ServiceMenu service;
    
   

    /**
     * Creates new form frm_EditP
     */
    public EditMenu() {
        initComponents();
        service = new ServiceMenu(); // Khởi tạo đối tượng service

    }
    
    public EditMenu(Table_menuInfo parentForm) {
        initComponents();
        this.parentForm = parentForm; // Lưu đối tượng của Table_PersonnelInfo truyền vào từ form cha
        service = new ServiceMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHuy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenMon = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboLoai = new javax.swing.JComboBox<>();
        cboTT = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel1.setText("Thông tin món ăn");

        jLabel3.setText("Tên món ăn");

        jLabel6.setText("Trạng thái");

        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Com", "Thịt bê", "Súp ", "Lẩu", "Nước uống", " " }));
        cboLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiActionPerformed(evt);
            }
        });

        cboTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Con hang", "Het hang" }));

        jLabel4.setText("Giá đồ ăn");

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        jLabel5.setText("Loai đồ ăn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTenMon)
                                .addComponent(txtGia)
                                .addComponent(cboTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(btnLuu)
                            .addGap(65, 65, 65)
                            .addComponent(btnHuy)))
                    .addContainerGap(89, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(107, 107, 107)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(23, 23, 23)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGap(42, 42, 42)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLuu)
                        .addComponent(btnHuy))
                    .addContainerGap(21, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        //  int ID_Food = Integer.parseInt(txtIDMon.getText());
       
     String NameFood = txtTenMon.getText();
    String Gia = txtGia.getText();
    String Loai =(String) cboLoai.getSelectedItem();
    String status = (String) cboTT.getSelectedItem();
    
    try {
              double price = convertToDouble(Gia);

        
        
        // Gọi phương thức từ ServicePersional để cập nhật dữ liệu
        service.updateEmployee(ID_Food, NameFood, price, Loai, status);
        
        // Hiển thị thông báo cập nhật thành công
        JOptionPane.showMessageDialog(this, "Cập nhật thông tin món ăn thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        
        // Refresh lại dữ liệu trên bảng của form cha
        parentForm.refreshData();
        
        // Đóng form chỉnh sửa
        this.dispose();
    } catch (SQLException ex) {
        // Xử lý nếu có lỗi khi cập nhật dữ liệu
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật món ăn !", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnLuuActionPerformed

     public void setData( int ID_Food,String FoodName,String FoodPrice, String Name, String Status) {
        // Thiết lập dữ liệu cho các trường nhập liệu trên form
        this.ID_Food   = ID_Food;
        txtTenMon.setText(FoodName);
    //  double price = convertToDouble(txtGia.setText(Price));
       txtGia.setText(FoodPrice);
      cboLoai.setSelectedItem(Name);
      cboTT.setSelectedItem(Status);
    }
    
     public void refreshParentData() {
       if (parentForm != null) {
           parentForm.refreshData(); // Gọi phương thức refreshData() của form cha (Table_PersonnelInfo)
       }
   }
    public double convertToDouble(String priceString) {
    // Loại bỏ ký tự phân tách thập phân và ký tự đơn vị tiền tệ
    String cleanedPrice = priceString.replace(",", "").replace("đ", "");

    // Chuyển đổi chuỗi thành kiểu double
    double price = Double.parseDouble(cleanedPrice);
    
    return price;
}
     
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtTenMon;
    // End of variables declaration//GEN-END:variables
}

