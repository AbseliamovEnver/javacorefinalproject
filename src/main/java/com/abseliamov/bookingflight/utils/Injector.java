package com.abseliamov.bookingflight.utils;

import com.abseliamov.bookingflight.controller.CityController;
import com.abseliamov.bookingflight.controller.RouteController;
import com.abseliamov.bookingflight.controller.UserController;
import com.abseliamov.bookingflight.dao.CityDAOImpl;
import com.abseliamov.bookingflight.dao.RouteDAOImpl;
import com.abseliamov.bookingflight.dao.UserDAOImpl;
import com.abseliamov.bookingflight.service.CityService;
import com.abseliamov.bookingflight.service.RouteService;
import com.abseliamov.bookingflight.service.UserService;
import com.abseliamov.bookingflight.view.InitializationUser;
import com.abseliamov.bookingflight.view.PassengerMenu;

public class Injector {

    private static CurrentUser currentUser = CurrentUser.getInstance();
    private static UserDAOImpl userDAO = new UserDAOImpl();
    private static UserService userService = new UserService(userDAO, currentUser);
    private static UserController userController = new UserController(userService, currentUser);
    private static InitializationUser initializationUser = new InitializationUser(userController);

    private static CityDAOImpl cityDAOImpl = new CityDAOImpl();
    private static CityService cityService = new CityService(cityDAOImpl, currentUser);
    private static CityController cityController = new CityController(cityService, currentUser);

    private static RouteDAOImpl routeDAOImpl = new RouteDAOImpl();
    private static RouteService routeService = new RouteService(routeDAOImpl, cityService);
    private static RouteController routeController = new RouteController(routeService, currentUser);

    private static PassengerMenu passengerMenu = new PassengerMenu(initializationUser, cityController, routeController);

    public static CurrentUser getCurrentUser() {
        return currentUser;
    }

    public static UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static UserController getUserController() {
        return userController;
    }

    public static InitializationUser getInitializationUser() {
        return initializationUser;
    }

    public static CityDAOImpl getCityDAOImpl() {
        return cityDAOImpl;
    }

    public static CityService getCityService() {
        return cityService;
    }

    public static CityController getCityController() {
        return cityController;
    }

    public static RouteDAOImpl getRouteDAOImpl() {
        return routeDAOImpl;
    }

    public static RouteService getRouteService() {
        return routeService;
    }

    public static RouteController getRouteController() {
        return routeController;
    }

    public static PassengerMenu getPassengerMenu() {
        return passengerMenu;
    }

    private Injector() {
    }
}
