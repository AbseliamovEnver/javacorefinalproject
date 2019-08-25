package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.dao.CityDAOImpl;
import com.abseliamov.bookingflight.entity.City;

import java.util.List;

public class CityService {
    private CityDAOImpl cityDAO;

    public CityService(CityDAOImpl cityDAO) {
        this.cityDAO = cityDAO;
    }

    public City getCityById(long cityId) {
        return cityDAO.getById(cityId);
    }

    public List<City> getAllCity() {
        List<City> cities = cityDAO.getAll();
        if (!cities.isEmpty()) {
            System.out.println("*********************************");
            System.out.println("ID\tCITY");
            System.out.println("*********************************");
            for (City city : cities) {
                System.out.println(city.getId() + ".\t" + city.getName());
            }
            System.out.println("*********************************");
        } else {
            System.out.println("List cities is empty.");
        }
        return cityDAO.getAll();
    }
}
