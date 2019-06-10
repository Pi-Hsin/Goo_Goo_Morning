package com.example.goo_goo_mornind;
/**
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.app.AlarmManager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //獲取鬧鐘管理者
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }


    public void setAlarm(View view){

        //獲取當前系統時間
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        //彈出時間對話方塊
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c=Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                c.set(Calendar.MINUTE,minute);

                Intent intent=new Intent();
                intent.setAction("com.asus.android_28_alarm_notification");

                //將來時態的跳轉
                PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0x00,intent,0);

                //設定鬧鐘
                alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
                //時間一到,傳送廣播
                //廣播接受者(跳轉Activity)
                //時間一到,跳轉Activity,並在該Activity中播放音樂

            }
        },hour,minute,true);
        timePickerDialog.show();
    }


    public void setAlarmCycle(View view){
        //獲取當前系統時間
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        //彈出時間對話方塊
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c=Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                c.set(Calendar.MINUTE,minute);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);

                Intent intent=new Intent();
                intent.setAction("com.asus.android_28_alarm_notification");

                //將來時態的跳轉
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0x00,intent,0);

                //設定鬧鐘
//               alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
                //設定週期鬧鐘
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),5000, pendingIntent);

                //時間一到,傳送廣播
                //廣播接受者(跳轉Activity)
                //時間一到,跳轉Activity,並在該Activity中播放音樂


                NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                sendNotification2();

            }
        },hour,minute,true);
        timePickerDialog.show();
    }


    public void cancelAlarmCycle(View view){
        alarmManager.cancel(pendingIntent);
    }


    public void sendNotification(View view){
        //例項化通知管理器
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //例項化通知
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setContentTitle("今天天氣不錯");
        builder.setContentText("陽光明媚,萬里無雲");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);

        builder.setAutoCancel(true);

        builder.setSmallIcon(android.R.drawable.ic_media_play);

        builder.setContentIntent(PendingIntent.getActivity(this,0x102,new Intent(this,RingActivity.class),0));

        Notification notification=builder.build();
        //傳送通知
        notificationManager.notify(0x101,notification);

    }



    public void sendNotification2(){
        //例項化通知管理器
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //例項化通知
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setContentTitle("今天天氣");
        builder.setContentText("萬里無雲");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);

        builder.setAutoCancel(true);

        builder.setSmallIcon(android.R.drawable.ic_media_play);

        builder.setContentIntent(PendingIntent.getActivity(this,0x102,new Intent(this,RingActivity.class),0));

        Notification notification=builder.build();
        //傳送通知
        notificationManager.notify(0x101,notification);

    }



}**/

import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class AddClock extends AppCompatActivity {
     static int cnt;
    TextView setTime1;

    Button mButton1;
    Button mButton2;

    String time1String = null;
    String defalutString = "__ : __";
    String game1String;

    AlertDialog builder = null;
    Calendar c=Calendar.getInstance();

    private ImageView imageView_select;
    private Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clock);
        imageView_select = findViewById(R.id.cockatoo);
        spinner =  findViewById(R.id.spinner);

        //取得目前有幾個鬧鐘
        Intent intent= this.getIntent();
        if (intent != null) {
            cnt = intent.getIntExtra("cnt", 1)+1;
            Toast.makeText(AddClock.this, Integer.toString(cnt), Toast.LENGTH_SHORT)
                    .show();
        }
        //取得活動的Preferences物件
        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        time1String = settings.getString("TIME1", defalutString);
        InitButton1();
        //InitButton2();
        setTime1.setText(time1String);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final String[] game = {"體感遊戲", "繞口令遊戲","我不玩遊戲"};
        ArrayAdapter<String> gameList = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                game);
        spinner.setAdapter(gameList);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(AddClock.this, "你選的是" + game[position], Toast.LENGTH_SHORT).show();
                if(game[position]=="體感遊戲"){
                    imageView_select.setImageDrawable( getResources().getDrawable(R.drawable.owl));
                    game1String="1";
                    Toast.makeText(AddClock.this, "你選的是" + game[position], Toast.LENGTH_SHORT).show();
                }else if(game[position]=="繞口令遊戲"){
                    imageView_select.setImageDrawable( getResources().getDrawable(R.drawable.cockatoo));
                    game1String="2";
                    Toast.makeText(AddClock.this, "你選的是" + game[position], Toast.LENGTH_SHORT).show();
                }else {
                    imageView_select.setImageDrawable( getResources().getDrawable(R.drawable.normal));
                    game1String="3";
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //如果沒選的話，預設為不玩遊戲
                imageView_select.setImageDrawable( getResources().getDrawable(R.drawable.normal));
                game1String="3";
            }
        });
    }


    public void InitButton1() {

        setTime1=(TextView) findViewById(R.id.setTime1);
        mButton1=(Button)findViewById(R.id.mButton1);
        mButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                c.setTimeInMillis(System.currentTimeMillis());
                int mHour=c.get(Calendar.HOUR_OF_DAY);
                int mMinute=c.get(Calendar.MINUTE);

                new TimePickerDialog(AddClock.this, new TimePickerDialog.OnTimeSetListener() {

                    public void onTimeSet(TimePicker view,int hourOfDay, int minute) {
                        c.setTimeInMillis(System.currentTimeMillis());
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.set(Calendar.SECOND,0);
                        c.set(Calendar.MILLISECOND,0);

                        String tmpS=format(hourOfDay)+":"+format(minute);
                        setTime1.setText(tmpS);

/**
                                Intent intent = new Intent(AddClock.this, CallAlarm.class);
                                PendingIntent sender=PendingIntent.getBroadcast(
                                        AddClock.this,0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager)getSystemService(ALARM_SERVICE);
                                am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender

                                );

                                String tmpS=format(hourOfDay)+":"+format(minute);
                                setTime1.setText(tmpS);

                                //SharedPreferences儲存資料,並提交
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();


                                Toast.makeText(AddClock.this,"設定Goo Time為"+tmpS,
                                        Toast.LENGTH_SHORT)
                                        .show();
 **/
                        }
                    },mHour,mMinute,true).show();
            }
        });
    }

    /*
    public void InitButton2() {

        mButton2=(Button) findViewById(R.id.mButton2);
        mButton2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(AddClock.this, CallAlarm.class);

                PendingIntent sender=PendingIntent.getBroadcast(AddClock.this,cnt++, intent, 0);
                AlarmManager am;
                am =(AlarmManager)getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(AddClock.this,"Goo Time 刪除",
                        Toast.LENGTH_SHORT).show();
                setTime1.setText("__ : __");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "__ : __");
                editor.commit();
            }
        });
    }
    */

    @Override

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            builder = new AlertDialog.Builder(AddClock.this)
                    .setIcon(R.drawable.clock)
                    .setTitle("Goo提示:")
                    .setMessage("您是否要退出Goo Goo Morning!!!")
                    .setPositiveButton("取消",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //AddClock.this.finish();
                                    builder.dismiss();
                                }
                            })
                    .setNegativeButton("還是取消",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    builder.dismiss();
                                }
                            }).show();

        }

        return true;

    }



    private String format(int x) {
        String s=""+x;
        if(s.length()==1) s="0"+s;
        return s;
    }

    public void save_onclick(View view) {

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        String tmpS=format(hour)+":"+format(min);
        Intent intent = new Intent(AddClock.this, CallAlarm.class);

        intent.putExtra("time",tmpS);
        intent.putExtra("type",spinner.getSelectedItemPosition()+"");


        PendingIntent sender=PendingIntent.getBroadcast(
                AddClock.this,cnt, intent, 0);

        //如果設定的時間在當前時間以前，要把日期+1
        if(c.getTimeInMillis() <= System.currentTimeMillis()){
            c.add(Calendar.DAY_OF_YEAR,1);
        }


        AlarmManager am;
        am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);


        setTime1.setText(tmpS);


        Intent intent2 = new Intent(AddClock.this, MainActivity.class);

        Bundle bundle = new Bundle();
        //Clock test=new Clock(0,tmpS,spinner.getSelectedItemPosition());
        bundle.putString("clock",Integer.toString(cnt)+","+tmpS+","+spinner.getSelectedItemPosition()+",0,");


        /**
        SharedPreferences sharedPreferences = getSharedPreferences("data" , MODE_PRIVATE);
        //取得SharedPreferences ， 丟入的參數為("名稱" , 存取權限)

        sharedPreferences.edit().putInt("id" , position).apply();
        //存入資料，丟入的參數為(key , value)

        sharedPreferences.getInt("score" , 0);
        //取出資料， 丟入的參數為(key , 若是沒值，預設為多少)
**/

        intent2.putExtras(bundle);

        intent2.putExtra("alarm_clock",tmpS);
        intent2.putExtra("alarm_game",game1String);

        startActivity(intent2);

        //Intent intent2 = new Intent(AddClock.this, MainActivity.class);
       // intent2.putExtra("alarm_clock",tmpS);
       //startActivity(intent2);

        //SharedPreferences儲存資料,並提交



        Toast.makeText(AddClock.this,"設定第"+Integer.toString(cnt)+"Goo Time為"+tmpS,
                Toast.LENGTH_SHORT)
                .show();
    }

    //回到主畫面
    public void back_to_main_iv(View view) {
        Intent intent = new Intent();
        intent.setClass(this , MainActivity.class);
        startActivity(intent);
    }

    public void choose_game(View view) {

    }
}



