package com.example.drugeatingtime.Activity;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.drugeatingtime.Dialogdata.DialogCustom;
import com.example.drugeatingtime.R;


public class LoginActivity extends AppCompatActivity  {

    LoginActivity m_oLoginActivity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        m_oLoginActivity = this;

        TextView regist =(TextView) findViewById(R.id.registration);

        regist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogCustom oDialog = new DialogCustom(m_oLoginActivity);
                oDialog.setCancelable(true);
                oDialog.show();
            }
        });
    }
}

