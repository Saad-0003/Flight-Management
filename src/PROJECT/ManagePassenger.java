package PROJECT;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import static java.awt.Font.BOLD;

public class ManagePassenger extends JFrame {
    JPanel image1;
    BufferedImage new1;
    ImageIcon ii1;
    DefaultTableModel model;
    JTable table;
    private Connection conn;

    public ManagePassenger() {
        super("Admin");
        setLayout(null);
        setSize(1250, 720);
        setLocation(0, 0);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Initialize database connection
        connectToDatabase();

        //----------------------------------- top panel
        JPanel topp = new JPanel();
        topp.setLayout(null);
        topp.setBounds(0, 0, 1250, 100);
        topp.setBackground(new Color(234, 47, 74));
        add(topp);

        JLabel text = new JLabel("PASSENGER MANAGEMENT");
        text.setFont(new Font("Arial", BOLD, 40));
        text.setBounds(150, 25, 600, 50);
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

        //----------------------------------- Passenger Table
        String[] columns = {"Passenger ID", "Name", "Email", "Phone", "Flight No", "From", "To", "Boarding Pass", "Seat No", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        loadPassengerData();

        // Set table properties
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.getTableHeader().setFont(new Font("Arial", BOLD, 12));

        // Set header background color to match the red theme
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(234, 47, 74));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(80);  // Passenger ID
        table.getColumnModel().getColumn(1).setPreferredWidth(120); // Name
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Email
        table.getColumnModel().getColumn(3).setPreferredWidth(110); // Phone
        table.getColumnModel().getColumn(4).setPreferredWidth(70);  // Flight No
        table.getColumnModel().getColumn(5).setPreferredWidth(80);  // From
        table.getColumnModel().getColumn(6).setPreferredWidth(80);  // To
        table.getColumnModel().getColumn(7).setPreferredWidth(100); // Boarding Pass
        table.getColumnModel().getColumn(8).setPreferredWidth(60);  // Seat No
        table.getColumnModel().getColumn(9).setPreferredWidth(80);  // Status

        // Center align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 1150, 470);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        // ----------------------------- Buttons
        JButton addBtn = new JButton("Add");
        addBtn.setBounds(580, 610, 140, 35);
        addBtn.setBackground(new Color(40, 167, 69));
        addBtn.setBorderPainted(false);
        addBtn.setFocusPainted(false);
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 16));
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddPassengerDialog();
            }
        });
        add(addBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(740, 610, 140, 35);
        deleteBtn.setBackground(new Color(220, 53, 69));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 16));
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeletePassengerDialog();
            }
        });
        add(deleteBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(900, 610, 140, 35);
        updateBtn.setBackground(new Color(255, 193, 7));
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setFont(new Font("Arial", Font.BOLD, 16));
        updateBtn.setBorderPainted(false);
        updateBtn.setFocusPainted(false);
        updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdatePassengerDialog();
            }
        });
        add(updateBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(1060, 610, 140, 35);
        backBtn.setBackground(new Color(48, 166, 193));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Menu_page();
            }
        });
        add(backBtn);

        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airline_db",
                    "root",
                    "Fyy7UEkZ@" 
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(),
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadPassengerData() {
        try {
            model.setRowCount(0); // Clear existing data

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM passengers");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("passenger_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("flight_no"),
                        rs.getString("departure_city"),
                        rs.getString("destination_city"),
                        rs.getString("boarding_pass"),
                        rs.getString("seat_no"),
                        rs.getString("status")
                });
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading passenger data: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to show Add Passenger Dialog
    private void showAddPassengerDialog() {
        JDialog addDialog = new JDialog(this, "Add New Passenger", true);
        addDialog.setLayout(null);
        addDialog.setSize(500, 650);
        addDialog.setLocationRelativeTo(this);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 500, 60);
        headerPanel.setBackground(new Color(40, 167, 69));
        headerPanel.setLayout(null);

        JLabel headerLabel = new JLabel("Add New Passenger");
        headerLabel.setBounds(150, 15, 200, 30);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        addDialog.add(headerPanel);

        // Form fields
        String[] labels = {"Passenger ID:", "Name:", "Email:", "Phone:", "Flight No:", "From:", "To:", "Boarding Pass:", "Seat No:", "Status:"};
        JTextComponent[] fields = new JTextComponent[10];

        int yPos = 80;
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(30, yPos, 120, 25);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            addDialog.add(label);

            if (i == 2) { // Email field - make it text area for word wrap
                JTextArea textArea = new JTextArea();
                textArea.setFont(new Font("Arial", Font.PLAIN, 12));
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setBounds(160, yPos, 280, 45);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                addDialog.add(scrollPane);
                fields[i] = textArea;
                yPos += 55;
            } else {
                fields[i] = new JTextField();
                fields[i].setBounds(160, yPos, 280, 30);
                fields[i].setFont(new Font("Arial", Font.PLAIN, 12));
                addDialog.add(fields[i]);
                yPos += 40;
            }
        }

        // Status dropdown
        String[] statusOptions = {"Confirmed", "Pending", "Cancelled"};
        JComboBox<String> statusCombo = new JComboBox<>(statusOptions);
        statusCombo.setBounds(160, yPos - 40, 280, 30);
        statusCombo.setFont(new Font("Arial", Font.PLAIN, 12));
        addDialog.remove(fields[9]); // Remove the text field for status
        addDialog.add(statusCombo);

        // Buttons
        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(140, yPos + 30, 100, 35);
        saveBtn.setBackground(new Color(40, 167, 69));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Arial", Font.BOLD, 14));
        saveBtn.setBorderPainted(false);
        saveBtn.setFocusPainted(false);
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(260, yPos + 30, 100, 35);
        cancelBtn.setBackground(new Color(220, 53, 69));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cancelBtn.setBorderPainted(false);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate fields
                boolean valid = true;
                for (int i = 0; i < 9; i++) {
                    if (fields[i].getText().trim().isEmpty()) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    // Add new row to database
                    try {
                        String sql = "INSERT INTO passengers (passenger_id, name, email, phone, " +
                                "flight_no, departure_city, destination_city, boarding_pass, " +
                                "seat_no, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, fields[0].getText().trim());
                        pstmt.setString(2, fields[1].getText().trim());
                        pstmt.setString(3, fields[2].getText().trim());
                        pstmt.setString(4, fields[3].getText().trim());
                        pstmt.setString(5, fields[4].getText().trim());
                        pstmt.setString(6, fields[5].getText().trim());
                        pstmt.setString(7, fields[6].getText().trim());
                        pstmt.setString(8, fields[7].getText().trim());
                        pstmt.setString(9, fields[8].getText().trim());
                        pstmt.setString(10, statusCombo.getSelectedItem().toString());

                        pstmt.executeUpdate();
                        pstmt.close();

                        loadPassengerData(); // Refresh table
                        JOptionPane.showMessageDialog(addDialog, "Passenger added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        addDialog.dispose();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(addDialog, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(addDialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelBtn.addActionListener(e -> addDialog.dispose());

        addDialog.add(saveBtn);
        addDialog.add(cancelBtn);
        addDialog.setVisible(true);
    }

    // Method to show Delete Passenger Dialog
    private void showDeletePassengerDialog() {
        String passengerID = JOptionPane.showInputDialog(this, "Enter Passenger ID to delete:", "Delete Passenger", JOptionPane.QUESTION_MESSAGE);

        if (passengerID != null && !passengerID.trim().isEmpty()) {
            try {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete passenger " + passengerID + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM passengers WHERE passenger_id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, passengerID.trim());
                    int rowsAffected = pstmt.executeUpdate();
                    pstmt.close();

                    if (rowsAffected > 0) {
                        loadPassengerData(); // Refresh table
                        JOptionPane.showMessageDialog(this, "Passenger deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Passenger ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to show Update Passenger Dialog
    private void showUpdatePassengerDialog() {
        String passengerID = JOptionPane.showInputDialog(this, "Enter Passenger ID to update:", "Update Passenger", JOptionPane.QUESTION_MESSAGE);

        if (passengerID != null && !passengerID.trim().isEmpty()) {
            try {
                String sql = "SELECT * FROM passengers WHERE passenger_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, passengerID.trim());
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    showUpdateForm(rs);
                } else {
                    JOptionPane.showMessageDialog(this, "Passenger ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                rs.close();
                pstmt.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to show Update Form
    private void showUpdateForm(ResultSet rs) throws SQLException {
        String passengerId = rs.getString("passenger_id");

        JDialog updateDialog = new JDialog(this, "Update Passenger", true);
        updateDialog.setLayout(null);
        updateDialog.setSize(500, 650);
        updateDialog.setLocationRelativeTo(this);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 500, 60);
        headerPanel.setBackground(new Color(255, 193, 7));
        headerPanel.setLayout(null);

        JLabel headerLabel = new JLabel("Update Passenger");
        headerLabel.setBounds(170, 15, 200, 30);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.BLACK);
        headerPanel.add(headerLabel);
        updateDialog.add(headerPanel);

        // Form fields
        String[] labels = {"Passenger ID:", "Name:", "Email:", "Phone:", "Flight No:", "From:", "To:", "Boarding Pass:", "Seat No:", "Status:"};
        JTextComponent[] fields = new JTextComponent[10];

        int yPos = 80;
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(30, yPos, 120, 25);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            updateDialog.add(label);

            if (i == 2) { // Email field - make it text area for word wrap
                JTextArea textArea = new JTextArea();
                textArea.setFont(new Font("Arial", Font.PLAIN, 12));
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                textArea.setText(rs.getString(i+1));
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setBounds(160, yPos, 280, 45);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                updateDialog.add(scrollPane);
                fields[i] = textArea;
                yPos += 55;
            } else {
                fields[i] = new JTextField();
                fields[i].setBounds(160, yPos, 280, 30);
                fields[i].setFont(new Font("Arial", Font.PLAIN, 12));
                fields[i].setText(rs.getString(i+1));
                updateDialog.add(fields[i]);
                yPos += 40;
            }
        }

        // Status dropdown
        String[] statusOptions = {"Confirmed", "Pending", "Cancelled"};
        JComboBox<String> statusCombo = new JComboBox<>(statusOptions);
        statusCombo.setBounds(160, yPos - 40, 280, 30);
        statusCombo.setFont(new Font("Arial", Font.PLAIN, 12));
        statusCombo.setSelectedItem(rs.getString("status"));
        updateDialog.remove(fields[9]); // Remove the text field for status
        updateDialog.add(statusCombo);

        // Make Passenger ID field non-editable
        fields[0].setEditable(false);
        fields[0].setBackground(new Color(240, 240, 240));

        // Buttons
        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(130, yPos + 30, 100, 35);
        updateBtn.setBackground(new Color(255, 193, 7));
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setFont(new Font("Arial", Font.BOLD, 14));
        updateBtn.setBorderPainted(false);
        updateBtn.setFocusPainted(false);
        updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(270, yPos + 30, 100, 35);
        cancelBtn.setBackground(new Color(220, 53, 69));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cancelBtn.setBorderPainted(false);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate fields
                boolean valid = true;
                for (int i = 1; i < 9; i++) { // Skip passenger ID (index 0)
                    if (fields[i].getText().trim().isEmpty()) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    // Update row in database
                    try {
                        String sql = "UPDATE passengers SET name = ?, email = ?, phone = ?, " +
                                "flight_no = ?, departure_city = ?, destination_city = ?, " +
                                "boarding_pass = ?, seat_no = ?, status = ? " +
                                "WHERE passenger_id = ?";

                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, fields[1].getText().trim());
                        pstmt.setString(2, fields[2].getText().trim());
                        pstmt.setString(3, fields[3].getText().trim());
                        pstmt.setString(4, fields[4].getText().trim());
                        pstmt.setString(5, fields[5].getText().trim());
                        pstmt.setString(6, fields[6].getText().trim());
                        pstmt.setString(7, fields[7].getText().trim());
                        pstmt.setString(8, fields[8].getText().trim());
                        pstmt.setString(9, statusCombo.getSelectedItem().toString());
                        pstmt.setString(10, fields[0].getText().trim());

                        int rowsAffected = pstmt.executeUpdate();
                        pstmt.close();

                        if (rowsAffected > 0) {
                            loadPassengerData(); // Refresh table
                            JOptionPane.showMessageDialog(updateDialog, "Passenger updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            updateDialog.dispose();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(updateDialog, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(updateDialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelBtn.addActionListener(e -> updateDialog.dispose());

        updateDialog.add(updateBtn);
        updateDialog.add(cancelBtn);
        updateDialog.setVisible(true);
    }
}