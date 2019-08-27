package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.dao.RouteDAOImpl;
import com.abseliamov.bookingflight.dao.TicketDAOImpl;
import com.abseliamov.bookingflight.entity.Ticket;
import com.abseliamov.bookingflight.entity.route.Route;

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

    public boolean getAllTickets() {
        return false;
    }

    public List<Ticket> getTicketByRouteId(long routeId) {
        List<Ticket> tickets = ticketDAO.getTicketByRouteId(routeId);
        if (tickets != null) {
            printTickets(tickets);
        } else {
            System.out.println("List tickets is empty.");
        }
        return tickets;
    }

    public void buyTicket(long routeID, long ticketID) {

    }

    public void orderConfirm(long routeId, long selectTicketID) {
        Route route = routeDAOImpl.getById(routeId);
        Ticket ticket = ticketDAO.getById(selectTicketID);
        routeService.printOrder(route);
        printOrder(ticket);
    }

    private void printTickets(List<Ticket> tickets) {
        System.out.println("|-----|-----|------------|-------------|---------------|------------|");
        System.out.printf("  %-5s%-8s%-12s%-17s%-16s%-1s", "ID", "PLACE", "LOCATION", "CLASS PLACE", "BAGGAGE", "PRICE\n");
        System.out.println("|-----|-----|------------|-------------|---------------|------------|");
        for (Ticket ticket : tickets) {
            System.out.printf("  %-6d%-8d%-12s%-14s%-16s%.2f\n", ticket.getId(), ticket.getPlace(),
                    ticket.getLocation(), ticket.getTypeCabin(), ticket.getBaggage(), ticket.getPrice());
            System.out.println("|-----|-----|------------|-------------|---------------|------------|");
        }
    }

    private void printOrder(Ticket ticket) {
        System.out.println("|-----|------------|-------------|---------------|------------|");
        System.out.printf(" %-8s%-12s%-17s%-16s%-1s", "PLACE", "LOCATION", "CLASS PLACE", "BAGGAGE", "PRICE\n");
        System.out.println("|-----|------------|-------------|---------------|------------|");
        System.out.printf("  %-8d%-12s%-14s%-16s%.2f\n", ticket.getPlace(),
                ticket.getLocation(), ticket.getTypeCabin(), ticket.getBaggage(), ticket.getPrice());
        System.out.println("|-----|------------|-------------|---------------|------------|\n");
    }
}
