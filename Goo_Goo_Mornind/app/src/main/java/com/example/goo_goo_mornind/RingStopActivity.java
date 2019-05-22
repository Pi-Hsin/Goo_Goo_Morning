package com.example.goo_goo_mornind;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RingStopActivity extends AppCompatActivity {

    private Button fish;
    private static RingStopActivity inst;
    MediaPlayer mp;

    public static RingStopActivity instance() {
        return inst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_stop);

        fish = (Button)findViewById(R.id.ShutDown);

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFish();
            }
        });
        play();
        mp.setLooping(true);
    }


    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public void openFish() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void play(){
        if(mp == null) {
            mp = MediaPlayer.create(this, R.raw.alarm1);
        }
        mp.start();
    }

    public void stop() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

}
