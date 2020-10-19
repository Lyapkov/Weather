package com.dlyapkov.weather;

import java.io.Serializable;

public class Parcel implements Serializable {
    private int imageIndex;
    private String cityName;
    private int temperature;
    private String date;

    public Parcel(int imageIndex, String cityName, int temperature, String date) {
        this.cityName = cityName;
        this.date = date;
        this.imageIndex = imageIndex;
        this.temperature = temperature;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDate() {
        return date;
    }
}
