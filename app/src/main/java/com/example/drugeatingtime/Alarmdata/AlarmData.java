package com.example.drugeatingtime.Alarmdata;


public class AlarmData {
    public int hh;
    public int mm;
    public int reqCode;
    public String name;

    public AlarmData(int hh, int mm, int reqCode,String name) {
        this.hh = hh;
        this.mm = mm;
        this. reqCode = reqCode;
        this.name =name;
    }

    @Override
    public String toString() {
        return hh+":"+mm +" and requestCode : "+reqCode;
    }


}

