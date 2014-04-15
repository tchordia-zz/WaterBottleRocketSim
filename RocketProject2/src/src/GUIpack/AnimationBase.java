package src.GUIpack;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AnimationBase extends JPanel implements ActionListener
{
	final int circleDiameter = 10;
//	int x=this.getWidth()/2-circleDiameter;
//	int y=this.getHeight()/2-circleDiameter;
	int x1;
	int y1;
	int x2;
	int y2;
	int y3;
	int y4;
	int x;
	int y;
	int frameNumber;
	Image background;
	Timer timer;
	
	public AnimationBase()
	{
		timer = new Timer(50, this);
		timer.start();
		try
		{
			background = ImageIO.read(new File("Untitled.png"));
		}
		catch(IOException ex)
		{
			
		}
		x1=0;
		y1=0;
		x2=-background.getWidth(null);
		y2=background.getHeight(null);
		y3=2*background.getHeight(null);
		y4=3*background.getHeight(null);
		
		y=552;
		x=0;
	}
	
	public void drawFrame(Graphics gr)
	{
		gr.setColor(Color.blue);
		if(x1==this.getWidth())
			x1=x2-background.getWidth(null);
		
		if(x2==this.getWidth())
			x2=x1-background.getWidth(null);
		
		gr.drawImage(background, x1, y1, null);
		gr.drawImage(background, x1, y2, null);
		gr.drawImage(background, x1, y3, null);
		gr.drawImage(background, x1, y4, null);
		
		System.out.println(getHeight()-10);
		
	}
	
	public void paint(Graphics gr)
	{
		super.paint(gr);
		drawFrame(gr);
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		AnimationBase base = new AnimationBase();
		frame.add(base);
		frame.setVisible(true);
		frame.setSize(600,600);
		frame.setTitle("animation test");
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		frameNumber++;
		repaint();
		}
}
