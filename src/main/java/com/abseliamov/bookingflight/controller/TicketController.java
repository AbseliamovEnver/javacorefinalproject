package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.entity.Ticket;
import com.abseliamov.bookingflight.service.TicketService;
import com.abseliamov.bookingflight.utils.CurrentUser;

import java.util.List;

public class TicketController {
    private TicketService ticketService;
    private CurrentUser currentUser;

    public TicketController(TicketService ticketService, CurrentUser currentUser) {
        this.ticketService = ticketService;
        this.currentUser = currentUser;
    }

    public List<Ticket> getListTicketsByRouteId(long routeId) {
        return ticketService.getTicketByRouteId(routeId);
    }

    public void orderConfirm(long routeId, long selectTicketID) {
        ticketService.orderConfirm(routeId, selectTicketID);
    }

    public void buyTicket(long routeID, long ticketID) {
        ticketService.buyTicket(routeID, ticketID);
    }
}
