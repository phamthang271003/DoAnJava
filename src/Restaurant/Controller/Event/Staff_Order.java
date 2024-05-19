/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Event;

import Restaurant.Controller.Service.ServiceSignInUp;
import Restaurant.Controller.Service.ServiceStaff;
import Restaurant.View.Form.Staff.TableFood;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Staff_Order implements ActionListener {
     private TableFood tb;
     private ServiceStaff service;
     private ServiceSignInUp serviceSINUP;
     
     public Staff_Order(TableFood tb,ServiceStaff service)
     {
         this.tb=tb;
         this.service=service;
     }
     
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource()==tb.getBtnThemMon())
        {
            int index=tb.getTableSelectionIndex();
            String checkSL=tb.getSoLuong();
            try {
                int soLuong = Integer.parseInt(tb.getSoLuong());
                // Xử lý số lượng đã được chuyển đổi thành công
            } catch (NumberFormatException ex) {
                // Xử lý trường hợp khi chuỗi không thể chuyển đổi thành số
                JOptionPane.showMessageDialog(tb, "Số lượng món ăn không hợp lệ.");
                return;
            }
            if(checkSL.equals(""))
            {
                JOptionPane.showMessageDialog(tb, "Số lượng món ăn không hợp lệ.");
                return;
            }
            int SL=Integer.parseInt(tb.getSoLuong()) ;
            
            if(SL <= 0 )
            {
                JOptionPane.showMessageDialog(tb, "Vui lòng nhập số lượng món ăn");
                return;
            }
            if(index!=-1)
            {
               String[] foods=tb.getTableSelection(index);
               String ID_Food=foods[0];
               String FoodName=foods[1];
               foods[2] = foods[2].substring(0, foods[2].length() - 1);
                String[] split=foods[2].split(",");
               int Price=Integer.parseInt(split[0]+split[1]) ;
               String Status=foods[4];
               if(Status.equals("Het hang"))
               {
                   JOptionPane.showMessageDialog(tb, "Món này đã hết nguyên liệu");
                   return;
               }
               String ID_Category=foods[3];
               String[] checkFood=tb.getTbOrderIndexInfor(ID_Food);
               if(checkFood!=null)
               {
                   int FoodPrice=Price*SL;
                   int indexTBOrder=Integer.parseInt(checkFood[0]);
                   int SoLuong=Integer.parseInt(checkFood[4]);
                   int TongTien=Integer.parseInt(checkFood[5]);
                   
                   FoodPrice=FoodPrice+TongTien;
                   SL=SL+SoLuong;
                   tb.setTbOrderIndex(indexTBOrder, ID_Food, FoodName, ID_Category, SL, FoodPrice);
                   tb.setTxtSoLuong();
               } 
               else 
               {
                   int FoodPrice=Price*SL;
                   tb.setTableOrder(ID_Food, FoodName, ID_Category, SL, FoodPrice);
                   tb.setTxtSoLuong();
               }
               
               
            }
            else
            {
                JOptionPane.showMessageDialog(tb, "Vui lòng chọn một món ăn từ bảng món ăn");
            }
        }  
        else if(e.getSource()==tb.getBtnXoaMon())
        {
            int index=tb.getTableOrderIndex();
            if(index!=-1)
            {
                tb.removeTbOrderRow(index);
            }
            else
            {
                JOptionPane.showMessageDialog(tb, "Vui lòng chọn một món ăn từ bảng đặt món");
            }
        }
        else if(e.getSource()==tb.getBtnThanhToan())
        {
            int theLastBillId=service.theLastIdInBill();
            int ID_Bill=theLastBillId + 1;
            int totalPrice=tb.totalPriceInTbOrder();
            if(totalPrice > 0)
            {
                serviceSINUP=new ServiceSignInUp();
                String ID_Client=serviceSINUP.ID_Client;
                String ID_Table=tb.getIDTxtMaBan();
                String Status="Da thanh toan";
                ArrayList<String[]> list=tb.getAllTbOrder();
                service.insertBill(ID_Bill, ID_Client, ID_Table, totalPrice, Status);
                service.insertBillInfo(ID_Bill, list);
                JOptionPane.showMessageDialog(tb, "Tổng tiền phải trả là: "+totalPrice+"đ");
                tb.clearTbOrder();
            }
            else
            {
                JOptionPane.showMessageDialog(tb, "Chưa đặt món");
            }
        }
//        else if(e.getSource()==tb.getBtnThoat())
//        {
//            DashboardFormStaff form=new DashboardFormStaff();
//             FormStaff.showForm(form);
//           
//        }
    }
}
    
