package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.route.Route;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements GeneralDaoInterface<Route> {
    private File file = IOUtil.getFile("file.routes");
    private final String ROUTES_FILE_HEADER = "routeId, cityDepartureId, cityArrivalId, " +
            "dateDeparture, dateArrival, businessSeats, economySeats";
    private final String COMMA_SEPARATOR = ",";

    @Override
    public void create(Route route) {

    }

    @Override
    public Route getById(long id) {
        return readFromFile(file).stream()
                .filter(route -> route.getId() == id)
                .findFirst().get();
    }

    @Override
    public List<Route> getAll() {
        return readFromFile(file);
    }

    @Override
    public void update(Route route) {
        List<Route> routes = readFromFile(file);
        for (Route routeItem : routes) {
            if (routeItem.getId() == route.getId()) {
                routeItem = route;
                break;
            }
        }
        writeToFile(routes);
    }

    @Override
    public void delete(Route route) {

    }

    private List<Route> readFromFile(File file) {
        List<Route> routesFromFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = reader.readLine()) != null) {
                if (data.equals(ROUTES_FILE_HEADER)) {
                    continue;
                }
                String[] routeData = data.split(",");
                routesFromFile.add(new Route(Long.parseLong(routeData[0].trim()),
                        Long.parseLong(routeData[1].trim()),
                        Long.parseLong(routeData[2].trim()),
                        parseDate(routeData[3].trim()),
                        parseDate(routeData[4].trim()),
                        Integer.parseInt(routeData[5].trim()),
                        Integer.parseInt(routeData[6].trim())));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found " + e);
        } catch (IOException e) {
            System.out.println("Error read from file " + file.getName() + " " + e);
        }
        return routesFromFile;
    }

    private void writeToFile(List<Route> routes) {
        try (FileWriter writer = new FileWriter(file)) {
            StringBuilder builder = new StringBuilder();

            builder.append(ROUTES_FILE_HEADER);
            for (Route routeItem : routes) {
                builder.append("\n");
                builder.append(routeItem.getId());
                builder.append(COMMA_SEPARATOR);
                builder.append(routeItem.getDepartureCityId());
                builder.append(COMMA_SEPARATOR);
                builder.append(routeItem.getArrivalCityId());
                builder.append(COMMA_SEPARATOR);
                builder.append(routeItem.getTimeDeparture());
                builder.append(COMMA_SEPARATOR);
                builder.append(routeItem.getTimeArrival());
                builder.append(COMMA_SEPARATOR);
                builder.append(routeItem.getBusinessClassSeat());
                builder.append(COMMA_SEPARATOR);
                builder.append(routeItem.getEconomyClassSeat());
            }
            writer.write(builder.toString());
        } catch (
                IOException e) {
            System.out.println("Error write to file " + file.getName() + " " + e);
        }
    }

    private LocalDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(dateString, formatter);
    }
}
