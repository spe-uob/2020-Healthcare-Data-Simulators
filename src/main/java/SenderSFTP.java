import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import javax.swing.*;
import java.io.File;

public class SenderSFTP {
    ChannelSftp channelSftp;
    SenderSFTP(ChannelSftp channelSftp){
        this.channelSftp = channelSftp;
    }
    public void sendDataToServer() throws SftpException, JSchException {
       // System.out.println(this.channelSftp.getHome());
        this.channelSftp.connect();
        System.out.println("Am intrat");
        String outputPath = System.getProperty("user.dir").concat("/output/fhir/");
        File dir = new File( outputPath);
        System.out.println(dir.getName());
        File[] directoryListing = dir.listFiles();
        for(File f: directoryListing){
            if(!f.getName().contains("json")) continue;
            this.channelSftp.put(outputPath.concat(f.getName()), f.getName());
        }
        JOptionPane.showMessageDialog(null, "Transfer successful!","Success!", JOptionPane.INFORMATION_MESSAGE);

    }
}
