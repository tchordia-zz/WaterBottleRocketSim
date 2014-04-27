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

import javax.swing.Painter;

/**
 * 
 * @author Cameron Yang, Tanmay Chordia
 * 
 * This class creates the Panel that contains the sliders to be used for the simulation.
 *
 */

public class RocketSliders extends JPanel implements ChangeListener {

	FlowLayout experimentLayout = new FlowLayout();

	public static minimalSlider Ln;  
	public static minimalSlider D;
	public static minimalSlider Df;
	public static minimalSlider Dr;
	public static minimalSlider Lt;
	public static minimalSlider Xp;
	public static minimalSlider Cr;
	public static minimalSlider Ct;
	public static minimalSlider S;  
	public static minimalSlider Lf;
	public static minimalSlider R;
	public static minimalSlider Xr;
	public static minimalSlider Xb;
	public static minimalSlider N;
	
    UIDefaults sliderDefaults = new UIDefaults();
    
    private BufferedImage sliderThumb;

	public RocketSliders() {

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
		
		Ln = new minimalSlider (minimalSlider.HORIZONTAL, 0, 10, 7); // divide by 100

		Ln.setMajorTickSpacing(1);

		Ln.setMinorTickSpacing(1);

		Ln.setPaintLabels(true);
		
		Ln.addChangeListener(this);
		
		JPanel noseLengthPanel = new JPanel();
		noseLengthPanel.setBackground(Color.white);
		noseLengthPanel.add(Ln);
		noseLengthPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Length of Nose"));
		

		D = new minimalSlider(minimalSlider.HORIZONTAL, 0, 10, 6);

		D.setMajorTickSpacing(1);

		D.setMinorTickSpacing(1);

		D.setPaintLabels(true);

		D.addChangeListener(this);

		JPanel noseBaseDiameterPanel = new JPanel();
		noseBaseDiameterPanel.setBackground(Color.white);
		noseBaseDiameterPanel.add(D);
		noseBaseDiameterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Diameter of Nose Base"));


		Df = new minimalSlider(minimalSlider.HORIZONTAL, 0, 100, 20); // divide value by 1000

		Df.setMajorTickSpacing(10);

		Df.setPaintLabels(true);

		Df.addChangeListener(this);

		JPanel DfPanel = new JPanel();
		DfPanel.setBackground(Color.white);
		DfPanel.add(Df);
		DfPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Diameter at Front Transition"));


		Dr = new minimalSlider(minimalSlider.HORIZONTAL, 20, 100, 26);

		Dr.setMajorTickSpacing(10);

		Dr.setMinorTickSpacing(1);

		Dr.setPaintLabels(true);

		Dr.addChangeListener(this);

		JPanel DrPanel = new JPanel();
		DrPanel.setBackground(Color.white);
		DrPanel.add(Dr);
		DrPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Diameter at Rear Transition"));


		Xp = new minimalSlider(minimalSlider.HORIZONTAL, 0, 5, 1);

		Xp.setMajorTickSpacing(1);

		Xp.setMinorTickSpacing(1);

		Xp.setPaintLabels(true);

		Xp.addChangeListener(this);

		JPanel XpoefficientPanel = new JPanel();

		XpoefficientPanel.setBackground(Color.white);
		XpoefficientPanel.add(Xp);
		XpoefficientPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Distance from Nose to Transition"));


		Cr = new minimalSlider(minimalSlider.HORIZONTAL, 0, 10, 5);// divide by 100
		
		Cr.setMajorTickSpacing(1);

		Cr.setMinorTickSpacing(1);

		Cr.setPaintLabels(true);
		
		Cr.addChangeListener(this);
		
		JPanel CrPanel = new JPanel();
		CrPanel.setBackground(Color.white);
		CrPanel.add(Cr);
		CrPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Fin root chord"));
		
		
		Ct = new minimalSlider (minimalSlider.HORIZONTAL, 1, 10, 1); // divide by
																			  // 100
		Ct.setMajorTickSpacing(1);

		Ct.setMinorTickSpacing(1);

		Ct.setPaintLabels(true);

		Ct.addChangeListener(this);
		
		JPanel CtPanel = new JPanel();
		CtPanel.setBackground(Color.white);
		CtPanel.add(Ct);
		CtPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Fin Tip Chord"));
		
		
		S = new minimalSlider (minimalSlider.HORIZONTAL, 1, 10, 1);
		
		S.setMajorTickSpacing(1);

		S.setMinorTickSpacing(1);

		S.setPaintLabels(true);

		S.addChangeListener(this);
		
		JPanel SPanel = new JPanel();
		SPanel.setBackground(Color.white);
		SPanel.add(S);
		SPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Fin Semi Span"));
		
		
		Lf = new minimalSlider (minimalSlider.HORIZONTAL, 1, 10, 1);
		
		Lf.setMajorTickSpacing(1);

		Lf.setMinorTickSpacing(1);

		Lf.setPaintLabels(true);

		Lf.addChangeListener(this);
		
		JPanel LfPanel = new JPanel();
		LfPanel.setBackground(Color.white);
		LfPanel.add(Lf);
		LfPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Length of Fin Mid-Chord"));
		
		R = new minimalSlider (minimalSlider.HORIZONTAL, 1, 10, 1);
		
		R.setMajorTickSpacing(1);

		R.setMinorTickSpacing(1);

		R.setPaintLabels(true);

		R.addChangeListener(this);
		
		JPanel RPanel = new JPanel();
		RPanel.setBackground(Color.white);
		RPanel.add(R);
		RPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Radius of Body at Aft End"));
		
		Xr = new minimalSlider (minimalSlider.HORIZONTAL, 1, 10, 1);
		
		Xr.setMajorTickSpacing(1);

		Xr.setMinorTickSpacing(1);

		Xr.setPaintLabels(true);

		Xr.addChangeListener(this);
		
		JPanel XrPanel = new JPanel();
		XrPanel.setBackground(Color.white);
		XrPanel.add(Xr);
		XrPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Edge and Fin Tip Leading Edge"));
		
		Xb= new minimalSlider (minimalSlider.HORIZONTAL, 1, 10, 1);
		
		Xb.setMajorTickSpacing(1);

		Xb.setMinorTickSpacing(1);

		Xb.setPaintLabels(true);

		Xb.addChangeListener(this);
		
		JPanel XbPanel = new JPanel();
		XbPanel.setBackground(Color.white);
		XbPanel.add(Xb);
		XbPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Distance from Nose Tip to Fin Root"));
		
		N = new minimalSlider (minimalSlider.HORIZONTAL, 1, 10, 1);
		
		N.setMajorTickSpacing(1);

		N.setMinorTickSpacing(1);

		N.setPaintLabels(true);

		N.addChangeListener(this);
		
		JPanel NPanel = new JPanel();
		NPanel.setBackground(Color.white);
		NPanel.add(N);
		NPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Number of Fins"));
		
		GroupLayout sliderLayout = new GroupLayout(sliders);
		
		sliders.setLayout(sliderLayout);
		
		sliderLayout.setAutoCreateContainerGaps(true);
		sliderLayout.setAutoCreateGaps(true);
		
		GroupLayout.SequentialGroup horizontalLayout = sliderLayout.createSequentialGroup();
		GroupLayout.SequentialGroup verticalLayout = sliderLayout.createSequentialGroup();
		
		horizontalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(noseLengthPanel)
				.addComponent(DrPanel)
				.addComponent(CrPanel)
				.addComponent(CtPanel)
				.addComponent(LfPanel)
				.addComponent(XrPanel)
				.addComponent(NPanel)
				);

		horizontalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(noseBaseDiameterPanel)
				.addComponent(XpoefficientPanel)
				.addComponent(DfPanel)
				.addComponent(SPanel)
				.addComponent(RPanel)
				.addComponent(XbPanel)
				);

		sliderLayout.setHorizontalGroup(horizontalLayout);
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(noseLengthPanel).addComponent(noseBaseDiameterPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(DrPanel).addComponent(XpoefficientPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup()
				.addComponent(DfPanel).addComponent(CrPanel));

		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(SPanel).addComponent(CtPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(LfPanel).addComponent(RPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(XrPanel).addComponent(XbPanel));
		
		verticalLayout.addGroup(sliderLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(NPanel));
		
		sliderLayout.setVerticalGroup(verticalLayout);
		sliders.setBackground(Color.white);
		add(sliders);
	}
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Button Panel Example");
		frame.setSize(new Dimension(600,600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new RocketSliders(), BorderLayout.PAGE_START);
		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
	}

	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Ln: "
				+ (Ln.getValue() / (100 + 0.0)));
		System.out.println("D: " + D.getValue() / (100 + 0.0));
		System.out.println("Df: " + Df.getValue()
				/ (1000 + 0.0));
		System.out.println("Dr: " + Dr.getValue()*10000);
		System.out.println("Xp: " + Xp.getValue());
		System.out.println("Cr: " + Cr.getValue()
				/ (100 + 0.0));
		System.out.println("Ct: " + Ct.getValue()
				/ (100 + 0.0));

	}

}