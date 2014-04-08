package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.print.attribute.standard.JobName;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;

public class ProjectileBall extends JPanel
{
	double frameNumber = 0;
	
	public static double x;
	public static double y;

	public double xInit;
	public double yInit;
	
	static double cosineTheta;
	static double sineTheta;
	static double magnitude;
	
	static boolean timerRunning;
	
	public int windowHeight;
	public int windowWidth;
	
	static AnimationTimer timer;
	
	static Circle circle = new Circle(10, javafx.scene.paint.Color.BLUE);
	static Line xAxis = new Line();
	static Line yAxis = new Line();
	
	static boolean inCircle = false;
	
	public ProjectileBall(int W, int H)
	{
		windowWidth = W;
		windowHeight = H;
		
		x = 10;
		y = 652;

		xInit = x;
		yInit = y;
		
		final JFXPanel fxPanel = new JFXPanel();
		
		fxPanel.setSize(windowWidth, windowHeight);
		
		setLayout(new BorderLayout());
		
		add(fxPanel, BorderLayout.CENTER);
		
		establishTimer();
		
        Platform.runLater(new Runnable() {
            public void run() {
            	Scene scene = createScene();
            	fxPanel.setScene(scene);
//            	timer.start();
            	}
            });
	}
	
	public ProjectileBall()
	{
		x = 0;
		y = getHeight();
		
		x = 0;
		y = 500;
		
		xInit=x;
		yInit=y;
		
		final JFXPanel fxPanel = new JFXPanel();
		
		fxPanel.setSize(600, 600);
		
		add(fxPanel, BorderLayout.CENTER);
		
		establishTimer();
		
        Platform.runLater(new Runnable() {
            public void run() {
            	Scene scene = createScene();
            	fxPanel.setScene(scene);
            	timer.start();
            	}
            });
	}
	
	private static Scene createScene()
	{
		Group root = new Group();
		Scene scene = new Scene(root, Color.WHITE);
		
		circle.setRadius(10);
		circle.setCenterX(x);
		circle.setCenterY(y);
		
		System.out.println(x);
		System.out.println(y);
		
		final Line line = new Line();
		
		line.setStartX(10);
		line.setStartY(652);
		line.setEndX(10);
		line.setEndY(652);
		
		root.getChildren().addAll(line, circle);
		
		scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event)
					{
						if(!timerRunning==true && inCircle==false)
						{
						double height = 652-event.getSceneY();
						double width = event.getSceneX();
						double hypotenuse = Math.sqrt(height*height + width*width);
						
						magnitude = hypotenuse/4;
						cosineTheta = width/hypotenuse;
						sineTheta = height/hypotenuse;
						
						double lineSize = 50;
						
						line.setEndX(cosineTheta*lineSize);
						line.setEndY(-sineTheta*lineSize+652);
						
						System.out.println("Mouse Clicked");
						System.out.println(height);
						System.out.println(width);
						System.out.println(hypotenuse);
						System.out.println(cosineTheta);
						System.out.println(sineTheta);
						System.out.println(sineTheta*lineSize);
						System.out.println(cosineTheta*lineSize);
						}
					}
				});
		
		circle.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event)
					{
						timerRunning=true;
						timer.start();
					}
				}
				);
		circle.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event)
					{
						inCircle=true;
					}
				}
				);
		circle.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event)
					{
						inCircle=false;
					}
				}
				);
		
		return (scene);
	}
	
	private void establishTimer()
	{
		timer = new AnimationTimer()
		{
			@Override
			public void handle(long l)
			{
				for(int i=1; !(i==50); i++)
				{
				frameNumber+=.02;
            	mathClass(frameNumber);
				circle.setTranslateX(x-10);
				circle.setTranslateY(y);
				System.out.println("X value: " + x);
				System.out.println("Y value: " + y);
				System.out.println("Frame Number: " + frameNumber);
				}
			}
		};
	}
	
	//this class should be overridden
	public void mathClass(double t)
	{		
		t /= 12.5;
				
		double vInitialX = magnitude*cosineTheta;
		double accelerationX = 0;
		
		double vInitialY = magnitude*sineTheta;
		double gravity = -10;
	
		x = 10 + (vInitialX*t + accelerationX * t*t);
		y = 0 - (vInitialY*t + gravity * t*t);
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		ProjectileBall ball = new ProjectileBall(600, 600);
		ball.setPreferredSize(new Dimension(700, 700));
		
		frame.setLayout(new BorderLayout());
		frame.setTitle("Projectile Ball");
		frame.add(ball, BorderLayout.CENTER);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}