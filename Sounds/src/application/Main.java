package application;

import java.awt.Toolkit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class Main extends Application {

    private  final AudioClip ALERT_AUDIOCLIP = new AudioClip("http://ragejunkie.com/wp-content/uploads/2014/01/DVBBS-Borgeous-Tsunami-Jay-Cosmic-Remix.mp3");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        Scene scene = new Scene(root, 300, 250);

        Button btn = new Button();
        btn.setText("Play (My Sound)");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ALERT_AUDIOCLIP.play();
            }
        });

       

        root.getChildren().add(btn);
       

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}