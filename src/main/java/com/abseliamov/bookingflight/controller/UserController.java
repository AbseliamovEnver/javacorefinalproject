package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.service.UserDAO;

public class UserController {
    private UserDAO userDAO = new UserDAO();

    public boolean createUser(String firstName, String lastName, String password) {
        return userDAO.createUser(firstName, lastName, password);
    }

    public boolean loginUser(String firstName, String password) {

        return userDAO.loginUser(firstName, password);
    }

    public void logoutUser() {
        userDAO.logoutUser();
    }
}
