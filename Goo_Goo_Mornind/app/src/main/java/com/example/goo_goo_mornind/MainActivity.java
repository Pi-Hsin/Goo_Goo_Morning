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
    private static ToggleButton toggleButton1;
    private static ToggleButton toggleButton2;
    private static ToggleButton toggleButton3;
    public static  String test ;
    public static  int monster=0 ;
    public static int cnt=0;
    public static String mode1 ;
    public static String mode2 ;
    public static String mode3 ;
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
        //取消鬧鐘
        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打開
                if (toggleButton1.isChecked()){
                    //imageView.setAlpha(1f);
                    //mTextView.setAlpha(1f);

                }else{
                    mode1="1";
                    monster++;
                    //關掉
                    Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                    PendingIntent sender=PendingIntent.getBroadcast(
                            MainActivity.this,cnt, intent, 0);
                    AlarmManager am;
                    am =(AlarmManager)getSystemService(ALARM_SERVICE);
                    am.cancel(sender);
                    Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                    alerm1.setAlpha(0.5f);
                    textView1.setAlpha(0.5f);

                }
            }
        });

        //取消鬧鐘
        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打開
                if (toggleButton2.isChecked()){
                    //imageView.setAlpha(1f);
                    //mTextView.setAlpha(1f);

                }else{
                    mode2="1";
                    monster++;
                    //關掉
                    Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                    PendingIntent sender=PendingIntent.getBroadcast(
                            MainActivity.this,cnt, intent, 0);
                    AlarmManager am;
                    am =(AlarmManager)getSystemService(ALARM_SERVICE);
                    am.cancel(sender);
                    Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                    alerm2.setAlpha(0.5f);
                    textView2.setAlpha(0.5f);
                }
            }
        });
        //取消鬧鐘
        toggleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打開
                if (toggleButton3.isChecked()){
                    //imageView.setAlpha(1f);
                    //mTextView.setAlpha(1f);

                }else{
                    mode3="1";
                    monster++;
                    //關掉
                    Intent intent = new Intent(MainActivity.this, CallAlarm.class);
                    PendingIntent sender=PendingIntent.getBroadcast(
                            MainActivity.this,cnt, intent, 0);
                    AlarmManager am;
                    am =(AlarmManager)getSystemService(ALARM_SERVICE);
                    am.cancel(sender);
                    Toast.makeText(MainActivity.this,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                    alerm3.setAlpha(0.5f);
                    textView3.setAlpha(0.5f);
                }
            }
        });

       // Restore preferences
        test=(mPreferences.getString("Arraylist",""));
        cnt=(mPreferences.getInt("cnt",0));
        monster=(mPreferences.getInt("monster",0));
        mode1=(mPreferences.getString("mode1","0"));
        mode2=(mPreferences.getString("mode2","0"));
        mode3=(mPreferences.getString("mode3","0"));

        if(monster==2){
            button.setVisibility(View.INVISIBLE);
        }
        String[] tmp1=test.split(",");
        //Toast.makeText(MainActivity.this, Integer.toString(cnt), Toast.LENGTH_SHORT) .show();
        //原本有一個鬧鐘
        if(cnt==1){
            String id=tmp1[0];
            String time=tmp1[1];
            String type=tmp1[2];
            String mode=tmp1[3];
            alerm1.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            toggleButton1.setVisibility(View.VISIBLE);
            textView1.setText(time);

            if(type.equals("0")){
                toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type.equals("1")){
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            String userid1 = getSharedPreferences("sharedPrefFile", MODE_PRIVATE)
                    .getString("mode1", "0");
            //響過

            if(userid1.equals("1")){
                alerm1.setAlpha(0.5f);
                textView1.setAlpha(0.5f);
            }
            if(mode1.equals("1")){
                alerm1.setAlpha(0.5f);
                textView1.setAlpha(0.5f);
            }
            Toast.makeText(MainActivity.this, "yaya"+mode1, Toast.LENGTH_SHORT)
                    .show();
        //原本有二個鬧鐘
        }else if(cnt==2) {
            String id=tmp1[0];
            String time=tmp1[1];
            String type=tmp1[2];
            String mode=tmp1[3];
            String id2 = tmp1[0+4];
            String time2 = tmp1[1+4];
            String type2 = tmp1[2+4];
            String mode2 = tmp1[3+4];
            alerm1.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            toggleButton1.setVisibility(View.VISIBLE);
            textView1.setText(time);

            if(type.equals("0")){
                toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type.equals("1")){
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            String userid1 = getSharedPreferences("sharedPrefFile", MODE_PRIVATE)
                    .getString("mode1", "0");
            //響過
            if(userid1.equals("1")){
                alerm1.setAlpha(0.5f);
                textView1.setAlpha(0.5f);
            }
            if(mode1.equals("1")){
                alerm1.setAlpha(0.5f);
                textView1.setAlpha(0.5f);
            }
            alerm2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            toggleButton2.setVisibility(View.VISIBLE);
            textView2.setText(time2);

            if(type2.equals("0")){
                toggleButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type2.equals("1")){
                toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            String userid2 = getSharedPreferences("sharedPrefFile", MODE_PRIVATE)
                    .getString("mode2", "0");
            //響過
            if(userid2.equals("1")){
                alerm2.setAlpha(0.5f);
                textView2.setAlpha(0.5f);
            }
            if(mode2.equals("1")){
                alerm2.setAlpha(0.5f);
                textView2.setAlpha(0.5f);
            }

            //原本有三個鬧鐘
        }else if(cnt==3) {
            String id=tmp1[0];
            String time=tmp1[1];
            String type=tmp1[2];
            String mode=tmp1[3];

            String id2 = tmp1[0+4];
            String time2 = tmp1[1+4];
            String type2 = tmp1[2+4];
            String mode2 = tmp1[3+4];

            String id3 = tmp1[0+4*2];
            String time3 = tmp1[1+4*2];
            String type3 = tmp1[2+4*2];
            String mode3 = tmp1[3+4*2];

            alerm1.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            toggleButton1.setVisibility(View.VISIBLE);
            textView1.setText(time);

            if(type.equals("0")){
                toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type.equals("1")){
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton1.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            String userid1 = getSharedPreferences("sharedPrefFile", MODE_PRIVATE)
                    .getString("mode1", "0");
            //響過
            if(userid1.equals("1")){
                alerm1.setAlpha(0.5f);
                textView1.setAlpha(0.5f);
            }
            if(mode1.equals("1")){
                alerm1.setAlpha(0.5f);
                textView1.setAlpha(0.5f);
            }
            alerm2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            toggleButton2.setVisibility(View.VISIBLE);
            textView2.setText(time2);

            if(type2.equals("0")){
                toggleButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type2.equals("1")){
                toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton2.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            String userid2 = getSharedPreferences("sharedPrefFile", MODE_PRIVATE)
                    .getString("mode2", "0");
            //響過
            if(userid2.equals("1")){
                alerm2.setAlpha(0.5f);
                textView2.setAlpha(0.5f);
            }
            if(mode2.equals("1")){
                alerm2.setAlpha(0.5f);
                textView2.setAlpha(0.5f);
            }
            alerm3.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            toggleButton3.setVisibility(View.VISIBLE);
            textView3.setText(time3);

            if(type3.equals("0")){
                toggleButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
            }else if(type3.equals("1")){
                toggleButton3.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
            }else{
                toggleButton3.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
            }
            String userid3 = getSharedPreferences("sharedPrefFile", MODE_PRIVATE)
                    .getString("mode3", "0");
            //響過
            if(userid3.equals("1")){
                alerm3.setAlpha(0.5f);
                textView3.setAlpha(0.5f);
            }
            if(mode3.equals("1")){
                alerm3.setAlpha(0.5f);
                textView3.setAlpha(0.5f);
            }

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
            mPreferences.edit()
                    .putString("mode1",mode1)
                    .putString("mode2",mode2)
                    .putString("mode3",mode3)

            .apply();

            test+=tmp;

            if(cnt==0){
                alerm1.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);
                toggleButton1.setVisibility(View.VISIBLE);
                textView1.setText(time);


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

                if(type.equals("0")){
                    toggleButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.owl));
                }else if(type.equals("1")){
                    toggleButton3.setBackgroundDrawable( getResources().getDrawable(R.drawable.cockatoo));
                }else{
                    toggleButton3.setBackgroundDrawable( getResources().getDrawable(R.drawable.normal));
                }
            }

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
        preferencesEditor.putInt("monster", monster);
        preferencesEditor.putString("mode1", mode1);
        preferencesEditor.putString("mode2", mode2);
        preferencesEditor.putString("mode3", mode3);
        preferencesEditor.apply();
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("Arraylist", test);
        preferencesEditor.putInt("cnt", cnt);
        preferencesEditor.putInt("monster", monster);
        preferencesEditor.putString("mode1", mode1);
        preferencesEditor.putString("mode2", mode2);
        preferencesEditor.putString("mode3", mode3);
        preferencesEditor.apply();
        //Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("Arraylist", "");
        preferencesEditor.putInt("cnt", 0);
        preferencesEditor.putInt("monster", 0);
        preferencesEditor.putString("mode1", "0");
        preferencesEditor.putString("mode2", "0");
        preferencesEditor.putString("mode3", "0");
        preferencesEditor.apply();
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }

}
