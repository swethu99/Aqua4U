package com.example.jahnavigottimukkala.proj;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class orders extends AppCompatActivity implements View.OnClickListener {

    Button FilterOrder;
    String[] language = {"Waterbottle", "Water can", "Cooling Watercan", "Water Packets"};
    AutoCompleteTextView atv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        //SearchOrder=findViewById(R.id.b1);
        //SearchOrder.setOnClickListener(this);
        FilterOrder = findViewById(R.id.b2);
        FilterOrder.setOnClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, language);
        atv = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        atv.setThreshold(1);
        atv.setAdapter(adapter);
        atv.setTextColor(Color.WHITE);
        atv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                atv.showDropDown();
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b2) {
            /*Intent i = new Intent(orders.this, Main2Activity.class);
            startActivity(i);*/
        }
    }
}
