package com.masstersoft.weather.model;

/**
 * Created by Andrey Antonenko on 03.10.2015.
 */
public class City {
    private String cityName;
    private String cityId;
    private String coutryCode;

    public City(String name, String cityId, String countryCode){
        this.cityName = name;
        this.cityId = cityId;
        this.coutryCode = countryCode;
    }

    public String getCityName(){
        return cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCoutryCode() {
        return coutryCode;
    }
}
