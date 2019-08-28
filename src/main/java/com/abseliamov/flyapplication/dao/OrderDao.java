package com.abseliamov.flyapplication.dao;

import com.abseliamov.flyapplication.entity.Order;
import com.abseliamov.flyapplication.utils.CurrentUser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class OrderDao extends AbstractDao<Order> {
    private UserDao userDao;
    private TicketDao ticketDao;
    private CurrentUser currentUser;

    private final String FILE_PROPERTIES = "src/main/resources/properties/file.properties";
    private final String ORDER_DIRECTORY = "order.directory";
    private final String ORDERS_FILE_HEADER = "orderId, user, ticket";
    private final String COMMA_SEPARATOR = ",";
    private File file = getOrderFile(getOrderDirectory());

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
        if (orders.size() == 0) {
            orders.add(new Order(orderId, order.getUser(), order.getTicket()));
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
            orders.add(new Order(newId, order.getUser(), order.getTicket()));
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
            System.out.println("Error " + file + " is empty.");
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
            System.out.println("Error " + file + " is empty.");
        }
    }

    @Override
    Order parseDataFromFile(String[] orderData) {
        return new Order(
                Long.parseLong(orderData[0]),
                userDao.getUserByItem(orderData[1]),
                ticketDao.getTicketByItem(orderData[2]));
    }

    @Override
    StringBuilder buildDataToFile(List<Order> orders) {
        StringBuilder builder = new StringBuilder();
        builder.append(ORDERS_FILE_HEADER);
        for (Order orderItem : orders) {
            builder.append("\n");
            builder.append(orderItem.getId());
            builder.append(COMMA_SEPARATOR);
            builder.append(orderItem.getUser());
            builder.append(COMMA_SEPARATOR);
            builder.append(orderItem.getTicket());
        }
        return builder;
    }

    public List<Order> getAll() {
        return getAll(file, ORDERS_FILE_HEADER);
    }

    public void writeToFile(List<Order> orders) {
        writeToFile(file, orders);
    }

    long getId(List<Order> orders) {
        final long[] id = {1};
        orders.forEach(order -> {
            if (order.getId() > id[0]) {
                id[0] = order.getId() + 1;
            }
        });
        return id[0];
    }

    private File getOrderDirectory() {
        Properties property = new Properties();
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

        File directory = new File(userDirectory);

        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory;
    }

    private File getOrderFile(File fileDirectory) {
        String orderFileName = new Date().getTime() + ".csv";
        File fileOrder = new File(fileDirectory + orderFileName);
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
