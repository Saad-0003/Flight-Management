package PROJECT;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class OurPlans extends JFrame {
    private Color primaryColor = new Color(211, 18, 39);
    private Color secondaryColor = new Color(50, 50, 50);
    private Font montserratBold;
    private Font montserratRegular;
    JButton button1;

    public OurPlans() {
        super("AIR AXIAL - Our Plans");
        setLayout(null);
        setSize(1250, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        button1 = new JButton("Back to dashboard");
        button1.setBounds(480, 630, 260, 35);
        button1.setForeground(Color.WHITE);
        button1.setBackground(new Color(234, 47, 74));
        button1.setFont(new Font("Montserrat", Font.BOLD, 18));
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button1.setVisible(true);
        add(button1);

        
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

        createUI();
        setVisible(true);
    }

    private void createUI() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 1250, 720);
        panel.setBackground(new Color(245, 245, 245));
        add(panel);
        
        

        panel.add(createHeaderPanel("Our Travel Plans"));
        
        
        
        
        button1.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    		dispose();
    		new Landing();}});


        JLabel titleLabel = new JLabel("Choose Your Perfect Flight Experience", JLabel.CENTER);
        titleLabel.setBounds(0, 90, 1250, 40);
        titleLabel.setFont(montserratBold.deriveFont(28f));
        titleLabel.setForeground(new Color(234, 47, 74));
        panel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Select from our range of premium travel options designed for every type of traveler", JLabel.CENTER);
        subtitleLabel.setBounds(0, 130, 1250, 30);
        subtitleLabel.setFont(montserratRegular.deriveFont(16f));
        subtitleLabel.setForeground(secondaryColor);
        panel.add(subtitleLabel);

        JPanel plansContainer = new JPanel(new GridLayout(1, 3, 20, 0));
        plansContainer.setBounds(100, 200, 1050, 400);
        plansContainer.setOpaque(false);
        panel.add(plansContainer);

        plansContainer.add(createPlanCard(
            "ECONOMY CLASS", 
            "Perfect for budget travelers", 
            "$199", 
            new String[]{"Standard legroom", "Complimentary snacks", "20kg baggage", "Free entertainment"}, 
            new Color(240, 248, 255), 
            new Color(100, 149, 237)
        ));

        plansContainer.add(createPlanCard(
            "BUSINESS CLASS", 
            "Premium comfort for professionals", 
            "$499", 
            new String[]{"Extra legroom", "Gourmet meals", "35kg baggage", "Priority boarding", "Lounge access"}, 
            new Color(255, 250, 240), 
            new Color(210, 105, 30)
        ));

        plansContainer.add(createPlanCard(
            "FIRST CLASS", 
            "Ultimate luxury experience", 
            "$999", 
            new String[]{"Private suite", "Gourmet dining", "50kg baggage", "Chauffeur service", "Spa access"}, 
            new Color(255, 240, 245), 
            new Color(147, 112, 219)
        ));

//        //JButton bookButton = createStyledButton("Book Now", 470, 610);
//        bookButton.addActionListener(e -> {
//            dispose();
//            new BookTicket();
//        });
//        panel.add(bookButton);
    }

    private JPanel createPlanCard(String title, String subtitle, String price, String[] features, Color bgColor, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(bgColor);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(montserratBold.deriveFont(22f));
        titleLabel.setForeground(accentColor);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(montserratBold.deriveFont(18f));
        priceLabel.setForeground(primaryColor);
        headerPanel.add(priceLabel, BorderLayout.EAST);
        
        card.add(headerPanel, BorderLayout.NORTH);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(montserratRegular.deriveFont(14f));
        subtitleLabel.setForeground(secondaryColor);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        card.add(subtitleLabel, BorderLayout.CENTER);

        JPanel featuresPanel = new JPanel();
        featuresPanel.setLayout(new BoxLayout(featuresPanel, BoxLayout.Y_AXIS));
        featuresPanel.setOpaque(false);
        featuresPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        for (String feature : features) {
            JPanel featureItem = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            featureItem.setOpaque(false);
            featureItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            JLabel bullet = new JLabel("•");
            bullet.setFont(montserratBold.deriveFont(16f));
            bullet.setForeground(accentColor);

            JLabel featureLabel = new JLabel(feature);
            featureLabel.setFont(montserratRegular.deriveFont(14f));
            featureLabel.setForeground(secondaryColor);

            featureItem.add(bullet);
            featureItem.add(featureLabel);
            featuresPanel.add(featureItem);
        }
        card.add(featuresPanel, BorderLayout.CENTER);

        JButton bookButton = new JButton("BOOK NOW");
        bookButton.setFont(montserratBold.deriveFont(14f));
        bookButton.setBackground(accentColor);
        bookButton.setForeground(Color.WHITE);
        bookButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        bookButton.setFocusPainted(false);
        bookButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bookButton.addActionListener(e -> {
            dispose();
            new BookTicket();
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(bookButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
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
    
}