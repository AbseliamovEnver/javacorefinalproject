package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.dao.CityDAOImpl;
import com.abseliamov.bookingflight.entity.City;
import com.abseliamov.bookingflight.utils.CurrentUser;

import java.util.List;

public class CityService {
    private CityDAOImpl cityDAOImpl;
    private CurrentUser currentUser;
    private List<City> cities;

    public CityService(CityDAOImpl cityDAOImpl, CurrentUser currentUser) {
        this.cityDAOImpl = cityDAOImpl;
        this.currentUser = currentUser;
    }

    public City getCity(int cityId) {
        City city = null;
        cities = cityDAOImpl.getAll();
        if (!cities.isEmpty()) {
            for (City cityItem : cities) {
                if (cityItem.getId() == cityId) {
                    city = cityItem;
                    break;
                }
            }
        } else {
            System.out.println("List of cities is empty.");
        }
        return city;
    }

    public boolean getAllCity() {
        cities = cityDAOImpl.getAll();
        if (!cities.isEmpty()) {
            for (City city : cities) {
                System.out.println(city.getId() + ". " + city.getName());
            }
        } else {
            System.out.println("List of cities is empty.");
            return false;
        }
        return true;
    }
}
