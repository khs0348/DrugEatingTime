package com.example.drugeatingtime.Alarmdata;


public class AlarmData {
    public int hh;
    public String hh_precede;
    public int mm;
    public String mm_precede;
    public int reqCode;
    public String name;

    public AlarmData(String hh_precede,int hh,int mm, String mm_precede,int reqCode,String name) {
        this.hh = hh;
        if(hh<10) { this.hh_precede = "0"; } else {this.hh_precede ="";}
        this.mm = mm;
        if(mm<10) { this.mm_precede = "0"; }else{this.mm_precede = "";}
        this.name =name;
    }

    @Override
    public String toString() {
        return hh_precede + hh+":"+mm_precede+mm +" and requestCode : "+reqCode;
    }


}

