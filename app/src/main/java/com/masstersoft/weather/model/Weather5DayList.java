package com.masstersoft.weather.model;

import com.activeandroid.query.Select;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Andrey Antonenko on 10.10.2015.
 */
public class Weather5DayList  implements IWeatherList{


    private City city;
    private ArrayList<WeatherDay> listWeatherDays;

    private static Weather5DayList instance;

    public static Weather5DayList getInstance(){
        if(instance == null){
            return new Weather5DayList();
        }
        else{
            return instance;
        }
    }

    private Weather5DayList(){
        this.listWeatherDays=new ArrayList<>();
    }

    public void loadDataWeather(String cityName){



        city = (City) new Select()
                .from(City.class)
                .where("CityName = ?", cityName)
                .executeSingle();
        if(city == null){
            return;
        }

        for(int i=1; i<=4;i++){
            //находим текущую дату
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();

            Long time = currentDate.getTime();
            long anotherDate = i;
            time = time + (60*60*24*1000*anotherDate);
            currentDate = new Date(time);

            sdf.setTimeZone(TimeZone.getTimeZone("GMT+4"));
            String formattedDate = sdf.format(currentDate);

            android.util.Log.d("Weather CityList ", String.valueOf(city.getCityId()));
            WeatherDay weatherDay = (WeatherDay) new Select()
                    .from(WeatherDay.class)
                    .where("DateItem = ?", formattedDate)
                    .where("CityId = ?", city.getCityId())
                    .executeSingle();


            listWeatherDays.add(weatherDay);
            if(weatherDay.isEmpty()){

            }
        }

    }

    public ArrayList<WeatherDay> getWeather(){
        return listWeatherDays;
    }
    public int getCount(){
        if(listWeatherDays!=null)
            return listWeatherDays.size();
        else
            return -1;
    }

    public City getCity() {
        return city;
    }

    @Override
    public List<WeatherDay> getWeatherDayList() {
        return listWeatherDays;
    }
}
