package com.abseliamov.flyapplication.entity;

import java.time.LocalDateTime;

public class Route extends GenerateID {
    private long id;
    private City departureCity;
    private City arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
//    private TypeSeat businessClass;
//    private TypeSeat economyClass;
    private int businessClassSeatCount;
    private int economyClassSeatCount;

    private Route(){
    }

    public long getId() {
        return id;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

//    public TypeSeat getBusinessClass() {
//        return businessClass;
//    }
//
//    public TypeSeat getEconomyClass() {
//        return economyClass;
//    }

    public int getBusinessClassSeatCount() {
        return businessClassSeatCount;
    }

    public int getEconomyClassSeatCount() {
        return economyClassSeatCount;
    }

    public static RouteBuilder newBuilder(){
        return new Route().new RouteBuilder();
    }

    public class RouteBuilder {
        private RouteBuilder() {
        }

        public RouteBuilder setId(long routeId) {
            Route.this.id = routeId;
            return this;
        }

        public RouteBuilder setDepartureCity(City departureCity) {
            Route.this.departureCity = departureCity;
            return this;
        }

        public RouteBuilder setArrivalCity(City arrivalCity) {
            Route.this.arrivalCity = arrivalCity;
            return this;
        }

        public RouteBuilder setDepartureTime(LocalDateTime departureTime) {
            Route.this.departureTime = departureTime;
            return this;
        }

        public RouteBuilder setArrivalTime(LocalDateTime arrivalTime) {
            Route.this.arrivalTime = arrivalTime;
            return this;
        }

//        public RouteBuilder setBusinessClass(TypeSeat businessClass) {
//            Route.this.businessClass = businessClass;
//            return this;
//        }
//
//        public RouteBuilder setEconomyClass(TypeSeat economyClass) {
//            Route.this.economyClass = economyClass;
//            return this;
//        }

        public RouteBuilder setNumberBusinessClassSeat(int numberBusinessClassSeat){
            Route.this.businessClassSeatCount = numberBusinessClassSeat;
            return this;
        }

        public RouteBuilder setNumberEconomyClassSeat(int numberEconomyClassSeat){
            Route.this.economyClassSeatCount = numberEconomyClassSeat;
            return this;
        }

        public Route build() {
            return Route.this;
        }
    }
}
