package com.example.goo_goo_mornind;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class NormalActivity extends AppCompatActivity {
    private ImageView moveImage;
    private RelativeLayout mRelativeLayout;
    private Button ivb;//imageView_closeButton
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        ivb = (Button)findViewById(R.id.imageView_closeButton);
        ivb.setVisibility(View.INVISIBLE);

        ivb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setClass(NormalActivity.this , MainActivity.class);
                startActivity(intent2);
            }
        });

        initView();

        moveImage.setOnTouchListener(onTouchListener);
        ObjectAnimator ani = ObjectAnimator.ofFloat( findViewById( R.id.sildeimg)  // 動畫目標
                , "translationX"  // 動畫效果 (X 軸翻轉)
                , -200        // 開始角度
                , 200    // 結束角度
        );
        ani.setRepeatCount(ObjectAnimator.INFINITE);
        //ani.setRepeatMode(ObjectAnimator.REVERSE);
        ani.setDuration(2000);  // 動畫時間
        ani.start();

        //加入音樂
        mediaPlayer = MediaPlayer.create(this,R.raw.alarm1);
        mediaPlayer.start();
    }

    private void initView() {
        moveImage = (ImageView) findViewById(R.id.move_image);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
    }


    private boolean isMove;

    private float mLastX;
    private float mLastY;
    private float mStartX;
    private float mStartY;
    private long mLastTime ;
    private long mCurrentTime;

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        int lastX;
        int lastY;
        int left;
        int top;
        int right;
        int bottom;
        int screenWidth;
        int screenHeight;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    isMove = false;
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    mStartX = event.getRawX();
                    mStartY = event.getRawY();
                    mLastTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;
                    screenWidth = mRelativeLayout.getWidth();
                    screenHeight = mRelativeLayout.getHeight();

                    if (dx != 0 || dy != 0) {
                        isMove = true;
                    }

                    left = v.getLeft() + dx;
                    top = v.getTop() ;
                    right = v.getRight() + dx;
                    bottom = v.getBottom() ;
                    if (left < 0) {
                        left = 0;
                        right = left + v.getWidth();
                    }
                    if (right > screenWidth) {
                        right = screenWidth;
                        left = right - v.getWidth();
                    }
                    if (top < 0) {
                        top = 0;
                        bottom = top + v.getHeight();
                    }
                    if (bottom > screenHeight) {
                        bottom = screenHeight;
                        top = bottom - v.getHeight();
                    }
                    v.layout(left, top, right, bottom);
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_UP:
                    if(left <= (screenWidth / 2)) {
                        //left = 0;
                    }else{
                        left = screenWidth - moveImage.getWidth();
                    }
                    v.layout(left, top, right, bottom);
                    Rect vRect = new Rect();
                    v.getHitRect(vRect);
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) v
                            .getLayoutParams();
                    lp.leftMargin = vRect.left;
                    lp.topMargin = vRect.top;
                    v.setLayoutParams(lp);
                    if (right >= screenWidth) {
                        //Toast.makeText(MainActivity.this,"換圖",Toast.LENGTH_SHORT).show();
                        moveImage.setImageDrawable(getResources().getDrawable(R.drawable.normal_2));
                        ivb.setVisibility(View.VISIBLE);
                        //!!!!!!!!!!這邊顯示關閉的按紐，並且關掉鬧鐘喔!!!!!!!!!
                        //mediaPlayer.stop();
                        //NormalActivity.this.finish();


                    }
                    mLastX = event.getRawX();
                    mLastY = event.getRawY();

                    mCurrentTime = System.currentTimeMillis();
                    if(mCurrentTime - mLastTime < 800){//长按不起作用
                        Log.d("kitchee","开始Y="+mStartY);
                        Log.d("kitchee","最后Y="+mLastY);
                        Log.d("kitchee","移动Y="+Math.abs(mStartY - mLastY));
                        if(Math.abs(mStartX- mLastX )< 10.0 && Math.abs(mStartY - mLastY) < 10.0){//判断是否属于点击
                            //Toast.makeText(MainActivity.this,"可以执行点击任务",Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    };

}