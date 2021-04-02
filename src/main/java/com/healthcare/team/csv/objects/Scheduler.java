package com.healthcare.team.csv.objects;

import com.healthcare.team.GeneratorForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scheduler extends JFrame {
    private JButton backToMainMenuButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JProgressBar progressBar1;
    private JButton startGeneratingButton1;
    private JButton stopGeneratingButton;
    private JTextField size;
    private JPanel panelScheduler;
    private JProgressBar bar;

    public Scheduler() {

        this.add(panelScheduler);
        this.setTitle("Healthcare Data Simulator");
        this.setSize(500, 600);

        bar = new JProgressBar(0, 100);

        panelScheduler.add(bar);
        bar.setBounds(0, 0, 10, 10);
        bar.setVisible(false);
        bar.setStringPainted(true);
        bar.setFont(new Font("Raanana", Font.ITALIC, 10));
        bar.setForeground(Color.RED);
        bar.setBackground(Color.WHITE);

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GeneratorForm generatorForm = new GeneratorForm();
                generatorForm.setVisible(true);
            }
        });


    }
}
