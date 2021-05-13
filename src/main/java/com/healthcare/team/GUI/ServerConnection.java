package com.healthcare.team.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.Dimension;

public class ServerConnection extends JFrame {

    private JTextField username;
    private JTextField password;
    private JTextField region;
    private JTextField clientID;
    private JPanel rootPanel;
    private JButton nextButton;
    private JLabel regionLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel clientIDLabel;



    public ServerConnection() {
        super("Healthcare Data Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(rootPanel);
        rootPanel.setBackground( Color.decode("#97BAEC") );
        Border borderCustom=BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.decode("#445BC1"), Color.decode("#1727AE")); // top, bottom colours
        nextButton.setPreferredSize(new Dimension(60, 30));
        nextButton.setBorder(borderCustom);
        Border usualBorder =BorderFactory.createLineBorder(Color.decode("#BBDFFA"), 4, true);
        clientID.setBorder(usualBorder);
        username.setBorder(usualBorder);
        region.setBorder(usualBorder);
        password.setBorder(usualBorder);
        setTitle("Healthcare Data Simulator");
        setSize(500,600);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // OAuth oAuth = new OAuth(clientID.getText(), region.getText(), username.getText(), password.getText());

                //if(Utils.isValidString(oAuth.generateToken())){
                    setVisible(false);
                    RegionSelection regionSelection = new RegionSelection();
                    regionSelection.setVisible(true);
                    //oAuth.sendToRabbitMQ();
                //}
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
