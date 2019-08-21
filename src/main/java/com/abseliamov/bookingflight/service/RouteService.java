package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.dao.RouteDAO;
import com.abseliamov.bookingflight.entity.route.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteService {
    private RouteDAO routeDAO = new RouteDAO();
    private CityService cityService = new CityService();

    public List<Route> getRoutesByCity(long departureCityId, long arrivalCityId) {
        List<Route> routes = routeDAO.getAllRoutes();
        List<Route> routeList = new ArrayList<>();
        if (!routes.isEmpty()) {
            for (Route routeItem : routes) {
                if (routeItem.getDepartureCityId() == departureCityId
                        && routeItem.getArrivalCityId() == arrivalCityId) {
                    routeList.add(routeItem);
                }
            }
            if (!routeList.isEmpty()) {
                printRoutes(routeList);
            } else {
                System.out.println("Routes between these cities not found.");
            }
        } else {
            System.out.println("List of routes is empty.");
        }
        return routeList;
    }

    public boolean getAllRoutes() {
        return false;
    }

    private void printRoutes(List<Route> routes) {
        for (Route route : routes) {
            System.out.println("******************************************");
            System.out.println("Route:" +
                    "\n\tID:\t\t\t\t\t\t" + route.getId() +
                    "\n\tDeparture City:\t\t\t" + cityService.getCity((int) route.getDepartureCityId()) +
                    "\n\tArrival City:\t\t\t" + cityService.getCity((int) route.getArrivalCityId()) +
                    "\n\tDate Departure:\t\t\t" + route.getDateDeparture() +
                    "\n\tDate Arrival:\t\t\t" + route.getDateArrival() +
                    "\n\tBusiness Class Seats:\t" + route.getBusinessClassSeat() +
                    "\n\tEconomy Class Seats:\t" + route.getEconomyClassSeat() +
                    "\n******************************************");
        }
    }
}
