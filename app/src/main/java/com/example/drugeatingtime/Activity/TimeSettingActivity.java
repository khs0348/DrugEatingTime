package com.example.drugeatingtime.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.drugeatingtime.Dialogdata.TimeSetDialog;
import com.example.drugeatingtime.R;

public class TimeSettingActivity extends AppCompatActivity {

    private TimeSetDialog sDialog;
    TimeSettingActivity m_sTimeSettingActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set_main);

        m_sTimeSettingActivity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

            sDialog = new TimeSetDialog(m_sTimeSettingActivity);
            sDialog.setCancelable(true);
            sDialog.show();

            return true;
        }

        if (id == R.id.action_settings_deletion){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
