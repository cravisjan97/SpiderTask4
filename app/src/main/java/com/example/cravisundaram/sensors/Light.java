package com.example.cravisundaram.sensors;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by C RAVI SUNDARAM on 23-06-2015.
 */
public class Light extends Activity implements SensorEventListener {
    TextView t;
    SensorManager sm;
    Sensor sensor;

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sense);
        t=(TextView)findViewById(R.id.textView);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        PackageManager PM= this.getPackageManager();
        boolean gyro = PM.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE);
        boolean l = PM.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE);
        boolean s = PM.hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT);

        //light sensor is present

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        t.setText(""+event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
