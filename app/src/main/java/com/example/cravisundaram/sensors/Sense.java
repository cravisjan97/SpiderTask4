package com.example.cravisundaram.sensors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class Sense extends Activity {
    MySurface s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s=new MySurface(this);
        setContentView(s);

    }

    @Override
    protected void onPause() {
        super.onPause();
        s.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        s.resume();
    }
    public class MySurface extends SurfaceView implements Runnable {
        SurfaceHolder holder;
        Thread t=null;
        boolean flag=true;

        public MySurface(Context context) {
            super(context);
            holder=getHolder();

        }
        public void pause()
        {
            flag=false;
            while(true)
            {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        public void resume()
        {
            flag=true;
            t= new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            while(flag) {
                if (!holder.getSurface().isValid())
                    continue;
                Canvas canvas = holder.lockCanvas();
                canvas.drawRGB(218,165,32);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.rgb(0, 255, 127));

                canvas.drawCircle(20,20,20,paint);
                holder.unlockCanvasAndPost(canvas);

            }
        }
    }

}
