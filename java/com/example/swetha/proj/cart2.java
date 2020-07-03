package com.example.jahnavigottimukkala.proj;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class cart2 extends AppCompatActivity{
    SQLiteDatabase db;
    TextView t7,t20,t9;
    String name,q,cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creating database and table  

        SharedPreferences sp = getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
        name= sp.getString("name1","NA");
        q = sp.getString("pass","NA");
        cost = sp.getString("pass1","NA");
        t7 =findViewById(R.id.textView7);
        t9 =findViewById(R.id.textView9);
        t20 =findViewById(R.id.textView20);

        /*t7.setText(name);
        t9.setText(q);
        t20.setText(cost);*/

        db = openOrCreateDatabase("carts.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS carts(itemname VARCHAR,quantity VARCHAR,cost VARCHAR);");
        db.execSQL("INSERT INTO carts VALUES('" + name + "','" + q + "','" + cost + "');");

        Cursor c = db.rawQuery("SELECT * FROM carts", null);
        // Checking if no records found 
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
            return;
        }
        else {
            // Appending records to a string buffer 
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("Item Name: " + c.getString(0) + "\n");
                buffer.append("Quantity: " + c.getString(1) + "\n");
                buffer.append("Cost: " + c.getString(2) + "\n\n");
            }
            // Displaying all records 
            showMessage("My Cart ", buffer.toString());
        }


    }


    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setIcon(R.drawable.cart);
        builder.setMessage(message);
        builder.show();
    }


}

