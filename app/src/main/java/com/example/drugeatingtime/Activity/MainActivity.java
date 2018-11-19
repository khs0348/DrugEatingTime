package com.example.drugeatingtime.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.drugeatingtime.R;

public class MainActivity extends AppCompatActivity {

    LoginActivity a = new LoginActivity();
    boolean loginON = a.loginON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (loginON == false) {
            TextView TimeSetting = (TextView) findViewById(R.id.TimeSetting);
            TimeSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(it);
                }
            });
        } else {
            TextView TimeSetting = (TextView) findViewById(R.id.TimeSetting);
            TimeSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getApplicationContext(), TimeSettingActivity.class);
                    startActivity(it);
                }
            });
        }

        if (loginON == false) {
            TextView DrugSerch = (TextView) findViewById(R.id.DrugSerch);
            DrugSerch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(it);
                }
            });
        } else {
            TextView DrugSerch = (TextView) findViewById(R.id.DrugSerch);
            DrugSerch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getApplicationContext(), DrugSerchActivity.class);
                    startActivity(it);
                }
            });
        }

        if (loginON == false) {
            TextView AppSetting = (TextView) findViewById(R.id.AppSetting);
            AppSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(it);
                }
            });
        }else{
            TextView AppSetting = (TextView) findViewById(R.id.AppSetting);
            AppSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getApplicationContext(), AppSettingActivity.class);
                    startActivity(it);
                }
            });
         }
    }
}
