package com.example.jahnavigottimukkala.proj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class buyone extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView t1,t2,t3,t4,t12,setPrice,setTotal,displayInteger;
    Button browseButton,plus,minus;
    String msg,u;
    RadioGroup rg1;
    EditText ed;
    RadioButton rb1,rb2;
    String s1,add,cost;
    int quantityCounter=0,quantity;
    int total,final_total,c;
    RelativeLayout rlayout;
    //ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyone);
        rlayout=findViewById(R.id.buy_one_layout_id);
        browseButton = findViewById(R.id.button);
        ed = (EditText) findViewById(R.id.editText);
        t1 =findViewById(R.id.textView1);
        t2 =findViewById(R.id.textView2);
        t3 =findViewById(R.id.textView3);
        t4 =findViewById(R.id.textView4);
        t12=findViewById(R.id.textView12);
       // t14=findViewById(R.id.textView14);
        setPrice=findViewById(R.id.textViewSetPriceId);
        displayInteger = (TextView) findViewById(R.id.textViewQuantityId);
        setTotal=findViewById(R.id.textViewsetTotalId);
        rg1=findViewById(R.id.RadioGroup1);
        rb1=findViewById(R.id.radioButton1);
        rb2=findViewById(R.id.radioButton2);
        rg1.setOnCheckedChangeListener(this);
        browseButton.setOnClickListener(this);
       plus=findViewById(R.id.buttonPlusId);
       minus=findViewById(R.id.buttonMinusId);
       //quantity=findViewById(R.id.textViewQuantityId);

        Bundle extras = getIntent().getExtras();
        s1=extras.getString("ss1");
        //c=extras.getInt("cost");
        cost=extras.getString("cost");
        total=extras.getInt("oneItemPrice");
        quantity=extras.getInt("quantity");
        t12.setText(s1);
        quantityCounter=quantity;
        //total=cos*quantityCounter
        //t14.setText(cost*quantityCounter);
        setPrice.setText(" "+cost);
        setTotal.setText("Rs:"+total);
        displayInteger.setText(""+quantity);
        //setTotal.setText(cost);
       // final_total=total*quantityCounter;

        // setTotal.setText(total*quantityCounter);
        /*byte[] byteArray = extras.getByteArray("clickImage");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.image);
        iv=findViewById(R.id.image);
        iv.setImageBitmap(bmp);*/

       // setTotal.setText(""+final_total);
    }
@Override
    public void onClick(View view) {
        if (msg == "Pay on Delivery" || msg == "Net Banking") {
            Intent i = new Intent(getApplicationContext(), buy2.class);
            add = ed.getText().toString().trim();
            if (!add.isEmpty()) {
                i.putExtra("payment", msg);
                i.putExtra("s1", s1);
                i.putExtra("add", add);
                i.putExtra("cost", final_total);
                i.putExtra("quantity", quantityCounter);
                startActivity(i);
            }
            else {
                Snackbar sbar=Snackbar.make(rlayout,"Please Enter address!!",Snackbar.LENGTH_LONG);
                View sbView=sbar.getView();
                sbView.setBackgroundColor(Color.parseColor("#77b5fe"));
                sbar.show();
            }
        }
        else{
            //Toast.makeText(this,"Please select the payment type!",Toast.LENGTH_SHORT).show();
            Snackbar sbar=Snackbar.make(rlayout,"Please select the payment method!!",Snackbar.LENGTH_LONG);
            View sbView=sbar.getView();
            sbView.setBackgroundColor(Color.parseColor("#77b5fe"));

            sbar.show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        //RadioButton rb = (RadioButton) findViewById(i);
        String url;
        //Toast.makeText(getApplicationContext(),rb.getText(),Toast.LENGTH_SHORT).show();
        if(i == R.id.radioButton1)
        {
            msg="Pay on Delivery";

        }
        else if (i == R.id.radioButton2)
        {
            u = "http://retail.onlinesbi.com/retail/login.htm";
            url = u;
            msg="Net Banking";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        }
    }
    public void increaseValue(View view) {

            quantityCounter = quantityCounter+ 1;
            display(quantityCounter);

    }
    public void decreaseValue(View view) {

            quantityCounter = quantityCounter - 1;
            if(quantityCounter<0){
                quantityCounter=0;
            }
            display(quantityCounter);

    }

    private void display(int number) {
        displayInteger.setText("" + number);
        final_total=total*quantityCounter;
        setTotal.setText("Rs."+final_total);

    }

}

