package com.abseliamov.bookingflight.view;

import com.abseliamov.bookingflight.controller.CityController;
import com.abseliamov.bookingflight.controller.RouteController;
import com.abseliamov.bookingflight.utils.InputData;
import com.abseliamov.bookingflight.utils.ReadInputData;

public class PassengerMenu {
    public static InitializationUser initializationUser = new InitializationUser();
    private CityController cityController = new CityController();
    private RouteController routeController = new RouteController();
    Integer itemMenu;

    public void mainMenu() {
        MenuInputOutputService.printMenuHeader(MenuContentList.getHeaderSite());
        do {
            MenuInputOutputService.printMenuItem(MenuContentList.getMainMenu());
            itemMenu = Integer.parseInt(ReadInputData.
                    getValidInputData("Select main menu item: ", InputData.INTEGER));
            switch (itemMenu) {
                case 0:
                    MenuInputOutputService.printMenuHeader(MenuContentList.getFooterMenu());
                    System.exit(0);
                    break;
                case 1:
                    MenuInputOutputService.printMenuItem(MenuContentList.getSearchMenu());
                    searchMenu();
                    break;
                case 2:
                    initializationUser.registrationUser();
                    break;
                default:
                    System.out.println("Error. Incorrect menu item.\n*********************************");
                    break;
            }
        } while (!MenuInputOutputService.validateNumberSize(MenuContentList.getMainMenu().size(), itemMenu));
    }

    private void searchMenu() {
        long cityDepartureId = 0;
        long cityArrivalId = 0;
        do {
            itemMenu = Integer.parseInt(ReadInputData.
                    getValidInputData("Select search menu item: ", InputData.INTEGER));
            switch (itemMenu) {
                case 0:
                    return;
                case 1:
                    if (cityController.getAllCity()) {
                        cityDepartureId = Integer.parseInt(ReadInputData.
                                getValidInputData("Select departure city index: ", InputData.INTEGER));
                        cityArrivalId = Integer.parseInt(ReadInputData.
                                getValidInputData("Select arrival city index: ", InputData.INTEGER));
                    }
                    routeController.getRoutesByCity(cityDepartureId, cityArrivalId);
                    break;
                default:
                    System.out.println("Error. Incorrect menu item.\n*********************************");
                    break;
            }
        } while (!MenuInputOutputService.validateNumberSize(MenuContentList.getSearchMenu().size(), itemMenu));
    }
}