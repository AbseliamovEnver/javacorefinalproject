package com.abseliamov.flyapplication.controller;

import com.abseliamov.flyapplication.utils.CurrentUser;
import com.abseliamov.flyapplication.entity.City;
import com.abseliamov.flyapplication.service.CityService;

public class CityController {
    private CityService cityService;
    private CurrentUser currentUser;

    public CityController(CityService cityService, CurrentUser currentUser) {
        this.cityService = cityService;
        this.currentUser = currentUser;
    }

    public void addCity(City city) {
        cityService.add(city);
    }

    public String getCityById(long id) {
        return cityService.getById(id).getName();
    }

    public boolean getAllCity() {
        return cityService.getAll() != null;
    }

    public void updateCity(City city) {
        cityService.update(city);
    }

    public void deleteCity(City city) {
        cityService.delete(city);
    }
}
