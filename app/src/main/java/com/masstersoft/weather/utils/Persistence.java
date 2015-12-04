package com.masstersoft.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dmitry on 27.11.2015.
 */
public class Persistence {

    private static final String PREFERENCES = "MySetting";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private Context context;
    private static Persistence ins;


    public Persistence(Context context)
    {
        this.context = context;
        settings = context.getSharedPreferences(PREFERENCES, 0);
        editor = settings.edit();
        ins = this;
    }

    public static  Persistence getIns(){
        return ins;
    }


    public int getMode()
    {
        return settings.getInt("mode", 0);
    }



    public void setMode(int m)
    {
        editor.putInt("mode", m);
        editor.commit();
    }


}