package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView x, y ,z,list;
    private Sensor mySensor;
    private SensorManager SM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //stworzenie sensor managera
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        //dodanie akcelerometra
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //dodanie listenera dla akcelerometra
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Przypisanie obiektow TextView
        x=(TextView)findViewById(R.id.textView);
        y=(TextView)findViewById(R.id.textView2);
        z=(TextView)findViewById(R.id.textView3);
        list = (TextView)findViewById(R.id.textView4);

        //wypisujemy liste sensoror do obiektu textview
        List<Sensor> mlist=SM.getSensorList(Sensor.TYPE_ALL);
        for (int i=1; i<mlist.size();i++){
            list.append("\n" + mlist.get(i).getName());
        }

    }

    //przypisujemy wartosci z sensorow do textview po zmianie polozenia urzadzenia
    @Override
    public void onSensorChanged(SensorEvent event) {
        x.setText("X: " + event.values[0]);
        y.setText("Y: " + event.values[1]);
        z.setText("Z: " + event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //niepotrzebne
    }
}