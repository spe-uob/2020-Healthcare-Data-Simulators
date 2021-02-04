import org.apache.log4j.BasicConfigurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI {
public static void main(String args[]) throws FileNotFoundException {
     BasicConfigurator.configure();
    /* final OAuth tokenGen = new OAuth();
     tokenGen.generateToken();
     System.out.println(tokenGen.token);*/


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


    serverFrame.setSize(700,600);
    frame.setSize(700, 600);
    generateFrame.setSize(700, 600);


    JButton buttonNext = new JButton("NEXT");
    serverFrame.getContentPane().add(buttonNext);
    serverFrame.setVisible(true);

    final JButton startGenerate = new JButton("Generate !");
    final JButton sendGenerated = new JButton("Send");
    JPanel panelCustom = new JPanel(new GridBagLayout());
    panelCustom.add(startGenerate);
    panelCustom.add(sendGenerated);
    JLabel label1 = new JLabel("Population Size");
    JLabel label2 = new JLabel("Age");
    JLabel label3 = new JLabel("Sex");
    JLabel label4 = new JLabel("Module");
    JLabel label5 = new JLabel("State");



    label1.setBounds(10,10,120,25);
    label2.setBounds(10,50,120,25);
    label3.setBounds(10,90,120,25);
    label4.setBounds(10,130,120,25);
    label5.setBounds(10, 170, 120, 25);

    final TextField tf1 = new TextField();
    final TextField tf2 = new TextField();
    //TextField tf3 = new TextField();
    //TextField tf4 = new TextField();
    //TextField tf5 = new TextField();
    final TextField tf6 = new TextField();

    String[] choicesStates = {"Somerset", "Cumbria"};
    String[] choicesSex = {"male", "female"};
    String[] choicesDisease = {"cancer", "heart"};


    final JComboBox tf3 = new JComboBox(choicesSex);
    final JComboBox tf5 = new JComboBox(choicesStates);
    final JComboBox tf4 = new JComboBox(choicesDisease);


    tf1.setBounds(150,10,100,25);
    tf2.setBounds(150,50,100,25);
    tf6.setBounds(260,50,100,25);


    tf3.setBounds(150,90,100,25);
    tf4.setBounds(150,130,100,25);
    tf5.setBounds(150,170,100,25);


    generateFrame.add(label1);
    generateFrame.add(label2);
    generateFrame.add(label3);
    generateFrame.add(label4);
    generateFrame.add(label5);
    generateFrame.add(tf1);
    generateFrame.add(tf2);
    generateFrame.add(tf3);
    generateFrame.add(tf4);
    generateFrame.add(tf5);
    generateFrame.add(tf6);



    generateFrame.add(panelCustom);
    startGenerate.setEnabled(true);
    sendGenerated.setEnabled(false);

    ///text boxes



    JPanel panel = new JPanel(new GridBagLayout());

    final JButton button1 = new JButton("Generate patients using synthea");
    panel.add(button1, constraints);
    final JButton button2 = new JButton("Send Data");
    panel.add(button2, constraints);
    frame.add(panel);

    button2.setEnabled(false);

    buttonNext.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            System.out.println("noroc");
            serverFrame.setVisible(false);
            frame.setVisible(true);
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
                Compute x = new Compute(tf1.getText(), tf2.getText(), tf6.getText(), tf3.getSelectedItem().toString(), tf4.getSelectedItem().toString(), tf5.getSelectedItem().toString());

                startGenerate.setEnabled(false);
                System.out.println("merge????");
                x.generatePatient();

                startGenerate.setEnabled(true);
                sendGenerated.setEnabled(true);
            }
        }
    });
    /*button2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // this makes sure the button you are pressing is the button variable
            if(e.getSource() == button2) {
                //if pressed execute code:
                ParseJSON s = new ParseJSON();
                button1.setEnabled(false);
                button2.setEnabled(false);
                try {
                    s.parseJson(tokenGen.token);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                button1.setEnabled(true);
                button2.setEnabled(true);
            }
        }
    });*/



}
}
