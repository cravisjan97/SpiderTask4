package com.example.cravisundaram.sensors;

import android.app.Activity;
import android.content.Context;
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

/**
 * Created by C RAVI SUNDARAM on 22-06-2015.
 */
public class Accelerate extends Activity implements SensorEventListener {

    float sx, sy, sz;
    MySurface s;
    SensorManager sm;

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class MySurface extends SurfaceView implements Runnable {
        SurfaceHolder holder;
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
                Canvas canvas = holder.lockCanvas();
                canvas.drawRGB(218, 165, 32);

                float centerX = canvas.getWidth() / 2;
                float centerY = canvas.getHeight() / 2;


                Paint paint1 = new Paint();
                paint1.setStyle(Paint.Style.FILL);
                paint1.setColor(Color.rgb(255, 0, 0));
                canvas.drawRect(centerX / 2, 100, 3 * centerX / 2, 150, paint1);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.rgb(0, 255, 127));
                canvas.drawCircle(centerX + sx * 10, 125, 20, paint);

                Paint paint2 = new Paint();
                paint2.setStyle(Paint.Style.FILL);
                paint2.setColor(Color.rgb(255, 0, 0));
                canvas.drawRect(centerX / 2, 300, 3 * centerX / 2, 350, paint2);
                Paint paint3 = new Paint();
                paint3.setStyle(Paint.Style.FILL);
                paint3.setColor(Color.rgb(0, 255, 127));
                canvas.drawCircle(centerX + sy * 10, 325, 20, paint3);

                Paint paint4 = new Paint();
                paint4.setStyle(Paint.Style.FILL);
                paint4.setColor(Color.rgb(255, 0, 0));
                canvas.drawRect(centerX / 2, 500, 3 * centerX / 2, 550, paint4);
                Paint paint5 = new Paint();
                paint5.setStyle(Paint.Style.FILL);
                paint5.setColor(Color.rgb(0, 255, 127));
                canvas.drawCircle(centerX + sz * 10, 525, 20, paint5);

                Paint paint6 = new Paint();
                paint6.setColor(Color.rgb(0, 0, 255));
                paint6.setTextSize(40);
                canvas.drawText("X-AXIS", 3 * centerX / 4, 200, paint6);

                Paint paint7 = new Paint();
                paint7.setColor(Color.rgb(0, 0, 255));
                paint7.setTextSize(40);
                canvas.drawText("Y-AXIS", 3 * centerX / 4, 400, paint7);

                Paint paint8 = new Paint();
                paint8.setColor(Color.rgb(0, 0, 255));
                paint8.setTextSize(40);
                canvas.drawText("Z-AXIS", 3 * centerX / 4, 600, paint8);

                holder.unlockCanvasAndPost(canvas);

            }
        }
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s = new MySurface(this);


        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor sensor = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        sx = sy = sz = 0;
        s.resume();
        setContentView(s);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sx = event.values[0];
        sy = event.values[1];
        sz = event.values[2];

    }

}
