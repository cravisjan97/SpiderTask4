package com.example.cravisundaram.sensors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

/**
 * Created by C RAVI SUNDARAM on 22-06-2015.
 */
public class ProximitySensor extends Activity implements SensorEventListener {

    SensorManager sm;
    float x;
    MySurface s;
    Bitmap Bon,Boff;
    //This class is created for canvas
    public class MySurface extends SurfaceView {
        SurfaceHolder holder;
        boolean flag = true;

        public MySurface(Context context) {
            super(context);
            holder = getHolder();

        }

        public void pause() {
            flag = false;
        }//to stop the canvas when it is not on the foreground


        public void resume() {
            flag = true;
            new Service().execute();//call for async task to perform the surface view operations

        }
        public class Service extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... params) {
                while (flag) {
                    if (!holder.getSurface().isValid())//Loop to find the right surface to draw the canvas
                        continue;
                    Canvas canvas = holder.lockCanvas();
                    canvas.drawRGB(0, 0, 0);
                    Paint paint8 = new Paint();
                    paint8.setColor(Color.rgb(184, 134, 11));
                    paint8.setTextSize(40);
                    canvas.drawText("Proximity : "+x,125 , 600, paint8);
                    Bon=BitmapFactory.decodeResource(getResources(),R.drawable.bulbon);//to add images to the canvas
                    Boff=BitmapFactory.decodeResource(getResources(),R.drawable.bulboff);
                    if(x==0)
                        canvas.drawBitmap(Bon,100,20,null);
                    else
                        canvas.drawBitmap(Boff,100,20,null);
                    holder.unlockCanvasAndPost(canvas);

                }
                return null;
            }
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s=new MySurface(this);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);//registering sensor manager
        s.resume();
        x=0;
        setContentView(s);
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);//unregistering sensor manager
        super.onPause();
        s.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        s.resume();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x=event.values[0];//gives the proximity value

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
