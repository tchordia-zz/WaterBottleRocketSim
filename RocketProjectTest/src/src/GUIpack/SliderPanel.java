package src.GUIpack;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderPanel extends JPanel implements ChangeListener {

	FlowLayout experimentLayout = new FlowLayout();

	public static JSlider massRocket;
	public static JSlider massWater;
	public static JSlider volumeBottle;
	public static JSlider airPressure;
	public static JSlider dragC;
	public static JSlider bottleRadius;
	public static JSlider nozzleRadius;

	public SliderPanel() {
		try {
			UIManager
					.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel group1 = initBack(new JPanel());
		GridLayout flow = new GridLayout();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		massRocket = new JSlider(JSlider.HORIZONTAL, 0, 10, 7); // divide by 100

		massRocket.setMajorTickSpacing(1);

		massRocket.setMinorTickSpacing(1);

		massRocket.setPaintTicks(true);

		massRocket.setPaintLabels(true);

		massRocket.setSnapToTicks(true);

		massRocket.addChangeListener(this);
		add(new JLabel("Mass of the Rocket", JLabel.CENTER));
		group1.add(massRocket);

		massWater = new JSlider(JSlider.HORIZONTAL, 0, 10, 6);

		massWater.setMajorTickSpacing(1);

		massWater.setMinorTickSpacing(1);

		massWater.setPaintTicks(true);

		massWater.setPaintLabels(true);

		massWater.setSnapToTicks(true);

		massWater.addChangeListener(this);
		
		group1.add(massWater);
		volumeBottle = new JSlider(JSlider.HORIZONTAL, 0, 100, 20); // divide
																	// value by
																	// 10000

		volumeBottle.setMajorTickSpacing(10);

		volumeBottle.setMinorTickSpacing(1);

		volumeBottle.setPaintTicks(true);

		volumeBottle.setPaintLabels(true);

		volumeBottle.setSnapToTicks(true);
		volumeBottle.addChangeListener(this);
		group1.add(volumeBottle);
		airPressure = new JSlider(JSlider.HORIZONTAL, 200000, 1000000, 260000);

		airPressure.setMajorTickSpacing(100000);

		airPressure.setMinorTickSpacing(10000);

		airPressure.setPaintTicks(true);

		airPressure.setPaintLabels(true);

		airPressure.setSnapToTicks(true);
		airPressure.addChangeListener(this);
		group1.add(airPressure);
		dragC = new JSlider(JSlider.HORIZONTAL, 0, 5, 1);

		dragC.setMajorTickSpacing(1);

		dragC.setMinorTickSpacing(1);

		dragC.setPaintTicks(true);

		dragC.setPaintLabels(true);

		dragC.setSnapToTicks(true);
		dragC.addChangeListener(this);
		group1.add(dragC);
		bottleRadius = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);// divide by
																	// 100

		bottleRadius.setMajorTickSpacing(1);

		bottleRadius.setMinorTickSpacing(1);

		bottleRadius.setPaintTicks(true);

		bottleRadius.setPaintLabels(true);

		bottleRadius.setSnapToTicks(true);
		group1.add(bottleRadius);
		bottleRadius.addChangeListener(this);
		nozzleRadius = new JSlider(JSlider.HORIZONTAL, 1, 10, 1); // divide by
																	// 100

		nozzleRadius.setMajorTickSpacing(1);

		nozzleRadius.setMinorTickSpacing(1);

		nozzleRadius.setPaintTicks(true);

		nozzleRadius.setPaintLabels(true);

		nozzleRadius.setSnapToTicks(true);

		nozzleRadius.addChangeListener(this);

		group1.add(nozzleRadius);
		
		

		

		

		// group2.add(fourthButton);
		// group2.add(fifthButton);
		// group2.add(sixthButton);
		add(group1);

	}

	private JPanel initBack(JPanel pane)
	{
		BufferedImage image2 = null;
		try {
			image2 = ImageIO
					.read(new File("background.jpeg"));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		int type = image2.getType() == 0? BufferedImage.TYPE_INT_ARGB : image2.getType();
		image2 = resizeImage(image2, type, 500, 500);
		final BufferedImage image = image2;
		
		pane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
			
			
		};
		return pane;
	}
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("Button Panel Example");
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SliderPanel());
		frame.setVisible(true);

	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type, int iw, int ih){
		BufferedImage resizedImage = new BufferedImage(iw, ih, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, iw, ih, null);
		g.dispose();
	 
		return resizedImage;
	    }
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("massRocket: "
				+ (massRocket.getValue() / (100 + 0.0)));
		System.out.println("massWater: " + massWater.getValue() / (100 + 0.0));
		System.out.println("volumeBottle: " + volumeBottle.getValue()
				/ (1000 + 0.0));
		System.out.println("airPressure: " + airPressure.getValue());
		System.out.println("dragC: " + dragC.getValue());
		System.out.println("bottleRadius: " + bottleRadius.getValue()
				/ (100 + 0.0));
		System.out.println("nozzleRadius: " + nozzleRadius.getValue()
				/ (100 + 0.0));

	}

}