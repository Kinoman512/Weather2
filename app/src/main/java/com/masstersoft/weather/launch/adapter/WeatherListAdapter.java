package com.masstersoft.weather.launch.adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.masstersoft.weather.R;
import com.masstersoft.weather.launch.MenuCityFragment;
import com.masstersoft.weather.model.City;
import com.masstersoft.weather.model.IWeatherList;

import java.util.ArrayList;
import java.util.List;

import static com.masstersoft.weather.launch.MenuCityFragment.*;

/**
 * Created by Dmitry on 16.11.2015.
 */
public class WeatherListAdapter extends BaseAdapter {

    IWeatherList wl;
    Context context;

    public WeatherListAdapter(Context context,IWeatherList list) {
        if (list != null) {
            wl = list;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return  wl.getWeatherDayList().size();
    }

    public Object getItem(int num) {
        // TODO Auto-generated method stub
        return wl.getWeatherDayList().get(num);
    }
    @Override


    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int i, View someView, ViewGroup arg2) {
        //Получение объекта inflater из контекста
        LayoutInflater inflater = LayoutInflater.from(context);
        //Если someView (View из ListView) вдруг оказался равен
        //null тогда мы загружаем его с помошью inflater
        if (someView == null) {
            someView = inflater.inflate(R.layout.list_item_weather, arg2, false);
        }
        //Обявляем наши текствьюшки и связываем их с разметкой
        TextView dataItemText = (TextView) someView.findViewById(R.id.dataItem);
        TextView tempMaxText = (TextView) someView.findViewById(R.id.tempMax);
        TextView tempMinText = (TextView) someView.findViewById(R.id.tempMin);
        TextView hymidityText = (TextView) someView.findViewById(R.id.hymidity);
        TextView weatherSpeed = (TextView) someView.findViewById(R.id.weatherSpeed);



        ImageView image = (ImageView)someView.findViewById(R.id.icon_weather);
        Glide.with(context)
                .load("http://openweathermap.org/img/w/10n.png")
                .into(image);

//        final String city = header.getText().toString();

//            header.setOnClickListener(  MenuCityFragment.OnClick ());


        dataItemText.setText(wl.getWeatherDayList().get(i).getDateItem());
        tempMaxText.setText( String.valueOf( wl.getWeatherDayList().get(i).getTempMax()));
        tempMinText.setText(String.valueOf(  wl.getWeatherDayList().get(i).getTempMin()));
        hymidityText.setText(String.valueOf(wl.getWeatherDayList().get(i).getHumidity()));
        weatherSpeed.setText(String.valueOf(  wl.getWeatherDayList().get(i).getWindSpeed()));

        return someView;
    }

}

