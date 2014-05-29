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

public class Music  {
	public static String launchSound = "music/bottlerocketsound.wav";
	public static String tsunami = "music/tsunami.mp3";
	public static String wonder = "music/POL-wonder-place-short.wav";
	public static String rocket = "/BottleRocketFX/Music/rocket.wav";
	public static Thread t = null;
	public static loopRunnable looper = null;
	


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

	public void loopAudio(String filename) {
	
		
		looper = new loopRunnable(filename);
		t = new Thread(looper, "music");
		t.start();
		
		
	}
	public void stopLoop()
	{
		looper.musicPlayer.stop(looper.loop);
	}
	@SuppressWarnings("deprecation")
	public void stopMusic()
	{
		if (t!= null)
		{
			t.stop();
		}
	}
	

	
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