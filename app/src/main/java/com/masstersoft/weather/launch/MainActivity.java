package com.masstersoft.weather.launch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.masstersoft.weather.R;
import com.masstersoft.weather.utils.API;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWeather();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getWeather() {

        new AsyncTask<Void, Void, API.ApiResponse>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected API.ApiResponse doInBackground(Void... x) {
                ArrayList<String> params = new ArrayList<String>();
                params.add("id");params.add("52489");
                params.add("APPID");params.add("58c3cdec0969373fd82d01a13c7de5bc");
                params.add("lang");params.add("ru");
                params.add("units");params.add("metric");

                return API.execute(API.ApiMethod.GET_WEATHER.format(), API.HttpMethod.GET, params.toArray(new String[params.size()]));
            }

            @Override
            protected void onPostExecute(API.ApiResponse apiResponse) {
                super.onPostExecute(apiResponse);
                try {
                    if (apiResponse.isSuccess()) {
                        android.util.Log.d("Weather",apiResponse.getJson().toString());

                    }

                } catch (Exception e) {
                    android.util.Log.e("Weather", "ALERT! ALERT! Exception!", e);
                } finally {

                }
            }
        }.execute();
    }
}
