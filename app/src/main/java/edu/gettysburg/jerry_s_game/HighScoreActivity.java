package edu.gettysburg.jerry_s_game;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        try {
            FileInputStream fis = openFileInput("high_score");
            DataInputStream in = new DataInputStream(fis);
            int savedHighScore = in.readInt();
            Log.i("HighScore", "Current high score: " + savedHighScore);
            fis.close();
            in.close();
            TextView score = (TextView)findViewById(R.id.scoreText);
            score.setText(""+savedHighScore);
        } catch (IOException e) {
            TextView score = (TextView)findViewById(R.id.scoreText);
            score.setText("0");
            e.printStackTrace();
        }
    }
}