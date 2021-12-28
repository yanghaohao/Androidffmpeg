package com.example.yanghao.speakdemo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ffmpeg.Video

class MainActivity : AppCompatActivity() {
    private var btnSpeak: Button? = null
    private var txtText: TextView? = null
    private var llMain: RelativeLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtText = findViewById<View>(R.id.and_speech_tv) as TextView
        btnSpeak = findViewById<View>(R.id.and_speech_btn) as Button
        llMain = findViewById(R.id.ll_main)
        btnSpeak!!.setOnClickListener { //开启语音识别功能
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            //设置模式，目前设置的是自由识别模式
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            //提示语言开始文字，就是效果图上面的文字
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please start your voice")
            //开始识别，这里检测手机是否支持语音识别并且捕获异常
            try {
                startActivityForResult(intent, RESULT_SPEECH)
                txtText!!.text = ""
            } catch (a: ActivityNotFoundException) {
                val t = Toast.makeText(
                    applicationContext,
                    "Opps! Your device doesn't support Speech to Text",
                    Toast.LENGTH_SHORT
                )
                t.show()
            }
        }

        txtText!!.text = Video().video();
    }

    //接收返回的结果
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RESULT_SPEECH -> {
                if (resultCode == RESULT_OK && data != null) {
                    //返回结果是一个list，我们一般取的是第一个最匹配的结果
                    val text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    txtText!!.text = text!![0]
                    val button = Button(this@MainActivity)
                    val textView = TextView(this@MainActivity)
                    if (text[0].contains("按钮")) {
                        llMain!!.addView(button)

                    }
                    if (text[0].contains("名字是")) {
                        val a = text[0].split("是").toTypedArray()
                        textView.text = a[1]
                        llMain!!.addView(textView)
                    }
                }
            }
        }
    }

    companion object {
        protected const val RESULT_SPEECH = 1
    }
}