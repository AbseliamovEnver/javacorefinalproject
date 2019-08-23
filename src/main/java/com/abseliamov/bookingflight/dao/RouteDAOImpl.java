package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.route.Route;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteDAOImpl implements GeneralDaoInterface<Route> {
    IODaoUtil daoUtil = new IODaoUtil();

    @Override
    public void create(Route route) {

    }

    @Override
    public Route getById() {
        return null;
    }

    @Override
    public List<Route> getAll() {
        List<Route> routes = new ArrayList<>();
        String data;
        try {
            while ((data = daoUtil.getReader("file.routes").readLine()) != null) {
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

    @Override
    public void update(Route route) {

    }

    @Override
    public void delete(Route route) {

    }

    private String parseDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error parse date: " + e);
        }
        return simpleDateFormat.format(date);
    }
}
