package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPanel extends JPanel implements ActionListener {
    Gui container;
    public RegisterPanel(Gui gui){
        container=gui;
        setLayout(new GridBagLayout());
        JLabel idLabel=new JLabel("ID vaccinazione: ");
        JLabel userLabel=new JLabel("Username: ");
        JLabel passwordLabel=new JLabel("Password: ");
        JLabel nomeLabel=new JLabel("Nome e cognome: ");
        JLabel emailLabel=new JLabel("e-mail: ");
        JLabel cfLabel=new JLabel("Codice fiscale: ");
        JTextField userTextField=new JTextField();
        JTextField passwordTextField=new JTextField();
        JTextField idTextField=new JTextField();
        JTextField nomeTextField=new JTextField();
        JTextField emailTextField=new JTextField();
        JTextField cfTextField=new JTextField();

        JButton registerButton=new JButton("Registrati");
        GridBagConstraints gbc=new GridBagConstraints();

        setBorder(BorderFactory.createEmptyBorder(10,60,60,60));

        userLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        nomeLabel.setHorizontalAlignment(JLabel.CENTER);
        emailLabel.setHorizontalAlignment(JLabel.CENTER);
        cfLabel.setHorizontalAlignment(JLabel.CENTER);
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridy=0;
        gbc.weightx=0.25;
        add(nomeLabel,gbc);
        gbc.weightx=1;
        add(nomeTextField,gbc);
        gbc.gridy=1;
        gbc.weightx=0.25;
        add(cfLabel,gbc);
        gbc.weightx=1;
        add(cfTextField,gbc);
        gbc.gridy=2;
        gbc.weightx=0.25;
        add(emailLabel,gbc);
        gbc.weightx=1;
        add(emailTextField,gbc);
        gbc.gridy=3;
        gbc.weightx=0.25;
        add(userLabel,gbc);
        gbc.weightx=1;
        add(userTextField,gbc);
        gbc.gridy=4;
        gbc.weightx=0.25;
        add(passwordLabel,gbc);
        gbc.weightx=1;
        add(passwordTextField,gbc);
        gbc.gridy=5;
        gbc.weightx=0.25;
        add(idLabel,gbc);
        gbc.weightx=1;
        add(idTextField,gbc);

        gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.gridy=6;
        gbc.gridx=0;
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        gbc.gridheight=GridBagConstraints.REMAINDER;
        add(registerButton,gbc);
        registerButton.setActionCommand("register");
        registerButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("register")){
            container.changePanel("user");
        }
    }
}
