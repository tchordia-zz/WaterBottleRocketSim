package src.GUIpack;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.Options;

public class RocketF extends JFrame  {
	
	
	
    public LaunchPanel mpanel = new LaunchPanel();
    public WPanel wpanel = new WPanel();
	public CPanel cpanel = new CPanel();
	public String user;
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RocketF ()
    {
		super(); 
	    UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
        Options.setDefaultIconSize(new Dimension(18, 18));

       
//        try {
//            
//            UIManager.setLookAndFeel(new PlasticLookAndFeel());
//        } catch (Exception e) {
//            System.err.println("Can't set look & feel:" + e);
//        }
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        setTitle("Water Bottle Rocket Launch");
        setSize(2000, 1000);
        
       
        
        
        
        
        
        
        
        
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //getContentPane().setBackground(Color.cyan);
        
        add(wpanel);
	
    
    }

	

  public void mInit()
  {
	  remove(wpanel);
	  add(cpanel,BorderLayout.NORTH);
      add(mpanel);
      repaint();
  }
  
  public void restart()
  {
	  remove(mpanel);
	  remove(cpanel);
	 
	  add(wpanel);
	  wpanel.pane.remove(wpanel.launch);
	  wpanel.pane.remove(wpanel.target);
	  wpanel.pane.add(wpanel.tpane);
	  wpanel.pane.setLayout(new BorderLayout());
		wpanel.tpane.add(wpanel.j);
	  repaint();
	  refFrame(this);
	 // System.out.println(user);
  }
       
   public static void main(String[] args) 
   {
		
	   RocketF frame = new RocketF();

    }

   


   
   public class WPanel extends WelcomePanel
   {
	   
	   
	   public WPanel() 
	   {
		   super();
		   setSize(getWidth(),getHeight());
		   
		   
		   
	   }
	   
	   @Override
	   public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			System.out.println(e.getActionCommand());
			if (e.getActionCommand().equals("launch"))
			{
				System.out.println(e.getActionCommand());
				
				mInit();
				
			}
			else if (e.getActionCommand().equals("user"))
			{
				user = j.getText();
				pane.remove(tpane);
				pane.setLayout(new GridLayout());
				pane.add(launch, BorderLayout.NORTH);
				pane.add(target, BorderLayout.NORTH);
				repaint();
			}
			pane.repaint();
			
			try
			{
			SwingUtilities.getRoot(this).setVisible(true);
			}
			catch (Exception eve)
			{}
		}
	   
	
	   
   }
   
   public static void refFrame(Component comp)
   {
	   try
		{
		SwingUtilities.getRoot(comp).setVisible(true);
		}
		catch (Exception eve)
		{}

   }
   public class CPanel extends ControlPanel
   {
	   public CPanel() 
	   {
		   super();
		   setSize(getWidth(),getHeight()/5);
	   }
	   
	   @Override
	   public void actionPerformed(ActionEvent e) {
			restart();
			//System.out.println("restart");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			
		}
	   
	
	   
   }
   
   
   

}