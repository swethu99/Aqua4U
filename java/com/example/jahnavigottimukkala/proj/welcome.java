package com.example.jahnavigottimukkala.proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class welcome extends AppCompatActivity implements View.OnClickListener{
    Button b1,b2;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        b1 =findViewById(R.id.button);
        b1.setOnClickListener(this);
        b2 =findViewById(R.id.button7);
        b2.setOnClickListener(this);
        img=findViewById(R.id.iv1);
        Animation animation3=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink_rotate);
        img.startAnimation(animation3);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            //Toast.makeText(this,"Welcome", Toast.LENGTH_LONG).show();
            Intent i=new Intent(getApplicationContext(),login.class);
            startActivity(i);
        }
    }
}
