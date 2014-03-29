/**
 * 
 */
package src.GUIpack;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;

/**
 * @author Tanmay
 *
 */
public class LaunchPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	
	public SPanel spanel = new SPanel();
	public GraphPanel apanel = new GraphPanel(new RocketMath2());
	
	public LaunchPanel() {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout());
		setVisible(true);
	    add(this.spanel);
	    add(this.apanel);
	    Border bord = BorderFactory.createLineBorder(Color.black, 5);
		setBorder(bord); 
	    setVisible(true);
	}

	/**
	 * @param layout
	 */
	public LaunchPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
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
	
	public class SPanel extends SliderPanel
	   {
		   public SPanel() 
		   {
			   super();
			   
		   }
		   RocketMath2 rocket2;
		   @Override
		   public void stateChanged(ChangeEvent arg0)
		   {
			   System.out.println("massRocket: " + (massRocket.getValue()/(10+0.0))) ;
				System.out.println("massWater: " + massWater.getValue()/(10+0.0)) ;
				System.out.println("volumeBottle: " + volumeBottle.getValue()/(10000+0.0)) ;
				System.out.println("airPressure: " + airPressure.getValue()) ;
				System.out.println("dragC: " + dragC.getValue()) ;
				System.out.println("bottleRadius: " + bottleRadius.getValue()/(100+0.0)) ;
				System.out.println("nozzleRadius: " + nozzleRadius.getValue()/(100+0.0)) ;
				rocket2 = new RocketMath2(massRocket.getValue()/(10 + 0.0), massWater.getValue()/(10 + 0.0), volumeBottle.getValue()/(10000 + 0.0),airPressure.getValue(),dragC.getValue(),bottleRadius.getValue()/(100 + 0.0), nozzleRadius.getValue()/(100 + 0.0));
				apanel.updaterr(rocket2);
				
		   }
		   
		
		   
	   }
	   
	  

}
