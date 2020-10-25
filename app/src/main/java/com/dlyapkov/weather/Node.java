package com.dlyapkov.weather;

public class Node {
    private String city;
    private String date;
    private String temperature;
    //private int image;

    public Node(String city, String date, String temperature) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
        //this.image = image;
    }

    public String getDate() {
        return date;
    }

    //public int getImage() {
    //    return image;
    //}

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public void setImage(int image) {
//        this.image = image;
//    }

}
