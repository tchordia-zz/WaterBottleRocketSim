package src.GUIpack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 * @author Tanmay
 * Class that plays music on the JavaFX thread. 
 */
public class Music  {
	public static String launchSound = "music/bottlerocketsound.wav";
	public static String tsunami = "music/tsunami.mp3";
	public static String wonder = "music/POL-wonder-place-short.wav";
	public static String rocket = "/BottleRocketFX/Music/rocket.wav";
	public static Thread t = null;
	public static loopRunnable looper = null;
	


	/**
	 * @param fileName Play an audio file stored in the given file
	 * This is used to play a single sound effect, not a continuous music stream.
	 * @return
	 */
	public static boolean playAudio(String fileName) {
		try {
			MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(
					fileName).toURI().toString()));
			mediaPlayer.play();
			
			return true;
		} catch (Exception x) {
			return false;
		}
	}

	/**
	 * Run a coninuous stream of music at the given address, use the preset locations saved as static variable
	 * 
	 * @param filename = filename 
	 */
	public void loopAudio(String filename) {
	
		
		looper = new loopRunnable(filename);
		t = new Thread(looper, "music");
		t.start();
		
		
	}
	
	/**
	 * Stop the loop of myusic
	 */
	public void stopLoop()
	{
		looper.musicPlayer.stop(looper.loop);
	}
	
	

	
	/**
	 * @author Tanmay
	 * A class that implements runnable that runs a continuous stream of music on a different thread
	 */
	public class loopRunnable implements Runnable  
	{
		String f;
		AudioPlayer musicPlayer;
		ContinuousAudioDataStream loop = null;
		public loopRunnable(String filename)
		{
			f = filename;
		}
		
		@Override
		public void run() {
			AudioStream backgroundMusic;
			AudioData musicData;
			musicPlayer = AudioPlayer.player;
			
		
				
					try {
						backgroundMusic = new AudioStream(new FileInputStream(f));
						musicData = backgroundMusic.getData();
						loop = new ContinuousAudioDataStream(musicData);
						musicPlayer.start(loop);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				
			
		}
	}
	
	
	
}