import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnLogout;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        setTitle("Tic-Tac-Toe - Main Menu");
        setSize(350, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 50));

        JLabel lblJudul = new JLabel("TIC-TAC-TOE GAME");
        lblJudul.setBounds(75, 15, 210, 30);
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblJudul);

        JLabel lblWelcome = new JLabel("Halo, " + currentPlayer.getUsername() + "!");
        lblWelcome.setBounds(100, 50, 200, 25);
        lblWelcome.setForeground(new Color(100, 200, 255));
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 13));
        panel.add(lblWelcome);

        btnStartGame = new JButton("▶  Mulai Game");
        btnStartGame.setBounds(75, 95, 200, 45);
        btnStartGame.setBackground(new Color(46, 139, 87));
        btnStartGame.setForeground(Color.WHITE);
        btnStartGame.setFont(new Font("Arial", Font.BOLD, 13));
        btnStartGame.setFocusPainted(false);
        panel.add(btnStartGame);

        btnStatistics = new JButton("📊  Statistik Saya");
        btnStatistics.setBounds(75, 155, 200, 45);
        btnStatistics.setBackground(new Color(70, 130, 180));
        btnStatistics.setForeground(Color.WHITE);
        btnStatistics.setFont(new Font("Arial", Font.BOLD, 13));
        btnStatistics.setFocusPainted(false);
        panel.add(btnStatistics);

        btnTopScorers = new JButton("🏆  Top 5 Scorers");
        btnTopScorers.setBounds(75, 215, 200, 45);
        btnTopScorers.setBackground(new Color(184, 134, 11));
        btnTopScorers.setForeground(Color.WHITE);
        btnTopScorers.setFont(new Font("Arial", Font.BOLD, 13));
        btnTopScorers.setFocusPainted(false);
        panel.add(btnTopScorers);

        btnLogout = new JButton("↩  Logout");
        btnLogout.setBounds(75, 275, 95, 40);
        btnLogout.setBackground(new Color(100, 100, 100));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 12));
        btnLogout.setFocusPainted(false);
        panel.add(btnLogout);

        btnExit = new JButton("✕  Keluar");
        btnExit.setBounds(180, 275, 95, 40);
        btnExit.setBackground(new Color(180, 50, 50));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Arial", Font.BOLD, 12));
        btnExit.setFocusPainted(false);
        panel.add(btnExit);

        add(panel);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statsFrame = new StatisticsFrame(currentPlayer);
            statsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnLogout.addActionListener(e -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            dispose();
        });

        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }
}
