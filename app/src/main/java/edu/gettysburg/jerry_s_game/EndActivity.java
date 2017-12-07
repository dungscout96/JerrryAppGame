package edu.gettysburg.jerry_s_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by chelseavarella-lee on 12/6/17.
 */

public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        //get total Score
        Intent sourceIntent = getIntent();
        int gameScore = sourceIntent.getIntExtra("Score", -1);
        TextView yourScore = findViewById(R.id.yourScore);
        if (gameScore == -1){
            gameScore = 0;
        }
        yourScore.setText("" + gameScore);

        TextView highestView = findViewById(R.id.highScore);
        try {
            FileInputStream fis = openFileInput("high_score");
            DataInputStream in = new DataInputStream(fis);
            int savedHighScore = in.readInt();
            Log.i("EndActivity", "High score: " + savedHighScore);
            fis.close();
            in.close();
            highestView.setText(""+savedHighScore);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //play again button
        Button play = this.findViewById(R.id.playAgainButton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(gameIntent, 0);
            }
        });

        //main menu button
        Button info = this.findViewById(R.id.mainMenuButton);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(view.getContext(), HomeScreenActivity.class);
                startActivityForResult(infoIntent, 0);
            }
        });
    }
}
