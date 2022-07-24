package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Gui container;
	public LoginPanel(Gui gui) {
		container=gui;
		setLayout(new GridBagLayout());
		JLabel userLabel=new JLabel("Username: ");
		JLabel passwordLabel=new JLabel("Password: ");
		JTextField userTextField=new JTextField();
		JTextField passwordTextField=new JTextField();
		JButton loginButton=new JButton("Accedi");
		GridBagConstraints gbc=new GridBagConstraints();
		
		setBorder(BorderFactory.createEmptyBorder(10,60,60,60));
		userLabel.setHorizontalAlignment(JLabel.CENTER);
		passwordLabel.setHorizontalAlignment(JLabel.CENTER);
		gbc.insets=new Insets(10,10,10,10);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.anchor=GridBagConstraints.PAGE_START;
		gbc.gridy=0;
		gbc.weightx=0.25;
		add(userLabel,gbc);
		gbc.weightx=1;
		add(userTextField,gbc);
		gbc.gridy=1;
		gbc.weightx=0.25;
		add(passwordLabel,gbc);
		gbc.weightx=1;
		add(passwordTextField,gbc);
		
		gbc=new GridBagConstraints();
		gbc.insets=new Insets(10,10,10,10);
		gbc.gridy=2;
		gbc.gridx=0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.gridheight=GridBagConstraints.REMAINDER;
		add(loginButton,gbc);
		loginButton.setActionCommand("login");
		loginButton.addActionListener(this);
		
	}
	private void userExists() {
		
	}
	public void actionPerformed(ActionEvent e) {
		Object command=e.getActionCommand();
		System.out.println(command);
		if(command.equals("login")) {
			container.changePanel("user");
		}
	}
}
