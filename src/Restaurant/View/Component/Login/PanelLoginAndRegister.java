package Restaurant.View.Component.Login;

import Restaurant.Controller.Event.SignIn_SignUp;
import Restaurant.Controller.Service.ServiceSignInUp;
import Restaurant.View.Swing.Login.Button;
import Restaurant.View.Swing.Login.MyPasswordField;
import Restaurant.View.Swing.Login.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    private MyTextField txtEmail;
    private MyPasswordField txtPass;

    private MyTextField txtSUEmail;
    private MyPasswordField txtSUPass;
    private MyPasswordField txtSURePass;

    // Các phương thức getter cho txtEmail và txtPassword
    public MyTextField getTxtEmail() {
        return txtEmail;
    }

    public MyPasswordField getTxtPassword() {
        return txtPass;
    }

    public MyTextField getTxtSUEmail() {
        return txtSUEmail;
    }

    public MyPasswordField getTxtSUPassword() {
        return txtSUPass;
    }

    public MyPasswordField getTxtSURePassword() {
        return txtSURePass;
    }

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        //label.setForeground(new Color(7, 164, 121));
        label.setForeground(Color.black);
        register.add(label);
//        MyTextField txtUser = new MyTextField();
//        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/Login/user.png")));
//        txtUser.setBackground(Color.WHITE);
//        txtUser.setHint("Name");
//        register.add(txtUser, "w 60%");
        txtSUEmail = new MyTextField();
        txtSUEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/Login/mail.png")));
        txtSUEmail.setHint("Email");
        txtSUEmail.setBackground(Color.WHITE);
        register.add(txtSUEmail, "w 60%");
        txtSUPass = new MyPasswordField();
        txtSUPass.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/Login/pass.png")));
        txtSUPass.setHint("Password");
        txtSUPass.setBackground(Color.WHITE);
        register.add(txtSUPass, "w 60%");
        txtSURePass = new MyPasswordField();
        txtSURePass.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/Login/pass.png")));
        txtSURePass.setHint("Re-enter Password");
        txtSURePass.setBackground(Color.WHITE);
        register.add(txtSURePass, "w 60%");
        Button cmd = new Button();
        //cmd.setBackground(new Color(7, 164, 121));
        cmd.setBackground(new Color(0, 0, 0));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        ServiceSignInUp service = new ServiceSignInUp();
        SignIn_SignUp SignIn = new SignIn_SignUp(this, service);
        cmd.addActionListener(SignIn);
        register.add(cmd, "w 40%, h 40");

        register.setBackground( Color.decode("#DCDCDC")); 
    }

    private void initLogin() {
        login.setBackground(Color.decode("#DCDCDC")); 
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));

        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        //label.setForeground(new Color(7, 164, 121));
        label.setForeground(Color.black);
        login.add(label);
        txtEmail = new MyTextField();
        txtEmail.setText("PhamThang03@gmail.com");
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/Login/mail.png")));
        txtEmail.setHint("Email");
        txtEmail.setBackground(Color.WHITE);
        login.add(txtEmail, "w 60%");
        txtPass = new MyPasswordField();
        txtPass.setText("123");
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/Login/pass.png")));
        txtPass.setHint("Password");
        txtPass.setBackground(Color.WHITE);
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd = new Button();
        //cmd.setBackground(new Color(7, 164, 121));
        cmd.setBackground(new Color(0, 0, 0));
        cmd.setForeground(new Color(250, 250, 250));

        cmd.setText("SIGN IN");
        ServiceSignInUp service = new ServiceSignInUp();
        SignIn_SignUp SignIn = new SignIn_SignUp(this, service);
        cmd.addActionListener(SignIn);
        login.add(cmd, "w 40%, h 40");
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
