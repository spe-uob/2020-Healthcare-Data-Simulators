import org.apache.log4j.BasicConfigurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI {

public static void main(String args[]) throws FileNotFoundException {
    final String[] tokenFinal = new String[1];
    BasicConfigurator.configure();
    //GUI
    final JFrame frame = new JFrame("Healthcare Data Simulators");
    final JFrame serverFrame = new JFrame("Connection details");
    final JFrame generateFrame = new JFrame("Custom settings for generation");

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.anchor = GridBagConstraints.WEST;

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    generateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    serverFrame.setSize(375,600);
    frame.setSize(375, 600);
    generateFrame.setSize(375, 600);


    final JButton buttonNext = new JButton("Next");
    JPanel panelServer = new JPanel(new GridBagLayout());
    panelServer.add(buttonNext);

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
    final TextField password_tb= new TextField("jOvK-dRCs-kCW3-ZgPx");
    client_id_tb.setBounds(150,10,200,25);
    region_tb.setBounds(150,50,200,25);
    username_tb.setBounds(150,90,200,25);
    password_tb.setBounds(150,130,200,25);

    serverFrame.add(client_id);
    serverFrame.add(region);
    serverFrame.add(username);
    serverFrame.add(password);
    serverFrame.add(client_id_tb);
    serverFrame.add(region_tb);
    serverFrame.add(username_tb);
    serverFrame.add(password_tb);
    serverFrame.add(panelServer);
    serverFrame.setVisible(true);
    //buttonNext.setEnabled(true);

    //Menu ( GUI 2 )
    JPanel panel = new JPanel(new GridBagLayout());
    final JButton button1 = new JButton("Generate patients using synthea");
    panel.add(button1, constraints);
    final JButton button2 = new JButton("Use Mirth");
    panel.add(button2, constraints);
    frame.add(panel);

    button2.setEnabled(false);

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
    JLabel label5 = new JLabel("State");

    label1.setBounds(10,10,120,25);
    label2.setBounds(10,50,120,25);
    label3.setBounds(10,90,120,25);
    label4.setBounds(10,130,120,25);
    label5.setBounds(10, 170, 120, 25);

    final TextField pop_tb= new TextField();
    final TextField minAge_tb = new TextField();
    final TextField maxAge_tb = new TextField();

    String[] choicesStates = {"Somerset", "Cumbria"};
    String[] choicesGender = {"male", "female"};
    String[] choicesModules = {"cancer", "heart"};


    final JComboBox gen_cb = new JComboBox(choicesGender);
    final JComboBox mod_cb = new JComboBox(choicesModules);
    final JComboBox st_cb = new JComboBox(choicesStates);


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





    buttonNext.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            final OAuth tokenGen = new OAuth(client_id_tb.getText(), region_tb.getText(), username_tb.getText(), password_tb.getText());
            tokenGen.generateToken();
            tokenFinal[0] = tokenGen.token;
            if(tokenGen.token.equals("")) JOptionPane.showMessageDialog(null, "Could not establish connection!","Error", JOptionPane.ERROR_MESSAGE);
            else{
                JOptionPane.showMessageDialog(null, "Connection established!","Success!", JOptionPane.INFORMATION_MESSAGE);
                serverFrame.setVisible(false);
                frame.setVisible(true);
            }
        }
    });


    button1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // this makes sure the button you are pressing is the button variable
            if(e.getSource() == button1) {
                //if pressed execute code:
                frame.setVisible(false);
                generateFrame.setVisible(true);
                /*Compute x = new Compute();
                button1.setEnabled(false);
                button2.setEnabled(false);
                x.generatePatient();
                button1.setEnabled(true);
                button2.setEnabled(true);*/
            }
        }
    });

    startGenerate.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // this makes sure the button you are pressing is the button variable
            if(e.getSource() == startGenerate) {
                //if pressed execute code:
              //  String params = tf1.getText() + tf2.getText() + tf6.getText() + tf3.getSelectedItem().toString() + tf4.getSelectedItem().toString() + tf5.getSelectedItem().toString();
             //   System.out.println(params);
                Compute computer = new Compute(pop_tb.getText(), minAge_tb.getText(), maxAge_tb.getText(), gen_cb.getSelectedItem().toString(),
                        mod_cb.getSelectedItem().toString(), st_cb.getSelectedItem().toString());

                startGenerate.setEnabled(false);
                System.out.println(tokenFinal[0]);
                System.out.println("merge????");
                computer.generatePatient();

                startGenerate.setEnabled(true);
                sendGenerated.setEnabled(true);
            }
        }
    });
    sendGenerated.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // this makes sure the button you are pressing is the button variable
            if(e.getSource() == sendGenerated) {
                //if pressed execute code:
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
                button1.setEnabled(true);
                button2.setEnabled(true);
            }
        }
    });



}
}
