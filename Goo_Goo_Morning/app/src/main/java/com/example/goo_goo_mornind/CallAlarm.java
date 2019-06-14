package com.example.goo_goo_mornind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class CallAlarm extends BroadcastReceiver

{

    @Override

    public void onReceive(Context context, Intent intent)

    {


        Intent i = new Intent(context, AlarmAlert.class);



        Bundle bundleRet = new Bundle();

        bundleRet.putString("STR_CALLER", "");
        bundleRet.putString("time",intent.getStringExtra("time"));
        bundleRet.putString("type",intent.getStringExtra("type"));
        bundleRet.putString("id",intent.getStringExtra("id"));

        i.putExtras(bundleRet);

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);

    }

}