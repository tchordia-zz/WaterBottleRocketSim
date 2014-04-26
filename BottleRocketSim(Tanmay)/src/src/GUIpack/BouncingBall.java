package src.GUIpack;

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
    public void update(RocketMath rocket)
    
    {
    	for (; rocket.h>=0; ) 
		{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.print("wait error");
			}
			y =this.getHeight()-50-(int) rocket.h *this.getHeight()/48 ;
			//System.out.println(ball.y);
			rocket.doStep();
			
			paint(getGraphics());
		}
    }
   @Override
   public void paint(Graphics g) {
       super.paintComponent(g);
       
       g.fillOval(getWidth()/2, y - R, R * 2, R * 2);
   }
}