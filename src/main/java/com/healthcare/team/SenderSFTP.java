package com.healthcare.team;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import javax.swing.*;
import java.io.File;
import java.util.Objects;

public class SenderSFTP {
    ChannelSftp channelSftp;
    SenderSFTP(ChannelSftp channelSftp){
        this.channelSftp = channelSftp;
    }
    public void sendDataToServer(GUI.DATA d, File file) throws SftpException, JSchException {
       // System.out.println(this.channelSftp.getHome());
        this.channelSftp.connect();
        //System.out.println("Am intrat");
        switch (d) {
            case SYNTHEA:
                String outputPath = System.getProperty("user.dir").concat("/output/fhir/");
                File dir = new File(outputPath);
                System.out.println(dir.getName());

                for (File f : Objects.requireNonNull(dir.listFiles())) {
                    if (!f.getName().contains("json")) continue;
                    this.channelSftp.put(outputPath.concat(f.getName()), f.getName());
                }
                break;

            case BINARY:
                if (file == null) JOptionPane.showMessageDialog(null, "No File to upload","Error!", JOptionPane.ERROR_MESSAGE);
                else {
                    System.out.println(file.getAbsolutePath());
                    this.channelSftp.put(file.getAbsolutePath(),file.getName());

                }
                break;
        }
        JOptionPane.showMessageDialog(null, "Transfer successful!","Success!", JOptionPane.INFORMATION_MESSAGE);

    }
}
