package application;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// these two lines play the bottle rocket sound
		playAudio("rocket.wav");

	}

	public void playAudio(String fileName) {
		try {

			MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(
					fileName).toURI().toString()));
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.play();

		} catch (Exception x) {
			System.out.println("bad file name");
		}
	}
}