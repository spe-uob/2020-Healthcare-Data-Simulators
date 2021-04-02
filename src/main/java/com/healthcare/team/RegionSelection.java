package com.healthcare.team;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class RegionSelection extends JFrame {
    private JPanel rootPanel;
    private JButton otherButton;
    private JPanel hospitalOptions;
    private JButton gloucestershireButton;
    private JButton shropshireButton;
    private JButton somersetButton;


    public RegionSelection() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(rootPanel);
        setTitle("Healthcare Data Simulator");
        setSize(500, 600);

        somersetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GeneratorForm generatorForm = new GeneratorForm();
                setVisible(false);
                generatorForm.updateRegionText(somersetButton.getText());
                generatorForm.setVisible(true);

            }
        });

        gloucestershireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratorForm generatorForm = new GeneratorForm();
                setVisible(false);
                generatorForm.updateRegionText(gloucestershireButton.getText());
                generatorForm.setVisible(true);

            }
        });

        shropshireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratorForm generatorForm = new GeneratorForm();
                setVisible(false);
                generatorForm.updateRegionText(shropshireButton.getText());
                generatorForm.setVisible(true);

            }
        });
    }
}
