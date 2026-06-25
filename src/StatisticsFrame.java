import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;

    public StatisticsFrame(Player player) {
        this.playerService = new PlayerService();
        // ambil data terbaru dari DB
        Player playerTerbaru = playerService.getPlayerById(player.getId());
        if (playerTerbaru != null) {
            this.currentPlayer = playerTerbaru;
        } else {
            this.currentPlayer = player;
        }

        setTitle("Statistik Saya");
        setSize(320, 320);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 50));

        JLabel lblJudul = new JLabel("STATISTIK SAYA");
        lblJudul.setBounds(85, 15, 180, 30);
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblJudul);

        JLabel lblNama = new JLabel("Player: " + currentPlayer.getUsername());
        lblNama.setBounds(50, 55, 220, 25);
        lblNama.setForeground(new Color(100, 200, 255));
        lblNama.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(lblNama);

        // kotak statistik
        String[] labelList = {"🏆  Menang", "💀  Kalah", "🤝  Seri", "⭐  Total Score"};
        String[] nilaiList = {
            String.valueOf(currentPlayer.getWins()),
            String.valueOf(currentPlayer.getLosses()),
            String.valueOf(currentPlayer.getDraws()),
            String.valueOf(currentPlayer.getScore())
        };
        Color[] warnaList = {
            new Color(46, 139, 87),
            new Color(180, 50, 50),
            new Color(70, 130, 180),
            new Color(184, 134, 11)
        };

        for (int i = 0; i < 4; i = i + 1) {
            JPanel kotakStat = new JPanel();
            kotakStat.setLayout(null);
            kotakStat.setBounds(50, 95 + i * 42, 220, 35);
            kotakStat.setBackground(warnaList[i]);

            JLabel lblNamaStatistik = new JLabel(labelList[i]);
            lblNamaStatistik.setBounds(10, 8, 130, 20);
            lblNamaStatistik.setForeground(Color.WHITE);
            lblNamaStatistik.setFont(new Font("Arial", Font.PLAIN, 12));
            kotakStat.add(lblNamaStatistik);

            JLabel lblNilaiStatistik = new JLabel(nilaiList[i]);
            lblNilaiStatistik.setBounds(175, 8, 40, 20);
            lblNilaiStatistik.setForeground(Color.WHITE);
            lblNilaiStatistik.setFont(new Font("Arial", Font.BOLD, 14));
            kotakStat.add(lblNilaiStatistik);

            panel.add(kotakStat);
        }

        JButton btnTutup = new JButton("Tutup");
        btnTutup.setBounds(100, 270, 120, 30);
        btnTutup.setBackground(new Color(100, 100, 100));
        btnTutup.setForeground(Color.WHITE);
        btnTutup.setFont(new Font("Arial", Font.BOLD, 12));
        btnTutup.setFocusPainted(false);
        panel.add(btnTutup);

        add(panel);

        btnTutup.addActionListener(e -> dispose());
    }
}
