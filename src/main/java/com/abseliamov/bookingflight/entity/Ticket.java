package com.abseliamov.bookingflight.entity;

public class Ticket {
    private long id;
    private long routeId;
    private TypeCabin typeCabin;
    private int place;
    private double price;

    public Ticket() {
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Ticket(long id, long routeId, TypeCabin typeCabin, int place, double price) {
        this.id = id;
        this.routeId = routeId;
        this.typeCabin = typeCabin;
        this.place = place;
        this.price = price;
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

    public TypeCabin getTypeCabin() {
        return typeCabin;
    }

    public void setTypeCabin(TypeCabin typeCabin) {
        this.typeCabin = typeCabin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
