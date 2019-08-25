package com.abseliamov.bookingflight.entity;

public class City {
    private long Id;
    private String name;

    public City(long id, String name) {
        Id = id;
        this.name = name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (Id != city.Id) return false;
        return name != null ? name.equals(city.name) : city.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (Id ^ (Id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
