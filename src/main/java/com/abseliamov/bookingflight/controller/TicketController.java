package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.entity.Ticket;
import com.abseliamov.bookingflight.service.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Ticket getTicketByRouteId(long routeId){
        return ticketService.getTicketByRouteId(routeId);
    }
}
