package com.example.jahnavigottimukkala.proj;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class ordersFragment extends Fragment{

    Button FilterOrder;
    String[] language = {"Waterbottle", "Water can", "Cooling Watercan", "Water Packets"};
    AutoCompleteTextView atv;
    ArrayAdapter<String> adapter;
    String name;
    int q;
    int cost;
    String pay;
    String ad,c,qu;
    SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_orders, container, false);
        /*FilterOrder = v.findViewById(R.id.b2);
        FilterOrder.setOnClickListener(this);
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_selectable_list_item, language);
        atv = (AutoCompleteTextView) v.findViewById(R.id.autocomplete);
        atv.setThreshold(1);
        atv.setAdapter(adapter);
        atv.setTextColor(Color.WHITE);
        atv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                atv.showDropDown();
                return false;
            }
        });*/

        SharedPreferences sp = getActivity().getSharedPreferences("orders",Context.MODE_PRIVATE);
        name= sp.getString("name1","NA");
        ad = sp.getString("pass","NA");
        pay = sp.getString("pass1","NA");
        q = sp.getInt("pass2", 0);
        cost = sp.getInt("pass3", 0);
        c = " "+cost;
        qu = " "+ q;


        db = getActivity().openOrCreateDatabase("orderss.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS orderss(itemname VARCHAR,quantity VARCHAR,cost VARCHAR ,pay VARCHAR,ad VARCHAR);");
        db.execSQL("INSERT INTO orderss VALUES('" + name + "','" + qu +"','" + c + "','" + pay + "','" + ad + "');");

        Cursor c = db.rawQuery("SELECT * FROM orderss", null);
        // Checking if no records found 
        // Appending records to a string buffer 
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
        }
        else {
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("Item Name: " + c.getString(0) + "\n");
                buffer.append("Quantity: " + c.getString(1) + "\n");
                buffer.append("Cost: " + c.getString(2) + "\n");
                buffer.append("payment: " + c.getString(3) + "\n");
                buffer.append("Address: " + c.getString(4) + "\n\n");
            }
            // Displaying all records 
            showMessage("My Orders ", buffer.toString());
        }

        return v;
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setIcon(R.drawable.ic_beenhere_black_24dp);
        builder.setMessage(message);
        builder.show();
    }
}
