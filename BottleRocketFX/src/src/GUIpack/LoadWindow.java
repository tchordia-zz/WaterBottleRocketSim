package src.GUIpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import mathPack.RocketMath;

public class LoadWindow extends JDialog implements ActionListener {

	CoolButton save = new CoolButton("Load", CoolButton.SMALL);
	JComboBox combo = new JComboBox();
	JPanel pane = new JPanel();
	RocketMath rocket;
	String user;
	String savename;
	public LoadWindow( String user)
	{
		super();
		this.user = user;
		
		this.setModal(true);
		save.addActionListener(this);
		combo.addActionListener(this);
		combo = new JComboBox(DataSave.getList(user, "hey"));
		
		setLayout(new FlowLayout());
		
		//combo.setPreferredSize();
		save.setActionCommand("Save");
		
		combo.setMaximumSize( combo.getPreferredSize() );
		Font font = new Font("Verdana", Font.BOLD, 15);
		pane.setBackground(Color.black);
		 pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Which rocket do you want to load?",TitledBorder.CENTER, TitledBorder.CENTER, font, Color.white));
		pane.add(combo);
		getContentPane().setBackground(Color.black);
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
		savename = (String) combo.getSelectedItem();
		if (a.equals("Save"))
		{
			try {
				RocketF.mRocket =DataSave.retrieve(user, savename).getAngularLaunch();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(RocketF.mRocket.m);
			this.dispose();
		}
		else
		{
				save.setEnabled(true);
		}
	}
	public static void main(String[] args)
	{
		LoadWindow l = new LoadWindow( "Tanmay" );
	}
}
