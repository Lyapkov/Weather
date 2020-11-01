package com.dlyapkov.weather;

public class MainPresenter {

    private final static MainPresenter presenter = new MainPresenter();

    private static WeatherRequest request;

    private MainPresenter() {
        request = null;
    }

    public void setRequest(WeatherRequest request) {
        this.request = request;
    }

    public static MainPresenter getPresenter() {
        return presenter;
    }

    public static WeatherRequest getRequest() {
        return request;
    }

}
