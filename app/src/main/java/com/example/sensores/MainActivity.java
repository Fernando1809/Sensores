package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int whip=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView texto = (TextView) findViewById(R.id.tvSensor);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (sensor == null) finish();

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.values[0] < sensor.getMaximumRange()) {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    texto.setText("CAMBIANDO A COLOR ROJO");
                } else {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    texto.setText("SENSOR DE PROXIMIDAD");
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        start();
    }

    private void start() {
    }

    public void star() {
        sensorManager.registerListener(sensorEventListener, sensor, 2000 * 1000);
    }

    public void stop() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }
        @Override
    protected void onResume() {
            star();
            super.onResume();

            Button siguiente = (Button) findViewById(R.id.btSiguiente);
            siguiente.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent siguiente = new Intent(MainActivity.this,Acelerometro.class);
                    startActivity(siguiente);
                }
            });
        }
    }
