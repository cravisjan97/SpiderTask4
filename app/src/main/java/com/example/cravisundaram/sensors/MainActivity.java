package com.example.cravisundaram.sensors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * Created by C RAVI SUNDARAM on 23-06-2015.
 */
public class MainActivity extends Activity {
    Button b, b1;
    private static final String TAG = "MySensors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "oncreate\n");
        setContentView(R.layout.activity_sense);
        b = (Button) findViewById(R.id.button);
        b1 = (Button) findViewById(R.id.button2);
        //buttons to launch the next activity with the respective sensors
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Accelerate.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProximitySensor.class);
                startActivity(i);
            }
        });

    }
}
