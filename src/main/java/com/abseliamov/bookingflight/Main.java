package com.abseliamov.bookingflight;

import com.abseliamov.bookingflight.utils.Injector;
import com.abseliamov.bookingflight.view.MenuContentList;

public class Main {
    public static void main(String[] args) {

        MenuContentList.createMenuContent();

        Injector.getPassengerMenu().mainMenu();
    }
}
