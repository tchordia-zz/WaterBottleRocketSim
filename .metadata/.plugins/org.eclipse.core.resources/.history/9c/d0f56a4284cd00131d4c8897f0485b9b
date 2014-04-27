package src.GUIpack;

/**
 * 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Tanmay
 * 
 */
public class DataSave2 implements Serializable {

	/**
	 * 
	 */

	private static File filetxt;
	private static File fileser;
	private static Scanner txtf;
	private static PrintWriter out;
	private static String dirname = "Users";
	private final static File dir = new File(dirname);
	private static r3 rocket;
	static boolean cl;
	static boolean sleep= true;
	public DataSave2() {
		// TODO Auto-generated constructor stub
	}

	public static void setup (String user, String savename)
	{
		String filename = new String(user + "array.txt" );
		dir.mkdir();
		filetxt = new File(dir,filename);
		try {
			filetxt.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			txtf = new Scanner(filetxt);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filename = new String(user + ":" + savename + ".ser");
		 fileser = new File(dir, filename);
		 try {
				filetxt.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public static boolean saveAs(String user, String savename, RocketMath ro) {
		
		rocket = new r3(ro);
		DataSave2.setup(user, savename);
		try
		{
			
			if (txtf.hasNext()) 
			{
				try {
					out = new PrintWriter(new FileWriter(filetxt, true));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}

				
				
				
				
				if (checkExists(user, savename))
				{
					
				}
				
				out.println(savename);
				System.out.println("entered if");
				out.close();
			} 
			else
			{
				out=new PrintWriter(filetxt);
				out.println(savename);
				System.out.println("didnt enter if");
				out.close();
			}
			uploadO( rocket);

		} catch (IOException e) {
			System.out.println("COULD NOT LOG!!");

			return false;
		}
		return true;
	}
	public static boolean checkExists(String user, String savename)
	{
		
		DataSave2.setup(user, savename);
		while (txtf.hasNext()) {
			if (txtf.next().equals(savename)) {
				// TODO code if the save name already exists
				System.out.println("Already exists!");
				return true;
			}
		}
		return false;
	}
	
	public static String[] getList(String user, String savename)
	{
		ArrayList<String> list = new ArrayList();
		DataSave2.setup(user, savename);
		while (txtf.hasNext()) {
			
			String a = txtf.next();
			if (!list.contains(a))
			{
				list.add(a);
				System.out.println(a);
			}
		}
	//	return false;
		return (String[]) list.toArray(new String[list.size()]);
	
	}
	private static void uploadO(r3 rocket) {

		try {

			FileOutputStream fout = new FileOutputStream(fileser);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(rocket);
			oos.close();
			System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RocketMath retrieve(String user, String savename) throws IOException,
			ClassNotFoundException {
		DataSave2.setup(user, savename);
		FileInputStream fin;
		System.out.println(fileser);
		try
		{
		fin = new FileInputStream(fileser);
		
		ObjectInputStream ois = new ObjectInputStream(fin);
		RocketMath ret = (RocketMath) r3.toRocketMath((r3) ois.readObject());
		ois.close();
		return ret;
		}
		catch (FileNotFoundException e)
		
		{
			System.out.println("File not Found");
			return null;
		}
		
		
		
	}
	private static class lFrame extends JDialog implements ActionListener {
	    JPanel mainPanel = new JPanel();
		public lFrame(String name) {
	    	  super();
	    	  Toolkit tk = Toolkit.getDefaultToolkit();
		  		Dimension screenSize = tk.getScreenSize();
		  		final int WIDTH = screenSize.width;
		  		final int HEIGHT = screenSize.height;
		  		// Setup the frame accordingly
		  		// This is assuming you are extending the JFrame //class
		  		
		  		this.setLocation(WIDTH / 3, HEIGHT / 3 - 100);
	    	 
	    	
	    	  try {
	  			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	  		} catch (ClassNotFoundException | InstantiationException
	  				| IllegalAccessException | UnsupportedLookAndFeelException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	    	  
	    	
	  		
	    	//add(mainPanel);
	  	//	setModal(true);
	    //	setUndecorated(true);

	  		
	  		
	    	  
	  		CoolButton yes = new CoolButton("Yes", CoolButton.SMALL);
	  		mainPanel.setBackground(Color.black);
	  		CoolButton no = new CoolButton("No", CoolButton.SMALL);
	  		JLabel ll = new JLabel("A file of this name already exists. Would you like to overwrite it?     ");
	  		ll.setFont(new Font(ll.getFont().getName(), Font.PLAIN, 15));
	  		ll.setForeground(Color.white);
	  		mainPanel.add(ll);
	  		mainPanel.add(yes);
	  		mainPanel.add(no);
	  		add(mainPanel);
	  		
	  		yes.addActionListener(this);
	  		no.addActionListener(this);
	  		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  		setSize(550,200);
	  		setVisible(true);
	  		requestFocus();
	  		setModal(true);
	  		repaint();
	      }

	      @Override
	      public void actionPerformed(ActionEvent e) {
	         cl = true;
	    	  if (e.getActionCommand().equals("No"))
	         {
	        	 cl = false;
	        	 
	         }
	    	  sleep = false;
	         this.dispose();
	      }
	   }
	public static class r3 extends RocketMath implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5128331306530100179L;

		public r3(RocketMath rocket) {
			super(rocket.m0, rocket.mW, rocket.vB, rocket.p0, rocket.cD,
					rocket.rBot, rocket.rNoz);
		}

		public static void printStats(RocketMath rocket) {
			System.out.println(rocket.m0 + " " + rocket.mW + " " + rocket.vB
					+ " " + rocket.p0 + " " + rocket.cD + " " + rocket.rBot
					+ " " + rocket.rNoz);
		}

		public static RocketMath toRocketMath(r3 r) {
			return new RocketMath(r.m0, r.mW, r.vB, r.p0, r.cD,
					r.rBot, r.rNoz);
		}
	}

	public static void main(String[] args) throws ClassNotFoundException,
			IOException {

		// r3.printStats(new RocketMath());
		// DataSave.uploadS("Tanmay", 7, new RocketMath());
		//
		//
		//
		// r3.printStats(DataSave.retrieve("Tanmay"));
		//
		// System.out.println();
		//
		// r3.printStats(new RocketMath(0.76, 0.66, .003, 253312.5, 1,.05,
		// .01));
		// DataSave.uploadS("John", 2, new RocketMath(0.76, 0.66, .003,
		// 253312.5, 1,.05, .01));
		//
		//
		//
		// r3.printStats(DataSave.retrieve("John"));

		DataSave2.saveAs("Johnf", "tes", new RocketMath());
		System.out.println();
		System.out.println("herro");
		//r3.printStats(DataSave.retrieve("Jfohn", "tehs"));
		System.exit(0);
	}
}
//
//	/**
//	 * @param args
//	 * @throws IOException
//	 * 
//	 */
//	public static File init(String user, String extension) throws IOException {
//		String filename = new String(user + extension);
//		System.out.println(filename);
//
//		dir.mkdir();
//
//		filetxt = new File(dir, filename);
//
//		try {
//			filetxt.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			txtf = new Scanner(filetxt);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return filetxt;
//	}
//
//	public static boolean uploadS(String user, int score, RocketMath ro) {
//		int high;
//		rocket = new r3(ro);
//		try {
//			filetxt = DataSave.init(user, ".txt");
//			if (txtf.hasNext()) {
//				try {
//					out = new PrintWriter(new FileWriter(filetxt, true));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					return false;
//				}
//
//				txtf.next();
//				txtf.next();
//				high = (int) Integer.parseInt(txtf.next());
//				if (score > high) {
//					high = score;
//					DataSave.init(user, ".ser");
//					//uploadO(user, rocket);
//				}
//
//				DataSave.init(user, ".txt").delete();
//
//			} else {
//				//uploadO(user, rocket);
//				high = score;
//			}
//			DataSave.init(user, ".txt");
//			out = new PrintWriter(filetxt);
//			out.println("Highest Height: " + high);
//			out.println("Last Height: " + score);
//
//			out.close();
//		} catch (IOException e) {
//			System.out.println("COULD NOT LOG!!");
//
//			return false;
//		}
//		return true;
//	}
//
//}
