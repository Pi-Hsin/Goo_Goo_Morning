package com.example.goo_goo_mornind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import android.widget.RelativeLayout;
import android.widget.TextView;


import android.widget.Toast;

import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Button button;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> myDataset = new ArrayList<>();
        Intent intent = getIntent();
        String time = intent.getStringExtra("alarm_clock");
        if(time != null &&!time.equals("")){
            myDataset.add(Integer.toString(0));

        //預設有3個鬧鐘，之後再看怎們儲存值

        //for(int i = 0; i < 3; i++){
        //    myDataset.add(Integer.toString(i));

       // }
        mAdapter = new MyAdapter(myDataset,time,MainActivity.this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        button=(Button)findViewById(R.id.Button);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }else{

        }

    }

    //新增鬧鐘
    public void addAlerm(View view){
        Intent intent = new Intent();
        intent.setClass(this , AddClock.class);
        startActivity(intent);
    }
    //導覽頁
    public void question(View view){
        Intent intent = new Intent();
        intent.setClass(this , OnboardingActivity.class);
        startActivity(intent);
    }

}
