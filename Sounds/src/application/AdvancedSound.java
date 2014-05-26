package application;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayerBuilder;
import javafx.stage.Stage;

public class AdvancedSound extends Application {

	private static final Media CLICK_AUDIOCLIP = new Media(
			new File("music.wav").toURI().toString());
	private static final Media BACKGROUND_MEDIA = new Media(new File(
			"music.wav").toURI().toString());

	private MediaPlayerBuilder builder;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);

		MediaPlayer player = new MediaPlayer(this.BACKGROUND_MEDIA);
		
			player.play();
			System.out.println(this.BACKGROUND_MEDIA.getDuration());

		
	}
}