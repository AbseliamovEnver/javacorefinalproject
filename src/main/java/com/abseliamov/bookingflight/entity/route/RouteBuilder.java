package com.abseliamov.bookingflight.entity.route;

public class RouteBuilder {
    private long id;
    private int departureCityId;
    private int arrivalCityId;
    private String dateDeparture;
    private String dateArrival;
    private int businessClassSeat;
    private int economyClassSeat;

    public RouteBuilder() {
    }

    public RouteBuilder getId(long value) {
        id = value;
        return this;
    }

    public RouteBuilder departureCityId(int value) {
        departureCityId = value;
        return this;
    }

    public RouteBuilder arrivalCityId(int value) {
        arrivalCityId = value;
        return this;
    }

    public RouteBuilder dateDeparture(String  value) {
        dateDeparture = value;
        return this;
    }

    public RouteBuilder dateArrival(String value) {
        dateArrival = value;
        return this;
    }

    public RouteBuilder businessClassSeat(int value) {
        businessClassSeat = value;
        return this;
    }

    public RouteBuilder economyClassSeat(int value) {
        economyClassSeat = value;
        return this;
    }

    public Route build() {
        return new Route(id, departureCityId, arrivalCityId,
                dateDeparture, dateArrival, businessClassSeat, economyClassSeat);
    }
}
