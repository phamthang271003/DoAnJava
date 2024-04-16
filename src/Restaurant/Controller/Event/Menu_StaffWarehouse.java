/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Event;

import Restaurant.View.Form.WarehouseStaff.Table_lngredientInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author quangthang
 */
public class Menu_StaffWarehouse implements ActionListener{

    private Table_lngredientInfo Table_lngredientInfo;

    public Menu_StaffWarehouse(Table_lngredientInfo Table_lngredientInfo) {
        this.Table_lngredientInfo = Table_lngredientInfo;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
      String button = e.getActionCommand();
      if(button.equals("Thêm")){
          System.out.println("Bạn đã nhấn nút" + button);
      }
    }
    
}
