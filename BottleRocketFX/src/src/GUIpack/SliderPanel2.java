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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import javax.swing.Painter;

public class SliderPanel2 extends JPanel implements ChangeListener {

	FlowLayout experimentLayout = new FlowLayout();

	public static JSlider massWater;
	public static JSlider airPressure;
	public static JSlider nozzleRadius;
	JPanel sliders = new JPanel();
	public GUI gui = new GUI();
	public LoadPanel load;

	JTabbedPane tabbedPane = new JTabbedPane();
	//public LoadPanel load = new LoadPanel();
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
		
		
		nozzleRadius = customize(new JSlider(JSlider.HORIZONTAL, 1, 10, 5)); // divide by
																// 100
		nozzleRadius.setMajorTickSpacing(1);

		nozzleRadius.setMinorTickSpacing(1);

//		nozzleRadius.setPaintTicks(true);

		nozzleRadius.setPaintLabels(true);

//		nozzleRadius.setSnapToTicks(true);

		nozzleRadius.addChangeListener(this);
		
		nozzleRadius.setToolTipText("Adjusting this changes the size of the water nozle in centimeters.");
		
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
				.addComponent(airPressurePanel)
				.addComponent(nozzleRadiusPanel)
				.addComponent(waterMassPanel));
		
		sliderLayout.setHorizontalGroup(horizontalLayout);
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(waterMassPanel)
				);
		verticalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(airPressurePanel)
				);
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(nozzleRadiusPanel)
				);
		
		sliderLayout.setVerticalGroup(verticalLayout);
		sliders.setBackground(Color.white);
//
//		try {
//          
//          UIManager.setLookAndFeel(new PlasticLookAndFeel());
//      } catch (Exception e) {
//          System.err.println("Can't set look & feel:" + e);
//      }
		
		
		gui.setBackground(Color.white);
		
		setBackground(Color.white);
		tabbedPane.add("sliders",sliders);
		tabbedPane.addChangeListener(this);
		tabbedPane.setName("tabs");
		tabbedPane.add("exact values", gui);
//		tabbedPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
		System.out.println("massWater: " + massWater.getValue() / (100 + 0.0));
		System.out.println("airPressure: " + airPressure.getValue()*10000);
		System.out.println("nozzleRadius: " + nozzleRadius.getValue()
				/ (100 + 0.0));

	}

}