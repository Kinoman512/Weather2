
package com.masstersoft.weather.launch;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

public class MyApplication extends Application {
    public static final String TAG = "VIVZ";

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        // Notice this initialization code here
       // Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("myBD").create();
        ActiveAndroid.initialize(this);
    }
}
