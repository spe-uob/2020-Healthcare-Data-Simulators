import org.apache.log4j.BasicConfigurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class GUI {
public static void main(String args[]) throws FileNotFoundException {
     BasicConfigurator.configure();
     final OAuth tokenGen = new OAuth();
     tokenGen.generateToken();
     System.out.println(tokenGen.token);


    //GUI
    final JFrame frame = new JFrame("Healthcare Data Simulators");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel(new GridBagLayout());
    //panel.setPreferredSize(new Dimension(400,800));
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.anchor = GridBagConstraints.WEST;
    final JButton button1 = new JButton("Generate Pacient");
    panel.add(button1, constraints);
    final JButton button2 = new JButton("Send Data");
    panel.add(button2, constraints);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
    button2.setEnabled(false);

    button1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // this makes sure the button you are pressing is the button variable
            if(e.getSource() == button1) {
                //if pressed execute code:
                Compute x = new Compute();
                button1.setEnabled(false);
                button2.setEnabled(false);
                x.generatePatient();
                button1.setEnabled(true);
                button2.setEnabled(true);
            }
        }
    });
    button2.addActionListener(new ActionListener() {
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
    });



}
}
