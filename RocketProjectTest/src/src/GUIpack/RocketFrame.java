//
/*
 * THIS IS A BROKEN CLASS. DOES NOT WORK. #for saving purposes only.
 */






//package src.GUIpack;
//
//import java.awt.BorderLayout;
//import java.awt.CardLayout;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//
//public class RocketFrame extends JFrame {
//
//	
//
//	public WPanel wpanel = new WPanel();
//	public CPanel cpanel = new CPanel();
//	public LaunchPanel mpanel = new LaunchPanel();
//	//public CardLayout cards = new CardLayout();
//	public String filename = "background.jpeg";
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	public RocketFrame() {
//		super();
//		try {
//			UIManager
//					.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		setTitle("Water Bottle Rocket Launch");
//		
//		setSize(1000,1000);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setVisible(true);
//		
//		setSize(1000, 1000);
//		
//		setVisible(true);
//		//setLayout(cards);
//		add(SliderPanel.initBack(new JPanel(), filename));
//		add(wpanel);
//		repaint();
//		
//		
//	}
//
////	public void paint(Graphics g) {
////		
////		BufferedImage image2 = null;
////		try {
////			image2 = ImageIO.read(new File("frameBack.jpg"));
////		} catch (IOException e) {
////
////			e.printStackTrace();
////		}
////		int type = image2.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image2
////				.getType();
////		image2 = SliderPanel.resizeImage(image2, type, 1000, 1000);
////		final BufferedImage image = image2;
////
////		g.drawImage(image, 0, 0, null);
////
////	}
//
//	public void mInit() {
//		remove(wpanel);
//		add(cpanel, BorderLayout.NORTH);
//		add(mpanel);
//		
//		repaint();
//	}
//
//	public void restart() {
//		remove(mpanel);
//		remove(cpanel);
//
//		add(wpanel);
//		repaint();
//	}
//
//	public static void main(String[] args) {
//
//		RocketFrame r = new RocketFrame();
//
//	}
//
//	public class WPanel extends WelcomePanel {
//		public WPanel() {
//			super();
//			setSize(getWidth(), getHeight());
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			super.actionPerformed(e);
//			System.out.println(e.getActionCommand());
//			if (e.getActionCommand().equals("launch")) {
//				System.out.println(e.getActionCommand());
//
//				mInit();
//				repaint();
//			} else {
//
//			}
//
//		}
//
//	}
//
//	public class CPanel extends ControlPanel {
//		public CPanel() {
//			super();
//			setSize(getWidth(), getHeight() / 5);
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			
//			restart();
//			 System.out.println("restart");
//			 try {
//			 Thread.sleep(1000);
//			 } catch (InterruptedException e1) {
//			 // TODO Auto-generated catch block
//			 e1.printStackTrace();
//			 }
//
//		}
//
//	}
//
//	
//}