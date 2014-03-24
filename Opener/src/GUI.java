import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//notice javax

public class GUI extends JFrame implements ActionListener {
	JPanel masterpane = new JPanel();
	JPanel pane = new JPanel();
	BouncingBall ball = new BouncingBall();
	JTextField m0 = new JTextField(10);
	JTextField mW = new JTextField(10);
	JTextField vB = new JTextField(10);
	JTextField p0 = new JTextField(10);
	JTextField cD = new JTextField(10);
	JTextField rBot = new JTextField(10);
	JTextField rNoz = new JTextField(10);
	JButton start = new JButton("Start");
	JTextField height = new JTextField(10);

	public GUI() // the frame constructor method
	{
		super("RocketInput");
		setBounds(250, 200, 400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = this.getContentPane(); // inherit main frame
		con.add(masterpane); // add the panel to frame
		masterpane.setLayout(new GridLayout(1, 2));
		masterpane.add(pane);
		pane.setLayout(new GridLayout(9, 2));
		// customize panel here
		pane.add(new JLabel("Mass of Rocket"));
		pane.add(m0);

		pane.add(new JLabel("Mass of Water"));
		pane.add(mW);

		pane.add(new JLabel("Volume of Bottle"));
		pane.add(vB);

		pane.add(new JLabel("Pressure"));
		pane.add(p0);

		pane.add(new JLabel("Drag Coefficient"));
		pane.add(cD);

		pane.add(new JLabel("Radius of Bottle"));
		pane.add(rBot);

		pane.add(new JLabel("Radius of Nozzle"));
		pane.add(rNoz);

		start.addActionListener(this);
		pane.add(start);
		
		// pane.add(someWidget);
		masterpane.add(ball);
		setVisible(true); // display this frame
	}

	public static void main(String args[]) {

		new GUI();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		RocketMath rocket;
		try { 
		rocket = new RocketMath(Double.parseDouble(m0.getText()),
		
		 Double.parseDouble(mW.getText()), Double.parseDouble(vB.getText()),
		 Double.parseDouble(p0.getText()), Double.parseDouble(cD.getText()),
		 Double.parseDouble(rBot.getText()), Double.parseDouble(rNoz
		 .getText()), .01);
		}
		catch (Exception e)
		{
			rocket = new RocketMath(.77, .66, .002, 300000, 1, .05, .01,
			.01);
			m0.setText(".77");
			mW.setText(".66");
			vB.setText(".002");
			p0 .setText("300000");
			cD .setText("1");
			rBot .setText(".05");
			rNoz .setText(".01");
		}

		
		for (; rocket.h>=0; ) 
		{
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.print("wait error");
			}
			ball.y =this.getHeight()-50-(int) rocket.h *this.getHeight()/48 ;
			//System.out.println(ball.y);
			rocket.doStep();
			
			ball.paint(ball.getGraphics());
		}
		
	}
}
