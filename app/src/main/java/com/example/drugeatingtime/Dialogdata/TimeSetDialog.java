package com.example.drugeatingtime.Dialogdata;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.example.drugeatingtime.R;

public class TimeSetDialog extends Dialog {

    public TimeSetDialog(Context context){
        super(context,android.R.style.Theme_Translucent_NoTitleBar);
    }
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_time_setting);

    }

}
