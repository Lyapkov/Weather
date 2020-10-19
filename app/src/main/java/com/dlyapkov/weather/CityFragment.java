package com.dlyapkov.weather;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static com.dlyapkov.weather.WeatherInfoFragment.PARCEL;


public class CityFragment extends Fragment {

    boolean isExistWeather;
    Parcel currentParcel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.fragment_city, container, false));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isExistWeather = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentParcel = (Parcel) savedInstanceState.getSerializable("CurrentCity");
        } else {
            currentParcel = new Parcel(0, getResources().getStringArray(R.array.cities)[0], getResources().getIntArray(R.array.temperature)[0], getResources().getStringArray(R.array.dates)[0]);
        }

        if (isExistWeather) {
            showWeatherInfo(currentParcel);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("CurrentCity", currentParcel);
        super.onSaveInstanceState(outState);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentParcel = new Parcel(fi, getResources().getStringArray(R.array.cities)[fi], getResources().getIntArray(R.array.temperature)[fi], getResources().getStringArray(R.array.dates)[fi]);
                    showWeatherInfo(currentParcel);
                }
            });
        }
    }

    private void showWeatherInfo(Parcel parcel) {
        if (isExistWeather) {
            WeatherInfoFragment detail = (WeatherInfoFragment) getFragmentManager().findFragmentById(R.id.weather);
            if (detail == null || detail.getParcel().getImageIndex() != parcel.getImageIndex()) {
                detail = WeatherInfoFragment.create(parcel);
                FragmentTransaction ft = getFragmentManager().beginTransaction()
                        .replace(R.id.weather, detail)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), CityActivity.class);
            intent.putExtra(PARCEL, parcel);
            startActivity(intent);
        }
    }
}