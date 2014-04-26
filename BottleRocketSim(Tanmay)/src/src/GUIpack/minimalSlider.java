package src.GUIpack;


import com.sun.java.swing.Painter;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * 
 * @author Cameron Yang (I stole but who doesn't I mean come on)
 * 
 * RAISE YOUR DONGERS 
 *
 *
 *
 *ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็
 *
 *
 *
 *
 *ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็
 *
 *
 *
 *
 *ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็ส็็็็็็็็็็็็็็็็็็็็็็็็็༼ ຈل͜ຈ༽ส็็็็็็็็็็็็็็็็็็็็็็็็็
 * RAISE YOUR DONGERS
 */

public class minimalSlider extends JSlider{
	public minimalSlider()
	{
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
        sliderDefaults.put("Slider.thumbWidth", 20);
        sliderDefaults.put("Slider.thumbHeight", 20);
        sliderDefaults.put("Slider:SliderThumb.backgroundPainter", new Painter<JComponent>() {
            public void paint(Graphics2D g, JComponent c, int w, int h) {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setStroke(new BasicStroke(2f));
                g.setColor(Color.RED);
                g.fillOval(1, 1, w-3, h-3);
                g.setColor(Color.WHITE);
                g.drawOval(1, 1, w-3, h-3);
            }
        });
        sliderDefaults.put("Slider:SliderTrack.backgroundPainter", new Painter<JComponent>() {
            public void paint(Graphics2D g, JComponent c, int w, int h) {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setStroke(new BasicStroke(2f));
                g.setColor(Color.GRAY);
                g.fillRoundRect(0, 6, w-1, 8, 8, 8);
                g.setColor(Color.WHITE);
                g.drawRoundRect(0, 6, w-1, 8, 8, 8);
            }
        });
	}
}
