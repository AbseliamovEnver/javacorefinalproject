package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.dao.CityDAO;
import com.abseliamov.bookingflight.entity.City;

import java.util.List;

public class CityService {
    private CityDAO cityDAO = new CityDAO();
    private List<City> cities;

    public City getCity(int cityId) {
        City city = null;
        cities = cityDAO.getAllCity();
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
        cities = cityDAO.getAllCity();
        if (!cities.isEmpty()) {
            for (City city: cities){
                System.out.println(city.getId() + ". " + city.getName());
            }
        } else {
            System.out.println("List of cities is empty.");
            return false;
        }
        return true;
    }
}
