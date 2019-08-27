package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.Role;
import com.abseliamov.bookingflight.entity.User;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements GeneralDaoInterface<User> {
    private File file = IOUtil.getFile("file.users");
    private final String USERS_FILE_HEADER = "Id, firstName, lastName, password, role";
    private final String COMMA_SEPARATOR = ",";

    @Override
    public void create(User user) {
        boolean userExist = false;
        long userID = 0;
        List<User> users = readFromFile(file);
        if (users.size() == 0) {
            users.add(new User(userID, user.getFirstName(), user.getLastName(), user.getPassword(), user.getRole()));
            writeToFile(users);
            userExist = true;
        } else {
            for (User userItem : users) {
                if (userItem.equals(user)) {
                    System.out.println("Such user already exists.");
                    userExist = true;
                    break;
                }
            }
        }
        if (!userExist) {
            long newId = users.get(users.size() - 1).getId() + 1;
            users.add(new User(newId, user.getFirstName(), user.getLastName(), user.getPassword(), user.getRole()));
            writeToFile(users);
        }
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return readFromFile(file);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    public User loginUser(String firstName, String password) {
        return getAll().stream()
                .filter(user -> user.getFirstName().equals(firstName))
                .filter(user -> user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    private List<User> readFromFile(File file) {
        List<User> usersFromFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = reader.readLine()) != null) {
                if (data.equals(USERS_FILE_HEADER)) {
                    continue;
                }
                String[] userData = data.split(",");
                usersFromFile.add(new User(Long.parseLong(userData[0]), userData[1], userData[2], userData[3],
                        Role.valueOf(userData[4])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found " + e);
        } catch (IOException e) {
            System.out.println("Error read from file " + file.getName() + " " + e);
        }
        return usersFromFile;
    }

    private void writeToFile(List<User> users) {
        try (FileWriter writer = new FileWriter(file)) {
            StringBuilder builder = new StringBuilder();

            builder.append(USERS_FILE_HEADER);
            for (User userItem : users) {
                builder.append("\n");
                builder.append(userItem.getId());
                builder.append(COMMA_SEPARATOR);
                builder.append(userItem.getFirstName());
                builder.append(COMMA_SEPARATOR);
                builder.append(userItem.getLastName());
                builder.append(COMMA_SEPARATOR);
                builder.append(userItem.getPassword());
                builder.append(COMMA_SEPARATOR);
                builder.append(userItem.getRole().toString());
            }
            writer.write(builder.toString());
        } catch (
                IOException e) {
            System.out.println("Error write to file " + file.getName() + " " + e);
        }
    }
}
