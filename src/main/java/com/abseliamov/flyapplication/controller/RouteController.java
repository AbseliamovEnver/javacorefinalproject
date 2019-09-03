package com.abseliamov.flyapplication.controller;

import com.abseliamov.flyapplication.entity.Ticket;
import com.abseliamov.flyapplication.utils.CurrentUser;
import com.abseliamov.flyapplication.entity.Route;
import com.abseliamov.flyapplication.entity.TypeSeat;
import com.abseliamov.flyapplication.service.RouteService;

import java.time.LocalDate;
import java.util.List;

public class RouteController {
    private RouteService routeService;
    private CurrentUser currentUser;

    public RouteController(RouteService routeService, CurrentUser currentUser) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    public void addRoute(Route route) {
        routeService.add(route);
    }

    public Route getRouteById(long id) {
        return routeService.getById(id);
    }

    public List<Route> getAllRoute() {
        return routeService.getAll();
    }

    public void updateRoute(Route route) {
        routeService.update(route);
    }

    public void deleteRoute(Route route) {
        routeService.delete(route);
    }

    public List<Route> getRouteByRequest(String departureCityName, String arrivalCityName, LocalDate dateDeparture,
                                         TypeSeat typeSeat, int numberPassengers) {
        return routeService.getRouteByRequest(departureCityName, arrivalCityName, dateDeparture, typeSeat, numberPassengers);
    }

    public void reduceSeat(long routeId, int ticketNumber) {
        routeService.reduceSeat(routeId, ticketNumber);
    }

    public void incrementTicket(long routeId, Ticket ticket) {
        routeService.incrementTicket(routeId, ticket);
    }
}
