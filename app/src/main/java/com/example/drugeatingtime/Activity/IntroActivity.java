package com.example.drugeatingtime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.drugeatingtime.R;

public class IntroActivity extends MainActivity {
    @Override
    protected  void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
                    public  void run(){
                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);

                finish();
            }
        },2000);//n초후 MainActivity 로 화면 전환
    }
}
