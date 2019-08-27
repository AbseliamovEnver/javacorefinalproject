package com.abseliamov.bookingflight.view;

import com.abseliamov.bookingflight.controller.CityController;
import com.abseliamov.bookingflight.controller.OrderController;
import com.abseliamov.bookingflight.controller.RouteController;
import com.abseliamov.bookingflight.controller.TicketController;
import com.abseliamov.bookingflight.entity.TypeCabin;
import com.abseliamov.bookingflight.entity.route.Route;
import com.abseliamov.bookingflight.utils.CurrentUser;
import com.abseliamov.bookingflight.utils.IOUtil;
import com.abseliamov.bookingflight.utils.InputData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PassengerMenu {
    private InitializationUser initializationUser;
    private CityController cityController;
    private RouteController routeController;
    private TicketController ticketController;
    private OrderController orderController;
    private CurrentUser currentUser;

    public PassengerMenu(InitializationUser initializationUser, CityController cityController,
                         RouteController routeController, TicketController ticketController, CurrentUser currentUser) {
        this.initializationUser = initializationUser;
        this.cityController = cityController;
        this.routeController = routeController;
        this.ticketController = ticketController;
        this.currentUser = currentUser;
    }

    public void mainMenu() {
        IOUtil.printMenuHeader(MenuContentList.getHeaderMenu());
        int mainMenuItem;
        do {
            IOUtil.printMenuItem(MenuContentList.getMainMenu());
            mainMenuItem = Integer.parseInt(IOUtil.getValidInputData("Select MAIN MENU item: ", InputData.INTEGER));

            switch (mainMenuItem) {
                case 0:
                    IOUtil.printMenuHeader(MenuContentList.getFooterMenu());
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
        } while (IOUtil.validateNumberSize(mainMenuItem, MenuContentList.getMainMenu().size()));
    }

    private void searchMenu() {
        int searchMenuItem = 0;
        long routeId = 0;
        long ticketId = 0;

        do {
            if (searchMenuItem == 0) {
                IOUtil.printMenuItem(MenuContentList.getSearchMenu());
                searchMenuItem = Integer.parseInt(IOUtil.getValidInputData("Select TICKET MENU item: ", InputData.INTEGER));
            }
            switch (searchMenuItem) {
                case 0:
                    return;
                case 1:
                    routeId = searchTicket();
                    searchMenuItem = (routeId != 0) ? 2 : 0;
                    break;
                case 2:
                    ticketId = buyTicket(routeId);
                    searchMenuItem = (ticketId != 0) ? 1 : 0;
                    break;
                case 4:
                    if (currentUser != null) {
                        initializationUser.logoutUser();
                    }
                    break;
                default:
                    System.out.println("Error. Incorrect menu item.\n************************************");
                    break;
            }
        } while (IOUtil.validateNumberSize(searchMenuItem, MenuContentList.getSearchMenu().size()));
    }

    private long searchTicket() {
        long cityDepartureId = 0;
        long cityArrivalId = 0;
        int numberPassengers = 0;
        TypeCabin typeCabin = null;
        LocalDate date = null;
        List<Route> routes;

        while (true) {
            if (cityController.getAllCity()) {
                do {
                    cityDepartureId = Long.parseLong(IOUtil.getValidInputData(
                            "Select departure city id: ", InputData.INTEGER));
                    cityArrivalId = Long.parseLong(IOUtil.getValidInputData(
                            "Select arrival city id: ", InputData.INTEGER));
                    if (cityDepartureId == cityArrivalId) {
                        System.out.println("Departure and arrival cities should be different.");
                    }
                } while (cityDepartureId == cityArrivalId);

                String dateDeparture = IOUtil.getValidInputData(
                        "Enter departure date in format \'dd.MM.yyyy\': ", InputData.DATE);
                date = LocalDate.parse(dateDeparture, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                TypeCabin.printEnum(TypeCabin.values());
                int classCabin = Integer.parseInt(IOUtil.getValidInputData(
                        "Select ID class cabin: ", InputData.INTEGER));
                typeCabin = TypeCabin.getType(classCabin);

                numberPassengers = Integer.parseInt(IOUtil.getValidInputData(
                        "Enter the number of passengers: ", InputData.INTEGER));
            }
            routes = routeController.getRoutesByRequest(cityDepartureId, cityArrivalId, date, typeCabin, numberPassengers);
            if (routes != null) {
                long ticketId = Integer.parseInt(IOUtil.getValidInputData(
                        "To purchase, select a flight ID or enter \'0\' for a new search: ", InputData.INTEGER));
                if (ticketId != 0) {
                    return ticketId;
                }
            } else {
                System.out.println("For a given request, nothing found.");
                int result = Integer.parseInt(IOUtil.getValidInputData(
                        "Enter \'1\' for new search or \'0\' for return: ", InputData.INTEGER));
                if (result == 0) return result;
            }
        }
    }

    private long buyTicket(long routeId) {
        long reset = 0;
        while (true) {
            if (routeId == 0) {
                System.out.println("Please search route ID.");
                long selectId = Long.parseLong(IOUtil.getValidInputData(
                        "Enter \'1\' to find the route or \'0\' to return: ", InputData.INTEGER));
                return selectId;
            } else {
                if (ticketController.getListTicketsByRouteId(routeId) != null) {
                    long selectTicketId = Long.parseLong(IOUtil.getValidInputData(
                            "Select ID ticket or enter \'0\' to return: ", InputData.INTEGER));
                    if (selectTicketId != 0) {
                        ticketController.orderConfirm(routeId, selectTicketId);
                        int order = Integer.parseInt(IOUtil.getValidInputData(
                                "Enter \'1\' to confirm the order or enter \'0\' to cancel: ", InputData.INTEGER));
                        if (order == 1) {
                            orderController.createOrder(routeId, selectTicketId);
                            routeController.reduceSeat(routeId, selectTicketId);
                            return reset;
                        } else {
                            return reset;
                        }
                    }
                    return selectTicketId;
                }
            }
        }
    }
}
