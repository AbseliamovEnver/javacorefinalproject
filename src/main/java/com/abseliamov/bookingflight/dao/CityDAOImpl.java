package com.abseliamov.bookingflight.dao;

import com.abseliamov.bookingflight.entity.City;
import com.abseliamov.bookingflight.utils.IOUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements GeneralDaoInterface<City> {
    private File file = IOUtil.getFile("file.cities");
    private final String CITIES_FILE_HEADER = "Id, cityName";
    private final String COMMA_SEPARATOR = ",";
    private List<City> citiesFromFile = new ArrayList<>();

    @Override
    public void create(City city) {
        boolean cityExist = false;
        long cityID = 0;
        List<City> cities = readFromFile(file);
        if (cities.size() == 0) {
            cities.add(new City(cityID, city.getName()));
            writeToFile(cities);
            cityExist = true;
        } else {
            for (City cityItem : cities) {
                if (cityItem.equals(city)) {
                    System.out.println("Such city already exists.");
                    cityExist = true;
                    break;
                }
            }
        }
        if (!cityExist) {
            long newId = cities.get(cities.size() - 1).getId() + 1;
            cities.add(new City(newId, city.getName()));
            writeToFile(cities);
        }
    }

    @Override
    public City getById(long id) {
        return getAll().stream()
                .filter(city -> city.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<City> getAll() {
       return readFromFile(file);
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void delete(City city) {
    }

    private List<City> readFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = reader.readLine()) != null) {
                if (data.equals(CITIES_FILE_HEADER)) {
                    continue;
                }
                String[] cityData = data.split(",");
                citiesFromFile.add(new City(Long.parseLong(cityData[0]), cityData[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found " + e);
        } catch (IOException e) {
            System.out.println("Error read from file " + file.getName() + " " + e);
        }
        return citiesFromFile;
    }

    private void writeToFile(List<City> cities) {
        try (FileWriter writer = new FileWriter(file)) {
            StringBuilder builder = new StringBuilder();

            builder.append(CITIES_FILE_HEADER);
            for (City cityItem : cities) {
                builder.append("\n");
                builder.append(cityItem.getId());
                builder.append(COMMA_SEPARATOR);
                builder.append(cityItem.getName());
            }
            writer.write(builder.toString());
        } catch (
                IOException e) {
            System.out.println("Error write to file " + file.getName() + " " + e);
        }
    }
}
