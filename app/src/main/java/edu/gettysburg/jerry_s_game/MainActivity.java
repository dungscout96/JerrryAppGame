package edu.gettysburg.jerry_s_game;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private void timer(long time, final TextView mTextField){
        new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("done!");
                Toast.makeText(getApplicationContext(),"Hello timer",Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    public void init()
    {

//        final TextView timerTextView = new TextView(this);
        //timer(30000, timerTextView);
        //layout.addView(timerTextView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
}