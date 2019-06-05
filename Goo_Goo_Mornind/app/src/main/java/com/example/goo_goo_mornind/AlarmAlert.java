package com.example.goo_goo_mornind;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;


public class AlarmAlert extends Activity

{
    private Vibrator vibrator;
    @Override

    protected void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //startMedia();//播放音樂
        startVibrator();//震動

        new AlertDialog.Builder(AlarmAlert.this)
                .setIcon(R.drawable.clock)
                .setTitle("Goo Goo Morning!!")
                .setMessage("又是一個美好的一天惹")
                .setPositiveButton("關掉偶",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {   vibrator.cancel();
                                AlarmAlert.this.finish();

                            }

                        })

                .show();

    }

    private void startVibrator() {
        long[] pattern = {500, 1000, 500, 1000};//停止開始停止 開始
        vibrator.vibrate(pattern, 0);
    }

}