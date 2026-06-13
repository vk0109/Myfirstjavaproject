/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StickManCricket;

import javax.swing.*;
import java.awt.Image;
public class WelcomePanel extends JPanel {

    WelcomePanel(MainFrame frame) {
        setLayout(null);

       
    // ImageIcon img = new ImageIcon("cricket.png");
   // ImageIcon img = new ImageIcon(getClass().getResource("/cricket.png"));
//JLabel icon = new JLabel(img);
//icon.setBounds(180, 80, 512, 512);
//add(icon);
ImageIcon img = new ImageIcon(getClass().getResource("/cricket.png"));

// scale image if needed
Image image = img.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
img = new ImageIcon(image);

JLabel icon = new JLabel(img);

// panel 500x500 → center
icon.setBounds((500-200)/2, 50, 200, 200); // x=150, y=50, w=200, h=200
add(icon);

        JButton play = new JButton("PLAY");
        play.setBounds(180, 280, 120, 40);
        add(play);
        frame.playMusic(); // 🎧 start from beginning
play.addActionListener(e -> {
    frame.showScreen("loading");
    frame.loadingPanel.startLoading();
});
       
    }
}
