package com.example.drugeatingtime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.drugeatingtime.Dialogdata.DialogCustom;
import com.example.drugeatingtime.R;


public class LoginActivity extends AppCompatActivity {

    private DialogCustom oDialog;

    LoginActivity m_oLoginActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        m_oLoginActivity = this;

        registration();//회원가입 클릭
        logincorrect();//로그인버튼 클릭

    }

    public void registration() { //회원가입 클릭시 커스텀 다이얼로그 창 띄우는 함수
        TextView regist = (TextView)findViewById(R.id.registration);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oDialog = new DialogCustom(m_oLoginActivity);
                oDialog.setCancelable(true);
                oDialog.show();
            }
        });
    }

    public void joinComplete(View view) {//회원가입 완료 버튼 누를시 토스트메시지와 커스텀다이얼로그창 닫는 함수
                Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                oDialog.dismiss();
            }

    public void logincorrect() {//로그인버튼 눌렀을때 토스트메시지와 메인페이지엑티비티로 화면전환하는 함수
        final TextView login =(TextView)findViewById(R.id.Login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그인 완료", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(),MainPageActivity.class);
                startActivity(it);

            }
        });
    }
}




