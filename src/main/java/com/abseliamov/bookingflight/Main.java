package com.abseliamov.bookingflight;

import com.abseliamov.bookingflight.view.MenuContentList;
import com.abseliamov.bookingflight.view.PassengerMenu;

public class Main {
    public static void main(String[] args) {

        MenuContentList.createMenuContent();

        PassengerMenu menu = new PassengerMenu();

        menu.mainMenu();
    }
}
