import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BouncingBall extends JPanel{
    //Ball position
	Graphics bufferGraphics; 
    private int x = this.getWidth()/2;
    public int y = 0;
    //Position change after every repaint
    private int xIncrement = 5;
    private int yIncrement = 5;
    //Ball radius
    private final int R = 10;

    private Timer timer;

    public BouncingBall(){
//        timer = new Timer(25, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               BouncingBall.this.repaint();
//        }
//         });
//
//    timer.start();
   }

   @Override
   public void paint(Graphics g) {
       super.paintComponent(g);
       //If the ball hits one of the panel boundaries
       //change to the opposite direction
       if (x < 0 || x > getWidth()) {
           xIncrement *= -1;
       }
       if (y < 0 || y > getHeight()) {
           yIncrement *= -1;
       }
       //increment position
      
       System.out.println(y);
       //draw the ball
       BufferedImage image2 = null;
       try {
			image2 = ImageIO
					.read(new File("bottle-rocket.jpg"));
		} catch (IOException e) {

			e.printStackTrace();
		}
    
       	BufferStrategy myStrategy = null;
       	Graphics gr = myStrategy.getDrawGraphics();
        g.drawImage(image2, getWidth()/2-25, y, null);
//       g.fillOval(getWidth()/2, y - R, R * 2, R * 2);
   }
   
}