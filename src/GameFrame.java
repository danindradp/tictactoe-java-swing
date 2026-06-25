import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    private JLabel lblSkor;
    private boolean gameSelesai;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();
        this.gameSelesai = false;

        setTitle("Tic-Tac-Toe - Game");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 50));

        JLabel lblJudul = new JLabel("TIC-TAC-TOE");
        lblJudul.setBounds(120, 10, 180, 30);
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblJudul);

        JLabel lblPlayer = new JLabel("Player: " + currentPlayer.getUsername() + "  (X)   vs   Komputer (O)");
        lblPlayer.setBounds(30, 45, 350, 20);
        lblPlayer.setForeground(new Color(150, 200, 255));
        lblPlayer.setFont(new Font("Arial", Font.PLAIN, 11));
        panel.add(lblPlayer);

        // papan 3x3
        buttons = new JButton[9];
        int startX = 40;
        int startY = 80;
        int ukuran = 95;

        for (int i = 0; i < 9; i = i + 1) {
            buttons[i] = new JButton("");
            int baris = i / 3;
            int kolom = i % 3;
            buttons[i].setBounds(startX + kolom * (ukuran + 5), startY + baris * (ukuran + 5), ukuran, ukuran);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 36));
            buttons[i].setBackground(new Color(50, 50, 80));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            panel.add(buttons[i]);

            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
        }

        lblStatus = new JLabel("Giliran kamu! Klik kotak untuk main.");
        lblStatus.setBounds(30, 390, 350, 25);
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(lblStatus);

        lblSkor = new JLabel("Score kamu sekarang: " + currentPlayer.getScore());
        lblSkor.setBounds(30, 415, 250, 20);
        lblSkor.setForeground(new Color(150, 200, 150));
        lblSkor.setFont(new Font("Arial", Font.PLAIN, 11));
        panel.add(lblSkor);

        JButton btnUlang = new JButton("Main Lagi");
        btnUlang.setBounds(40, 445, 130, 35);
        btnUlang.setBackground(new Color(46, 139, 87));
        btnUlang.setForeground(Color.WHITE);
        btnUlang.setFont(new Font("Arial", Font.BOLD, 12));
        btnUlang.setFocusPainted(false);
        panel.add(btnUlang);

        JButton btnMenu = new JButton("Kembali ke Menu");
        btnMenu.setBounds(185, 445, 155, 35);
        btnMenu.setBackground(new Color(70, 130, 180));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(new Font("Arial", Font.BOLD, 12));
        btnMenu.setFocusPainted(false);
        panel.add(btnMenu);

        add(panel);

        btnUlang.addActionListener(e -> {
            gameLogic.resetBoard();
            gameSelesai = false;
            for (int i = 0; i < 9; i = i + 1) {
                buttons[i].setText("");
                buttons[i].setBackground(new Color(50, 50, 80));
                buttons[i].setEnabled(true);
            }
            lblStatus.setText("Giliran kamu! Klik kotak untuk main.");
        });

        btnMenu.addActionListener(e -> {
            // refresh data player dari DB sebelum balik ke menu
            PlayerService ps = new PlayerService();
            Player playerTerbaru = ps.getPlayerById(currentPlayer.getId());
            if (playerTerbaru != null) {
                MainMenuFrame menuFrame = new MainMenuFrame(playerTerbaru);
                menuFrame.setVisible(true);
            } else {
                MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
                menuFrame.setVisible(true);
            }
            dispose();
        });
    }

    private void handlePlayerMove(int index) {
        if (gameSelesai) return;

        // coba gerak player
        boolean berhasil = gameLogic.makeMove(index, 'X');
        if (!berhasil) {
            JOptionPane.showMessageDialog(this, "Kotak sudah terisi! Pilih kotak lain.", "Invalid Move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // update tampilan button
        buttons[index].setText("X");
        buttons[index].setForeground(new Color(100, 200, 255));
        buttons[index].setBackground(new Color(30, 60, 100));

        // cek player menang
        if (gameLogic.checkWinner('X')) {
            gameSelesai = true;
            lblStatus.setText("🎉 Kamu MENANG! +10 poin");
            nonaktifkanBoard();
            selesaiGame("WIN");
            return;
        }

        // cek draw
        if (gameLogic.isDraw()) {
            gameSelesai = true;
            lblStatus.setText("🤝 SERI! +3 poin");
            selesaiGame("DRAW");
            return;
        }

        // giliran komputer
        lblStatus.setText("Komputer lagi mikir...");
        int gerakKomputer = gameLogic.computerMove();
        if (gerakKomputer != -1) {
            gameLogic.makeMove(gerakKomputer, 'O');
            buttons[gerakKomputer].setText("O");
            buttons[gerakKomputer].setForeground(new Color(255, 100, 100));
            buttons[gerakKomputer].setBackground(new Color(100, 30, 30));
        }

        // cek komputer menang
        if (gameLogic.checkWinner('O')) {
            gameSelesai = true;
            lblStatus.setText("😢 Komputer MENANG! +0 poin");
            nonaktifkanBoard();
            selesaiGame("LOSE");
            return;
        }

        // cek draw setelah komputer gerak
        if (gameLogic.isDraw()) {
            gameSelesai = true;
            lblStatus.setText("🤝 SERI! +3 poin");
            selesaiGame("DRAW");
            return;
        }

        lblStatus.setText("Giliran kamu!");
    }

    private void nonaktifkanBoard() {
        for (int i = 0; i < 9; i = i + 1) {
            buttons[i].setEnabled(false);
        }
    }

    private void selesaiGame(String hasil) {
        playerService.updateStatistics(currentPlayer, hasil);

        String pesan = "";
        if (hasil.equals("WIN")) {
            pesan = "Selamat! Kamu menang! +10 poin ditambahkan.";
        } else if (hasil.equals("LOSE")) {
            pesan = "Sayang sekali, komputer menang. Coba lagi!";
        } else {
            pesan = "Seri! +3 poin ditambahkan.";
        }
        JOptionPane.showMessageDialog(this, pesan, "Game Selesai", JOptionPane.INFORMATION_MESSAGE);
    }
}
