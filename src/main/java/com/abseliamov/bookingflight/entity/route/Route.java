package com.abseliamov.bookingflight.entity.route;

import java.time.LocalDateTime;

public class Route {
    private long id;
    private long departureCityId;
    private long arrivalCityId;
    private LocalDateTime timeDeparture;
    private LocalDateTime timeArrival;
    private int businessClassSeat;
    private int economyClassSeat;

    public Route(long id, long departureCityId, long arrivalCityId, LocalDateTime timeDeparture,
                 LocalDateTime timeArrival, int businessClassSeat, int economyClassSeat) {
        this.id = id;
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.businessClassSeat = businessClassSeat;
        this.economyClassSeat = economyClassSeat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(long departureCityId) {
        this.departureCityId = departureCityId;
    }

    public long getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(long arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public LocalDateTime getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(LocalDateTime timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public LocalDateTime getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(LocalDateTime timeArrival) {
        this.timeArrival = timeArrival;
    }

    public int getBusinessClassSeat() {
        return businessClassSeat;
    }

    public void setBusinessClassSeat(int businessClassSeat) {
        this.businessClassSeat = businessClassSeat;
    }

    public int getEconomyClassSeat() {
        return economyClassSeat;
    }

    public void setEconomyClassSeat(int economyClassSeat) {
        this.economyClassSeat = economyClassSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (id != route.id) return false;
        if (departureCityId != route.departureCityId) return false;
        if (arrivalCityId != route.arrivalCityId) return false;
        if (businessClassSeat != route.businessClassSeat) return false;
        if (economyClassSeat != route.economyClassSeat) return false;
        if (timeDeparture != null ? !timeDeparture.equals(route.timeDeparture) : route.timeDeparture != null)
            return false;
        return timeArrival != null ? timeArrival.equals(route.timeArrival) : route.timeArrival == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (departureCityId ^ (departureCityId >>> 32));
        result = 31 * result + (int) (arrivalCityId ^ (arrivalCityId >>> 32));
        result = 31 * result + (timeDeparture != null ? timeDeparture.hashCode() : 0);
        result = 31 * result + (timeArrival != null ? timeArrival.hashCode() : 0);
        result = 31 * result + businessClassSeat;
        result = 31 * result + economyClassSeat;
        return result;
    }
}
