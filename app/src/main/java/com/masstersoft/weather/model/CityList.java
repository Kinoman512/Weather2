package com.masstersoft.weather.model;

import java.util.ArrayList;

/**
 * Created by Andrey Antonenko on 03.10.2015.
 */
public class CityList {
    private ArrayList<City> cities;

    private static CityList instance;

    private void generateCitiesList(){
        this.cities.add(new City("Rostov-na-Donu","501175","ru"));
        this.cities.add(new City("Moskva","524894","ru"));
        this.cities.add(new City("Taganrog","484907","ru"));
    }

    private CityList(){
        this.cities = new ArrayList<>();
        generateCitiesList();
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
