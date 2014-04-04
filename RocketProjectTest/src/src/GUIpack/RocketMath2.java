/**
 * 
 */
package src.GUIpack;

import java.awt.Graphics;

/**
 * Wrapper Class for the DrawGraph class to use with the copy method.
 * after version 2 I removed most of the methods, this class is now essentially useless, it 
 * only has a copy method for the RocketMath class
 * 
 * @author Tanmay Chordia
 * @date  2/19/2014
 * @see RocketMath Class, DrawGraph
 * @version 2
 *
 */


public class RocketMath2 extends RocketMath  {

/* 
 * See RocketMath class
 * */
	
	public RocketMath2(double m0, double mW, double vB, double p0, double cD, double rBot, double rNoz)
	{
		super(m0, mW, vB, p0, cD, rBot, rNoz);
		
	}
	
	public RocketMath2()
	{
		super();
	}
	
	
    public RocketMath2 copy() {
    	RocketMath2 a = new RocketMath2(m0,mW,vB, p0, cD/5000000,  rBot,  rNoz);
    	return a;
    }
}
	
	