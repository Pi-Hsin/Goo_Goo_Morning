package com.example.goo_goo_mornind;

import android.content.Context;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Button button;
    private MyAdapter mAdapter;
    private  Set<String> test1 = new HashSet<>();;
    public static  Set<String> test = new HashSet<>();
    private SharedPreferences mPreferences;

    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> myDataset = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        button=(Button)findViewById(R.id.Button);
        // Initialize preferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

       // Restore preferences
        test=(mPreferences.getStringSet("Arraylist",test));
        Toast.makeText(MainActivity.this, test1.toString(), Toast.LENGTH_SHORT)
                    .show();



        //addClock傳來的值
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String tmp=(bundle.getString("clock"));
            String[] x=tmp.split(",");
            String id=x[0];
            String time=x[1];
            String type=x[2];
            String mode=x[3];
            Clock y=new Clock(id,time,type,mode);
            test.add(tmp);
            myDataset.add(Integer.toString(test.size()));
            mAdapter = new MyAdapter(myDataset,MainActivity.this,y);
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

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putStringSet("Arraylist", test);
        preferencesEditor.apply();
    }
}
