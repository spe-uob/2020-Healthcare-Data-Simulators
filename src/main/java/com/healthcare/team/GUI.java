package com.healthcare.team;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import org.apache.log4j.BasicConfigurator;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

public class GUI {
    public GUI() {}

    public enum PROTOCOLS{
        HTTP,
        SFTP,
        MESSAGE_BROKER,
    }
    public enum DATA{
        //MIRTH,
        SYNTHEA,
        BINARY,
    }

    public void Run() throws FileNotFoundException {
        FlatIntelliJLaf.install();
        BasicConfigurator.configure();

        final MessageBroker[] msgBroker = new MessageBroker[1];
        final String[] tokenFinal = new String[1];
        final PROTOCOLS[] protocol = new PROTOCOLS[1];
        final File[] selectedFile = {new File("")};
        final ChannelSftp[] channelSftp = {null};
        //GUI

        allFramesSetup(msgBroker, tokenFinal, protocol, selectedFile, channelSftp);
    }

    private void allFramesSetup(MessageBroker[] msgBroker, String[] tokenFinal,
                                PROTOCOLS[] protocol, File[] selectedFile,
                                ChannelSftp[] channelSftp) throws FileNotFoundException {
        // Constraints on positioning
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.SOUTH;

        // Frames
        final JFrame frame = new JFrame("Healthcare Data Simulators");
        frame.setResizable(false);
        frame.setSize(375, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JFrame sftpFrame = new JFrame("SFTP Connection details");
        sftpFrame.setResizable(false);
        sftpFrame.setSize(375, 600);
        sftpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JFrame messageBrokerFrame = new JFrame("Message Broker Details");
        messageBrokerFrame.setResizable(false);
        messageBrokerFrame.setSize(375, 600);
        messageBrokerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JFrame httpFrame = new JFrame("HTTP Connection details");
        httpFrame.setResizable(false);
        httpFrame.setSize(375, 600);
        httpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JFrame connectionOptionsFrame = new JFrame("Connection options");
        connectionOptionsFrame.setResizable(false);
        connectionOptionsFrame.setSize(375, 600);
        connectionOptionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //OPTIONS MENU
        final JButton httpOption = new JButton("HTTP");
        final JButton sftpOption = new JButton("SFTP");
        final JButton messageBrokerOption = new JButton("Message Broker");
        JPanel panelOptions = new JPanel(new GridBagLayout());
        panelOptions.add(httpOption);
        panelOptions.add(sftpOption);
        panelOptions.add(messageBrokerOption);
        connectionOptionsFrame.add(panelOptions);
        connectionOptionsFrame.setVisible(true);
        //listeners for each button
        sftpOption.addActionListener(actionEvent -> {
            protocol[0] = PROTOCOLS.SFTP;
            connectionOptionsFrame.setVisible(false);
            sftpFrame.setVisible(true);
        });
        messageBrokerOption.addActionListener(actionEvent -> {
            protocol[0] = PROTOCOLS.MESSAGE_BROKER;
            connectionOptionsFrame.setVisible(false);
            messageBrokerFrame.setVisible(true);
        });
        httpOption.addActionListener(actionEvent -> {
            protocol[0] = PROTOCOLS.HTTP;
            connectionOptionsFrame.setVisible(false);
            httpFrame.setVisible(true);
        });

        final JFrame generateFrame = new JFrame("Custom settings for generation");
        generateFrame.setResizable(false);
        generateFrame.setSize(375, 600);
        generateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JFrame uploadFrame = new JFrame("Upload a file directly");
        uploadFrame.setResizable(false);
        uploadFrame.setSize(375, 600);
        uploadFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JFrame uploadFrameConv = new JFrame("Convertor");
        uploadFrameConv.setSize(375, 600);

        messageBrokerSetup(msgBroker, frame, messageBrokerFrame);

        // SFTP
        final JButton buttonNextSFTP = new JButton("Next");
        final TextField sftpRemoteHost_tb = new TextField("snowy.cs.bris.ac.uk");
        final TextField sftpUsername_tb = new TextField("ab19123");
        final JPasswordField sftpPassword_tb = new JPasswordField("TestParola");
        sftpSetup(sftpFrame, buttonNextSFTP, sftpRemoteHost_tb, sftpUsername_tb, sftpPassword_tb);
        buttonNextSFTP.addActionListener(actionEvent -> {
            SFTP sftp = new SFTP(sftpRemoteHost_tb.getText(), sftpUsername_tb.getText(), new String(sftpPassword_tb.getPassword()));
            try {
                channelSftp[0] = sftp.establishConnection();
                if (channelSftp[0] == null) JOptionPane.showMessageDialog(null,
                        "Could not establish connection!\nTry Again!", "Error", JOptionPane.ERROR_MESSAGE);
                else {
                    JOptionPane.showMessageDialog(null, "Connection established!", "Success!",
                            JOptionPane.INFORMATION_MESSAGE);
                    sftpFrame.setVisible(false);
                    frame.setVisible(true);
                }
            } catch (JSchException e) {
                JOptionPane.showMessageDialog(null,
                        "Could not establish connection!\nTry Again!", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        // HTTP
        final JButton buttonNext = new JButton("Next");
        final TextField client_id_tb = new TextField("7n4vr35t6o5153456ervok1vm9");
        final TextField region_tb = new TextField("eu-west-2");
        final TextField username_tb = new TextField("data-sim-team");
        final JPasswordField password_tb = new JPasswordField("jOvK-dRCs-kCW3-ZgPx");
        httpSetup(httpFrame, buttonNext, client_id_tb, region_tb, username_tb, password_tb);
        buttonNext.addActionListener(actionEvent -> {

            final OAuth tokenGen = new OAuth(client_id_tb.getText(), region_tb.getText(),
                    username_tb.getText(), new String(password_tb.getPassword()));
            tokenGen.generateToken();
            tokenFinal[0] = tokenGen.token;
            if (tokenGen.token.equals("")) JOptionPane.showMessageDialog(null,
                    "Could not establish connection!\nTry Again!", "Error", JOptionPane.ERROR_MESSAGE);
            else {
                JOptionPane.showMessageDialog(null,
                        "Connection established!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                httpFrame.setVisible(false);
                frame.setVisible(true);
            }
        });

        //Menu ( GUI 2 )
        JPanel panel = new JPanel(new GridBagLayout());
        final JButton buttonSynthea = new JButton("Generate data");
        final JButton buttonMirth = new JButton("HL7 Convertor");
        final JButton buttonUpload = new JButton("Upload file");
        secondMenuSetup(frame, constraints, panel, buttonSynthea, buttonMirth, buttonUpload);
        buttonSynthea.addActionListener(actionEvent -> {
            // this makes sure the button you are pressing is the button variable
            if (actionEvent.getSource() == buttonSynthea) {
                frame.setVisible(false);
                generateFrame.setVisible(true);
            }
        });
        buttonUpload.addActionListener(actionEvent -> {
            // this makes sure the button you are pressing is the button variable
            if (actionEvent.getSource() == buttonUpload) {
                frame.setVisible(false);
                uploadFrame.setVisible(true);
            }
        });
        buttonMirth.addActionListener(actionEvent -> {
            frame.setVisible(false);
            uploadFrameConv.setVisible(true);
        });

        // Upload Frame
        final JFileChooser fileChooser = new JFileChooser();
        final JButton buttonUploadFile = new JButton("Upload");
        final JButton buttonSendFile = new JButton("Send");
        final JLabel displayFile = new JLabel("");
        final JFileChooser fileChooserConv = new JFileChooser();
        final JButton buttonUploadFileConv = new JButton("Upload");
        final JButton buttonConvertFileConv = new JButton("Convert to HL7 FHIR");
        final JButton buttonSendFileConv = new JButton("Send");
        uploadSetup(uploadFrame, uploadFrameConv, constraints, fileChooser, buttonUploadFile,
                buttonSendFile, displayFile, buttonUploadFileConv, buttonConvertFileConv, buttonSendFileConv);

        buttonSendFile.addActionListener(actionEvent -> {
            switch (protocol[0]) {
                case HTTP:
                    Send sender = new Send(DATA.BINARY, selectedFile[0], null, null, tokenFinal[0]);
                    sender.run();
                    break;
                case SFTP:
                    SenderSFTP senderSFTP = new SenderSFTP(channelSftp[0]);
                    try {
                        senderSFTP.sendDataToServer(DATA.BINARY, selectedFile[0]);
                    } catch (SftpException | JSchException e) {
                        e.printStackTrace();
                    }
                case MESSAGE_BROKER:
                    try {
                        msgBroker[0].Send(DATA.BINARY, selectedFile[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
        buttonConvertFileConv.addActionListener(actionEvent -> {
            Convertor c = new Convertor();
            c.Convertor(selectedFile[0].getAbsolutePath());
            buttonSendFileConv.setEnabled(true);
        });
        buttonUploadFile.addActionListener(actionEvent -> {
            if (actionEvent.getSource() == buttonUploadFile) {
                int result = fileChooser.showOpenDialog(uploadFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile[0] = fileChooser.getSelectedFile();
                    displayFile.setText(selectedFile[0].getAbsolutePath());

                }
            }
        });
        buttonUploadFileConv.addActionListener(actionEvent -> {
            if (actionEvent.getSource() == buttonUploadFileConv) {
                int resultConv = fileChooserConv.showOpenDialog(uploadFrameConv);
                if (resultConv == JFileChooser.APPROVE_OPTION) {
                    selectedFile[0] = fileChooserConv.getSelectedFile();
                    displayFile.setText(selectedFile[0].getAbsolutePath());
                    buttonConvertFileConv.setEnabled(true);
                }
            }
        });
        buttonSendFileConv.addActionListener(actionEvent -> {
            switch (protocol[0]) {
                case HTTP:
                    ParseJSONConverted parseJSONConverted = new ParseJSONConverted();
                    try {
                        parseJSONConverted.parseJson(tokenFinal[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case SFTP:
                    SenderSFTP senderSFTP = new SenderSFTP(channelSftp[0]);
                    try {
                        senderSFTP.sendDataToServer(DATA.BINARY,
                                new File(System.getProperty("user.dir").concat("/ConvertedFile.json")));
                    } catch (SftpException | JSchException e) {
                        e.printStackTrace();
                    }

                case MESSAGE_BROKER:
                    try {
                        msgBroker[0].Send(DATA.BINARY, new File(System.getProperty("user.dir").concat("/ConvertedFile.json")));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


            }
        });

        //Configurations (GUI 2.1)
        NumberFormatter formatterAge = ageTextBoxSetup();
        //CombBoxes
        ParserCustomSettings pcs = new ParserCustomSettings();
        ArrayList<String> choicesStates = pcs.parse(System.getProperty("user.dir").concat("/regions.txt"));
        ArrayList<String> choicesModules = pcs.parse(System.getProperty("user.dir").concat("/modules.txt"));
        String[] choicesGender = {"Male", "Female"};

        final JButton startGenerate = new JButton("Generate !");
        final JButton sendGenerated = new JButton("Send");
        final JFormattedTextField pop_tb = populationTextBoxSetup();
        final JFormattedTextField minAge_tb = new JFormattedTextField(formatterAge);
        final JFormattedTextField maxAge_tb = new JFormattedTextField(formatterAge);
        final JComboBox<String> gen_cb = new JComboBox<>(choicesGender);
        final JComboBox<Object> mod_cb = new JComboBox<>(choicesModules.toArray());
        final JComboBox<Object> st_cb = new JComboBox<>(choicesStates.toArray());
        thirdMenuSetup(generateFrame, startGenerate, sendGenerated, pop_tb, minAge_tb, maxAge_tb, gen_cb, mod_cb, st_cb);
        startGenerate.addActionListener(actionEvent -> {
            // this makes sure the button you are pressing is the button variable
            if (actionEvent.getSource() == startGenerate) {

                Compute computer = new Compute(pop_tb.getText(), minAge_tb.getText(),
                        maxAge_tb.getText(),
                        Objects.requireNonNull(gen_cb.getSelectedItem()).toString(),
                        Objects.requireNonNull(mod_cb.getSelectedItem()).toString(),
                        Objects.requireNonNull(st_cb.getSelectedItem()).toString());

                startGenerate.setEnabled(false);
                computer.generatePatient();

                startGenerate.setEnabled(true);
                sendGenerated.setEnabled(true);
            }
        });
        sendGenerated.addActionListener(actionEvent -> {
            // this makes sure the button you are pressing is the button variable
            if (actionEvent.getSource() == sendGenerated) {
                //if pressed execute code:
                switch (protocol[0]) {
                    case HTTP:
                        ParseJSON s = new ParseJSON();
                        startGenerate.setEnabled(false);
                        sendGenerated.setEnabled(false);
                        try {
                            s.parseJson(tokenFinal[0]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case SFTP:
                        SenderSFTP senderSFTP = new SenderSFTP(channelSftp[0]);
                        try {
                            senderSFTP.sendDataToServer(DATA.SYNTHEA, null);
                        } catch (SftpException | JSchException e) {
                            e.printStackTrace();
                        }

                    case MESSAGE_BROKER:
                        try {
                            msgBroker[0].Send(DATA.SYNTHEA, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                }

                buttonSynthea.setEnabled(true);
                //button2.setEnabled(true);
            }
        });
    }

    private void messageBrokerSetup(MessageBroker[] msgBroker, JFrame frame, JFrame messageBrokerFrame) {
        // Message Broker
        final JButton buttonNextMsgBroker = new JButton("Next");
        final JPanel panelMsgBroker = new JPanel(new GridBagLayout());
        final JLabel msgBrokerEndpoint = new JLabel("Endpoint");
        final JLabel msgBrokerPort = new JLabel("Port");
        final JLabel msgBrokerUsername = new JLabel("Username");
        final JLabel msgBrokerPassword = new JLabel("Password");
        final TextField msgBrokerEndpoint_tb
                = new TextField("b-2b8a65ac-59e8-4888-b0ed-093a848d3775.mq.us-east-1.amazonaws.com");
        final TextField msgBrokerPort_tb = new TextField("5671");
        final TextField msgBrokerUsername_tb = new TextField("healthcaredatasim");
        final JPasswordField msgBrokerPassword_tb = new JPasswordField("philipisthebestclient");

        msgBrokerSetValues(messageBrokerFrame, buttonNextMsgBroker, panelMsgBroker, msgBrokerEndpoint,
                msgBrokerPort, msgBrokerUsername, msgBrokerPassword, msgBrokerEndpoint_tb,
                msgBrokerPort_tb, msgBrokerUsername_tb, msgBrokerPassword_tb);
        buttonNextMsgBroker.addActionListener(actionEvent -> {
            msgBroker[0] = new MessageBroker(msgBrokerEndpoint_tb.getText(), msgBrokerPort_tb.getText(),
                    msgBrokerUsername_tb.getText(), new String(msgBrokerPassword_tb.getPassword()));
            try {
                msgBroker[0].Connect();
                JOptionPane.showMessageDialog(null, "Connection established!", "Success!",
                        JOptionPane.INFORMATION_MESSAGE);
                messageBrokerFrame.setVisible(false);
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not establish connection!\nTry Again!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void msgBrokerSetValues(JFrame messageBrokerFrame, JButton buttonNextMsgBroker,
                                    JPanel panelMsgBroker, JLabel msgBrokerEndpoint,
                                    JLabel msgBrokerPort, JLabel msgBrokerUsername,
                                    JLabel msgBrokerPassword, TextField msgBrokerEndpoint_tb,
                                    TextField msgBrokerPort_tb, TextField msgBrokerUsername_tb,
                                    JPasswordField msgBrokerPassword_tb) {
        msgBrokerEndpoint.setBounds(10, 10, 140, 25);
        msgBrokerPort.setBounds(10, 50, 120, 25);
        msgBrokerUsername.setBounds(10, 90, 120, 25);
        msgBrokerPassword.setBounds(10, 130, 120, 25);
        msgBrokerEndpoint_tb.setBounds(150, 10, 200, 25);
        msgBrokerPort_tb.setBounds(150, 50, 200, 25);
        msgBrokerUsername_tb.setBounds(150, 90, 200, 25);
        msgBrokerPassword_tb.setBounds(150, 130, 200, 25);

        panelMsgBroker.add(buttonNextMsgBroker);
        messageBrokerFrame.add(msgBrokerEndpoint);
        messageBrokerFrame.add(msgBrokerPort);
        messageBrokerFrame.add(msgBrokerUsername);
        messageBrokerFrame.add(msgBrokerPassword);
        messageBrokerFrame.add(msgBrokerEndpoint_tb);
        messageBrokerFrame.add(msgBrokerPort_tb);
        messageBrokerFrame.add(msgBrokerUsername_tb);
        messageBrokerFrame.add(msgBrokerPassword_tb);
        messageBrokerFrame.add(panelMsgBroker);
        messageBrokerFrame.setVisible(false);
    }

    private void thirdMenuSetup(JFrame generateFrame, JButton startGenerate, JButton sendGenerated,
                                JFormattedTextField pop_tb, JFormattedTextField minAge_tb,
                                JFormattedTextField maxAge_tb, JComboBox<String> gen_cb,
                                JComboBox<Object> mod_cb, JComboBox<Object> st_cb) {
        JPanel panelCustom = new JPanel(new GridBagLayout());
        JLabel label1 = new JLabel("Population Size");
        JLabel label2 = new JLabel("Age");
        JLabel label3 = new JLabel("Gender");
        JLabel label4 = new JLabel("Module");
        JLabel label5 = new JLabel("Region");
        panelCustom.add(startGenerate);
        panelCustom.add(sendGenerated);

        label1.setBounds(10,10,120,25);
        label2.setBounds(10,50,120,25);
        label3.setBounds(10,90,120,25);
        label4.setBounds(10,130,120,25);
        label5.setBounds(10, 170, 120, 25);

        pop_tb.setBounds(150,10,200,25);
        minAge_tb.setBounds(150,50,95,25);
        maxAge_tb.setBounds(260,50,95,25);

        gen_cb.setBounds(150,90,200,25);
        mod_cb.setBounds(150,130,200,25);
        st_cb.setBounds(150,170,200,25);

        generateFrame.add(label1);
        generateFrame.add(label2);
        generateFrame.add(label3);
        generateFrame.add(label4);
        generateFrame.add(label5);
        generateFrame.add(pop_tb);
        generateFrame.add(minAge_tb);
        generateFrame.add(maxAge_tb);
        generateFrame.add(gen_cb);
        generateFrame.add(mod_cb);
        generateFrame.add(st_cb);


        generateFrame.add(panelCustom);
        startGenerate.setEnabled(true);
        sendGenerated.setEnabled(false);
    }

    private NumberFormatter ageTextBoxSetup() {
        // Age textboxes
        NumberFormat formatAge = NumberFormat.getInstance();
        NumberFormatter formatterAge = new NumberFormatter(formatAge);
        formatterAge.setValueClass(Integer.class);
        formatterAge.setMinimum(0);
        formatterAge.setMaximum(140);
        formatterAge.setAllowsInvalid(false);
        formatterAge.setCommitsOnValidEdit(true);
        return formatterAge;
    }

    private JFormattedTextField populationTextBoxSetup() {
        // Population text box
        NumberFormat formatPopulation = NumberFormat.getInstance();
        NumberFormatter formatterPopulation = new NumberFormatter(formatPopulation);
        formatterPopulation.setValueClass(Integer.class);
        formatterPopulation.setMinimum(0);
        formatterPopulation.setMaximum(9999);
        formatterPopulation.setAllowsInvalid(false);
        formatterPopulation.setCommitsOnValidEdit(true);
        return new JFormattedTextField(formatterPopulation);
    }

    private void uploadSetup(JFrame uploadFrame, JFrame uploadFrameConv, GridBagConstraints constraints,
                             JFileChooser fileChooser, JButton buttonUploadFile, JButton buttonSendFile,
                             JLabel displayFile, JButton buttonUploadFileConv, JButton buttonConvertFileConv,
                             JButton buttonSendFileConv) {
        final JLabel displayFileConv = new JLabel("");
        FileNameExtensionFilter filterConv = new FileNameExtensionFilter("", "txt");
        JPanel panelUpload = new JPanel(new GridBagLayout());
        JPanel panelUploadConv = new JPanel(new GridBagLayout());

        FileNameExtensionFilter filter = new FileNameExtensionFilter("", "jpg", "png", "jpeg", "pdf","mpeg","mp4","csv","txt","doc","docx");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(null);
        panelUpload.add(buttonUploadFile);
        panelUpload.add(buttonSendFile, constraints);
        GridBagConstraints c1 = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        panelUpload.add(displayFile,c1);
        uploadFrame.add(panelUpload);
        //Converter

        fileChooser.setFileFilter(filterConv);
        fileChooser.setCurrentDirectory(null);
        panelUploadConv.add(buttonUploadFileConv);
        panelUploadConv.add(buttonConvertFileConv);
        panelUploadConv.add(buttonSendFileConv, constraints);
        GridBagConstraints c1Conv = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        panelUploadConv.add(displayFileConv,c1Conv);
        uploadFrameConv.add(panelUploadConv);
        buttonConvertFileConv.setEnabled(false);
        buttonSendFileConv.setEnabled(false);
    }

    private void secondMenuSetup(JFrame frame, GridBagConstraints constraints, JPanel panel,
                                 JButton buttonSynthea, JButton buttonMirth, JButton buttonUpload) {
        panel.add(buttonSynthea, constraints);
        panel.add(buttonMirth, constraints);
        panel.add(buttonUpload, constraints);
        frame.add(panel);
    }

    private void httpSetup(JFrame httpFrame, JButton buttonNext, TextField client_id_tb,
                           TextField region_tb, TextField username_tb, JPasswordField password_tb) {
        final JLabel client_id = new JLabel("Client ID");
        final JLabel region = new JLabel("Region");
        final JLabel username = new JLabel("Username");
        final JLabel password = new JLabel("Password");
        JPanel panelHttp = new JPanel(new GridBagLayout());

        msgBrokerSetValues(httpFrame, buttonNext, panelHttp, client_id, region, username, password, client_id_tb, region_tb, username_tb, password_tb);
        //buttonNext.setEnabled(true);
    }

    private void sftpSetup(JFrame sftpFrame, JButton buttonNextSFTP, TextField sftpRemoteHost_tb,
                           TextField sftpUsername_tb, JPasswordField sftpPassword_tb) {
        final JLabel sftpRemoteHost = new JLabel("Remote Host");
        final JLabel sftpUsername = new JLabel("Username");
        final JLabel sftpPassword = new JLabel("Password");

        JPanel panelServer = new JPanel(new GridBagLayout());
        panelServer.add(buttonNextSFTP);

        sftpRemoteHost.setBounds(10,10,140,25);
        sftpUsername.setBounds(10,50,120,25);
        sftpPassword.setBounds(10,90,120,25);
        sftpRemoteHost_tb.setBounds(150,10,200,25);
        sftpUsername_tb.setBounds(150,50,200,25);
        sftpPassword_tb.setBounds(150,90,200,25);

        sftpFrame.add(sftpRemoteHost);
        sftpFrame.add(sftpUsername);
        sftpFrame.add(sftpPassword);
        sftpFrame.add(sftpRemoteHost_tb);
        sftpFrame.add(sftpUsername_tb);
        sftpFrame.add(sftpPassword_tb);
        sftpFrame.add(panelServer);
        sftpFrame.setVisible(false);
    }
}
