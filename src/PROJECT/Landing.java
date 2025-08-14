package PROJECT;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Landing extends JFrame{
	
	JPanel jp1,jp2,jp3,jp4,jp5;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8;
	JButton jb1,jb2,jb2_2,jb3,jb4,jb5,jb6,jb7,jb8;
	JPanel image1,image2,imag3;
	BufferedImage new1;
	ImageIcon ii1;
	
	
	
	public Landing() {
		super();
		setLayout(null);
		setSize(1250,720);
		setLocation(10,0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		image1=new JPanel(){
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);try {
					new1 = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\AAWHITE.png"));
		            g.drawImage(new1, 0, 0, getWidth(), getHeight(), this);}
		            catch (IOException e) {
			            e.printStackTrace();
			        }}};
			        
			        image1.setOpaque(false); 
			        image1.setBounds(28, 10, 150, 200);
			        image1.setLayout(null);
			        add(image1);
		
		
		jp1=new JPanel();
		jp1.setBounds(0,0,1250,50);
		jp1.setBackground(Color.WHITE);
		jp1.setLayout(null);
		jp1.setVisible(true);
		add(jp1);
		
		jl1=new JLabel("✈ WE FLY : ");
		jl1.setBounds(35, 14, 150, 30);
		jl1.setForeground(Color.DARK_GRAY);
		jl1.setFont(new Font("sans-serif",Font.BOLD,19));
		jl1.setVisible(true);
		jp1.add(jl1);
		
		jb1=new JButton("Flights");
		jb1.setBounds(160,15,110,30);
		jb1.setFont(new Font("Montserrat",Font.BOLD,17));
		jb1.setForeground(Color.white);
		jb1.setBackground(new Color(234,47,74));
		jb1.setBorderPainted(false);
		jb1.setVisible(true);
		jb1.setFocusPainted(false);
		jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp1.add(jb1);
		
		jb2=new JButton(" ADMIN ");
		jb2.setBounds(980,15,110,30);
		jb2.setFont(new Font("Montserrat",Font.BOLD,17));
		jb2.setForeground(new Color(234,47,74));
		jb2.setBackground(Color.white);
		jb2.setBorderPainted(false);
		jb2.setVisible(true);
		jb2.setFocusPainted(false);
		jb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp1.add(jb2);
		
		jb2_2=new JButton("ABOUT US");
		jb2_2.setBounds(1080,15,140,30);
		jb2_2.setFont(new Font("Montserrat",Font.BOLD,17));
		jb2_2.setForeground(new Color(234,47,74));
		jb2_2.setBackground(Color.white);
		jb2_2.setBorderPainted(false);
		jb2_2.setVisible(true);
		jb2_2.setFocusPainted(false);
		jb2_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp1.add(jb2_2);
		
	//--------------------------------------------------------------------------------------------------------------------
		
	
		
		jp2=new JPanel();
		jp2.setBounds(0,30,1250,120);
		jp2.setBackground(new Color(234,47,74));
		jp2.setLayout(null);
		jp2.setVisible(true);
		add(jp2);
		
		jl2=new JLabel("AIR AXEL");
		jl2.setBounds(150, 40, 400, 60);
		jl2.setForeground(Color.WHITE);
		jl2.setFont(new Font("Montserrat",Font.BOLD,40));
		jl2.setVisible(true);
		jp2.add(jl2);
		
		jb3=new JButton("Plans");
		jb3.setBounds(740,60,90,30);
		jb3.setFont(new Font("Montserrat",Font.BOLD,18));
		jb3.setForeground(Color.WHITE);
		jb3.setBackground(new Color(234,47,74));
		jb3.setBorderPainted(false);
		jb3.setVisible(true);
		jb3.setFocusPainted(false);
		jb3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp2.add(jb3);
		
		jb4=new JButton("Book Flight");
		jb4.setBounds(830,60,150,30);
		jb4.setFont(new Font("Montserrat",Font.BOLD,18));
		jb4.setForeground(Color.WHITE);
		jb4.setBackground(new Color(234,47,74));
		jb4.setBorderPainted(false);
		jb4.setVisible(true);
		jb4.setFocusPainted(false);
		jb4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp2.add(jb4);
		
		jb5=new JButton("Feedback");
		jb5.setBounds(980,60,130,30);
		jb5.setFont(new Font("Montserrat",Font.BOLD,18));
		jb5.setForeground(Color.WHITE);
		jb5.setBackground(new Color(234,47,74));
		jb5.setBorderPainted(false);
		jb5.setVisible(true);
		jb5.setFocusPainted(false);
		jb5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp2.add(jb5);
		
		jb6=new JButton("Help");
		jb6.setBounds(1095,60,100,30);
		jb6.setFont(new Font("Montserrat",Font.BOLD,18));
		jb6.setForeground(Color.WHITE);
		jb6.setBackground(new Color(234,47,74));
		jb6.setBorderPainted(false);
		jb6.setVisible(true);
		jb6.setFocusPainted(false);
		jb6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp2.add(jb6);
		
		jb7=new JButton("Search Flight");
		jb7.setBounds(580,60,160,30);
		jb7.setFont(new Font("Montserrat",Font.BOLD,18));
		jb7.setForeground(Color.WHITE);
		jb7.setBackground(new Color(234,47,74));
		jb7.setBorderPainted(false);
		jb7.setVisible(true);
		jb7.setFocusPainted(false);
		jb7.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jp2.add(jb7);
		
		
	//----------------------------------------------------------------------------------------------------------------------
		
		jp3=new JPanel();
		jp3.setBounds(30,120,600,300);
		jp3.setBackground(new Color(255,255,255,200));
		jp3.setOpaque(true);
		jp3.setLayout(null);
		jp3.setVisible(true);
		add(jp3);
		
		
			
			jl3=new JLabel("Weekly Offer:");
			jl3.setBounds(60, 50, 180, 40);
			jl3.setForeground(new Color(32,15,96));
			jl3.setFont(new Font("Montserrat",Font.BOLD,20));
			jl3.setVisible(true);
			jp3.add(jl3);
			
			jl4=new JLabel("Islamabad and Lahore");
			jl4.setBounds(60, 70, 500, 100);
			jl4.setForeground(new Color(32,15,96));
			jl4.setFont(new Font("Montserrat",Font.PLAIN,40));
			jl4.setVisible(true);
			jp3.add(jl4);
			
			jl5=new JLabel("to Dubai");
			jl5.setBounds(60, 120, 500, 100);
			jl5.setForeground(new Color(32,15,96));
			jl5.setFont(new Font("Montserrat",Font.PLAIN,40));
			jl5.setVisible(true);
			jp3.add(jl5);
			
			jl6=new JLabel("One way only in PKR 32,000");
			jl6.setBounds(60, 170, 500, 100);
			jl6.setForeground(new Color(32,15,96));
			jl6.setFont(new Font("Montserrat",Font.PLAIN,20));
			jl6.setVisible(true);
			jp3.add(jl6);
			
			jb8=new JButton("More Details");
			jb8.setBounds(60,250,160,45);
			jb8.setFont(new Font("Montserrat",Font.PLAIN,18));
			jb8.setForeground(Color.WHITE);
			jb8.setBackground(new Color(234,47,74));
			jb8.setBorderPainted(false);
			jb8.setVisible(true);
			jb8.setFocusPainted(false);
			jb8.setCursor(new Cursor(Cursor.HAND_CURSOR));
			jp3.add(jb8);
	
//----------------------------------------------------------------------------------------------------------------------------------
		
		
			jp4=new JPanel();
			jp4.setBounds(120,630,1000,60);
			jp4.setBackground(new Color(234,47,74,140));
			jp4.setLayout(null);
			jp4.setVisible(true);
			jp4.setOpaque(true);
			add(jp4);
			
			jl6=new JLabel("Where Dreams Take Flight");
			jl6.setBounds(270,0, 500, 60);
			jl6.setForeground(new Color(255,255,255));
			jl6.setFont(new Font("Montserrat",Font.BOLD,30));
			jl6.setVisible(true);
			jp4.add(jl6);
			
//------------------------------------------------- IMAGE ----------------------------------------------------------------------------------------------------------
		
		
			image2=new JPanel(){
				protected void paintComponent(Graphics g){
					super.paintComponent(g);
					ii1=new ImageIcon("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\plan3.jpg");
					Image I2=ii1.getImage();
					g.drawImage(I2, 0, 0, getWidth(), getHeight(), this);}};
					image2.setVisible(true);
					image2.setBounds(0, 120, 1250, 600);
					image2.setLayout(null);
					add(image2);
					
//--------------------------------------------- Button Actions ---------------------------------------------------------------------------				
	jb2_2.addActionListener(new ActionListener() {
						
	@Override
	public void actionPerformed(ActionEvent e) {
	dispose();
	new About_Us();}});
	
	
	
	jb6.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		dispose();
		new Help();}});
	

	
   jb5.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		dispose();
		new Feedback();}});
   
   jb4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		dispose();
		new BookTicket();}});
   
   jb3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		dispose();
		new OurPlans();}});
   
   jb1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		dispose();
		new searchFlight();}});
   
   jb7.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		dispose();
		new searchFlight();}});
   
   jb8.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		dispose();
		new searchFlight();}});
   
   
   jb2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		dispose();
		new Amin_Login();}});
		
setVisible(true);}}
