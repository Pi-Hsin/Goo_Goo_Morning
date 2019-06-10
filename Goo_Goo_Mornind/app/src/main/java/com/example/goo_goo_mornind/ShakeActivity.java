package com.example.goo_goo_mornind;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ShakeActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorMgr;
    private Sensor accelerometer;
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 4000;

    private long mShakeTimestamp;
    private int mShakeCount;
    private String[] mQ={"100/10-7= ? ","30*3/10-5= ? ","10-7+3-5+1= ? ","55/(6+5)= ? ","100/(2*5)-7 = ?","91/(2+3+2)-10= ? ","log10+10-8 = ? ","121/(6+5)-7= ?","43-38+5-8= ?","111/3-30-2= ? "};
    private String[] mA={"3","4","2","5","3","3","3","4","2","5"};
    private TextView mAnswer;
    private TextView mQuention;
    private TextView mtime;
    private TextView mX;
    private TextView mY;
    private TextView mZ;
    private String mCorrect;
    private ImageView iv;
    private ImageView ivb;//imageView_closeButton

    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        mtime = findViewById(R.id.textView_alarmTime);
        //接收值
        Intent intent = getIntent();
        String time = intent.getStringExtra("alarm_clock");
        mtime.setText(time);
        Toast.makeText(ShakeActivity.this,"設定Goo Time為"+time,
                Toast.LENGTH_SHORT)
                .show();
        mQuention = findViewById(R.id.text_Quention);
        mAnswer=findViewById(R.id.text_Answer);
        Random ran = new Random();
        int mNumber=ran.nextInt(10)+1;
        mQuention.setText("Quention:\n"+mQ[mNumber]);
        mCorrect=mA[mNumber];
        iv = (ImageView)findViewById(R.id.Owl_shake_ImgV);
        ivb = (ImageView)findViewById(R.id.imageView_closeButton);
        ivb.setVisibility(View.INVISIBLE);

        mAnswer.setText("0");//Please Shake your phone reply answer
        mSensorMgr = (SensorManager)getSystemService(SENSOR_SERVICE);

        accelerometer=((SensorManager) mSensorMgr).getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        ObjectAnimator ani = ObjectAnimator.ofFloat( findViewById( R.id.Owl_shake_ImgV)  // 動畫目標
                , "rotation"  // 動畫效果 (X 軸翻轉)
                , 0f          // 開始角度
                , -45f        // 結束角度
        );
        //ani.setRepeatCount(ObjectAnimator.INFINITE);
        //ani.setRepeatMode(ObjectAnimator.REVERSE);
        //ani.setDuration(1000);  // 動畫時間
        //ani.start();

        //加入音樂
        mediaPlayer = MediaPlayer.create(this,R.raw.alarm1);
        mediaPlayer.start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = Float.valueOf(event.values[0]);
            float y = Float.valueOf(event.values[1]);
            float z = Float.valueOf(event.values[2]);

            mX.setText("X: "+String.valueOf(x));
            mY.setText("Y: "+String.valueOf(y));
            mZ.setText("Z: "+String.valueOf(z));

            // SensorManager.GRAVITY_EARTH 約為 10
            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            // 平方根 求斜邊的公式, Math.sqrt 開根號
            float gForce = (float) Math.sqrt((double) (gX * gX + gY * gY + gZ * gZ));

            // 沒搖晃時 gForce 為 1 或 < 1, 搖晃時要 > 2.7F (x,y,z立體搖晃要夠大)
            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                final long now = System.currentTimeMillis();
                // ignore shake events too close to each other (500ms)
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }

                // reset the shake count after 3 seconds of no shakes
                if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                    mShakeCount = 0;
                }

                mShakeTimestamp = now;
                mShakeCount++;

                if( mShakeCount%2 ==1)
                {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.owl_q2));
                }
                else {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.owl_q));
                }

                //Toast.makeText(this, "shake:" +String.valueOf(mShakeCount), Toast.LENGTH_SHORT).show();

                if(String.valueOf(mShakeCount) != mCorrect)
                {
                    mAnswer.setText("shake: " + String.valueOf(mShakeCount));
                    //Toast.makeText(this,"正確解答："+mCorrect+"  現在累積數："+mShakeCount,Toast.LENGTH_SHORT).show();
                }
                if (mShakeCount == Integer.valueOf(mCorrect))
                {
                    Toast.makeText(this,"You are correct!!!",Toast.LENGTH_LONG).show();
                    mShakeCount=0;
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.owl_t));
                    ivb.setVisibility(View.VISIBLE);
                    //!!!!!!!!!!這邊顯示關閉的按紐，並且關掉鬧鐘喔!!!!!!!!!
                    mediaPlayer.stop();
                    ShakeActivity.this.finish();
                    Intent intent2 = new Intent();
                    intent2.setClass(ShakeActivity.this , MainActivity.class);
                    startActivity(intent2);
                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorMgr.registerListener(this, mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorMgr.unregisterListener(this);
        super.onPause();
    }

}
