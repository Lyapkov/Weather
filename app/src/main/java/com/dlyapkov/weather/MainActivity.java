package com.dlyapkov.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static com.dlyapkov.weather.CityActivity.KEY;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textViewTemperature = findViewById(R.id.textViewTemperature);
        final TextView textViewCity = findViewById(R.id.textViewCity);
        final Button buttonInfo = findViewById(R.id.buttonInfo);
        MainPresenter presenter = MainPresenter.getPresenter();
        final String city = (String) getIntent().getStringExtra(KEY);
        textViewCity.setText(city);
        textViewTemperature.setText(String.valueOf(presenter.getTemperature()));

        buttonInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://yandex.ru/pogoda/" + String.valueOf(textViewCity.getText());
                        Uri uri = Uri.parse(url);
                        Intent intentInfo = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intentInfo);
                    }
                }
        );

    }

    public void choice_city(View view) {

    }
}