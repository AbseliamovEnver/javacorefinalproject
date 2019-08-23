package com.abseliamov.bookingflight.view;

import com.abseliamov.bookingflight.utils.InputData;
import com.abseliamov.bookingflight.utils.ReadInputData;

public class StartMenu {
    InitializationUser initializationUser;

    public StartMenu(InitializationUser initializationUser) {
        this.initializationUser = initializationUser;
    }

    public void start() {
        Menu menu = new Menu();
        System.out.println("\t** WELCOME TO BOOKING FLIGHT **");

        while (true) {
            menu.mainMenu();
            int itemMenu = Integer.parseInt(ReadInputData.getValidInputData("Select menu item", InputData.INTEGER));
            switch (itemMenu){
                case 0:
                    System.out.println("***-= THANK FOR USING OUR APPLICATION! =-***");
                    System.out.println("\t\t***-= BON VOYAGE!!! =-***");
                    System.exit(0);
                    break;
                case 1:
                    initializationUser.loginUser();
                    break;
                case 2:
                    initializationUser.registrationUser();
                    break;
            }
        }
    }
}
