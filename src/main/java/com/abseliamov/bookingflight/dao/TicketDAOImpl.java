package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.Ticket;
import com.abseliamov.bookingflight.entity.TypeCabin;
import com.abseliamov.bookingflight.utils.CurrentUser;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TicketDAOImpl implements GeneralDaoInterface<Ticket> {
    CurrentUser currentUser;
    private File file = IOUtil.getFile("file.price");
    private final String PRICE_FILE_HEADER = "routeId, typeCabin, place, price";
    private final String COMMA_SEPARATOR = ",";

    public TicketDAOImpl(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    public List<Ticket> getTicketByRouteId(long routeId) {
        return getAll().stream()
                .filter(ticket -> ticket.getRouteId() == routeId)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Ticket ticket) {

    }

    @Override
    public Ticket getById(long id) {
        return readFromFile(file).stream()
                .filter(ticket -> ticket.getId() == id)
                .findFirst().get();
    }

    @Override
    public List<Ticket> getAll() {
        return readFromFile(file);
    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public void delete(Ticket ticket) {

    }

    private List<Ticket> readFromFile(File file) {
        List<Ticket> ticketsFromFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = reader.readLine()) != null) {
                if (data.equals(PRICE_FILE_HEADER)) {
                    continue;
                }
                String[] ticketData = data.split(",");
                ticketsFromFile.add(new Ticket(
                        Long.parseLong(ticketData[0]),
                        Long.parseLong(ticketData[1]),
                        Integer.parseInt(ticketData[2]),
                        ticketData[3],
                        TypeCabin.valueOf(ticketData[4]),
                        ticketData[5],
                        Double.parseDouble(ticketData[6])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found " + e);
        } catch (IOException e) {
            System.out.println("Error read from file " + file.getName() + " " + e);
        }
        return ticketsFromFile;
    }

//    private void writeToFile(List<User> users) {
//        try (FileWriter writer = new FileWriter(file)) {
//            StringBuilder builder = new StringBuilder();
//
//            builder.append(USERS_FILE_HEADER);
//            for (User userItem : users) {
//                builder.append("\n");
//                builder.append(userItem.getId());
//                builder.append(COMMA_SEPARATOR);
//                builder.append(userItem.getFirstName());
//                builder.append(COMMA_SEPARATOR);
//                builder.append(userItem.getLastName());
//                builder.append(COMMA_SEPARATOR);
//                builder.append(userItem.getPassword());
//                builder.append(COMMA_SEPARATOR);
//                builder.append(userItem.getRole().toString());
//            }
//            writer.write(builder.toString());
//        } catch (
//                IOException e) {
//            System.out.println("Error write to file " + file.getName() + " " + e);
//        }
//    }
}
