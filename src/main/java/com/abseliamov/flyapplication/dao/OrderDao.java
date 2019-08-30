package com.abseliamov.flyapplication.dao;

import com.abseliamov.flyapplication.entity.Order;
import com.abseliamov.flyapplication.utils.CurrentUser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderDao extends AbstractDao<Order> {
    private UserDao userDao;
    private TicketDao ticketDao;
    private CurrentUser currentUser;

    private final String FILE_PROPERTIES = "src/main/resources/properties/file.properties";
    private final String ORDER_DIRECTORY = "order.directory";
    private final String ORDERS_FILE_HEADER = "ORDER ID, ROUTE ID, TICKET ID";
    private final String COMMA_SEPARATOR = ",";

    public OrderDao(UserDao userDao, TicketDao ticketDao, CurrentUser currentUser) {
        this.userDao = userDao;
        this.ticketDao = ticketDao;
        this.currentUser = currentUser;
    }

    @Override
    public void create(Order order) {
        boolean orderExist = false;
        long orderId = 1;
        List<Order> orders = getAll();
        if (orders.isEmpty()) {
            orders.add(new Order(orderId, order.getRouteId(), order.getTicketId()));
            writeToFile(orders);
            orderExist = true;
        } else {
            for (Order orderItem : orders) {
                if (orderItem.equals(order)) {
                    System.out.println("Such order already exists.");
                    orderExist = true;
                    break;
                }
            }
        }
        if (!orderExist) {
            long newId = getId(orders);
            orders.add(new Order(newId, order.getRouteId(), order.getTicketId()));
            writeToFile(orders);
        }
    }

    @Override
    public Order getById(long id) {
        return getAll().stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Order order) {
        List<Order> orders = getAll();
        List<Order> updateList = new ArrayList<>();
        if (!orders.isEmpty()) {
            for (Order orderItem : orders) {
                if (orderItem.getId() == order.getId()) {
                    updateList.add(order);
                    continue;
                }
                updateList.add(orderItem);
            }
            writeToFile(updateList);
        } else {
            System.out.println("Error " + getOrderFile().getName() + " is empty.");
        }
    }

    @Override
    public void delete(Order order) {
        List<Order> orders = getAll();
        List<Order> deleteList = new ArrayList<>();
        if (!orders.isEmpty()) {
            for (Order orderItem : orders) {
                if (orderItem.getId() == order.getId()) {
                    continue;
                }
                deleteList.add(orderItem);
            }
            writeToFile(deleteList);
        } else {
            System.out.println("Error " + getOrderFile().getName() + " is empty.");
        }
    }

    @Override
    Order parseDataFromFile(String[] orderData) {
        return new Order(
                Long.parseLong(orderData[0]),
                Long.parseLong(orderData[1]),
                Long.parseLong(orderData[2]));
    }

    @Override
    StringBuilder buildDataToFile(List<Order> orders) {
        StringBuilder builder = new StringBuilder();
        builder.append(ORDERS_FILE_HEADER);
        for (Order orderItem : orders) {
            builder.append("\n");
            builder.append(orderItem.getId());
            builder.append(COMMA_SEPARATOR);
            builder.append(orderItem.getRouteId());
            builder.append(COMMA_SEPARATOR);
            builder.append(orderItem.getTicketId());
        }
        return builder;
    }

    public List<Order> getAll() {
        return getAll(getOrderFile(), ORDERS_FILE_HEADER);
    }

    public void writeToFile(List<Order> orders) {
        writeToFile(getOrderFile(), orders);
    }

    long getId(List<Order> orders) {
        final long[] id = {1};
        orders.forEach(order -> {
            if (order.getId() >= id[0]) {
                id[0] = order.getId() + 1;
            }
        });
        return id[0];
    }

    private File getOrderFile() {
        Properties property = new Properties();
        try {
            property.load(new FileReader(FILE_PROPERTIES));
        } catch (IOException e) {
            System.out.println("Error load properties file " + e);
        }
        String regex = "[^A-Za-z0-9]";
        String userDirectory = property.getProperty(ORDER_DIRECTORY)
                + currentUser.getUser().getId() + "_"
                + currentUser.getUser().getFirstName().replaceAll(regex, "") + "_"
                + currentUser.getUser().getLastName().replaceAll(regex, "") + "/";

        File directory = new File(userDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File orderFile = new File(directory + "/purchased tickets.csv");
        if (!orderFile.exists()) {
            try {
                orderFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error create order file " + e);
            }
        }

        return orderFile;
    }
}
