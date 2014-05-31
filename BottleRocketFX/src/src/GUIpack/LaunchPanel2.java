package src.GUIpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javafx.scene.input.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;

import mathPack.AngularLaunch;
import mathPack.Barrowman;
import mathPack.RocketMath;

public class LaunchPanel2 extends JPanel {

	public SPanel spanel = new SPanel();
	public VerticalBall apanel;
	AngularLaunch rocket2;
	GraphPanel graph = new GraphPanel(rocket2);
	double launcha = 60;
	private static final long serialVersionUID = 1L;

	public LaunchPanel2() {

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
							/ (500 + 0.0), launcha);
			graph.updaterr(rocket2.copy());

		spanel.setBackground(Color.white);
		add(spanel);
		add(graph);
		add(apanel);
		apanel.setVisible(true);
		setSize(new Dimension(1000, 1000));
		setLayout(new GridLayout());
		setBackground(Color.white);
		setPreferredSize(new Dimension(1000, 1000));

		setVisible(true);
	}

	public void setupAnimation() {

		Random aynRand = new Random();
		
		int randomNumber = aynRand.nextInt(300)+100;
        
        apanel = new VerticalBall(300, 400, randomNumber)
        {
        	public void resetBall()
        	{
        		super.resetBall();
    			rocket2 = new AngularLaunch(
    	    			0,
    	    			SliderPanel2.massWater.getValue()/(10 + 0.0),
    	    			apanel.rocket.barrow.volume(),
    	    			SliderPanel2.airPressure.getValue()*10000,
    	    			Barrowman.cone,
    	    			(apanel.rocket.Df > apanel.rocket.Dr) ? apanel.rocket.Df:apanel.rocket.Dr,
    	    			spanel.nozzleRadius.getValue()/(500 + 0.0),
    	    		launcha);
    			
    			RocketMath.printStats(rocket2);
    			graph.updaterr(rocket2.copy());
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
    	    			launcha);
    			System.out.println(rocket2.angle);
    			RocketMath.printStats(rocket2.copy());
    			graph.updaterr(rocket2.copy());
        	}

        	public void mathClass(double t)
        	{
        		rocket2.doStepThrust();
        		VerticalBall.y=rocket2.y1*-10;
        		VerticalBall.x=rocket2.x1*10;
        		VerticalBall.angle = 90-rocket2.angle*180/Math.PI;
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
							.asin(VerticalBall.sineTheta)));
			graph.updaterr(rocket2.copy());

		}
	}
	
	public void loadNewRocket(double[] d)
	{
		apanel.loadNewRocket(new CreateRocket(d));
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