package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.Order;
import com.abseliamov.bookingflight.utils.CurrentUser;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class OrderDAOImpl implements GeneralDaoInterface<Order> {
    //    private File file = IOUtil.getFile("file.cities");
    private final String FILE_PROPERTIES = "src/main/resources/properties/file.properties";
    private final String ORDER_DIRECTORY = "order.directory";
    private final String ORDERS_FILE_HEADER = "ID, routeID, ticketID";
    private final String COMMA_SEPARATOR = ",";
    private CurrentUser currentUser;

    public OrderDAOImpl(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void create(Order order) {
        File file = getOrderFile(new Date().getTime() + ".csv");
        List<Order> orders = readFromFile(file);
        boolean orderExists = orders.stream().anyMatch(order::equals);
        if (!orderExists) {
            orders.add(order);
            writeToFile(file, orders);
        }
    }

    @Override
    public Order getById(long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Order order) {

    }

    private List<Order> readFromFile(File file) {
        List<Order> ordersFromFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = reader.readLine()) != null) {
                if (data.equals(ORDERS_FILE_HEADER)) {
                    continue;
                }
                String[] orderData = data.split(",");
                ordersFromFile.add(new Order(Long.parseLong(orderData[0]),
                        Long.parseLong(orderData[1]), Long.parseLong(orderData[2])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found " + e);
        } catch (IOException e) {
            System.out.println("Error read from file " + file.getName() + " " + e);
        }
        return ordersFromFile;
    }

    private void writeToFile(File file, List<Order> orders) {
        try (FileWriter writer = new FileWriter(file)) {
            StringBuilder builder = new StringBuilder();

            builder.append(ORDERS_FILE_HEADER);
            for (Order orderItem : orders) {
                builder.append("\n");
                builder.append(orderItem.getId());
                builder.append(COMMA_SEPARATOR);
                builder.append(orderItem.getRouteID());
                builder.append(COMMA_SEPARATOR);
                builder.append(orderItem.getTicketID());
            }
            writer.write(builder.toString());
        } catch (
                IOException e) {
            System.out.println("Error write to file " + file.getName() + " " + e);
        }
    }

    private File getOrderFile(String orderFileName) {
        Properties property = new Properties();
        File directory;
        File fileOrder;
        try {
            property.load(new FileReader(FILE_PROPERTIES));
        } catch (IOException e) {
            System.out.println("Error load properties file " + e);
        }
        String regex = "[^A-Za-z0-9]";
        String userDirectory = property.getProperty(ORDER_DIRECTORY)
                + currentUser.getUser().getFirstName().replaceAll(regex, "") + "_"
                + currentUser.getUser().getLastName().replaceAll(regex, "") + "_"
                + currentUser.getUser().getId() + "/";

        directory = new File(userDirectory);
        fileOrder = new File(orderFileName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!fileOrder.exists()) {
            try {
                fileOrder.createNewFile();
            } catch (IOException e) {
                System.out.println("Error create order file " + e);
            }
        }
        return fileOrder;
    }
}
