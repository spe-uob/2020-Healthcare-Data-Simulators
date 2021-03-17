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


    public GeneratorForm() {
        add(rootPanel);
        setTitle("Healthcare Data Simulator");
        setSize(400,500);

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                GUIForm guiForm = new GUIForm();
                guiForm.setVisible(true);
            }
        });
    }

}
