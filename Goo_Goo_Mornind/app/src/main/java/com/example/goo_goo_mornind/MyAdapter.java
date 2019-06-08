package com.example.goo_goo_mornind;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

import static android.content.Context.ALARM_SERVICE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mData;
    private Context mcontext;
    private Clock mclock;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ToggleButton mToggleButton;
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            mToggleButton = (ToggleButton) v.findViewById(R.id.ToggleButton);
            imageView=(ImageView) v.findViewById(R.id.imageView_clockBackground);
            // 點擊項目時
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "click " +getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });


            if(mclock.mtype=="0"){
                mToggleButton.setBackgroundDrawable( mcontext.getResources().getDrawable(R.drawable.owl));
            }else if(mclock.mtype=="1"){
                mToggleButton.setBackgroundDrawable( mcontext.getResources().getDrawable(R.drawable.cockatoo));
            }else {
                mToggleButton.setBackgroundDrawable( mcontext.getResources().getDrawable(R.drawable.normal));
            }




            // 按下Button要做的事，可以開關鬧鐘

            mToggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //打開
                    if (mToggleButton.isChecked()){

                    }else{
                        //關掉
                        Intent intent = new Intent(mcontext, CallAlarm.class);
                        PendingIntent sender=PendingIntent.getBroadcast(
                                mcontext,0, intent, 0);
                        AlarmManager am;
                        am =(AlarmManager)mcontext.getSystemService(ALARM_SERVICE);
                        am.cancel(sender);
                        Toast.makeText(mcontext,"Goo Time 刪除", Toast.LENGTH_SHORT).show();
                        imageView.setAlpha(0.5f);
                        mTextView.setAlpha(0.5f);

                    }



                }
            });
        }
    }

    public MyAdapter(List<String> data,Context context,Clock clock) {
        mData = data;
        mcontext=context;
        mclock=clock;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_alerm, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //顯示鬧鐘時間
        holder.mTextView.setText(mclock.mtime);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
}