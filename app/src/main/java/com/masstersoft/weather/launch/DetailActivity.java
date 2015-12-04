package com.masstersoft.weather.launch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.masstersoft.weather.R;

public class DetailActivity extends AppCompatActivity {

    TextView tvFromTemp;
    TextView tvToTemp;
    TextView tvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvFromTemp = (TextView)findViewById(R.id.tvFromTemp);
        tvToTemp = (TextView)findViewById(R.id.tvToTemp);
        tvCity = (TextView)findViewById(R.id.tvCity);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            tvCity.setText((String) b.get("CityName"));
            tvFromTemp.setText("от " + (String) b.get("Min_temp"));
            tvToTemp.setText("до " + (String) b.get("Max_temp"));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
}
