package com.masstersoft.weather.launch;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.masstersoft.weather.R;
import com.masstersoft.weather.model.IWeatherList;
import com.masstersoft.weather.utils.Persistence;

public class MainActivity extends AppCompatActivity {
    ListView lw;
    MenuCityFragment menuCity;
    Persistence storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage = new Persistence(this);

        menuCity = new MenuCityFragment();
        setCurrentFragment(menuCity, false);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void setCurrentFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (addToBackStack) transaction.addToBackStack("test");
        transaction.commit();
    }

    public void onClickBtn(View view) {
        IWeatherList iw = menuCity.btnOpenCityClick();
        if (iw.getCity() == null) {
            Toast.makeText(this, "Города нет в базе данных!", Toast.LENGTH_SHORT).show();
        } else {
            setCurrentFragment(new WeatherCityFragment(iw), true);
        }

    }

    public void onClickCity(View view) {
        IWeatherList iw = menuCity.onClickCity(view);
        if (iw.getCity() == null) {
            Toast.makeText(this, "Города нет в базе данных!", Toast.LENGTH_SHORT).show();
        } else {
            setCurrentFragment(new WeatherCityFragment(iw), true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void onWeather3(MenuItem i) {
        changeWeatherMode(3);
    }

    public void onWeather7(MenuItem i) {
        changeWeatherMode(7);
    }

    public void onWeather16(MenuItem i) {
        changeWeatherMode(16);
    }

    public void onAbout(MenuItem i) {
        Toast.makeText(this, " Автор Дмитрий Грибков ", Toast.LENGTH_SHORT).show();
    }

    private void changeWeatherMode(int mode) {
        storage.setMode(mode);
        menuCity.onResume();
        Toast.makeText(this, "Выбрана погода на  " + mode + " дней!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }else
        super.onBackPressed();

    }
}
