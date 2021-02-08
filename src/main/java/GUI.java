import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.apache.log4j.BasicConfigurator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GUI {
    enum PROTOCOLS{
        HTTP,
        SFTP,
    }
    enum DATA{
        MIRTH,
        SYNTHEA,
        BINARY,
    }
public static void main(String args[]) throws FileNotFoundException {
    final String[] tokenFinal = new String[1];
    final PROTOCOLS[] protocol = new PROTOCOLS[1];
    final DATA[] data = new DATA[1];
    final File[] selectedFile = {new File("")};
    final ChannelSftp[] channelSftp = {null};
    BasicConfigurator.configure();
    //GUI
    final JFrame connectionOptionsFrame = new JFrame("Connection options");
    final JFrame frame = new JFrame("Healthcare Data Simulators");
    final JFrame httpFrame = new JFrame("HTTP Connection details");
    final JFrame generateFrame = new JFrame("Custom settings for generation");
    final JFrame sftpFrame = new JFrame("SFTP Connection details");
    final JFrame uploadFrame = new JFrame("Upload a file directly");
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.anchor = GridBagConstraints.WEST;

    connectionOptionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    httpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    generateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    connectionOptionsFrame.setSize(375,600);
    sftpFrame.setSize(375,600);
    httpFrame.setSize(375,600);
    frame.setSize(375, 600);
    generateFrame.setSize(375, 600);
    uploadFrame.setSize(375, 600);
    //Connection option menu
    final JButton httpOption = new JButton("HTTP");
    final JButton sftpOption = new JButton("SFTP");
    JPanel panelOptions = new JPanel(new GridBagLayout());
    panelOptions.add(httpOption);
    panelOptions.add(sftpOption);
    connectionOptionsFrame.add(panelOptions);
    connectionOptionsFrame.setVisible(true);
    //SFTP
    final JButton buttonNextSFTP = new JButton("Next");
    JPanel panelServer = new JPanel(new GridBagLayout());
    panelServer.add(buttonNextSFTP);

    final JLabel sftpRemoteHost = new JLabel("Remote Host");
    final JLabel sftpUsername = new JLabel("Username");
    final JLabel sftpPassword = new JLabel("Password");
    sftpRemoteHost.setBounds(10,10,140,25);
    sftpUsername.setBounds(10,50,120,25);
    sftpPassword.setBounds(10,90,120,25);

    final TextField sftpRemoteHost_tb= new TextField("snowy.cs.bris.ac.uk");
    final TextField sftpUsername_tb= new TextField("ab19123");
    final JPasswordField sftpPassword_tb = new JPasswordField("TestParola");
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


    // HTTP
    final JButton buttonNext = new JButton("Next");
    JPanel panelHttp = new JPanel(new GridBagLayout());
    panelHttp.add(buttonNext);

    final JLabel client_id = new JLabel("Client ID");
    final JLabel region = new JLabel("Region");
    final JLabel username = new JLabel("Username");
    final JLabel password = new JLabel("Password");
    client_id.setBounds(10,10,140,25);
    region.setBounds(10,50,120,25);
    username.setBounds(10,90,120,25);
    password.setBounds(10,130,120,25);

    final TextField client_id_tb= new TextField("7n4vr35t6o5153456ervok1vm9");
    final TextField region_tb= new TextField("eu-west-2");
    final TextField username_tb = new TextField("data-sim-team");
    final JPasswordField password_tb= new JPasswordField("jOvK-dRCs-kCW3-ZgPx");
    client_id_tb.setBounds(150,10,200,25);
    region_tb.setBounds(150,50,200,25);
    username_tb.setBounds(150,90,200,25);
    password_tb.setBounds(150,130,200,25);

    httpFrame.add(client_id);
    httpFrame.add(region);
    httpFrame.add(username);
    httpFrame.add(password);
    httpFrame.add(client_id_tb);
    httpFrame.add(region_tb);
    httpFrame.add(username_tb);
    httpFrame.add(password_tb);
    httpFrame.add(panelHttp);
    httpFrame.setVisible(false);
    //buttonNext.setEnabled(true);

    //Menu ( GUI 2 )
    JPanel panel = new JPanel(new GridBagLayout());
    final JButton buttonSynthea = new JButton("Generate data");
    panel.add(buttonSynthea, constraints);
    final JButton buttonMirth = new JButton("Use Mirth");
    panel.add(buttonMirth, constraints);
    final JButton buttonUpload = new JButton("Upload file");
    panel.add(buttonUpload, constraints);
    frame.add(panel);

    buttonMirth.setEnabled(false);

    // Upload Frame
    JPanel panelUpload = new JPanel(new GridBagLayout());
    final JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("", "jpg", "png", "jpeg", "pdf","mpeg","mp4","csv","txt","doc","docx");
    fileChooser.setFileFilter(filter);
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    final JButton buttonUploadFile = new JButton("Upload");
    panelUpload.add(buttonUploadFile);
    final JButton buttonSendFile = new JButton("Send");
    panelUpload.add(buttonSendFile);
    final JLabel displayFile = new JLabel("");
    panelUpload.add(displayFile);


    uploadFrame.add(panelUpload);

    //Configurations (GUI 2.1)
    final JButton startGenerate = new JButton("Generate !");
    final JButton sendGenerated = new JButton("Send");
    JPanel panelCustom = new JPanel(new GridBagLayout());
    panelCustom.add(startGenerate);
    panelCustom.add(sendGenerated);
    JLabel label1 = new JLabel("Population Size");
    JLabel label2 = new JLabel("Age");
    JLabel label3 = new JLabel("Gender");
    JLabel label4 = new JLabel("Module");
    JLabel label5 = new JLabel("Region");

    label1.setBounds(10,10,120,25);
    label2.setBounds(10,50,120,25);
    label3.setBounds(10,90,120,25);
    label4.setBounds(10,130,120,25);
    label5.setBounds(10, 170, 120, 25);

    //final TextField pop_tb= new TextField();
    //final TextField minAge_tb = new TextField();
    //final TextField maxAge_tb = new TextField();

    // Population textbox
    NumberFormat formatPopulation = NumberFormat.getInstance();
    NumberFormatter formatterPopulation = new NumberFormatter(formatPopulation);
    formatterPopulation.setValueClass(Integer.class);
    formatterPopulation.setMinimum(0);
    formatterPopulation.setMaximum(9999);
    formatterPopulation.setAllowsInvalid(false);
    formatterPopulation.setCommitsOnValidEdit(true);
    final JFormattedTextField pop_tb = new JFormattedTextField(formatterPopulation);

    // Age textboxes
    NumberFormat formatAge = NumberFormat.getInstance();
    NumberFormatter formatterAge = new NumberFormatter(formatAge);
    formatterAge.setValueClass(Integer.class);
    formatterAge.setMinimum(0);
    formatterAge.setMaximum(140);
    formatterAge.setAllowsInvalid(false);
    formatterAge.setCommitsOnValidEdit(true);
    final JFormattedTextField minAge_tb = new JFormattedTextField(formatterAge);
    final JFormattedTextField maxAge_tb = new JFormattedTextField(formatterAge);

    //Comboxes

    ParserCustomSettings pcs = new ParserCustomSettings();

    ArrayList<String> choicesStates = pcs.parse(System.getProperty("user.dir").concat("/regions.txt"));
    ArrayList<String> choicesModules = pcs.parse(System.getProperty("user.dir").concat("/modules.txt"));
    String[] choicesGender = {"Male", "Female"};

    final JComboBox gen_cb = new JComboBox(choicesGender);
    final JComboBox mod_cb = new JComboBox(choicesModules.toArray());
    final JComboBox st_cb = new JComboBox(choicesStates.toArray());


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

    ///text boxes


//    final JButton httpOption = new JButton("HTTP");
//    final JButton sftpOption = new JButton("SFTP");

    buttonSendFile.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(protocol[0]) {
                case HTTP:
                    Send sender = new Send();
                    try {
                        sender.SendResource(DATA.BINARY, selectedFile[0], null, null, tokenFinal[0]);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case SFTP:
                    SenderSFTP senderSFTP = new SenderSFTP(channelSftp[0]);
                    try {
                        senderSFTP.sendDataToServer(DATA.BINARY, selectedFile[0]);
                    } catch (SftpException ex) {
                        ex.printStackTrace();
                    } catch (JSchException ex) {
                        ex.printStackTrace();
                    }
            }
        }
    });
    httpOption.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            protocol[0] = PROTOCOLS.HTTP;
            connectionOptionsFrame.setVisible(false);
            httpFrame.setVisible(true);
        }
    });
    sftpOption.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            protocol[0] = PROTOCOLS.SFTP;
            connectionOptionsFrame.setVisible(false);
            sftpFrame.setVisible(true);
        }
    });

//    sftpRemoteHost.setBounds(10,10,140,25);
//    sftpUsername.setBounds(10,50,120,25);
//    sftpPassword.setBounds(10,90,120,25);

    buttonNextSFTP.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            SFTP sftp = new SFTP(sftpRemoteHost_tb.getText(), sftpUsername_tb.getText(),sftpPassword_tb.getText());
            try {
                channelSftp[0] =  sftp.establishConnection();
                if(channelSftp[0] == null) JOptionPane.showMessageDialog(null, "Could not establish connection!\nTry Again!","Error", JOptionPane.ERROR_MESSAGE);
                else{
                    JOptionPane.showMessageDialog(null, "Connection established!","Success!", JOptionPane.INFORMATION_MESSAGE);
                    sftpFrame.setVisible(false);
                    frame.setVisible(true);
                }
            } catch (JSchException e) {
                JOptionPane.showMessageDialog(null, "Could not establish connection!\nTry Again!","Error", JOptionPane.ERROR_MESSAGE);


            }
        }
    });
    buttonNext.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            final OAuth tokenGen = new OAuth(client_id_tb.getText(), region_tb.getText(), username_tb.getText(), password_tb.getText());
            tokenGen.generateToken();
            tokenFinal[0] = tokenGen.token;
            if(tokenGen.token.equals("")) JOptionPane.showMessageDialog(null, "Could not establish connection!/nTry Again!","Error", JOptionPane.ERROR_MESSAGE);
            else{
                JOptionPane.showMessageDialog(null, "Connection established!","Success!", JOptionPane.INFORMATION_MESSAGE);
                httpFrame.setVisible(false);
                frame.setVisible(true);
            }
        }
    });


    buttonSynthea.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // this makes sure the button you are pressing is the button variable
            if(e.getSource() == buttonSynthea) {
                data[0] = DATA.SYNTHEA;
                frame.setVisible(false);
                generateFrame.setVisible(true);
            }
        }
    });
    buttonUpload.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // this makes sure the button you are pressing is the button variable
            if(e.getSource() == buttonUpload) {
                data[0] = DATA.BINARY;
                frame.setVisible(false);
                uploadFrame.setVisible(true);
            }
        }
    });

    buttonUploadFile.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonUploadFile){
                int result = fileChooser.showOpenDialog(uploadFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile[0] = fileChooser.getSelectedFile();
                    displayFile.setText(selectedFile[0].getAbsolutePath());

                }
            }
        }
    });

    startGenerate.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // this makes sure the button you are pressing is the button variable
            if(e.getSource() == startGenerate) {

                Compute computer = new Compute(pop_tb.getText(), minAge_tb.getText(), maxAge_tb.getText(), gen_cb.getSelectedItem().toString(),
                        mod_cb.getSelectedItem().toString(), st_cb.getSelectedItem().toString());

                startGenerate.setEnabled(false);
                //System.out.println(tokenFinal[0]);
                computer.generatePatient();

                startGenerate.setEnabled(true);
                sendGenerated.setEnabled(true);
            }
        }
    });
    sendGenerated.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // this makes sure the button you are pressing is the button variable
            if(e.getSource() == sendGenerated) {
                //if pressed execute code:
                switch(protocol[0]) {
                    case HTTP:
                        ParseJSON s = new ParseJSON();
                        startGenerate.setEnabled(false);
                        sendGenerated.setEnabled(false);
                        try {
                            s.parseJson(tokenFinal[0]);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case SFTP:
                        SenderSFTP senderSFTP = new SenderSFTP(channelSftp[0]);
                        try {
                            senderSFTP.sendDataToServer(DATA.SYNTHEA,null);
                        } catch (SftpException ex) {
                            ex.printStackTrace();
                        } catch (JSchException ex) {
                            ex.printStackTrace();
                        }
                }

                buttonSynthea.setEnabled(true);
                //button2.setEnabled(true);
            }
        }
    });



}
}
