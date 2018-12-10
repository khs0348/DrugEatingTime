package com.example.drugeatingtime.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugeatingtime.Alarmdata.TimeSetActivity;
import com.example.drugeatingtime.R;

public class MainPageActivity extends AppCompatActivity {
    private Toast mToast;
    public static Activity loginonActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loginon);
        loginonActivity = MainPageActivity.this;

        TimeSetting();
        DrugSearch();
        AppSetting();

        LogoutButton();
    }

    public void onBackPressed(){
        if(mToast==null){
            mToast =Toast.makeText(getApplicationContext(),"하단의 로그아웃 버튼을 눌러주세요.",Toast.LENGTH_SHORT);
        }else {
            mToast.setText("하단의 로그아웃 버튼을 눌러주세요.");
        }
        mToast.show();
    }

    public void TimeSetting() {
        TextView TimeSetting = (TextView) findViewById(R.id.TimeSetting);
        TimeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), TimeSetActivity.class);
                startActivity(it);
            }
        });
    }

    public void DrugSearch() {
        TextView DrugSearch = (TextView) findViewById(R.id.DrugSearch);
        DrugSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id =v.getId();
                TextView layout =(TextView)findViewById(id);
                String tag =(String)layout.getTag();

                Intent it = new Intent(getApplicationContext(), DrugSearchActivity.class);
                it.putExtra("it_tag",tag);
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
                Intent it = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(it);

                loginonActivity.finish();// 로그아웃 누를시 MainPage 엑티비티를 종료한다

                LoginActivity loginActivity =(LoginActivity)LoginActivity.loginActivity; // 로그아웃 누를시 로그인 엑티비티를 종료한다.
                loginActivity.finish();
            }
        });
    }
}
