package application;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {
	public static String bottleRocketSound = "bottlerocketsound.wav";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// these two lines play the bottle rocket sound
		playAudio("bottlerocketsound.wav");
		

	}

	public boolean playAudio(String fileName) {
		try {

			MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(fileName)
					.toURI().toString()));
			mediaPlayer.play();
			return true;
		} catch (Exception x) {
			return false;
		}
	}
}
