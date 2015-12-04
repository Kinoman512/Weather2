package com.masstersoft.weather;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.masstersoft.weather.model.CityList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        android.util.Log.d("Weather", String.valueOf(CityList.getInstance().getCities().size()));
    }



}