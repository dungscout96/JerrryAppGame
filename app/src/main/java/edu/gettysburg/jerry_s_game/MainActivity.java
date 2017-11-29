package edu.gettysburg.jerry_s_game;

import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int width;
    private int height;

    private ImageView balloon1;
    private ImageView balloon2;

    private float balloon1x;
    private float balloon1y;
    private float balloon2x;
    private float balloon2y;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balloon1 = (ImageView) findViewById(R.id.balloon1);
        balloon2 = (ImageView) findViewById(R.id.balloon2);

        // Get Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        width = size.x;
        height = size.y;

        balloon1.setX(-80.0f);
        balloon1.setY(-80.0f);

        balloon2.setX(-50.0f);
        balloon2.setY(-80.0f);

        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0, 20);
    }


        public void changePos(){
            balloon1y -= 10;
            if (balloon1.getY() + balloon1.getHeight() < 0){
                balloon1x = (float)Math.floor(Math.random() * (width - balloon1.getWidth()));
                balloon1y = height + 100.0f;
            }
            balloon1.setX(balloon1x);
            balloon1.setY(balloon1y);
    }

}

