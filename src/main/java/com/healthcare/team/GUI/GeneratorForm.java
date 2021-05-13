package com.healthcare.team.GUI;

import com.healthcare.team.BashProcess.ConfigurationSynthea;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.Dimension;

public class GeneratorForm extends JFrame {
    private JPanel rootPanel;
    private JButton generateSyntheticDataButton;
    private JButton addHealthDataFlatButton;
    private JButton backToMenuButton;
    private JTextArea textArea1;
    private JTextField selectedRegion;


    public GeneratorForm() {
        super("Healthcare Data Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(rootPanel);
        rootPanel.setBackground( Color.decode("#97BAEC") );
        rootPanel.setForeground( Color.decode("#2C43B8"));
        generateSyntheticDataButton.setPreferredSize(new Dimension(245, 75));
        addHealthDataFlatButton.setPreferredSize(new Dimension(245, 75));
        generateSyntheticDataButton.setBorder(BorderFactory.createLineBorder(Color.decode("#6987D5"), 4, true));
        addHealthDataFlatButton.setBorder(BorderFactory.createLineBorder(Color.decode("#445BC1"), 4, true));
        Border borderCustom=BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.decode("#445BC1"), Color.decode("#1727AE")); // top, bottom colours
        backToMenuButton.setPreferredSize(new Dimension(80, 30));
        backToMenuButton.setBorder(borderCustom);

        setTitle("Healthcare Data Simulator");
        setSize(500,600);

        backToMenuButton.addActionListener(new ActionListener() {
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

    protected void updateRegionText(String regionName) {
        selectedRegion.setText(regionName);
        System.out.println("Region name " + regionName);
    }
}
