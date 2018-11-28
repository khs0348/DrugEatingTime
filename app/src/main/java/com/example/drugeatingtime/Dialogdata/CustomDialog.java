package com.example.drugeatingtime.Dialogdata;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.drugeatingtime.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;


public class CustomDialog extends Dialog{

    private Button join;
    private EditText Email_make;
    private EditText Password_make;
    private EditText Password_make2;
    private EditText Name;
    private EditText Birth;
    private DatabaseReference databaseReference;

    public CustomDialog(Context context){
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
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        Email_make = (EditText)findViewById(R.id.EmailMake);
        Password_make = (EditText)findViewById(R.id.PasswordMake);
        Password_make2 = (EditText)findViewById(R.id.PasswordMake2);
        Name = (EditText)findViewById(R.id.UserName);
        Birth = (EditText)findViewById(R.id.UserBirth);

        joinComplete();
    }

    private ValueEventListener checkRegister = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
            while (child.hasNext()) {
                String mail = Email_make.getText().toString();
                mail = mail.substring(0,mail.lastIndexOf("."));
                String adress = Email_make.getText().toString();
                adress = adress.substring(adress.lastIndexOf(".")+1);
                mail = mail +adress;
                if (mail.equals(child.next().getKey())) {
                    Email_make.setError("중복된 이메일 입니다.");
                    databaseReference.removeEventListener(this);
                    return;
                }
                if (!validateForm()) {
                    databaseReference.removeEventListener(this);
                    return;
                }
            }
            makeNewId();
            dismiss();

        }

        void makeNewId()
        {
            String mail = Email_make.getText().toString();
            mail = mail.substring(0,mail.lastIndexOf("."));
            String com = Email_make.getText().toString();
            com = com.substring(com.lastIndexOf(".")+1);
            mail = mail + com;
            databaseReference.child(mail).child("password").setValue(Password_make.getText().toString());
            databaseReference.child(mail).child("birth").setValue(Birth.getText().toString());
            databaseReference.child(mail).child("name").setValue(Name.getText().toString());
            databaseReference.child(mail).child("Email").setValue(Email_make.getText().toString());
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };

    public void joinComplete(){
        join = (Button)findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email_make.getText().toString();
                String pass = Password_make.getText().toString();
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Email_make.setError("이메일 형식에 맞게 입력하세요");
                    return;
                } else {
                    databaseReference.addListenerForSingleValueEvent(checkRegister);
                }
                };

        });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = Email_make.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Email_make.setError("다시 입력하세요.");
            valid = false;
        } else {
            Email_make.setError(null);
        }

        String password = Password_make.getText().toString();
        String password2 = Password_make2.getText().toString();

        if(TextUtils.isEmpty(password) || TextUtils.isEmpty((password2))) {
            Password_make.setError("다시 입력하세요.");
            Password_make2.setError("다시 입력하세요.");
            valid = false;
        }
        if (Password_make.getText().toString().equals(Password_make2.getText().toString())) {
            Password_make.setError(null);
        } else {
            Password_make.setError("비밀번호가 다릅니다.");
            valid = false;

        }

        String name = Name.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Name.setError("다시 입력하세요.");
            valid = false;
        } else {
            Name.setError(null);
        }

        String birth = Birth.getText().toString();
        if (TextUtils.isEmpty(birth)) {
            Birth.setError("다시 입력하세요.");
            valid = false;
        } else {
            Birth.setError(null);
        }

        return valid;
    }

}