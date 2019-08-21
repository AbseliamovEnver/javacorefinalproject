package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.service.CityService;

public class CityController {
    private CityService cityService = new CityService();

    public void getCity(int cityId) {
        cityService.getCity(cityId);
    }

    public boolean getAllCity() {
        return cityService.getAllCity();
    }
}
