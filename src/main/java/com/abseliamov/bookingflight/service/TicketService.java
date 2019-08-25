package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.dao.RouteDAOImpl;
import com.abseliamov.bookingflight.dao.TicketDAOImpl;
import com.abseliamov.bookingflight.entity.Ticket;
import com.abseliamov.bookingflight.entity.route.Route;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private RouteDAOImpl routeDAOImpl;
    private RouteService routeService;
    private TicketDAOImpl ticketDAO;

    public TicketService(RouteDAOImpl routeDAOImpl, RouteService routeService, TicketDAOImpl ticketDAO) {
        this.routeDAOImpl = routeDAOImpl;
        this.routeService = routeService;
        this.ticketDAO = ticketDAO;
    }

    public List<Route> getRoutesByCity(long departureCityId, long arrivalCityId) {
        List<Route> routes = routeDAOImpl.getAll();
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
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-15s%-32s%-30s%-1s\n", " ", "Ticket", "Date", "Class seats");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-15s%-19s%-20s%-10s%-1s", "ID", "Departure", "Arrival",
                "Departure", "Arrival", "Business", "Economy\n");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Route route : routes) {
            System.out.printf("%-5d%-15s%-15s%-19s%-23s%-10d%-1d\n", route.getId(),
                    routeService.getCity((int) route.getDepartureCityId()),
                    routeService.getCity((int) route.getArrivalCityId()),
                    route.getTimeDeparture(), route.getTimeArrival(),
                    route.getBusinessClassSeat(), route.getEconomyClassSeat());
        }
    }

    public Ticket getTicketByRouteId(long routeId) {
        ticketDAO.getTicketByRouteId(routeId);
        return null;
    }
}
