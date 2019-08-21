package com.abseliamov.bookingflight.view;

import com.abseliamov.bookingflight.utils.PrintMenu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<String> mainMenuList = new ArrayList<>();
    private List<String> mainAdminMenu = new ArrayList<>();

    public void mainMenu() {
        mainMenuList.add("Login");
        mainMenuList.add("Register");
        mainMenuList.add("Exit");

        PrintMenu.printMenu(mainMenuList);
    }

    public void adminMainMenu(){
        mainAdminMenu.add("Create route");
        mainAdminMenu.add("Search route");
        mainAdminMenu.add("Update route");
        mainAdminMenu.add("Delete route");
        mainAdminMenu.add("Update user");
        mainAdminMenu.add("Exit");

        PrintMenu.printMenu(mainAdminMenu);
    }

    public void userMainMenu(){

    }
}
