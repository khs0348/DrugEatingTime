package com.example.drugeatingtime.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugeatingtime.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("김한결");

        TimeSetting();
        DrugSearch();
        AppSetting();

        LoginButton();

    }

    public void TimeSetting() {
        TextView TimeSetting = (TextView) findViewById(R.id.GoToLogin1);
        TimeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그인 해주세요", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(it);
            }
        });
    }

    public void DrugSearch() {
        TextView DrugSearch = (TextView) findViewById(R.id.GoToLogin2);
        DrugSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그인 해주세요", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(it);
            }
        });
    }

    public void AppSetting() {
        TextView AppSetting = (TextView) findViewById(R.id.GoToLogin3);
        AppSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그인 해주세요", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(it);
            }
        });
    }

    public void LoginButton(){
        Button Login = (Button)findViewById(R.id.DoLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(it);
            }
        });
    }
}
