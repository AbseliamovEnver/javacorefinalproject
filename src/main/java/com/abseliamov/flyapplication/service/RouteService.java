package com.abseliamov.flyapplication.service;

import com.abseliamov.flyapplication.dao.RouteDao;
import com.abseliamov.flyapplication.dao.TicketDao;
import com.abseliamov.flyapplication.entity.Route;
import com.abseliamov.flyapplication.entity.Ticket;
import com.abseliamov.flyapplication.entity.TypeSeat;
import com.abseliamov.flyapplication.utils.CurrentUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RouteService implements ServiceInterface<Route> {
    private RouteDao routeDao;
    private TicketDao ticketDao;
    private CurrentUser currentUser;

    public RouteService(RouteDao routeDao, TicketDao ticketDao, CurrentUser currentUser) {
        this.routeDao = routeDao;
        this.ticketDao = ticketDao;
        this.currentUser = currentUser;
    }

    @Override
    public void add(Route route) {
        routeDao.create(route);
    }

    @Override
    public Route getById(long id) {
        return routeDao.getById(id);
    }

    @Override
    public List<Route> getAll() {
        return routeDao.getAll();
    }

    @Override
    public void update(Route route) {
        routeDao.update(route);
    }

    @Override
    public void delete(Route route) {
        routeDao.delete(route);
    }

    public List<Route> getRouteByRequest(long departureCityId, long arrivalCityId, LocalDate dateDeparture,
                                         TypeSeat typeSeat, int numberPassengers) {
        LocalDateTime timeDeparture;
        if (dateDeparture.equals(LocalDate.now())) {
            timeDeparture = LocalDateTime.now().plusHours(1);
        } else {
            timeDeparture = dateDeparture.atStartOfDay();
        }
        List<Route> routes = routeDao.getAll().stream()
                .filter(route -> route.getDepartureCity().getId() == departureCityId)
                .filter(route -> route.getArrivalCity().getId() == arrivalCityId)
                .filter(route -> route.getDepartureTime().isAfter(timeDeparture))
                .collect(Collectors.toList());
        if (typeSeat == TypeSeat.ECONOMY) {
            routes = routes.stream()
                    .filter(route -> route.getEconomyClassSeatCount() >= numberPassengers)
                    .collect(Collectors.toList());
        } else if (typeSeat == TypeSeat.BUSINESS) {
            routes = routes.stream()
                    .filter(route -> route.getBusinessClassSeatCount() >= numberPassengers)
                    .collect(Collectors.toList());
        }
        if (!routes.isEmpty()) {
            printRoutes(routes);
        }
        return routes;
    }

    private void printRoutes(List<Route> routes) {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-15s%-32s%-30s%-1s\n", " ", "CITY", "DATE", "CLASS PLACE");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-15s%-19s%-20s%-10s%-1s", "ID", "DEPARTURE", "ARRIVAL",
                "DEPARTURE", "ARRIVAL", "BUSINESS", "ECONOMY\n");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Route route : routes) {
            System.out.printf("%-5d%-15s%-15s%-19s%-23s%-10d%-1d\n",
                    route.getId(),
                    route.getDepartureCity().getName(),
                    route.getArrivalCity().getName(),
                    route.getDepartureTime(),
                    route.getArrivalTime(),
//                    route.getBusinessClass(),
//                    route.getEconomyClass());
                    route.getBusinessClassSeatCount(),
                    route.getEconomyClassSeatCount());
        }
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void reduceSeat(long routeId, long ticketId) {
        Route route = routeDao.getById(routeId);
        Ticket ticket = ticketDao.getById(ticketId);
        if (route != null) {
            if (ticket.getTypeSeat() == TypeSeat.BUSINESS) {
                Route.newBuilder().setNumberBusinessClassSeat(route.getBusinessClassSeatCount() - 1);
            } else if (ticket.getTypeSeat() == TypeSeat.ECONOMY) {
                Route.newBuilder().setNumberEconomyClassSeat(route.getEconomyClassSeatCount() - 1);
            }
        }
        routeDao.update(route);
    }
}
