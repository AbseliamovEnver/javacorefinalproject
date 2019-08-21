package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.route.Route;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteDAO {
    private static BufferedReader reader;

    static {
        try {
            reader = new BufferedReader(new FileReader(IOUtil.getFile("file.routes")));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file routes.csv " + e);
        }
    }

    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        String data;
        try {
            while ((data = getReader().readLine()) != null) {
                String[] routeData = data.split(",");
                routes.add(new Route(Long.parseLong(routeData[0].trim()),
                        Long.parseLong(routeData[1].trim()), Long.parseLong(routeData[2].trim()),
                        parseDate(routeData[3].trim()), parseDate(routeData[4].trim()),
                        Integer.parseInt(routeData[5].trim()), Integer.parseInt(routeData[6].trim())));
            }
        } catch (IOException e) {
            System.out.println("Error reading file with routes " + e);
        }
        return routes;
    }

    public static BufferedReader getReader() {
        return reader;
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
