package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.dao.UserDAOImpl;
import com.abseliamov.bookingflight.entity.Role;
import com.abseliamov.bookingflight.entity.User;
import com.abseliamov.bookingflight.utils.CurrentUser;

public class UserService {
    private UserDAOImpl userDAO;
    private CurrentUser currentUser;

    public UserService(UserDAOImpl userDAO, CurrentUser currentUser) {
        this.userDAO = userDAO;
        this.currentUser = currentUser;
    }

    //    private static final String USERS_BASE = "src/main/resources/users.csv";
//    private static BufferedReader reader;
//    private static BufferedWriter writer;
//    private CurrentUser currentUser;
//
//
//    static {
//        try {
//            reader = new BufferedReader(new FileReader(USERS_BASE));
//            writer = new BufferedWriter(new FileWriter(USERS_BASE, true));
//        } catch (FileNotFoundException e) {
//            System.out.println("Error reading file users.csv " + e);
//        } catch (IOException e) {
//            System.out.println("Error writing file users.csv " + e);
//        }
//    }

    public void createUser(String firstName, String lastName, String password) {
        userDAO.create(new User(0, firstName, lastName, password, Role.USER));
    }

    public boolean loginUser(String firstName, String password) {
        User user = userDAO.loginUser(firstName, password);
        if (user != null) {
            currentUser.setUser(user);
            return true;
        }
        return false;
    }

    public void logoutUser() {
        if (currentUser != null) {
            currentUser.setUser(null);
        }
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

    private void setUserRole(User updateUser, Role role) {
        if (CurrentUser.getInstance().getUser().getRole() == Role.ADMIN) {
            switch (role) {
                case ADMIN:
                    updateUser.setRole(Role.ADMIN);
                    break;
                case USER:
                    updateUser.setRole(Role.USER);
                    break;
                default:
                    updateUser.setRole(Role.GUEST);
                    break;
            }
        } else {
            System.out.println("You do not have administrative permissions.");
        }
    }
}
