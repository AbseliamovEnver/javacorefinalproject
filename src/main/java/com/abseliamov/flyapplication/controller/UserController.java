package com.abseliamov.flyapplication.controller;

import com.abseliamov.flyapplication.utils.CurrentUser;
import com.abseliamov.flyapplication.entity.Role;
import com.abseliamov.flyapplication.entity.User;
import com.abseliamov.flyapplication.service.UserService;

import java.util.List;

public class UserController {
    private UserService userService;
    private CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    public void addUser(User user) {
        userService.add(user);
    }

    public User getUserById(long id) {
        return userService.getById(id);
    }

    public List<User> getAllUser() {
        return userService.getAll();
    }

    public void updateUser(User user) {
        userService.update(user);
    }

    public void deleteUser(User user) {
        userService.delete(user);
    }

    public boolean loginUser(String firstName, String password) {
        return userService.loginUser(firstName, password);
    }

    public void logoutUser() {
        userService.logoutUser();
    }

    public void setUserRole(User updateUser, Role role) {
        userService.setUserRole(updateUser, role);
    }
}
