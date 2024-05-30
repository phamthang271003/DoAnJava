/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Restaurant.View.Form.Staff;

import Restaurant.Controller.Event.Menu_Staff;
import Restaurant.Controller.Event.Staff_Order;
import Restaurant.Controller.Service.ServiceStaff;
import Restaurant.Controller.Service.ServiceStaffWarehouse;
import Restaurant.Model.ModelFood;
import Restaurant.Model.Modelngredient;
import Restaurant.View.Component.Dashboard.SearchOptinEvent;
import Restaurant.View.Component.Dashboard.SearchOption;
import Restaurant.View.Component.Dashboard.UWPButton;
import Restaurant.View.Component.Staff.SimpleFormStaff;
import Restaurant.View.Swing.Dashboard.Button;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author quangthang
 */
public class TableFood extends SimpleFormStaff {

    private Staff_Order staff;
    private  TableS tableS;
    private ServiceStaff service;
    private ArrayList<ModelFood> list;
    private Menu_Staff menuStaff; 
    DecimalFormat df;

    /**
     * Creates new form Table
     */
    public TableFood(Menu_Staff menuStaff) throws SQLException {
        initComponents();
        service = new ServiceStaff();
        df = new DecimalFormat("#,###");
        initTable();
        ArrayList<ModelFood> list=service.listFoodFromCate("1");
        this.setTable(list);
        menuStaff = new Menu_Staff(tableS, this);
        staff=new Staff_Order(this, service);
        cboLoaiTA.addItemListener(menuStaff);
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
        //testData();
        btnThem.addActionListener(staff);
        btnThanhToan.addActionListener(staff);
        btnXoa.addActionListener(staff);
    }
    
    public int getTableOrderIndex()
    {
        int selectedRow = tbOrder.getSelectedRow();
        return selectedRow;
    }
    
    public ArrayList<String[]> getAllTbOrder() {
        ArrayList<String[]> orders = new ArrayList<>();
        
        DefaultTableModel model = (DefaultTableModel) tbOrder.getModel(); // Lấy đối tượng TableModel của tbOrder
        
        int rowCount = model.getRowCount(); // Số lượng dòng trong tbOrder
        int columnCount = model.getColumnCount(); // Số lượng cột trong tbOrder
        
        for (int i = 0; i < rowCount; i++) {
            String[] order = new String[columnCount]; // Mảng lưu trữ dữ liệu của mỗi dòng
            for (int j = 0; j < columnCount; j++) {
                order[j] = String.valueOf(model.getValueAt(i, j)); // Lấy dữ liệu từ cell tại dòng i và cột j
            }
            orders.add(order); // Thêm mảng dữ liệu của dòng vào danh sách orders
        }
        
        return orders; // Trả về danh sách các dòng dữ liệu từ tbOrder
    }
    
    public void clearTbOrder() {
        DefaultTableModel model = (DefaultTableModel) tbOrder.getModel();
        model.setRowCount(0);
    }
    
    public int totalPriceInTbOrder() {
        int total = 0;

        if (tbOrder.getRowCount() > 0) {
            for (int i = 0; i < tbOrder.getRowCount(); i++) {
                total += Integer.parseInt(tbOrder.getValueAt(i, 4).toString());
            }
        }

        return total;
    }
   
    public void setTableOrder(String ID_Food,String FoodName,String ID_Category,int SL,int FoodPrice)
    {
        DefaultTableModel model=(DefaultTableModel) tbOrder.getModel();
        
        model.addRow(new Object[]{ID_Food,FoodName,ID_Category,SL,FoodPrice});
    }
    
    public void setTbOrderIndex(int index, String ID_Food, String FoodName, String ID_Category, int SL, int FoodPrice) {
        DefaultTableModel model = (DefaultTableModel) tbOrder.getModel();

        // Kiểm tra xem index có hợp lệ không
        if (index >= 0 && index < model.getRowCount()) {
            // Cập nhật các phần tử trong dòng tại index được chỉ định
            model.setValueAt(ID_Food, index, 0);
            model.setValueAt(FoodName, index, 1);
            model.setValueAt(ID_Category, index, 2);
            model.setValueAt(SL, index, 3);
            model.setValueAt(FoodPrice, index, 4);
        } else {
            // Nếu index không hợp lệ, hiển thị thông báo hoặc xử lý tùy ý
            System.out.println("Index không hợp lệ");
        }
    }
    
    public void removeTbOrderRow(int index) {
        DefaultTableModel model = (DefaultTableModel) tbOrder.getModel();
        model.removeRow(index);
    }
    
    public String[] getTbOrderIndexInfor(String ID) {
        for (int row = 0; row < tbOrder.getRowCount(); row++) {
            Object value = tbOrder.getValueAt(row, 0); // Lấy giá trị ở cột đầu tiên của dòng hiện tại
            if (value != null && value.toString().equals(ID)) { // So sánh giá trị với ID
                // Nếu giá trị trùng với ID, lấy thông tin của dòng đó
                String[] rowData = new String[6]; // Mảng chứa thông tin của dòng được chọn, bao gồm index và 5 cột dữ liệu
                rowData[0] = String.valueOf(row); // Lưu index của dòng
                // Lấy thông tin của 5 cột dữ liệu
                for (int i = 1; i <= 5; i++) {
                    int j=i-1;
                    Object columnValue = tbOrder.getValueAt(row, j);
                    rowData[i] = (columnValue != null) ? columnValue.toString() : ""; // Chuyển đổi giá trị sang String và đảm bảo không có giá trị null
                }
                return rowData; // Trả về thông tin của dòng được chọn
            }
        }
        // Nếu không tìm thấy dòng có ID trùng khớp, trả về null
        return null;
    }
    
    public String getIDTxtMaBan()
    {
        return txtMaBan.getText();
    }
    
    
    
    public UWPButton getBtnXoaMon()
    {
        return btnXoa;
    }
    
    public UWPButton getBtnThanhToan()
    {
        return btnThanhToan;
    }
    
    public void setTxtSoLuong()
    {
        txtSoLuong.setText("1");
    }
    
    public String getSoLuong()
    {
        return txtSoLuong.getText();
    }

    public JComboBox<String> getCboLoaiTA() {
        return cboLoaiTA;
    }

    // Phương thức setter cho JComboBox
    public void setCboLoaiTA(JComboBox<String> cboLoaiTA) {
        this.cboLoaiTA = cboLoaiTA;
    }

   public String getCBOSelection(int index) {
        Object selectedItem = cboLoaiTA.getItemAt(index);
        if (selectedItem != null) {
            return selectedItem.toString();
        } else {
            return ""; // hoặc trả về null tùy thuộc vào ý định của bạn
        }
    }

    public void setTenBanText(String text) {
        txtTenBan.setText(text);
    }

    public void setMaBanText(String text) {
        txtMaBan.setText(text);
    }
    
    public void setTangText(String text)
    {
        txtTang.setText(text);
    }
    
     public int getTableSelectionIndex()
    {
        return table.getSelectedRow();
    }
     
    public UWPButton getBtnThemMon()
    {
        return btnThem;
    }
     
     public void setTable(ArrayList<ModelFood> list)
    {
        DefaultTableModel model=(DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(ModelFood data:list)
        {
            model.addRow(new Object[]{data.getID_Food(), data.getFoodName(), df.format(data.getFoodPrice()) + "đ", data.getID_Category(), data.getStatus()});
        }
           
        
       
    }
    
    public String[] getTableSelection(int index) {
         int selectedRow = index;
        if (selectedRow != -1) { // Kiểm tra xem có dòng nào được chọn không
            String[] rowData = new String[5]; // Mảng chứa thông tin của dòng được chọn
            for (int i = 0; i < 5; i++) {
                Object value = table.getValueAt(selectedRow, i);
                rowData[i] = (value != null) ? value.toString() : ""; // Chuyển đổi giá trị sang String và đảm bảo không có giá trị null
            }
            return rowData;
        } else {
            // Nếu không có dòng nào được chọn, trả về null hoặc một giá trị tùy ý để xử lý
            return null;
        }
    }
    
    

        public void initTable() throws SQLException {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(service.getFoodCategory());
            cboLoaiTA.setModel(model);
//                 DefaultTableModel model = (DefaultTableModel) table.getModel();
//            try {
//                list = service.MenuIngr();
//                for (Modelngredient data : list) {
//                    model.addRow(new Object[]{data.getiD_Ingr(), data.getNameIngre(), df.format(data.getPrice()) + "đ", data.getUnit()});
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
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
        btnThem = new Restaurant.View.Component.Dashboard.UWPButton();
        btnXoa = new Restaurant.View.Component.Dashboard.UWPButton();
        btnThanhToan = new Restaurant.View.Component.Dashboard.UWPButton();
        txt = new Restaurant.View.Component.Dashboard.TextFieldSearchOption();
        cboLoaiTA = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaBan = new javax.swing.JTextField();
        txtTenBan = new javax.swing.JTextField();
        txtTang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbOrder = new javax.swing.JTable();

        jPanel1.setLayout(new java.awt.BorderLayout());

        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Food", "Tên món ăn", "Đơn giá", "ID_Category", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(40);
        scroll.setViewportView(table);

        btnThem.setText("Thêm món");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txt.setText("textFieldSearchOption1");

        cboLoaiTA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Loại món ăn:");

        jLabel2.setText("Mã bàn:");

        jLabel3.setText("Tên bàn:");

        txtMaBan.setEditable(false);

        txtTenBan.setEditable(false);

        txtTang.setEditable(false);

        jLabel4.setText("Tầng:");

        jLabel5.setText("Số lượng:");

        txtSoLuong.setText("1");

        tbOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Food", "FoodName", "ID_Category", "SL", "FoodPrice"
            }
        ));
        tbOrder.setRowHeight(40);
        jScrollPane1.setViewportView(tbOrder);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1212, 1212, 1212)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(638, 638, 638)))
                        .addGap(99, 99, 99))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(117, 117, 117)
                                                .addComponent(txtTenBan))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtMaBan)))
                                        .addGap(47, 47, 47)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboLoaiTA, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTang, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(13, 13, 13)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoaiTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Restaurant.View.Component.Dashboard.UWPButton btnThanhToan;
    private Restaurant.View.Component.Dashboard.UWPButton btnThem;
    private Restaurant.View.Component.Dashboard.UWPButton btnXoa;
    private javax.swing.JComboBox<String> cboLoaiTA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JTable tbOrder;
    private Restaurant.View.Component.Dashboard.TextFieldSearchOption txt;
    private javax.swing.JTextField txtMaBan;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTang;
    private javax.swing.JTextField txtTenBan;
    // End of variables declaration//GEN-END:variables
}
