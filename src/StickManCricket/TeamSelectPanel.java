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

public class TeamSelectPanel extends JPanel {

    public TeamSelectPanel(MainFrame frame) {
        setLayout(null);

        // 🎨 Attractive gradient style color
        setBackground(new Color(10, 10, 30));

        JLabel title = new JLabel("SELECT YOUR TEAM");
        title.setBounds(130, 30, 250, 30);
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

        JButton nextBtn = new JButton("Continue");
        nextBtn.setBounds(170, 200, 150, 40);

        nextBtn.setBackground(Color.ORANGE);
        nextBtn.setForeground(Color.BLACK);

        add(nextBtn);

        nextBtn.addActionListener(e -> {
            String selectedTeam = (String) teamBox.getSelectedItem();

            frame.setUserTeam(selectedTeam); // 🔥 store team
            frame.showScreen("opponent");    // next panel
        });
    }
*/
package StickManCricket;

import javax.swing.*;
import java.awt.*;

public class TeamSelectPanel extends JPanel {

    public TeamSelectPanel(MainFrame frame) {
        setLayout(null);
        setBackground(new Color(10, 10, 30));

        JLabel title = new JLabel("SELECT YOUR TEAM");
        title.setBounds(130, 30, 250, 30);
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

        JButton nextBtn = new JButton("Continue");
        nextBtn.setBounds(170, 200, 150, 40);
        nextBtn.setBackground(Color.ORANGE);
        nextBtn.setForeground(Color.BLACK);
        add(nextBtn);

        nextBtn.addActionListener(e -> {
            String selectedTeam = (String) teamBox.getSelectedItem();
            frame.setUserTeam(selectedTeam);
            frame.showScreen("opponent");
        });
    }
}