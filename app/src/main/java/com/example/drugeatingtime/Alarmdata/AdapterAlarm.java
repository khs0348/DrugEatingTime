package com.example.drugeatingtime.Alarmdata;

import java.util.ArrayList;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.drugeatingtime.Alarmdata.LinearLayoutSingleAlarmItem.OnRemoveButtonClickListner;


public class AdapterAlarm extends BaseAdapter {

    Context mContext;
    ArrayList<String> mData;
    LayoutInflater mInflate;
    ArrayList<AlarmData> arrayListAlarmDatas;

    public AdapterAlarm(Context context, ArrayList<AlarmData> arrayListAlarmDatas) {
        mContext = context;
        this.arrayListAlarmDatas = arrayListAlarmDatas;
        mInflate = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {

        return arrayListAlarmDatas.size();
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {

        return arrayListAlarmDatas.get(position).reqCode;
    }

    public boolean removeData(int position){
        arrayListAlarmDatas.remove(position);
        notifyDataSetChanged();
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayoutSingleAlarmItem layoutSingleAlarmItem = (LinearLayoutSingleAlarmItem) convertView;

        if (layoutSingleAlarmItem == null) {
            layoutSingleAlarmItem = new LinearLayoutSingleAlarmItem(mContext);
            layoutSingleAlarmItem.setOnRemoveButtonClickListner(onRemoveButtonClickListner);
        }
        layoutSingleAlarmItem.setData(arrayListAlarmDatas.get(position), position);
        return layoutSingleAlarmItem;
    }

    OnRemoveButtonClickListner onRemoveButtonClickListner = new OnRemoveButtonClickListner() {

        @Override
        public void onClicked(int hh, int mm, int reqCode, int position) {
            String mm_precede="";
            if(mm<10){ mm_precede="0"; }else{ mm_precede=""; }
            String hh_precede="";
            if(hh<10){ hh_precede="0";}else{ hh_precede="";}
            Toast.makeText(mContext, hh_precede + hh + ":" +mm_precede+mm +" 알람이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            AlarmManager alarmManager = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(mContext, ActivityAlarmedTimeShow.class);
            // Toast.makeText(mContext, "reqCode : "+reqCode, Toast.LENGTH_SHORT).show();
            PendingIntent pi = PendingIntent.getActivity(mContext, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.cancel(pi);
            removeData(position);
        }
    };
}