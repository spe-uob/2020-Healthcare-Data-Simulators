import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GUI {
    public static void main(String args[]) {

        JFrame frame = new JFrame("Healthcare Data Simulators");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //size of the GUI
        frame.setSize(500, 500);

        //buttons
        JButton button1 = new JButton("Press to generate pacients");
        frame.getContentPane().add(button1);
        frame.setVisible(true);


        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // this makes sure the button you are pressing is the button variable
                if(e.getSource() == button1) {
                    //if pressed execute code:
//                    Compute x = new Compute();
//                    System.out.println(x.randomInt());
                        System.out.println("Starting...");
                        //button1.setText("Waiting...");
                        ProcessBuilder processBuilder = new ProcessBuilder();
                        processBuilder.command("bash","-c","java -jar synthea_JAR/synthea-with-dependencies.jar");

                        try{

                            button1.setEnabled(false);

                            Process process = processBuilder.start();
                            StringBuilder output = new StringBuilder();
                            BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(process.getInputStream())
                            );
                            String line;
                            while((line=reader.readLine()) != null) {
                                output.append((line+"\n"));
                            }
                            System.out.println(output);
                            System.out.println("Success!");

                            JOptionPane.showMessageDialog(null, "1 Pacient has been generated","Success!", JOptionPane.INFORMATION_MESSAGE);
                            button1.setEnabled(true);
                            button1.setText("Press to generate pacients");
                        }
                        catch (IOException er) {
                            System.out.println(er);
                        }
                        //Runtime.getRuntime().exec("/bin/bash -c java -jar synthea_JAR/synthea-with-dependencies.jar");


                }
            }
        });



    }
}
