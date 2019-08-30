package com.abseliamov.flyapplication.entity;

import java.io.Serializable;

public class Order extends GenerateID implements Serializable {
    private long id;
    private long routeId;
    private long ticketId;

    public Order(long id, long routeId, long ticketId) {
        this.id = id;
        this.routeId = routeId;
        this.ticketId = ticketId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
}
