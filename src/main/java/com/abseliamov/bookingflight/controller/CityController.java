package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.service.CityService;
import com.abseliamov.bookingflight.utils.CurrentUser;

public class CityController {
    private CityService cityService;
    private CurrentUser currentUser;

    public CityController(CityService cityService, CurrentUser currentUser) {
        this.cityService = cityService;
        this.currentUser = currentUser;
    }

    public void getCity(long cityId) {
        cityService.getCityById(cityId);
    }

    public boolean getAllCity() {
        return cityService.getAllCity() != null;
    }
}
