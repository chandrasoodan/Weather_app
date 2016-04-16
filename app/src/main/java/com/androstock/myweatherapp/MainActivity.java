package com.androstock.myweatherapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;

    Typeface weatherFont;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView)findViewById(R.id.city_field);
        updatedField = (TextView)findViewById(R.id.updated_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)findViewById(R.id.humidity_field);
        pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);



        editText = (EditText) findViewById(R.id.editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });



        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
                    public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                        cityField.setText(weather_city);
                        updatedField.setText(weather_updatedOn);
                        detailsField.setText(weather_description);
                        currentTemperatureField.setText(weather_temperature);
                        humidity_field.setText("Humidity: "+weather_humidity);
                        pressure_field.setText("Pressure: "+weather_pressure);
                        weatherIcon.setText(Html.fromHtml(weather_iconText));

                    }
                });
                asyncTask.execute(editText.getText().toString());
                editText.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);
            }
        });


    }
    @Override
    public void onBackPressed() {
        editText.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        cityField.setText("");
        updatedField.setText("");
        detailsField.setText("");
        currentTemperatureField.setText("");
        humidity_field.setText("");
        pressure_field.setText("");
        weatherIcon.setText("");
    }


}
