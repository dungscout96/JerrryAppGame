package edu.gettysburg.jerry_s_game;

import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int width;
    private int height;

    private ArrayList<ImageView> balloons = new ArrayList<ImageView>();

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

        final TextView mTextField = this.findViewById(R.id.textView);
        new CountDownTimer(30000, 1000) {


            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                mTextField.setText("done!");
            }

        }.start();


        ImageView balloon1 = (ImageView) findViewById(R.id.balloonRed);

        balloons.add(balloon1);

        // Get Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        width = size.x;
        height = size.y;

        balloon1.setX(-80.0f);
        balloon1.setY(-80.0f);

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

    }

    public void addBalloons() {


    }


    public void changePos() {

        for (int i = 0; i < balloons.size(); i++) {

            ImageView balloon = balloons.get(i);

            float balloony = balloon.getY();
            balloony -= 10;
            balloon.setY(balloony);

            float balloon1x = balloon.getX();

            if (balloon.getY() + balloon.getHeight() < 0) {
                balloons.remove(balloon);
            }
        }
    }
}

