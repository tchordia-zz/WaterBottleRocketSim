/**
 * 
 */
package src.GUIpack;

/**
 * @author Tanmay Chordia
 * @date  2/19/2014
 * @see RocketMathApplet Class
 * @version 3
 *
 */

public class RocketMath {

	/**
	 * @param args
	 * @param m0 = total weight of rocket (kg)
	 * @param mW = intital amount of water (kg)
	 * @param vB = bottle volume (liters)
	 * @param p0 = intial air pressure (pascals)
	 * @param cD = drag coefficient  ( no unit)
	 * @param step = time step
	 * @param rBot = bottle radius (m)
	 * @param rNoz = nozzle radius (m)
	 * @param iP = internal pressure (pascals)
	 * @param pW = water density
	 * @param oP = external pressure
	 * @param m = mass at a given time
	 * @param a = acceleration at a given time
	 * @param v = velocity at a given time
	 * @param h = height at a given time
	 * @param pA = air density at a given time

	 general notes: p + letter = density, letter + p = pressure
	 ex: pA = air density, iP = internal pressure
	 */
	public double m0;
	public double mW;
	public double vB;
	public double p0;
	public double cD;
   
    public double rBot;
    public double rNoz;
    public double vW0;
    public double vA0;
    public double iP;
    public double pW = .001;
    
    
	public double dMdt;
	public double thrust;
	public double m = 0;
	public double v = 0;
	public double a = 0;
	public double h = 0;
	public int t = 0;
	public final double y = 1.4;
	public final double g = 9.81;
	public final double oP = 101325; //pascals
	public double step = 0.01; 
	public final double pA = .0000013; //TODO: FIX ALL CONSTANT VALUES 
    
	
	public RocketMath(double m0, double mW, double vB, double p0, double cD, double rBot, double rNoz)
	{
		this.m0 = m0;
		this.mW = mW;
		this.vB = vB;
		this.p0 = p0;
		this.iP = p0;
		this.cD = cD * 5000000;
		this.rBot = rBot;
		this.rNoz = rNoz;
		this.vW0 = pW * mW;
		this.vA0 = vB - vW0;
		this.m = m0;
		
		this.dM_dt();
		this.iPc();
		this.thrust();
		
		//System.out.println(thrust + " mass " + m + " acceleration " + a + " velocity " + v + " height " + h);
		
	}
	
	public RocketMath(double m0, double mW, double vB, double p0, double cD, double rBot, double rNoz, double step)
	{
		this.m0 = m0;
		this.mW = mW;
		this.vB = vB;
		this.p0 = p0;
		this.iP = p0;
		this.cD = cD * 5000000;
		this.rBot = rBot;
		this.rNoz = rNoz;
		this.vW0 = pW * mW;
		this.vA0 = vB - vW0;
		this.m = m0;
		
		this.step = step;
		this.dM_dt();
		this.iPc();
		this.thrust();
		
		//System.out.println(thrust + " mass " + m + " acceleration " + a + " velocity " + v + " height " + h);
		
	}
	
	public RocketMath()
	{
		RocketMath rocket = new RocketMath(0.76, 0.66, .002, 253312.5, 1,.05, .01);
		
		this.m0 = .76;
		this.mW = .66;
		this.vB = .002;
		this.p0 = 253312.5;
		this.iP = p0;
		this.cD = 1 * 5000000;
		this.rBot = .05;
		this.rNoz = .01;
		this.vW0 = pW * mW;
		this.vA0 = vB - vW0;
		this.m = m0;
		
		this.dM_dt();
		this.iPc();
		this.thrust();
		
		//System.out.println(thrust + " mass " + m + " acceleration " + a + " velocity " + v + " height " + h);
	}
	
	
	
	/**
	 * @return iP, return the internal air pressure of the water bottle rocket
	 */
	public double iPc()
	{
		iP = p0 * Math.pow((( vA0 + (m0 - m)/pW)/ vA0), -y);
		//System.out.println();
		
		int a = (int)(1000 * (m0 - m));
		double b = (a + 0.0)/1000;
		/*System.out.println(Math.pow( ((vA0 + b/pW)/ vA0), -y));
		System.out.println(( vA0 + (m0 - m)/pW)/ vA0);
		System.out.println(( m0 - mW));
		System.out.println(m);*/
		
		iP = p0 * Math.pow((( vA0 + b/pW)/ vA0), -y);

//		System.out.println();
//		System.out.println("Internal Pressure " + iP);
		return iP;
		
	}
	
	/**
	 * @return dM/dt for a given value of t, edit the instance variable
	 */
	public double dM_dt()
	{
		if (iP > oP)
		{
		dMdt = -1* Math.PI * (rNoz * rNoz) * pW * Math.sqrt((2 * (iP - oP ))/pW);
		}
		else dMdt = 0;
//		System.out.println("dM/dt " + dMdt);
		return dMdt;
	}
	/**
	 * @return thrust at a given time, edit the instance variable
	 */
	public double thrust()
	{
		thrust = 0;
//		System.out.println("dm/dt thrust" + dMdt);
		if ((dMdt != 0) && (iP > oP))
		{
		thrust = 2 * Math.PI * (rNoz * rNoz) * (iP - oP) ;
		}
		
//		System.out.println("Thrust " + thrust);
		return thrust;
	}
	
	/**
	 * @return the mass of the rocket at a given time, edit the instance variable
	 */
	public double m()
	{
//		System.out.println("Mass1 " + m);
		if (m > (m0 - mW))
		{
		m += (dMdt * step);
		}
//		System.out.println("Mass2 " + m);
		return m;
	}
	
	
	/**
	 * @return return the acceleration at a given time.
	 * calculate acceleration
	 */
	public double cA()
	{
		a = thrust/m - g + drag()/m; 
//		System.out.println("Acceleration " + a + " thrust/m " + thrust/m + " gravity " + m * g );
		return a;
	}
	
	/**
	 * @return the new velocity, change the value of v
	 */
	public double cV()
	{
		v += a * step;
//		System.out.println("velocity " + v);
		return v;
	}
	
	/**
	 * @return the new height, change the value of H
	 */
	public double cH()
	{
		h += v * step;
//		System.out.println("Height " + h);
		return h;
	}
	
	/**
	 * @return the amount of drag
	 */
	public double drag()
	{
		double drag = -.5 * cD * pA * Math.PI * rBot * rBot * v * Math.abs(v);
		System.out.println("    " + -.5 * cD * pA * Math.PI * rBot * rBot );
		System.out.println("Drag" + drag);
	return drag;
	}
	
	/**
	 *  Increment the time by the step, and recalculate all values
	 */
	public void doStep ()
	{
		System.out.println(thrust + " mass " + m + " acceleration " + a + " velocity " + v + " height " + h);
		t+= step * 100;
		System.out.println("time " + t);
		iPc();
		m();
		dM_dt();
		thrust();
		cA();
		cV();
		cH();
		
	}
	/**
	 * @param args
	 * @param m0 = total weight of rocket (kg)
	 * @param mW = intital amount of water (liters)
	 * @param vB = bottle volume (liters)
	 * @param p0 = intial air pressure (pascals)
	 * @param cD = drag coefficient  ( no unit)
	 * @param step = time step 
	 * @param rBot = bottle radius (m)
	 * @param rNoz = nozzle radius (m)
	 * @param iP = internal pressure (pascals)
	 * @param pW = water density
	 * @param oP = external pressure
	 * @param m = mass at a given time
	 * @param a = acceleration at a given time
	 * @param v = velocity at a given time
	 * @param h = height at a given time
	 * @param pA = air density at a given time

	 general notes: p + letter = density, letter + p = pressure
	 ex: pA = air density, iP = internal pressure
	 */
	public static void main ( String args[])
	{
		RocketMath rocket = new RocketMath(0.76, 0.66, .002, 253312.5, 1,.05, .01);
		// (double m0, double mW, double vB, double p0, double cD, double rBot, double rNoz)
		for (int i = 1; i <= 375; i++)
			rocket.doStep();
		System.out.println(rocket.rNoz);
	}

}