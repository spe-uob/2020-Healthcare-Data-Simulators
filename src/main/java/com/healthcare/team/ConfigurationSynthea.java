package com.healthcare.team;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ConfigurationSynthea extends JFrame {
    private JTextField size;
    private JPanel panel1;
    private JTextField somersetTextField;
    private JTextField minAge;
    private JComboBox module;
    private JComboBox sex;
    private JTextField maxAge;
    private JButton generateButton;
    private JButton sendButton;
    private JButton backButton;

    public ConfigurationSynthea() {
        add(panel1);
        setTitle("Healthcare Data Simulator");
        setSize(500, 600);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GeneratorForm generatorForm = new GeneratorForm();
                generatorForm.setVisible(true);
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Compute computer = new Compute(size.getText(), minAge.getText(), maxAge.getText(), Objects.requireNonNull(sex.getSelectedItem()).toString(),
                        Objects.requireNonNull(module.getSelectedItem()).toString().toLowerCase(), Objects.requireNonNull(somersetTextField.getText().toString()));

               computer.generatePatient();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParseCSV parseCSV = new ParseCSV();
                try {
                    parseCSV.sendToRabbit();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void updateRegionText(String regionName) {
        somersetTextField.setText(regionName);
        System.out.println("Region name " + regionName);
    }
}
