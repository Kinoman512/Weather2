package com.masstersoft.weather.model;

import android.content.ClipData;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.masstersoft.weather.model.City;
import com.masstersoft.weather.model.WeatherDay;
import com.masstersoft.weather.utils.API;
import com.masstersoft.weather.utils.WeatherAsincTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Dmitry on 15.11.2015.
 */
public class ParserWeatherJSON  implements  IWeatherList{

    private API.ApiResponse weatherObj;
    private City city;
    private List<WeatherDay> listWeatherDays = new ArrayList<>();



    public  ParserWeatherJSON(String city ) {
        String[] array = {"q", city, "ru", "metric"};
        getWeather(array);
    }

    public  ParserWeatherJSON(int id ) {
        String[] array = {"id", String.valueOf(id), "ru", "metric"};
        getWeather(array);
    }

    private void getWeather(String[] array ) {

        WeatherAsincTask task = new WeatherAsincTask();
        task.execute(array);
        try {
            weatherObj = task.get(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();

        }
        if(weatherObj.getJson() == null){
            city = null;
            listWeatherDays = null;
            android.util.Log.d("Parser Weather Json ","Нет интернета");

            return;
        }

//        android.util.Log.d("Parser Weather Json ", weatherObj.getJson().toString());
        parseJson();
    }

    private void parseJson() {

        try {
            String id = weatherObj.getJson()
                .getJSONObject("city")
                .getString("id");
            String name = weatherObj.getJson()
                    .getJSONObject("city")
                    .getString("name");
            String lang = weatherObj.getJson()
                    .getJSONObject("city")
                    .getString("country");

            city = new City();
            city.setCityId(id);
            city.setCityName(name);
            city.setCoutryCode(lang);



            JSONArray list = weatherObj.getJson().getJSONArray("list");
            int length = weatherObj.getJson().getJSONArray("list").length();
            android.util.Log.d("Weather Parser list", String.valueOf(length));

            String varDate = "";
            int count = 0;

            float avgMinTemp = 0;
            float avgTempMax = 0;
            int avgWeather = -1;
            float avgWindSpeed = 0;
            int avgHumadity = 0;

            for(int i =0; i< length; i++){

                long unixSeconds = list.getJSONObject(i).getLong("dt");
                Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+4"));
                String formattedDate = sdf.format(date);

                count++;

                avgMinTemp += list.getJSONObject(i)
                        .getJSONObject("main")
                        .getLong("temp_min");
                avgTempMax += list.getJSONObject(i)
                        .getJSONObject("main")
                        .getLong("temp_max");
                avgWeather = list.getJSONObject(i)
                        .getJSONArray("weather")
                        .getJSONObject(0)
                        .getInt("id");
                avgWindSpeed += list.getJSONObject(i)
                        .getJSONObject("wind")
                        .getLong("speed");
                avgHumadity += list.getJSONObject(i)
                        .getJSONObject("main")
                        .getLong("humidity");

//                android.util.Log.d("Weather Date " + i,avgTempMax + " " +count + " " + avgMinTemp);
                if( !varDate.equals(formattedDate)){
                    varDate = formattedDate;

                    avgMinTemp/= count;
                    avgTempMax/= count;
                    avgWindSpeed/= count;
                    avgHumadity /= count;
                    WeatherDay wd = new WeatherDay(varDate, city.getCityId(), avgMinTemp, avgTempMax, avgHumadity,  avgWeather, avgWindSpeed);
                    listWeatherDays.add(wd);

                    avgMinTemp = 0;
                    avgTempMax = 0;
                    avgWeather = -1;
                    avgWindSpeed = 0;
                    avgHumadity = 0;
                    count = 0;

                    android.util.Log.d("Weather Date " , wd.toString() );
                    android.util.Log.d("Weather Date " + i,wd.getDateItem() + " " + wd.getTempMax() + " " + wd.getWeather());
                }
                saveObjToBD();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public  void saveObjToBD(){
        ActiveAndroid.beginTransaction();
        try {
            int j = new Select()
                    .from(City.class)
                    .where("CityId = ?", city.getCityId())
                    .execute().size();

            if(j == 0){
                city.save();
            }
            for(WeatherDay w : listWeatherDays) {
                 j = new Select()
                        .from(WeatherDay.class)
                         .where("DateItem = ?", w.getDateItem())
                         .where("CityId = ?", city.getCityId())
                         .execute().size();

                if(j == 0){
                    w.save();
                }
                //w.save();
                //разобраться с сохранением
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    };

    @Override
    public City getCity() {
        return city;
    }

    @Override
    public List<WeatherDay> getWeatherDayList() {
        return listWeatherDays;
    }
}
