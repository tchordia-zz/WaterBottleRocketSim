/**
 * 
 */
package src.GUIpack;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * @author Tanmay
 *
 */
public class CoolButton extends JButton {
public static final int BIG = 1;
public static final int SMALL = 2;
public static final Color CBLUE = new Color(23, 36, 176);
public static final Color CRED = new Color(181, 34, 37 );
public static final Color CGREEN = new Color(50, 145,113);
	/**
	 * 
	 */
	public CoolButton(String text) {
		super(text);
		setBackground(Color.black);
	}

	/**
	 * @param icon
	 */
	public CoolButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 */
	public CoolButton(String text, int type) {
		super(text);
		
		if (type == BIG)
		{
			Font font = new Font("Verdana", Font.BOLD, 30);
			setBackground(CBLUE);
			setForeground(Color.white);
			setFont(font);
		}
		else if (type == SMALL)
		{
			Font font = new Font("Verdana", Font.BOLD, 15);
			setBackground(CBLUE);
			setForeground(Color.white);
			setFont(font);
		}
	}
	
	public CoolButton(String text, int type, Color color) {
		super(text);
	//	setBorder(null);
		setBorderPainted(false);
		if (type == BIG)
		{
			Font font = new Font("Verdana", Font.BOLD, 30);
			setBackground(color);
			setForeground(Color.white);
			setFont(font);
		}
		else if (type == SMALL)
		{
			Font font = new Font("Verdana", Font.BOLD, 15);
			setBackground(color);
			setForeground(Color.white);
			setFont(font);
		}
	}

	/**
	 * @param a
	 */
	public CoolButton(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param icon
	 */
	public CoolButton(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}

}
