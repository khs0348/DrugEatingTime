package com.example.drugeatingtime.Alarmdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Size;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugeatingtime.Activity.MainPageActivity;
import com.example.drugeatingtime.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityAlarmedTimeShow extends Activity {

    public static Activity AlarmedTimeShowActivity;

    TextView textViewAlarmedTime;
    TextView textViewAlarmedData;
    TextView textViewDrugname;
    TextView textViewDate;
    long mNow;
    Date mDate;
    //SimpleDateFormat mFormat =new SimpleDateFormat("yyyy/MM/dd \nHH:mm");
    SimpleDateFormat mFormat =new SimpleDateFormat("HH:mm");
    SimpleDateFormat mFormat2 =new SimpleDateFormat("MMM dd일 E요일");
    MediaPlayer mediaPlayer;
    Vibrator mVibe;

    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_time_show);

        AlarmedTimeShowActivity =ActivityAlarmedTimeShow.this;

        mediaPlayer = MediaPlayer.create(this,R.raw.call);

        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern ={0,2500,2700};
        mVibe.vibrate(pattern,0);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        |WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
        |WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        |WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE,"hello:");
        wakeLock.acquire();


        BtnAlarmRelieve();

        textViewAlarmedTime = (TextView)findViewById(R.id.textViewAlarmedTime);
        Intent intent = getIntent();
        String alarm = intent.getStringExtra("alarm");
        textViewAlarmedTime.setText(alarm);

        textViewDrugname = (TextView)findViewById(R.id.textViewDrugName);
        String name = intent.getStringExtra("name");
        textViewDrugname.setText(name);

        textViewAlarmedData =(TextView)findViewById(R.id.textViewAlarmedData);
        textViewAlarmedData.setText(getTime());

        textViewDate =(TextView)findViewById(R.id.textViewDate);
        textViewDate.setText(getTime2());

    }
    public void BtnAlarmRelieve() {
        Button btnAlarmRelieve = (Button) findViewById(R.id.AlarmRelieve);
        btnAlarmRelieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlarmedTimeShowActivity.finish();
                mediaPlayer.setLooping(false);
                mediaPlayer.stop();
                mVibe.cancel();
                wakeLock.release();
            }
        });
    }

    public void onBackPressed(){
       mediaPlayer.stop();
       mediaPlayer.setLooping(false);
       AlarmedTimeShowActivity.finish();
       mVibe.cancel();
       wakeLock.release();
    }

    @Override
    public void onPause(){
        super.onPause();

    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    private String getTime2(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat2.format(mDate);
    }
}
