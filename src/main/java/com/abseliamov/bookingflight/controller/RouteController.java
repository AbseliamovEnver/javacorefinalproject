package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.service.RouteService;
import com.abseliamov.bookingflight.utils.CurrentUser;

public class RouteController {
    private RouteService routeService;
    private CurrentUser currentUser;

    public RouteController(RouteService routeService, CurrentUser currentUser) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    public boolean getRoutesByCity(long departureCityId, long arrivalCityId) {
        return routeService.getRoutesByCity(departureCityId, arrivalCityId) == null;
    }


    public boolean getAllRoutes() {
        return routeService.getAllRoutes();
    }
}
