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
    private final String ROUTES_FILE_HEADER = "Id, cityName";
    private final String COMMA_SEPARATOR = ",";
    private List<Route> routesFromFile = new ArrayList<>();

    @Override
    public void create(Route route) {

    }

    @Override
    public Route getById(long id) {
        return null;
    }

    @Override
    public List<Route> getAll() {
//        List<Route> routes = new ArrayList<>();
//        String data;
//        try {
//            while ((data = daoUtil.getReader("file.routes").readLine()) != null) {
//                String[] routeData = data.split(",");
//                routes.add(new Route(Long.parseLong(routeData[0].trim()),
//                        Long.parseLong(routeData[1].trim()),
//                        Long.parseLong(routeData[2].trim()),
//                        parseDate(routeData[3].trim()),
//                        parseDate(routeData[4].trim()),
//                        Integer.parseInt(routeData[5].trim()),
//                        Integer.parseInt(routeData[6].trim())));
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading file with routes " + e);
//        }
//        return routes;
        return readFromFile(file);
    }

    @Override
    public void update(Route route) {

    }

    @Override
    public void delete(Route route) {

    }

    private List<Route> readFromFile(File file) {
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

    private LocalDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(dateString, formatter);
    }
}
