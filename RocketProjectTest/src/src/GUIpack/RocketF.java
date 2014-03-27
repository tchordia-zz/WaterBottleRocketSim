package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class RocketF extends JFrame {

	public GraphPanel apanel = new GraphPanel(new RocketMath2());
	public JPanel mpanel = new LaunchPanel();
	public WPanel wpanel = new WPanel();
	public CPanel cpanel = new CPanel();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RocketF() {
		super();
		try {
			UIManager
					.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Water Bottle Rocket Launch");
		setSize(1000, 1000);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// setLayout(new CardLayout());
		add(SliderPanel.initBack(new JPanel(), "background.jpeg"));
		add(wpanel);
		
		
	}

//	public void paint(Graphics g) {
//		
//		BufferedImage image2 = null;
//		try {
//			image2 = ImageIO.read(new File("frameBack.jpg"));
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		int type = image2.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image2
//				.getType();
//		image2 = SliderPanel.resizeImage(image2, type, 1000, 1000);
//		final BufferedImage image = image2;
//
//		g.drawImage(image, 0, 0, null);
//
//	}

	public void mInit() {
		remove(wpanel);
		add(cpanel, BorderLayout.NORTH);
		add(mpanel);
		repaint();
	}

	public void restart() {
		remove(mpanel);
		remove(cpanel);

		add(wpanel);
		repaint();
	}

	public static void main(String[] args) {

		RocketF frame = new RocketF();

	}

	public class WPanel extends WelcomePanel {
		public WPanel() {
			super();
			setSize(getWidth(), getHeight());
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			System.out.println(e.getActionCommand());
			if (e.getActionCommand().equals("launch")) {
				System.out.println(e.getActionCommand());

				mInit();

			} else {

			}

		}

	}

	public class CPanel extends ControlPanel {
		public CPanel() {
			super();
			setSize(getWidth(), getHeight() / 5);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			restart();
			// System.out.println("restart");
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }

		}

	}

}