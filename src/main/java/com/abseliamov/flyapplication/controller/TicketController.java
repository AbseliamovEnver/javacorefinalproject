package com.abseliamov.flyapplication.controller;

import com.abseliamov.flyapplication.entity.Ticket;
import com.abseliamov.flyapplication.service.TicketService;
import com.abseliamov.flyapplication.utils.CurrentUser;

import java.util.List;

public class TicketController {
    private TicketService ticketService;
    private CurrentUser currentUser;

    public TicketController(TicketService ticketService, CurrentUser currentUser) {
        this.ticketService = ticketService;
        this.currentUser = currentUser;
    }

    public void addTicket(Ticket ticket) {
        ticketService.add(ticket);
    }

    public Ticket getTicketById(long id) {
        return ticketService.getById(id);
    }

    public List<Ticket> getAllTicket() {
        return ticketService.getAll();
    }

    public void updateTicket(Ticket ticket) {
        ticketService.update(ticket);
    }

    public void deleteTicket(Ticket ticket) {
        ticketService.delete(ticket);
    }

    public List<Ticket> getAllTicketsByRouteId(long routeId){
        return ticketService.getAllTicketsByRouteId(routeId);
    }

    public void printTicket(Ticket ticket) {
        ticketService.printTicket(ticket);
    }
}
