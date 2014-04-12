

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BouncingBall extends JPanel{
    //Ball position
    private int x = this.getWidth()/2;
    public int y = 0;
    //Position change after every repaint
    private int xIncrement = 5;
    private int yIncrement = 5;
    //Ball radius
    private final int R = 10;
    private boolean flag = true;
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
    	setBackground(Color.white);
   }
    public void update(AngularLaunch rocket)
    
    {
    	for (; rocket.y1>=0; ) 
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.print("wait error");
			}
			y=this.getHeight();//-(int)rocket.y1*this.getHeight()/48;
			x = (int) rocket.x1;
			//System.out.println(ball.y);
			rocket.doStepThrust();
			
			paint(getGraphics());
		}
    }
   @Override
   public void paint(Graphics g) {
       super.paintComponent(g);
       //If the ball hits one of the panel boundaries
//       //change to the opposite direction
//       if (x < 0 || x > getWidth()) {
//           xIncrement *= -1;
//       }
//       if (y < 0 || y > getHeight()) {
//           yIncrement *= -1;
//       }r
//       //increment position
//      if (flag)
//      {
//    	  y = getHeight();
//    	  flag = false;
//      }
    
       //draw the ball
       
//       g.drawImage(img,x,= y,null);
//       g.fillOval(x, ry= 500= - R , R * 2, R * 2);
       g.fillOval(x,y-R - 200 ,R * 2,R * 2);
       System.out.println(y-R);
   }
}