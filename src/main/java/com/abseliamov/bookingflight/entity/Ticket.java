package com.abseliamov.bookingflight.entity;

public class Ticket {
    private long routeId;
    private long id;
    private int place;
    private String location;
    private TypeCabin typeCabin;
    private String baggage;
    private double price;

    public Ticket() {
    }

    public Ticket(long routeId, long id, int place, String location, TypeCabin typeCabin, String baggage, double price) {
        this.routeId = routeId;
        this.id = id;
        this.place = place;
        this.location = location;
        this.typeCabin = typeCabin;
        this.baggage = baggage;
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

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TypeCabin getTypeCabin() {
        return typeCabin;
    }

    public void setTypeCabin(TypeCabin typeCabin) {
        this.typeCabin = typeCabin;
    }

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
