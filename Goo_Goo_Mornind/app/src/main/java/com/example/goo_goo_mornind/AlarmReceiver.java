package com.example.goo_goo_mornind;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmReceiver extends WakefulBroadcastReceiver {


    private MediaPlayer mp;

    @Override
    public void onReceive(final Context context, Intent intent) {
        //this will update the UI with message
        AddClockActivity inst = AddClockActivity.instance();

        inst.changeToRingingScreen();

    }
}
