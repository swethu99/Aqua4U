package com.example.jahnavigottimukkala.proj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText mTextUsername,mTextPasssword;
    TextView mTextViewRegister;
    Button mButtonLogin;
    DatabaseHelper db;
    LinearLayout llayout;

    public static class MyStaticClass {
        public static String value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        llayout=findViewById(R.id.login_layout_id);
        db=new DatabaseHelper(this);
        mTextUsername=findViewById(R.id.editText_username);
        mTextPasssword=findViewById(R.id.editText_password);
        mButtonLogin=findViewById(R.id.button_login);
        mTextViewRegister=findViewById(R.id.textView_register);
        //login Button click
        mTextViewRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(login.this,register.class);

                        startActivity(intent);
                    }
                });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String name=mTextUsername.getText().toString();

                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPasssword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if (!user.isEmpty() && !pwd.isEmpty()) {
                    if (res == true) {
                        MyStaticClass.value = user;
                        String s = MyStaticClass.value;
                        Intent i = new Intent(login.this, MainActivity.class);
                        //i.putExtra("user",s);
                        startActivity(i);
                    } else {
                        Snackbar sbar = Snackbar.make(llayout, "Login Error!!", Snackbar.LENGTH_LONG);
                        View sbView = sbar.getView();
                        sbView.setBackgroundColor(Color.parseColor("#77b5fe"));
                        sbar.show();
                    }
                }
                else {
                    Snackbar sbar = Snackbar.make(llayout, "Please Enter all the fields!!", Snackbar.LENGTH_LONG);
                    View sbView = sbar.getView();
                    sbView.setBackgroundColor(Color.parseColor("#77b5fe"));
                    sbar.show();
                }
            }
        });
    }

}

