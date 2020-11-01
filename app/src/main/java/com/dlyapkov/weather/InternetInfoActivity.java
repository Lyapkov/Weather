package com.dlyapkov.weather;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InternetInfoActivity extends AppCompatActivity {
    private TextView  city;
    private TextView  temperature;
    private TextView  pressure;
    private TextView  humidity;
    private TextView  windSpeed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_info);
        init();
        city.setText(MainPresenter.getRequest().getName());
        temperature.setText(String.format("%f2", MainPresenter.getRequest().getMain().getTemp()));
        pressure.setText(String.format("%d", MainPresenter.getRequest().getMain().getPressure()));
        humidity.setText(String.format("%d", MainPresenter.getRequest().getMain().getHumidity()));
        windSpeed.setText(String.format("%d", MainPresenter.getRequest().getWind().getSpeed()));
    }

    private void init() {
        city = findViewById(R.id.editTextCity);
        temperature = findViewById(R.id.textViewTemperature);
        pressure = findViewById(R.id.textViewPressure);
        humidity = findViewById(R.id.textViewHumidity);
        windSpeed = findViewById(R.id.textViewWindSpeed);
    }
}
