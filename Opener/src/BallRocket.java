import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;

import javax.swing.JApplet;



/**
 * This class creates a ball, and treats it like a rocket,
 * firing it off and tracking its altitude. It is a prelim rocket.
 * 
 * @author Tanmay Chordia
 * @date Feb 19, 2014
 * @version 1
 */
public class BallRocket extends AnimationBase {

	/**
	 * @throws HeadlessException
	 */
	public BallRocket() throws HeadlessException {
		super();
	}
	RocketMath rocket = new RocketMath(0.76, 0.66, .002, 1053312.5, 1,.05, .01, .04); // last one changes the step to .05, increase to speed up, decrease to slow down 
	Circle circ = new Circle(getWidth()/2, 500, 5); //the circle object
	public int screenHeight = 40; // the height of the screen

	/* (non-Javadoc)
	 * @see RocketApplet.AnimationBase#drawFrame(java.awt.Graphics)
	 */
	@Override
	public void drawFrame(Graphics g) {
		circ.setX(getWidth() / 2);
		if ((getHeight() * Math.round(rocket.h)) / screenHeight >= 0)
		{
		setMillisecondsPerFrame( 1 );
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.WHITE);
		g.fillRect(0,0,1000, 1000);



		g.setColor(Color.black);
		rocket.doStep();
		circ.setH((int) (Math.round((height * rocket.h) / screenHeight)), height);
		circ.fill(g);
		System.out.println(circ.y);
		}
		else // refires the rocket
			rocket = new RocketMath(0.76, 0.66, .002, 653312.5, 1,.05, .01,.02); //last param changes step to .05

	}

}