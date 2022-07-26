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
	private JMenu menuUtente;
	private JMenuItem login;
	private JMenuItem register;
	private JPanel cards=new JPanel(new CardLayout());
	private CardLayout cardLayout=new CardLayout();
	
	public Gui(Cittadini cittadini) {
		client=cittadini;
		homePanel=new HomePanel(this);
		searchPanel=new SearchPanel(this,client);
		UserPanel userPanel = new UserPanel(this);
		setLayout(cardLayout);
		cards.add(homePanel, "home");
		cards.add(searchPanel,"search");
		cards.add(userPanel,"user");
		cardLayout.setVgap(10);
		cardLayout.setHgap(10);
		cardLayout=(CardLayout) cards.getLayout();
		add(cards);
		JMenuBar menuBar=new JMenuBar();
    	menuUtente=new JMenu("Utente");
    	login=new JMenuItem("accedi");
		register=new JMenuItem("registrati");
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
	protected void logIn(String userId){
		menuUtente.setName(userId);
		login.setName("Info utente");
		login.setActionCommand("user");
		register.setName("Esci");
		register.setActionCommand("logout");
	}
	protected void logout(){
		menuUtente=new JMenu("Utente");
		login=new JMenuItem("accedi");
		register=new JMenuItem("registrati");
		login.setActionCommand("login");
		register.setActionCommand("register");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object command=e.getActionCommand();
		System.out.println(command);
		if(command.equals("login")) {
			if(loginPanel==null){
				loginPanel=new LoginPanel(this,client);
				cards.add(loginPanel,command);
			}
			changePanel((String) command);
		}
		else if(command.equals("register")){
			if(registerPanel==null){
				registerPanel=new RegisterPanel(this);
				cards.add(registerPanel,command);
			}
			changePanel((String) command);
		}
		else if(command.equals("user")){
			changePanel((String) command);
		}
		else if(command.equals("logout")){
			logout();
		}
		else if(command.equals("home")){
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
