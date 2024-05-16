package Restaurant.View.Dialog;

import Restaurant.Controller.Service.ServiceStaff;
import Restaurant.Model.ModelTable;
import Restaurant.View.Form.Staff.TableS;
import Restaurant.View.Swing.Login.ButtonOutLine;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MS_ConfirmReserve extends javax.swing.JDialog {

    private final Animator animator;
    private TableS tableS;
    private boolean show = true;
    private Frame frame;
    private ServiceStaff service;
   

    public MS_ConfirmReserve(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        service = new ServiceStaff();
        this.frame = parent;
        setOpacity(0f);
        getContentPane().setBackground(Color.WHITE);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    setOpacity(fraction);
                } else {
                    setOpacity(1f - fraction);
                }
            }
            @Override
            public void end() {
                if (show == false) {
                    setVisible(false);
                }
            }
        };
        animator = new Animator(200, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);    
    }

    public void ConfirmReserve(ModelTable table ,TableS tableS) {
         this.tableS = tableS;
        setLocationRelativeTo(frame);
        lbMessage.setText("Mã Bàn: " + table.getID_Table() + " - Tên Bàn: " + table.getTableName());
        animator.start();
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Thực hiện cập nhật trạng thái vào cơ sỡ dữ liệu từ truy vấn
                   service.setTableReserve(table.getID_Table());
                   //Thực hiện cập nhật giao diện bàn khi đặt bàn
                   tableS.updateTableStatus("Da dat truoc", tableS.COLOR_RESERVED, "Hủy đặt trước", tableS.COLOR_CANCELLED);
                      closeMenu();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(MS_ConfirmReserve.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMenu();     
            }
        });
        //Hiển thị lại form xác nhận các lần tiếp theo sau khi nhấn lần đầu
         show = true;
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound2 = new Restaurant.View.Swing.Dashboard.PanelRound();
        btnXacNhan = new Restaurant.View.Swing.Login.ButtonOutLine();
        btnHuy = new Restaurant.View.Swing.Login.ButtonOutLine();
        lbTitle = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        lbMessage = new javax.swing.JLabel();
        txtTenKhach = new Restaurant.View.Swing.Dashboard.MyTextField();
        txtSDT = new Restaurant.View.Swing.Dashboard.MyTextField();
        lbMessage1 = new javax.swing.JLabel();
        lbMessage2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(215, 215, 215));
        setUndecorated(true);

        btnXacNhan.setBackground(new java.awt.Color(17, 153, 142));
        btnXacNhan.setForeground(new java.awt.Color(108, 91, 123));
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
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

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(108, 91, 123));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("XÁC NHẬN ĐẶT TRƯỚC");

        lbMessage.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMessage.setForeground(new java.awt.Color(108, 91, 123));
        lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage.setText("Message");

        txtTenKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachActionPerformed(evt);
            }
        });

        lbMessage1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMessage1.setForeground(new java.awt.Color(108, 91, 123));
        lbMessage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage1.setText("Tên khách hàng");

        lbMessage2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMessage2.setForeground(new java.awt.Color(108, 91, 123));
        lbMessage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage2.setText("Số điện thoại");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMessage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMessage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenKhach, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(86, 86, 86))
            .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound2Layout.createSequentialGroup()
                    .addContainerGap(228, Short.MAX_VALUE)
                    .addComponent(lbIcon)
                    .addGap(192, 192, 192)))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbMessage)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbMessage1))
                .addGap(34, 34, 34)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbMessage2)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound2Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(243, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed


    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
       // closeMenu();
    }//GEN-LAST:event_btnHuyActionPerformed

    public void closeMenu() {
        if (animator.isRunning()) {
            animator.stop();
        }
        show = false;
        animator.start();
    }

    public ButtonOutLine getCmdCancel() {
        return btnHuy;
    }

    public ButtonOutLine getCmdOK() {
        return btnXacNhan;
    }

    public void setCmdCancel(ButtonOutLine cmdCancel) {
        this.btnHuy = cmdCancel;
    }

    public void setCmdOK(ButtonOutLine cmdOK) {
        this.btnXacNhan = cmdOK;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#516395"), 0, getHeight(), Color.decode("#614385"));
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponents(g);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Restaurant.View.Swing.Login.ButtonOutLine btnHuy;
    private Restaurant.View.Swing.Login.ButtonOutLine btnXacNhan;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbMessage;
    private javax.swing.JLabel lbMessage1;
    private javax.swing.JLabel lbMessage2;
    private javax.swing.JLabel lbTitle;
    private Restaurant.View.Swing.Dashboard.PanelRound panelRound2;
    private Restaurant.View.Swing.Dashboard.MyTextField txtSDT;
    private Restaurant.View.Swing.Dashboard.MyTextField txtTenKhach;
    // End of variables declaration//GEN-END:variables
}
