package PROJECT;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

import static java.awt.Font.BOLD;

public class ManageFlight extends JFrame {
    JPanel image1;
    BufferedImage new1;
    DefaultTableModel model;
    JTable table;

    public ManageFlight() {
        super("Admin");
        setLayout(null);
        setSize(1250, 720);
        setLocation(0, 0);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        //----------------------------------- top panel
        JPanel topp = new JPanel();
        topp.setLayout(null);
        topp.setBounds(0, 0, 1250, 100);
        topp.setBackground(new Color(234, 47, 74));
        add(topp);

        JLabel text = new JLabel("FLIGHTS MANAGEMENT");
        text.setFont(new Font("Arial", BOLD, 40));
        text.setBounds(150, 25, 550, 50);
        text.setForeground(Color.WHITE);
        topp.add(text);

        // ---------------------- Add logo image
        image1 = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    new1 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\AAWHITE.png"));
                    g.drawImage(new1, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        image1.setOpaque(false);
        image1.setBounds(12, -41, 150, 200);
        image1.setLayout(null);
        topp.add(image1);

        //----------------------------------- Flight Table
        String[] columns = {"Flight No", "From", "To", "Departure", "Arrival", "Duration", "Price (PKR)", "Status"};
        model = new DefaultTableModel(columns, 0);
        loadFlightsData();

        table = new JTable(model);
        // Set table properties
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Arial", BOLD, 14));

        // Set header background color to match the red theme
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(234, 47, 74));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(80);  // Flight No
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // From
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // To
        table.getColumnModel().getColumn(3).setPreferredWidth(90);  // Departure
        table.getColumnModel().getColumn(4).setPreferredWidth(90);  // Arrival
        table.getColumnModel().getColumn(5).setPreferredWidth(80);  // Duration
        table.getColumnModel().getColumn(6).setPreferredWidth(100); // Price
        table.getColumnModel().getColumn(7).setPreferredWidth(80);  // Status

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
        addBtn.setBounds(650, 610, 120, 35);
        addBtn.setBackground(new Color(40, 167, 69));
        addBtn.setForeground(Color.WHITE);
        addBtn.setBorderPainted(false);
        addBtn.setFocusPainted(false);
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addBtn.setFont(new Font("Arial", Font.BOLD, 16));
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddFlightDialog();
            }
        });
        add(addBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(790, 610, 120, 35);
        deleteBtn.setBackground(new Color(220, 53, 69));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 16));
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteFlightDialog();
            }
        });
        add(deleteBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(930, 610, 120, 35);
        updateBtn.setBackground(new Color(255, 193, 7));
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setBorderPainted(false);
        updateBtn.setFocusPainted(false);
        updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateBtn.setFont(new Font("Arial", Font.BOLD, 16));
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateFlightDialog();
            }
        });
        add(updateBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(1070, 610, 120, 35);
        backBtn.setBackground(new Color(48, 166, 193));
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));

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

    private void loadFlightsData() {
        model.setRowCount(0); // Clear existing data

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flights")) {

            while (rs.next()) {
                Object[] row = {
                        rs.getString("flight_no"),
                        rs.getString("departure_city"),
                        rs.getString("destination_city"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getString("duration"),
                        String.format("%,.0f", rs.getDouble("price")),
                        rs.getString("status")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading flight data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addFlightToDatabase(Object[] flightData) {
        String sql = "INSERT INTO flights (flight_no, departure_city, destination_city, " +
                "departure_time, arrival_time, duration, price, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, (String) flightData[0]);
            pstmt.setString(2, (String) flightData[1]);
            pstmt.setString(3, (String) flightData[2]);
            pstmt.setString(4, (String) flightData[3]);
            pstmt.setString(5, (String) flightData[4]);
            pstmt.setString(6, (String) flightData[5]);
            pstmt.setDouble(7, Double.parseDouble(((String) flightData[6]).replace(",", "")));
            pstmt.setString(8, (String) flightData[7]);

            pstmt.executeUpdate();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error adding flight: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFlightInDatabase(int rowIndex, Object[] flightData) {
        String sql = "UPDATE flights SET departure_city=?, destination_city=?, " +
                "departure_time=?, arrival_time=?, duration=?, price=?, status=? " +
                "WHERE flight_no=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, (String) flightData[1]);
            pstmt.setString(2, (String) flightData[2]);
            pstmt.setString(3, (String) flightData[3]);
            pstmt.setString(4, (String) flightData[4]);
            pstmt.setString(5, (String) flightData[5]);
            pstmt.setDouble(6, Double.parseDouble(((String) flightData[6]).replace(",", "")));
            pstmt.setString(7, (String) flightData[7]);
            pstmt.setString(8, (String) flightData[0]);

            pstmt.executeUpdate();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error updating flight: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteFlightFromDatabase(String flightNo) {
        String sql = "DELETE FROM flights WHERE flight_no=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, flightNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting flight: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Add Flight Dialog
    private void showAddFlightDialog() {
        JDialog dialog = new JDialog(this, "Add New Flight", true);
        dialog.setSize(500, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Add New Flight");
        titleLabel.setFont(new Font("Arial", BOLD, 24));
        titleLabel.setForeground(new Color(234, 47, 74));
        titleLabel.setBounds(150, 20, 200, 30);
        dialog.add(titleLabel);

        // Form fields
        JLabel[] labels = {
                new JLabel("Flight No:"),
                new JLabel("From:"),
                new JLabel("To:"),
                new JLabel("Departure:"),
                new JLabel("Arrival:"),
                new JLabel("Duration:"),
                new JLabel("Price (PKR):"),
                new JLabel("Status:")
        };

        JTextField[] fields = new JTextField[8];

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(new Font("Arial", Font.BOLD, 14));
            labels[i].setForeground(Color.BLACK);
            labels[i].setBounds(50, 70 + (i * 50), 120, 25);
            dialog.add(labels[i]);

            fields[i] = new JTextField();
            fields[i].setBounds(180, 70 + (i * 50), 250, 30);
            fields[i].setFont(new Font("Arial", Font.PLAIN, 12));
            fields[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            dialog.add(fields[i]);
        }

        // Set default status
        fields[7].setText("Available");

        // Buttons
        JButton addButton = new JButton("Add Flight");
        addButton.setBounds(150, 480, 120, 35);
        addButton.setBackground(new Color(40, 167, 69));
        addButton.setForeground(Color.WHITE);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setFont(new Font("Arial", Font.BOLD, 14));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate fields
                boolean isValid = true;
                for (int i = 0; i < fields.length; i++) {
                    if (fields[i].getText().trim().isEmpty()) {
                        isValid = false;
                        fields[i].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    } else {
                        fields[i].setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)
                        ));
                    }
                }

                if (!isValid) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all required fields!",
                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if flight number already exists
                String flightNo = fields[0].getText().trim();
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).toString().equals(flightNo)) {
                        JOptionPane.showMessageDialog(dialog, "Flight number already exists!",
                                "Duplicate Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Add new row to table and database
                Object[] newRow = new Object[8];
                for (int i = 0; i < fields.length; i++) {
                    newRow[i] = fields[i].getText().trim();
                }

                // Add to database
                addFlightToDatabase(newRow);

                // Add to table
                model.addRow(newRow);

                JOptionPane.showMessageDialog(dialog, "Flight added successfully!");
                dialog.dispose();
            }
        });
        dialog.add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(280, 480, 120, 35);
        cancelButton.setBackground(new Color(108, 117, 125));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }

    // Delete Flight Dialog
    private void showDeleteFlightDialog() {
        JDialog dialog = new JDialog(this, "Delete Flight", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Delete Flight");
        titleLabel.setFont(new Font("Arial", BOLD, 24));
        titleLabel.setForeground(new Color(220, 53, 69));
        titleLabel.setBounds(120, 20, 160, 30);
        dialog.add(titleLabel);

        JLabel flightNoLabel = new JLabel("Flight Number:");
        flightNoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        flightNoLabel.setBounds(50, 70, 120, 25);
        dialog.add(flightNoLabel);

        JTextField flightNoField = new JTextField();
        flightNoField.setBounds(170, 70, 150, 30);
        flightNoField.setFont(new Font("Arial", Font.PLAIN, 12));
        flightNoField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        dialog.add(flightNoField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(120, 120, 80, 35);
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightNo = flightNoField.getText().trim();
                if (flightNo.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please enter flight number!",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Find and delete the flight
                boolean found = false;
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).toString().equals(flightNo)) {
                        int confirm = JOptionPane.showConfirmDialog(dialog,
                                "Are you sure you want to delete flight " + flightNo + "?",
                                "Confirm Delete", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            // Delete from database
                            deleteFlightFromDatabase(flightNo);

                            // Delete from table
                            model.removeRow(i);

                            JOptionPane.showMessageDialog(dialog, "Flight deleted successfully!");
                            dialog.dispose();
                        }
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(dialog, "Flight number not found!",
                            "Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.add(deleteButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(210, 120, 80, 35);
        cancelButton.setBackground(new Color(108, 117, 125));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }

    // Update Flight Dialog
    private void showUpdateFlightDialog() {
        JDialog searchDialog = new JDialog(this, "Update Flight - Search", true);
        searchDialog.setSize(400, 200);
        searchDialog.setLocationRelativeTo(this);
        searchDialog.setLayout(null);
        searchDialog.getContentPane().setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Find Flight to Update");
        titleLabel.setFont(new Font("Arial", BOLD, 20));
        titleLabel.setForeground(new Color(255, 193, 7));
        titleLabel.setBounds(90, 20, 220, 30);
        searchDialog.add(titleLabel);

        JLabel flightNoLabel = new JLabel("Flight Number:");
        flightNoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        flightNoLabel.setBounds(50, 70, 120, 25);
        searchDialog.add(flightNoLabel);

        JTextField flightNoField = new JTextField();
        flightNoField.setBounds(170, 70, 150, 30);
        flightNoField.setFont(new Font("Arial", Font.PLAIN, 12));
        flightNoField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchDialog.add(flightNoField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(105, 120, 120, 35);
        searchButton.setBackground(new Color(255, 193, 7));
        searchButton.setForeground(Color.BLACK);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightNo = flightNoField.getText().trim();
                if (flightNo.isEmpty()) {
                    JOptionPane.showMessageDialog(searchDialog, "Please enter flight number!",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Find the flight
                int rowIndex = -1;
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).toString().equals(flightNo)) {
                        rowIndex = i;
                        break;
                    }
                }

                if (rowIndex == -1) {
                    JOptionPane.showMessageDialog(searchDialog, "Flight number not found!",
                            "Not Found", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                searchDialog.dispose();
                showUpdateForm(rowIndex);
            }
        });
        searchDialog.add(searchButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(225, 120, 120, 35);
        cancelButton.setBackground(new Color(108, 117, 125));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.addActionListener(e -> searchDialog.dispose());
        searchDialog.add(cancelButton);

        searchDialog.setVisible(true);
    }

    // Update Form Dialog
    private void showUpdateForm(int rowIndex) {
        JDialog dialog = new JDialog(this, "Update Flight", true);
        dialog.setSize(500, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Update Flight Details");
        titleLabel.setFont(new Font("Arial", BOLD, 24));
        titleLabel.setForeground(new Color(255, 193, 7));
        titleLabel.setBounds(120, 20, 260, 30);
        dialog.add(titleLabel);

        // Form fields
        JLabel[] labels = {
                new JLabel("Flight No:"),
                new JLabel("From:"),
                new JLabel("To:"),
                new JLabel("Departure:"),
                new JLabel("Arrival:"),
                new JLabel("Duration:"),
                new JLabel("Price (PKR):"),
                new JLabel("Status:")
        };

        JTextField[] fields = new JTextField[8];

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(new Font("Arial", Font.BOLD, 14));
            labels[i].setForeground(Color.BLACK);
            labels[i].setBounds(50, 70 + (i * 50), 120, 25);
            dialog.add(labels[i]);

            fields[i] = new JTextField();
            fields[i].setBounds(180, 70 + (i * 50), 250, 30);
            fields[i].setFont(new Font("Arial", Font.PLAIN, 12));
            fields[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));

            // Pre-fill with existing data
            fields[i].setText(model.getValueAt(rowIndex, i).toString());

            // Make flight number field read-only
            if (i == 0) {
                fields[i].setEditable(false);
                fields[i].setBackground(new Color(240, 240, 240));
            }
            dialog.add(fields[i]);
        }

        // Buttons
        JButton updateButton = new JButton("Update");
        updateButton.setBounds(150, 480, 120, 35);
        updateButton.setBackground(new Color(255, 193, 7));
        updateButton.setForeground(Color.BLACK);
        updateButton.setBorderPainted(false);
        updateButton.setFocusPainted(false);
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate fields (skip flight number as it's read-only)
                boolean isValid = true;
                for (int i = 1; i < fields.length; i++) {
                    if (fields[i].getText().trim().isEmpty()) {
                        isValid = false;
                        fields[i].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    } else {
                        fields[i].setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)
                        ));
                    }
                }

                if (!isValid) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all required fields!",
                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Prepare update data
                Object[] updatedData = new Object[8];
                for (int i = 0; i < fields.length; i++) {
                    updatedData[i] = fields[i].getText().trim();
                }

                // Update database
                updateFlightInDatabase(rowIndex, updatedData);

                // Update the row in table
                for (int i = 0; i < fields.length; i++) {
                    model.setValueAt(fields[i].getText().trim(), rowIndex, i);
                }

                JOptionPane.showMessageDialog(dialog, "Flight updated successfully!");
                dialog.dispose();
            }
        });
        dialog.add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(280, 480, 125, 35);
        cancelButton.setBackground(new Color(108, 117, 125));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }
}