package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.service.UserService;
import com.abseliamov.bookingflight.utils.CurrentUser;

public class UserController {
    private UserService userService;
    private CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    public void createUser(String firstName, String lastName, String password) {
        userService.createUser(firstName, lastName, password);
    }

    public boolean loginUser(String firstName, String password) {
        return userService.loginUser(firstName, password);
    }

    public void logoutUser() {
        userService.logoutUser();
    }
}
