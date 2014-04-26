package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DesertBlue;

public class WelcomePanel extends JPanel implements ActionListener {
	JPanel pane;
	JPanel tpane = new JPanel();
	JButton start = new CoolButton("<html><p><font size=\"30\" color = \"white\">Welcome to Rocket Sim</font><br> <center><font color = \"white\"> Click to Start</center></font></p></html>" );
	
	JButton launch = new CoolButton("Launch Mode", CoolButton.BIG);
	JButton target = new CoolButton("Target Mode", CoolButton.BIG, CoolButton.CRED);
	JButton builder = new CoolButton("Rocket Builder", CoolButton.BIG, CoolButton.CGREEN);
	JTextField j = new JTextField(20);
	public WelcomePanel()
	{
		Font font = new Font("Verdana", Font.BOLD, 30);
//		start.setBackground(Color.black);
//		start.setForeground(Color.white);
//		start.setFont(font);
//		launch.setBackground(Color.black);
//		launch.setForeground(Color.white);
//		launch.setFont(font);
//		target.setBackground(Color.black);
//		target.setForeground(Color.white);
//		target.setFont(font);
		PlasticLookAndFeel.setPlasticTheme(new DesertBlue());  
		try {
	            
	            UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
	            //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	        } catch (Exception e) {
	            System.err.println("Can't set look & feel:" + e);
	        }
		setLayout(new BorderLayout());
		//setLayout(new FlowLayout());
		pane = ControlPanel.addBack(new JPanel(),"bottleBack.jpg", 1850,1000);
		
		pane.setPreferredSize(new Dimension(2000, 1000));
		//setBackground(Color.cyan);
		
		pane.setLayout(new BorderLayout());
		pane.add(start, BorderLayout.CENTER);
		
		
		start.addActionListener(this);
		start.setActionCommand("start");
		
		ImageIcon img = new ImageIcon("rocketPic.jpeg");
		start.setIcon(img);
		launch.addActionListener(this);
		launch.setActionCommand("launch");
		target.addActionListener(this);
		target.setActionCommand("target");
		add(pane);
		Border bord = BorderFactory.createLineBorder(Color.black, 5);
		setBorder(bord);
		pane.setBackground(Color.black);
		pane.setBorder(bord);
		 j.setActionCommand("user");
		 j.addActionListener(this);
		 j.setFont(font);
		 font = new Font("Verdana", Font.BOLD, 20);
		 tpane.setBackground(Color.black);
		 tpane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Enter Your Name",TitledBorder.CENTER, TitledBorder.CENTER, font, Color.white));
		}
	
	public static void main (String args[])
	{
		JFrame j = new JFrame();
		j.setSize(100,100);
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
			pane.add(tpane, BorderLayout.CENTER);
			tpane.add(j, BorderLayout.CENTER);
			j.requestFocus();
			pane.repaint();
		}
		this.setVisible(true);
		//System.out.println(e.getActionCommand());
		
		
	}
}
