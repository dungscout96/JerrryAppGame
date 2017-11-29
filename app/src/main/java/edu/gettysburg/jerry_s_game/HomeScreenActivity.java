package edu.gettysburg.jerry_s_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //play button
        Button play = this.findViewById(R.id.playbutton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(gameIntent, 0);
            }
        });

        //info button
        Button info = this.findViewById(R.id.infoButton);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(view.getContext(), InfoActivity.class);
                startActivityForResult(infoIntent, 0);
            }
        });

        //High Score button
        Button highScore = this.findViewById(R.id.highScoreButton);
        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent highScoreIntent = new Intent(view.getContext(), HighScoreActivity.class);
                startActivityForResult(highScoreIntent, 0);
            }
        });


    }
}
