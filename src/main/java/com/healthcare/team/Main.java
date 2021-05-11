package com.healthcare.team;

public class Main {

    private static void initialVerificationSetup() { new InitialSetup().setup(); }

    public static void main(String[] args)  {
        initialVerificationSetup();
        System.out.println("starting...");
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.setVisible(true);
    }
}
