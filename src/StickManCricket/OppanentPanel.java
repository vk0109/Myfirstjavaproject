/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hp
 */
/*package StickManCricket;

import javax.swing.*;
import java.awt.*;

public class OppanentPanel extends JPanel {

    public OppanentPanel(MainFrame frame) {
        setLayout(null);

        setBackground(new Color(30, 0, 0)); // 🔥 dark red vibe

        JLabel title = new JLabel("SELECT OPPONENT TEAM");
        title.setBounds(110, 30, 300, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title);

        String[] teams = {
            "India 🇮🇳", "Australia 🇦🇺", "England 🏴",
            "Pakistan 🇵🇰", "South Africa 🇿🇦"
        };

        JComboBox<String> teamBox = new JComboBox<>(teams);
        teamBox.setBounds(150, 120, 200, 30);
        add(teamBox);

        JButton startBtn = new JButton("START MATCH");
        startBtn.setBounds(150, 200, 180, 40);

        startBtn.setBackground(Color.GREEN);
        startBtn.setForeground(Color.BLACK);

        add(startBtn);

        startBtn.addActionListener(e -> {
            String opponent = (String) teamBox.getSelectedItem();

            // ❌ Same team check
            if (opponent.equals(frame.getUserTeam())) {
                JOptionPane.showMessageDialog(this,
                    "Opponent team same nahi ho sakti ❌");
            } else {
                frame.setOpponentTeam(opponent);
                frame.stopMusic();
                frame.showScreen("game"); // 🔥 start game
            }
        });
    }
}*/
package StickManCricket;

import javax.swing.*;
import java.awt.*;

public class OppanentPanel extends JPanel {

    public OppanentPanel(MainFrame frame) {
        setLayout(null);
        setBackground(new Color(30, 0, 0));

        JLabel title = new JLabel("SELECT OPPONENT TEAM");
        title.setBounds(110, 30, 300, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title);

        String[] teams = {
            "India 🇮🇳", "Australia 🇦🇺", "England 🏴",
            "Pakistan 🇵🇰", "South Africa 🇿🇦"
        };

        JComboBox<String> teamBox = new JComboBox<>(teams);
        teamBox.setBounds(150, 120, 200, 30);
        add(teamBox);

        JButton startBtn = new JButton("START MATCH");
        startBtn.setBounds(150, 200, 180, 40);
        startBtn.setBackground(Color.GREEN);
        startBtn.setForeground(Color.BLACK);
        add(startBtn);

        startBtn.addActionListener(e -> {
            String opponent = (String) teamBox.getSelectedItem();
            if (opponent.equals(frame.getUserTeam())) {
                JOptionPane.showMessageDialog(this, "Opponent team same nahi ho sakti ❌");
            } else {
                frame.setOpponentTeam(opponent);
                frame.showScreen("game");
            }
        });
    }
}
