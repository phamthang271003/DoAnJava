/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Event;

import Restaurant.View.Dialog.MS_CancelReserve;
import Restaurant.View.Dialog.MS_ConfirmReserve;
import Restaurant.View.Form.Staff.TableS;
import Restaurant.View.MainFrame.Main_Staff;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author quangthang
 */
public class Menu_Staff implements ActionListener {

    private TableS tableS;
    private MS_ConfirmReserve book;
    private MS_CancelReserve cancel;

    public Menu_Staff(TableS tableS) {
        this.tableS = tableS;
        this.book = new MS_ConfirmReserve(Main_Staff.getFrames()[0], true);
        this.cancel = new MS_CancelReserve(Main_Staff.getFrames()[0], true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tableS.getBtnDatBan()) {   
             handleReservation();
        
        } else if (e.getSource() == tableS.getBtnGoiMon()) {
    
        }

    }
  private void handleReservation() {
        String status = tableS.getTable().getStatus();
        if (status.equals("Con trong")) {
            book.ConfirmReserve(tableS.getTable(),tableS);
        } else if (status.equals("Da dat truoc")) {
            cancel.CancelReserve(tableS.getTable(),tableS);
             //book.ConfirmReserve(tableS.getTable(),tableS);
        }
    }


}
