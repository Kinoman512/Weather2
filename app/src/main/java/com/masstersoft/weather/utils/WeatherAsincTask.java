package com.masstersoft.weather.utils;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Dmitry on 10.10.2015.
 */
public class WeatherAsincTask extends AsyncTask<String, Void, API.ApiResponse> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected API.ApiResponse doInBackground(String... x) {
        ArrayList<String> params = new ArrayList<String>();
        params.add(x[0]);params.add(x[1]);
        params.add("APPID");params.add("58c3cdec0969373fd82d01a13c7de5bc");
        params.add("lang");params.add(x[2]);
        params.add("units");params.add(x[3]);

        android.util.Log.e("Weather", params.toString());

        return API.execute(API.ApiMethod.GET_WEATHER.format(), API.HttpMethod.GET, params.toArray(new String[params.size()]));
    }

    @Override
    protected void onPostExecute(API.ApiResponse apiResponse) {
        super.onPostExecute(apiResponse);
        try {
            if (apiResponse.isSuccess()) {
                android.util.Log.d("Weather Asinc Task ", apiResponse.getJson().getJSONArray("list")
                        .getJSONObject(0)
                        .getJSONObject("main")
                        .get("temp_max")
                        .toString());

            }

        } catch (Exception e) {
            android.util.Log.e("Weather", "ALERT! ALERT! Exception!", e);
        } finally {

        }
    }
}