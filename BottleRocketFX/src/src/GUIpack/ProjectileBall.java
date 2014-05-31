package src.GUIpack;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.File;
import java.util.Date;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import javax.swing.JPanel;

/**
 * Projectile Ball Class
 * 
 * @author Cameron Yang
 * 
 * @Description This program creates the animation that launches a ball. The
 *              ball follows a basic parabolic path of motion created in the
 *              math class. This program is meant to be used to model a
 *              projectile with a path given by the math class.
 */
public class ProjectileBall extends JPanel implements Serializable {

	static double frameNumber = 0;
	Music music = new Music();
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

	static double angle = 90;

	// this timer is used for animation.
	static AnimationTimer timer;

	// this establishes the projectile. It's global because more than one method
	// needs to access it
	// static Circle circle = new Circle(10,
	// javafx.scene.paint.Color.web("#3D9AD1"));

	// this line is the point-y stick when you click on the screen. Chooses the
	// angle of the ball
	static Line line;

	// this makes the numbers
	static Text distanceMarkers[];

	// used to detect intersection
	boolean intersectTest;
	boolean squareTest = false;
	boolean falling;

	// used to see if the pointer is in the circle or not
	// used to prevent the angle from changing while clicking inside the circle
	static boolean inCircle = false;
	int score = 0;
	// it's a rect
	private Rectangle square;

	boolean orangeColored = true;

	boolean inButton = false;

	Group bottleRocket;

	private Date clock = new Date();
	long time = 0;
	long oldTime = 0;

	Text framesPerSecond;

	// this is the ground
	Line ground;

	Group boundsInParent = new Group();

	Line parentBoundingLineLeft;
	Line parentBoundingLineRight;
	Line parentBoundingLineTop;
	Line parentBoundingLineBottom;

	Line actualDifferenceLine;
	Line displayedDifferenceLine;

	double minX;
	double minY;
	double maxX;
	double maxY;

	final Text buttonText = new Text(0, windowHeight + 150, "Launch!");
	final Rectangle button = new Rectangle(100, 25,
			javafx.scene.paint.Color.web("#FFCC40"));
	public static double rocketFactor = .4;
	CreateRocket rocket = new CreateRocket(rocketFactor);

	double fallingRotation = 0;

	double angleAtFall = 0;
	double fallingAngle = 0;
	boolean secondPointFalling = false;
	boolean stool = false;
	int i = 0;

	double youngJeans;
	
	double targetDistance;
	
	final JFXPanel fxPanel = new JFXPanel();

	/**
	 * Class constructor. pretty basic, sets up the jfxPanel. The new thread
	 * starts the JavaFX
	 * 
	 */
	public ProjectileBall(int W, int H, double distance) {
		
		targetDistance = distance;
		
		windowWidth = W;
		windowHeight = H;

		x = 10;
		y = H;

		xInit = x;
		yInit = y;

		bottleRocket = rocket.getRocket();
		
		bottleRocket.setTranslateX(0);
		bottleRocket.setTranslateY(0);		
		
		System.out.println("Circle Height: "
				+ (rocket.Xb + rocket.Xr + rocket.Ct));
		System.out
				.println("Circle Bounds: " + bottleRocket.getBoundsInParent());

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
		stool = false;
		falling = false;
		secondPointFalling = false;
		angle = 0;
		bottleRocket.setRotate(angle);
		timer.stop();
		
		
		timerRunning = false;
		intersectTest = false;
		squareTest = false;
		falling = false;
		bottleRocket.setTranslateX(0);
		bottleRocket.setTranslateY(0);
		bottleRocket.getTransforms().clear();
		fallingRotation = 0;
		x = xInit;
		y = yInit;
		frameNumber = 0;
		i = 0;

		bottleRocket.setLayoutX(x + 25);
		bottleRocket
				.setLayoutY(y
						- ((rocket.totalBodyLength / 2) - rocket.Cr + rocket.Xr + rocket.Ct)  - 30);

		minX = bottleRocket.getBoundsInParent().getMinX();
		minY = bottleRocket.getBoundsInParent().getMinY();
		maxX = bottleRocket.getBoundsInParent().getMaxX();
		maxY = bottleRocket.getBoundsInParent().getMaxY();

		parentBoundingLineLeft.setStartX(minX);
		parentBoundingLineLeft.setStartY(minY);
		parentBoundingLineLeft.setEndX(minX);
		parentBoundingLineLeft.setEndY(maxY);

		parentBoundingLineRight.setStartX(maxX);
		parentBoundingLineRight.setStartY(maxY);
		parentBoundingLineRight.setEndX(maxX);
		parentBoundingLineRight.setEndY(minY);

		parentBoundingLineTop.setStartX(maxX);
		parentBoundingLineTop.setStartY(maxY);
		parentBoundingLineTop.setEndX(minX);
		parentBoundingLineTop.setEndY(maxY);

		parentBoundingLineBottom.setStartX(maxX);
		parentBoundingLineBottom.setStartY(minY);
		parentBoundingLineBottom.setEndX(minX);
		parentBoundingLineBottom.setEndY(minY);

		button.setFill(javafx.scene.paint.Color.web("#FFCC40"));
		buttonText.setText("Launch!");
		buttonText.setX(button.getWidth() / 2
				- buttonText.getBoundsInLocal().getWidth() / 2);

		secondPointFalling = false;
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
		width = Math.max(width,0);
		height = (width == 0) ? hypotenuse * height/Math.abs(height):height;
		magnitude = hypotenuse / 4;
		cosineTheta = width / hypotenuse;
		sineTheta = height / hypotenuse;

		System.out.println("Mouse Clicked");
		System.out.println(height);
		System.out.println("width" + width);
		System.out.println(hypotenuse);
		System.out.println("Cosine Theta: " + cosineTheta);
		System.out.println("Sine Theta: " + sineTheta);
		System.out.println("Angle sine: " + Math.asin(sineTheta));
		System.out.println("Angle cosine: " + Math.acos(cosineTheta));

		angle = Math.acos(sineTheta) * 180 / Math.PI;

		bottleRocket.setRotate(angle);

		minX = bottleRocket.getBoundsInParent().getMinX();
		minY = bottleRocket.getBoundsInParent().getMinY();
		maxX = bottleRocket.getBoundsInParent().getMaxX();
		maxY = bottleRocket.getBoundsInParent().getMaxY();

		parentBoundingLineLeft.setStartX(minX);
		parentBoundingLineLeft.setStartY(minY);
		parentBoundingLineLeft.setEndX(minX);
		parentBoundingLineLeft.setEndY(maxY);

		parentBoundingLineRight.setStartX(maxX);
		parentBoundingLineRight.setStartY(maxY);
		parentBoundingLineRight.setEndX(maxX);
		parentBoundingLineRight.setEndY(minY);

		parentBoundingLineTop.setStartX(maxX);
		parentBoundingLineTop.setStartY(maxY);
		parentBoundingLineTop.setEndX(minX);
		parentBoundingLineTop.setEndY(maxY);

		parentBoundingLineBottom.setStartX(maxX);
		parentBoundingLineBottom.setStartY(minY);
		parentBoundingLineBottom.setEndX(minX);
		parentBoundingLineBottom.setEndY(minY);
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
		
		x = 10;
		y = windowHeight;
		
		bottleRocket = rocket.getRocket();
		
		bottleRocket.setTranslateX(0);
		bottleRocket.setTranslateY(0);
		bottleRocket.setLayoutX(x + 25);
		bottleRocket.setLayoutY(y-((rocket.totalBodyLength/ 2) - rocket.Cr + rocket.Xr + rocket.Ct) - 30);


		// sets up the square
		square = new Rectangle(50, 50, javafx.scene.paint.Color.web("#FF6E40"));
		square.setX(xInit + targetDistance);
		square.setY(yInit - 50);

		// sets up line
		line = new Line();
		line.setStroke(javafx.scene.paint.Color.web("#03436A"));
		line.setStartX(10);
		line.setStartY(yInit);
		line.setEndX(10);
		line.setEndY(yInit);

		button.setY(windowHeight + 150);
		buttonText.setX(button.getWidth() / 2
				- buttonText.getBoundsInLocal().getWidth() / 2);
		buttonText.setY(windowHeight + 150 + button.getHeight() / 2
				+ buttonText.getBoundsInLocal().getHeight() / 4);

		group.getChildren().addAll(button, buttonText);

		framesPerSecond = new Text();

		framesPerSecond.setText("Score " + 0);
		framesPerSecond.setX(200);
		framesPerSecond.setY(windowHeight + 150);

		// makes the ground
		distanceMarkers = new Text[10];

		ground = new Line(0, yInit, 2000, yInit);

		for (int i = 0; i < 10; i++) {
			int marker = ((i + 1) * 100);
			distanceMarkers[i] = new Text(marker + 25, yInit + 50,
					Integer.toString(marker));
			root.getChildren().add(distanceMarkers[i]);
		}

		minX = bottleRocket.getBoundsInParent().getMinX();
		minY = bottleRocket.getBoundsInParent().getMinY();
		maxX = bottleRocket.getBoundsInParent().getMaxX();
		maxY = bottleRocket.getBoundsInParent().getMaxY();

		boundsInParent = new Group();

		parentBoundingLineLeft = new Line(minX, minY, minX, maxY);
		parentBoundingLineRight = new Line(maxX, minY, maxX, maxY);
		parentBoundingLineTop = new Line(minX, maxY, maxX, maxY);
		parentBoundingLineBottom = new Line(minX, minY, maxX, minY);

		actualDifferenceLine = new Line(0, 0, 0, 0);
		actualDifferenceLine.setStroke(Color.RED);

		displayedDifferenceLine = new Line(0, 0, 0, 0);
		displayedDifferenceLine.setStroke(Color.BLUE);

		parentBoundingLineLeft.setStroke(Color.CADETBLUE);
		parentBoundingLineRight.setStroke(Color.CADETBLUE);
		parentBoundingLineTop.setStroke(Color.CADETBLUE);
		parentBoundingLineBottom.setStroke(Color.CADETBLUE);

		boundsInParent.getChildren().addAll(parentBoundingLineLeft,
				parentBoundingLineRight, parentBoundingLineTop,
				parentBoundingLineBottom, displayedDifferenceLine,
				actualDifferenceLine);

		// adds everything to the scene
		root.getChildren().addAll(line, bottleRocket, square, ground, group,
				framesPerSecond, boundsInParent);

		group.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						inButton = true;
					}
				});
		group.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						inButton = false;
					}
				});

		group.addEventFilter(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {

						if (orangeColored == false) {

							if (intersectTest == true) {
								resetBall();
							}
							if (timerRunning == false) {
								button.setFill(javafx.scene.paint.Color
										.web("#FFCC40"));
								buttonText.setText("Launch!");
								buttonText.setX(button.getWidth()
										/ 2
										- buttonText.getBoundsInLocal()
												.getWidth() / 2);
								orangeColored = true;
							}
						} else if (orangeColored == true
								&& timerRunning == false) {
							orangeColored = false;
							button.setFill(javafx.scene.paint.Color
									.web("#FF6E40"));
							buttonText.setText("Timer Running");
							buttonText.setX(button.getWidth() / 2
									- buttonText.getBoundsInLocal().getWidth()
									/ 2);
							timerRunning = true;
							timer.start();
//							music.playAudio(Music.rocket);
						}
					}
				});

		// this event is to make the line appear and get the angle on mouse drag
		scene.addEventFilter(MouseEvent.MOUSE_DRAGGED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (!timerRunning == true && inCircle == false
								&& inButton == false) {
							angleAdjust(event);
						}
					}
				});

		// this sets the angle on press, it's different than on drag
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (!timerRunning == true && inCircle == false
								&& inButton == false) {
							angleAdjust(event);
						}
					}
				});

		// this event resets the ball after the timer starts.
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {

					public void handle(MouseEvent event) {
						if (timerRunning == true && (inCircle == false)
								&& inButton == false) {
							resetBall();
						}
					}
				});

		// this starts the timer when the ball is clicked
		bottleRocket.addEventFilter(MouseEvent.MOUSE_RELEASED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (orangeColored == false) {
							// timer.start();
						}
					}
				});
		// checks if the cursor is in the circlef
		bottleRocket.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						inCircle = true;
					}
				});
		bottleRocket.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						inCircle = false;
					}
				});
		resetBall();
		return (scene);
	}

	/**
	 * creates the timer
	 */
	private void establishTimer() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				timerRunning = true;

				if (!(intersectTest == true)) {

					mathClass(frameNumber);

					clock = new Date();

					
					//System.out.println("SCORE BITCHES " + score);
					oldTime = clock.getTime();

					frameNumber += 1;

					bottleRocket.setTranslateX(x);
					bottleRocket.setTranslateY(y);
					
					System.out.println("Current X: " + x);
					System.out.println("Current Y: " + y);

					bottleRocket.setRotate(angle);

					minX = bottleRocket.getBoundsInParent().getMinX();
					minY = bottleRocket.getBoundsInParent().getMinY();
					maxX = bottleRocket.getBoundsInParent().getMaxX();
					maxY = bottleRocket.getBoundsInParent().getMaxY();

					parentBoundingLineLeft.setStartX(minX);
					parentBoundingLineLeft.setStartY(minY);
					parentBoundingLineLeft.setEndX(minX);
					parentBoundingLineLeft.setEndY(maxY);

					parentBoundingLineRight.setStartX(maxX);
					parentBoundingLineRight.setStartY(maxY);
					parentBoundingLineRight.setEndX(maxX);
					parentBoundingLineRight.setEndY(minY);

					parentBoundingLineTop.setStartX(maxX);
					parentBoundingLineTop.setStartY(minY);
					parentBoundingLineTop.setEndX(minX);
					parentBoundingLineTop.setEndY(minY);

					parentBoundingLineBottom.setStartX(maxX);
					parentBoundingLineBottom.setStartY(maxY);
					parentBoundingLineBottom.setEndX(minX);
					parentBoundingLineBottom.setEndY(maxY);

					intersectTest = ground.intersects(bottleRocket
							.getBoundsInParent());
					
					//
					for(int z=0; z<=14 && !squareTest; ++z){
						
					System.out.println(z);
					squareTest = square.intersects(bottleRocket
							.getChildren()
							.get(z)
							.localToScene(
									bottleRocket.getChildren().get(z)
											.getBoundsInLocal()));							
					}
					if (squareTest) {
						intersectTest = true;
						
						score += 1;
						framesPerSecond.setText("Score: " + score);
					}
					//
					
				}

				if (intersectTest == true && falling == false) {

					System.out.println("INTERSECT");
					if (!squareTest) {
						bottleRocket
								.setTranslateY(bottleRocket.getTranslateY()
										- (bottleRocket.getBoundsInParent()
												.getMaxY() - ground
												.getBoundsInLocal().getMinY()));
					}
					bottleRocket.setLayoutX(bottleRocket.getTranslateX() + 35);
					bottleRocket.setLayoutY(yInit
							- ((rocket.totalBodyLength / 2) - rocket.Cr
									+ rocket.Xr + rocket.Ct)
							+ bottleRocket.getTranslateY() - 30);
					bottleRocket.setTranslateX(0);
					bottleRocket.setTranslateY(0);

					youngJeans = bottleRocket.getRotate();

					if (fallingAngle == 0) {
						double phi = Math.atan((rocket.Df) / rocket.Ln) * 180
								/ Math.PI;
						fallingAngle = 90 - (180 - bottleRocket.getRotate());
						fallingAngle -= phi;
					}

					falling = true;

					buttonText.setText("Reset Ball");
					buttonText.setX(button.getWidth() / 2
							- buttonText.getBoundsInLocal().getWidth() / 2);
					buttonText.setY(windowHeight + 150 + button.getHeight() / 2
							+ buttonText.getBoundsInLocal().getHeight() / 4);

					System.out.println("Circle Box: "
							+ bottleRocket.getBoundsInParent());
					System.out.println("Ground Box: "
							+ ground.getBoundsInLocal() + "\n");

					System.out.println("\n");

					System.out.println("Circle Box: "
							+ bottleRocket.getBoundsInParent());
					System.out.println("Ground Box: "
							+ ground.getBoundsInLocal() + "\n");

				}
				if (falling == true) {
					if (!secondPointFalling) {
						bottleRocket.getTransforms().clear();
						bottleRocket.getTransforms().add(
								new Rotate(-i, x, y - rocket.totalBodyLength
										/ 2));
					} else {
						if (angleAtFall == 0) {
							angleAtFall = i;
							System.out.println(angleAtFall);
						} else {
							bottleRocket
									.setTranslateY(-.0065 * youngJeans
											* youngJeans + 2.5642 * youngJeans
											- 199.66);
							System.out
									.println("Low Shirt Sujeeth says, 'y value at: "
											+ bottleRocket.getTranslateY()
											+ "'");
							bottleRocket.setTranslateX((.0013 * youngJeans
									* youngJeans + .4077 * youngJeans - 70.184)
									* -1);

							System.out
									.println("Big boobed Sujeeth says, 'x value at: "
											+ bottleRocket.getTranslateX()
											+ "'");

							bottleRocket.getTransforms().clear();
							Rotate rotate = new Rotate(-i,
									rocket.transitionTopRight,
									rocket.topTransitionHeight);
							bottleRocket.getTransforms().add(rotate);
							stool = ground.intersects(bottleRocket
									.getChildren()
									.get(13)
									.localToScene(
											bottleRocket.getChildren().get(13)
													.getBoundsInLocal()));
							System.out.println("contact: " + stool);
							if (stool) {
								this.stop();
							}
						}
					}
					if (i == (int) fallingAngle) {
						System.out.println(i);
						bottleRocket.getTransforms().clear();
						bottleRocket.getTransforms().add(
								new Rotate(-fallingAngle, x, y
										- rocket.totalBodyLength / 2));
						secondPointFalling = true;
					}
				}
			};
		};
	}

	// this class should be overridden
	public void mathClass(double t) {

		t /= 100;

		double vInitialX = magnitude * cosineTheta;
		double accelerationX = 0;

		double vInitialY = magnitude * sineTheta;
		double gravity = -10;

		x = 0 + (vInitialX * t + accelerationX * t * t);
		y = 0 - (vInitialY * t + gravity * t * t);
	}

	public void loadNewRocket(CreateRocket r)
	{
		rocket = r;
		
		Platform.runLater(new Runnable() {
			public void run() {
				Scene scene = createScene();
				fxPanel.setScene(scene);
			}
		});	}

}