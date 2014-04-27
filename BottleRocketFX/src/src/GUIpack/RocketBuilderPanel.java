package src.GUIpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javafx.application.Platform;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class RocketBuilderPanel extends JPanel {

	
	CreateRocket r = new CreateRocket(5);
		RocketSliders sliders = new RocketSliders(){
			@Override
			public void stateChanged(ChangeEvent e)
			{
			super.stateChanged(e);
				getParent().remove(r);
				
				System.out.println("Hellomotto wtaf are you doung");
				
				r = new CreateRocket(
						RocketSliders.Ln.getValue(),  
						  RocketSliders.D.getValue(),
						  RocketSliders.Df.getValue(),
						  RocketSliders.Dr.getValue(), 5,
						  RocketSliders.Xp.getValue(),
						  RocketSliders.Cr.getValue(),
						  RocketSliders.Ct.getValue(),
						  RocketSliders.S.getValue(),  
						  RocketSliders.Lf.getValue(),
						  RocketSliders.R.getValue(),
						  RocketSliders.Xr.getValue(),
						  RocketSliders.Xb.getValue(),
						  RocketSliders.N.getValue()
						 
						
						
						);
				Platform.runLater(new Runnable() {
					public void run() {
						r.entireRocket.setTranslateX(300);
						r.entireRocket.setTranslateY(500);
						}
				});
				r.setPreferredSize(new Dimension (500,500));
				getParent().add(r);
			r.repaint();
			
			System.out.println("hellomotto wtaf are oyu doing");
			
			}
		};
	SliderPanel2 s = new SliderPanel2();
		
		public RocketBuilderPanel()
		{
			super();
			setLayout( new GridLayout());
			add(sliders);
			
			add(s);
			add(r);
			setBackground(Color.white);
			sliders.setVisible(true);

			Platform.runLater(new Runnable() {
				public void run() {
					r.entireRocket.setTranslateX(300);
					r.entireRocket.setTranslateY(500);
					}
			});
			
			

		}
		
}
