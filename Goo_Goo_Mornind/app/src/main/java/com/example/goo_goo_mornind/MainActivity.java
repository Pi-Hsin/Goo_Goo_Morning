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


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Button button;
    private static MyAdapter mAdapter;
    public static  String test ;
    public static int cnt=0;
    public static   ArrayList<String> myDataset=new ArrayList<>();
    public static  Clock[] clocks = new Clock[10];
    private SharedPreferences mPreferences;
    private String sharedPrefFile ="com.example.android.hellosharedprefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        button=(Button)findViewById(R.id.Button);
        // Initialize preferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

       // Restore preferences
        test=(mPreferences.getString("Arraylist",""));
        cnt=(mPreferences.getInt("cnt",0));
        String[] tmp1=test.split(",");
        Toast.makeText(MainActivity.this, Integer.toString(cnt), Toast.LENGTH_SHORT)
                .show();
        //原本有一個鬧鐘
        if(cnt==1){
            String id=tmp1[0];
            String time=tmp1[1];
            String type=tmp1[2];
            String mode=tmp1[3];
            clocks[0].mid=id;
            clocks[0].mtime=time;
            clocks[0].mtype=type;
            clocks[0].mmode=mode;

            Toast.makeText(MainActivity.this, clocks[0].mtime, Toast.LENGTH_SHORT)
                    .show();
        //原本有二個鬧鐘
        }else if(cnt==2) {
            String id=tmp1[0];
            String time=tmp1[1];
            String type=tmp1[2];
            String mode=tmp1[3];
            clocks[0].mid=id;
            clocks[0].mtime=time;
            clocks[0].mtype=type;
            clocks[0].mmode=mode;
            String id2 = tmp1[0+4];
            String time2 = tmp1[1+4];
            String type2 = tmp1[2+4];
            String mode2 = tmp1[3+4];
            clocks[1].mid=id2;
            clocks[1].mtime=time2;
            clocks[1].mtype=type2;
            clocks[1].mmode=mode2;
            Toast.makeText(MainActivity.this, clocks[0].mtime+clocks[1].mtime, Toast.LENGTH_SHORT)
                    .show();
            //原本有三個鬧鐘
        }else if(cnt==3) {
            String id=tmp1[0];
            String time=tmp1[1];
            String type=tmp1[2];
            String mode=tmp1[3];
            clocks[0].mid=id;
            clocks[0].mtime=time;
            clocks[0].mtype=type;
            clocks[0].mmode=mode;
            String id2 = tmp1[0+4];
            String time2 = tmp1[1+4];
            String type2 = tmp1[2+4];
            String mode2 = tmp1[3+4];
            clocks[1].mid=id2;
            clocks[1].mtime=time2;
            clocks[1].mtype=type2;
            clocks[1].mmode=mode2;
            String id3 = tmp1[0+4*2];
            String time3 = tmp1[1+4*2];
            String type3 = tmp1[2+4*2];
            String mode3 = tmp1[3+4*2];
            clocks[2].mid=id3;
            clocks[2].mtime=time3;
            clocks[2].mtype=type3;
            clocks[2].mmode=mode3;
             Toast.makeText(MainActivity.this, clocks[0].mtime+clocks[1].mtime+clocks[2].mtime, Toast.LENGTH_SHORT)
                    .show();
        }

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
            test+=tmp;
            clocks[cnt]=y;

            Toast.makeText(MainActivity.this, clocks[cnt].mtime, Toast.LENGTH_SHORT)
                    .show();
            myDataset.add(Integer.toString(cnt));

            cnt++;
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
        intent.putExtra("cnt",cnt);
        Toast.makeText(MainActivity.this, Integer.toString(cnt), Toast.LENGTH_SHORT)
                .show();
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
        preferencesEditor.putString("Arraylist", test);
        preferencesEditor.putInt("cnt", cnt);
        preferencesEditor.apply();
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }
}
