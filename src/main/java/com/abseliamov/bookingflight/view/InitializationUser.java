package com.abseliamov.bookingflight.view;

import com.abseliamov.bookingflight.controller.UserController;
import com.abseliamov.bookingflight.utils.InputData;
import com.abseliamov.bookingflight.utils.ReadInputData;

public class InitializationUser {
    private UserController userController = new UserController();

    public void loginUser() {
        String firstName;
        String password;
        boolean loginUser = false;

        do {
            firstName = ReadInputData.getValidInputData("Enter your name: ", InputData.STRING);
            if (firstName.isEmpty() || firstName.contains(" ")) {
                System.out.println("\'" + firstName + "\' - it is incorrect value");
                continue;
            }
            password = ReadInputData.getValidInputData("Enter your password: ", InputData.STRING);
            if (password.isEmpty() || password.contains(" ")) {
                System.out.println("\'" + password + "\' - it is incorrect value");
                continue;
            }
            loginUser = userController.loginUser(firstName, password);
            if (!loginUser) {
                System.out.println("The name or password that you entered is not registered." +
                        "\nPlease enter the correct data or register");
            }
        } while (!loginUser);
        System.out.println(firstName + " welcome to our site!!!");
    }

    public void registrationUser() {
        String firstName;
        String lastName;
        String password;
        String confirmPassword;
        boolean createUser = false;

        do {
            firstName = ReadInputData.getValidInputData("Enter your first name: ", InputData.STRING);
            if (firstName.isEmpty() || firstName.contains(" ")) {
                System.out.println("\'" + firstName + "\' - it is incorrect value");
                continue;
            }
            lastName = ReadInputData.getValidInputData("Enter your last name: ", InputData.STRING);
            if (lastName.isEmpty() || lastName.contains(" ")) {
                System.out.println("\'" + lastName + "\' - it is incorrect value");
                continue;
            }
            password = ReadInputData.getValidInputData("Enter your password: ", InputData.STRING);
            if (password.isEmpty() || password.contains(" ")) {
                System.out.println("\'" + password + "\' - it is incorrect value");
                continue;
            } else {
                confirmPassword = ReadInputData.getValidInputData("Сonfirm password again: ", InputData.STRING);
                if (!password.equals(confirmPassword)) {
                    System.out.println("\'" + confirmPassword + "\' - it is incorrect value");
                    continue;
                }
            }
            createUser = userController.createUser(firstName, lastName, password);
            if (!createUser) {
                System.out.println("User with such data already exists.\nPlease enter other data.");
            }
        } while (!createUser);
        System.out.println("You have successfully registered.\n\tWelcome to our site!!!");
    }
}
