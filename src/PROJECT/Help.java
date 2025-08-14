package PROJECT;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Help extends JFrame {
	
	JPanel Panel1,Panel2,Panel3,Panel4,Panel5,Panel6,Panel7,Panel8;
	JLabel j_label1,j_label2,j_label3,j_label4,j_label5,j_label6,j_label7,j_label8,j_label9,j_label10;
	JButton j_button;
	JPanel image_1,image_2,image_3,image_4,image_5,image_6;
	BufferedImage bimage1,bimage2,bimage3,bimage4,bimage5,bimage6,bimage7;
	JTextArea j_text_area;
    JButton JB_1;
	public Help() {
		super();
		setLayout(null);
		setSize(1250,720);
		setLocation(10,0);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JB_1=new JButton("Back");
		JB_1.setBounds(1060, 620, 140, 40);
		JB_1.setForeground(Color.WHITE);
		JB_1.setBackground(new Color(234,47,74));
		JB_1.setFont(new Font("Montserrat",Font.BOLD,20));
		JB_1.setBorderPainted(false);
		JB_1.setVisible(true);
		JB_1.setFocusPainted(false);
		JB_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(JB_1);
		
       JB_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			dispose();
			new Landing();}});
		
		
		j_label3=new JLabel("HELP");
		j_label3.setBounds(60, 180, 300, 45);
		j_label3.setForeground(new Color(24,50,82));
		j_label3.setFont(new Font("Montserrat",Font.BOLD,40));
		j_label3.setVisible(true);
		add(j_label3);
		
		Panel3=new JPanel();
		Panel3.setBounds(60,230,100,2);
		Panel3.setBackground(new Color(24,50,82));
		Panel3.setLayout(null);
		Panel3.setVisible(true);
		add(Panel3);
		
		j_label4=new JLabel("Contact Us");
		j_label4.setBounds(60, 260, 300, 45);
		j_label4.setForeground(new Color(24,50,82));
		j_label4.setFont(new Font("Montserrat",Font.PLAIN,30));
		j_label4.setVisible(true);
		add(j_label4);
		
		Panel4=new JPanel();
		Panel4.setBounds(60,305,140,2);
		Panel4.setBackground(new Color(24,50,82));
		Panel4.setLayout(null);
		Panel4.setVisible(true);
		add(Panel4);
		
		
		 image_3 = new JPanel() {
			    protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        try {
			        	bimage2 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\phone.png"));
			            g.drawImage(bimage2, 0, 0, getWidth(), getHeight(), this);
			        } catch (IOException e) {
			            e.printStackTrace();
			        }}};
			        image_3.setOpaque(false); 
			        image_3.setBounds(710, 35, 100, 100);
			        image_3.setLayout(null);
			        add(image_3);
		
		image_1=new JPanel(){
			
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);try {
					bimage1 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\AAWHITE.png"));
		            g.drawImage(bimage1, 0, 0, getWidth(), getHeight(), this);}
		            catch (IOException e) {
			            e.printStackTrace();
			        }}}; 
			        image_1.setOpaque(false); 
			        image_1.setBounds(28, -7, 150, 200);
			        image_1.setLayout(null);
			        add(image_1);
			        
			        image_2 = new JPanel() {
					    protected void paintComponent(Graphics g) {
					        super.paintComponent(g);
					        try {
					        	bimage2 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\phone.png"));
					            g.drawImage(bimage2, 0, 0, getWidth(), getHeight(), this);
					        } catch (IOException e) {
					            e.printStackTrace();
					        }}};
					        image_2.setOpaque(false); 
					        image_2.setBounds(710, 35, 100, 100);
					        image_2.setLayout(null);
					        add(image_2);	        
	
			        

			        Panel1=new JPanel();
					Panel1.setBounds(0,30,1250,100);
					Panel1.setBackground(new Color(234,47,74));
					Panel1.setLayout(null);
					Panel1.setVisible(true);
					add(Panel1);
					
					j_label1=new JLabel("AIR AXEL");
					j_label1.setBounds(150, 24, 400, 60);
					j_label1.setForeground(Color.WHITE);
					j_label1.setFont(new Font("Montserrat",Font.BOLD,40));
					j_label1.setVisible(true);
					Panel1.add(j_label1);
					
					j_label2=new JLabel("Contact Us:   081-111-000-111");
					j_label2.setBounds(790, 24, 400, 60);
					j_label2.setForeground(Color.WHITE);
					j_label2.setFont(new Font("Montserrat",Font.BOLD,20));
					j_label2.setVisible(true);
					Panel1.add(j_label2);
					
					Panel2=new JPanel();
					Panel2.setBounds(850,75,300,2);
					Panel2.setBackground(new Color(255,255,255));
					Panel2.setLayout(null);
					Panel2.setVisible(true);
					Panel1.add(Panel2);
					
		
					image_3 = new JPanel() {
					    protected void paintComponent(Graphics g) {
					        super.paintComponent(g);
					        try {
					        	bimage3 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\bell.png"));
					            g.drawImage(bimage3, 0, 0, getWidth(), getHeight(), this);
					        } catch (IOException e) {
					            e.printStackTrace();
					        }}};
					        image_3.setOpaque(false); 
					        image_3.setBounds(80, 350, 50, 50);
					        image_3.setLayout(null);
					        add(image_3);		
					
					        j_label4=new JLabel("SALES OFFICE");
							j_label4.setBounds(150, 360, 200, 50);
							j_label4.setForeground(new Color(24,50,82));
							j_label4.setFont(new Font("Montserrat",Font.BOLD,20));
							j_label4.setVisible(true);
							add(j_label4);   
							
							
							image_4 = new JPanel() {
							    protected void paintComponent(Graphics g) {
							        super.paintComponent(g);
							        try {
							        	bimage4 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\mail.png"));
							            g.drawImage(bimage4, 0, 0, getWidth(), getHeight(), this);
							        } catch (IOException e) {
							            e.printStackTrace();
							        }}};
							        image_4.setOpaque(false); 
							        image_4.setBounds(480, 360, 45, 45);
							        image_4.setLayout(null);
							        add(image_4);
							        
							        j_label4=new JLabel("EMAIL US");
									j_label4.setBounds(555, 360, 200, 50);
									j_label4.setForeground(new Color(24,50,82));
									j_label4.setFont(new Font("Montserrat",Font.BOLD,20));
									j_label4.setVisible(true);
									add(j_label4);
									
									
									image_5 = new JPanel() {
									    protected void paintComponent(Graphics g) {
									        super.paintComponent(g);
									        try {
									        	bimage5 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\message.png"));
									            g.drawImage(bimage5, 0, 0, getWidth(), getHeight(), this);
									        } catch (IOException e) {
									            e.printStackTrace();
									        }}};
									        image_5.setOpaque(false); 
									        image_5.setBounds(830, 360, 45, 45);
									        image_5.setLayout(null);
									        add(image_5);
									        
									        
									        
									        j_label5=new JLabel("CHAT/WHATSAPP");
											j_label5.setBounds(900, 360, 200, 50);
											j_label5.setForeground(new Color(24,50,82));
											j_label5.setFont(new Font("Montserrat",Font.BOLD,20));
											j_label5.setVisible(true);
											add(j_label5);
											
											
											j_label6=new JLabel("• 11-E, Egerton Road, Lahore");
											j_label6.setBounds(70, 410, 300, 50);
											j_label6.setForeground(new Color(24,50,82));
											j_label6.setFont(new Font("Montserrat",Font.BOLD,20));
											j_label6.setVisible(true);
											add(j_label6);
											
											j_label7=new JLabel("• airaxal@gmail.com");
											j_label7.setBounds(500, 410, 300, 50);
											j_label7.setForeground(new Color(24,50,82));
											j_label7.setFont(new Font("Montserrat",Font.BOLD,20));
											j_label7.setVisible(true);
											add(j_label7);
											
											
											j_label8=new JLabel("• 042-36301854");
											j_label8.setBounds(890, 410, 300, 50);
											j_label8.setForeground(new Color(24,50,82));
											j_label8.setFont(new Font("Montserrat",Font.BOLD,20));
											j_label8.setVisible(true);
											add(j_label8);
											
											
											Panel5=new JPanel();
											Panel5.setBounds(170, 410,170,2);
											Panel5.setBackground(new Color(234,47,74));
											Panel5.setLayout(null);
											Panel5.setVisible(true);
											add(Panel5);
											
											Panel6=new JPanel();
											Panel6.setBounds(570, 410,170,2);
											Panel6.setBackground(new Color(234,47,74));
											Panel6.setLayout(null);
											Panel6.setVisible(true);
											add(Panel6);
											
											Panel7=new JPanel();
											Panel7.setBounds(945, 410,170,2);
											Panel7.setBackground(new Color(234,47,74));
											Panel7.setLayout(null);
											Panel7.setVisible(true);
											add(Panel7);
											
											
											image_6 = new JPanel() {
											    protected void paintComponent(Graphics g) {
											        super.paintComponent(g);
											        try {
											        	bimage5 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\search.png"));
											            g.drawImage(bimage5, 0, 0, getWidth(), getHeight(), this);
											        } catch (IOException e) {
											            e.printStackTrace();
											        }}};
											        image_6.setOpaque(false); 
											        image_6.setBounds(60, 500, 45, 45);
											        image_6.setLayout(null);
											        add(image_6);
											
											
											j_label9=new JLabel("TOP FAQ");
											j_label9.setBounds(120, 490, 300, 50);
											j_label9.setForeground(new Color(24,50,82));
											j_label9.setFont(new Font("Montserrat",Font.PLAIN,40));
											j_label9.setVisible(true);
											add(j_label9);
											
											Panel8=new JPanel();
											Panel8.setBounds(130,540,140,2);
											Panel8.setBackground(new Color(24,50,82));
											Panel8.setLayout(null);
											Panel8.setVisible(true);
											add(Panel8);
											
											
											j_text_area=new JTextArea("Q. Fare types\r\n"
													+ "Q. Modification\r\n"
													+ "Q. Name change\r\n"
													+ "Q. Infant baggage allowance");
											j_text_area.setBounds(110,560,300,120);
											j_text_area.setFont(new Font("Montserrat",Font.PLAIN,20));
											j_text_area.setForeground(new Color(24,50,82));
											j_text_area.setOpaque(false);
											j_text_area.setEditable(false);
											add(j_text_area);
												
setVisible(true);}}
