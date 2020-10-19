package com.dlyapkov.weather;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class WeatherInfoFragment extends Fragment {
    public static final String PARCEL = "parcel";

    public static WeatherInfoFragment create(Parcel parcel) {
        WeatherInfoFragment f = new WeatherInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        f.setArguments(args);
        return f;
    }

    public Parcel getParcel() {
        Parcel parcel = (Parcel) getArguments().getSerializable(PARCEL);
        return parcel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather_info, container, false);
        ImageView imageWeather = layout.findViewById(R.id.imageView);
        TextView cityNameView = layout.findViewById(R.id.cityText);
        TextView dateView = layout.findViewById(R.id.dateText);
        TextView temperatureView = layout.findViewById(R.id.temperatureText);

        TypedArray imgs = getResources().obtainTypedArray(R.array.images);
        Parcel parcel = getParcel();

        imageWeather.setImageResource(imgs.getResourceId(parcel.getImageIndex(), -1));

        dateView.setText(parcel.getDate());
        temperatureView.setText(String.valueOf(parcel.getTemperature()));
        cityNameView.setText(parcel.getCityName());
        return layout;
    }
}