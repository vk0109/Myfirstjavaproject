/*package StickManCricket;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
public class MainFrame extends JFrame {

    // 🔹 CardLayout aur panels
    CardLayout card;
    JPanel mainPanel;
    LoadingPanel loadingPanel;
    WelcomePanel welcomePanel;
    LoginPanel loginPanel;
    SignupPanel signupPanel;
    StartGamePanel startGamePanel;
    GameSettingsPanel gameSettingsPanel;
    TeamSelectPanel teamPanel;
    OppanentPanel opponentPanel;
    GamePanel gamePanel;
    Clip clip;
    String userTeam;
    String opponentTeam;

    // Constructor
    public MainFrame() {
        setTitle("Stickman Cricket Game");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 🔥 CardLayout initialize first
        card = new CardLayout();
        mainPanel = new JPanel(card);

        // 1️⃣ Welcome panel
        welcomePanel = new WelcomePanel(this);
        mainPanel.add(welcomePanel, "welcome");

        // 2️⃣ Loading panel
        loadingPanel = new LoadingPanel(this);
        mainPanel.add(loadingPanel, "loading");

        // 3️⃣ Login panel
        loginPanel = new LoginPanel(this);
        mainPanel.add(loginPanel, "login");

        // 4️⃣ Signup panel
        signupPanel = new SignupPanel(this);
        mainPanel.add(signupPanel, "signup");

        // 5️⃣ StartGame panel
        startGamePanel = new StartGamePanel(this);
        mainPanel.add(startGamePanel, "startGame");
        

gameSettingsPanel = new GameSettingsPanel(this);
mainPanel.add(gameSettingsPanel, "settings");

teamPanel = new TeamSelectPanel(this);
mainPanel.add(teamPanel, "team");

 opponentPanel = new OppanentPanel(this);
mainPanel.add(opponentPanel, "opponent");

gamePanel = new GamePanel(this);
mainPanel.add(gamePanel, "game");
        // Add mainPanel to frame
        add(mainPanel);

        // 🔹 Show welcome panel first
        card.show(mainPanel, "welcome");

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 🔹 Method to switch screens
    public void showScreen(String name) {
        card.show(mainPanel, name);
    }

    // 🔹 Getter for LoadingPanel
    public LoadingPanel getLoadingPanel() {
        return loadingPanel;
    }
    public void setUserTeam(String team) {
    this.userTeam = team;
}

public String getUserTeam() {
    return userTeam;
}

public void setOpponentTeam(String team) {
    this.opponentTeam = team;
}

public String getOpponentTeam() {
    return opponentTeam;
}
public void playMusic() {
    try {
        if (clip != null && clip.isRunning()) return;

        AudioInputStream audio = AudioSystem.getAudioInputStream(
            getClass().getResource("/ipl_full_theme.wav")
        );

        clip = AudioSystem.getClip();
        clip.open(audio);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void stopMusic() {
    if (clip != null) {
        clip.stop();
        clip.close();
    }
}
    public static void main(String[] args) {
        new MainFrame();
    }
}*/
package StickManCricket;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;

public class MainFrame extends JFrame {

    CardLayout card;
    JPanel mainPanel;
    LoadingPanel loadingPanel;
    WelcomePanel welcomePanel;
    LoginPanel loginPanel;
    SignupPanel signupPanel;
    StartGamePanel startGamePanel;
    GameSettingsPanel gameSettingsPanel;
    TeamSelectPanel teamPanel;
    OppanentPanel opponentPanel;
    // No static GamePanel – we'll create it dynamically

    Clip clip;

    // Settings data
    private int overs = 2;          // default overs
    private String difficulty = "Medium";
    private String stadium = "Ghogre Kridasankul , Jalna";
    private String userTeam = "India 🇮🇳";
    private String opponentTeam = "Australia 🇦🇺";

    public MainFrame() {
        setTitle("Stickman Cricket Game");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        card = new CardLayout();
        mainPanel = new JPanel(card);

        // 1️⃣ Welcome panel
        welcomePanel = new WelcomePanel(this);
        mainPanel.add(welcomePanel, "welcome");

        // 2️⃣ Loading panel
        loadingPanel = new LoadingPanel(this);
        mainPanel.add(loadingPanel, "loading");

        // 3️⃣ Login panel
        loginPanel = new LoginPanel(this);
        mainPanel.add(loginPanel, "login");

        // 4️⃣ Signup panel
        signupPanel = new SignupPanel(this);
        mainPanel.add(signupPanel, "signup");

        // 5️⃣ StartGame panel
        startGamePanel = new StartGamePanel(this);
        mainPanel.add(startGamePanel, "startGame");

        // 6️⃣ GameSettings panel
        gameSettingsPanel = new GameSettingsPanel(this);
        mainPanel.add(gameSettingsPanel, "settings");

        // 7️⃣ TeamSelect panel
        teamPanel = new TeamSelectPanel(this);
        mainPanel.add(teamPanel, "team");

        // 8️⃣ Opponent panel
        opponentPanel = new OppanentPanel(this);
        mainPanel.add(opponentPanel, "opponent");

        // ❌ No static GamePanel – will be created on demand

        add(mainPanel);
        card.show(mainPanel, "welcome");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showScreen(String name) {
        if (name.equals("game")) {
            // Dynamically create GamePanel with current settings
            GamePanel gp = new GamePanel(this, overs, difficulty, stadium, userTeam, opponentTeam);
            mainPanel.add(gp, "game");
            card.show(mainPanel, "game");
            gp.requestFocusInWindow(); // important for key events
        } else {
            card.show(mainPanel, name);
        }
    }

    // Getters & Setters for settings
    public void setOvers(int overs) { this.overs = overs; }
    public int getOvers() { return overs; }

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getDifficulty() { return difficulty; }

    public void setStadium(String stadium) { this.stadium = stadium; }
    public String getStadium() { return stadium; }

    public void setUserTeam(String team) { this.userTeam = team; }
    public String getUserTeam() { return userTeam; }

    public void setOpponentTeam(String team) { this.opponentTeam = team; }
    public String getOpponentTeam() { return opponentTeam; }

    public LoadingPanel getLoadingPanel() { return loadingPanel; }

    public void playMusic() {
        try {
            if (clip != null && clip.isRunning()) return;
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                getClass().getResource("/ipl_full_theme.wav")
            );
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}