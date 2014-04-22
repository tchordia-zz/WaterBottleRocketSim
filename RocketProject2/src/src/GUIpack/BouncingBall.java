package src.GUIpack;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BouncingBall extends JPanel{
    //Ball position
    private int x = 0;
    public int y = 0;
    //Position change after every repaint
  
    //Ball radius
    private final int R = 20;
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

			y=(int)(rocket.y1*10);
			x = (int)( rocket.x1*10);
			//System.out.println(ball.y);
			rocket.doStepThrust();
			System.out.println(rocket.drag1);
			paint(getGraphics());
		}
    }
    public void drawRocket(Graphics g)
    {
    	g.fillArc(500, this.getHeight()/2, 40, 70, 0, (int) (2*Math.PI));
    }
   @Override
   public void paint(Graphics g) {
       super.paintComponent(g);
       g.setColor(Color.green);
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
       g.fillOval((x+R),getHeight()-y-R ,R * 2,R * 2);
            
      try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       
   }
}
