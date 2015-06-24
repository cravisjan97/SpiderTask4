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

    public class MySurface extends SurfaceView implements Runnable {
        SurfaceHolder holder;
        Canvas canvas;
        Thread t = null;
        boolean flag = false;

        public MySurface(Context context) {
            super(context);
            holder = getHolder();

        }

        public void pause() {
            flag = false;
            while (true) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public void resume() {
            flag = true;
            t = new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            while (flag) {
                if (!holder.getSurface().isValid())
                    continue;
                canvas = holder.lockCanvas();
                canvas.drawRGB(0, 0, 0);
                Paint paint8 = new Paint();
                paint8.setColor(Color.rgb(184, 134, 11));
                paint8.setTextSize(40);
                canvas.drawText("Proximity : "+x,125 , 600, paint8);
                Bon=BitmapFactory.decodeResource(getResources(),R.drawable.bulbon);
                Boff=BitmapFactory.decodeResource(getResources(),R.drawable.bulboff);
                if(x==0)
                    canvas.drawBitmap(Bon,100,20,null);
                else
                    canvas.drawBitmap(Boff,100,20,null);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s=new MySurface(this);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        s.resume();
        x=0;
        setContentView(s);
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
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
        x=event.values[0];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
