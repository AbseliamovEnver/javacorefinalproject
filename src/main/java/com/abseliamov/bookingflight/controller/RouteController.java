package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.service.RouteService;

public class RouteController {
    private RouteService routeService = new RouteService();

    public boolean getRoutesByCity(long departureCityId, long arrivalCityId){
        return routeService.getRoutesByCity(departureCityId, arrivalCityId) == null;
    }



    public boolean getAllRoutes(){
        return routeService.getAllRoutes();
    }
}
