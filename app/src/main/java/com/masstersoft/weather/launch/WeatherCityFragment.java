package com.masstersoft.weather.launch;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.masstersoft.weather.R;
import com.masstersoft.weather.launch.adapter.CityListAdapter;
import com.masstersoft.weather.launch.adapter.WeatherListAdapter;
import com.masstersoft.weather.model.CityList;
import com.masstersoft.weather.model.IWeatherList;

/**
 * Created by Dmitry on 20.11.2015.
 */
public class WeatherCityFragment extends Fragment {

    private Context context;
    ListView lw;
    IWeatherList wl;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = inflater.getContext();

        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);

//        editText1 = (EditText) rootView.findViewById(R.id.editText1);
//
        TextView cityNameView = (TextView) rootView.findViewById(R.id.CityName);
        cityNameView.setText(wl.getCity().getCityName());

        lw = (ListView) rootView.findViewById(R.id.listView2);
        lw.setAdapter(new WeatherListAdapter(context, wl));

        return rootView;
    }

    public WeatherCityFragment(IWeatherList arg){
        wl = arg;
    }



}
