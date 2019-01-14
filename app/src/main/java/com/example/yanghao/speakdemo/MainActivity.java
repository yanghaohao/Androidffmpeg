package com.example.yanghao.speakdemo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected static final int RESULT_SPEECH = 1;
    private Button btnSpeak;
    private TextView txtText;
    private RelativeLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtText = (TextView) findViewById(R.id.and_speech_tv);
        btnSpeak = (Button) findViewById(R.id.and_speech_btn);
        llMain = findViewById(R.id.ll_main);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //开启语音识别功能
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //设置模式，目前设置的是自由识别模式
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //提示语言开始文字，就是效果图上面的文字
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please start your voice");
                //开始识别，这里检测手机是否支持语音识别并且捕获异常
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(), "Opps! Your device doesn't support Speech to Text", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }

    //接收返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && data != null) {
                    //返回结果是一个list，我们一般取的是第一个最匹配的结果
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtText.setText(text.get(0));
                    Button button = new Button(MainActivity.this);
                    TextView textView = new TextView(MainActivity.this);
                    if (text.get(0).contains("按钮")){
                        llMain.addView(button);
                        textView.setId(R.id.wenzi);
                    }
                    if (text.get(0).contains("名字是")){
                        String[] a = text.get(0).split("是");
                        textView.setText(a[1]);
                        llMain.addView(textView);
                    }


                }
                break;
            }
        }
    }
}


