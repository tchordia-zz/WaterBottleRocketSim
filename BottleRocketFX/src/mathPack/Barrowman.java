package mathPack;

import src.GUIpack.CreateRocket;
import javafx.scene.Group;

/**
 * barrowman eqns: calcs center of pressure / volume
 * 
 * @author Sahil
 * 
 */
public class Barrowman {
	double ln;
	double d;
	double df;
	double dr;
	double lt;
	double xp;
	double cr;
	double ct;
	double s;
	double lf;
	double r;
	double xr;
	double xb;
	double n;
	double x; // distance of center of pressure from nose tip
	double cnn = 2; // hardcoded for cone
	double cnt;
	double cnf;
	double xn;
	double xt;
	double xf;
	double volume;
	public static final String semiCircle = "s";
	public final static String parabola = "p";
	public static final String square = "sq";
	public static final String cone = "c";
	public static final double conversionFactor = (1.0) / 200;
	double cft = conversionFactor;
	double centerOfMass;
	String conetype;

	/**
	 * cnn is hardcoded btw LOL
	 * 
	 * @param ln
	 *            length of nose
	 * @param d
	 *            diameter at base of nose
	 * @param df
	 *            diameter at front of transition
	 * @param dr
	 *            diameter at rear of transition
	 * @param lt
	 *            length of transition
	 * @param xp
	 *            distance from tip of nose to front of transition
	 * @param cr
	 *            fin root chord
	 * @param ct
	 *            fin tip chord
	 * @param s
	 *            fin semispan
	 * @param lf
	 *            length of fin mid-chord line
	 * @param r
	 *            radius of body at aft end
	 * @param xr
	 *            distance between fin root leading edge and fin tip leading
	 *            edge parallel to body
	 * @param xb
	 *            distance from nose tip to fin root chord leading edge
	 * @param n
	 *            number of fins
	 */
	public Barrowman(double ln, double d, double df, double dr, double lt,
			double xp, double cr, double ct, double s, double lf, double r,
			double xr, double xb, double n, String conetype) {
		this.ln = ln;
		xn = ln * .666;// this is intended for cone
		this.d = d;
		this.df = df;
		this.dr = dr;
		this.lt = lt;
		this.xp = xp;
		this.cr = cr;
		this.ct = ct;
		this.s = s;
		this.lf = lf;
		this.r = r;
		this.xr = xr;
		this.xb = xb;
		this.n = n;
		this.conetype = conetype;

	}

	public Barrowman(CreateRocket r) {
		Group a = r.getRocket();
		this.ln = r.Ln * cft;
		xn = ln * .666;// this is intended for cone
		this.d = r.D * cft;
		this.df = r.Df * cft;
		this.dr = r.Dr * cft;
		this.lt = r.Lt* cft;
		this.xp = r.Xp* cft;
		this.cr = r.Cr* cft;
		this.ct = r.Ct* cft;
		this.s = r.S* cft;
		this.lf = r.Lf* cft;
		this.r = r.R* cft;
		this.xr = r.Xr* cft;
		this.xb = r.Xb* cft;
		this.n = r.N* cft;
		this.conetype = Barrowman.cone;

	}

	private double calcCNT() {
		cnt = 2 * (Math.pow((dr / d), 2) - Math.pow(df / d, 2));
		return cnt;
	}

	private double calcXT() {
		xt = xp + lt / 3 * (1 + (1 - df / dr) / (1 - Math.pow(df / dr, 2)));
		return xt;
	}

	private double calcCNF() {
		cnf = (1 + r / (s + r)) * (4 * n * Math.pow(s / d, 2))
				/ (1 + Math.sqrt(1 + Math.pow(2 * lf / (cr + ct), 2)));
		return cnf;
	}

	private double calcCF() {
		xf = xb + xr / 3 * (cr + 2 * ct) / (cr + ct) + 1 / 6
				* ((cr + ct) - (cr * ct / (cr + ct)));
		return xf;
	}

	/**
	 * call this method to get center of pressure
	 * 
	 * @return x (distance from nosetip)
	 */
	public double centerOfPressure() {
		calcCNT();
		calcXT();
		calcCNF();
		calcCF();
		x = (cnn * xn + cnt * xt + cnf * xf) / (cnn + cnt + cnf);
		return x;
	}

	/**
	 * call this method to get volume of rocket (includes the entire thing from
	 * top cylinder, transition, and bottom body
	 * 
	 * @return volume
	 */
	public double volume() {
		double v1 = Math.PI * d / 2 * d / 2 * (xp - ln);
		double v2 = Math.PI * lt / 3
				* (df / 2 * df / 2 + df / 2 * dr / 2 + dr / 2 * dr / 2);
		double v3 = Math.PI * dr * dr * (xb - xp - lt + cr);// includes all the
		System.out.println(dr + " " + df + " " +d + " " +cr + " "); // way to bottom of
		// nozzle
		volume = v1 + v2 + v3;
		return volume;
	}

	public double dragC() {
		if (conetype == this.cone)
			return .5; // cone
		if (conetype == this.parabola)
			return .04; // parabolic
		if (conetype == this.square)
			return 1.15; // square
		if (conetype == this.semiCircle)
			return .42; // sphere
		else
			return 1;
	}

	public double centerOfMass() {
		this.centerOfMass = (xb + xr + ct) * 2 / 3;
		return centerOfMass;
	}

	public boolean stabilityCheck() {

		return this.centerOfPressure() > this.centerOfMass;
	}

	public RocketMath returnLoadedRocket(double m0, double mW, double vB,
			double p0, double rNoz) {

		return new RocketMath(m0, mW, volume(), p0, dragC(), d / 2, rNoz); // what
																			// do
																			// i
																			// even
																			// do
																			// here
	}
}
