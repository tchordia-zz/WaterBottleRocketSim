package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javafx.application.Platform;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import mathPack.RocketMath;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import com.jgoodies.looks.Options;

public class RocketF extends JFrame {
	Music x = new Music();
	Boolean musicon = true;
	public WPanel wpanel = new WPanel();
	public MenuP menubar = new MenuP();
	public JPanel htmlpanel = new JPanel();
	public JPanel pdfpanel = new JPanel();
	public RocketFAnimated targetpanel = new RocketFAnimated();
	HTMLFile html = new HTMLFile("st");
	public RocketCreation rBuilder = new RocketCreation();
	public static RocketMath mRocket;
	// public CPanel cpanel = new CPanel();
	public static String user;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RocketF() {
		super();

		// x.loopAudio(Music.riskyplan);
		UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
		Options.setDefaultIconSize(new Dimension(18, 18));

		// try {
		//
		// UIManager.setLookAndFeel(new PlasticLookAndFeel());
		// } catch (Exception e) {
		// System.err.println("Can't set look & feel:" + e);
		// }
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("Water Bottle Rocket Launch");
		setSize(2000, 1000);

		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// getContentPane().setBackground(Color.cyan);

		add(wpanel);

		setJMenuBar(menubar);

		htmlpanel.add(html);
		htmlpanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
		html.setPreferredSize(new Dimension(getWidth(), getHeight()));

		SwingController controller = new SwingController();

		// Build a SwingViewFactory configured with the controller
		SwingViewBuilder factory = new SwingViewBuilder(controller);

		// Use the factory to build a JPanel that is pre-configured
		// with a complete, active Viewer UI.
		JPanel viewerComponentPanel = factory.buildViewerPanel();

		// add copy keyboard command
		ComponentKeyBinding.install(controller, viewerComponentPanel);

		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController().setAnnotationCallback(
				new org.icepdf.ri.common.MyAnnotationCallback(controller
						.getDocumentViewController()));
		// optional open a document
		controller.openDocument("src/htmlpack/wrocket.pdf");// - See more at:
															// http://www.icesoft.org/JForum/posts/list/20535.page#sthash.cagw5UDN.dpuf
	}

	// public void mInit() {
	// removeAll();
	// // add(cpanel,BorderLayout.NORTH);
	// add(mpanel);
	// repaint();
	// }

	public void removeAll() {
		remove(wpanel);
		// remove(mpanel);
		remove(rBuilder);
		remove(htmlpanel);
		remove(pdfpanel);
		remove(targetpanel);
	}

	public void switchTo(JComponent p) {
		removeAll();
		getContentPane().add(p);
		repaint();
		refFrame(this);
	}

	public void switchUser() {

		wpanel.reset();
		switchTo(wpanel);

		refFrame(this);
		// System.out.println(user);
	}

	public static void main(String[] args) {

		RocketF frame = new RocketF();
		// JFrame j = new JFrame();
		// JEditorPane editorPane;
		//
		// editorPane = new JEditorPane();
		// editorPane.setEditable(false);
		//
		// URL helpURL = null;
		// helpURL =
		// HTMLFile.class.getResource("/com/htmlPack/RocketExplanation.html");
		// //URL("RocketExplanation.html");
		// if (helpURL != null) {
		// try {
		// editorPane.setPage(helpURL);
		// } catch (IOException e) {
		// System.err.println("Attempted to read a bad URL: " + helpURL);
		// }
		// } else {
		// System.err.println("Couldn't find file: TextSamplerDemoHelp.html");
		// }
		//
		// //Put the editor pane in a scroll pane.
		// JScrollPane s = new JScrollPane();
		// s.setVerticalScrollBarPolicy(
		// JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// s.setPreferredSize(new Dimension(250, 145));
		// s.setMinimumSize(new Dimension(10, 10));
		//

	}

	public class WPanel extends WelcomePanel {

		public WPanel() {
			super();
			setSize(getWidth(), getHeight());
		}

		public void reset() {
			pane.remove(launch);
			pane.remove(target);
			pane.remove(builder);
			pane.remove(exp);
			pane.add(tpane);
			pane.setLayout(new BorderLayout());
			tpane.add(j);
			j.requestFocus();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			System.out.println(e.getActionCommand());
			if (e.getActionCommand().equals("launch")) {
				System.out.println(e.getActionCommand());

				// mInit();

			} else if (e.getActionCommand().equals("user")) {
				user = j.getText();
				System.out.println(user);
				// mpanel.updateUser(user);
				rBuilder.load.updateCombo(user);
				pane.remove(tpane);
				pane.setLayout(new GridLayout());
				pane.add(launch, BorderLayout.NORTH);
				pane.add(target, BorderLayout.NORTH);
				pane.add(builder, BorderLayout.NORTH);
				pane.add(exp, BorderLayout.NORTH);
				menubar.updateUsers();
				menubar.updateRocketList();
				repaint();
			} else if (e.getActionCommand().equals("builder")) {
				switchTo(rBuilder);
			} else if (e.getActionCommand().equals("exp")) {
				switchTo(htmlpanel);
				// switchTo(pdfpanel);

			} else if (e.getActionCommand().equals("target")) {
				switchTo(targetpanel);
			}
			pane.repaint();

			try {
				SwingUtilities.getRoot(this).setVisible(true);
			} catch (Exception eve) {
			}
		}

	}

	public static void sleep(long a) {
		try {
			Thread.sleep(a);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void refFrame(Component comp) {
		try {
			SwingUtilities.getRoot(comp).setVisible(true);
		} catch (Exception eve) {
		}

	}

	public static void setRocket(RocketMath r) {
		RocketF.mRocket = r;
	}

	public class HTMLFile extends JScrollPane {
		JEditorPane editorPane;
		URL helpURL = null;

		public HTMLFile(String str) {
			super();
			System.out.println("new html file" + str);
			setPreferredSize(new Dimension(htmlpanel.getWidth(),
					htmlpanel.getHeight()));
			if (str.equals("st")) {
				str = new String("/htmlPack/RocketExplanation.html");
			} else {
				str = str.substring(5);
				System.out.println(str);
			}
			editorPane = new JEditorPane();
			editorPane.setEditable(false);
			setUrl(str);
			// setUrl("/htmlPack/Rocket.html");

			// Put the editor pane in a scroll pane.

			this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			this.setPreferredSize(new Dimension(250, 145));
			this.setMinimumSize(new Dimension(10, 10));

			this.setViewportView(editorPane);
			// this.setViewportView(pdfpanel);

			editorPane.addHyperlinkListener(new HyperlinkListener() {
				@Override
				public void hyperlinkUpdate(HyperlinkEvent hle) {
					if (HyperlinkEvent.EventType.ACTIVATED.equals(hle
							.getEventType())) {
						System.out.println(hle.getURL().toString());
						if (hle.getURL().toString().contains("pdf")) {
							openPdf(hle.getURL().toString());
						} else {
							htmlpanel.remove(html);
							htmlpanel.repaint();
							// sleep(1000);
							html = new HTMLFile(hle.getURL().toString());

							htmlpanel.add(html);
							htmlpanel.repaint();
							htmlpanel.setPreferredSize(new Dimension(
									getWidth(), getHeight()));
							html.setPreferredSize(new Dimension(getWidth(),
									getHeight()));
							switchTo(htmlpanel);
							html.setVisible(true);
						}
					}
				}
			});
		}

		public void openPdf(String filename) {
			try {
				filename = filename.substring(5);
				File pdfFile;
				if (!filename.contains("src")) {
					pdfFile = new File("src" + filename);
					System.out.println("filename is iii " + filename);
				} else {
					pdfFile = new File(filename);
				}
				if (pdfFile.exists()) {

					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().open(pdfFile);
					} else {
						System.out.println("Awt Desktop is not supported!");
					}

				} else {
					System.out.println("File does not exist!");
					System.out.println("Working Directory = "
							+ System.getProperty("user.dir"));
				}

				System.out.println("Done");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void setUrl(String str) {
			helpURL = HTMLFile.class.getResource(str);
			// URL("RocketExplanation.html");
			if (helpURL != null) {
				try {
					editorPane.setPage(helpURL);
				} catch (IOException e) {
					System.err.println("Attempted to read a bad URL: "
							+ helpURL);
				}
			} else {
				System.err
						.println("Couldn't find file: TextSamplerDemoHelp.html");
			}
			repaint();
		}

		public void setUrl(URL url) {
			helpURL = url;
			// URL("RocketExplanation.html");
			if (helpURL != null) {
				try {
					editorPane.setPage(helpURL);
				} catch (IOException e) {
					System.err.println("Attempted to read a bad URL: "
							+ helpURL);
				}
			} else {
				System.err
						.println("Couldn't find file: TextSamplerDemoHelp.html");
			}
			this.setViewportView(editorPane);
			repaint();
		}
	}

	public class SaveDialog extends JOptionPane {
		public SaveWindow s;
		FullRocket f;
		public SaveDialog() {
			super();
			setSize(100,100);
			f = new FullRocket(targetpanel.apanel.rocket,null,null);
			s = new SaveWindow(f, RocketF.user) {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					String a = e.getActionCommand();
//					System.out.println(a);
//					if (a.equals("Save")) {
//						System.out.println("Users fools: " + user);
//						DataSave.saveAs(user, savename, rocket);
//						save.setEnabled(false);
//						text.setText("");
//					} else {
//						savename = a;
//						if (DataSave.checkExists(user, savename)) {
//							save.setText("Name exists. Click to overwrite");
//						}
//						if (savename.length() > 0) {
//							save.setEnabled(true);
//						}
//					}
//				}
			};
			add(s);
			//setModal(true);
			setVisible(true);
		}

	}

	public class MenuP extends RMenu {
		public MenuP() {
			super();
		}

		public void updateUsers() {
			subMenu2.removeAll();
			File usersText = new File("Users", "UsersList");
			Scanner reader = null;
			JMenuItem i;
			try {
				reader = new Scanner(usersText);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			if (usersText.exists()) {
				while (reader.hasNext()) {
					String readin = reader.next();
					System.out.println(readin);
					i = new JMenuItem(readin);
					i.addActionListener(changeUsers);
					subMenu2.add(i);
				}
				subMenu2.add(userbox);
			} else {
				System.out.println("List of Users does not exist");
			}
		}

		public void updateRocketList() {
			String rocket;
			File usersText = new File("Users", user + "array.txt");
			Scanner reader = null;
			JMenuItem i;
			try {
				reader = new Scanner(usersText);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			if (usersText.exists() && reader.hasNext()) {
				subMenu.removeAll();
				while (reader.hasNext()) {
					System.out.println(rocket = reader.next());
					i = new JMenuItem(rocket);
					i.addActionListener(changeRockets);
					subMenu.add(i);
				}
			} else {
				subMenu.removeAll();
				i = new JMenuItem("User has no rockets.");
				subMenu.add(i);
				System.out.println("User has no rockets");
			}
		}

		@Override
		public void userChangeFunction(ActionEvent e) {
			user = e.getActionCommand();
			System.out.println(user);
			updateRocketList();
			rBuilder.load.updateCombo(user);
			updateRocketList();
		}

		@Override
		public void rocketChangeFunction(ActionEvent e) {
			try {
				
				double[] ds = DataSave
						.retrieve(user, e.getActionCommand()).getCreateRocket()
						.getValues();
				
				System.out.println(DataSave
						.retrieve(user, e.getActionCommand()).getCreateRocket()
						.getValues());
				
				rBuilder.rockett.updateRocket(ds);

				targetpanel.loadNewRocket(ds);
				
				RocketSliders.Ln.setValue((int)ds[0]);
				RocketSliders.D.setValue((int)ds[1]);
				RocketSliders.Df.setValue((int)ds[2]);
				RocketSliders.Dr.setValue((int)ds[3]);
				RocketSliders.Lt.setValue((int)ds[4]);
				RocketSliders.Xp.setValue((int)ds[5]);
				RocketSliders.Cr.setValue((int)ds[6]);
				RocketSliders.Ct.setValue((int)ds[7]);
				RocketSliders.S.setValue((int)ds[8]);
				RocketSliders.Lf.setValue((int)ds[9]);
				RocketSliders.R.setValue((int)ds[10]);
				RocketSliders.Xr.setValue((int)ds[11]);
				RocketSliders.Xb.setValue((int)ds[12]);
				RocketSliders.Nz.setValue((int)ds[13]);
		
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			String abc = e.getActionCommand();
			if (e.getActionCommand().equals("Change User")) {
				switchUser();
			} else if (e.getActionCommand().equals("Launch Mode")) {
				// mInit();
			} else if (e.getActionCommand().equals("Save")) {
				
				String b =  JOptionPane.showInputDialog(this, "Enter Savename:");
				DataSave.saveAs (RocketF.user, b, new FullRocket(rBuilder.rockett,null,null));
				updateRocketList();
				
//				rBuilder.load.pane.remove(rBuilder.load.combo);
//				rBuilder.load.combo = new JComboBox(DataSave.getList(user, "hey"));
//				rBuilder.load.pane.add(rBuilder.load.combo);
//				
			} else if (e.getActionCommand().equals("Load")) {
				LoadWindow s = new LoadWindow(user);
			} else if (abc.equals("Target Mode")) {
				switchTo(targetpanel);
			} else if (abc.equals("Info")) {
				switchTo(htmlpanel);
			} else if (abc.equals("Rocket Builder")) {
				switchTo(rBuilder);
			} else if (abc.equals("newuser")) {
				PrintWriter specOut = null;
				File usertxt = new File("Users", "UsersList");
				try {
					usertxt.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					specOut = new PrintWriter(new FileWriter(usertxt, true));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Scanner scan = null;
				boolean nameExists = false;
				try {
					scan = new Scanner(usertxt);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				while (scan.hasNext() && !nameExists) {
					String name = scan.next();
					System.out.println("usernames: " + name);
					nameExists = (name.equals(user));
				}
				if (user != null && !nameExists) {
					specOut.println(user);
				} else {
					System.out.println("Name Exists!!!");
				}
				specOut.close();

				user = userbox.getText();
				System.out.println(user);
				updateUsers();
				updateRocketList();
				// rBuilder.load.updateCombo(user);
			} else if (e.getActionCommand().equals("Toggle Music")) {
				System.out.println("lol");
				if (musicon) {
					x.stopLoop();
					musicon = false;
				} else {
					x.loopAudio(x.current);
					musicon = true;
				}
				// stop background music
			} else if (e.getActionCommand().equals("Change Tune")) {
				if (x.current.equals(x.wonder)) {
					x.stopLoop();
					x.loopAudio(x.numberone);
					System.out.println("numberone");
				} else if (x.current.equals(x.numberone)) {
					x.stopLoop();
					x.loopAudio(x.riskyplan);
					System.out.println("riskyplan");
				} else if (x.current.equals(x.riskyplan)) {
					x.stopLoop();
					x.loopAudio(x.seamemories);
					System.out.println("seamemories");
				} else if (x.current.equals(x.seamemories)) {
					x.stopLoop();
					x.loopAudio(x.wonder);
					System.out.println("wonder");
				}
				// else if (x.current.equals(x.tsunami))
				// {
				// x.stopLoop();
				// x.loopAudio(x.wonder);
				// System.out.println("wonder");
				// }

			}
		}
	}
}