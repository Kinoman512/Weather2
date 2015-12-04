package com.masstersoft.weather.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Andrey Antonenko on 03.10.2015.
 */
@Table(name = "City")
public class City extends Model {

    @Column(name = "CityName")
    private String cityName;

    @Column(name = "CityId",unique = true)
    private String cityId;

    @Column(name = "CoutryCode")
    private String coutryCode;

    public City(){super();};
    public City(String name, String cityId, String countryCode){
        super();
        this.cityName = name;
        this.cityId = cityId;
        this.coutryCode = countryCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setCoutryCode(String coutryCode) {
        this.coutryCode = coutryCode;
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
