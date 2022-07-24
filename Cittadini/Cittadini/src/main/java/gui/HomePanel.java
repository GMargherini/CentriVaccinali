package gui;

import java.awt.event.*;
import javax.swing.*;

public class HomePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Gui container;
	public HomePanel(Gui container) {
		this.container=container;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
		JButton search=new JButton("Cerca un centro vaccinale");
    	
    	search.setAlignmentX(CENTER_ALIGNMENT);
    	search.setAlignmentY(CENTER_ALIGNMENT);
    	add(search);
    	
    	setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    	search.setActionCommand("search");
    	
    	search.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object command=e.getActionCommand();
		System.out.println(command);
		if(command.equals("search")) {
			container.changePanel("search");
		}
		else {
			container.changePanel("home");
		}
	}
}
