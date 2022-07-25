package gui;

import cittadini.Cittadini;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cittadini client;
	private int width=700;
	private int height=500;
	private JPanel homePanel;
	private JPanel searchPanel;
	private JPanel centrePanel;
	private JPanel loginPanel;
	private JPanel registerPanel;
	private JPanel cards=new JPanel(new CardLayout());
	private CardLayout cardLayout=new CardLayout();
	
	public Gui(Cittadini cittadini) {
		homePanel=new HomePanel(this);
		searchPanel=new SearchPanel(this);
		setLayout(cardLayout);
		cards.add(homePanel, "home");
		cards.add(searchPanel,"search");
		cardLayout.setVgap(10);
		cardLayout.setHgap(10);
		cardLayout=(CardLayout) cards.getLayout();
		add(cards);
		JMenuBar menuBar=new JMenuBar();
    	JMenu menuUtente=new JMenu("Utente");
    	JMenuItem login=new JMenuItem("accedi");
		JMenuItem register=new JMenuItem("registrati");
    	JButton home=new JButton("Home");
    	setTitle("Cittadini");
    	home.setOpaque(false);
    	home.setContentAreaFilled(false);
    	home.setBorderPainted(false);

    	menuBar.add(home);
    	menuBar.add(Box.createHorizontalGlue());
    	menuBar.add(menuUtente);
    	menuUtente.add(login);
		menuUtente.add(register);
    	home.setActionCommand("home");
    	home.addActionListener(this);
    	login.setActionCommand("login");
    	login.addActionListener(this);
		register.setActionCommand("register");
		register.addActionListener(this);
    	setJMenuBar(menuBar);
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		changePanel("home");
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object command=e.getActionCommand();
		System.out.println(command);
		if(command.equals("login")) {
			if(loginPanel==null){
				loginPanel=new LoginPanel(this);
				cards.add(loginPanel,command);
			}
			changePanel((String) command);
		}
		if(command.equals("register")){
			if(registerPanel==null){
				registerPanel=new RegisterPanel(this);
				cards.add(registerPanel,command);
			}
			changePanel((String) command);
		}
		if(command.equals("home")){
			changePanel("home");
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
