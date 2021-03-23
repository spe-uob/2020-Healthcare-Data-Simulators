package com.healthcare.team;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class configurationSynthea extends JFrame {
    private JTextField size;
    private JPanel panel1;
    private JTextField somersetTextField;
    private JTextField minAge;
    private JComboBox module;
    private JComboBox sex;
    private JTextField maxAge;
    private JButton generateButton;
    private JButton sendButton;

    public configurationSynthea() {
        add(panel1);
        setTitle("Healthcare Data Simulator");
        setSize(500,600);


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


}
