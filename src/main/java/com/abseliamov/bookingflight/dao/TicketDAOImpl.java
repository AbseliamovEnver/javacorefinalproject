package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.Ticket;
import com.abseliamov.bookingflight.entity.TypeCabin;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl {
    private File file = IOUtil.getFile("file.price");
    private final String PRICE_FILE_HEADER = "Id, routeId, typeCabin, place, price";
    private final String COMMA_SEPARATOR = ",";
    private List<Ticket> ticketsFromFile = new ArrayList<>();

    public void getTicketByRouteId(long routeId) {
    }

    private List<Ticket> readFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = reader.readLine()) != null) {
                if (data.equals(PRICE_FILE_HEADER)) {
                    continue;
                }
                String[] cityData = data.split(",");
                ticketsFromFile.add(new Ticket(Long.parseLong(cityData[0]),
                        Long.parseLong(cityData[1]),
                        TypeCabin.valueOf(cityData[2]),
                        Integer.parseInt(cityData[3]),
                        Double.parseDouble(cityData[4])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found " + e);
        } catch (IOException e) {
            System.out.println("Error read from file " + file.getName() + " " + e);
        }
        return ticketsFromFile;
    }
}
