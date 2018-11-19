package com.example.drugeatingtime.Dialogdata;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.drugeatingtime.R;



public class DialogCustom extends Dialog{


    public DialogCustom(Context context){
        super(context,android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags =WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount=0.3f;
        getWindow().setAttributes(lpWindow);
        setContentView(R.layout.dialog_custom);

    }

}