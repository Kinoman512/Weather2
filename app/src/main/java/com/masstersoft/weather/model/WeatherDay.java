package com.masstersoft.weather.model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Table;
import com.activeandroid.annotation.Column;

/**
 * Created by Andrey Antonenko on 10.10.2015.
 */
@Table(name = "WeatherDay")
public class WeatherDay extends Model {

    @Column(name = "DateItem")
    private String dateItem;

    @Column(name = "CityId")
    private String cityId;

    @Column(name = "TempMin")
    private float tempMin;

    @Column(name = "TempMax")
    private float tempMax;

    @Column(name = "Humidity")
    private int humidity;

    @Column(name = "Weather")
    private int weather;//ясно облачно пасмурно....

    @Column(name = "WindSpeed")
    private float windSpeed;

    public WeatherDay(){super();};

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setDateItem(String dateItem) {
        this.dateItem = dateItem;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public WeatherDay(String dateItem, String cityId, float tempMin, float tempMax, int humidity, int weather, float windSpeed) {
        super();
        this.dateItem = dateItem;
        this.cityId = cityId;
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

    public int getWeather() {
        return weather;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public boolean isEmpty(){
        if(dateItem.isEmpty()) return true;
        if(cityId.isEmpty()) return true;

        return false;
    }
}
