package gui;

import cittadini.Cittadini;
import datamodel.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;


public class SearchPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Gui container;
	Cittadini client;
	String[][] data;
	Boolean ricercaPerNome=true;
	JPanel cards=new JPanel(new CardLayout());
	CardLayout cardLayout=new CardLayout();
	JComboBox<String> cb=new JComboBox<String>();
	JTextField searchNome;
	JTextField searchComune;
	JTextField searchTipo;
	JTable resultTable;
	public SearchPanel(Gui container, Cittadini client) {
		this.container=container;
		this.client=client;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton searchBtn1 = new JButton("cerca");
		JButton searchBtn2 = new JButton("cerca");
		searchNome=new JTextField();
		searchComune=new JTextField();
		searchTipo=new JTextField();
		
		
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

		searchBtn1.setActionCommand("search");
		searchBtn2.setActionCommand("search");
		searchBtn1.addActionListener(this);
		searchBtn2.addActionListener(this);
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
	}
	public void actionPerformed(ActionEvent e) {
		Object command=e.getActionCommand();
		System.out.println(command);
		if(command.equals("search")) {
			if(ricercaPerNome){
				ArrayList<CentroVaccinale>result=client.cercaCentroVaccinale(searchNome.getText());
				int size=result.size();
				data=new String[size][6];
				for(int i=0;i<size;i++){
					data[i]=result.get(i).toArray();
				}
			}
			else{
				ArrayList<CentroVaccinale>result=client.cercaCentroVaccinale(searchComune.getText(),searchTipo.getText());
				int size=result.size();
				data=new String[size][6];
				for(int i=0;i<size;i++){
					data[i]=result.get(i).toArray();
				}
			}
			DefaultTableModel model=(DefaultTableModel) resultTable.getModel();
			model.fireTableDataChanged();
			resultTable.setModel(model);
		}
		if(command.equals("comboBoxChanged")) {
			cardLayout.show(cards, cb.getSelectedItem().toString());
			ricercaPerNome=cb.getSelectedItem().toString().equals("Ricerca per nome");
		}
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
