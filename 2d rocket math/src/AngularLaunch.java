public class AngularLaunch extends RocketMath {
	RocketMath rocket;
	double angle;
	double accx;
	double accy;
	static double thrust;
	double drag;
	double vx;
	double oldvx=0;
	double vy;
	double oldvy=0;
	double x;
	double oldx = 0;
	static double y;
	double oldy = 0;

	public AngularLaunch(double m0, double mW, double vB, double p0, double cD,
			double rBot, double rNoz, double angle) {
		 super(m0, mW, vB, p0, cD, rBot, rNoz);
		
		this.angle = angle;
	}

	// methods to be used
	// drag, thrust
	// since each step is 1 second i didn't bother putting that in
	public void calculateXAcc() {
		thrust = this.thrust();
		if (vx>=0)
			drag = this.drag();
		else
			drag = 0;
		accx = (thrust * Math.cos(angle * Math.PI / 180) - drag
				* Math.cos(angle * Math.PI / 180))
				/ this.m();
	}

	public void calculateYAcc() {
		thrust = this.thrust();
		drag = this.drag();
		accy = (thrust * Math.sin(angle * Math.PI / 180) - drag
				* Math.sin(angle * Math.PI / 180) - this.m() * 9.81)
				/ this.m();
	}

	public void calculateVx() {
		vx = oldvx + accx;
		oldvx = vx;
	}

	public void calculateVy() {
		vy = oldvy + accy;
		oldvy = vy;
	}

	public void newFlightAngle() {
		angle = Math.atan(accy / accx) * 180 / Math.PI;
	}

	public void xpos() {
		x = oldx+vx;
		oldx = x;
	}

	public void ypos() {
		y = oldy+vy;
		oldy = y;
	}
	public void doStepThrust()
	{
		super.doStep();
		this.calculateXAcc();
		this.calculateYAcc();
		this.calculateVx();
		this.calculateVy();
		newFlightAngle();
		this.xpos();
		this.ypos();
		System.out.println(t+" Cartesian Coordinates: ("+x+", "+y+") " + "Angle: "+ angle);
	}
	// when thrust = 0
	// make 2d motion based on velocity
	// cartesian coordinates just for kicks
	public static void main(String args[]) {
		AngularLaunch rocket = new AngularLaunch(0.76, 0.66, 2, 253312.5, 1, .05, .01, 60);
		// (double m0, double mW, double vB, double p0, double cD, double rBot, double rNoz)
		rocket.doStepThrust();
		for (int i = 0; i<375 && y>0  ; i++ )
		{
			rocket.doStepThrust();
			//RocketMath.printStats(rocket);
		}
		
	
	}
}
