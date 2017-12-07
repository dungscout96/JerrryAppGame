package edu.gettysburg.jerry_s_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Button retry = this.findViewById(R.id.retryButton);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(view.getContext(), HomeScreenActivity.class);
                startActivityForResult(homeIntent, 0);
            }
        });

        Button game = this.findViewById(R.id.retryButton);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(gameIntent, 0);
            }
        });

    }
}
