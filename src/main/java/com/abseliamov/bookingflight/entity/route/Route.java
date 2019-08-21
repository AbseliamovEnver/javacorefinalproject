package com.abseliamov.bookingflight.entity.route;

import java.util.Date;

public class Route {
    private long id;
    private long departureCityId;
    private long arrivalCityId;
    private Date dateDeparture;
    private Date dateArrival;
    private int businessClassSeat;
    private int economyClassSeat;

    public Route(long id, long departureCityId, long arrivalCityId,
                 Date dateDeparture, Date dateArrival, int businessClassSeat, int economyClassSeat) {
        this.id = id;
        this.departureCityId = departureCityId;
        this.arrivalCityId = arrivalCityId;
        this.dateDeparture = dateDeparture;
        this.dateArrival = dateArrival;
        this.businessClassSeat = businessClassSeat;
        this.economyClassSeat = economyClassSeat;
    }

    public long getId() {
        return id;
    }

    public long getDepartureCityId() {
        return departureCityId;
    }

    public long getArrivalCityId() {
        return arrivalCityId;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public int getBusinessClassSeat() {
        return businessClassSeat;
    }

    public int getEconomyClassSeat() {
        return economyClassSeat;
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
        if (dateDeparture != null ? !dateDeparture.equals(route.dateDeparture) : route.dateDeparture != null)
            return false;
        return dateArrival != null ? dateArrival.equals(route.dateArrival) : route.dateArrival == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (departureCityId ^ (departureCityId >>> 32));
        result = 31 * result + (int) (arrivalCityId ^ (arrivalCityId >>> 32));
        result = 31 * result + (dateDeparture != null ? dateDeparture.hashCode() : 0);
        result = 31 * result + (dateArrival != null ? dateArrival.hashCode() : 0);
        result = 31 * result + businessClassSeat;
        result = 31 * result + economyClassSeat;
        return result;
    }

    @Override
    public String toString() {
        return "Route:\n" +
                "id: " + id +
                ", departureCityId: " + departureCityId +
                ", arrivalCityId: " + arrivalCityId +
                ", dateDeparture: " + dateDeparture +
                ", dateArrival: " + dateArrival +
                ", businessClassSeat: " + businessClassSeat +
                ", economyClassSeat: " + economyClassSeat;
    }
}
