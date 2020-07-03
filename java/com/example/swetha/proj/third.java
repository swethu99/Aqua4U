package com.example.jahnavigottimukkala.proj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.opengl.Matrix;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class third extends AppCompatActivity implements View.OnClickListener {
    ImageView iv;int val;
    Bitmap bmp;
    TextView Onespec1,Onespec2,Onespec3,Twospec1,Twospec2,Twospec3;
    Button b1,b2;
    ImageButton iv1,iv2,iv3,iv4;
    String s1,s2,s3;int s4;
    byte[] byteArray;
    RelativeLayout llyout;

    DatabaseHelper db;

    //sp
   // Button viewcart;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //viewcart=findViewById(R.id.button6);
        // spec1=findViewById(R.id.textView5);
        llyout = findViewById(R.id.third);
       // db = openOrCreateDatabase("register.db", Context.MODE_PRIVATE, null);
        db=new DatabaseHelper(this);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        iv1=findViewById(R.id.water_bottle);
        iv2=findViewById(R.id.water_packet);
        iv3=findViewById(R.id.water_can);
        iv4=findViewById(R.id.twoBottle);
        Onespec1=findViewById(R.id.textView5);
        Onespec2=findViewById(R.id.textView10);
        Onespec3=findViewById(R.id.textView11);
        /*Twospec1=findViewById(R.id.textView5);
        Twospec2=findViewById(R.id.textView10);
        Twospec3=findViewById(R.id.textView11);*/
        Bundle extras = getIntent().getExtras();
        s1=extras.getString("item_name");
        s2=extras.getString("item_quantity");
        s3=extras.getString("item_cost");
        s4=extras.getInt("oneItemPrice");
        val=s4;

        /*s4=extras.getString("t4");
        String s5=extras.getString("t20");
        String s6=extras.getString("t21");*/
        byteArray = extras.getByteArray("clickImage");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.image);
        iv=findViewById(R.id.image);
        iv.setImageBitmap(bmp);
        Onespec1.setText(s1);
        Onespec2.setText(s2);
        Onespec3.setText(s3);
        /*Onespec1.setText(s4);
        Onespec2.setText(s2);
        Onespec3.setText(s3);*/


    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.button1) {
            Intent intent = new Intent(getApplicationContext(), buyone.class);
            intent.putExtra("ss1",s1);
            intent.putExtra("cost",s3);
            intent.putExtra("oneItemPrice",val);
            startActivity(intent);
            /*Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.waterbottle);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Intent intent=new Intent(getApplicationContext(),buyone.class);
            //intent.putExtra("t1",t1);
            intent.putExtra("clickImage", byteArray);
            startActivity(intent);*/
        }
        if(view.getId()==R.id.button2) {
            saveData();
            //Toast.makeText(this,"Added To Cart!!",Toast.LENGTH_LONG).show();
            Snackbar sbar = Snackbar.make(llyout, "Added To Cart!!", Snackbar.LENGTH_LONG);
            View sbView = sbar.getView();
            sbView.setBackgroundColor(Color.parseColor("#77b5fe"));
            sbar.show();
        }

           // Toast.makeText(this,"Data Saved in Shared Preference", Toast.LENGTH_SHORT).show();


            /*i.putExtra("ss1",s1);
            i.putExtra("cost",s3);
            i.putExtra("clickImage", byteArray);
            i.putExtra("oneItemPrice",val);*/




}
    public void saveData(){
        SharedPreferences sp = getSharedPreferences
                ("mycredentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("name1",Onespec1.getText().toString());
        edit.putString("pass",Onespec2.getText().toString());
        edit.putString("pass1",Onespec3.getText().toString());
        edit.commit();
        //Toast.makeText(this,"Data Saved in Shared Preference", Toast.LENGTH_SHORT).show();
    }


}
