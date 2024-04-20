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
             //book.ConfirmReserve(tableS.getTable());
             handleReservation();
        
        } else if (e.getSource() == tableS.getBtnGoiMon()) {
            // Xử lý khi nút "Gọi món" được nhấn
        }

    }
  private void handleReservation() {
        String status = tableS.getTable().getStatus();
        if (status.equals("Con trong")) {
            book.ConfirmReserve(tableS.getTable());
            updateTableStatus("Da dat truoc", tableS.COLOR_RESERVED, "Hủy đặt trước", tableS.COLOR_CANCELLED);
        } else if (status.equals("Da dat truoc")) {
            cancel.CancelReserve(tableS.getTable());
            updateTableStatus("Con trong", tableS.COLOR_DEFAULT, "Đặt trước", new Color(108, 91, 123));
        }
    }

    private void updateTableStatus(String statusText, Color statusColor, String buttonText, Color buttonColor) {
        tableS.getLbValue().setText(statusText);
        tableS.getImg().setBackground(statusColor);
        tableS.getBtnDatBan().setText(buttonText);
        tableS.getBtnDatBan().setBackground(buttonColor);
        tableS.getTable().setStatus(statusText);
    }
}
