package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.entity.TypeCabin;
import com.abseliamov.bookingflight.service.RouteService;
import com.abseliamov.bookingflight.utils.CurrentUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class RouteController {
    private RouteService routeService;
    private CurrentUser currentUser;

    public RouteController(RouteService routeService, CurrentUser currentUser) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    public void getRoutesByRequest(long departureCityId, long arrivalCityId, LocalDate dateDeparture,
                                   TypeCabin typeCabin, int numberPassengers) {
        LocalDateTime timeDeparture;
        if (dateDeparture.equals(LocalDate.now())) {
            timeDeparture = LocalDateTime.now().plusHours(1);
        } else {
            timeDeparture = dateDeparture.atStartOfDay();
        }
        routeService.getRouteByRequest(departureCityId, arrivalCityId,
                timeDeparture, typeCabin, numberPassengers);
    }

    public boolean getAllRoutes() {
        return routeService.getAllRoutes();
    }

    public void getAllCity() {
        routeService.getAllRoutes();
    }

    public boolean getAllDate() {
        return routeService.getAllDate();
    }
}
