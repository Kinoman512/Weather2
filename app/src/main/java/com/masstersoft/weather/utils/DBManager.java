package com.masstersoft.weather.utils;

import com.activeandroid.query.Select;
import com.masstersoft.weather.model.City;
import com.masstersoft.weather.model.WeatherDay;

import java.util.List;

/**
 * Created by Dmitry on 07.11.2015.
 */
public class DBManager {

    public static List<City> getAllCity() {
        return new Select()
                .from(City.class)
                .orderBy("Name ASC")
                .execute();
    }

    public static WeatherDay getWeather5Day() {
        return null;
    }
}
