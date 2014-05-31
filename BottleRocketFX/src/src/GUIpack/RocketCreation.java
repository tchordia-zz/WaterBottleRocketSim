package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class RocketCreation extends JPanel{
	
	RocketSliders2 sliders = new RocketSliders2();
	
	CreateRocket rockett = new CreateRocket();
	FullRocket f = new FullRocket(rockett, null, null);
	SavePanel save = new SavePanel();
	LoadP load = new LoadP();
	String u;
	RocketCreation()
	{		
		setLayout(new GridLayout());
		add(sliders);
		add(rockett);
		add(save);
		add(load);
	}
	
	
	public static void main(String[] args)
	{
		RocketCreation mainPanel = new RocketCreation();
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.white);
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
			System.out.println(": " + Ct.getValue());
			System.out.println("Nz: " + Nz.getValue());
			
			rockett.updateRocket(
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
					Nz.getValue());
		}
	}
	
	private class SavePanel extends SaveWindow
	{
		public SavePanel()
		{
			super(f, RocketF.user);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String a = e.getActionCommand();
			System.out.println(a+"/Saving");
			if (a.equals("Save")) {
				f.g=rockett.getValues();
				System.out.println("wutchu want: " + f.g[0]);
				user=RocketF.user;
				DataSave.saveAs(user, savename, f);
				load.pane.remove(load.combo);
				load.combo = new JComboBox(DataSave.getList(user, "hey"));
				load.pane.add(load.combo);
				save.setEnabled(false);
				text.setText("");
			} else {
				savename = a;
				if (DataSave.checkExists(user, savename)) {
					save.setText("Name exists. Click to overwrite");
				}
				if (savename.length() > 0) {
					save.setEnabled(true);
				}
			}
			
		}
	}
	
	public class LoadP extends LoadPanel
	{
		public LoadP()
		{
			super(RocketF.user);
		}
		public void updateCombo(String user)
		{
			pane.remove(combo);
			combo = new JComboBox(DataSave.getList(user, "hey"));
			pane.add(combo);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String a = e.getActionCommand();
			System.out.println(a);
			user = RocketF.user;
			savename = (String) combo.getSelectedItem();
			if (a.equals("Save"))
			{
				try {
					System.out.println(DataSave.retrieve(user, savename).getCreateRocket().getValues());
					rockett.updateRocket(DataSave.retrieve(user, savename).getCreateRocket().getValues());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else 
			{
					save.setEnabled(true);
			}
		}
	}
}


