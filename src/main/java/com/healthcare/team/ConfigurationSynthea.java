//package com.healthcare.team;
//
//import static com.healthcare.team.commons.Constants.*;
//import static java.util.stream.Collectors.toList;
//
//import com.healthcare.team.commons.Modules;
//import com.healthcare.team.commons.Utils;
//import com.healthcare.team.commons.Validations;
//import com.healthcare.team.scheduler.JobScheduler;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.List;
//
//public class ConfigurationSynthea extends JFrame {
//    private JPanel pane;
//    private JTextField size;
//    private JTextField somersetTextField;
//    private JTextField minAge;
//    private JComboBox module;
//    private JComboBox sex;
//    private JTextField maxAge;
//    private JButton generateButton;
//    private JButton sendButton;
//    private JButton backButton;
//    private JProgressBar bar;
//    private JTextField timer;
//    private JButton stopButton;
//    private JLabel populationLbl;
//    private JLabel ageLbl;
//    private JLabel genderLbl;
//    private JLabel moduleLbl;
//    private JLabel regionLbl;
//    private JLabel timerLbl;
//    private JButton pauseButton;
//    private JLabel jobStatus;
//    private JProgressBar progressBar1;
//    private DefaultComboBoxModel cbSexModel = new DefaultComboBoxModel();
//    private DefaultComboBoxModel cbModuleModel = new DefaultComboBoxModel();
//
//    private boolean isValidJobExecution;
//
//    public ConfigurationSynthea() {
//        super("Healthcare Data Simulator");
//        this.setTitle("Healthcare Data Simulator");
//        this.setSize(500, 600);
//
//        Container pane = this.getContentPane();
//        pane.setLayout(new GridBagLayout());
//
//        GridBagConstraints c = new GridBagConstraints();
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 0;
//        c.weighty = 0.5;
//        c.anchor = GridBagConstraints.FIRST_LINE_START;
//        pane.add(backButton, c);
//
//        bar = new JProgressBar(0, 100);
//        bar.setBounds(0, 0, 25, 10);
//        bar.setVisible(false);
//        bar.setStringPainted(true);
//        bar.setFont(new Font("Raanana", Font.ITALIC, 10));
//        bar.setForeground(Color.RED);
//        bar.setBackground(Color.WHITE);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 5;
//        c.gridy = 0;
//        c.weightx = 0.5;
//        c.gridwidth = 10;
//        c.insets = new Insets(0, 2, 5, 0);
//        c.anchor = GridBagConstraints.PAGE_START;
//        pane.add(bar, c);
//
//        c.gridx = 0;
//        c.gridy = 5;
//        c.weighty = 0.5;
//        pane.add(populationLbl, c);
//
//        c.gridx = 2;
//        c.gridy = 5;
//        c.gridwidth = 2;
//        pane.add(size, c);
//
//        c.gridx = 0;
//        c.gridy = 10;
//        pane.add(ageLbl, c);
//
//        c.gridx = 2;
//        c.gridy = 10;
//        c.weightx = 0.5;
//        c.gridwidth = 1;
//        pane.add(minAge, c);
//
//        c.gridx = 4;
//        c.gridy = 10;
//        c.weightx = 0.5;
//        c.gridwidth = 10;
//        c.ipadx = 2;
//        pane.add(maxAge, c);
//
//        c.gridx = 0;
//        c.gridy = 15;
//        c.weightx = 1.5;
//        pane.add(genderLbl, c);
//        sex = new JComboBox(cbSexModel);
//
//        for (final String gender : Validations.validGenders) {
//            EventQueue.invokeLater(() -> {// updates to the Swing GUI must be done on EDT
//                cbSexModel.addElement(gender);
//            });
//        }
//        sex.setModel(cbSexModel);
//        c.gridx = 2;
//        c.gridy = 15;
//        pane.add(sex, c);
//
//        c.gridx = 0;
//        c.gridy = 20;
//        pane.add(moduleLbl, c);
//        module = new JComboBox(cbModuleModel);
//        List<String> modules = Arrays.stream(Modules.values()).map(Modules::getDescription).collect(toList());
//        for (final String mdl : modules) {
//            EventQueue.invokeLater(() -> {// updates to the Swing GUI must be done on EDT
//                cbModuleModel.addElement(mdl);
//            });
//        }
//        module.setModel(cbModuleModel);
//        pane.add(module);
//        c.gridx = 2;
//        c.gridy = 20;
//        pane.add(module, c);
//
//        c.gridx = 0;
//        c.gridy = 25;
//        pane.add(regionLbl, c);
//        c.gridx = 2;
//        c.gridy = 25;
//        c.gridwidth = 2;
//        somersetTextField.setColumns(25);
//        pane.add(somersetTextField, c);
//
//        c.gridx = 0;
//        c.gridy = 30;
//        pane.add(timerLbl, c);
//        c.gridx = 2;
//        c.gridy = 30;
//        c.gridwidth = 2;
//        pane.add(timer, c);
//
//        c.gridx = 0;
//        c.gridy = 35;
//        c.gridwidth = 5;
//        c.insets = new Insets(0, 100, 0, 0);
//        pane.add(jobStatus, c);
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 40;
//        c.gridwidth = 1;
//        c.weightx = 0.5;
//        c.gridheight = 1;
//        c.ipady = 5;
//        c.ipadx = 2;
//        c.insets = new Insets(5, 0,5 ,2);
//        pane.add(generateButton, c);
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 2;
//        c.gridy = 40;
//        c.gridwidth = 1;
//        c.weightx = 0.5;
//        c.gridheight = 1;
//        c.ipady = 5;
//        c.ipadx = 2;
//        c.insets = new Insets(5, 2,5 ,2);
//        pauseButton.setVisible(Boolean.FALSE);
//        pane.add(pauseButton, c);
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 6;
//        c.gridy = 40;
//        c.gridwidth = 2;
//        c.weightx = 0.5;
//        c.gridheight = 1;
//        c.ipady = 5;
//        c.ipadx = 4;
//        c.insets = new Insets(5, 2,5 ,2);
//        stopButton.setVisible(Boolean.FALSE);
//        pane.add(stopButton, c);
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 8;
//        c.gridy = 40;
//        c.gridwidth = 1;
//        c.weightx = 0.5;
//        c.gridheight = 1;
//        c.ipady = 5;
//        c.ipadx = 2;
//        c.insets = new Insets(5, 2,5 ,0);
//        pane.add(sendButton, c);
//
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//                GeneratorForm generatorForm = new GeneratorForm();
//                generatorForm.setVisible(true);
//            }
//        });
//
//        generateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    isValidJobExecution = validJobExecution();
//                    new TaskBashProcess().execute();
//                    if (isValidJobExecution) {
//                        Compute compute = buildCompute();
//                        executeJob(compute);
//                    } else {
//                        notifyUser();
//                    }
//                } catch (IllegalArgumentException ex) {
//                    alertUser();
//                }
//            }
//        });
//
//        sendButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Now we are sending patients with NHSNumber encrypted
//                //in suitable queues according to the region patients are generated from
//                new ParseCSV().sendPatientsToRabbit(somersetTextField.getText());
//            }
//        });
//
//        stopButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (timer.getText().isBlank() && generateButton.isEnabled()) {
//                    stopButton.setVisible(Boolean.FALSE);
//                }
//                JobScheduler.stop();
//                generateButton.setVisible(Boolean.TRUE);
//                jobStatus.setText("");
//                pauseButton.setVisible(Boolean.FALSE);
//                stopButton.setVisible(Boolean.FALSE);
//            }
//        });
//
//        pauseButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (Utils.isStringInvalid(timer.getText()) && generateButton.isEnabled()) {
//                    pauseButton.setVisible(Boolean.FALSE);
//                }
//                if (pauseButton.getText().equalsIgnoreCase(ACTION_PAUSE_JOB)) {
//                    JobScheduler.pause();
//                    pauseButton.setText(ACTION_RESUME_JOB);
//                    jobStatus.setText("Job is paused");
//                } else if (pauseButton.getText().equalsIgnoreCase(ACTION_RESUME_JOB)) {
//                    JobScheduler.resume(Integer.parseInt(timer.getText()));
//                    pauseButton.setText(ACTION_PAUSE_JOB);
//                    jobStatus.setText("Job runs in background");
//                }
//            }
//        });
//    }
//
//    private boolean validJobExecution() {
//        String interval = timer.getText();
//        String region = somersetTextField.getText();
//        return Utils.isNumeric(interval)
//                && Utils.isValidString(region)
//                && Integer.parseInt(interval) >= JOB_MIN_INTERVAL_IN_SECONDS;
//    }
//
////    private int getJobInterval() {
////        return Integer.parseInt(size.getText()) * JOB_MIN_INTERVAL_IN_SECONDS;
////    }
//
//    private Compute buildCompute() {
//        return new Compute(size.getText(), minAge.getText(), maxAge.getText(), Objects.requireNonNull(sex.getSelectedItem()).toString(),
//                Objects.requireNonNull(module.getSelectedItem()).toString(), somersetTextField.getText());
//    }
//
//    private void executeJob(Compute compute) {
//        JobScheduler.init(compute, somersetTextField.getText(),
//                Integer.parseInt(timer.getText()),
//                "START");
//    }
//
//    public void updateRegionText(String regionName) {
//        somersetTextField.setText(regionName);
//        System.out.println("Region name " + regionName);
//    }
//
//    protected void alertUser() {
//        JOptionPane.showMessageDialog(null,
//                "Please provide a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
//    }
//
//    private boolean checkTimerForNotification(String interval) {
//        if (!interval.isBlank() && Utils.isNumeric(interval)
//                && Integer.parseInt(interval) < JOB_MIN_INTERVAL_IN_SECONDS) {
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//    }
//
//    private void notifyUser() {
//        if (checkTimerForNotification(timer.getText())) {
//            JOptionPane.showMessageDialog(null,
//                    "Please provide a time bigger than 15 seconds for the scheduler to keep working",
//                    "Notification", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//
//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }
//
//    class TaskBashProcess extends SwingWorker<Void, String> {
//
//        private boolean isUserAlerted;
//
//        @Override
//        public Void doInBackground() {
//            Compute computer = null;
//            try {
//                computer = buildCompute();
//            } catch (IllegalArgumentException e) {
//                alertUser();
//                isUserAlerted = Boolean.TRUE;
//            }
//
//            ProcessBuilder processBuilder = new ProcessBuilder();
//            processBuilder.command(computer.processParameters(somersetTextField.getText()));
//            try {
//                Process process = processBuilder.start();
//                StringBuilder output = new StringBuilder();
//                BufferedReader reader = new BufferedReader(
//                        new InputStreamReader(process.getInputStream())
//                );
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    output.append(line).append('\n');
//                    publish(line);
//                }
//
//                if (computer.showAlert(output.toString())) {
//                    computer.alertUser();
//                    throw new IOException("Generating failed");
//                }
//
//                computer.informUser();
//                System.out.println("Success!");
//            } catch (IOException e) {
//                System.out.println(e);
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void process(List<String> chunks) {
//            bar.setIndeterminate(true);
//            bar.setString("Loading...");
//            bar.setVisible(true);
//        }
//
//        @Override
//        protected void done() {
//            try {
//                if (!isUserAlerted) {
//                    bar.setIndeterminate(false);
//                    bar.setString("Finished!");
//                    if(isValidJobExecution){
//                        jobStatus.setText("Job runs in background");
//                        generateButton.setVisible(Boolean.FALSE);
//                        pauseButton.setVisible(Boolean.TRUE);
//                        stopButton.setVisible(Boolean.TRUE);
//                        sendButton.setVisible(Boolean.FALSE);
//                    }
//                }
//            } catch (Exception ignore) {
//            }
//        }
//    }
//}

package com.healthcare.team;

import static com.healthcare.team.commons.Constants.*;
import static java.util.stream.Collectors.toList;

import com.healthcare.team.commons.Modules;
import com.healthcare.team.commons.Utils;
import com.healthcare.team.commons.Validations;
import com.healthcare.team.scheduler.JobScheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.List;

public class ConfigurationSynthea extends JFrame {
    private JPanel pane;
    private JTextField size;
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
    private JLabel populationLbl;
    private JLabel ageLbl;
    private JLabel genderLbl;
    private JLabel moduleLbl;
    private JLabel regionLbl;
    private JLabel timerLbl;
    private JButton pauseButton;
    private JLabel jobStatus;
    private JProgressBar progressBar1;
    private DefaultComboBoxModel cbSexModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbModuleModel = new DefaultComboBoxModel();

    private boolean isValidJobExecution;

    public ConfigurationSynthea() {
        super("Healthcare Data Simulator");
        this.setTitle("Healthcare Data Simulator");
        this.setSize(500, 600);

        Container pane = this.getContentPane();
        pane.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        pane.add(backButton, c);

        bar = new JProgressBar(0, 100);
        bar.setBounds(0, 0, 25, 20);
        bar.setVisible(false);
        bar.setStringPainted(true);
        bar.setFont(new Font("Raanana", Font.ITALIC, 10));
        bar.setForeground(Color.RED);
        bar.setBackground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 41;
        c.weightx=2;
        c.insets = new Insets(0, 2, 5, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        pane.add(bar, c);

        c.gridx = 0;
        c.gridy = 5;
        c.weighty = 0.5;
        pane.add(populationLbl, c);

        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 10;
        pane.add(size, c);

        c.gridx = 0;
        c.gridy = 10;
        pane.add(ageLbl, c);

        c.gridx = 2;
        c.gridy = 10;
        c.weightx = 0.5;
        c.gridwidth = 10;
        pane.add(minAge, c);

        c.gridx = 2;
        c.gridy = 11;
        c.weightx = 0.5;
        c.gridwidth = 10;
        pane.add(maxAge, c);

        c.gridx = 0;
        c.gridy = 15;
        c.weightx = 1.5;
        pane.add(genderLbl, c);
        sex = new JComboBox(cbSexModel);

        for (final String gender : Validations.validGenders) {
            EventQueue.invokeLater(() -> {// updates to the Swing GUI must be done on EDT
                cbSexModel.addElement(gender);
            });
        }
        sex.setModel(cbSexModel);
        c.gridx = 2;
        c.gridy = 15;
        pane.add(sex, c);

        c.gridx = 0;
        c.gridy = 20;
        pane.add(moduleLbl, c);
        module = new JComboBox(cbModuleModel);
        List<String> modules = Arrays.stream(Modules.values()).map(Modules::getDescription).collect(toList());
        for (final String mdl : modules) {
            EventQueue.invokeLater(() -> {// updates to the Swing GUI must be done on EDT
                cbModuleModel.addElement(mdl);
            });
        }
        module.setModel(cbModuleModel);
        pane.add(module);
        c.gridx = 2;
        c.gridy = 20;
        pane.add(module, c);

        c.gridx = 0;
        c.gridy = 25;
        pane.add(regionLbl, c);
        c.gridx = 2;
        c.gridy = 25;
        c.gridwidth = 2;
        somersetTextField.setColumns(25);
        pane.add(somersetTextField, c);

        c.gridx = 0;
        c.gridy = 30;
        pane.add(timerLbl, c);
        c.gridx = 2;
        c.gridy = 30;
        c.gridwidth = 2;
        pane.add(timer, c);

        c.gridx = 2;
        c.gridy = 31;
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 0, 0);
        pane.add(jobStatus, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridheight = 1;

        c.insets = new Insets(5, 0,5 ,2);
        pane.add(generateButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 43;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridheight = 1;

        c.insets = new Insets(5, 2,5 ,2);
        pauseButton.setVisible(Boolean.FALSE);
        pane.add(pauseButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 42;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridheight = 1;

        c.insets = new Insets(5, 2,5 ,2);
        stopButton.setVisible(Boolean.FALSE);
        pane.add(stopButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 40;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridheight = 1;

        c.insets = new Insets(5, 2,5 ,0);
        pane.add(sendButton, c);

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
                try {
                    isValidJobExecution = validJobExecution();
                    new TaskBashProcess().execute();
                    if (isValidJobExecution) {
                        Compute compute = buildCompute();
                        executeJob(compute);
                    } else {
                        notifyUser();
                    }
                } catch (IllegalArgumentException ex) {
                    alertUser();
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Now we are sending patients with NHSNumber encrypted
                //in suitable queues according to the region patients are generated from
                new ParseCSV().sendPatientsToRabbit(somersetTextField.getText());
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer.getText().isBlank() && generateButton.isEnabled()) {
                    stopButton.setVisible(Boolean.FALSE);
                }
                JobScheduler.stop();
                generateButton.setVisible(Boolean.TRUE);
                jobStatus.setText("");
                pauseButton.setVisible(Boolean.FALSE);
                stopButton.setVisible(Boolean.FALSE);
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utils.isStringInvalid(timer.getText()) && generateButton.isEnabled()) {
                    pauseButton.setVisible(Boolean.FALSE);
                }
                if (pauseButton.getText().equalsIgnoreCase(ACTION_PAUSE_JOB)) {
                    JobScheduler.pause();
                    pauseButton.setText(ACTION_RESUME_JOB);
                    jobStatus.setText("Job is paused");
                } else if (pauseButton.getText().equalsIgnoreCase(ACTION_RESUME_JOB)) {
                    JobScheduler.resume(Integer.parseInt(timer.getText()));
                    pauseButton.setText(ACTION_PAUSE_JOB);
                    jobStatus.setText("Job runs in background");
                }
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

    private int getJobInterval() {
        return Integer.parseInt(size.getText()) * JOB_MIN_INTERVAL_IN_SECONDS;
    }

    private Compute buildCompute() {
        return new Compute(size.getText(), minAge.getText(), maxAge.getText(), Objects.requireNonNull(sex.getSelectedItem()).toString(),
                Objects.requireNonNull(module.getSelectedItem()).toString(), somersetTextField.getText());
    }

    private void executeJob(Compute compute) {
        JobScheduler.init(compute, somersetTextField.getText(),
                Integer.parseInt(timer.getText()),
                "START");
    }

    public void updateRegionText(String regionName) {
        somersetTextField.setText(regionName);
        System.out.println("Region name " + regionName);
    }

    protected void alertUser() {
        JOptionPane.showMessageDialog(null,
                "Please provide a valid input!", "Error!", JOptionPane.ERROR_MESSAGE);
    }

    private boolean checkTimerForNotification(String interval) {
        if (!interval.isBlank() && Utils.isNumeric(interval)
                && Integer.parseInt(interval) < JOB_MIN_INTERVAL_IN_SECONDS) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private void notifyUser() {
        if (checkTimerForNotification(timer.getText())) {
            JOptionPane.showMessageDialog(null,
                    "Please provide a time bigger than 15 seconds for the scheduler to keep working",
                    "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    class TaskBashProcess extends SwingWorker<Void, String> {

        private boolean isUserAlerted;

        @Override
        public Void doInBackground() {
            Compute computer = null;
            try {
                computer = buildCompute();
            } catch (IllegalArgumentException e) {
                alertUser();
                isUserAlerted = Boolean.TRUE;
            }

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
                if (!isUserAlerted) {
                    bar.setIndeterminate(false);
                    bar.setString("Finished!");
                    if(isValidJobExecution){
                        jobStatus.setText("Job runs in background");
                        generateButton.setVisible(Boolean.FALSE);
                        pauseButton.setVisible(Boolean.TRUE);
                        stopButton.setVisible(Boolean.TRUE);
                        sendButton.setVisible(Boolean.FALSE);
                    }
                }
            } catch (Exception ignore) {
            }
        }
    }
}
