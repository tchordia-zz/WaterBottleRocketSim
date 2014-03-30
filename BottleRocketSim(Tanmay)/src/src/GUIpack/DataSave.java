package src.GUIpack;

/**
 * 
 */

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
import java.util.Scanner;

/**
 * @author Tanmay
 * 
 */
public class DataSave implements Serializable {

	/**
	 * 
	 */

	private static File file;
	
	private static Scanner txtf;
	private static PrintWriter out;
	private static String dirname = "Users";
	private final static File dir = new File(dirname);
	private static r3 rocket;
	public DataSave() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * 
	 */
	public static File init(String user, String extension) throws IOException
	{
		String filename = new String(user + extension);
		System.out.println(filename);
		
		dir.mkdir();
		
		
		
		file = new File(dir, filename);
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			txtf = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
	public static boolean uploadS(String user,  int score, RocketMath2 ro) {
		int high;
		rocket = new r3(ro);
		try {
			file = DataSave.init(user, ".txt");
			if (txtf.hasNext()) {
				try {
					out = new PrintWriter(new FileWriter(file, true));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}

				txtf.next();
				txtf.next();
				high = (int) Integer.parseInt(txtf.next());
				if (score > high) {
					high = score;
					uploadO(user, DataSave.init(user, ".ser"), rocket);
				}
				
				DataSave.init(user,".txt").delete();
				
				

			}
			else
			{
				uploadO(user, file, rocket);
				high= score ;
			}
			DataSave.init(user, ".txt");
			out = new PrintWriter(file);
			out.println("Highest Height: " + high);
			out.println("Last Height: " + score);
			
			out.close();
		} catch (IOException e) {
			System.out.println("COULD NOT LOG!!");

			return false;
		}
		return true;
	}

	private static void uploadO (String user, File file, r3 rocket)
	{
		
		try{
			
			FileOutputStream fout = new FileOutputStream(DataSave.init(user,".ser"));
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(rocket);
			oos.close();
			System.out.println("Done");
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
	}
	
	public static RocketMath2 retrieve(String user) throws IOException, ClassNotFoundException
	{
		DataSave.init(user,".ser");
		FileInputStream fin;
	
		fin = new FileInputStream(file);
		
		ObjectInputStream ois = new ObjectInputStream(fin);
		RocketMath2 ret = (RocketMath2) r3.toRocketMath2((r3) ois.readObject());
		ois.close();
		return ret;
	}
	
	public static class r3 extends RocketMath2 implements Serializable
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = -5128331306530100179L;
		
		public r3(RocketMath2 rocket)
		{
			super(rocket.m0, rocket.mW, rocket.vB, rocket.p0, rocket.cD, rocket.rBot, rocket.rNoz);
		}
		
		public static void printStats(RocketMath2 rocket)
		{
			System.out.println(rocket.m0+ " " + rocket.mW+ " " + rocket.vB+ " " + rocket.p0+ " " + rocket.cD+ " " + rocket.rBot+ " " + rocket.rNoz);
		}
		
		public static RocketMath2 toRocketMath2(r3 r)
		{
			return new RocketMath2(r.m0, r.mW, r.vB, r.p0, r.cD /5000000, r.rBot, r.rNoz); 
		}
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		r3.printStats(new RocketMath2());
		DataSave.uploadS("Tanmay", 7, new RocketMath2());
		
		
		
		r3.printStats(DataSave.retrieve("Tanmay"));
		
		System.out.println();
		
		r3.printStats(new RocketMath2(0.76, 0.66, .003, 253312.5, 1,.05, .01));
		DataSave.uploadS("John", 2, new RocketMath2(0.76, 0.66, .003, 253312.5, 1,.05, .01));
		
		
		
		r3.printStats(DataSave.retrieve("John"));
		

	}

}
