package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.City;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    private static BufferedReader reader;

    public List<City> getAllCity() {
        List<City> cities = new ArrayList<>();
        String data;
        try {
            while ((data = getReader().readLine()) != null) {
                String[] cityData = data.split(",");
                cities.add(new City(Long.parseLong(cityData[0]), cityData[1]));
            }
        } catch (IOException e) {
            System.out.println("Error reading file with cities " + e);
        }
        return cities;
    }

    private static BufferedReader getReader() {
        try {
            if (reader == null) {
                reader = new BufferedReader(new FileReader(IOUtil.getFile("file.cities")));
            } else return reader;
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file cities.csv " + e);
        }
        return reader;
    }
}
