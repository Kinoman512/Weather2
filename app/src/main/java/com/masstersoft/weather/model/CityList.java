package com.masstersoft.weather.model;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Antonenko on 03.10.2015.
 */
public class CityList {
    private ArrayList<City> cities;

    private static CityList instance;


    private CityList(){
        cities =(ArrayList) new Select()
                .from(City.class)
                .execute();
    }

    public static CityList getInstance(){
        if(instance == null){
            return new CityList();
        }
        else{
            return instance;
        }
    }

    public ArrayList<City> getCities(){
        return cities;
    }


    public int getCount(){
        return this.cities.size();
    }
}
