package com.masstersoft.weather.launch;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.masstersoft.weather.R;
import com.masstersoft.weather.model.City;
import com.masstersoft.weather.model.CityList;
import com.masstersoft.weather.launch.adapter.CityListAdapter;
import com.masstersoft.weather.model.IWeatherList;
import com.masstersoft.weather.model.ParserWeatherJSON;
import com.masstersoft.weather.model.Weather5DayList;
import com.masstersoft.weather.utils.Persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 15.11.2015.
 */
public class MenuCityFragment extends Fragment  {
    private Context context;

    private Button btnOpenCity;
    private EditText editText1;
    ListView lw;
    List<City> data = new ArrayList<City>();
    private TextView  modeText;


    Persistence storage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = inflater.getContext();
        View rootView = inflater.inflate(R.layout.fragment_menu_city, container, false);
        btnOpenCity = (Button) rootView.findViewById(R.id.btn1);
        editText1 = (EditText) rootView.findViewById(R.id.editText1);
        modeText = (TextView) rootView.findViewById(R.id.mode);

        lw = (ListView) rootView.findViewById(R.id.listView);
        lw.setAdapter(new CityListAdapter(context, CityList.getInstance().getCities()));

        storage = Persistence.getIns();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        int mode = storage.getMode();
        modeText.setText("Погода на " + mode + " дней" );
    }

    public IWeatherList btnOpenCityClick() {
        String city = editText1.getText().toString();

         return getIWeatherList(city);
    }
    public IWeatherList onClickCity(View view){
        ViewGroup viewGroup = (ViewGroup)view;
        TextView child = (TextView) viewGroup.getChildAt(1);
        String city = child.getText().toString();

        return getIWeatherList(city);
    }

    public IWeatherList getIWeatherList( String city ){

        //если нет интернета, загрузить из бд
        IWeatherList pwj = new ParserWeatherJSON(city);
        if(pwj.getCity() == null) {
            Weather5DayList  wdl =  Weather5DayList.getInstance();
            wdl.loadDataWeather(city);
            return wdl;
        }
        return pwj;
    }



}
