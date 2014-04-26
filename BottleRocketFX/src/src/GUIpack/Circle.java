package src.GUIpack;

import javax.swing.JApplet;

import java.awt.*;
import java.util.Random;
/**
 *  This is a generic circle class that allows you to easily create a cirlce, draw it , and draw it filled.
 *  
 *  It allows you to bypass the conventional method of using the drawOvel() method, and draw a circle with its center and radius
 * 
 *
 *  @author  Tanmay Chordia
 *  @date    Dec 12, 2013
 *	@version 1
 *  
 *  
 *  TODO: FINISH COMMMENTS
 */
public class Circle
{
    int x, y, radius;
    /**
     * 
     */
    // constructors
    public Circle()
    { x = 0; y = 0; radius = 0; }

    public Circle( int x, int y, int radius )
    { this.x = x;  this.y = y;  this.radius = radius; }

    public void draw(Graphics gr)
    {
        int ulX = x-radius ; // X of upper left corner of rectangle
        int ulY = y-radius ; // Y of upper left corner of rectangle
        gr.drawOval( ulX, ulY, 2*radius, 2*radius );
    }
    public void drawc(int x1,int y1, int r, Graphics gr)
    {
        this.x = x1;
        this.y = y1;
        this.radius=r;
        this.draw(gr);
        
        
    }
    
    public void setH(int h, int height)
    {
    	y = height - h;
    }
    public void fill(Graphics gr)
    {
        int ulX = x-radius ; // X of upper left corner of rectangle
        int ulY = y-radius ; // Y of upper left corner of rectangle
        gr.fillOval( ulX +1, ulY+1, 2*radius-2, 2*radius-2 );
        
    }
    
    public void setX(int x1)
    {
    	this.x = x1;
    }
    public void fillRand(Graphics gr)
    {
        int ulX = x-radius ; // X of upper left corner of rectangle
        int ulY = y-radius ; // Y of upper left corner of rectangle
        Random rand = new Random();
        int color = rand.nextInt(5) + 1;
        switch (color)
        {
            case 1: gr.setColor( Color.BLUE );
                break;
            case 2: gr.setColor( Color.RED );
                break;
            case 3: gr.setColor( Color.ORANGE );
                break;
            case 4: gr.setColor( Color.GREEN);
                break;
            case 5: gr.setColor( Color.YELLOW );
                break;
            default:
                break;
        }
        
        
        gr.fillOval( ulX +1, ulY+1, 2*radius-1, 2*radius-1 );
        
    }
    
    public void draws(int x1,int y1, int s, Graphics gr)
    {
       
        gr.drawRect( x1 - s/2, y1 - s/2, s, s );
    }
    
    /**
     * TODO Write your method description here.
     * @param args Command line args
     */
    public static void main( String[] args )
    {
        // TODO Auto-generated method stub

    }

}
