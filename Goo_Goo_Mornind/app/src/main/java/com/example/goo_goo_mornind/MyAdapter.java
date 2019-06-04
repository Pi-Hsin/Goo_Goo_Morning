package com.example.goo_goo_mornind;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public Button Button;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            Button = (Button) v.findViewById(R.id.button);
            // 點擊項目時
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "click " +getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });

            // 點擊項目中的Button時
            Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 按下Button要做的事，可以開關鬧鐘
                }
            });
        }
    }

    public MyAdapter(List<String> data) {
        mData = data;
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
        holder.mTextView.setText(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}