package StickManCricket;

import javax.swing.*;
import java.sql.*;

public class SignupPanel extends JPanel {

    public SignupPanel(MainFrame frame) {
        setLayout(null);

        JLabel title = new JLabel("Sign Up");
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

        JButton signupBtn = new JButton("Sign Up");
        signupBtn.setBounds(150, 220, 120, 30);
        add(signupBtn);

        signupBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username,password) VALUES(?,?)"
                );
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Signup Successful 😎");
                frame.showScreen("login");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "DB Error!");
            }
        });
    }
}