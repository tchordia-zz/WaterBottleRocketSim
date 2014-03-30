/**
 * 
 */
package src.GUIpack;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

/**
 * @author Tanmay
 *
 */
public class LaunchPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	
	public SliderGui sliderp = new SliderGui();
	
	public GUI gui = new GUI();
	public ImageIcon icon = createImageIcon("background.jpeg");
//	public LaunchPanel() {
//		// TODO Auto-generated constructor stub
//		setLayout(new GridLayout());
//		setVisible(true);
//	    add(this.spanel);
//	    add(this.apanel);
//	    Border bord = BorderFactory.createLineBorder(Color.black, 5);
//		setBorder(bord); 
//	    setVisible(true);
//	}
	public LaunchPanel()
	{
		setLayout(new GridLayout());
		
		
		gui.setLayout(new GridLayout());
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Sliders",icon,sliderp, "Slider Input" );
		tabs.addTab("Text", icon, gui, "text input");
		tabs.setSize(getWidth(), getHeight());
		add(tabs);
	}

	/**
	 * @param layout
	 */
	public LaunchPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}
	protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = LaunchPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	/**
	 * @param isDoubleBuffered
	 */
	public LaunchPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public LaunchPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	
	public class SliderGui extends JPanel
	{
		public SPanel spanel = new SPanel();
		public GraphPanel apanel = new GraphPanel(new RocketMath2());
		public BouncingBall ball = new BouncingBall();
		public SliderGui()
		{
			setLayout(new GridLayout());
			add(spanel);
			add(apanel);
			add(ball);
		}
		public class SPanel extends SliderPanel2 implements ActionListener
		   {
			   public SPanel() 
			   {
				   super();
				   JButton submit = new JButton("Animate");
				   submit.addActionListener(this);
				   add(submit);
			   }
			   RocketMath2 rocket2;
			   @Override
			   public void actionPerformed(ActionEvent arg0)
			   {
				   System.out.println("massRocket: " + (massRocket.getValue()/(10+0.0))) ;
					System.out.println("massWater: " + massWater.getValue()/(10+0.0)) ;
					System.out.println("volumeBottle: " + volumeBottle.getValue()/(10000+0.0)) ;
					System.out.println("airPressure: " + airPressure.getValue()) ;
					System.out.println("dragC: " + dragC.getValue()) ;
					System.out.println("bottleRadius: " + bottleRadius.getValue()/(100+0.0)) ;
					System.out.println("nozzleRadius: " + nozzleRadius.getValue()/(100+0.0)) ;
					rocket2 = new RocketMath2(massRocket.getValue()/(10 + 0.0), massWater.getValue()/(10 + 0.0), volumeBottle.getValue()/(10000 + 0.0),airPressure.getValue() * 10000,dragC.getValue(),bottleRadius.getValue()/(100 + 0.0), nozzleRadius.getValue()/(100 + 0.0));
					
					
					ball.update(rocket2);
					
					
			   }
			   public void stateChanged(ChangeEvent arg0)
			   {
				   rocket2 = new RocketMath2(massRocket.getValue()/(10 + 0.0), massWater.getValue()/(10 + 0.0), volumeBottle.getValue()/(10000 + 0.0),airPressure.getValue() * 10000,dragC.getValue(),bottleRadius.getValue()/(100 + 0.0), nozzleRadius.getValue()/(100 + 0.0));
				   apanel.updaterr(rocket2);
			   }
			   
			
			   
		   }
	}
	   
	  

}
