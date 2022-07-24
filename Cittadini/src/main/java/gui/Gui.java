package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width=700;
	int height=500;
	JPanel homePanel;
	JPanel searchPanel;
	JPanel centrePanel;
	JPanel loginPanel;
	JPanel cards=new JPanel(new CardLayout());
	CardLayout cardLayout=new CardLayout();
	
	public Gui() {
		homePanel=new HomePanel(this);
		searchPanel=new SearchPanel(this);
		loginPanel=new LoginPanel(this);
		setLayout(cardLayout);
		cards.add(homePanel, "home");
		cards.add(searchPanel, "search");
		cards.add(loginPanel,"login");
		cardLayout.setVgap(10);
		cardLayout.setHgap(10);
		cardLayout=(CardLayout) cards.getLayout();
		add(cards);
		JMenuBar menuBar=new JMenuBar();
    	JMenu menuUtente=new JMenu("Utente");
    	JMenuItem login=new JMenuItem("accedi");
    	JButton home=new JButton("Home");
    	setTitle("Cittadini");
    	home.setOpaque(false);
    	home.setContentAreaFilled(false);
    	home.setBorderPainted(false);

    	menuBar.add(home);
    	menuBar.add(Box.createHorizontalGlue());
    	menuBar.add(menuUtente);
    	menuUtente.add(login);
    	home.setActionCommand("home");
    	home.addActionListener(new EventListener());
    	login.setActionCommand("login");
    	login.addActionListener(new EventListener());
    	setJMenuBar(menuBar);
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		changePanel("home");
		setVisible(true);
		setResizable(false);
	}
	
	private class EventListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object command=e.getActionCommand();
			System.out.println(command);
			if(command.equals("search")) {
				changePanel("search");
			}
			if(command.equals("login")) {
				changePanel("login");
			}
			else {
				changePanel("home");
			}
		}
	}
	protected void changePanel(String panel) {
		cardLayout.show(cards, panel);
	}
	protected void changePanel(String nome, String comune) {
		System.out.println(nome);
		centrePanel=new CentrePanel(this,nome,comune);
		cards.add(centrePanel,"centro");
		cardLayout.show(cards, "centro");
	}

}
