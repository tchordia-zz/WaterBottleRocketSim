/**
 * 
 */
package src.GUIpack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

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
		add(back);
		back.addActionListener(this);
		
	}

	/**
	 * @param args
	 */


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
