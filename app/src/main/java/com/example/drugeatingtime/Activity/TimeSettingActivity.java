package com.example.drugeatingtime.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.drugeatingtime.Alarmdata.AdapterAlarm;
import com.example.drugeatingtime.Alarmdata.TimeSetActivity;
import com.example.drugeatingtime.R;


public class TimeSettingActivity extends AppCompatActivity {

    ListView listViewAlarm;
    AdapterAlarm arrayAdapterAlarmList;

    TimeSettingActivity m_sTimeSettingActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set_main);

        m_sTimeSettingActivity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewAlarm	= (ListView)findViewById(R.id.listViewAlarm);
        listViewAlarm.setAdapter(arrayAdapterAlarmList);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_time_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings_addition) {

            Intent it = new Intent(getApplicationContext(), TimeSetActivity.class);
            startActivity(it);

            return true;
        }

        if (id == R.id.action_settings_deletion){


            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
