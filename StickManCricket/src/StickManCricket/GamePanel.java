
package StickManCricket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.sound.sampled.*;

public class GamePanel extends JPanel {
    // Positions
    int playerX = 220;
    int playerY = 320;
    int bowlerX = 220;
    int bowlerY = 20;
    boolean isBowling = true;
    int bowlerFrame = 0;

    // Ball
    int ballX = 230;
    int ballY = 80;
    int ballDX = 0;
    int ballDY = 0;
    boolean isHit = false;
    boolean ballReleased = false;

    // Animations
    int swingFrame = 0;

    // Game state
    int runs = 0, wickets = 0, balls = 0;
    int target;
    int totalBalls;
    boolean gameActive = true;
    Random rand = new Random();

    // UI Components
    JLabel scoreLabel, overLabel, statusLabel, targetLabel;
    JLabel teamLabel, stadiumLabel;

    // Stumps
    int bowlerStumpX = 225;
    int bowlerStumpY = 105;
    int batsmanStumpX = 225;
    int batsmanStumpY = 370;

    // Settings
    private int overs;
    private String difficulty;
    private String stadium;
    private String userTeam;
    private String opponentTeam;

    // 🎵 Sound clips
    private Clip hitSound;
    private Clip boundarySound;
    private Clip wicketSound;

    public GamePanel(MainFrame frame, int overs, String difficulty, String stadium, String userTeam, String opponentTeam) {
        this.overs = overs;
        this.difficulty = difficulty;
        this.stadium = stadium;
        this.userTeam = userTeam;
        this.opponentTeam = opponentTeam;

        target = calculateTarget();
        totalBalls = overs * 6;

        // 🎵 Load sounds
        loadSounds();

        setLayout(null);
        setBackground(new Color(34, 139, 34));

        // ... (rest of UI setup remains same)
        scoreLabel = new JLabel("Score: 0/0");
        scoreLabel.setBounds(20, 10, 150, 30);
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);

        overLabel = new JLabel("Overs: 0.0");
        overLabel.setBounds(350, 10, 120, 30);
        overLabel.setForeground(Color.WHITE);
        add(overLabel);

        targetLabel = new JLabel("Target: " + target);
        targetLabel.setBounds(200, 10, 120, 30);
        targetLabel.setForeground(Color.YELLOW);
        add(targetLabel);

        teamLabel = new JLabel(userTeam + " vs " + opponentTeam);
        teamLabel.setBounds(20, 50, 250, 20);
        teamLabel.setForeground(Color.WHITE);
        add(teamLabel);

        stadiumLabel = new JLabel("🏟️ " + stadium);
        stadiumLabel.setBounds(20, 75, 400, 20);
        stadiumLabel.setForeground(Color.WHITE);
        add(stadiumLabel);

        statusLabel = new JLabel("← → move | SPACE = shot", SwingConstants.CENTER);
        statusLabel.setBounds(100, 100, 300, 30);
        statusLabel.setForeground(Color.WHITE);
        add(statusLabel);

        // Controls
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!gameActive) return;

                if (e.getKeyCode() == KeyEvent.VK_LEFT) playerX -= 12;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) playerX += 12;
                if (playerX < 190) playerX = 190;
                if (playerX > 250) playerX = 250;

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (swingFrame == 0) {
                        swingFrame = 10;
                        checkShot(frame);
                    }
                }
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { requestFocusInWindow(); }
        });
        SwingUtilities.invokeLater(this::requestFocusInWindow);

        // Game loop (same as before)
        new Timer(30, e -> {
            if (!gameActive) return;

            if (isBowling) {
                bowlerY += 2;
                bowlerFrame = (bowlerFrame + 1) % 6;
                if (bowlerY >= 55 && !ballReleased) {
                    ballX = bowlerX + 15;
                    ballY = bowlerY + 28;
                    ballDX = 0;
                    ballDY = 3 + rand.nextInt(2);
                    ballReleased = true;
                }
                if (bowlerY >= 95) isBowling = false;
            }

            if (ballReleased && !isHit) {
                ballX += ballDX;
                ballY += ballDY;
            } else if (isHit) {
                ballX += ballDX;
                ballY += ballDY;
                ballDY += 1;
            }

            if (ballY > 420 || ballX < -20 || ballX > getWidth() + 20) {
                resetBall(frame);
            }

            if (swingFrame > 0) swingFrame--;
            repaint();
        }).start();
    }

    // 🎵 Load sounds from resources folder
    private void loadSounds() {
        try {
            // Hit sound
            AudioInputStream hitStream = AudioSystem.getAudioInputStream(
                getClass().getResourceAsStream("vishiv-crowd-cheering-in-stadium-435357.wav")
            );
            hitSound = AudioSystem.getClip();
            hitSound.open(hitStream);

            // Boundary sound
            AudioInputStream boundaryStream = AudioSystem.getAudioInputStream(
                getClass().getResourceAsStream("vishiv-crowd-cheering-in-stadium-435357.wav")
            );
            boundarySound = AudioSystem.getClip();
            boundarySound.open(boundaryStream);

            // Wicket sound
            AudioInputStream wicketStream = AudioSystem.getAudioInputStream(
                getClass().getResourceAsStream("vishiv-crowd-cheering-in-stadium-435357.wav")
            );
            wicketSound = AudioSystem.getClip();
            wicketSound.open(wicketStream);

        } catch (Exception e) {
            System.err.println("Sound files missing: " + e.getMessage());
        }
    }

    // 🎵 Play a sound (rewind and start)
    private void playSound(Clip clip) {
        if (clip != null) {
            clip.stop();       // stop if already playing
            clip.setFramePosition(0); // rewind
            clip.start();
        }
    }

    private int calculateTarget() {
        int baseRunsPerOver;
        switch (opponentTeam) {
            case "India 🇮🇳": baseRunsPerOver = 8; break;
            case "Australia 🇦🇺": baseRunsPerOver = 9; break;
            case "England 🏴": baseRunsPerOver = 7; break;
            case "Pakistan 🇵🇰": baseRunsPerOver = 8; break;
            case "South Africa 🇿🇦": baseRunsPerOver = 7; break;
            default: baseRunsPerOver = 7;
        }
        double multiplier;
        switch (difficulty) {
            case "Easy": multiplier = 0.8; break;
            case "Medium": multiplier = 1.0; break;
            case "Hard": multiplier = 1.2; break;
            default: multiplier = 1.0;
        }
        int targetRuns = (int) Math.round(baseRunsPerOver * overs * multiplier);
        return Math.max(1, targetRuns);
    }

    void checkShot(MainFrame frame) {
        if (!gameActive) return;

        boolean validHitZone = (Math.abs(playerX - ballX) < 40 && ballY > 280 && ballReleased && !isHit);

        if (validHitZone) {
            balls++;
            updateScore();

            int offset = ballX - (playerX + 10);
            int runsThisBall = 0;

            // Direction-based runs
            if (offset < -15) {
                runsThisBall = 6;
                statusLabel.setText("SIXER 🔥");
                playSound(boundarySound);   // boundary sound
            } else if (offset <= 5) {
                runsThisBall = 4;
                statusLabel.setText("FOUR 💥");
                playSound(boundarySound);
            } else if (offset <= 25) {
                runsThisBall = 3;
                statusLabel.setText("3 runs");
                playSound(hitSound);        // normal hit
            } else {
                runsThisBall = rand.nextInt(2) + 1;
                statusLabel.setText(runsThisBall + " runs");
                playSound(hitSound);
            }

            runs += runsThisBall;
            ballDX = 0;
            ballDY = -(12 + rand.nextInt(5));
            isHit = true;

        } else {
            // Miss = wicket
            wickets++;
            balls++;
            updateScore();
            statusLabel.setText("WICKET! ❌");
            playSound(wicketSound);        // wicket sound
            resetBall(frame);
        }

        updateScore();
        checkGameOver(frame);
    }

    void resetBall(MainFrame frame) {
        if (!gameActive) return;

        if (!isHit && ballReleased) {
            balls++;
            updateScore();
            statusLabel.setText("DOT BALL ⚪");
        }

        checkGameOver(frame);

        ballY = 80;
        ballX = 200 + rand.nextInt(100);
        bowlerY = 20;
        isBowling = true;
        ballReleased = false;
        isHit = false;
        ballDX = 0;
        ballDY = 0;
        bowlerFrame = 0;
        swingFrame = 0;
    }

    private void checkGameOver(MainFrame frame) {
        if (!gameActive) return;

        if (runs >= target) {
            gameActive = false;
            JOptionPane.showMessageDialog(this, "YOU WON 🎉\nScore: " + runs + "/" + wickets);
            frame.showScreen("welcome");
            return;
        }
        if (balls >= totalBalls || wickets >= 3) {
            gameActive = false;
            if (runs < target) {
                JOptionPane.showMessageDialog(this, "YOU LOST 😢\nScore: " + runs + "/" + wickets);
            } else {
                JOptionPane.showMessageDialog(this, "YOU WON 🎉\nScore: " + runs + "/" + wickets);
            }
            frame.showScreen("welcome");
        }
    }

    void updateScore() {
        scoreLabel.setText("Score: " + runs + "/" + wickets);
        int oversDone = balls / 6;
        int rem = balls % 6;
        overLabel.setText("Overs: " + oversDone + "." + rem);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(190, 110, 100, 300);

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(190, 280, 290, 280);
        g2d.drawLine(190, 125, 290, 125);

        drawStumps(g2d, bowlerStumpX, bowlerStumpY);
        drawStumps(g2d, batsmanStumpX, batsmanStumpY);

        drawBowler(g2d, bowlerX, bowlerY);
        drawBatsman(g2d, playerX, playerY);

        g2d.setColor(Color.RED);
        g2d.fillOval(ballX, ballY, 16, 16);
    }

    private void drawStumps(Graphics2D g, int sx, int sy) {
        g.setColor(new Color(240, 240, 200));
        g.fillRect(sx - 7, sy, 4, 32);
        g.fillRect(sx - 1, sy, 4, 32);
        g.fillRect(sx + 5, sy, 4, 32);
        g.setColor(Color.WHITE);
        g.fillRect(sx - 8, sy - 3, 19, 3);
    }

    private void drawBatsman(Graphics2D g, int x, int y) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3));

        g.fillOval(x + 2, y, 18, 18);
        g.drawLine(x + 11, y + 19, x + 11, y + 48);
        g.drawLine(x + 11, y + 26, x - 8, y + 38);
        g.drawLine(x + 11, y + 26, x + 24, y + 35);
        g.drawLine(x + 11, y + 48, x - 3, y + 68);
        g.drawLine(x + 11, y + 48, x + 24, y + 68);

        Graphics2D bat = (Graphics2D) g.create();
        bat.setColor(new Color(139, 69, 19));
        bat.setStroke(new BasicStroke(3));

        int pivotX = x + 26;
        int pivotY = y + 32;

        double angle = Math.toRadians(30);
        if (swingFrame > 0) {
            double prog = swingFrame / 10.0;
            angle = Math.toRadians(30 - 90 * prog);
        }

        bat.translate(pivotX, pivotY);
        bat.rotate(angle);
        bat.fillRect(0, -3, 18, 6);
        bat.fillRect(16, -7, 28, 13);
        bat.setColor(new Color(180, 120, 60));
        bat.drawLine(17, -6, 43, -6);
        bat.dispose();
    }

    private void drawBowler(Graphics2D g, int x, int y) {
        g.setColor(new Color(0, 100, 200));
        g.setStroke(new BasicStroke(3));

        g.fillOval(x + 2, y, 18, 18);
        g.drawLine(x + 11, y + 19, x + 11, y + 48);
        g.drawLine(x + 11, y + 48, x - 4, y + 68);
        g.drawLine(x + 11, y + 48, x + 22, y + 68);
        g.drawLine(x + 11, y + 26, x - 6, y + 22);

        int armSwing = (isBowling && bowlerFrame % 3 == 0) ? -8 : 8;
        g.drawLine(x + 11, y + 26, x + 32, y + 22 + armSwing);
    }
}