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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;

public class ProjectileBall extends JPanel
{
	static double frameNumber = 0;
	
	public static double x;
	public static double y;

	public static double xInit;
	public static double yInit;
	
	static double cosineTheta;
	static double sineTheta;
	static double magnitude;
	
	static boolean timerRunning;
	
	private static int windowHeight;
	private static int windowWidth;
	
	public AngularLaunch rocket2;
	
	static AnimationTimer timer;
	
	static Circle circle = new Circle(10, javafx.scene.paint.Color.web("#3D9AD1"));
	
	static Line line;
	
	private boolean intersectTest;
	
	static boolean inCircle = false;
	
	private Rectangle square;
	
	public ProjectileBall(int W, int H, AngularLaunch launch, SliderPanel panel)
	{
		windowWidth = W;
		windowHeight = H;
		
		x = 10;
		y = H+52;

		xInit = x;
		yInit = y;
		
		rocket2 = launch;
		
		final JFXPanel fxPanel = new JFXPanel();
		
		fxPanel.setSize(windowWidth, windowHeight);
		
		setLayout(new BorderLayout());
		
		add(fxPanel, BorderLayout.CENTER);
		
		establishTimer();
		
        Platform.runLater(new Runnable() {
            public void run()
            	{
            	Scene scene = createScene();
            	fxPanel.setScene(scene);
            	}
            });
	}
	public ProjectileBall(int W, int H)
	{
		windowWidth = W;
		windowHeight = H;
		
		x = 10;
		y = H+52;

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
            	}
            });
	}
	public ProjectileBall()
	{
		windowWidth = 600;
		windowHeight = 600;
		
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
            	}
            });
	}
	
	public void resetBall()
	{
		timer.stop();
		timerRunning=false;
		circle.setTranslateX(0);
		circle.setTranslateY(0);
		x=xInit;
		y=yInit;
		frameNumber=0;
	}
	
	public void angleAdjust(MouseEvent event)
	{
		double height = yInit-event.getSceneY();
		double width = event.getSceneX();
		double hypotenuse = Math.sqrt(height*height + width*width);
		
		magnitude = hypotenuse/4;
		cosineTheta = width/hypotenuse;
		sineTheta = height/hypotenuse;
		
		double lineSize = 50;
		
		line.setEndX(cosineTheta*lineSize);
		line.setEndY(-sineTheta*lineSize+yInit);
		
		System.out.println("Mouse Clicked");
		System.out.println(height);
		System.out.println(width);
		System.out.println(hypotenuse);
		System.out.println("Cosine Theta: "+cosineTheta);
		System.out.println("Sine Theta: "+sineTheta);
		System.out.println("Angle sine: "+Math.asin(sineTheta));
		System.out.println("Angle cosine: "+Math.acos(cosineTheta));
		
	}
	
	private Scene createScene()
	{
		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.WHITE);
		
		circle.setRadius(10);
		circle.setCenterX(xInit);
		circle.setCenterY(yInit);
		
		square = new Rectangle(50, 50, javafx.scene.paint.Color.web("#FF6E40"));
		
		square.setX(xInit+400);
		square.setY(yInit-40);
				
		System.out.println(x);
		System.out.println(y);
		
		line = new Line();
		
		line.setStroke(javafx.scene.paint.Color.web("#03436A"));
		line.setStartX(10);
		line.setStartY(yInit);
		line.setEndX(10);
		line.setEndY(yInit);
		
		root.getChildren().addAll(line, circle, square);
		
		scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event)
					{
						if(!timerRunning==true && inCircle==false)
						{
						angleAdjust(event);
						}
					}
				});

		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
				{

					public void handle(MouseEvent event)
					{
						if(timerRunning==true && inCircle==false /*&& (x>windowWidth || x<0 || y>windowHeight || y<0)*/)
						{
						resetBall();
						}
					}
				});
		
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event)
					{
						if(!timerRunning==true && inCircle==false)
						{
							angleAdjust(event);
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
//				intersectTest = circle.intersects(square.getBoundsInLocal());
//				if(!intersectTest==true)
//					square.setFill(Color.BLACK);
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
		ProjectileBall ball = new ProjectileBall(400, 400);
		ball.setPreferredSize(new Dimension(700, 700));
		
		frame.setLayout(new BorderLayout());
		frame.setTitle("Projectile Ball");
		frame.add(ball, BorderLayout.CENTER);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		}
	}