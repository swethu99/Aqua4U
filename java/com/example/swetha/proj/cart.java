package com.example.jahnavigottimukkala.proj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class cart extends AppCompatActivity implements View.OnClickListener {
    int minteger = 0,result;
    ImageButton iv1,iv2,iv3,iv4;
    Bitmap bitmap;
    Button b1,b2,del,plus,minus;
    String s1,s2,s3;
    TextView t1,t20,t9,in;
    int total,final_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        b2=findViewById(R.id.buy);
        b2.setOnClickListener(this);
        del=findViewById(R.id.button4);
        del.setOnClickListener(this);
        t1 =findViewById(R.id.textView7);
        in =findViewById(R.id.integer_number);
        t9 =findViewById(R.id.textView9);
        t20 =findViewById(R.id.textView20);
        Bundle extras = getIntent().getExtras();
        s1=extras.getString("ss1");
        s2=extras.getString("cost");
        total=extras.getInt("oneItemPrice");
        result=extras.getInt("cost");
        //result=extras.getInt("cost");
        t1.setText(s1);
        t9.setText("Rs: "+total);
        //t20.setText(s3);
        byte[] byteArray = extras.getByteArray("clickImage");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.image);
        iv1=findViewById(R.id.ib3);
        iv1.setImageBitmap(bmp);
        plus=findViewById(R.id.buttonPlusId);
        minus=findViewById(R.id.buttonMinusId);


    }

    public void increaseInteger(View view) {
        if (t1.getText() != "Title and description") {
            minteger = minteger+ 1;
            display(minteger);
        }
    }
    public void decreaseinteger(View view) {
        minteger = minteger - 1;
        if(minteger<0){
            minteger=0;
        }
        display(minteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.integer_number);
        displayInteger.setText("" + number);
        final_total=total*minteger;
        t9.setText("Rs: "+final_total);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buy) {
            Intent intent = new Intent(getApplicationContext(), buyone.class);
            total=total/minteger;
            intent.putExtra("ss1",s1);
            intent.putExtra("cost", s2);
            intent.putExtra("oneItemPrice", final_total);
            intent.putExtra("quantity", minteger);
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
        if (view.getId() == R.id.button4)
        {
            t1.setText("Title and description");
            t9.setText("Rs: ");
            minteger=0;
            display(minteger);
            iv1.setImageBitmap(null);
        }

    }
}
