package StickManCricket;
import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JPanel {

    JProgressBar bar;
    Timer timer;
    int i = 0;

    LoadingPanel(MainFrame frame) {
        setLayout(null);

        // 🔥 Progress Bar create kiya
        bar = new JProgressBar(0, 100);
        bar.setBounds(100, 200, 300, 30);
        bar.setForeground(Color.BLACK);       // 🔥 Bar ka color black
        bar.setStringPainted(true);           // 🔥 Percentage dikhega
        add(bar);

        // 🔥 Timer for loading animation
        timer = new Timer(30, e -> {
            i++;
            bar.setValue(i);                   // progress set
            // bar.setString(i + "%");         // optional, setStringPainted(true) se bhi percentage dikhega

            if (i >= 100) {
                timer.stop();
                frame.showScreen("login");
            }
        });
    }

    // 🔥 Start loading method
    public void startLoading() {
        System.out.println("Loading Started 😎");
        i = 0;
        bar.setValue(0);
        timer.start();
    }
}