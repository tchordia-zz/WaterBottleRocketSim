package src.GUIpack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class WelcomePanel extends JPanel implements ActionListener {
	JPanel pane;
	JButton start = new JButton("Start");
	JButton launch = new JButton("Launch Mode");
	JButton target = new JButton("Target Mode");
	public WelcomePanel()
	{
		
		
		pane = SliderPanel.initBack(new JPanel(),"background.jpeg");
		pane.add(start);
		
		start.addActionListener(this);
		start.setActionCommand("start");
		launch.addActionListener(this);
		launch.setActionCommand("launch");
		target.addActionListener(this);
		target.setActionCommand("target");
		add(pane);
		Border bord = BorderFactory.createLineBorder(Color.black, 5);
		setBorder(bord);
		}
	
	public static void main (String args[])
	{
		JFrame j = new JFrame();
		j.add(new WelcomePanel());
		j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("start"))
		{
			pane.remove(start);
			pane.add(launch);
			pane.add(target);
		}
		System.out.println(e.getActionCommand());
		
		
	}
}
