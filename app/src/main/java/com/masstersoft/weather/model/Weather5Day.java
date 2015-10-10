package com.masstersoft.weather.model;

/**
 * Created by Andrey Antonenko on 10.10.2015.
 */
public class Weather5Day {
    private String dateItem;
    private float tempMin;
    private float tempMax;
    private int humidity;
    private String weather;//ясно облачно пасмурно....
    private float windSpeed;

    public Weather5Day(String dateItem, float tempMin, float tempMax, int humidity, String weather, float windSpeed) {
        this.dateItem = dateItem;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
        this.weather = weather;
        this.windSpeed = windSpeed;
    }

    public String getDateItem() {
        return dateItem;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getWeather() {
        return weather;
    }

    public float getWindSpeed() {
        return windSpeed;
    }
}
