package com.masstersoft.weather.model;

import java.util.List;

/**
 * Created by Dmitry on 20.11.2015.
 */
public interface IWeatherList {
    City getCity();
    List<WeatherDay> getWeatherDayList();
}
