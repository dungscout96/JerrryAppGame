package edu.gettysburg.jerry_s_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by chelseavarella-lee on 12/6/17.
 */

public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

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
