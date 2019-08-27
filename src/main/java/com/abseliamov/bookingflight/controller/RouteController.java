package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.entity.TypeCabin;
import com.abseliamov.bookingflight.entity.route.Route;
import com.abseliamov.bookingflight.service.RouteService;
import com.abseliamov.bookingflight.utils.CurrentUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RouteController {
    private RouteService routeService;
    private CurrentUser currentUser;

    public RouteController(RouteService routeService, CurrentUser currentUser) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    public List<Route> getRoutesByRequest(long departureCityId, long arrivalCityId, LocalDate dateDeparture,
                                   TypeCabin typeCabin, int numberPassengers) {
        List<Route> routes = null;
        LocalDateTime timeDeparture;
        if (dateDeparture.equals(LocalDate.now())) {
            timeDeparture = LocalDateTime.now().plusHours(1);
        } else {
            timeDeparture = dateDeparture.atStartOfDay();
        }
        routes = routeService.getRouteByRequest(departureCityId, arrivalCityId,
                timeDeparture, typeCabin, numberPassengers);
        return routes;
    }

    public void reduceSeat(long routeId, long ticketId){
        routeService.reduceSeat(routeId, ticketId);
    }
}
