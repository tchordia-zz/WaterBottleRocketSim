package src.GUIpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;

public class RocketFAnimated extends JFrame  {
	
	public SPanel spanel = new SPanel();
	public ProjectileBall apanel;
	RocketMath2 rocket2 = new RocketMath2();
	private static final long serialVersionUID = 1L;
	
	public RocketFAnimated ()
    {

		super(); 

        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())){
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        
        apanel = new ProjectileBall(300, 400)
        {
        	public void resetBall()
        	{
        		super.resetBall();
    			rocket2 = new RocketMath2(spanel.massRocket.getValue()/(10 + 0.0),
    									  spanel.massWater.getValue()/(10 + 0.0),
    									  spanel.volumeBottle.getValue()/(10000 + 0.0),
    									  spanel.airPressure.getValue()*10000,
    									  spanel.dragC.getValue(),
    									  spanel.bottleRadius.getValue()/(100 + 0.0),
    									  spanel.nozzleRadius.getValue()/(100 + 0.0));
        	}
        	
        	public void mathClass(double t)
        	{
        		super.mathClass(t);
        		rocket2.t=t/1000;
        		rocket2.doStep();
        		ProjectileBall.y=rocket2.h;
        	}
        };
        
        
        setTitle("Button Panel Example");
        setSize(new Dimension(600, 600));
        setLayout(new GridLayout(0,2));
        spanel.setBackground(Color.white);
        add(spanel);
        add(apanel);
        setBackground(Color.white);
        setPreferredSize(new Dimension(1000,1000));
        apanel.setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

   public static void main(String[] args) 
   {
	   RocketFAnimated frame = new RocketFAnimated();
   }


   public class SPanel extends SliderPanel
   {
	   public SPanel() 
	   {
		   super();
		   setSize(getWidth(),getHeight());
	   }
	   @Override
	   public void stateChanged(ChangeEvent arg0)
	   {
		   System.out.println("massRocket: " + (massRocket.getValue()/(10+0.0))) ;
			System.out.println("massWater: " + massWater.getValue()/(10+0.0)) ;
			System.out.println("volumeBottle: " + volumeBottle.getValue()/(10000+0.0)) ;
			System.out.println("airPressure: " + airPressure.getValue()*1000) ;
			System.out.println("dragC: " + dragC.getValue()) ;
			System.out.println("bottleRadius: " + bottleRadius.getValue()/(100+0.0)) ;
			System.out.println("nozzleRadius: " + nozzleRadius.getValue()/(100+0.0)) ;
			rocket2 = new RocketMath2(massRocket.getValue()/(10 + 0.0), massWater.getValue()/(10 + 0.0), volumeBottle.getValue()/(10000 + 0.0),airPressure.getValue()*10000,dragC.getValue(),bottleRadius.getValue()/(100 + 0.0), nozzleRadius.getValue()/(100 + 0.0));
	   }
   }
}