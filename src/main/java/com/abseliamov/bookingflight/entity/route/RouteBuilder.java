package com.abseliamov.bookingflight.entity.route;

import java.time.LocalDateTime;

public class RouteBuilder {
    private long id;
    private long departureCityId;
    private long arrivalCityId;
    private LocalDateTime dateDeparture;
    private LocalDateTime dateArrival;
    private int businessClassSeat;
    private int economyClassSeat;

    public RouteBuilder() {
    }

    public RouteBuilder getId(long value) {
        id = value;
        return this;
    }

    public RouteBuilder departureCityId(long value) {
        departureCityId = value;
        return this;
    }

    public RouteBuilder arrivalCityId(long value) {
        arrivalCityId = value;
        return this;
    }

    public RouteBuilder dateDeparture(LocalDateTime value) {
        dateDeparture = value;
        return this;
    }

    public RouteBuilder dateArrival(LocalDateTime value) {
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
