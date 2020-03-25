package com.example.jahnavigottimukkala.proj;


import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;


public class rateFragment extends Fragment {

    LinearLayout llayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rate, container, false);
        //llayout= v.findViewById(R.id.rate);
        final RatingBar simpleRatingBar = (RatingBar) v.findViewById(R.id.simpleRatingBar);
        Button submitButton = (Button) v.findViewById(R.id.submitButton);
        // perform click event on button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                String totalStars = "Total Stars:: " + simpleRatingBar.getNumStars();
                String rating = "Rating :: " + simpleRatingBar.getRating();
                Snackbar sbar = Snackbar.make(getActivity().findViewById(R.id.rate), "Total Stars:: " + simpleRatingBar.getNumStars() + "\nRating :: " + simpleRatingBar.getRating(), Snackbar.LENGTH_LONG);
                View sbView = sbar.getView();
                sbView.setBackgroundColor(Color.parseColor("#77b5fe"));
                sbar.show();
                String no="9440134554";
                Intent intent=new Intent(getActivity().getApplicationContext(),homeFragment.class);
                PendingIntent pi=PendingIntent.getActivity(getActivity().getApplicationContext(), 0, intent,0);
//Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(no, null, totalStars +"\n"+rating, pi,null);

                homeFragment fragment = new homeFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout, fragment, "Home");
                ft.commit();



            }
        });
        return v;
    }

}
