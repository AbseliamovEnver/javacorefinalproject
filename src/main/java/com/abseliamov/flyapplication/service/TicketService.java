package com.abseliamov.flyapplication.service;

import com.abseliamov.flyapplication.dao.RouteDao;
import com.abseliamov.flyapplication.dao.TicketDao;
import com.abseliamov.flyapplication.entity.Route;
import com.abseliamov.flyapplication.entity.Ticket;
import com.abseliamov.flyapplication.utils.CurrentUser;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService implements ServiceInterface<Ticket> {
    TicketDao ticketDao;
    RouteDao routeDao;
    CurrentUser currentUser;

    public TicketService(TicketDao ticketDao, RouteDao routeDao, CurrentUser currentUser) {
        this.ticketDao = ticketDao;
        this.routeDao = routeDao;
        this.currentUser = currentUser;
    }

    @Override
    public void add(Ticket ticket) {
        ticketDao.create(ticket);
    }

    @Override
    public Ticket getById(long id) {
        return ticketDao.getById(id);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketDao.getAll();
    }

    @Override
    public void update(Ticket ticket) {
        ticketDao.update(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        ticketDao.delete(ticket);
    }

    public List<Ticket> getAllTicketsByRouteId(long routeId) {
        List<Ticket> tickets = ticketDao.getAll().stream()
                                .filter(ticket -> ticket.getRouteId() == routeId)
                                .collect(Collectors.toList());
        tickets.stream().forEach((this::printTicket));
        return tickets;
    }

    public void printTicket(Ticket ticket) {
        Route route = routeDao.getById(ticket.getRouteId());
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-15s%-32s%-30s%-1s\n", " ", "CITY", "DATE", "CLASS PLACE");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-15s%-19s%-20s%-10s%-1s", "ID", "DEPARTURE", "ARRIVAL",
                "DEPARTURE", "ARRIVAL", "BUSINESS", "ECONOMY\n");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-5d%-15s%-15s%-19s%-19s%-10d%-10s%-10s%-10s%.2f\n",
                ticket.getId(),
                route.getDepartureCity().getName(),
                route.getArrivalCity().getName(),
                route.getDepartureTime(),
                route.getArrivalTime(),
//                ticket.getRoute().getDepartureCity().getName(),
//                ticket.getRoute().getArrivalCity().getName(),
//                ticket.getRoute().getDepartureTime(),
//                ticket.getRoute().getArrivalTime(),
                ticket.getPlaceNumber(),
                ticket.getTypeSeat(),
                ticket.getLocation(),
                ticket.getBaggage(),
                ticket.getPrice());
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }
}
