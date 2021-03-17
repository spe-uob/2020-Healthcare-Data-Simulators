package com.healthcare.team;



public class Main {

    private static void initialVerificationSetup() {
        InitialSetup initialSetup = new InitialSetup();
        initialSetup.setup();
    }

    public static void main(String[] args)  {

        ///UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
     //   System.out.println("vlad");

        initialVerificationSetup();
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        System.out.println("vlad");

        GUIForm mainMenu = new GUIForm();
        mainMenu.setVisible(true);
    }
}
