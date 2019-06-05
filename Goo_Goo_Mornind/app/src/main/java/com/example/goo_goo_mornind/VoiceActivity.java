package com.example.goo_goo_mornind;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class VoiceActivity extends AppCompatActivity {

    Random r = new Random();   // 亂數種子
    private int randomNow; // 現在取的亂數
    private TextView textView_question;
    private ArrayList<String> voiceGame_List;
    private ImageView imageView_cockatoo;
    private TextView textView_repeatMe;
    private ImageView imageView_rightOrError;
    private ImageView imageView_answerBackground;
    private TextView textView_speech;
    private ImageView imageView_cockatooAnswer;
    private ImageView imageView_cockatooAnswerBackground;
    private TextView textView_goodmorning;
    private ImageView imageView_microphone;
    private TextView textView_hint;
    private ImageView imageView_closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find view
        imageView_cockatoo = findViewById(R.id.imageView_cockatoo);
        textView_repeatMe = findViewById(R.id.textView_repeatMe);
        textView_question = findViewById(R.id.textView_question);
        imageView_rightOrError = findViewById(R.id.imageView_rightOrError);
        imageView_answerBackground = findViewById(R.id.imageView_answerBackground);
        textView_speech = findViewById(R.id.textView_speech);
        imageView_cockatooAnswer = findViewById(R.id.imageView_cockatooAnswer);
        imageView_cockatooAnswerBackground = findViewById(R.id.imageView_cockatooAnswerBackground);
        textView_goodmorning = findViewById(R.id.textView_goodmorning);
        imageView_microphone = findViewById(R.id.imageView_microphone);
        textView_hint = findViewById(R.id.textView_hint);
        imageView_closeButton = findViewById(R.id.imageView_closeButton);

        // 一開始先不顯示回答的對話框、答對的鳥、對話框、關掉按鈕
        imageView_answerBackground.setVisibility(View.INVISIBLE);
        textView_speech.setVisibility(View.INVISIBLE);
        imageView_cockatooAnswer.setVisibility(View.INVISIBLE);
        imageView_rightOrError.setVisibility(View.INVISIBLE);
        imageView_cockatooAnswerBackground.setVisibility(View.INVISIBLE);
        textView_goodmorning.setVisibility(View.INVISIBLE);
        imageView_closeButton.setVisibility(View.INVISIBLE);

        // initialize question
        String[] tmpArray = {
                "我在天上飛",
                "早起的鳥兒有蟲吃",
                "起床了",
                "我是神力女超人",
                "我是鋼鐵人",
                "我是美國隊長",
                "我是奇異博士",
                "我是黑寡婦",
                "我是驚奇隊長",
                "我是蜘蛛人",
                "我是綠巨人",
                "我是蝙蝠俠",
                "我是超人",
                "我是雷神索爾" };
        voiceGame_List = new ArrayList<String>(tmpArray.length);
        for(int i=0;i<tmpArray.length;i++){
            voiceGame_List.add(tmpArray[i]);
        }

        // random select the first question
        randomNow = r.nextInt(voiceGame_List.size());
        textView_question.setText(voiceGame_List.get(randomNow));
        voiceGame_List.remove(randomNow);
    }

    public void speech_onClick(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請靠近手機說話~");
        try {
            startActivityForResult(intent,200);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),"Intent problem", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == RESULT_OK && data != null) {
                // 顯示出回答對話框
                imageView_answerBackground.setVisibility(View.VISIBLE);
                textView_speech.setVisibility(View.VISIBLE);
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                textView_speech.setText(result.get(0).replace(" ", ""));


                // 比對題目跟語音轉文字是否相同
                if(textView_question.getText().equals(textView_speech.getText())){
                    // 答對鳥圖、對話框出現
                    imageView_rightOrError.setVisibility(View.VISIBLE);
                    imageView_cockatooAnswer.setVisibility(View.VISIBLE);
                    imageView_cockatooAnswerBackground.setVisibility(View.VISIBLE);
                    textView_goodmorning.setVisibility(View.VISIBLE);
                    // 出現打勾小圖示
                    String uri_right = "@drawable/check"; //圖片路徑和名稱
                    int imageResource_right = getResources().getIdentifier(uri_right, null, getPackageName()); //取得圖片Resource位子
                    imageView_rightOrError.setImageResource(imageResource_right);
                    // 麥克風icon、提示字消失
                    imageView_microphone.setVisibility(View.INVISIBLE);
                    textView_hint.setVisibility(View.INVISIBLE);
                    // 關掉按鈕出現
                    imageView_closeButton.setVisibility(View.VISIBLE);
                } else {
                    // 原本鳥圖變憤怒鳥圖
                    String uri = "@drawable/cockatoo_f"; //圖片路徑和名稱
                    String uri_error = "@drawable/cross"; //圖片路徑和名稱
                    int imageResource = getResources().getIdentifier(uri, null, getPackageName()); //取得圖片Resource位子
                    int imageResource_error = getResources().getIdentifier(uri_error, null, getPackageName()); //取得圖片Resource位子
                    imageView_cockatoo.setImageResource(imageResource);
                    // 出現錯誤小圖示
                    imageView_rightOrError.setImageResource(imageResource_error);
                    // 重新出題目
                    imageView_rightOrError.setVisibility(View.VISIBLE);
                    textView_repeatMe.setText("Try again!");
                    textView_repeatMe.setTextColor(android.graphics.Color.RED);
                    randomNow = r.nextInt(voiceGame_List.size());
                    textView_question.setText(voiceGame_List.get(randomNow));
                    voiceGame_List.remove(randomNow);
                }
            }
        }
    }
}