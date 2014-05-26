package src.GUIpack;

import java.awt.BorderLayout;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RotateTest {
	
	Group rotatingBoxes;

	RotateTest()
	{
		Rectangle rect1 = new Rectangle(0, 0, 20, 20);
		Rectangle rect2 = new Rectangle(20, 0, 20, 20);
		Rectangle rect3 = new Rectangle(10, 20, 20, 20);
		rotatingBoxes.getChildren().addAll(rect1, rect2, rect3);
	}
	
}
