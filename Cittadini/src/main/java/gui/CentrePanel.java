package gui;

import cittadini.Cittadini;
import datamodel.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CentrePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Gui container;
	String[] columnNames= {"Sintomo","Numero segnalazioni","Severità media"};
	CentroVaccinale infoCentro;
	String[][] segnalazioni;

	public CentrePanel(Gui container, Cittadini client, String nome, String comune) {
		this.container=container;
		infoCentro=client.visualizzaInfoCentroVaccinale(nome,comune);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
		JButton signal =new JButton("Segnala evento avverso");
		signal.setAlignmentX(RIGHT_ALIGNMENT);
		if(checkCentre()) {
			add(signal);
		}
		
		JPanel info= new JPanel(new GridLayout(3,2,50,20));
		JLabel nomeLabel=new JLabel("Nome: "+infoCentro.getNome());
		JLabel comuneLabel=new JLabel("Comune: "+infoCentro.getComune());
		JLabel indirizzoLabel=new JLabel("Indirizzo: "+infoCentro.getIndirizzo());
		JLabel tipoLabel=new JLabel("Tipo: "+infoCentro.getTipo());
		JLabel segnalazioniLabel=new JLabel("Numero segnalazioni: "+infoCentro.getTotaleSegnalazioni());
		JLabel mediaLabel=new JLabel("Severità media: "+infoCentro.getMediaGenerale());
		
		info.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		info.add(nomeLabel);
		info.add(comuneLabel);
		info.add(indirizzoLabel);
		info.add(tipoLabel);
		info.add(segnalazioniLabel);
		info.add(mediaLabel);
		add(info);
		
		segnalazioni=new String[20][3];

		JTable resultTable=new JTable(segnalazioni,columnNames) ;
		JScrollPane scrollPane=new JScrollPane(resultTable);
		
		resultTable.setFillsViewportHeight(true);
		resultTable.setDefaultEditor(Object.class, null);
		add(scrollPane);
		
	}

	private boolean checkCentre() {
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
