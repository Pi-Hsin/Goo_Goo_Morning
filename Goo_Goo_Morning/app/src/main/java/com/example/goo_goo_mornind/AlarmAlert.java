package com.example.goo_goo_mornind;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.ToggleButton;


public class AlarmAlert extends Activity {
    private Vibrator vibrator;
    private ToggleButton tBtn_vib;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //tBtn_vib = (ToggleButton) findViewById(R.id.toggle_button1);
        Bundle bundle = getIntent().getExtras();
        String time=bundle.getString("time");
        String type=bundle.getString("type");
        String id=bundle.getString("id");
        //startMedia();//播放音樂


        //startVibrator();


        //startVibrator();
        if(type.equals("1")) {
            Intent intent2 = new Intent(AlarmAlert.this, VoiceActivity.class);
            intent2.putExtra("alarm_clock", time);
            intent2.putExtra("id", id);
            startActivity(intent2);
        }else if(type.equals("0")){
            Intent intent3= new Intent(AlarmAlert.this, ShakeActivity.class);
            intent3.putExtra("alarm_clock", time);
            intent3.putExtra("id", id);
            startActivity(intent3);
        }else{
            Intent intent4= new Intent(AlarmAlert.this, NormalActivity.class);
            intent4.putExtra("alarm_clock", time);
            intent4.putExtra("id", id);
            startActivity(intent4);
        }
/*

        new AlertDialog.Builder(AlarmAlert.this)
                .setIcon(R.drawable.clock)
                .setTitle("Goo Goo Morning!!")
                .setMessage("又是一個美好的一天惹")
                .setPositiveButton("關掉偶",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                //if (tBtn_vib.isChecked()){
                                 //   vibrator.cancel();
                                //}
                                vibrator.cancel();
                                AlarmAlert.this.finish();
                            }

                        })

                .show();
*/
    }

    private void startVibrator() {
        long[] pattern = {500, 1000, 500, 1000};//停止開始停止 開始
        vibrator.vibrate(pattern, 0);
    }

}