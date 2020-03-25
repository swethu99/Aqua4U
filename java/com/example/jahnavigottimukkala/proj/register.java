package com.example.jahnavigottimukkala.proj;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class register extends AppCompatActivity {
    EditText mTextUsername,mTextPasssword,getTextConfirmPassword;
    TextView mTextViewLogin;
    Button mButtonRegister;
    DatabaseHelper db;
    LinearLayout llayout;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        llayout=findViewById(R.id.register_layout_id);
        db=new DatabaseHelper(this);
        setContentView(R.layout.activity_register);
        mTextUsername=findViewById(R.id.editText_username);
        mTextPasssword=findViewById(R.id.editText_password);
        getTextConfirmPassword=findViewById(R.id.editText_confirm_password);
        mButtonRegister=findViewById(R.id.button_register);
        mTextViewLogin=findViewById(R.id.textView_register);
        //firebase

        //login Button click
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=mTextUsername.getText().toString().trim();
                String pwd=mTextPasssword.getText().toString().trim();
                String cnf_pwd=getTextConfirmPassword.getText().toString().trim();
                if (!user.isEmpty() && !pwd.isEmpty() && !cnf_pwd.isEmpty()){
                    long val=db.addUser(user,pwd);
                    if(pwd.equals(cnf_pwd) && val>0) {
                        Toast.makeText(getApplicationContext(),"you have successfully registerd",Toast.LENGTH_LONG).show();
                        Intent moveToLogin;
                        moveToLogin = new Intent(register.this, login.class);
                        startActivity(moveToLogin);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password no match",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter all details",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

/* //test
package com.example.jahnavigottimukkala.proj;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignUp = findViewById(R.id.button2);
        tvSignIn = findViewById(R.id.textView);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                FirebaseAuthException e = (FirebaseAuthException )task.getException();

                                Toast.makeText(MainActivity.this,"Reg error",Toast.LENGTH_LONG).show();
                            }
                            else{
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LOginActivity.class);
                startActivity(i);
            }
        });
    }
}*/

