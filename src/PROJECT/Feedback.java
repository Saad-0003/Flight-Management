package PROJECT;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Feedback extends JFrame{
	JButton jb_1,jb_2;
	JPanel PANEL1,PANEL2,PANEL3,PANEL4,imagepanel_1;
	JLabel LABEL1,LABEL2,LABEL3,LABEL4,LABEL5,LABEL6,LABEL7,LABEL8;
	JTextField tf1, tf2,tf3;
	JTextArea textarea1,textarea2;
	ImageIcon image_1;
	private Connection conn;

	public Feedback() {
		super();
		setLayout(null);
		setSize(1250,720);
		setLocation(10,0);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Initialize database connection
		connectToDatabase();

		PANEL1=new JPanel();
		PANEL1.setBounds(0, 40, 1250, 580);
		PANEL1.setBackground(new Color(51,62,73,220));
		PANEL1.setOpaque(true);
		PANEL1.setLayout(null);
		add(PANEL1);

		jb_1=new JButton("Submit");
		jb_1.setBounds(540, 630, 150, 40);
		jb_1.setForeground(Color.WHITE);
		jb_1.setBackground(new Color(51,62,73));
		jb_1.setFont(new Font("Montserrat",Font.BOLD,20));
		jb_1.setBorderPainted(false);
		jb_1.setVisible(true);
		jb_1.setFocusPainted(false);
		jb_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(jb_1);

		jb_2=new JButton("Back");
		jb_2.setBounds(30, 630, 140, 40);
		jb_2.setForeground(Color.WHITE);
		jb_2.setBackground(new Color(51,62,73));
		jb_2.setFont(new Font("Montserrat",Font.BOLD,20));
		jb_2.setBorderPainted(false);
		jb_2.setVisible(true);
		jb_2.setFocusPainted(false);
		jb_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(jb_2);

		jb_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (saveFeedbackToDB()) {
					JOptionPane.showMessageDialog(null,"FeedBack Submitted Successfully");
				}
			}});

		jb_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Landing();
			}});


		LABEL1=new JLabel("FEEDBACK");
		LABEL1.setBounds(60, 40, 200, 40);
		LABEL1.setForeground(Color.white);
		LABEL1.setFont(new Font("Montserrat",Font.BOLD,30));
		PANEL1.add(LABEL1);

		PANEL2=new JPanel();
		PANEL2.setBounds(100,85,160,2);
		PANEL2.setForeground(Color.WHITE);
		PANEL1.add(PANEL2);

		LABEL2=new JLabel("NAME :");
		LABEL2.setBounds(300, 110, 100, 30);
		LABEL2.setForeground(Color.white);
		LABEL2.setFont(new Font("Montserrat",Font.BOLD,20));
		PANEL1.add(LABEL2);

		tf1=new JTextField();
		tf1.setBounds(500,100,500,40);
		tf1.setFont(new Font("Arial",Font.PLAIN,15));
		tf1.setForeground(Color.BLACK);
		tf1.setBorder(BorderFactory.createEmptyBorder());
		PANEL1.add(tf1);


		LABEL3=new JLabel("E-Mail :");
		LABEL3.setBounds(300, 170, 100, 40);
		LABEL3.setForeground(Color.white);
		LABEL3.setFont(new Font("Montserrat",Font.BOLD,20));
		PANEL1.add(LABEL3);

		tf2=new JTextField();
		tf2.setBounds(500,165,500,40);
		tf2.setFont(new Font("Arial",Font.PLAIN,15));
		tf2.setForeground(Color.BLACK);
		tf2.setBorder(BorderFactory.createEmptyBorder());
		PANEL1.add(tf2);

		LABEL4=new JLabel("COMPLAIN TOPIC :");
		LABEL4.setBounds(180, 230, 200, 40);
		LABEL4.setForeground(Color.white);
		LABEL4.setFont(new Font("Montserrat",Font.BOLD,20));
		PANEL1.add(LABEL4);

		tf3=new JTextField();
		tf3.setBounds(500,225,500,40);
		tf3.setFont(new Font("Arial",Font.PLAIN,15));
		tf3.setForeground(Color.BLACK);
		tf3.setBorder(BorderFactory.createEmptyBorder());
		PANEL1.add(tf3);

		LABEL5=new JLabel("FEEDBACK :");
		LABEL5.setBounds(250, 300, 180, 40);
		LABEL5.setForeground(Color.white);
		LABEL5.setFont(new Font("Montserrat",Font.BOLD,20));
		PANEL1.add(LABEL5);

		textarea1=new JTextArea();
		textarea1.setBounds(500,295,500,120);
		textarea1.setFont(new Font("Arial",Font.PLAIN,15));
		textarea1.setForeground(Color.BLACK);
		textarea1.setBorder(BorderFactory.createEmptyBorder());
		textarea1.setLineWrap(true);           // Enables line wrapping
		textarea1.setWrapStyleWord(true);
		PANEL1.add(textarea1);

		LABEL6=new JLabel("SUGGESTIONS :");
		LABEL6.setBounds(210, 430, 180, 40);
		LABEL6.setForeground(Color.white);
		LABEL6.setFont(new Font("Montserrat",Font.BOLD,20));
		PANEL1.add(LABEL6);

		textarea2=new JTextArea();
		textarea2.setBounds(500,440,500,80);
		textarea2.setFont(new Font("Arial",Font.PLAIN,15));
		textarea2.setForeground(Color.BLACK);
		textarea2.setBorder(BorderFactory.createEmptyBorder());
		textarea2.setLineWrap(true);
		textarea2.setWrapStyleWord(true);
		PANEL1.add(textarea2);


		imagepanel_1=new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				image_1=new ImageIcon("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\plan1.jpg");
				Image new_image=image_1.getImage();
				g.drawImage(new_image, 0, 0, getWidth(), getHeight(), this);}};
		imagepanel_1.setBounds(0, 0, 1250, 720);
		imagepanel_1.setLayout(null);
		add(imagepanel_1);

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

	private boolean saveFeedbackToDB() {
		String name = tf1.getText().trim();
		String email = tf2.getText().trim();
		String topic = tf3.getText().trim();
		String feedbackText = textarea1.getText().trim();
		String suggestions = textarea2.getText().trim();

		if (name.isEmpty() || email.isEmpty() || topic.isEmpty() ||
				feedbackText.isEmpty() || suggestions.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill all fields",
					"Validation Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			String sql = "INSERT INTO feedback (name, email, topic, feedback, suggestions) " +
					"VALUES (?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, topic);
			pstmt.setString(4, feedbackText);
			pstmt.setString(5, suggestions);

			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Error saving feedback: " + ex.getMessage(),
					"Database Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
