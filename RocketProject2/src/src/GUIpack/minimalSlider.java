package src.GUIpack;


import com.sun.java.swing.Painter;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 
 * @author Cameron Yang (I stole but who doesn't I mean come on)
 * 
 * RAISE YOUR DONGERS 
 * RAISE YOUR DONGERS
 */

public class minimalSlider extends JSlider{
    private BufferedImage sliderThumb;
	public minimalSlider(int x, int y, int z, int a)
	{
		super(x,y,z,a);
		for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())){
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
		UIDefaults sliderDefaults = new UIDefaults();
        try {
        	sliderThumb = ImageIO.read(new File("new slider thumb.png"));
        }
        catch(IOException ex)
        {
        	System.out.println("ya fucked up");
        }
        sliderDefaults.put("Slider.thumbWidth", 21);
        sliderDefaults.put("Slider.thumbHeight", 25);
        sliderDefaults.put("Slider:SliderThumb.backgroundPainter", new Painter<JComponent>() {
        	public void paint(Graphics2D g, Object c, int w, int h) {
        		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        		g.drawImage(sliderThumb, 0, 0, null);
        	}
        });
        sliderDefaults.put("Slider:SliderTrack.backgroundPainter", new Painter<JComponent>() {
        	public void paint(Graphics2D g, Object c, int w, int h) {
        		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        		g.setStroke(new BasicStroke(2f));
        		g.setColor(Color.lightGray);
        		g.fillRoundRect(0, 8, w-1, 4, 8, 8);
        	}
        });
        this.putClientProperty("Nimbus.Overrides",sliderDefaults);
        this.putClientProperty("Nimbus.Overrides.InheritDefaults",false);	}
}
