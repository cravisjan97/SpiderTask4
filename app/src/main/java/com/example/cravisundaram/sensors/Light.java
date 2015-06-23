package com.example.cravisundaram.sensors;

import android.app.Activity;
import android.content.Context;
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
    TextView t,t1;
    SensorManager sm;
    Sensor sensor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sense);
        t=(TextView)findViewById(R.id.textView);
        t1=(TextView)findViewById(R.id.textView2);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=sm.getDefaultSensor(Sensor.TYPE_LIGHT);

       if(sensor!=null)
       {
           t1.setText("Light sensor is available");

                  }
        else
           t1.setText("not available");
        //light sensor is present

    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT) {
            t.setText(String.valueOf(event.values[0]));
        }
        else
            t.setText("nothing");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
