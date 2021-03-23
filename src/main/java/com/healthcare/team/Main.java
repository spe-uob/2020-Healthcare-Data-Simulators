package com.healthcare.team;



public class Main {

    private static void initialVerificationSetup() {
        InitialSetup initialSetup = new InitialSetup();
        initialSetup.setup();
    }

    public static void main(String[] args)  {

        ///UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        OAuth oAuth = new OAuth("7n4vr35t6o5153456ervok1vm9", "eu-west-2", "data-sim-team","jOvK-dRCs-kCW3-ZgPx");
        oAuth.generateToken();
        oAuth.sendToRabbitMQ();
        //initialVerificationSetup();
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        System.out.println("starting...");

        GUIForm mainMenu = new GUIForm();
        mainMenu.setVisible(true);
    }
}
