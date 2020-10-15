package com.dlyapkov.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView textViewCity = findViewById(R.id.textViewCity);
        MainPresenter presenter = MainPresenter.getPresenter();
        setContentView(R.layout.activity_main);
        textViewCity.setText(presenter.getTemperature());

    }

    public void choice_city(View view) {

    }
}