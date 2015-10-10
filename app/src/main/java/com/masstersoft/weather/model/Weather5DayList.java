package com.masstersoft.weather.model;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Andrey Antonenko on 10.10.2015.
 */
public class Weather5DayList {
    private ArrayList<Weather5Day> weather;

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
        this.weather=new ArrayList<>();
    }

    public void loadDataWeather(JSONObject jsonObject){

    }

    public ArrayList<Weather5Day> getWeather(){
        return weather;
    }
    public int getCount(){
        if(weather!=null)
            return weather.size();
        else
            return -1;
    }
}
