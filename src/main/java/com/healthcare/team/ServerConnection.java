package com.healthcare.team;

import com.healthcare.team.commons.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setTitle("Healthcare Data Simulator");
        setSize(500,600);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //OAuth oAuth = new OAuth(clientID.getText(), region.getText(), username.getText(), password.getText());

                //if(Utils.isValidString(oAuth.generateToken())){
                    setVisible(false);
                    RegionSelection regionSelection = new RegionSelection();
                    regionSelection.setVisible(true);
                  //  oAuth.sendToRabbitMQ();
                //}
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
