package PROJECT;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchFlight extends JFrame {
    private Color primaryColor = new Color(211, 18, 39);
    private Color secondaryColor = new Color(50, 50, 50);
    private DefaultTableModel flightsTableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    public searchFlight() {
        super("AIR AXIAL - Search Flights");
        setLayout(null);
        setSize(1250, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createFlightsPanel();
        setVisible(true);
    }

    private void createFlightsPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 1250, 720);
        panel.setBackground(new Color(245, 245, 245));

        JLabel titleLabel = new JLabel("Flight Schedule & Availability", JLabel.CENTER);
        titleLabel.setBounds(0, 30, 1250, 40);  
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(primaryColor);
        panel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Search and filter our current flight offerings", JLabel.CENTER);
        subtitleLabel.setBounds(0, 70, 1250, 30);  
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitleLabel.setForeground(secondaryColor);
        panel.add(subtitleLabel);

 
        JLabel offerLabel = new JLabel("SPECIAL OFFER: Dubai to Karachi flights now with PKR 32,000 discount!", JLabel.CENTER);
        offerLabel.setBounds(0, 110, 1250, 30);  
        offerLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        offerLabel.setForeground(new Color(0, 100, 0));
        offerLabel.setBackground(new Color(220, 255, 220));
        offerLabel.setOpaque(true);
        panel.add(offerLabel);


        JPanel searchPanel = new JPanel(null);
        searchPanel.setBounds(100, 150, 1050, 60);  
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        panel.add(searchPanel);

        JLabel searchLabel = new JLabel("Search Flights:");    
        searchLabel.setBounds(5, 10, 120, 30);
        searchLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchPanel.add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(120, 10, 400, 30);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(540, 10, 100, 30);
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchButton.setBackground(primaryColor);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(e -> filterFlights(searchField.getText()));
        searchPanel.add(searchButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(650, 10, 100, 30);
        clearButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        clearButton.setBackground(secondaryColor);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> {
            searchField.setText("");
            filterFlights("");
        });
        searchPanel.add(clearButton);


        JLabel filterLabel = new JLabel("Filter by:");
        filterLabel.setBounds(780, 10, 60, 30);
        filterLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchPanel.add(filterLabel);

        JComboBox<String> filterCombo = new JComboBox<>(new String[]{"All", "Available", "From", "To", "Departure Time"});
        filterCombo.setBounds(850, 10, 150, 30);
        filterCombo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        filterCombo.addActionListener(e -> {
            String filter = (String)filterCombo.getSelectedItem();
            if (filter != null && !filter.equals("All")) {
                searchField.setText("");
                filterFlightsBy(filter);
            }
        });
        searchPanel.add(filterCombo);

        String[] columns = {"Flight No", "From", "To", "Departure", "Arrival", "Duration", "Price (PKR)", "Status"};
        Object[][] data = {
            {"PK201", "Karachi", "Lahore", "10:00 AM", "12:00 PM", "2h", "25,000", "Available"},
            {"PK102", "Lahore", "Karachi", "5:00 PM", "7:00 PM", "2h", "25,000", "Available"},
            {"PK208", "Islamabad", "Karachi", "8:00 AM", "10:00 AM", "2h", "28,000", "Available"},
            {"PK305", "Islamabad", "Lahore", "2:00 PM", "3:00 PM", "1h", "18,000", "Available"},
            {"PK410", "Peshawar", "Quetta", "11:00 AM", "1:00 PM", "2h", "30,000", "Available"},
            
            {"PK150", "Karachi", "Dubai", "11:00 PM", "1:00 AM", "2h", "85,000", "Available"},
            {"PK151", "Dubai", "Karachi", "3:00 AM", "5:00 AM", "2h", "53,000", "Available"}, 
            {"PK302", "Islamabad", "Jeddah", "3:00 PM", "6:00 PM", "3h", "95,000", "Available"},
            {"PK401", "Lahore", "Abu Dhabi", "1:00 AM", "4:00 AM", "3h", "90,000", "Available"},
            {"PK502", "Karachi", "Riyadh", "9:00 AM", "11:30 AM", "2h 30m", "88,000", "Available"},
            
            {"PK501", "Karachi", "London", "9:00 AM", "2:00 PM", "7h", "250,000", "Available"},
            {"PK601", "Islamabad", "Paris", "10:00 PM", "4:00 AM", "6h", "220,000", "Available"},
            {"PK602", "Lahore", "Frankfurt", "1:00 AM", "6:30 AM", "5h 30m", "230,000", "Available"},
            {"PK701", "Karachi", "Istanbul", "2:00 PM", "6:00 PM", "4h", "150,000", "Available"},
            {"PK702", "Islamabad", "Rome", "4:00 AM", "9:00 AM", "5h", "210,000", "Available"},
            
            {"PK801", "Karachi", "New York", "11:00 PM", "6:00 AM", "13h", "350,000", "Available"},
            {"PK802", "Islamabad", "Toronto", "10:00 AM", "9:00 PM", "15h", "380,000", "Available"},
            {"PK803", "Lahore", "Chicago", "1:00 AM", "2:00 PM", "15h", "370,000", "Available"},
            {"PK804", "Karachi", "Los Angeles", "3:00 PM", "6:00 AM", "17h", "400,000", "Available"},
            
            {"PK901", "Karachi", "Beijing", "8:00 AM", "4:00 PM", "8h", "180,000", "Available"},
            {"PK902", "Islamabad", "Tokyo", "10:00 PM", "10:00 AM", "12h", "220,000", "Available"},
            {"PK903", "Lahore", "Bangkok", "2:00 AM", "8:00 AM", "6h", "150,000", "Available"},
            {"PK904", "Karachi", "Singapore", "5:00 PM", "11:00 PM", "6h", "160,000", "Available"},
            {"PK905", "Islamabad", "Kuala Lumpur", "12:00 PM", "8:00 PM", "8h", "170,000", "Available"},
            {"PK906", "Lahore", "Seoul", "3:00 AM", "12:00 PM", "9h", "200,000", "Available"},
            
            {"PK1001", "Karachi", "Sydney", "9:00 PM", "5:00 PM", "14h", "320,000", "Available"},
            {"PK1002", "Islamabad", "Melbourne", "11:00 PM", "7:00 PM", "16h", "330,000", "Available"},
            
            {"PK1101", "Karachi", "Cairo", "6:00 AM", "9:00 AM", "3h", "120,000", "Available"},
            {"PK1102", "Islamabad", "Nairobi", "2:00 PM", "6:00 PM", "4h", "140,000", "Available"},
            
            {"PK1201", "Karachi", "Moscow", "4:00 PM", "9:00 PM", "5h", "190,000", "Available"},
            {"PK1202", "Islamabad", "Madrid", "1:00 AM", "7:00 AM", "6h", "210,000", "Available"},
            {"PK1203", "Lahore", "Amsterdam", "3:00 PM", "8:00 PM", "5h", "220,000", "Available"},
            {"PK1204", "Karachi", "Zurich", "6:00 AM", "11:00 AM", "5h", "230,000", "Available"},
            {"PK1205", "Islamabad", "Brussels", "9:00 PM", "2:00 AM", "5h", "210,000", "Available"},
            {"PK1206", "Lahore", "Vienna", "11:00 AM", "4:00 PM", "5h", "215,000", "Available"},
            {"PK1207", "Karachi", "Stockholm", "2:00 PM", "7:00 PM", "5h", "225,000", "Available"},
            {"PK1208", "Islamabad", "Copenhagen", "5:00 AM", "10:00 AM", "5h", "220,000", "Available"},
            {"PK1209", "Lahore", "Oslo", "8:00 PM", "1:00 AM", "5h", "230,000", "Available"},
            {"PK1210", "Karachi", "Helsinki", "10:00 AM", "3:00 PM", "5h", "235,000", "Available"},
            {"PK1211", "Islamabad", "Dublin", "1:00 PM", "6:00 PM", "5h", "210,000", "Available"},
            {"PK1212", "Lahore", "Athens", "4:00 AM", "9:00 AM", "5h", "200,000", "Available"},
            {"PK1213", "Karachi", "Lisbon", "7:00 PM", "12:00 AM", "5h", "210,000", "Available"},
            {"PK1214", "Islamabad", "Prague", "9:00 AM", "2:00 PM", "5h", "205,000", "Available"},
            {"PK1215", "Lahore", "Budapest", "12:00 PM", "5:00 PM", "5h", "200,000", "Available"},
            {"PK1216", "Karachi", "Warsaw", "3:00 AM", "8:00 AM", "5h", "195,000", "Available"},
            {"PK1217", "Islamabad", "Bucharest", "6:00 PM", "11:00 PM", "5h", "190,000", "Available"},
            {"PK1218", "Lahore", "Sofia", "8:00 AM", "1:00 PM", "5h", "185,000", "Available"},
            {"PK1219", "Karachi", "Belgrade", "11:00 PM", "4:00 AM", "5h", "180,000", "Available"}
        };

        flightsTableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        JTable flightsTable = new JTable(flightsTableModel);
        sorter = new TableRowSorter<>(flightsTableModel);
        flightsTable.setRowSorter(sorter);

        flightsTable.setFillsViewportHeight(true);
        flightsTable.setRowHeight(40);
        flightsTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        flightsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        flightsTable.setGridColor(new Color(220, 220, 220));
        flightsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flightsTable.setShowGrid(false);
        flightsTable.setIntercellSpacing(new Dimension(0, 0));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < flightsTable.getColumnCount(); i++) {
            flightsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        flightsTable.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, 
                        hasFocus, row, column);
                if (value.equals("Available")) {
                    c.setForeground(new Color(0, 128, 0)); 
                    setFont(getFont().deriveFont(Font.BOLD));
                } else {
                    c.setForeground(Color.RED);
                }
                setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(flightsTable);
        tableScrollPane.setBounds(150, 230, 950, 350);  
        tableScrollPane.setBorder(new LineBorder(new Color(220, 220, 220), 1));
        panel.add(tableScrollPane);

        JButton backButton = new JButton("Back");
        backButton.setBounds(30, 600, 100, 40);  
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(secondaryColor);
        backButton.setForeground(Color.WHITE);
        panel.add(backButton);

        add(panel);
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Landing();
            }
        });
    }

    private void filterFlights(String searchText) {
        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            } catch (java.util.regex.PatternSyntaxException e) {
            }
        }
    }

    private void filterFlightsBy(String filter) {
        switch (filter) {
            case "Available":
                sorter.setRowFilter(RowFilter.regexFilter("Available", 7));
                break;
            case "From":
                sorter.setRowFilter(RowFilter.regexFilter("Karachi|Islamabad|Lahore|Peshawar|Quetta", 1));
                break;
            case "To":
                sorter.setRowFilter(RowFilter.regexFilter("Lahore|Dubai|Karachi|Istanbul|London|Jeddah|Abu Dhabi|Riyadh|Paris|Frankfurt|Rome|New York|Toronto|Chicago|Los Angeles|Beijing|Tokyo|Bangkok|Singapore|Kuala Lumpur|Seoul|Sydney|Melbourne|Cairo|Nairobi|Moscow|Madrid|Amsterdam|Zurich|Brussels|Vienna|Stockholm|Copenhagen|Oslo|Helsinki|Dublin|Athens|Lisbon|Prague|Budapest|Warsaw|Bucharest|Sofia|Belgrade", 2));
                break;
            case "Departure Time":
                sorter.setRowFilter(RowFilter.regexFilter("AM", 3));
                break;
        }
    }
}