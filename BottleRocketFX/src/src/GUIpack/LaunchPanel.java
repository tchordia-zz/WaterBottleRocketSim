/**
 * 
 */
package src.GUIpack;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
	String user;
	public LaunchPanel()
	{
		setLayout(new GridLayout());
		
		
		gui.setLayout(new GridLayout());
	
		add(sliderp);
	}
	public void updateUser(String user)
	{
		sliderp.spanel.load = new LoadPanel(user){
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			super.actionPerformed(e);
			try {
				RocketF.mRocket =DataSave.retrieve(user, savename);
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sliderp.apanel.updaterr(RocketF.mRocket.copy());
			sliderp.ball.update(RocketF.mRocket.copy());
			sliderp.ball.repaint();
			sliderp.apanel.repaint();
			System.out.println("Doneaa");}
		};
		sliderp.spanel.tabbedPane.add("load shit", sliderp.spanel.load);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	
	public class SliderGui extends JPanel
	{
		public SPanel spanel = new SPanel();
		public GraphPanel apanel = new GraphPanel(new RocketMath());
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
			boolean a = true;   
			 JButton submit = new CoolButton("Animate Slider Values", CoolButton.SMALL, Color.black);
			public SPanel() 
			   {
				   super();
				 
				   submit.addActionListener(this);
				   add(submit);
				   try {
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException | UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   repaint();
			   }
			   RocketMath rocket2;
			   @Override
			   public void actionPerformed(ActionEvent arg0)
			   {
				  
					rocket2 = new RocketMath(/*massRocket.getValue()/(10 + 0.0),*/ massWater.getValue()/(10 + 0.0), volumeBottle.getValue()/(10000 + 0.0),airPressure.getValue() * 10000,dragC.getValue(),bottleRadius.getValue()/(100 + 0.0), nozzleRadius.getValue()/(100 + 0.0));
					
					RocketMath r = rocket2.copy();
					apanel.updaterr(r);
					ball.update(rocket2);
					
					
					
			   }
			   public void stateChanged(ChangeEvent evt)
			   {
				  
				 try{
				   JTabbedPane p = ((JTabbedPane)evt.getSource());
				   if (a)
				   {
					   remove(submit);
					   repaint();
					   a = !a;
				   }
				   else
				   {
					   add(submit);		
					   repaint();
					   a = !a;
					   }
				 }
				 catch( java.lang.ClassCastException e)
				 {
				  
				   rocket2 = new RocketMath(/*massRocket.getValue()/(10 + 0.0),*/ massWater.getValue()/(10 + 0.0), volumeBottle.getValue()/(10000 + 0.0),airPressure.getValue() * 10000,dragC.getValue(),bottleRadius.getValue()/(100 + 0.0), nozzleRadius.getValue()/(100 + 0.0));
				   rocket2.printStuff();
				   apanel.updaterr(rocket2);
				 }
			   }
			   
			
			   
		   }
	}
	   
	  

}
