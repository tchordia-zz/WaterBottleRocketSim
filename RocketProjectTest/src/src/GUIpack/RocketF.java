package src.GUIpack;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;

public class RocketF extends JFrame {
	
	public SPanel spanel = new SPanel();
	public AnimPanel apanel = new AnimPanel(new RocketMath2());
    

	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RocketF ()
    {
     super();
    }

       
        
       
   public static void main(String[] args) 
   {
		RocketF frame = new RocketF();
	   	
//	   	try {
//	   	    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//	   	} catch (Exception e) {
//	   	    e.printStackTrace();
//	   	}
//	   
        frame.setTitle("Button Panel Example");
        frame.setSize(600, 600);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        frame.add(frame.spanel);
        frame.add(frame.apanel);
        frame.apanel.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

   

   public class SPanel extends SliderPanel
   {
	   public SPanel()
	   {
		   super();
		   setSize(200,200);
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