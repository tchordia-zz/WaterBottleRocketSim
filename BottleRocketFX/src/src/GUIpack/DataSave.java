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

import javafx.scene.Group;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class should always be used Statically, and should never actually be instantiated.
 * @author Tanmay
 * 
 */
public class DataSave implements Serializable {

	/**
	 * 
	 */

	private static File filetxt;
	private static File fileser;
	private static File usertxt;
	private static Scanner txtf;
	private static PrintWriter out;
	private static String dirname = "Users";
	private static File dir = new File(dirname);
	private static FullRocket rocket;




	/**
	 * Creates a file 
	 * @param user
	 * @param savename
	 */
	public static void setup(String user, String savename) {
		String filename = new String(user + "array.txt");
		PrintWriter specOut = null;
		dir.mkdir();
		filetxt = new File(dir, filename);
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
			fileser.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filename = new String("UsersList");
		usertxt = new File(dir, filename);
		try{
			usertxt.createNewFile();
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		try {
			specOut = new PrintWriter(new FileWriter(usertxt, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scanner scan = null;
		boolean nameExists = false;
		try {
			scan = new Scanner(usertxt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scan.hasNext() && !nameExists)
		{
			String name = scan.next();
			System.out.println("usernames: " + name);
			nameExists = (name.equals(user));
		}
		if (user!=null && !nameExists)
		{
			specOut.println(user);
		}		else
		{
			System.out.println("Name Exists!!!");
		}
		specOut.close();
	}

	public static boolean saveAs(String user, String savename, FullRocket ro) {

		rocket = ro;
		DataSave.setup(user, savename);
		try {

			if (txtf.hasNext()) {
				try {
					out = new PrintWriter(new FileWriter(filetxt, true));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}

				if (checkExists(user, savename)) {
				}
				out.println(savename);
				System.out.println("entered if");
				out.close();
			} else {
				out = new PrintWriter(filetxt);
				out.println(savename);
				System.out.println("didnt enter if");
				out.close();
			}
			uploadO(rocket);

		} catch (IOException e) {
			System.out.println("COULD NOT LOG!!");

			return false;
		}
		return true;
	}

	public static boolean checkExists(String user, String savename) {

		DataSave.setup(user, savename);
		while (txtf.hasNext()) {
			if (txtf.next().equals(savename)) {
				// TODO code if the save name already exists
				System.out.println("Already exists!");
				return true;
			}
		}
		return false;
	}

	/**
	 * @param user The user that wants to access data
	 * @param savename 
	 * @return The list of things that the user has saved.
	 */
	public static String[] getList(String user, String savename) {
		ArrayList<String> list = new ArrayList();
		DataSave.setup(user, savename);
		while (txtf.hasNext()) {
			String a = txtf.next();
			if (!list.contains(a)) {
				list.add(a);
				System.out.println(a);
			}
		}
		
		return (String[]) list.toArray(new String[list.size()]);

	}

	/**
	 * This method actually saves the FullRocket object into a serializeable file (.ser). Meant to be called only by uploadS
	 * @param rocket the FullRocket object that you want to save
	 */
	private static void uploadO(FullRocket rocket) {

		try {

			FileOutputStream fout = new FileOutputStream(fileser);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(rocket);
			oos.close();
			//System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the FullRocket object in the file saved under the name of the user. 
	 * @param user 
	 * @param savename
	 * @return the Full Rocket Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static FullRocket retrieve(String user, String savename)
			throws IOException, ClassNotFoundException {
		DataSave.setup(user, savename);
		FileInputStream fin;
		System.out.println("Please do not do this: "+fileser);
		try {
			fin = new FileInputStream(fileser);

			ObjectInputStream ois = new ObjectInputStream(fin);
			FullRocket ret = (FullRocket)( ois.readObject());
			ois.close();
			return ret;
		} catch (FileNotFoundException e)

		{
			System.out.println("File not Found");
			return null;
		}

	}


	public static void main(String[] args) throws ClassNotFoundException,
			IOException {

		// FullRocket.printStats(new RocketMath());
		// DataSave.uploadS("Tanmay", 7, new RocketMath());
		//
		//
		//
		// FullRocket.printStats(DataSave.retrieve("Tanmay"));
		//
		// System.out.println();
		//
		// FullRocket.printStats(new RocketMath(0.76, 0.66, .003, 253312.5, 1,.05,
		// .01));
		// DataSave.uploadS("John", 2, new RocketMath(0.76, 0.66, .003,
		// 253312.5, 1,.05, .01));
		//
		//
		//
		// FullRocket.printStats(DataSave.retrieve("John"));

	//	DataSave.saveAs("Johnf", "tes", new FullRocket());
		System.out.println();
		System.out.println("herro");
		// FullRocket.printStats(DataSave.retrieve("Jfohn", "tehs"));
		System.exit(0);
	}
}
//
// /**
// * @param args
// * @throws IOException
// *
// */
// public static File init(String user, String extension) throws IOException {
// String filename = new String(user + extension);
// System.out.println(filename);
//
// dir.mkdir();
//
// filetxt = new File(dir, filename);
//
// try {
// filetxt.createNewFile();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// try {
// txtf = new Scanner(filetxt);
// } catch (FileNotFoundException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return filetxt;
// }
//
// public static boolean uploadS(String user, int score, RocketMath ro) {
// int high;
// rocket = new r3(ro);
// try {
// filetxt = DataSave.init(user, ".txt");
// if (txtf.hasNext()) {
// try {
// out = new PrintWriter(new FileWriter(filetxt, true));
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// return false;
// }
//
// txtf.next();
// txtf.next();
// high = (int) Integer.parseInt(txtf.next());
// if (score > high) {
// high = score;
// DataSave.init(user, ".ser");
// //uploadO(user, rocket);
// }
//
// DataSave.init(user, ".txt").delete();
//
// } else {
// //uploadO(user, rocket);
// high = score;
// }
// DataSave.init(user, ".txt");
// out = new PrintWriter(filetxt);
// out.println("Highest Height: " + high);
// out.println("Last Height: " + score);
//
// out.close();
// } catch (IOException e) {
// System.out.println("COULD NOT LOG!!");
//
// return false;
// }
// return true;
// }
//
// }
