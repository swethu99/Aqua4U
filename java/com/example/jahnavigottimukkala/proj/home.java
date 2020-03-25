package com.example.jahnavigottimukkala.proj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;

public class home extends AppCompatActivity{
    ImageButton iv1,iv2,iv3,iv4;
    Bitmap bitmap;

    TextView tv2,tv,tv3,tv4,tv20,tv21,tv23,tv25,tv26,tv29,tv31,tv32,t;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        t=findViewById(R.id.user);
        Bundle b=getIntent().getExtras();
        String s = login.MyStaticClass.value;
        //String name=b.getString("user");
        t.setText(s);
        iv1=findViewById(R.id.water_bottle);
        iv2=findViewById(R.id.water_packet);
        iv3=findViewById(R.id.water_can);
        iv4=findViewById(R.id.twoBottle);
        //TextViews
        tv2=findViewById(R.id.textView2);
        tv=findViewById(R.id.textView);
        tv3=findViewById(R.id.textView3);
        tv4=findViewById(R.id.textView4);
        tv20=findViewById(R.id.textView20);
        tv21=findViewById(R.id.textView21);
        tv23=findViewById(R.id.textView23);
        tv25=findViewById(R.id.textView25);
        tv26=findViewById(R.id.textView26);
        tv29=findViewById(R.id.textView29);
        tv31=findViewById(R.id.textView31);
        tv32=findViewById(R.id.textView32);


        ///clivking images
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String t2=tv2.getText().toString().trim();
                String t=tv.getText().toString().trim();
                String t3=tv3.getText().toString().trim();
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.waterbottle);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Intent intent=new Intent(getApplicationContext(),third.class);
                intent.putExtra("item_name",t2);
                intent.putExtra("item_quantity",t);
                intent.putExtra("item_cost",t3);
                intent.putExtra("clickImage", byteArray);
                intent.putExtra("oneItemPrice",5);
                startActivity(intent);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                /*String t20=tv20.getText().toString().trim();
                String t21=tv21.getText().toString().trim();
                String t4=tv4.getText().toString().trim();*/
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.packet);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Intent intent=new Intent(getApplicationContext(),third.class);
                /*intent.putExtra("t4",t4);
                intent.putExtra("t20",t20);
                intent.putExtra("t21",t21);*/
                intent.putExtra("clickImage", byteArray);
                intent.putExtra("item_name","WATER PACKET SAC");
                intent.putExtra("item_quantity","100 Packets");
                intent.putExtra("item_cost","Rs.100");
                intent.putExtra("oneItemPrice",100);
                startActivity(intent);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.watercan);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Intent intent=new Intent(getApplicationContext(),third.class);
                intent.putExtra("item_name","WATER CAN");
                intent.putExtra("item_quantity","20 Liters");
                intent.putExtra("item_cost","Rs.20");
                intent.putExtra("clickImage", byteArray);
                intent.putExtra("oneItemPrice",20);
                startActivity(intent);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.two_bottle);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Intent intent=new Intent(getApplicationContext(),third.class);
                intent.putExtra("item_name","COOLING WATER CAN");
                intent.putExtra("item_quantity","20 Liters");
                intent.putExtra("item_cost","Rs.45");
                intent.putExtra("clickImage", byteArray);
                intent.putExtra("oneItemPrice",45);
                startActivity(intent);
            }
        });



    }

}

