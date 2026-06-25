import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {
    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();

        setTitle("Top 5 Scorers");
        setSize(450, 280);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 50));

        JLabel lblJudul = new JLabel("🏆  TOP 5 SCORERS");
        lblJudul.setBounds(130, 10, 200, 30);
        lblJudul.setForeground(new Color(255, 200, 50));
        lblJudul.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblJudul);

        // kolom tabel
        String[] kolom = {"#", "Username", "Menang", "Kalah", "Seri", "Score"};
        DefaultTableModel modelTabel = new DefaultTableModel(kolom, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // ambil data top 5 dari database
        ArrayList<Player> daftarTop = playerService.getTopFiveScorers();
        for (int i = 0; i < daftarTop.size(); i = i + 1) {
            Player p = daftarTop.get(i);
            String nomor = String.valueOf(i + 1);
            Object[] baris = {nomor, p.getUsername(), p.getWins(), p.getLosses(), p.getDraws(), p.getScore()};
            modelTabel.addRow(baris);
        }

        table = new JTable(modelTabel);
        table.setBackground(new Color(40, 40, 65));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.setSelectionBackground(new Color(100, 150, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 50, 400, 160);
        scrollPane.getViewport().setBackground(new Color(40, 40, 65));
        panel.add(scrollPane);

        JButton btnTutup = new JButton("Tutup");
        btnTutup.setBounds(165, 225, 120, 30);
        btnTutup.setBackground(new Color(100, 100, 100));
        btnTutup.setForeground(Color.WHITE);
        btnTutup.setFont(new Font("Arial", Font.BOLD, 12));
        btnTutup.setFocusPainted(false);
        panel.add(btnTutup);

        add(panel);

        btnTutup.addActionListener(e -> dispose());
    }
}
