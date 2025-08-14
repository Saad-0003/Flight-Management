 package PROJECT;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BookTicket extends JFrame {
    private JPanel bookingPanel, passengerPanel;
    private Color primaryColor = new Color(211, 18, 39);
    private Font montserratBold;
    private Font montserratRegular;
    private BufferedImage backgroundImage;
    JButton button1;
    private String generatedTicketNumber;
    private Connection conn;

    // Flight details storage
    private String departureCity;
    private String destinationCity;
    private String departureDate;
    private String tripType;

    public BookTicket() {
        super("AIR AXIAL - Book Tickets");
        setLayout(null);
        setSize(1250, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize database connection
        connectToDatabase();

        button1 = new JButton("Back to dashboard");
        button1.setBounds(30, 30, 250, 40);
        button1.setForeground(Color.WHITE);
        button1.setBackground(new Color(234, 47, 74));
        button1.setFont(new Font("Montserrat", Font.BOLD, 20));
        button1.setBorderPainted(false);
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button1.setVisible(true);
        button1.setFocusPainted(false);
        add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Landing();
            }
        });

        try {
            File fontFileBold = new File(getClass().getResource("Montserrat-Bold.ttf").toURI());
            File fontFileRegular = new File(getClass().getResource("Montserrat-Regular.ttf").toURI());
            montserratBold = Font.createFont(Font.TRUETYPE_FONT, fontFileBold).deriveFont(Font.BOLD, 16f);
            montserratRegular = Font.createFont(Font.TRUETYPE_FONT, fontFileRegular).deriveFont(Font.PLAIN, 14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(montserratBold);
            ge.registerFont(montserratRegular);
        } catch (Exception e) {
            montserratBold = new Font("SansSerif", Font.BOLD, 16);
            montserratRegular = new Font("SansSerif", Font.PLAIN, 14);
        }

        loadImages();
        createPanels();
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

    private void saveTicketToDB(String firstName, String lastName, String nationality,
                                String passportNo, String travelClass) {
        try {
            String sql = "INSERT INTO tickets (ticket_number, departure_city, destination_city, " +
                    "departure_date, trip_type, first_name, last_name, nationality, " +
                    "passport_number, travel_class) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, generatedTicketNumber);
            pstmt.setString(2, departureCity);
            pstmt.setString(3, destinationCity);
            pstmt.setString(4, departureDate);
            pstmt.setString(5, tripType);
            pstmt.setString(6, firstName);
            pstmt.setString(7, lastName);
            pstmt.setString(8, nationality);
            pstmt.setString(9, passportNo);
            pstmt.setString(10, travelClass);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving ticket: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadImages() {
        try {
            backgroundImage = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\booking.jpg"));
        } catch (IOException e) {
            System.err.println("Error loading images: " + e.getMessage());
        }
    }

    private void createPanels() {
        bookingPanel = createBookingPanel();
        passengerPanel = createPassengerPanel();

        add(bookingPanel);
        add(passengerPanel);

        bookingPanel.setVisible(true);
        passengerPanel.setVisible(false);
    }

    private JPanel createBookingPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 1250, 720);

        panel.add(createBackgroundPanel(600));
        panel.add(createFormPanel("Flight Booking", "Step 1 of 2: Flight Details"));
        panel.add(createHeaderPanel("Flight Booking"));

        JPanel contentPanel = new JPanel(null);
        contentPanel.setBounds(100, 80, 500, 500);
        ((JPanel) panel.getComponent(1)).add(contentPanel);

        JLabel formTitle = new JLabel("Enter Flight Information", JLabel.CENTER);
        formTitle.setBounds(0, 0, 500, 50);
        formTitle.setFont(montserratBold.deriveFont(26f));
        formTitle.setForeground(primaryColor);
        contentPanel.add(formTitle);

        JLabel fromLabel = new JLabel("Departure City*:");
        fromLabel.setBounds(50, 70, 150, 30);
        fromLabel.setFont(montserratBold.deriveFont(16f));
        contentPanel.add(fromLabel);

        JTextField fromField = new JTextField();
        fromField.setBounds(200, 70, 250, 40);
        fromField.setFont(montserratRegular.deriveFont(16f));
        fromField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPanel.add(fromField);

        JLabel toLabel = new JLabel("Destination City*:");
        toLabel.setBounds(50, 130, 150, 30);
        toLabel.setFont(montserratBold.deriveFont(16f));
        contentPanel.add(toLabel);

        JTextField toField = new JTextField();
        toField.setBounds(200, 130, 250, 40);
        toField.setFont(montserratRegular.deriveFont(16f));
        toField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPanel.add(toField);

        JLabel dateLabel = new JLabel("Departure Date*:");
        dateLabel.setBounds(50, 190, 150, 30);
        dateLabel.setFont(montserratBold.deriveFont(16f));
        contentPanel.add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(200, 190, 250, 40);
        dateField.setFont(montserratRegular.deriveFont(16f));
        dateField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPanel.add(dateField);

        JLabel tripLabel = new JLabel("Trip Type*:");
        tripLabel.setBounds(50, 250, 150, 30);
        tripLabel.setFont(montserratBold.deriveFont(16f));
        contentPanel.add(tripLabel);

        JComboBox<String> tripCombo = new JComboBox<>(new String[]{"One Way", "Return"});
        tripCombo.setBounds(200, 250, 250, 40);
        tripCombo.setFont(montserratRegular.deriveFont(16f));
        tripCombo.setBackground(Color.WHITE);
        contentPanel.add(tripCombo);

        JButton continueButton = createStyledButton("Continue", 150, 330);
        continueButton.setFont(montserratBold.deriveFont(18f));
        continueButton.setBounds(150, 330, 200, 50);
        continueButton.addActionListener(e -> {
            if (validateBookingForm(fromField, toField, dateField)) {
                // Store flight details
                departureCity = fromField.getText().trim();
                destinationCity = toField.getText().trim();
                departureDate = dateField.getText().trim();
                tripType = (String) tripCombo.getSelectedItem();

                switchPanel(bookingPanel, passengerPanel);
            }
        });
        contentPanel.add(continueButton);

        return panel;
    }

    private JPanel createPassengerPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 1250, 720);

        panel.add(createBackgroundPanel(600));
        panel.add(createFormPanel("Passenger Details", "Step 2 of 2: Passenger Information"));
        panel.add(createHeaderPanel("Passenger Details"));

        JPanel contentPanel = new JPanel(null);
        contentPanel.setBounds(100, 80, 500, 550);
        ((JPanel) panel.getComponent(1)).add(contentPanel);

        JLabel formTitle = new JLabel("Enter Passenger Details", JLabel.CENTER);
        formTitle.setBounds(0, 0, 500, 50);
        formTitle.setFont(montserratBold.deriveFont(26f));
        formTitle.setForeground(primaryColor);
        contentPanel.add(formTitle);

        JLabel firstNameLabel = new JLabel("First Name*:");
        firstNameLabel.setBounds(50, 70, 150, 30);
        firstNameLabel.setFont(montserratBold.deriveFont(16f));
        contentPanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(200, 70, 250, 40);
        firstNameField.setFont(montserratRegular.deriveFont(16f));
        firstNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name*:");
        lastNameLabel.setBounds(50, 130, 150, 30);
        lastNameLabel.setFont(montserratBold.deriveFont(16f));
        contentPanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(200, 130, 250, 40);
        lastNameField.setFont(montserratRegular.deriveFont(16f));
        lastNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPanel.add(lastNameField);

        JLabel nationalityLabel = new JLabel("Nationality*:");
        nationalityLabel.setBounds(50, 190, 150, 30);
        nationalityLabel.setFont(montserratBold.deriveFont(16f));
        contentPanel.add(nationalityLabel);

        JTextField nationalityField = new JTextField();
        nationalityField.setBounds(200, 190, 250, 40);
        nationalityField.setFont(montserratRegular.deriveFont(16f));
        nationalityField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPanel.add(nationalityField);

        JLabel passportLabel = new JLabel("Passport No*:");
        passportLabel.setBounds(50, 250, 150, 30);
        passportLabel.setFont(montserratBold.deriveFont(16f));
        contentPanel.add(passportLabel);

        JTextField passportField = new JTextField();
        passportField.setBounds(200, 250, 250, 40);
        passportField.setFont(montserratRegular.deriveFont(16f));
        passportField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPanel.add(passportField);

        JLabel classLabel = new JLabel("Class*:");
        classLabel.setBounds(50, 310, 150, 30);
        classLabel.setFont(montserratBold.deriveFont(16f));
        contentPanel.add(classLabel);

        JComboBox<String> classCombo = new JComboBox<>(new String[]{"Economy", "Business", "First Class"});
        classCombo.setBounds(200, 310, 250, 40);
        classCombo.setFont(montserratRegular.deriveFont(16f));
        classCombo.setBackground(Color.WHITE);
        contentPanel.add(classCombo);

        JButton confirmButton = createStyledButton("Confirm Booking", 150, 390);
        confirmButton.setFont(montserratBold.deriveFont(18f));
        confirmButton.setBounds(150, 390, 200, 50);
        confirmButton.addActionListener(e -> {
            if (validatePassengerForm(firstNameField, lastNameField, nationalityField, passportField)) {
                // Generate ticket number
                generatedTicketNumber = generateTicketNumber();

                // Save to database
                saveTicketToDB(firstNameField.getText(), lastNameField.getText(),
                        nationalityField.getText(), passportField.getText(),
                        classCombo.getSelectedItem().toString());

                // Show confirmation dialog with download option
                Object[] options = {"Download Ticket", "Return to Dashboard"};
                int choice = JOptionPane.showOptionDialog(this,
                        "Booking confirmed successfully!\nYour ticket number: " + generatedTicketNumber,
                        "Success",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (choice == 0) {
                    downloadTicket(firstNameField.getText(), lastNameField.getText(),
                            nationalityField.getText(), passportField.getText(),
                            classCombo.getSelectedItem().toString(), generatedTicketNumber);
                }
                dispose();
                new Landing();
            }
        });
        contentPanel.add(confirmButton);

        return panel;
    }

    private String generateTicketNumber() {
        // Generate a random 8-digit ticket number with airline prefix
        Random random = new Random();
        int randomNum = 10000000 + random.nextInt(90000000);
        return "AX-" + randomNum;
    }

    private void downloadTicket(String firstName, String lastName, String nationality,
                                String passportNo, String travelClass, String ticketNumber) {
        // Create a simple ticket image
        int width = 600;
        int height = 400;
        BufferedImage ticketImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = ticketImage.createGraphics();

        // Draw background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Draw header
        g2d.setColor(primaryColor);
        g2d.fillRect(0, 0, width, 60);
        g2d.setColor(Color.WHITE);
        g2d.setFont(montserratBold.deriveFont(24f));
        g2d.drawString("AIR AXIAL - BOARDING PASS", 150, 40);

        // Draw ticket content
        g2d.setColor(Color.BLACK);
        g2d.setFont(montserratBold.deriveFont(18f));
        g2d.drawString("Passenger: " + firstName + " " + lastName, 30, 100);
        g2d.drawString("Ticket Number: " + ticketNumber, 30, 140);
        g2d.drawString("Nationality: " + nationality, 30, 180);
        g2d.drawString("Passport: " + passportNo, 30, 220);
        g2d.drawString("Class: " + travelClass, 30, 260);
        g2d.drawString("Departure: " + departureCity + " to " + destinationCity, 30, 300);
        g2d.drawString("Date: " + departureDate, 30, 340);

        // Draw current date
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
        String issueDate = sdf.format(new Date());
        g2d.drawString("Issued on: " + issueDate, 30, 380);

        g2d.dispose();

        // Save the image
        try {
            // Save image file
            File imageOutput = new File("boarding_pass_" + ticketNumber + ".png");
            ImageIO.write(ticketImage, "png", imageOutput);

            // Create and save text file
            File textOutput = new File("boarding_pass_" + ticketNumber + ".txt");
            try (PrintWriter writer = new PrintWriter(textOutput)) {
                writer.println("AIR AXIAL - BOARDING PASS");
                writer.println("=========================");
                writer.println();
                writer.println("Passenger: " + firstName + " " + lastName);
                writer.println("Ticket Number: " + ticketNumber);
                writer.println("Nationality: " + nationality);
                writer.println("Passport: " + passportNo);
                writer.println("Class: " + travelClass);
                writer.println("Departure: " + departureCity + " to " + destinationCity);
                writer.println("Date: " + departureDate);
                writer.println("Issued on: " + issueDate);
                writer.println();
                writer.println("Thank you for choosing AIR AXIAL");
            }

            JOptionPane.showMessageDialog(this,
                    "Ticket downloaded successfully as:\n" +
                            imageOutput.getAbsolutePath() + "\n" +
                            "and\n" + textOutput.getAbsolutePath(),
                    "Download Complete",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error saving ticket: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createHeaderPanel(String title) {
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 0, 1250, 80);
        headerPanel.setBackground(primaryColor);

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setBounds(0, 0, 1250, 80);
        titleLabel.setFont(montserratBold.deriveFont(28f));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        return headerPanel;
    }

    private JPanel createBackgroundPanel(int width) {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setBounds(0, 0, width, 720);
        return panel;
    }

    private JPanel createFormPanel(String title, String step) {
        JPanel panel = new JPanel(null);
        panel.setBounds(550, 0, 700, 720);
        panel.setBackground(new Color(255, 255, 255, 240));
        panel.setBorder(new LineBorder(new Color(220, 220, 220), 1));

        JLabel stepLabel = new JLabel(step, JLabel.CENTER);
        stepLabel.setBounds(0, 20, 700, 40);
        stepLabel.setFont(montserratBold.deriveFont(20f));
        stepLabel.setForeground(primaryColor);
        panel.add(stepLabel);

        return panel;
    }

    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 60);
        button.setFont(montserratBold.deriveFont(16f));
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(primaryColor.darker(), 1));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(primaryColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(primaryColor);
            }
        });

        return button;
    }

    private boolean validateBookingForm(JTextField fromField, JTextField toField, JTextField dateField) {
        if (fromField.getText().trim().isEmpty()) {
            showError("Please enter departure city", fromField);
            return false;
        }

        if (toField.getText().trim().isEmpty()) {
            showError("Please enter destination city", toField);
            return false;
        }

        if (dateField.getText().trim().isEmpty()) {
            showError("Please enter departure date", dateField);
            return false;
        }

        if (fromField.getText().trim().equalsIgnoreCase(toField.getText().trim())) {
            showError("Departure and destination cannot be the same", fromField);
            return false;
        }

        return true;
    }

    private boolean validatePassengerForm(JTextField firstNameField, JTextField lastNameField,
                                          JTextField nationalityField, JTextField passportField) {
        if (firstNameField.getText().trim().isEmpty()) {
            showError("Please enter first name", firstNameField);
            return false;
        }

        if (lastNameField.getText().trim().isEmpty()) {
            showError("Please enter last name", lastNameField);
            return false;
        }

        if (nationalityField.getText().trim().isEmpty()) {
            showError("Please enter nationality", nationalityField);
            return false;
        }

        if (passportField.getText().trim().isEmpty()) {
            showError("Please enter passport number", passportField);
            return false;
        }

        return true;
    }

    private void showError(String message, JComponent component) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
        component.requestFocus();
    }

    private void switchPanel(JPanel from, JPanel to) {
        from.setVisible(false);
        to.setVisible(true);
    }
}
