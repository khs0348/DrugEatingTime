package com.example.drugeatingtime.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugeatingtime.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class AppSettingActivity extends AppCompatActivity {

    public static boolean checkPush = true;
    public static Activity appSettingActivity;
    public String Usercheck = LoginActivity.Usercheck;
    public String mail = LoginActivity.idmail;

    boolean boxState;
    public CheckBox box;
    public TextView text;
    public RelativeLayout layout;
    public LinearLayout sendEamail;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appSettingActivity = AppSettingActivity.this;
        setContentView(R.layout.activity_app_setting);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        box = (CheckBox)findViewById(R.id.box);
        text = (TextView)findViewById(R.id.pushText);
        layout = (RelativeLayout)findViewById(R.id.push);
        sendEamail = (LinearLayout) findViewById(R.id.EmailSend);


        if(Usercheck.equals("1")){
            boxState = true;
            box.setChecked(boxState);
            text.setText("푸쉬알림을 받습니다.");
            checkPush = true;
            FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        }
        else{
            boxState = false;
            box.setChecked(boxState);
            text.setText("푸쉬알림을 받지 않습니다.");
            checkPush = false;
            FirebaseMessaging.getInstance().setAutoInitEnabled(false);
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box.isChecked()){
                    box.setChecked(false);
                    text.setText("푸쉬알림을 받지 않습니다.");
                    checkPush = false;
                    upDatecheck(checkPush);
                    FirebaseMessaging.getInstance().setAutoInitEnabled(false);
                }
                else{
                    box.setChecked(true);
                    text.setText("푸쉬알림을 받습니다.");
                    checkPush = true;
                    upDatecheck(checkPush);
                    FirebaseMessaging.getInstance().setAutoInitEnabled(true);
                }
            }
        });
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPush){
                    box.setChecked(false);
                    text.setText("푸쉬알림을 받지 않습니다.");
                    checkPush = false;
                    upDatecheck(checkPush);
                    FirebaseMessaging.getInstance().setAutoInitEnabled(false);
                }
                else{
                    box.setChecked(true);
                    text.setText("푸쉬알림을 받습니다.");
                    checkPush = true;
                    upDatecheck(checkPush);
                    FirebaseMessaging.getInstance().setAutoInitEnabled(true);
                }
            }
        });
        sendEamail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                Uri uri = Uri.parse("mailto:khg3782@gmail.com");
                intent.setData(uri);
                startActivity(Intent.createChooser(intent,"이메일 보내기"));
            }
        });


    }
    public void onBackPressed(){
        Log.d("finish","시발");
        appSettingActivity.finish();
    }


    public static boolean isCheckPush() {
        return checkPush;
    }

    public void upDatecheck(boolean check) {
        String checkdata;
        if(check){
            checkdata = "1";
        }else checkdata = "0";
        databaseReference.child(mail).child("check").setValue(checkdata);
    }
    public void checking(){

    }
}
