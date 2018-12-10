package com.example.drugeatingtime.Alarmdata;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.drugeatingtime.Activity.MainPageActivity;
import com.example.drugeatingtime.R;

public class TimeSetActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private Context mContext;
    public static final int DEFAULT_ALARM_REQUEST = 800;
    public static Activity TimesetActivity;

    TimePicker timePickerAlarmTime;
    Button btnAddAlarm;
    EditText DrugName;
    ListView listViewAlarm;
    ArrayList<AlarmData> arrayListAlarmTimeItem = new ArrayList<AlarmData>();

    GregorianCalendar currentCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedEditor;

    AdapterAlarm arrayAdapterAlarmList;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_setting);


        TimesetActivity = TimeSetActivity.this;
        btnCancel();

        mContext = getApplicationContext();

        timePickerAlarmTime = (TimePicker)findViewById(R.id.timePickerAlarmTime);
        btnAddAlarm = (Button)findViewById(R.id.btnAddAlarm);
        DrugName =(EditText)findViewById(R.id.DrugName);
        listViewAlarm	= (ListView)findViewById(R.id.listViewAlarm);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        sharedEditor = sharedPref.edit();

        timePickerAlarmTime.setIs24HourView(false);
        arrayAdapterAlarmList = new AdapterAlarm(mContext, arrayListAlarmTimeItem);
        listViewAlarm.setAdapter(arrayAdapterAlarmList);

        alarmManager = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setTimeZone("");


        btnAddAlarm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                int hh = timePickerAlarmTime.getCurrentHour();
                int mm = timePickerAlarmTime.getCurrentMinute();
                int reqCode = DEFAULT_ALARM_REQUEST+arrayListAlarmTimeItem.size();
                int i =arrayListAlarmTimeItem.size();

                String name =DrugName.getText().toString();

                String hh_precede="";
                if(hh<10){ hh_precede="0";}

                String mm_precede="";
                if(mm<10){ mm_precede ="0"; }

                if(DrugName.getText().toString().replace(" ","").equals("")){
                    Toast.makeText(getBaseContext(),"약품명을 입력해주세요.",Toast.LENGTH_SHORT).show();
                }else {
                    arrayListAlarmTimeItem.add(new AlarmData(hh_precede,hh, mm, mm_precede, reqCode, name));
                    arrayAdapterAlarmList.notifyDataSetChanged();

                    sharedEditor.putInt("list" + i + "hh", hh);
                    sharedEditor.putString("list" + i + "hh_precede", hh_precede);
                    sharedEditor.putInt("list" + i + "mm", mm);
                    sharedEditor.putString("list" + i + "mm_precede", mm_precede);
                    sharedEditor.putInt("list" + i + "reqCode", reqCode);
                    sharedEditor.putString("list" + i + "name", name);
                    sharedEditor.putInt("size", i);
                    sharedEditor.commit();

                    GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));

                    int currentYY = currentCalendar.get(Calendar.YEAR);
                    int currentMM = currentCalendar.get(Calendar.MONTH);
                    int currentDD = currentCalendar.get(Calendar.DAY_OF_MONTH);

                    gregorianCalendar.set(currentYY, currentMM, currentDD, hh, mm, 00);

                    if (gregorianCalendar.getTimeInMillis() < currentCalendar.getTimeInMillis()) {
                        gregorianCalendar.set(currentYY, currentMM, currentDD + 1, hh, mm, 00);
                        Log.i("TAG", gregorianCalendar.getTimeInMillis() + ":");
                    }


                    Intent intent = new Intent(TimeSetActivity.this, ActivityAlarmedTimeShow.class);
                    intent.putExtra("alarm", "알람");
                    intent.putExtra("time", hh + ":" + mm);
                    intent.putExtra("data", currentCalendar.getTime().toGMTString());
                    intent.putExtra("reqCode", reqCode);
                    intent.putExtra("name", name);

                    Toast.makeText(mContext, name + " " + "알람 추가!", Toast.LENGTH_SHORT).show();
                    PendingIntent pi = PendingIntent.getActivity(TimeSetActivity.this, reqCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, gregorianCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);

                    DrugName.setText("");
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        arrayListAlarmTimeItem.clear();
        int size = sharedPref.getInt("size", 0);
        if(size !=0)
            for(int i = 0 ; i < size+1; i ++ ){
                int hh = sharedPref.getInt("list"+i+"hh", 0);
                String hh_precede = sharedPref.getString("list"+i+"hh_precede","");
                int mm = sharedPref.getInt("list"+i+"mm", 0);
                String mm_precede = sharedPref.getString("list"+i+"mm_precede","");
                int reqCode = sharedPref.getInt("list"+i+"reqCode", 0);
                String name = sharedPref.getString("list"+i+"name","");

                arrayListAlarmTimeItem.add(new AlarmData(hh_precede,hh, mm,mm_precede ,reqCode,name));
            }
        arrayAdapterAlarmList.notifyDataSetChanged();
    }

    public void btnCancel() {
        Button btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(getApplicationContext(),MainPageActivity.class);
                startActivity(it);
                TimesetActivity.finish();
            }
        });
    }
}