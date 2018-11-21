package com.example.drugeatingtime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.drugeatingtime.R;

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loginon);

        TimeSetting();
        DrugSerch();
        AppSetting();

        LogoutButton();

    }

    public void TimeSetting() {
        TextView TimeSetting = (TextView) findViewById(R.id.TimeSetting);
        TimeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), TimeSettingActivity.class);
                startActivity(it);
            }
        });
    }

    public void DrugSerch() {
        TextView DrugSerch = (TextView) findViewById(R.id.DrugSerch);
        DrugSerch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), DrugSerchActivity.class);
                startActivity(it);
            }
        });
    }

    public void AppSetting() {
        TextView AppSetting = (TextView) findViewById(R.id.AppSetting);
        AppSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AppSettingActivity.class);
                startActivity(it);
            }
        });
    }

    public void LogoutButton(){
        Button Login = (Button)findViewById(R.id.DoLogout);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"로그아웃 완료",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(it);
            }
        });
    }
}
