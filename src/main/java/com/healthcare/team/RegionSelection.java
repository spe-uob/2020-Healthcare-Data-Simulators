package com.healthcare.team;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.security.GeneralSecurityException;

public class RegionSelection extends JFrame{
    private JPanel rootPanel;
    private JButton otherButton;
    private JButton somersetButton;
    private JPanel hospitalOptions;

    public RegionSelection() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(rootPanel);
        setTitle("Healthcare Data Simulator");
        setSize(500,600);
        somersetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratorForm generatorForm = new GeneratorForm();
                setVisible(false);
                generatorForm.setVisible(true);

            }
        });
    }

}
