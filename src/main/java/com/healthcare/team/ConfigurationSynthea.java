package com.healthcare.team;

import static com.healthcare.team.commons.Constants.JOB_MIN_INTERVAL_IN_SECONDS;

import com.healthcare.team.commons.Utils;
import com.healthcare.team.scheduler.JobScheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.List;

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
    private JProgressBar bar;
    private JTextField timer;
    private JButton stopButton;

    public ConfigurationSynthea() {
        super("Healthcare Data Simulator");
        this.add(panel1);
        this.setTitle("Healthcare Data Simulator");
        this.setSize(500, 600);

        bar = new JProgressBar(0, 100);

        panel1.add(bar);
        bar.setBounds(0, 0, 10, 10);
        bar.setVisible(false);
        bar.setStringPainted(true);
        bar.setFont(new Font("Raanana", Font.ITALIC, 10));
        bar.setForeground(Color.RED);
        bar.setBackground(Color.WHITE);

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
                new TaskBashProcess().execute();
                executeJob();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Now we are sending patients with NHSNumber encrypted
                new ParseCSV().sendPatientsToRabbit(somersetTextField.getText());
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JobScheduler.init(null, "", Integer.parseInt(timer.getText()), "STOP");
            }
        });
    }

    private boolean validJobExecution() {
        String interval = timer.getText();
        String region = somersetTextField.getText();
        return Utils.isNumeric(interval)
                && Utils.isValidString(region)
                && Integer.parseInt(interval) >= JOB_MIN_INTERVAL_IN_SECONDS;
    }

    private Compute buildCompute() {
        return new Compute(size.getText(), minAge.getText(), maxAge.getText(), Objects.requireNonNull(sex.getSelectedItem()).toString(),
                Objects.requireNonNull(module.getSelectedItem()).toString(), somersetTextField.getText());
    }

    private void executeJob() {
        if (validJobExecution()) {
            JobScheduler.init(buildCompute(), somersetTextField.getText(),
                    Integer.parseInt(timer.getText()),
                    "START");
        }
    }

    public void updateRegionText(String regionName) {
        somersetTextField.setText(regionName);
        System.out.println("Region name " + regionName);
    }

    class TaskBashProcess extends SwingWorker<Void, String> {
        @Override
        public Void doInBackground() {
            Compute computer = buildCompute();
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(computer.processParameters(somersetTextField.getText()));
            try {
                Process process = processBuilder.start();
                StringBuilder output = new StringBuilder();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );

                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append('\n');
                    publish(line);
                }

                if (computer.showAlert(output.toString())) {
                    computer.alertUser();
                    throw new IOException("Generating failed");
                }

                computer.informUser();
                System.out.println("Success!");
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void process(List<String> chunks) {
            bar.setIndeterminate(true);
            bar.setString("Loading...");
            bar.setVisible(true);
        }

        @Override
        protected void done() {
            try {
                bar.setIndeterminate(false);
                bar.setString("Finished!");
                //TODO bar.setVisible(false);
            } catch (Exception ignore) {
            }
        }
    }
}
