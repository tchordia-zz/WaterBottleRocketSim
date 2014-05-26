package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class RocketCreation extends JPanel{
	
	RocketSliders2 sliders = new RocketSliders2();
	CreateRocket rocket = new CreateRocket();
	
	RocketCreation()
	{
		setLayout(new BorderLayout());		
		add(sliders, BorderLayout.LINE_START);
		add(rocket, BorderLayout.CENTER);
	}
	
	public static void main(String[] args)
	{
		RocketCreation mainPanel = new RocketCreation();
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Button Panel Example");
		frame.setSize(new Dimension(600,600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel, BorderLayout.PAGE_START);
		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
	}
	
	private class RocketSliders2 extends RocketSliders
	{
		@Override
		public void stateChanged(ChangeEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("Ln: " + (Ln.getValue()));
			System.out.println("D: " + D.getValue());
			System.out.println("Df: " + Df.getValue());
			System.out.println("Dr: " + Dr.getValue());
			System.out.println("Xp: " + Xp.getValue());
			System.out.println("Cr: " + Cr.getValue());
			System.out.println("Ct: " + Ct.getValue());
			
			rocket.updateRocket(
					Ln.getValue(),
					D.getValue(),
					Df.getValue(),
					Dr.getValue(),
					Lt.getValue(),
					Xp.getValue(),
					Cr.getValue(),
					Ct.getValue(),
					S.getValue(),
					Lf.getValue(),
					R.getValue(),
					Xr.getValue(),
					Xb.getValue(),
					2);
		}
	}
}


