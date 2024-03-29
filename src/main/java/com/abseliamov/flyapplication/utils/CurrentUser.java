package com.abseliamov.flyapplication.utils;

import com.abseliamov.flyapplication.entity.User;

public class CurrentUser {
    private static CurrentUser currentUser;
    private User user;

    private CurrentUser() {
    }

    public static CurrentUser getInstance() {
        if (currentUser == null) {
            currentUser = new CurrentUser();
        }
        return currentUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
