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
    public boolean loginON =false;


    LoginActivity m_oLoginActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        m_oLoginActivity = this;

        registration();//회원가입 클릭

        logincorrect();//로그인버튼 클릭

    }

    public void registration() {
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

    public void joinComplete(View view) {
                Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                oDialog.dismiss();
            }

    public void logincorrect() {
        final TextView login =(TextView)findViewById(R.id.Login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(it);
            }
        });

    }
}




