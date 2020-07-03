package com.example.jahnavigottimukkala.proj;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class aboutFragment extends Fragment implements View.OnClickListener {
    ImageButton button1,button2;
    Button b;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        button1 = (ImageButton) v.findViewById(R.id.ib2);
        button1.setOnClickListener(this);
        button2 = (ImageButton) v.findViewById(R.id.ib3);
        button2.setOnClickListener((View.OnClickListener) this);
        b =  v.findViewById(R.id.button3);
        b.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == button1.getId()) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:9440134554"));
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {

                // add the following line for runtime permission request
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        123);
                return;
            }

            startActivity(callIntent);
        }
        if (view.getId() == button2.getId()) {

            //String uri = "http://maps.google.com/maps?saddr=" + "9982878"+","+"76285774"+"&daddr="+"9992084"+","+"76286455";

            Uri uri = Uri.parse("geo:0,0?q=16.611031,80.467027(Google+mulapadu)");

            Intent in = new Intent(Intent.ACTION_VIEW, uri);
in.setPackage("com.google.android.apps.maps");
            startActivity(in);
        }
        if (view.getId() == b.getId()) {
            Intent i=new Intent(getActivity(),MainActivity.class);
            startActivity(i);
        }
    }

}
