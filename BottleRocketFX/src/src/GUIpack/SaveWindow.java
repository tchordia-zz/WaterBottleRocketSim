package src.GUIpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import mathPack.RocketMath;

public class SaveWindow extends JPanel implements ActionListener {

	CoolButton save = new CoolButton("Save", CoolButton.SMALL);
	JTextField text = new JTextField(10);
	JPanel pane = new JPanel();
	FullRocket rocket;
	String user;
	String savename;
	public SaveWindow(FullRocket r, String user)
	{
		super();
		this.user = user;
		rocket = r.copy();
		//this.setModal(true);
		save.addActionListener(this);
		text.addActionListener(this);
		save.setActionCommand("text");
		//getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLayout(new FlowLayout());
		save.setEnabled(false);
		//text.setPreferredSize();
		save.setActionCommand("Save");
		
		text.setMaximumSize( text.getPreferredSize() );
		Font font = new Font("Verdana", Font.BOLD, 15);
		pane.setBackground(Color.black);
		pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Name your Rocket, then press enter.",TitledBorder.CENTER, TitledBorder.CENTER, font, Color.white));
		pane.add(text);
		setBackground(Color.black);
		add(pane);
		pane.setPreferredSize(new Dimension(400,130));
		add(save);
		setSize(400,200);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		System.out.println(a);
		if (a.equals("Save"))
		{
			System.out.println("Users fools: " + user);
			DataSave.saveAs(user,savename, rocket);
			save.setEnabled(false);
			text.setText("");
		}
		else 
		{
			savename = a;
			if (DataSave.checkExists(user, savename))
					{
						save.setText("Name exists. Click to overwrite");
					}
			if (savename.length()>0)
			{
				save.setEnabled(true);
			}
		}
	}
	public void update(String u)
	{
		user = u;
	}
	public void update(FullRocket u)
	{
		rocket = u;
	}
	public static void main(String[] args)
	{
		SaveWindow l = new SaveWindow(new FullRocket(), "Tanmay" );
		
	}
}
