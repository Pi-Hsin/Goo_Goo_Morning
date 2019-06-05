package com.example.goo_goo_mornind;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class LogoActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent alarmIntent = new Intent(LogoActivity.this, MainActivity.class);
                startActivity(alarmIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
