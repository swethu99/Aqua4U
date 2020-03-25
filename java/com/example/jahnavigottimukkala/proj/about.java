package com.example.jahnavigottimukkala.proj;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class about extends AppCompatActivity implements View.OnClickListener {
    ImageButton button1,button2;
    Button b;
/*
   final String no= "9440327483";
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        button1 = (ImageButton) findViewById(R.id.ib2);
        button1.setOnClickListener(this);
        button2 = (ImageButton) findViewById(R.id.ib3);
        button2.setOnClickListener(this);
        b =  findViewById(R.id.button3);
        b.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == button1.getId()) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:9440134554"));
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {

                // add the following line for runtime permission request
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        123);
                return;
            }

            startActivity(callIntent);
        }
        if (view.getId() == button2.getId()) {
/*
            String uri = "http://maps.google.com/maps?saddr=" + "9982878"+","+"76285774"+"&daddr="+"9992084"+","+"76286455";
*/
            Uri uri = Uri.parse("geo:16.611031,80.467027");
            Intent in = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(in);
        }
        if (view.getId() == b.getId()) {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
    }


     /*button1.setOnClickListener(new OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+9440327483));
            startActivity(callIntent);
        }

    }*/

  /*  @Override
    public void onClick(View view) {

    });*/
}