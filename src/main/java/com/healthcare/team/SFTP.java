package com.healthcare.team;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


public class SFTP {
    private final String remoteHost;
    private final String username;
    private final String password;

    private SFTP(String remoteHost, String username, String password){
        this.remoteHost = remoteHost;
        this.username = username;
        this.password = password;
    }

    public static SFTP of (String remoteHost, String username, String password){
        return new SFTP(remoteHost, username, password);
    }
    public ChannelSftp establishConnection() throws JSchException {
        JSch jsch = new JSch();
        jsch.setKnownHosts(System.getenv("HOME") +"/.ssh/known_hosts" );
        Session jschSession = jsch.getSession(this.username, this.remoteHost);
        jschSession.setPassword(this.password);
        jschSession.connect();
        System.out.println(jschSession.toString());
        return (ChannelSftp) jschSession.openChannel("sftp");
    }
}
