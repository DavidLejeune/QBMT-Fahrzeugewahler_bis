package com.lejeune.david.fahrzeugewahler;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CongratsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);


        //region init
        //Setting the background color to white
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        //endregion

        //play sound +- 7 sec
        final MediaPlayer applause = MediaPlayer.create(this, R.raw.app14);
        applause.start();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent newIntent = new Intent(CongratsActivity.this, ShowActivity.class);
                finish();
                startActivity(newIntent);
            }
        }, 5000);
    }
}
