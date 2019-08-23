package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.City;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements GeneralDaoInterface<City> {
    IODaoUtil daoUtil = new IODaoUtil();

    @Override
    public void create(City city) {

    }

    @Override
    public City getById() {
        return null;
    }

    @Override
    public List<City> getAll() {
        List<City> cities = new ArrayList<>();
        String data;
        try {
            while ((data = daoUtil.getReader("file.cities").readLine()) != null) {
                String[] cityData = data.split(",");
                cities.add(new City(Long.parseLong(cityData[0]), cityData[1]));
            }
        } catch (IOException e) {
            System.out.println("Error reading file with cities " + e);
        }
        return cities;
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void delete(City city) {

    }
}
