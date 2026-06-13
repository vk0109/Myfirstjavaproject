package StickManCricket;

import javax.swing.*;
import java.sql.*;

public class LoginPanel extends JPanel {

    public LoginPanel(MainFrame frame) {
        setLayout(null);

        JLabel title = new JLabel("Login");
        title.setBounds(200, 30, 100, 30);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(80, 100, 100, 30);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(180, 100, 150, 30);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(80, 150, 100, 30);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(180, 150, 150, 30);
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 220, 120, 30);
        add(loginBtn);

        JButton signupBtn = new JButton("New User? Sign Up");
        signupBtn.setBounds(130, 260, 180, 30);
        add(signupBtn);

        signupBtn.addActionListener(e -> frame.showScreen("signup"));

        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?"
                );
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful 😎");
                    frame.showScreen("startGame"); // 🔥 go to StartGamePanel
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid User ❌");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "DB Error!");
            }
        });
    }
}