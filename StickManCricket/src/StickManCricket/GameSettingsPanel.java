/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class GameSettingsPanel extends JPanel {

    public GameSettingsPanel(MainFrame frame) {
        setLayout(null);

        // 🎨 Background color
        setBackground(new Color(20, 20, 40)); // dark bluish 🔥

        // 🏏 Title
        JLabel title = new JLabel("GAME SETTINGS");
        title.setBounds(140, 30, 250, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        // 🔢 Overs
        JLabel oversLabel = new JLabel("Select Overs:");
        oversLabel.setBounds(80, 100, 150, 30);
        oversLabel.setForeground(Color.WHITE);
        add(oversLabel);

        String[] overs = {"2 Overs", "5 Overs", "10 Overs"};
        JComboBox<String> oversBox = new JComboBox<>(overs);
        oversBox.setBounds(220, 100, 150, 30);
        add(oversBox);

        // 🎯 Difficulty
        JLabel diffLabel = new JLabel("Difficulty:");
        diffLabel.setBounds(80, 150, 150, 30);
        diffLabel.setForeground(Color.WHITE);
        add(diffLabel);

        String[] diff = {"Easy", "Medium", "Hard"};
        JComboBox<String> diffBox = new JComboBox<>(diff);
        diffBox.setBounds(220, 150, 150, 30);
        add(diffBox);

        // 🏟 Stadium
        JLabel stadiumLabel = new JLabel("Select Stadium:");
        stadiumLabel.setBounds(80, 200, 150, 30);
        stadiumLabel.setForeground(Color.WHITE);
        add(stadiumLabel);

        String[] stadiums = {"Ghogre Kridasankul , Jalna ","Wankhede Stadium , Mumbai", " Arun Jaitely Stadium ,Delhi", "Chennaswamy Stadium ,Bangalore","Narendra Modi Stadium , Ahmedabad"};
        JComboBox<String> stadiumBox = new JComboBox<>(stadiums);
        stadiumBox.setBounds(220, 200, 150, 30);
        add(stadiumBox);

        // ▶️ Start Button
        JButton startBtn = new JButton("START MATCH");
        startBtn.setBounds(150, 280, 180, 40);

        // 🎨 Button styling
        startBtn.setBackground(Color.ORANGE);
        startBtn.setForeground(Color.BLACK);
        startBtn.setFocusPainted(false);

        add(startBtn);

        // 🚀 Action
        startBtn.addActionListener(e -> {
            String selectedOvers = (String) oversBox.getSelectedItem();
            String selectedDiff = (String) diffBox.getSelectedItem();
            String selectedStadium = (String) stadiumBox.getSelectedItem();

            JOptionPane.showMessageDialog(this,
                "Match Starting!\n" +
                selectedOvers + "\n" +
                selectedDiff + "\n" +
                selectedStadium
            );

            frame.showScreen("team"); // 🔥 next
        });
    }
}*/
package StickManCricket;

import javax.swing.*;
import java.awt.*;

public class GameSettingsPanel extends JPanel {

    public GameSettingsPanel(MainFrame frame) {
        setLayout(null);
        setBackground(new Color(20, 20, 40));

        JLabel title = new JLabel("GAME SETTINGS");
        title.setBounds(140, 30, 250, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        // Overs
        JLabel oversLabel = new JLabel("Select Overs:");
        oversLabel.setBounds(80, 100, 150, 30);
        oversLabel.setForeground(Color.WHITE);
        add(oversLabel);

        String[] overs = {"2 Overs", "5 Overs", "10 Overs"};
        JComboBox<String> oversBox = new JComboBox<>(overs);
        oversBox.setBounds(220, 100, 150, 30);
        add(oversBox);

        // Difficulty
        JLabel diffLabel = new JLabel("Difficulty:");
        diffLabel.setBounds(80, 150, 150, 30);
        diffLabel.setForeground(Color.WHITE);
        add(diffLabel);

        String[] diff = {"Easy", "Medium", "Hard"};
        JComboBox<String> diffBox = new JComboBox<>(diff);
        diffBox.setBounds(220, 150, 150, 30);
        add(diffBox);

        // Stadium
        JLabel stadiumLabel = new JLabel("Select Stadium:");
        stadiumLabel.setBounds(80, 200, 150, 30);
        stadiumLabel.setForeground(Color.WHITE);
        add(stadiumLabel);

        String[] stadiums = {"Ghogre Kridasankul , Jalna", "Wankhede Stadium , Mumbai", "Arun Jaitely Stadium ,Delhi", "Chennaswamy Stadium ,Bangalore", "Narendra Modi Stadium , Ahmedabad"};
        JComboBox<String> stadiumBox = new JComboBox<>(stadiums);
        stadiumBox.setBounds(220, 200, 150, 30);
        add(stadiumBox);

        // Start Button
        JButton startBtn = new JButton("START MATCH");
        startBtn.setBounds(150, 280, 180, 40);
        startBtn.setBackground(Color.ORANGE);
        startBtn.setForeground(Color.BLACK);
        startBtn.setFocusPainted(false);
        add(startBtn);

        startBtn.addActionListener(e -> {
            String selectedOvers = (String) oversBox.getSelectedItem();
            String selectedDiff = (String) diffBox.getSelectedItem();
            String selectedStadium = (String) stadiumBox.getSelectedItem();

            // Convert overs to integer
            int oversNum = Integer.parseInt(selectedOvers.split(" ")[0]);
            frame.setOvers(oversNum);
            frame.setDifficulty(selectedDiff);
            frame.setStadium(selectedStadium);

            frame.showScreen("team");
        });
    }
}

