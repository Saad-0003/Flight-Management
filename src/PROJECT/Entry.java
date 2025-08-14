package PROJECT;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Entry extends JFrame{
	
	JPanel panel1,panel2,panel3;
	JPanel imagepanel1,imagepanel2;
	JButton button1;
	JLabel label1,label2,label3;
	ImageIcon image1;
	BufferedImage img;
	
	public Entry() {
		super();
		setLayout(null);
		setSize(1250,720);
		setLocation(10,0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		
		imagepanel2 = new JPanel() {
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        try {
		            img = ImageIO.read(new File("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\AA1.png"));
		            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }}};
		        imagepanel2.setOpaque(false); 
		        imagepanel2.setBounds(60, -150, 400, 600);
		        imagepanel2.setLayout(null);
		        add(imagepanel2);
		        
		
		panel1=new JPanel();
		panel1.setBackground(new Color(0,0, 0, 150));
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 500, 720);
		panel1.setOpaque(true);
		add(panel1);
		panel1.setVisible(true);
		
		
		label1=new JLabel("AIR AXEL");
		label1.setBounds(140, 200, 300, 100);
		label1.setFont(new Font("Montserrat",Font.BOLD,46));
		label1.setForeground(Color.white);
		label1.setVisible(true);
		panel1.add(label1);
		
		label2=new JLabel("\"EVERY SMILE");
		label2.setBounds(80, 350, 400, 100);
		label2.setFont(new Font("Arial",Font.BOLD,46));
		label2.setForeground(Color.white);
		label2.setVisible(true);
		panel1.add(label2);
		
		label3=new JLabel("MATTERS\"");
		label3.setBounds(120, 420, 400, 100);
		label3.setFont(new Font("Arial",Font.BOLD,50));
		label3.setForeground(Color.white);
		label3.setVisible(true);
		panel1.add(label3);
		
		button1=new JButton(" Press here to continue ");
		button1.setBounds(130, 550, 240, 35);
		button1.setForeground(Color.WHITE);
		button1.setFont(new Font("Arial",Font.BOLD,16));
		button1.setBackground(new Color(234,47,74,200));
		button1.setOpaque(true);
		button1.setBorderPainted(false);
		button1.setVisible(true);
		button1.setFocusPainted(false);
		button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel1.add(button1);
		
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Landing();
			}
		});	

		imagepanel1=new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				image1=new ImageIcon("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\plan1.jpg");
				Image I1=image1.getImage();
				g.drawImage(I1, 0, 0, getWidth(), getHeight(), this);}};	
				imagepanel1.setBounds(0, 0, 1250, 720);
		        imagepanel1.setLayout(null);
		        add(imagepanel1);
		        
		        
setVisible(true);}}
