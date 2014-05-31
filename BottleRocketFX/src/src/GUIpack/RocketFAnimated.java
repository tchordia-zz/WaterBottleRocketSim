package src.GUIpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Random;

import javafx.scene.input.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;

import mathPack.AngularLaunch;
import mathPack.Barrowman;
import mathPack.RocketMath;

public class RocketFAnimated extends JPanel {

	public SPanel spanel = new SPanel();
	public ProjectileBall apanel;
	private JLayeredPane layeredPane;
	AngularLaunch rocket2;

	private static final long serialVersionUID = 1L;

	public RocketFAnimated() {

		super();

		for (UIManager.LookAndFeelInfo laf : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(laf.getName())) {
				try {
					UIManager.setLookAndFeel(laf.getClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		setupAnimation();

		rocket2 = new AngularLaunch(0,
				spanel.massWater.getValue() / (10 + 0.0),
				apanel.rocket.barrow.volume(),
				spanel.airPressure.getValue() * 10000, Barrowman.cone,
				(apanel.rocket.Df > apanel.rocket.Dr) ? apanel.rocket.Df
						: apanel.rocket.Dr, spanel.nozzleRadius.getValue()
						/ (500 + 0.0), Math.toDegrees(Math
						.asin(ProjectileBall.sineTheta)));
		
		layeredPane= new JLayeredPane();
		
		JPanel green = new JPanel();
		
		layeredPane.add(green, new Integer(0));

		layeredPane.add(apanel, new Integer(0));
		layeredPane.add(spanel, new Integer(1));
		
		layeredPane.setPreferredSize(new Dimension(1000, 1000));

		spanel.setBackground(Color.white);

		apanel.setBounds(0,0, 1000, 1000);
		spanel.setBounds(0, 450, 300, 275);

		add(layeredPane);
		layeredPane.setBackground(Color.blue);
		layeredPane.setVisible(true);

		setSize(new Dimension(1000, 1000));
		setBackground(Color.white);
		setPreferredSize(new Dimension(1000, 1000));
		setVisible(true);
	}
	private void updatePreferredSize(int n) {
	    double d = (double) n * 1.08;
	    d = (n > 0) ? 1 / d : -d;

	    int w = (int) (getWidth() * d);
	    int h = (int) (getHeight() * d);
	    apanel.setPreferredSize(new Dimension(w, h));

	    

	    //getParent().doLayout();
	}
	public void setupAnimation() {

		Random aynRand = new Random();
		
		int randomNumber = aynRand.nextInt(300)+100;
        
        apanel = new ProjectileBall(300, 400, randomNumber)
        {
        	public void resetBall()
        	{
        		super.resetBall();
        		updatePreferredSize(5);
    			rocket2 = new AngularLaunch(
    	    			0,
    	    			spanel.massWater.getValue()/(10 + 0.0),
    	    			apanel.rocket.barrow.volume(),
    	    			spanel.airPressure.getValue()*10000,
    	    			Barrowman.cone,
    	    			(apanel.rocket.Df > apanel.rocket.Dr) ? apanel.rocket.Df:apanel.rocket.Dr,
    	    			spanel.nozzleRadius.getValue()/(500 + 0.0),
    	    			Math.toDegrees(Math.asin(ProjectileBall.sineTheta)));
    			
    			RocketMath.printStats(rocket2);
        	}
       
        	public void angleAdjust(MouseEvent e)
        	{
        		super.angleAdjust(e);
        		System.out.println("Nozzle Radius: " + apanel.rocket.Nz*Barrowman.toMeasurements);
        		System.out.println("Volume: " + apanel.rocket.barrow.volume());
    			rocket2 = new AngularLaunch(
    	    			0,
    	    			spanel.massWater.getValue()/(10 + 0.0),
    	    			apanel.rocket.barrow.volume(),
    	    			spanel.airPressure.getValue()*10000,
    	    			Barrowman.cone,
    	    			((apanel.rocket.Df > apanel.rocket.Dr) ? apanel.rocket.Df :apanel.rocket.Dr) * Barrowman.toMeasurements,
    	    			apanel.rocket.Nz*Barrowman.toMeasurements,
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

	public class SPanel extends SliderPanel2 {
		public SPanel() {
			super();
			setSize(getWidth(), getHeight());
		}
		@Override
		public void stateChanged(ChangeEvent arg0) {
			System.out.println("massWater: " + massWater.getValue()
					/ (10 + 0.0));
			System.out
					.println("volumeBottle: " + apanel.rocket.barrow.volume());
			System.out
					.println("airPressure: " + airPressure.getValue() * 10000);
			;
			System.out.println("nozzleRadius: " + nozzleRadius.getValue()
					/ (100 + 0.0));
			System.out.println("angle: " + rocket2.angle);
			rocket2 = new AngularLaunch(0, spanel.massWater.getValue()
					/ (10 + 0.0), apanel.rocket.barrow.volume(),
					spanel.airPressure.getValue() * 10000, Barrowman.cone,
					(apanel.rocket.Df > apanel.rocket.Dr) ? apanel.rocket.Df
							: apanel.rocket.Dr, spanel.nozzleRadius.getValue()
							/ (500 + 0.0), Math.toDegrees(Math
							.asin(ProjectileBall.sineTheta)));
		}
	}
	
	public static void main(String [] args)
	{
		JFrame frame = new JFrame();
		
		JPanel rockettt = new RocketFAnimated();
				
		frame.add(rockettt);
		frame.setSize(400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void loadNewRocket(double[] d)
	{
		apanel.loadNewRocket(new CreateRocket(d, ProjectileBall.rocketFactor));
	}

	public class customRocket extends SliderPanel2 {
		public customRocket() {
			super();
			setSize(getWidth(), getHeight());
		}

		@Override
		public void stateChanged(ChangeEvent arg0) {

		}
	}
}