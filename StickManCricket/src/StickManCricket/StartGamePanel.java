package StickManCricket;

import javax.swing.*;
import java.awt.*;

public class StartGamePanel extends JPanel {

    public StartGamePanel(MainFrame frame) {
        setLayout(null);

        JLabel title = new JLabel("Ready to Play?");
        title.setBounds(150, 80, 200, 30);
        add(title);

        JButton startBtn = new JButton("Start Game");
        startBtn.setBounds(180, 150, 120, 40);

        // 🔥 black button
        startBtn.setBackground(Color.BLACK);
        startBtn.setForeground(Color.WHITE);
        startBtn.setFocusPainted(false);

        add(startBtn);

        startBtn.addActionListener(e -> {
    frame.showScreen("settings"); // 🔥 yaha jaayega
});
    }
}