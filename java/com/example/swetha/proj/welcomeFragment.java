package com.example.jahnavigottimukkala.proj;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class welcomeFragment extends Fragment implements View.OnClickListener{
    Button b1,b2;
    ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_welcome, container, false);
        b1 = v.findViewById(R.id.button);
        b1.setOnClickListener(this);
        b2 = v.findViewById(R.id.button7);
        b2.setOnClickListener(this);
        img=v.findViewById(R.id.iv1);
        Animation animation3=AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.blink_rotate);
        img.startAnimation(animation3);
        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            //Toast.makeText(this,"Welcome", Toast.LENGTH_LONG).show();
            Intent i=new Intent(getActivity().getApplicationContext(),login.class);
            startActivity(i);
        }
    }
}
