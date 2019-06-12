package com.example.goo_goo_mornind;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    private ImageView alerm1;
    private ImageView alerm2;
    private ImageView alerm3;
    private Button button;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private ToggleButton toggleButton1;
    private ToggleButton toggleButton2;
    private ToggleButton toggleButton3;
    public static  String test ;
    public static int cnt=0;
    public static  Clock[] clocks = new Clock[10];
    private SharedPreferences mPreferences;
    private String sharedPrefFile ="com.example.android.hellosharedprefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alerm1=findViewById(R.id.imageView_alerm1);
        alerm2=findViewById(R.id.imageView_alerm2);
        alerm3=findViewById(R.id.imageView_alerm3);
        textView1=findViewById(R.id.textView_alarmTime1);
        textView2=findViewById(R.id.textView_alarmTime2);
        textView3=findViewById(R.id.textView_alarmTime3);
        toggleButton1=findViewById(R.id.toggleButton1);
        toggleButton2=findViewById(R.id.toggleButton2);
        toggleButton3=findViewById(R.id.toggleButton3);
        alerm1.setVisibility(View.INVISIBLE);
        alerm2.setVisibility(View.INVISIBLE);
        alerm3.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        toggleButton1.setVisibility(View.INVISIBLE);
        toggleButton2.setVisibility(View.INVISIBLE);
        toggleButton3.setVisibility(View.INVISIBLE);
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
            alerm1.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            toggleButton1.setVisibility(View.VISIBLE);
            textView1.setText(time);
            //取消鬧鐘
            toggleButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //打開
                    if (toggleButton1.isChecked()){
                        //imageView.setAlpha(1f);
                        //mTextView.setAlpha(1f);

                    }else{
                        
                        //關掉
                        Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                        PendingIntent sender=PendingIntent.getBroadcast(
                                MainActivity.this,1, intent, 0);
                        AlarmManager am;
                        am =(AlarmManager)getSystemService(ALARM_SERVICE);
                        am.cancel(sender);
                        Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                        alerm1.setAlpha(0.5f);
                        textView1.setAlpha(0.5f);
                    }
                }
            });
            if(type.equals("0")){
                toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type.equals("1")){
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            //響過
            if(mode.equals("1")){
                alerm1.setAlpha(0.5f);
                textView1.setAlpha(0.5f);
            }
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
            alerm1.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            toggleButton1.setVisibility(View.VISIBLE);
            textView1.setText(time);
            //取消鬧鐘
            toggleButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //打開
                    if (toggleButton1.isChecked()){
                        //imageView.setAlpha(1f);
                        //mTextView.setAlpha(1f);

                    }else{
                        //關掉
                        Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                        PendingIntent sender=PendingIntent.getBroadcast(
                                MainActivity.this,2, intent, 0);
                        AlarmManager am;
                        am =(AlarmManager)getSystemService(ALARM_SERVICE);
                        am.cancel(sender);
                        Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                        alerm1.setAlpha(0.5f);
                        textView1.setAlpha(0.5f);
                    }
                }
            });
            if(type.equals("0")){
                toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type.equals("1")){
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            //響過
            if(mode.equals("1")){
                alerm1.setAlpha(0.5f);
                textView1.setAlpha(0.5f);
            }
            alerm2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            toggleButton2.setVisibility(View.VISIBLE);
            textView2.setText(time2);
            //取消鬧鐘
            toggleButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //打開
                    if (toggleButton2.isChecked()){
                        //imageView.setAlpha(1f);
                        //mTextView.setAlpha(1f);

                    }else{
                        //關掉

                        Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                        PendingIntent sender=PendingIntent.getBroadcast(
                                MainActivity.this,0, intent, 0);
                        AlarmManager am;
                        am =(AlarmManager)getSystemService(ALARM_SERVICE);
                        am.cancel(sender);
                        Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                        alerm2.setAlpha(0.5f);
                        textView2.setAlpha(0.5f);
                    }
                }
            });
            if(type2.equals("0")){
                toggleButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type2.equals("1")){
                toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            //響過
            if(mode2.equals("1")){
                alerm2.setAlpha(0.5f);
                textView2.setAlpha(0.5f);
            }
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
            alerm1.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            toggleButton1.setVisibility(View.VISIBLE);
            textView1.setText(time);
            //取消鬧鐘
            toggleButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //打開
                    if (toggleButton1.isChecked()){
                        //imageView.setAlpha(1f);
                        //mTextView.setAlpha(1f);

                    }else{
                        //關掉
                        Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                        PendingIntent sender=PendingIntent.getBroadcast(
                                MainActivity.this,3, intent, 0);
                        AlarmManager am;
                        am =(AlarmManager)getSystemService(ALARM_SERVICE);
                        am.cancel(sender);
                        Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                        alerm1.setAlpha(0.5f);
                        textView1.setAlpha(0.5f);
                    }
                }
            });
            if(type.equals("0")){
                toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type.equals("1")){
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            //響過
            if(mode.equals("1")){
                alerm1.setAlpha(0.5f);
                textView1.setAlpha(0.5f);
            }
            alerm2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            toggleButton2.setVisibility(View.VISIBLE);
            textView2.setText(time2);
            //取消鬧鐘
            toggleButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //打開
                    if (toggleButton2.isChecked()){
                        //imageView.setAlpha(1f);
                        //mTextView.setAlpha(1f);

                    }else{
                        //關掉
                        Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                        PendingIntent sender=PendingIntent.getBroadcast(
                                MainActivity.this,0, intent, 0);
                        AlarmManager am;
                        am =(AlarmManager)getSystemService(ALARM_SERVICE);
                        am.cancel(sender);
                        Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                        alerm2.setAlpha(0.5f);
                        textView2.setAlpha(0.5f);
                    }
                }
            });
            if(type2.equals("0")){
                toggleButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type2.equals("1")){
                toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            //響過
            if(mode2.equals("1")){
                alerm2.setAlpha(0.5f);
                textView2.setAlpha(0.5f);
            }
            alerm3.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            toggleButton3.setVisibility(View.VISIBLE);
            textView3.setText(time3);
            //取消鬧鐘
            toggleButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //打開
                    if (toggleButton3.isChecked()){
                        //imageView.setAlpha(1f);
                        //mTextView.setAlpha(1f);

                    }else{
                        //關掉
                        Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                        PendingIntent sender=PendingIntent.getBroadcast(
                                MainActivity.this,0, intent, 0);
                        AlarmManager am;
                        am =(AlarmManager)getSystemService(ALARM_SERVICE);
                        am.cancel(sender);
                        Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                        alerm3.setAlpha(0.5f);
                        textView3.setAlpha(0.5f);
                    }
                }
            });
            if(type3.equals("0")){
                toggleButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type3.equals("1")){
                toggleButton3.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton3.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            //響過
            if(mode3.equals("1")){
                alerm3.setAlpha(0.5f);
                textView3.setAlpha(0.5f);
            }
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
            if(cnt==0){
                alerm1.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);
                toggleButton1.setVisibility(View.VISIBLE);
                textView1.setText(time);
                //取消鬧鐘
                toggleButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //打開
                        if (toggleButton1.isChecked()){
                            //imageView.setAlpha(1f);
                            //mTextView.setAlpha(1f);

                        }else{
                            //關掉
                            Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                            PendingIntent sender=PendingIntent.getBroadcast(
                                    MainActivity.this,0, intent, 0);
                            AlarmManager am;
                            am =(AlarmManager)getSystemService(ALARM_SERVICE);
                            am.cancel(sender);
                            Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                            alerm1.setAlpha(0.5f);
                            textView1.setAlpha(0.5f);
                        }
                    }
                });
                if(type.equals("0")){
                    toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
                }else if(type.equals("1")){
                    toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
                }else{
                    toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
                }
            }else if(cnt==1){
                alerm2.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                toggleButton2.setVisibility(View.VISIBLE);
                textView2.setText(time);
                //取消鬧鐘
                toggleButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //打開
                        if (toggleButton2.isChecked()){
                            //imageView.setAlpha(1f);
                            //mTextView.setAlpha(1f);

                        }else{
                            //關掉
                            Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                            PendingIntent sender=PendingIntent.getBroadcast(
                                    MainActivity.this,0, intent, 0);
                            AlarmManager am;
                            am =(AlarmManager)getSystemService(ALARM_SERVICE);
                            am.cancel(sender);
                            Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                            alerm2.setAlpha(0.5f);
                            textView2.setAlpha(0.5f);
                        }
                    }
                });
                if(type.equals("0")){
                    toggleButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
                }else if(type.equals("1")){
                    toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
                }else{
                    toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
                }
            }else {
                alerm3.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                toggleButton3.setVisibility(View.VISIBLE);
                textView3.setText(time);
                //取消鬧鐘
                toggleButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //打開
                        if (toggleButton3.isChecked()){
                            //imageView.setAlpha(1f);
                            //mTextView.setAlpha(1f);

                        }else{
                            //關掉
                            Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                            PendingIntent sender=PendingIntent.getBroadcast(
                                    MainActivity.this,0, intent, 0);
                            AlarmManager am;
                            am =(AlarmManager)getSystemService(ALARM_SERVICE);
                            am.cancel(sender);
                            Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                            alerm3.setAlpha(0.5f);
                            textView3.setAlpha(0.5f);
                        }
                    }
                });
                if(type.equals("0")){
                    toggleButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
                }else if(type.equals("1")){
                    toggleButton3.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
                }else{
                    toggleButton3.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
                }
            }

            Toast.makeText(MainActivity.this, clocks[cnt].mtime, Toast.LENGTH_SHORT)
                    .show();
            cnt++;
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
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("Arraylist", test);
        preferencesEditor.putInt("cnt", cnt);
        preferencesEditor.apply();
        //Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("Arraylist", test);
        preferencesEditor.putInt("cnt", cnt);
        preferencesEditor.apply();
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }
}
