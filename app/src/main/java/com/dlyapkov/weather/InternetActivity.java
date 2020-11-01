package com.dlyapkov.weather;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class InternetActivity extends AppCompatActivity {
    private static final String TAG = "WEATHER";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    private EditText city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inernet);
        init();
        city.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    try {
                        final URL uri = new URL(String.format(WEATHER_URL, city.getText(), BuildConfig.WEATHER_API_KEY));
                        final Handler handler = new Handler();
                        new Thread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {
                                HttpsURLConnection urlConnection = null;
                                try {
                                    urlConnection = (HttpsURLConnection) uri.openConnection();
                                    urlConnection.setRequestMethod("GET");
                                    urlConnection.setConnectTimeout(10000);
                                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                                    String result = getLines(in);
                                    Gson gson = new Gson();
                                    final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            displayWeather(weatherRequest);
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    if (null != urlConnection) {
                                        urlConnection.disconnect();
                                    }
                                }
                            }
                        }).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }

    private void init() {
        city = findViewById(R.id.editTextCity);
    }


    View.OnKeyListener keyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                try {
                    final URL uri = new URL(String.format(WEATHER_URL, city.getText(), BuildConfig.WEATHER_API_KEY));
                    final Handler handler = new Handler();
                    new Thread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            HttpsURLConnection urlConnection = null;
                            try {
                                urlConnection = (HttpsURLConnection) uri.openConnection();
                                urlConnection.setRequestMethod("GET");
                                urlConnection.setConnectTimeout(10000);
                                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                                String result = getLines(in);
                                Gson gson = new Gson();
                                final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        displayWeather(weatherRequest);
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (null != urlConnection) {
                                    urlConnection.disconnect();
                                }
                            }
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    private void displayWeather(WeatherRequest weatherRequest) {
        MainPresenter.getPresenter().setRequest(weatherRequest);
        Intent intent = new Intent(this, InternetInfoActivity.class);
        startActivity(intent);
    }
}