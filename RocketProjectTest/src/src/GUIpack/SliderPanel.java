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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComponent;

import com.sun.java.swing.Painter;

/**
 * 
 * @author Cameron Yang, Tanmay Chordia
 * 
 * This class creates the Panel that contains the sliders to be used for the simulation.
 *
 */

public class SliderPanel extends JPanel implements ChangeListener {

	FlowLayout experimentLayout = new FlowLayout();

	public static minimalSlider massRocket;  
	public static minimalSlider massWater;
	public static minimalSlider volumeBottle;
	public static minimalSlider airPressure;
	public static minimalSlider dragC;
	public static minimalSlider bottleRadius;
	public static minimalSlider nozzleRadius;
	
    UIDefaults sliderDefaults = new UIDefaults();
    
    private BufferedImage sliderThumb;

	public SliderPanel() {

        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())){
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
		JPanel sliders = new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		massRocket = new minimalSlider (minimalSlider.HORIZONTAL, 0, 10, 7); // divide by 100

		massRocket.setMajorTickSpacing(1);

		massRocket.setMinorTickSpacing(1);

		massRocket.setPaintLabels(true);
		
		massRocket.addChangeListener(this);
		
		JPanel rocketMassPanel = new JPanel();
		rocketMassPanel.setBackground(Color.white);
		rocketMassPanel.add(massRocket);
		rocketMassPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Rocket Mass"));
		

		massWater = new minimalSlider(minimalSlider.HORIZONTAL, 0, 10, 6);

		massWater.setMajorTickSpacing(1);

		massWater.setMinorTickSpacing(1);

		massWater.setPaintLabels(true);

		massWater.addChangeListener(this);

		JPanel waterMassPanel = new JPanel();
		waterMassPanel.setBackground(Color.white);
		waterMassPanel.add(massWater);
		waterMassPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Water Mass"));


		volumeBottle = new minimalSlider(minimalSlider.HORIZONTAL, 0, 100, 20); // divide value by 1000

		volumeBottle.setMajorTickSpacing(10);

		volumeBottle.setPaintLabels(true);

		volumeBottle.addChangeListener(this);

		JPanel bottleVolumePanel = new JPanel();
		bottleVolumePanel.setBackground(Color.white);
		bottleVolumePanel.add(volumeBottle);
		bottleVolumePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Bottle Volume"));


		airPressure = new minimalSlider(minimalSlider.HORIZONTAL, 20, 100, 26);

		airPressure.setMajorTickSpacing(10);

		airPressure.setMinorTickSpacing(1);

		airPressure.setPaintLabels(true);

		airPressure.addChangeListener(this);

		JPanel airPressurePanel = new JPanel();
		airPressurePanel.setBackground(Color.white);
		airPressurePanel.add(airPressure);
		airPressurePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Air Pressure in ten-thousands"));


		dragC = new minimalSlider(minimalSlider.HORIZONTAL, 0, 5, 1);

		dragC.setMajorTickSpacing(1);

		dragC.setMinorTickSpacing(1);

		dragC.setPaintLabels(true);

		dragC.addChangeListener(this);

		JPanel dragCoefficientPanel = new JPanel();

		dragCoefficientPanel.setBackground(Color.white);
		dragCoefficientPanel.add(dragC);
		dragCoefficientPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Drag Coefficient"));


		bottleRadius = new minimalSlider(minimalSlider.HORIZONTAL, 0, 10, 5);// divide by 100
		
		bottleRadius.setMajorTickSpacing(1);

		bottleRadius.setMinorTickSpacing(1);

		bottleRadius.setPaintLabels(true);
		
		bottleRadius.addChangeListener(this);
		
		JPanel bottleRadiusPanel = new JPanel();
		bottleRadiusPanel.setBackground(Color.white);
		bottleRadiusPanel.add(bottleRadius);
		bottleRadiusPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Bottle Radius"));
		
		
		nozzleRadius = new minimalSlider (minimalSlider.HORIZONTAL, 1, 10, 1); // divide by
																			  // 100
		nozzleRadius.setMajorTickSpacing(1);

		nozzleRadius.setMinorTickSpacing(1);

		nozzleRadius.setPaintLabels(true);

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
				.addComponent(bottleVolumePanel));

		sliderLayout.setHorizontalGroup(horizontalLayout);
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rocketMassPanel).addComponent(waterMassPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(airPressurePanel).addComponent(dragCoefficientPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(bottleVolumePanel).addComponent(bottleRadiusPanel));

		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(nozzleRadiusPanel));
		
		sliderLayout.setVerticalGroup(verticalLayout);
		sliders.setBackground(Color.white);
		setBackground(Color.white);
		tabbedPane.add("sliders",sliders);
		JPanel testPanel = new JPanel();
		testPanel.setBackground(Color.white);
		tabbedPane.add("exact values", testPanel);
		tabbedPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(tabbedPane);
	}
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Button Panel Example");
		frame.setSize(new Dimension(600,600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SliderPanel(), BorderLayout.PAGE_START);
		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
	}

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