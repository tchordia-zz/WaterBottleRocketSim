package src.GUIpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;

public class RocketF extends JFrame  {
	
	public SPanel spanel = new SPanel();
	public AnimPanel apanel = new AnimPanel(new RocketMath2());
    

	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RocketF ()
    {
		super(); 
//		try {
//		   	    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//		   	} catch (Exception e) {
//		   	    e.printStackTrace();
//		   	}
		
        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())){
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
		
//		GroupLayout layout = new GroupLayout(this);
		
//        this.setLayout(layout);
		
//		layout.setAutoCreateContainerGaps(true);
//		layout.setAutoCreateGaps(true);
		
        setTitle("Button Panel Example");
        setSize(new Dimension(600, 600));
        setLayout(new GridLayout(0,2));
        
//        GroupLayout.SequentialGroup horizontalLayout = layout.createSequentialGroup();
//        GroupLayout.SequentialGroup verticalLayout = layout.createSequentialGroup();
//        
//        apanel.setPreferredSize(new Dimension(400,600));
//        
//        horizontalLayout
//        		.addComponent(this.spanel)
//        		.addComponent(this.apanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
//        verticalLayout.addGroup(
//        		layout.createParallelGroup(Alignment.BASELINE)
//        		.addComponent(this.spanel).addComponent(apanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
//        
//        layout.setHorizontalGroup(horizontalLayout);
//        layout.setVerticalGroup(verticalLayout);
        spanel.setBackground(Color.white);
        add(spanel);
        add(apanel);
        setBackground(Color.white);
        setPreferredSize(new Dimension(1000,1000));
        apanel.setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

       
        
       
   public static void main(String[] args) 
   {
	   RocketF frame = new RocketF();
    }

   

   public class SPanel extends SliderPanel
   {
	   public SPanel() 
	   {
		   super();
		   setSize(getWidth(),getHeight());
	   }
	   RocketMath2 rocket2;
	   @Override
	   public void stateChanged(ChangeEvent arg0)
	   {
		   System.out.println("massRocket: " + (massRocket.getValue()/(10+0.0))) ;
			System.out.println("massWater: " + massWater.getValue()/(10+0.0)) ;
			System.out.println("volumeBottle: " + volumeBottle.getValue()/(10000+0.0)) ;
			System.out.println("airPressure: " + airPressure.getValue()*1000) ;
			System.out.println("dragC: " + dragC.getValue()) ;
			System.out.println("bottleRadius: " + bottleRadius.getValue()/(100+0.0)) ;
			System.out.println("nozzleRadius: " + nozzleRadius.getValue()/(100+0.0)) ;
			rocket2 = new RocketMath2(massRocket.getValue()/(10 + 0.0), massWater.getValue()/(10 + 0.0), volumeBottle.getValue()/(10000 + 0.0),airPressure.getValue()*10000,dragC.getValue(),bottleRadius.getValue()/(100 + 0.0), nozzleRadius.getValue()/(100 + 0.0));
			apanel.updaterr(rocket2);
			
	   }
	   
	
	   
   }

}