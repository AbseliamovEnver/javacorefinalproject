package com.abseliamov.flyapplication;

import com.abseliamov.flyapplication.utils.Injector;
import com.abseliamov.flyapplication.view.MenuContentList;

public class Main {
    public static void main(String[] args) {

        MenuContentList.createMenuContent();

        Injector.getPassengerMenu().mainMenu();
    }
}
