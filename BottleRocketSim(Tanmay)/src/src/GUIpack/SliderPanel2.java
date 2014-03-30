package src.GUIpack;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.java.swing.Painter;

public class SliderPanel2 extends JPanel implements ChangeListener {

	FlowLayout experimentLayout = new FlowLayout();

	public static JSlider massRocket;  
	public static JSlider massWater;
	public static JSlider volumeBottle;
	public static JSlider airPressure;
	public static JSlider dragC;
	public static JSlider bottleRadius;
	public static JSlider nozzleRadius;
	JPanel sliders = new JPanel();
	public GUI gui = new GUI();
    UIDefaults sliderDefaults = new UIDefaults();
    
    private BufferedImage sliderThumb;

	public SliderPanel2() {
//		try {
//			UIManager
//					.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		
        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())){
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        
        try {
        	sliderThumb = ImageIO.read(new File("new slider thumb.png"));
        }
        catch(IOException ex)
        {
        	System.out.println("ya fucked up");
        }
        sliderDefaults.put("Slider.thumbWidth", 21);
        sliderDefaults.put("Slider.thumbHeight", 25);
        sliderDefaults.put("Slider:SliderThumb.backgroundPainter", new Painter<JComponent>() {
            public void paint(Graphics2D g, JComponent c, int w, int h) {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.drawImage(sliderThumb, 0, 0, null);
            }
        });
        sliderDefaults.put("Slider:SliderTrack.backgroundPainter", new Painter<JComponent>() {
            public void paint(Graphics2D g, JComponent c, int w, int h) {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setStroke(new BasicStroke(2f));
                g.setColor(Color.BLACK);
                g.fillRoundRect(0, 8, w-1, 4, 8, 8);
            }
        });
		
		
        
        
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		
		massRocket = customize(new JSlider(JSlider.HORIZONTAL, 0, 10, 7)); // divide by 100

		massRocket.setMajorTickSpacing(1);

		massRocket.setMinorTickSpacing(1);

		massRocket.setPaintLabels(true);
		massRocket.addChangeListener(this);
		
		JPanel rocketMassPanel = new JPanel();
		rocketMassPanel.setBackground(Color.white);
		rocketMassPanel.add(massRocket);
		rocketMassPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Rocket Mass"));
		
		
		massWater = customize(new JSlider(JSlider.HORIZONTAL, 0, 10, 6));

		massWater.setMajorTickSpacing(1);

		massWater.setMinorTickSpacing(1);

//		massWater.setPaintTicks(true);

		massWater.setPaintLabels(true);

//		massWater.setSnapToTicks(true);

		massWater.addChangeListener(this);
		
		JPanel waterMassPanel = new JPanel();
		waterMassPanel.setBackground(Color.white);
		waterMassPanel.add(massWater);
		waterMassPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Water Mass"));
		
		
		volumeBottle = customize(new JSlider(JSlider.HORIZONTAL, 0, 100, 20)); // divide value by 1000

		volumeBottle.setMajorTickSpacing(10);

//		volumeBottle.setMinorTickSpacing(1);

//		volumeBottle.setPaintTicks(true);

		volumeBottle.setPaintLabels(true);

//		volumeBottle.setSnapToTicks(true);
		
		volumeBottle.addChangeListener(this);
		
		JPanel bottleVolumePanel = new JPanel();
		bottleVolumePanel.setBackground(Color.white);
		bottleVolumePanel.add(volumeBottle);
		bottleVolumePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Bottle Volume"));
		

		airPressure = customize (new JSlider(JSlider.HORIZONTAL, 20, 100, 26));

		airPressure.setMajorTickSpacing(10);
		
		airPressure.setMinorTickSpacing(1);

//		airPressure.setPaintTicks(true);

		airPressure.setPaintLabels(true);

//		airPressure.setSnapToTicks(true);
		
		airPressure.addChangeListener(this);
		
//		airPressure.setPreferredSize(new Dimension(airPressure.getPreferredSize().width*2, airPressure.getPreferredSize().height));
		
		JPanel airPressurePanel = new JPanel();
		airPressurePanel.setBackground(Color.white);
		airPressurePanel.add(airPressure);
		airPressurePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Air Pressure in ten-thousands"));
		
		
		dragC = customize(new JSlider(JSlider.HORIZONTAL, 0, 5, 1));

		dragC.setMajorTickSpacing(1);

		dragC.setMinorTickSpacing(1);

//		dragC.setPaintTicks(true);

		dragC.setPaintLabels(true);

//		dragC.setSnapToTicks(true);
		
		dragC.addChangeListener(this);
		
		JPanel dragCoefficientPanel = new JPanel();
		dragCoefficientPanel.setBackground(Color.white);
		dragCoefficientPanel.add(dragC);
		dragCoefficientPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Drag Coefficient"));


		bottleRadius = customize(new JSlider(JSlider.HORIZONTAL, 0, 10, 5));// divide by
																// 100
		bottleRadius.setMajorTickSpacing(1);

		bottleRadius.setMinorTickSpacing(1);

//		bottleRadius.setPaintTicks(true);

		bottleRadius.setPaintLabels(true);

//		bottleRadius.setSnapToTicks(true);
		
		bottleRadius.addChangeListener(this);
		
		JPanel bottleRadiusPanel = new JPanel();
		bottleRadiusPanel.setBackground(Color.white);
		bottleRadiusPanel.add(bottleRadius);
		bottleRadiusPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Bottle Radius"));
		
		
		nozzleRadius = customize(new JSlider(JSlider.HORIZONTAL, 1, 10, 1)); // divide by
																// 100
		nozzleRadius.setMajorTickSpacing(1);

		nozzleRadius.setMinorTickSpacing(1);

//		nozzleRadius.setPaintTicks(true);

		nozzleRadius.setPaintLabels(true);

//		nozzleRadius.setSnapToTicks(true);

		nozzleRadius.addChangeListener(this);
		
		JPanel nozzleRadiusPanel = new JPanel();
		nozzleRadiusPanel.setBackground(Color.white);
		nozzleRadiusPanel.add(nozzleRadius);
		nozzleRadiusPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Nozzle Radius"));
		
		GroupLayout sliderLayout = new GroupLayout(sliders);
		
		sliders.setLayout(sliderLayout);
		
		sliderLayout.setAutoCreateContainerGaps(true);
		sliderLayout.setAutoCreateGaps(true);
		
		GroupLayout.SequentialGroup horizontalLayout = sliderLayout.createSequentialGroup();
		GroupLayout.SequentialGroup verticalLayout = sliderLayout.createSequentialGroup();
		
		horizontalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(rocketMassPanel)
				.addComponent(airPressurePanel)
				.addComponent(bottleRadiusPanel)
				.addComponent(nozzleRadiusPanel));

		horizontalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(waterMassPanel)
				.addComponent(dragCoefficientPanel)
				.addComponent(bottleVolumePanel)
				);

		sliderLayout.setHorizontalGroup(horizontalLayout);
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rocketMassPanel).addComponent(waterMassPanel)
				);
		verticalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(airPressurePanel).addComponent(dragCoefficientPanel)
				);
		verticalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(bottleVolumePanel).addComponent(bottleRadiusPanel)
				);
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(nozzleRadiusPanel)
				);
		
		sliderLayout.setVerticalGroup(verticalLayout);
		sliders.setBackground(Color.white);

		
	
		
		setBackground(Color.white);
		tabbedPane.add("sliders",sliders);
		tabbedPane.addChangeListener(this);
		tabbedPane.setName("tabs");
		tabbedPane.add("exact values", gui);
		tabbedPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(tabbedPane);
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
	
	   private JSlider customize(JSlider slider)
	   {
           slider.putClientProperty("Nimbus.Overrides",sliderDefaults);
           slider.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
		return slider;
	   }
	
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Button Panel Example");
		frame.setSize(new Dimension(600,600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SliderPanel2(), BorderLayout.PAGE_START);
		frame.setBackground(Color.WHITE);
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
		System.out.println("airPressure: " + airPressure.getValue()*10000);
		System.out.println("dragC: " + dragC.getValue());
		System.out.println("bottleRadius: " + bottleRadius.getValue()
				/ (100 + 0.0));
		System.out.println("nozzleRadius: " + nozzleRadius.getValue()
				/ (100 + 0.0));

	}

}