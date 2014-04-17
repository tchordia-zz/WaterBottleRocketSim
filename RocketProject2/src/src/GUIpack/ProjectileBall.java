package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.print.attribute.standard.JobName;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;

/**
 * Projectile Ball Class
 * 
 * @author Cameron Yang
 * 
 * @description
 * 
 *              This program creates the animation that launches a ball. The
 *              ball follows a basic parabolic path of motion created in the
 *              math class. This program is meant to be used to model a
 *              projectile with a path given by the math class.
 */
public class ProjectileBall extends JPanel {
	static double frameNumber = 0;

	// x and y values of the ball
	public static double x;
	public static double y;

	// initial x and y values of the ball
	public static double xInit;
	public static double yInit;

	// stuff for the original launch angle
	static double cosineTheta;
	static double sineTheta;
	static double magnitude;

	// checks if the timer is running
	static boolean timerRunning;

	// self explanatory
	private static int windowHeight;
	private static int windowWidth;

	// this timer is used for animation.
	static AnimationTimer timer;

	// this establishes the projectile. It's global because more than one method
	// needs to access it
	static Circle circle = new Circle(10,
			javafx.scene.paint.Color.web("#3D9AD1"));

	// this line is the point-y stick when you click on the screen. Chooses the
	// angle of the ball
	static Line line;

	// this makes the numbers
	static Text distanceMarkers[];

	// used to detect intersection
	private boolean intersectTest;

	// used to see if the pointer is in the circle or not
	// used to prevent the angle from changing while clicking inside the circle
	static boolean inCircle = false;

	// it's a rect
	private Rectangle square;
	
	boolean orangeColored = true;

	boolean inButton = false;
	
	// this is the ground
	Line ground;

	/**
	 * Class constructor. pretty basic, sets up the jfxPanel. The new thread
	 * starts the JavaFX
	 * 
	 */
	public ProjectileBall(int W, int H) {
		windowWidth = W;
		windowHeight = H;

		x = 10;
		y = H + 52;

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

	public ProjectileBall() {
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

	/**
	 * <b>ResetBall</b>
	 * 
	 * <p>
	 * When you click the screen after launch, this class is called. It puts the
	 * ball back, and stops the animation.
	 * 
	 */
	public void resetBall() {
		timer.stop();
		timerRunning = false;
		circle.setTranslateX(0);
		circle.setTranslateY(0);
		x = xInit;
		y = yInit;
		frameNumber = 0;
	}

	/**
	 * <b>angleAdjust</b>
	 * 
	 * <p>
	 * This class is to adjust the angle when the mouse is pressed down. Creates
	 * the line, and gets the angle.
	 * 
	 * @param event
	 *            = The mouse event.
	 */
	public void angleAdjust(MouseEvent event) {
		double height = yInit - event.getSceneY();
		double width = event.getSceneX();
		double hypotenuse = Math.sqrt(height * height + width * width);

		magnitude = hypotenuse / 4;
		cosineTheta = width / hypotenuse;
		sineTheta = height / hypotenuse;

		double lineSize = 50;

		line.setEndX(cosineTheta * lineSize);
		line.setEndY(-sineTheta * lineSize + yInit);

		System.out.println("Mouse Clicked");
		System.out.println(height);
		System.out.println(width);
		System.out.println(hypotenuse);
		System.out.println("Cosine Theta: " + cosineTheta);
		System.out.println("Sine Theta: " + sineTheta);
		System.out.println("Angle sine: " + Math.asin(sineTheta));
		System.out.println("Angle cosine: " + Math.acos(cosineTheta));
	}

	/**
	 * <b>createScene</b>
	 * 
	 * <p>
	 * Creates the scene and sets up events.
	 * 
	 */
	private Scene createScene() {
		// establishes scene hierarchy.
		Group root = new Group();
		Scene scene = new Scene(root, javafx.scene.paint.Color.WHITE);
		Group group = new Group();

		// sets up circles
		circle.setRadius(10);
		circle.setCenterX(xInit);
		circle.setCenterY(yInit);

		// sets up the square
		square = new Rectangle(50, 50, javafx.scene.paint.Color.web("#FF6E40"));
		square.setX(xInit + 400);
		square.setY(yInit - 36);

		// I forgot why this was there
		System.out.println(x);
		System.out.println(y);

		// sets up line
		line = new Line();
		line.setStroke(javafx.scene.paint.Color.web("#03436A"));
		line.setStartX(10);
		line.setStartY(yInit);
		line.setEndX(10);
		line.setEndY(yInit);
		
		final Rectangle button = new Rectangle(100, 25, javafx.scene.paint.Color.web("#FFCC40"));
		button.setY(windowHeight+150);
		final Text buttonText = new Text(0, windowHeight+150, "Motion Path");
		buttonText.setX(button.getWidth()/2-buttonText.getBoundsInLocal().getWidth()/2);
		buttonText.setY(windowHeight+150+button.getHeight()/2+buttonText.getBoundsInLocal().getHeight()/4);
		
		group.getChildren().addAll(button, buttonText);
		
		// makes the ground
		distanceMarkers = new Text[10];
		ground = new Line(0, yInit + 11, 2000, yInit + 25);
		for (int i = 0; i < 10; i++) {
			int marker = (i + 1) * 100;
			distanceMarkers[i] = new Text(marker, yInit + 50,
					Integer.toString(marker));
			root.getChildren().add(distanceMarkers[i]);
		}
		// adds everything to the scene
		root.getChildren().addAll(line, circle, square, ground, group);
		
		group.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,
				new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent event) {
						inButton=true;
						System.out.println(inButton);
					}
				});
		group.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET,
				new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent event) {
						inButton=false;
						System.out.println(inButton);
					}
				});
		
		group.addEventFilter(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent event) {
						if(!timerRunning==true)
						{
						if(orangeColored==false)
						{
							button.setFill(javafx.scene.paint.Color.web("#FFCC40"));
							buttonText.setText("Motion Path");
							buttonText.setX(button.getWidth()/2-buttonText.getBoundsInLocal().getWidth()/2);
							orangeColored = true;
							System.out.println("bro it works");
						}
						else
						{
							button.setFill(javafx.scene.paint.Color.web("#FF6E40"));
							buttonText.setText("Timer");
							buttonText.setX(button.getWidth()/2-buttonText.getBoundsInLocal().getWidth()/2);
							orangeColored = false;
							System.out.println("bro it works");
						}
						}
					}
				});
		
		// this event is to make the line appear and get the angle on mouse drag
		scene.addEventFilter(MouseEvent.MOUSE_DRAGGED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (!timerRunning == true && inCircle == false && inButton == false) {
							angleAdjust(event);
						}
					}
				});
		
		// this sets the angle on press, it's different than on drag
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (!timerRunning == true && inCircle == false && inButton == false) {
							angleAdjust(event);
						}
					}
				});
		
		// this event resets the ball after the timer starts.
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						if (timerRunning == true && (inCircle == false)
								/* && (x> windowWidth || x<0 || y >windowHeight || y<0)*/) {
							resetBall();
						}
					}
				});

		// this starts the timer when the ball is clicked
		circle.addEventFilter(MouseEvent.MOUSE_RELEASED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if(orangeColored==false)
						{
						timerRunning = true;
						timer.start();
						}
						else
						{
							createAndLaunchPath();
						}
					}
				});
		// checks if the cursor is in the circle
		circle.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						inCircle = true;
					}
				});
		circle.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						inCircle = false;
					}
				});

		return (scene);
	}

	/**
	 * creates the timer
	 */
	private void establishTimer() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				// for(int i=1; !(i==100); i++)
				// {
				frameNumber += 1;
				System.out.println("Frame Number: "+ frameNumber + " Time: " +l);
				System.out.println("window width " + windowWidth / 2);
				if (x >= windowWidth) {
					this.stop();
				}
				circle.setTranslateX(x);
				circle.setTranslateY(y);
				intersectTest = ground.intersects(circle.getBoundsInParent());
				
				mathClass(frameNumber);
				
				if(intersectTest == true)
				{
					circle.setTranslateY(2);
					this.stop();
				}
				if (frameNumber % 10 == 0) {
					// System.out.println(ground.getBoundsInLocal());
					// System.out.println(circle.getBoundsInParent());
					System.out.println("X value: " + x);
					System.out.println("Y value: " + y);
					System.out.println("Frame Number: " + frameNumber);
				}

				// }
			}
		};
	}

	// this class should be overridden
	public void mathClass(double t) {
		
		t/=100;
		
		 double vInitialX = magnitude*cosineTheta;
		 double accelerationX = 0;
		
		 double vInitialY = magnitude*sineTheta;
		 double gravity = -10;
		
		 x = 0 + (vInitialX*t + accelerationX * t*t);
		 y = 0 - (vInitialY*t + gravity * t*t);
	}
	
	public void createAndLaunchPath()
	{
		Path path = new Path();
		PathTransition pathTransition = new PathTransition();
		QuadCurveTo quadCurve= new QuadCurveTo();
		for(int i = 0; i<101; i++)
		{
			mathClass(i);
		}
		pathTransition.setDuration(Duration.millis(40000));
		pathTransition.setPath(path);
		pathTransition.setNode(circle);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(true);
		pathTransition.play();
		timerRunning=true;
		System.out.println("playing");
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ProjectileBall ball = new ProjectileBall(600, 400);
		ball.setPreferredSize(new Dimension(700, 700));

		frame.setLayout(new BorderLayout());
		frame.setTitle("Projectile Ball");
		frame.add(ball, BorderLayout.CENTER);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}