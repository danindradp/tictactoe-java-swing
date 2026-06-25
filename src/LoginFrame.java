import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private PlayerService playerService;

    public LoginFrame() {
        playerService = new PlayerService();

        setTitle("Tic-Tac-Toe - Login");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 50));

        JLabel lblJudul = new JLabel("TIC-TAC-TOE GAME");
        lblJudul.setBounds(80, 15, 200, 30);
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblJudul);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(40, 60, 80, 25);
        lblUsername.setForeground(Color.WHITE);
        panel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(130, 60, 170, 25);
        panel.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(40, 100, 80, 25);
        lblPassword.setForeground(Color.WHITE);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130, 100, 170, 25);
        panel.add(txtPassword);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(110, 145, 120, 35);
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 13));
        btnLogin.setFocusPainted(false);
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username dan password tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Player player = playerService.login(username, password);
                if (player != null) {
                    JOptionPane.showMessageDialog(null, "Login berhasil! Selamat datang, " + player.getUsername() + "!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    MainMenuFrame menuFrame = new MainMenuFrame(player);
                    menuFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
