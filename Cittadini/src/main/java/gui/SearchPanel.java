package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class SearchPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Gui container;
	String[][] data;
	JPanel cards=new JPanel(new CardLayout());
	CardLayout cardLayout=new CardLayout();
	JComboBox<String> cb=new JComboBox<String>();
	JTable resultTable;
	public SearchPanel(Gui container) {
		this.container=container;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton searchBtn1 = new JButton("cerca");
		JButton searchBtn2 = new JButton("cerca");
		JTextField searchNome=new JTextField();
		JTextField searchComune=new JTextField();
		JTextField searchTipo=new JTextField();
		JButton back=new JButton("indietro");
		
		
		String[] columnNames= {"Nome","Comune","Tipo"};
		data=new String[30][3];
		resultTable=new JTable(data,columnNames) ;
		JScrollPane scrollPane=new JScrollPane(resultTable);
		JPanel top=new JPanel();
		JPanel middle=new JPanel();
		JPanel nome=new JPanel();
		JPanel comuneTipo=new JPanel();
		
		resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultTable.setFillsViewportHeight(true);
		resultTable.setDefaultEditor(Object.class, null);
		resultTable.getSelectionModel().addListSelectionListener(new TableSelectionHandler());
		add(scrollPane);
    	
		
		
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		top.add(Box.createHorizontalGlue());
		top.add(cb);
		
		nome.setLayout(new BoxLayout(nome, BoxLayout.X_AXIS));
		nome.add(searchNome);
		nome.add(searchBtn1);
		
		comuneTipo.setLayout(new BoxLayout(comuneTipo, BoxLayout.X_AXIS));
		comuneTipo.add(searchComune);
		comuneTipo.add(searchTipo);
		comuneTipo.add(searchBtn2);
		
		middle.setLayout(cardLayout);
		cards.add(nome,"Ricerca per nome");
		cards.add(comuneTipo,"Ricerca per comune e tipo");
		cardLayout=(CardLayout) cards.getLayout();
		middle.add(cards);
		
		cb.addItem("Ricerca per nome");
		cb.addItem("Ricerca per comune e tipo");
		cb.addActionListener(this);
		
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		add(top);
		add(Box.createRigidArea(new Dimension(20,20)));
		add(middle);
		add(Box.createRigidArea(new Dimension(20,20)));
		add(scrollPane);
		back.setActionCommand("home");
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		Object command=e.getActionCommand();
		System.out.println(command);
		if(command.equals("search")) {
			container.changePanel("search");
		}
		if(command.equals("comboBoxChanged")) {
			cardLayout.show(cards, cb.getSelectedItem().toString());
		}
		else {}
	}
	
	private class TableSelectionHandler implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent e) {
			if(! e.getValueIsAdjusting()) {
			int row=resultTable.getSelectedRow();
			String nome= (String) resultTable.getValueAt(row, 0);
			String comune=(String) resultTable.getValueAt(row, 1);
			container.changePanel(nome,comune);
			}
		}
	}
}
