package PROJECT;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import static java.awt.Font.BOLD;

public class ViewFeedback extends JFrame {

    JPanel image1;
    BufferedImage new1;
    ImageIcon ii1;
    private Connection conn;

    public ViewFeedback(){
        super("Admin");
        setLayout(null);
        setSize(1250, 720);
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize database connection
        connectToDatabase();

        //----------------------------------- top panel
        JPanel topp = new JPanel();
        topp.setLayout(null);
        topp.setBounds(0,0,1250,100);
        topp.setBackground(new Color(234, 47, 74));
        add(topp);

        JLabel text = new JLabel("FEEDBACK");
        text.setFont(new Font("Arial", BOLD, 40));
        text.setBounds(150, 25, 300, 50);
        text.setForeground(Color.WHITE);
        topp.add(text);

        // ---------------------- Add logo image
        image1=new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);try {
                    new1 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\AAWHITE.png"));
                    g.drawImage(new1, 0, 0, getWidth(), getHeight(), this);}
                catch (IOException e) {
                    e.printStackTrace();
                }}};

        image1.setOpaque(false);
        image1.setBounds(12, -41, 150, 200);
        image1.setLayout(null);
        topp.add(image1);

        //----------------------------------- Feedback Table
        String[] columnNames = {"NAME", "E-MAIL", "COMPLAIN TOPIC", "FEEDBACK", "SUGGESTIONS"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        loadFeedbackData(model);

        JTable table = new JTable(model);

        // Set table properties
        table.setRowHeight(60);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Arial", BOLD, 14));

        // Set header background color to match the red theme
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(234, 47, 74));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(120); // NAME
        table.getColumnModel().getColumn(1).setPreferredWidth(180); // E-MAIL
        table.getColumnModel().getColumn(2).setPreferredWidth(140); // COMPLAIN TOPIC
        table.getColumnModel().getColumn(3).setPreferredWidth(250); // FEEDBACK
        table.getColumnModel().getColumn(4).setPreferredWidth(250); // SUGGESTIONS

        // Enable text wrapping in cells
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setVerticalAlignment(JLabel.TOP);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 1150, 470);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        // ----------------------------- back button
        JButton bckBtn = new JButton("Back");
        bckBtn.setBounds(1060, 610, 120, 35);
        bckBtn.setBackground(new Color(48, 166, 193));
        bckBtn.setForeground(Color.WHITE);
        bckBtn.setBorderPainted(false);
        bckBtn.setFocusPainted(false);
        bckBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bckBtn.setFont(new Font("Arial", Font.BOLD, 16));
        bckBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close current window
                new Menu_page(); // Open Menu_page
            }
        });
        add(bckBtn);
        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airline_db",
                    "root",
                    "Password" // Replace with your actual password
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(),
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFeedbackData(DefaultTableModel model) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, email, topic, feedback, suggestions FROM feedback");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("topic"),
                        rs.getString("feedback"),
                        rs.getString("suggestions")
                });
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading feedback data: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
