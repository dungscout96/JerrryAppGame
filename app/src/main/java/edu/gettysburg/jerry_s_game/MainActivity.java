package edu.gettysburg.jerry_s_game;

import android.graphics.Point;
import android.media.AudioRecord;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int width;
    private int height;

    private ArrayList<ImageView> balloons = new ArrayList<ImageView>();
    private ArrayList<ImageView> balloonSelector = new ArrayList<ImageView>();


    //private float balloon1x;
    //private float balloon1y;
    //private float balloon2x;
    //private float balloon2y;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView balloonRed = (ImageView) findViewById(R.id.balloonRed);
        ImageView balloonOrange = (ImageView) findViewById(R.id.balloonOrange);
        ImageView balloonBlue = (ImageView) findViewById(R.id.balloonBlue);
        ImageView balloonPurple = (ImageView) findViewById(R.id.balloonPurple);
        ImageView balloonBlack = (ImageView) findViewById(R.id.balloonBlack);
        ImageView balloonDeath = (ImageView) findViewById(R.id.balloonDeath);
        ImageView balloonGreen = (ImageView) findViewById(R.id.balloonGreen);
        ImageView balloonYellow = (ImageView) findViewById(R.id.balloonYellow);

        balloonSelector = new ArrayList<ImageView>();

        balloonSelector.add(balloonDeath);
        balloonSelector.add(balloonBlack);
        balloonSelector.add(balloonPurple);
        balloonSelector.add(balloonBlue);
        balloonSelector.add(balloonGreen);
        balloonSelector.add(balloonYellow);
        balloonSelector.add(balloonOrange);
        balloonSelector.add(balloonRed);

        // Get Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        width = size.x;
        height = size.y;

        balloonRed.setX(-80.0f);
        balloonRed.setY(-80.0f);

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

        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        addBalloons();
                    }
                });
            }
        }, 0, 1000);

        makeBalloonsClickable();

    }


    public void makeBalloonsClickable() {


        for (ImageView balloon : balloons) {

            balloon.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Log.i("touched", "balloon touched");
                    //     balloon.setVisibility(View.INVISIBLE);
                    balloonTouched(view);
                    return false;
                }
            });
        }
    }

    public void balloonTouched(View view) {
        view.setVisibility(View.INVISIBLE);
    }

        public void addBalloons(){
            Random rand = new Random();
            int n = rand.nextInt(balloonSelector.size());
            ImageView balloon = balloonSelector.get(n);
            balloons.add(balloon);
        }

        public void changePos(){

            for(int i = 0; i < balloons.size();i++){

                ImageView balloon = balloons.get(i);
                balloon.setY(balloon.getY()-10);

                float balloon1x = balloon.getX();

                if (balloon.getY() + balloon.getHeight() < 0){
                    balloons.remove(balloon);
                }
            }
    }
}

