import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame2 extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn, resetBtn;

    public LoginFrame2() {
        setTitle("Login - Cafe System");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("Cafe Login");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(120, 20, 200, 30);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 80, 150, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 120, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 120, 150, 25);
        add(passwordField);

        loginBtn = new JButton("Login");
        resetBtn = new JButton("Reset");

        loginBtn.setBounds(80, 180, 100, 30);
        resetBtn.setBounds(200, 180, 100, 30);

        add(loginBtn);
        add(resetBtn);

        loginBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new CafeFrame2();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
            }
        }

        if (e.getSource() == resetBtn) {
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginFrame2();
    }
}