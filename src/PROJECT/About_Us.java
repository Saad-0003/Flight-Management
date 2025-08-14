package PROJECT;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class About_Us extends JFrame {
	
	JFrame f1,f2,f3,f4;
	JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9;
	JPanel aboutus,comfort,truevalue,service;
	JPanel panel_1,panel_2,panel_3,panel_4,ppaa;
	ImageIcon icon1,icon2,icon3,icon4;
	JTextArea jta1,jta2,jta3,jta4;
	JButton Jb_1;
	
	
	public About_Us(){
		super();
		setLayout(null);
		setSize(1250,720);
		setLocation(10,0);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		lb1=new JLabel("Why AirAxel?");
		lb1.setBounds(20,40,280,40);
		lb1.setFont(new Font("Montserrat",Font.BOLD,35));
		lb1.setForeground(new Color(32,15,96));
		lb1.setVisible(true);
		add(lb1);
		
		ppaa=new JPanel();
		ppaa.setBounds(20,90,500,5);
		ppaa.setBackground(new Color(234,47,74));
		add(ppaa);
		
		
		Jb_1=new JButton("Back");
		Jb_1.setBounds(1100, 10, 110, 40);
		Jb_1.setForeground(Color.WHITE);
		Jb_1.setBackground(new Color(234,47,74));
		Jb_1.setFont(new Font("Montserrat",Font.BOLD,20));
		Jb_1.setBorderPainted(false);
		Jb_1.setVisible(true);
		Jb_1.setFocusPainted(false);
		Jb_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(Jb_1);
		
        Jb_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			dispose();
			new Landing();}});
		
		
		aboutus=new JPanel();
		aboutus.setBackground(new Color(0,0,0,0));
		aboutus.setOpaque(true);
		aboutus.setBounds(20, 120, 350, 270);
		aboutus.setLayout(null);
		add(aboutus);
		
		comfort=new JPanel();
		comfort.setBackground(new Color(0,0,0,0));
		comfort.setOpaque(true);
		comfort.setBounds(620, 120, 350, 270);
		comfort.setLayout(null);
		add(comfort);
		
		service=new JPanel();
		service.setBackground(new Color(0,0,0,0));
		service.setOpaque(true);
		service.setBounds(250, 410, 380, 270);
		service.setLayout(null);
		add(service);
		
		truevalue=new JPanel();
		truevalue.setBackground(new Color(0,0,0,0));
		truevalue.setOpaque(true);
		truevalue.setBounds(860, 400, 360, 270);
		truevalue.setLayout(null);
		add(truevalue);
		
		
		panel_1=new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				icon1=new ImageIcon("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\us-01.jpg");
				Image I3=icon1.getImage();
				g.drawImage(I3, 0, 0, getWidth(), getHeight(), this);}};
				panel_1.setVisible(true);
				panel_1.setBounds(20, 120, 350, 270);
				panel_1.setLayout(null);
				add(panel_1);
				
				
				
				lb2=new JLabel("Who we are");
				lb2.setBounds(40,110,250,40);
				lb2.setFont(new Font("Montserrat",Font.BOLD,35));
				lb2.setForeground(Color.white);
				lb2.setVisible(true);
				aboutus.add(lb2);
				
				
				jta1=new JTextArea("Air Axel is a Pakistani airline\n"
						+ " and a joint venture company\n"
						+ " between the Boeing and  \n"
						+ " Lufthansa Group based in\n"
						+ " Pakistan.");
				jta1.setBounds(380, 180, 350, 270); 
		        jta1.setFont(new Font("Calibri",Font.PLAIN,18));
		        jta1.setForeground(Color.BLACK);
		        jta1.setOpaque(false); 
		        jta1.setEditable(false);
			    add(jta1);
						
			   
			   
			   
			            panel_2=new JPanel(){
					    protected void paintComponent(Graphics g){
						super.paintComponent(g);
						icon2=new ImageIcon("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\1comfort.jpg");
						Image I4=icon2.getImage();
						g.drawImage(I4, 0, 0, getWidth(), getHeight(), this);}};
						panel_2.setVisible(true);
						panel_2.setBounds(620, 120, 350, 270);
						panel_2.setLayout(null);
						add(panel_2);
						
						
						lb2=new JLabel("Comfort");
						lb2.setBounds(90,110,250,40);
						lb2.setFont(new Font("Montserrat",Font.BOLD,35));
						lb2.setForeground(Color.white);
						lb2.setVisible(true);
						comfort.add(lb2);
				
				
						jta2=new JTextArea("We offer the largest economy\n"
								+ "class legroom in the sky,\n"
								+ " because our cabin interiors\n"
								+ " are fitted with world\n"
								+ "class comfort seats.");
						jta2.setBounds(1000, 170, 350, 270); 
				        jta2.setFont(new Font("Calibri",Font.PLAIN,18));
				        jta2.setForeground(Color.BLACK);
				        jta2.setOpaque(false); 
				        jta2.setEditable(false);
					   add(jta2);
					   
					   
					   
panel_3=new JPanel(){
protected void paintComponent(Graphics g){
super.paintComponent(g);
icon3=new ImageIcon("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\women.jpg");
Image I5=icon3.getImage();
g.drawImage(I5, 0, 0, getWidth(), getHeight(), this);}};
panel_3.setVisible(true);
panel_3.setBounds(250, 400, 350, 260);
panel_3.setLayout(null);
add(panel_3);	

jta3=new JTextArea(" We Provide High \n"
		+ " reservations, check-in\n"
		+ " baggage handling, special\n"
		+ " needs, and in-flight\n"
		+ " amenities.");
jta3.setBounds(30, 480, 350, 270); 
jta3.setFont(new Font("Calibri",Font.PLAIN,18));
jta3.setForeground(Color.BLACK);
jta3.setOpaque(false); 
jta3.setEditable(false);
add(jta3);

lb3=new JLabel("Service");
lb3.setBounds(100,100,250,40);
lb3.setFont(new Font("Montserrat",Font.BOLD,35));
lb3.setForeground(Color.white);
lb3.setVisible(true);
service.add(lb3);


panel_4=new JPanel(){
protected void paintComponent(Graphics g){
super.paintComponent(g);
icon4=new ImageIcon("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\plan02.jpg");
Image I6=icon4.getImage();
g.drawImage(I6, 0, 0, getWidth(), getHeight(), this);}};
panel_4.setVisible(true);
panel_4.setBounds(860, 400, 360, 270);
panel_4.setLayout(null);
add(panel_4);

jta4=new JTextArea(" Air Axel focuses \n"
		+ " on offering customers\n"
		+ " comfort, reliability,\n"
		+ " and value for money\n"
		+ " air travel.");
jta4.setBounds(680, 470, 350, 270); 
jta4.setFont(new Font("Calibri",Font.PLAIN,18));
jta4.setForeground(Color.BLACK);
jta4.setOpaque(false); 
jta4.setEditable(false);
add(jta4);

lb4=new JLabel("True Value");
lb4.setBounds(90,120,250,40);
lb4.setFont(new Font("Montserrat ",Font.BOLD,35));
lb4.setForeground(Color.white);
lb4.setVisible(true);
truevalue.add(lb4);
		
setVisible(true);}}
