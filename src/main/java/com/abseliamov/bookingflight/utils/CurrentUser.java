package com.abseliamov.bookingflight.utils;

public class CurrentUser {
    private static CurrentUser currentUser;

    private CurrentUser() {
    }

    public static CurrentUser getInstance() {
        if (currentUser == null) {
            currentUser = new CurrentUser();
        }
        return currentUser;
    }
}
