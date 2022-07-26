package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserPanel extends JPanel implements ActionListener {
    Gui container;
    String[] columnNames= {"Sintomo","Severità","Note"};
    String[] data=new String[7];
    String[][] segnalazioni=new String[5][3];
    public UserPanel(Gui gui){
        container=gui;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );

        JPanel info= new JPanel(new GridLayout(3,2,50,30));
        JLabel idLabel=new JLabel("ID vaccinazione: "+data[0]);
        JLabel cfLabel=new JLabel("Codice fiscale: "+data[1]);
        JLabel nomeCognomeLabel=new JLabel("Nome e cognome: "+data[2]);
        JLabel nomeCentroLabel=new JLabel("Nome centro vaccinale: "+data[3]);
        JLabel comuneCentroLabel=new JLabel("Comune centro vaccinale: "+data[4]);
        JLabel dataLabel=new JLabel("Data vaccinazione: "+ data[5]);
        JLabel tipoLabel=new JLabel("Tipo vaccino: "+data[6]);

        info.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        info.add(idLabel);
        info.add(cfLabel);
        info.add(nomeCognomeLabel);
        info.add(tipoLabel);
        info.add(dataLabel);
        info.add(nomeCentroLabel);
        add(info);

        JTable resultTable=new JTable(segnalazioni,columnNames) ;
        JScrollPane scrollPane=new JScrollPane(resultTable);

        //resultTable.setFillsViewportHeight(true);
        resultTable.setDefaultEditor(Object.class, null);
        add(scrollPane);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
