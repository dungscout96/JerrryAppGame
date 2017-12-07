package edu.gettysburg.jerry_s_game;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(getString(R.string.saved_high_score), 15);
        editor.commit();

        try {
            FileInputStream fis = openFileInput("high_score");
            int savedHighScore = fis.read();
            Log.i("HighScore", "Current high score: " + savedHighScore);
            fis.close();
            TextView score = (TextView)findViewById(R.id.scoreText);
            score.setText(""+savedHighScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}