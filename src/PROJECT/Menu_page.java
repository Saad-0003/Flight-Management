package PROJECT;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Menu_page extends JFrame {
	
	JPanel image1,imagepanel_1,panelforimage;
	BufferedImage new1;
	ImageIcon ii1,image_1;
	JPanel jp1;

    public Menu_page() {
        super("Admin");
        setSize(1250, 720);
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // -------------------- Create layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1250, 720);
        add(layeredPane);
       
        // ------------------- Top red panel
        JPanel topp = new JPanel();
        topp.setLayout(null);
        topp.setBounds(0, 0, 1250, 100);
        topp.setBackground(new Color(234, 47, 74));
        layeredPane.add(topp, Integer.valueOf(1)); // higher layer

        JLabel text = new JLabel("ADMIN MENU");
        text.setFont(new Font("Arial", Font.BOLD, 40));
        text.setBounds(150, 25, 300, 50);
        text.setForeground(Color.WHITE);
        topp.add(text);

        // ----------------- Logo on top left
        
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
			        
		
		
        // ------------------- Buttons (on higher layer)
        Color red = new Color(234, 47, 74);

        JButton btn1 = new JButton("Manage Flights");
        btn1.setBounds(475, 260, 300, 40);
        btn1.setBackground(red);
        btn1.setForeground(Color.WHITE);
        btn1.setFont(new Font("Arial", Font.BOLD, 16));
        btn1.setBorderPainted(false);
        btn1.setFocusPainted(false);
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        layeredPane.add(btn1, Integer.valueOf(1));
        
        
        btn1.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    		dispose();
    		new ManageFlight();}});

        JButton btn2 = new JButton("Manage Customers");
        btn2.setBounds(475, 320, 300, 40);
        btn2.setBackground(red);
        btn2.setForeground(Color.WHITE);
        btn2.setFont(new Font("Arial", Font.BOLD, 16));
        btn2.setBorderPainted(false);
        btn2.setFocusPainted(false);
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        layeredPane.add(btn2, Integer.valueOf(1));
        
btn2.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    		dispose();
    		new ManagePassenger();}});

        JButton btn3 = new JButton("Feedback");
        btn3.setBounds(475, 380, 300, 40);
        btn3.setBackground(red);
        btn3.setForeground(Color.WHITE);
        btn3.setFont(new Font("Arial", Font.BOLD, 16));
        btn3.setBorderPainted(false);
        btn3.setFocusPainted(false);
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        layeredPane.add(btn3, Integer.valueOf(1));
        
        
btn3.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    		dispose();
    		new ViewFeedback();}});


        // ------------------ Back Button
        JButton bckBtn = new JButton("Back");
        bckBtn.setBounds(1060, 610, 120, 35);
        bckBtn.setBackground(new Color(48, 166, 193));
        bckBtn.setForeground(Color.WHITE);
        bckBtn.setFont(new Font("Arial", Font.BOLD, 16));
        bckBtn.setBorderPainted(false);
        bckBtn.setFocusPainted(false);
        bckBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        layeredPane.add(bckBtn, Integer.valueOf(1));

        setVisible(true);
        
        
        imagepanel_1=new JPanel() {
    		protected void paintComponent(Graphics g) {
    		super.paintComponent(g);
    	    image_1=new ImageIcon("D:\\College Work\\OOP JAVA\\OOP PROJECT\\src\\Terminal.jpg");
    		Image new_image=image_1.getImage();
    		g.drawImage(new_image, 0, 0, getWidth(), getHeight(), this);}};	
    		imagepanel_1.setBounds(0, 0, 1250, 720);
    	    imagepanel_1.setLayout(null);
    		add(imagepanel_1);
    		
    		panelforimage=new JPanel();
    		panelforimage.setBounds(0, 0, 1250, 720);
    		panelforimage.setForeground(new Color(255,255,255,250));
    		panelforimage.setOpaque(true);
    		add(panelforimage);   
    		
    		
    		
//    		jp1=new JPanel();
//    		jp1.setBounds(0, 0, 1250, 720);
//    		jp1.setForeground(new Color(255,255,255,160));
//    		jp1.setOpaque(true);
//    		add(jp1);
        
bckBtn.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    		dispose();
    		new Landing();}});}
    
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;
        private float opacity;

        public BackgroundPanel(String path, float opacity) {
            this.opacity = opacity;
            try {
                BufferedImage img = ImageIO.read(new File(path));
                backgroundImage = img.getScaledInstance(1250, 720, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                g2d.drawImage(backgroundImage, 0, 0, this);
            }
        }
    }
}
