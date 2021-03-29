package com.healthcare.team;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneratorForm extends JFrame {
    private JPanel rootPanel;
    private JButton generateSyntheticDataButton;
    private JButton addHealthDataFlatButton;
    private JButton backToMainMenuButton;
    private JTextArea textArea1;
    private JTextField selectedRegion;


    public GeneratorForm() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(rootPanel);
        setTitle("Healthcare Data Simulator");
        setSize(500,600);

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RegionSelection regionSelection = new RegionSelection();
                regionSelection.setVisible(true);
            }
        });
        generateSyntheticDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                ConfigurationSynthea configurationGUI = new ConfigurationSynthea();
                configurationGUI.setVisible(true);

                configurationGUI.updateRegionText(selectedRegion.getText());
            }
        });
    }

    public void updateRegionText(String regionName) {
        selectedRegion.setText(regionName);
        System.out.println("Region name " + regionName);
    }
}
