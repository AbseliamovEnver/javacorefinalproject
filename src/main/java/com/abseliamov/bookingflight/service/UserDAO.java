package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.entity.User;
import com.abseliamov.bookingflight.utils.CurrentUser;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class UserDAO {
    private static final String USERS_BASE = "src/main/resources/users.csv";
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private CurrentUser currentUser;


    static {
        try {
            reader = new BufferedReader(new FileReader(USERS_BASE));
            writer = new BufferedWriter(new FileWriter(USERS_BASE, true));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file users.csv " + e);
        } catch (IOException e) {
            System.out.println("Error writing file users.csv " + e);
        }
    }

    public boolean createUser(String firstName, String lastName, String password) {
        boolean createUser = false;
        Set<User> users = new HashSet<>();
//        String userData;
        File file = IOUtil.checkFileExists(USERS_BASE);

        String data;
        boolean findUser = false;
        try {
            if (USERS_BASE.length() != 0) {
                while ((data = getReader().readLine()) != null && !findUser) {
                    String[] userData = data.split(",");
                    if (userData[1].equals(firstName) && userData[3].equals(password)) {
                        findUser = true;
                        continue;
                    }
//                    users.add(new User(userData[0], userData[1], userData[2], Role.USER));
                }
            } else {
//                getWriter().write(String.valueOf(new User(firstName, lastName, password, Role.USER)));
                findUser = true;
            }
            if (!findUser){
                getWriter().write(String.valueOf(users));
            }else {

            }
        } catch (IOException e) {
            System.out.println("Error check user in base " + e);
        }
        return false;
    }

    public boolean loginUser(String firstName, String password) {

        return false;
    }

    public void logoutUser() {

    }

    private boolean checkUserInBase(String firstName, String password) {
        String data;
        boolean findUser = false;
//        try {
//            if (USERS_BASE.length() != 0) {
//                while ((data = getReader().readLine()) != null && !findUser) {
//                    String[] userData = data.split(",");
//                    if (userData[1].equals(firstName) && userData[3].equals(password))
//                        findUser = true;
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error check user in base " + e);
//        }
        return findUser;
    }

    private static BufferedReader getReader() {
        return reader;
    }

    private static BufferedWriter getWriter() {
        return writer;
    }
}
