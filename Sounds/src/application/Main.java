package application;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {

	

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	// these two lines play the bottle rocket sound
    	MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("bottlerocketsound.wav").toURI().toString()));
    	mediaPlayer.play();
    	
        
    }
}