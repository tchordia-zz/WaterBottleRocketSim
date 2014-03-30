/**
 * 
 */
package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * @author Tanmay
 *
 */
public class ControlPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	JButton back = new JButton("Back to Start");
	public ControlPanel() {
		
		back.addActionListener(this);
		Border bord = BorderFactory.createLineBorder(Color.black, 5);
		setBorder(bord);
		
		
		
		add(back);
	}

	/**
	 * @param args
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.drawImage(imageBack("marioSky.jpeg",getWidth(),getHeight() * 5 ), 0, 0, null);
		//g.drawImage(imageBack("RocketLogo.png",getWidth()/5,getHeight()/10 ), getWidth()/5, getHeight()/8, null);
	}
	
	public static JPanel addBack(JPanel pane, String filename, int width, int height)
	{
		BufferedImage image2 = null;
		try {
			image2 = ImageIO
					.read(new File(filename));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		int type = image2.getType() == 0? BufferedImage.TYPE_INT_ARGB : image2.getType();
		image2 = resizeImage(image2, type, width, height);
		final BufferedImage image = image2;
		
		pane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.drawImage(imageBack("greenBack.jpeg", 500,500), 0, 0, null);
				g.drawImage(image, 0, 0, null);
			}
			
			
		};
		return pane;
	}
	
	public static BufferedImage imageBack(String filename, int width, int height)
	{
		BufferedImage image2 = null;
		try {
			image2 = ImageIO
					.read(new File(filename));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		int type = image2.getType() == 0? BufferedImage.TYPE_INT_ARGB : image2.getType();
		image2 = resizeImage(image2, type, width, height);
		return image2;
	}
	static BufferedImage resizeImage(BufferedImage originalImage, int type, int iw, int ih){
		BufferedImage resizedImage = new BufferedImage(iw, ih, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, iw, ih, null);
		g.dispose();
	 
		return resizedImage;
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("hello");
		
	}
	
	public static void main (String args[])
	{
		JFrame frame = new JFrame();
		frame.add(new ControlPanel());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
	}

}
