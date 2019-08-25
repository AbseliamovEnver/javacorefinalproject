package com.abseliamov.bookingflight.view;

import com.abseliamov.bookingflight.controller.CityController;
import com.abseliamov.bookingflight.controller.RouteController;
import com.abseliamov.bookingflight.entity.TypeCabin;
import com.abseliamov.bookingflight.utils.CurrentUser;
import com.abseliamov.bookingflight.utils.InputData;
import com.abseliamov.bookingflight.utils.ReadInputData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PassengerMenu {
    private InitializationUser initializationUser;
    private CityController cityController;
    private RouteController routeController;
    private CurrentUser currentUser;
    Integer itemMenu;

    public PassengerMenu(InitializationUser initializationUser, CityController cityController,
                         RouteController routeController, CurrentUser currentUser) {
        this.initializationUser = initializationUser;
        this.cityController = cityController;
        this.routeController = routeController;
        this.currentUser = currentUser;
    }

    public void mainMenu() {
        MenuInputOutputService.printMenuHeader(MenuContentList.getHeaderSite());
        do {
            MenuInputOutputService.printMenuItem(MenuContentList.getMainMenu());
            itemMenu = Integer.parseInt(ReadInputData.
                    getValidInputData("Select MAIN MENU item: ", InputData.INTEGER));
            switch (itemMenu) {
                case 0:
                    MenuInputOutputService.printMenuHeader(MenuContentList.getFooterMenu());
                    System.exit(0);
                    break;
                case 1:
                    initializationUser.loginUser();
                    if (currentUser != null) {
                        searchMenu();
                    }
                    break;
                case 2:
                    initializationUser.registrationUser();
                    if (currentUser != null) {
                        searchMenu();
                    }
                    break;
                default:
                    System.out.println("Error. Incorrect menu item.\n*********************************");
                    break;
            }
        } while (!MenuInputOutputService.validateNumberSize(itemMenu, MenuContentList.getMainMenu().size()));
    }

    private void searchMenu() {
        long cityDepartureId = 0;
        long cityArrivalId = 0;
        LocalDate date = null;
        TypeCabin typeCabin = null;
        int numberPassengers = 0;
        do {
            MenuInputOutputService.printMenuItem(MenuContentList.getSearchMenu());
            itemMenu = Integer.parseInt(ReadInputData.
                    getValidInputData("Select TICKET MENU item: ", InputData.INTEGER));
            switch (itemMenu) {
                case 0:
                    return;
                case 1:
                    if (cityController.getAllCity()) {
                        do {
                            cityDepartureId = Integer.parseInt(ReadInputData.
                                    getValidInputData("Select departure city id: ", InputData.INTEGER));
                            cityArrivalId = Integer.parseInt(ReadInputData.
                                    getValidInputData("Select arrival city id: ", InputData.INTEGER));
                            if (cityDepartureId == cityArrivalId) {
                                System.out.println("Departure and arrival cities should be different.");
                            }
                        } while (cityDepartureId == cityArrivalId);

                        String dateDeparture = ReadInputData.
                                getValidInputData("Enter departure date in format \'dd.MM.yyyy\': ", InputData.DATE);
                        date = LocalDate.parse(dateDeparture, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                        TypeCabin.printEnum(TypeCabin.values());
                        int classCabin = Integer.parseInt(ReadInputData.
                                getValidInputData("Select ID class cabin: ", InputData.INTEGER));
                        typeCabin = TypeCabin.getType(classCabin);

                        numberPassengers = Integer.parseInt(ReadInputData.
                                getValidInputData("Enter the number of passengers: ", InputData.INTEGER));
                    }
                    routeController.getRoutesByRequest(cityDepartureId, cityArrivalId,
                            date, typeCabin, numberPassengers);

                    break;
                case 5:
                    if (currentUser != null) {
                        initializationUser.logoutUser();
                    }
                    break;
                default:
                    System.out.println("Error. Incorrect menu item.\n************************************");
                    break;
            }
        } while (!MenuInputOutputService.validateNumberSize(itemMenu, MenuContentList.getSearchMenu().size()));
    }
}
