package com.abseliamov.bookingflight.entity;

public class Order {
    private long id;
    private long routeID;
    private long ticketID;

    public Order(long id, long routeID, long ticketID) {
        this.id = id;
        this.routeID = routeID;
        this.ticketID = ticketID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRouteID() {
        return routeID;
    }

    public void setRouteID(long routeID) {
        this.routeID = routeID;
    }

    public long getTicketID() {
        return ticketID;
    }

    public void setTicketID(long ticketID) {
        this.ticketID = ticketID;
    }
}
