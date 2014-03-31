package src.GUIpack;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScrollingScreenTest extends JPanel
{
	
	public ScrollingScreenTest()
	{
		super();
		setVisible(true);
	}
	
	
	@Override
	public void paint(Graphics gr)
	{
		super.paint(gr);
		gr.setColor(Color.blue);
		gr.fillOval(getWidth()/2-10, getHeight()/2-10, 10, 10);
	}
	
	
	public static void main(String[] args)
	{
		ScrollingScreenTest screen = new ScrollingScreenTest();
		JFrame frame = new JFrame();
		frame.add(screen);
		frame.setVisible(true);
		frame.setSize(600,600);
	}
}