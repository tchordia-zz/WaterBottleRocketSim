package src.GUIpack;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;

public class RocketF extends JFrame  {
	
	public SPanel spanel = new SPanel();
	public AnimPanel apanel = new AnimPanel(new RocketMath2());
    

	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RocketF ()
    {
		super(); 
		try {
		   	    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		   	} catch (Exception e) {
		   	    e.printStackTrace();
		   	}

        setTitle("Button Panel Example");
        setSize(600, 600);
        setLayout(new GridLayout());
        
        add(this.spanel);
        add(this.apanel);
        add(new JButton());
        apanel.setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
		
    
    }

       
        
       
   public static void main(String[] args) 
   {
		
	   RocketF frame = new RocketF();

    }

   

   public class SPanel extends SliderPanel
   {
	   public SPanel() 
	   {
		   super();
		   setSize(getWidth(),getHeight());
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