package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RocketCreation extends JPanel{
	
	RocketCreation()
	{
		setLayout(new BorderLayout());
		RocketSliders sliders = new RocketSliders();
		CreateRocket rocket = new CreateRocket();
		
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
}


