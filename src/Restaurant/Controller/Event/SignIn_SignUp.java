/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Event;

import Restaurant.Controller.Service.ServiceSignInUp;
import Restaurant.View.Component.Login.PanelLoginAndRegister;
import Restaurant.View.MainFrame.Main_Manager;
import Restaurant.View.MainFrame.Main_Staff;
import Restaurant.View.MainFrame.Main_Warehouse_Staff;
import Restaurant.View.Swing.Login.MyPasswordField;
import Restaurant.View.Swing.Login.MyTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class SignIn_SignUp implements ActionListener{

    private PanelLoginAndRegister panel;
    private ServiceSignInUp service;
    
    // Constructor mặc định
    
    public SignIn_SignUp(PanelLoginAndRegister panel,ServiceSignInUp service)
    {
        this.panel=panel;
        this.service=service;
    }
    
   @Override
    public void actionPerformed(ActionEvent e) {
      String button = e.getActionCommand();
     
      
      
      if(button.equals("SIGN IN")){
        MyTextField txtEmail = panel.getTxtEmail();
        MyPasswordField txtPass = panel.getTxtPassword();
        
        String email = txtEmail.getText();
        String password = new String(txtPass.getPassword());
        
        // Split dùng để lấy tên Email không kèm theo đuôi
        String[] splitEmail=email.split("@");
          System.out.println("Bạn đã nhấn nút" + email);
        //JOptionPane.showMessageDialog(panel, "Đăng nhập thành công");
        
        // Thực hiện kiểm tra đăng nhập bằng service
        boolean loginSuccess = service.checkLogin(email, password, splitEmail[0]);
        int checkRole=service.checkRole(email);
        // Hiển thị thông báo tùy thuộc vào kết quả đăng nhập
        if (loginSuccess) {
            if(checkRole==1)
            {
                
                JOptionPane.showMessageDialog(panel, "Đăng nhập thành công ! Chào mừng bạn nhân viên");
                Main_Staff staff=new Main_Staff();
                staff.setVisible(true);
            }
            else if(checkRole==2)
            {
                JOptionPane.showMessageDialog(panel, "Đăng nhập thành công ! Chào mừng bạn nhân viên quản lý kho");
                Main_Warehouse_Staff WHStaff=new Main_Warehouse_Staff();
                WHStaff.setVisible(true);
                
            }
            else if(checkRole==3)
            {
                JOptionPane.showMessageDialog(panel, "Đăng nhập thành công ! Chào mừng bạn quản lý");
                Main_Manager manager=new Main_Manager();
                manager.setVisible(true);
            }
            
            
        } else {
            JOptionPane.showMessageDialog(panel, "Đăng nhập thất bại. Vui lòng kiểm tra lại Email và Password.");
        }
      }
      else if(button.equals("SIGN UP"))
      {
        MyTextField txtEmail = panel.getTxtSUEmail();
        MyPasswordField txtPass = panel.getTxtSUPassword();
        MyPasswordField txtRePass = panel.getTxtSURePassword();
        String email = txtEmail.getText();
        String password = new String(txtPass.getPassword());
        String repassword=new String(txtRePass.getPassword());
        boolean checkEmail=service.checkEmail(email);
        String[] splitEmail=email.split("@");
        if(!email.equals("") || !password.equals("") || !repassword.equals(""))
        {
            if(email.endsWith("@gmail.com") && splitEmail.length == 2 && !email.matches(".*\\.@gmail.com") && email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
            {
                if (!checkEmail)
                {

                    if(password.equals(repassword))
                    {
                        int check=service.insertUser(email, password);
                        if(check==1)
                        {
                            JOptionPane.showMessageDialog(panel, "Đăng ký thành công");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(panel, "Đăng ký thất bại");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(panel, "Nhập lại sai mật khẩu ! Vui lòng nhập lại !");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(panel, "Email đã tồn tại vui lòng nhập Email mới");
                }
            
            }
            else
            {
                JOptionPane.showMessageDialog(panel, "Email sai định dạng vui lòng nhập đúng đuôi @gmail.com");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(panel, "Vui lòng không để trống bất cứ thông tin nào");
        }
        
      }
    }
    
}
