package com.example.drugeatingtime.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.drugeatingtime.Dialogdata.CustomDialog;
import com.example.drugeatingtime.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;


public class LoginActivity extends AppCompatActivity {

    public class Userdata{
        private String Email;
        private String birth;
        private String name;
        private String password;

        public Userdata(){}

        public Userdata(String password, String Email, String name, String birth){
            this.Email = Email;
            this.birth = birth;
            this.name = name;
            this.password = password;
        }


        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return Email;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private CustomDialog oDialog;
    private EditText Email_input;
    private EditText Password_input;
    private DatabaseReference databaseReference;
    private DatabaseReference db;
    private String email;

    LoginActivity m_oLoginActivity = null;

    public static Activity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginActivity = LoginActivity.this;

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        m_oLoginActivity = this;

        registration();//회원가입 클릭
        logincorrect();//로그인버튼 클릭

        Email_input = (EditText)findViewById(R.id.Email_input);
        Password_input = (EditText)findViewById(R.id.Password_input);

    }

    public void registration() { //회원가입 클릭시 커스텀 다이얼로그 창 띄우는 함수
        TextView regist = (TextView)findViewById(R.id.registration);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oDialog = new CustomDialog(m_oLoginActivity);
                oDialog.setCancelable(true);
                oDialog.show();
            }
        });
    }


    public void logincorrect() {//로그인버튼 눌렀을때 토스트메시지와 메인페이지엑티비티로 화면전환하는 함수
        final TextView login =(TextView)findViewById(R.id.Login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Email_input.getText().toString();
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), "이메일 형식을 맞춰서 입력해 주세요.", Toast.LENGTH_LONG).show();
                    return;
                } else if(Password_input.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                }else{
                    email = email.substring(0, email.lastIndexOf("."));
                    String com = Email_input.getText().toString();
                    com = com.substring(com.lastIndexOf(".") + 1);
                    email = email + com;
                    db = databaseReference.child(email);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();

                            while (i.hasNext()) {
                                if (i.next().getKey().equals(email)) {
                                    Userdata userdata = new Userdata();
                                    userdata.setPassword(dataSnapshot.child(email).child("password").getValue().toString());
                                    userdata.setEmail(dataSnapshot.child(email).child("Email").getValue().toString());
                                    if(userdata.getPassword().equals(Password_input.getText().toString())) {
                                        Toast.makeText(getApplicationContext(), "로그인!", Toast.LENGTH_LONG).show();
                                        Intent it = new Intent(getApplicationContext(), MainPageActivity.class);
                                        startActivity(it);
                                        return;
                                    }else{
                                        Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            Toast.makeText(getApplicationContext(), "존재하지 않는 아이디입니다.", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }
        });
    }
}




