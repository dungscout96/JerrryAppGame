package edu.gettysburg.jerry_s_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void gameOver(int totalScore){
        Intent gameOverIntent = new Intent(this.getBaseContext(), EndActivity.class);
        gameOverIntent.putExtra("Score", totalScore);
        startActivityForResult(gameOverIntent, 0);
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}