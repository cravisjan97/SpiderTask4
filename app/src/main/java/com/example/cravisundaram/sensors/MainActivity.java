package com.example.cravisundaram.sensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by C RAVI SUNDARAM on 23-06-2015.
 */
public class MainActivity extends Activity {
    Button b,b1;
    private static final String TAG = "MySensors";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"oncreate\n");
        setContentView(R.layout.activity_sense);
        b=(Button)findViewById(R.id.button);
        b1=(Button)findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Accelerate.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ProximitySensor.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"restart\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"start\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"resume\n");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"pause\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"stop\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"destroy\n");
    }
}