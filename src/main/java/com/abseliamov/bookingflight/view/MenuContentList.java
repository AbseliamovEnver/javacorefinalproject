package com.abseliamov.bookingflight.view;

import com.abseliamov.bookingflight.utils.CurrentUser;

import java.util.ArrayList;
import java.util.List;

public class MenuContentList {
    private static List<String> headerSite = new ArrayList<>();
    private static List<String> mainMenu = new ArrayList<>();
    private static List<String> searchMenu = new ArrayList<>();
    private static List<String> classCabins = new ArrayList<>();
    private static List<String> searchRouteMenu = new ArrayList<>();
    private static List<String> userDataMenu = new ArrayList<>();
    private static List<String> footerMenu = new ArrayList<>();
    private static CurrentUser currentUser;

    private MenuContentList() {
    }

    public static void createMenuContent() {
        headerSite.add("*********************************");
        headerSite.add(" ** WELCOME TO BOOKING FLIGHT **");
        headerSite.add("*********************************");

        mainMenu.add("MAIN MENU");
        mainMenu.add("Sing in");
        mainMenu.add("Create account");
        mainMenu.add("Exit");

        searchMenu.add("TICKET MENU");
        searchMenu.add("Search ticket");
        searchMenu.add("Buy ticket");
        searchMenu.add("Return ticket");
        searchMenu.add("Logout");
        searchMenu.add("Exit");

        classCabins.add("Class cabin");
        classCabins.add("Business class");
        classCabins.add("Economy class");

        searchRouteMenu.add("Select flight departure city: ");
        searchRouteMenu.add("Select city of flight arrival: ");
        searchRouteMenu.add("Enter departure date");
        searchRouteMenu.add("Select comfort class");
        searchRouteMenu.add("Exit");

        footerMenu.add("**************************************");
        footerMenu.add("-= THANK FOR USING OUR APPLICATION! =-");
        footerMenu.add(" *****-= HAVE A NICE TRIP!!! =-*****");
    }

    public static List<String> getHeaderSite() {
        return headerSite;
    }

    public static List<String> getMainMenu() {
        return mainMenu;
    }

    public static List<String> getSearchMenu() {
        return searchMenu;
    }

    public static List<String> getClassCabins() {
        return classCabins;
    }

    public static List<String> getSearchRouteMenu() {
        return searchRouteMenu;
    }

    public static List<String> getUserDataMenu() {
        return userDataMenu;
    }

    public static List<String> getFooterMenu() {
        return footerMenu;
    }
}
