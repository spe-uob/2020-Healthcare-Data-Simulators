package com.healthcare.team;



public class Main {

    private static void initialVerificationSetup() {
        new InitialSetup().setup();
    }

    public static void main(String[] args)  {

        ///UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //OAuth oAuth = new OAuth("7n4vr35t6o5153456ervok1vm9", "eu-west-2", "data-sim-team","jOvK-dRCs-kCW3-ZgPx");
      //  oAuth.generateToken();
        //oAuth.sendToRabbitMQ();
        initialVerificationSetup();
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        System.out.println("starting...");

       // RegionSelection mainMenu = new RegionSelection();
        //mainMenu.setVisible(true);

        ServerConnection serverConnection = new ServerConnection();
        serverConnection.setVisible(true);
    }
}
