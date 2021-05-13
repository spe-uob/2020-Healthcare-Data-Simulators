package com.healthcare.team.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.Dimension;

public class RegionSelection extends JFrame {
    private JPanel rootPanel;
    private JPanel hospitalOptions;
    private JButton gloucestershireButton;
    private JButton shropshireButton;
    private JButton somersetButton;

    public RegionSelection() {
        super("Healthcare Data Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        rootPanel.setOpaque(true);
        rootPanel.setBackground( Color.decode("#97BAEC") );

        add(rootPanel);
        gloucestershireButton.setBorder(BorderFactory.createLineBorder(Color.decode("#445BC1"), 4, true));
        shropshireButton.setBorder(BorderFactory.createLineBorder(Color.decode("#2C43B8"), 4, true));
        somersetButton.setBorder(BorderFactory.createLineBorder(Color.decode("#6987D5"), 4, true));

        gloucestershireButton.setPreferredSize(new Dimension(500, 50));
        shropshireButton.setPreferredSize(new Dimension(500, 50));
        somersetButton.setPreferredSize(new Dimension(500, 50));
        setTitle("Healthcare Data Simulator");
        setSize(500, 600);
        setVisible(true);

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
