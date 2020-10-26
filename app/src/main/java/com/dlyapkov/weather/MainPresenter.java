package com.dlyapkov.weather;

public class MainPresenter {

    private final static MainPresenter presenter = new MainPresenter();

    private int temperature;

    private MainPresenter() {
        temperature = 20;
    }

    public int getTemperature() {
        return temperature;
    }

    public static MainPresenter getPresenter() {
        return presenter;
    }

}
