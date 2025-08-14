package PROJECT;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.awt.Font.BOLD;

public class Amin_Login extends JFrame {

    public Amin_Login() {
        super("Admin Login");

        // ---------------------------- Background Panel
        BackgroundPanel bgPanel = new BackgroundPanel("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\admin.jpg", 0.7f);
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        setSize(1250, 720);
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ---------------------------- Login Panel
        JPanel logp = new JPanel();
        logp.setLayout(null);
        logp.setBounds(460, 150, 320, 380);
        logp.setBackground(Color.WHITE);
        add(logp);

        // ---------------------------- Top Label Panel
        JPanel textP = new JPanel();
        textP.setLayout(null);
        textP.setBounds(0, 0, 320, 50);
        textP.setBackground(new Color(234, 47, 74));
        logp.add(textP);

        JLabel text = new JLabel("ADMIN LOGIN");
        text.setFont(new Font("Arial", BOLD, 25));
        text.setBounds(80, 10, 250, 40);
        text.setForeground(Color.WHITE);
        textP.add(text);

        // ---------------------------- Username & Password
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userLabel.setBounds(40, 90, 100, 30);
        logp.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(40, 120, 240, 30);
        logp.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passLabel.setBounds(40, 170, 100, 30);
        logp.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(40, 200, 240, 30);
        logp.add(passField);

        // ---------------------------- Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 270, 120, 35);
        loginBtn.setBackground(new Color(234, 47, 74));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setBorderPainted(false);
        loginBtn.setFocusPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logp.add(loginBtn);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword());

                // Validate input
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Amin_Login.this,
                            "Please enter both username and password.",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Authenticate against database
                if (authenticateUser(username, password)) {
                    dispose();
                    new Menu_page();
                } else {
                    JOptionPane.showMessageDialog(Amin_Login.this,
                            "Invalid credentials. Please try again.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                    // Clear password field for security
                    passField.setText("");
                }
            }
        });

        // ----------------------------- back button
        JButton bckBtn = new JButton("Back");
        bckBtn.setBounds(1060, 610, 120, 35);
        bckBtn.setBackground(new Color(48, 166, 193));
        bckBtn.setForeground(Color.WHITE);
        bckBtn.setFont(new Font("Arial", Font.BOLD, 16));
        bckBtn.setBorderPainted(false);
        bckBtn.setFocusPainted(false);
        bckBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(bckBtn);

        setVisible(true);

        bckBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Landing();
            }
        });
    }

    // ---------------------------- Optimized Authentication Method
    private boolean authenticateUser(String username, String password) {
        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(this,
                        "Database connection error. Contact administrator.",
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String query = "SELECT * FROM Admins WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            return rs.next(); // Returns true if a matching record is found

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + e.getMessage(),
                    "SQL Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            // Clean up resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}

// ---------------------------- Custom Background Panel (Unchanged)
class BackgroundPanel extends JPanel {
    private BufferedImage image;
    private float opacity;

    public BackgroundPanel(String path, float opacity) {
        this.opacity = opacity;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Image not found: " + path);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}