package com.example.drugeatingtime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
        },4000);//n초후 MainActivity 로 화면 전환
    }
}
