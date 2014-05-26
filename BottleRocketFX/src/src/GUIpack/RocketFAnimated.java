package src.GUIpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javafx.scene.input.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;

import mathPack.AngularLaunch;

public class RocketFAnimated extends JPanel  {
	
	public SPanel spanel = new SPanel();
	public ProjectileBall apanel;
	AngularLaunch rocket2 = new AngularLaunch(
			spanel.massRocket.getValue()/(10 + 0.0),
			spanel.massWater.getValue()/(10 + 0.0),
			spanel.volumeBottle.getValue()/(10000 + 0.0),
			spanel.airPressure.getValue()*10000,
			spanel.dragC.getValue(),
			spanel.bottleRadius.getValue()/(100 + 0.0),
			spanel.nozzleRadius.getValue()/(500 + 0.0),
			Math.toDegrees(Math.asin(ProjectileBall.sineTheta)));
	
	private static final long serialVersionUID = 1L;
	
	public RocketFAnimated ()
    {

		super(); 

        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())){
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } 
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        
        setupAnimation();

        spanel.setBackground(Color.white);
        add(spanel);
        add(apanel);
        apanel.setVisible(true);

        
        setSize(new Dimension(1000, 1000));
        setLayout(new GridLayout(0,2));
        setBackground(Color.white);
        setPreferredSize(new Dimension(1000,1000));
       
        setVisible(true);
    }
	
	public void setupAnimation()
	{
        
        apanel = new ProjectileBall(300, 400)
        {
        	public void resetBall()
        	{
        		super.resetBall();
    			rocket2 = new AngularLaunch(
    					spanel.massRocket.getValue()/(10 + 0.0),
    					spanel.massWater.getValue()/(10 + 0.0),
    					apanel.rocket.barrow.volume(),
    					spanel.airPressure.getValue()*10000,
    					spanel.dragC.getValue(),
    					spanel.bottleRadius.getValue()/(100 + 0.0),
    					spanel.nozzleRadius.getValue()/(500 + 0.0),
    					Math.toDegrees(Math.PI/2));
        	}
       
        	public void angleAdjust(MouseEvent e)
        	{
        		super.angleAdjust(e);
    			rocket2 = new AngularLaunch(
    					spanel.massRocket.getValue()/(10 + 0.0),
    					spanel.massWater.getValue()/(10 + 0.0),
    					apanel.rocket.barrow.volume(),
    					spanel.airPressure.getValue()*10000,
    					spanel.dragC.getValue(),
    					spanel.bottleRadius.getValue()/(100 + 0.0),
    					spanel.nozzleRadius.getValue()/(500 + 0.0),
    					Math.toDegrees(Math.asin(ProjectileBall.sineTheta)));
    			System.out.println(rocket2.angle);
        	}

        	public void mathClass(double t)
        	{
        		rocket2.doStepThrust();
        		ProjectileBall.y=rocket2.y1*-10;
        		ProjectileBall.x=rocket2.x1*10;
        		ProjectileBall.angle = 90-rocket2.angle*180/Math.PI;
        	}
        };
	}

   public static void main(String[] args) 
   {
	   JFrame frame = new JFrame();
	   frame.getContentPane().add(new RocketFAnimated());
	   frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
       frame.setSize(1000,1000);
       frame.setLocation(100,100);
   }

   public class SPanel extends SliderPanel2
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
			System.out.println("volumeBottle: " + apanel.rocket.barrow.volume()) ;
			System.out.println("airPressure: " + airPressure.getValue()*10000) ;
			System.out.println("dragC: " + dragC.getValue()) ;
			System.out.println("bottleRadius: " + bottleRadius.getValue()/(100+0.0)) ;
			System.out.println("nozzleRadius: " + nozzleRadius.getValue()/(100+0.0)) ;
			System.out.println("angle: " + rocket2.angle);
			rocket2 = new AngularLaunch(
					spanel.massRocket.getValue()/(10 + 0.0),
					spanel.massWater.getValue()/(10 + 0.0),
					apanel.rocket.barrow.volume(),
					spanel.airPressure.getValue()*10000,
					spanel.dragC.getValue(),
					spanel.bottleRadius.getValue()/(100 + 0.0),
					spanel.nozzleRadius.getValue()/(500 + 0.0),
					Math.toDegrees(Math.asin(ProjectileBall.sineTheta)));
	   }
   }
   public class customRocket extends SliderPanel2
   {
	   public customRocket() 
	   {
		   super();
		   setSize(getWidth(),getHeight());
	   }
	   @Override
	   public void stateChanged(ChangeEvent arg0)
	   {
		   
	   }
   }
}