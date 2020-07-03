package com.example.jahnavigottimukkala.proj;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class buy2 extends AppCompatActivity implements View.OnClickListener {
    TextView t1,t13,t,t15,t16;
    String payment,name,add,s;
    Button browseButton;
    NotificationManager manager;
    Notification myNotification;
    int notifyID =1;
    int cost,quantity;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy2);
        Bundle b=getIntent().getExtras();
        s = login.MyStaticClass.value;
        name=b.getString("s1");
        payment=b.getString("payment");
        add=b.getString("add");
        cost=b.getInt("cost");
        quantity=b.getInt("quantity");
        //s.toUpperCase();
        t1=(TextView)findViewById(R.id.textView6);
        t=(TextView)findViewById(R.id.textView8);
        t13=(TextView)findViewById(R.id.textView13);
        t15=(TextView)findViewById(R.id.textView15);
        t16=(TextView)findViewById(R.id.textView11);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        t1.setText(payment);
        t13.setText(name);
        t.setText(add);
        t15.setText("Rs."+cost);
        t16.setText(" "+quantity);
        browseButton = (Button) findViewById(R.id.button);
        browseButton.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channel_Id = "my_channel_01";// The id of the channel.
            CharSequence channelName = "NotifChannel";// The user-visible name of the channel.
            int channelImportance = NotificationManager.IMPORTANCE_HIGH;

            manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // Create a notification and set the notification channel.
            NotificationChannel channel = new NotificationChannel(channel_Id, channelName,
                    channelImportance);
            manager.createNotificationChannel(channel);

            //Create the intent that’ll fire when the user taps the notification//
            //Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
            //PendingIntent.FLAG_UPDATE_CURRENT - Flag indicating that if the described PendingIntent already exists,
            // then keep it but replace its extra data with what is in this new Intent.
            //PendingIntent pendingIntent = PendingIntent.getActivity(buy2.this, 1,i, PendingIntent.FLAG_UPDATE_CURRENT);

            myNotification  = new Notification.Builder(buy2.this,channel_Id)
                    .setContentTitle("New Message")
                    //.setAutoCancel(true)
                    .setOngoing(true)
                    .setContentText("Your order has been successfully placed!! Thanks for shopping with us:)")
                    .setSmallIcon(R.drawable.notification_message)
                    /*.setContentIntent(pendingIntent)*/
                    .build();

            manager.notify(notifyID, myNotification);
        }
        else{
            //Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
            //PendingIntent.FLAG_UPDATE_CURRENT - Flag indicating that if the described PendingIntent already exists,
            // then keep it but replace its extra data with what is in this new Intent.
            //PendingIntent pendingIntent = PendingIntent.getActivity(buy2.this, 1,i, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification.Builder builder = new Notification.Builder(buy2.this);
            builder.setAutoCancel(true);
            builder.setContentTitle("Order Successful!");
            builder.setContentText("Your order has been successfully placed!! Thanks for shopping with us:)");
            builder.setSmallIcon(R.drawable.notification_message);
            //builder.setContentIntent(pendingIntent);
            builder.setOngoing(true);
            builder.setSubText("Visit app again:)");   //API level 16
            //builder.setNumber(100);
            myNotification= builder.build();
            //  myNotication = builder.getNotification();
            // myNotication.flags = Notification.FLAG_AUTO_CANCEL;
            manager.notify(notifyID,myNotification);


        }

        String url;
        String no="9440134554";
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
//Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(no, null, "Username: "+s+"\nItem: "+name+"\nAddress: "+add+"\nPayment Method: "+payment+"\nQuantity: "+quantity+"\nTotal Amount: "+cost, pi,null);
        Toast.makeText(getApplicationContext(), "Message Sent successfully!",Toast.LENGTH_LONG).show();
        sp = getSharedPreferences("orders", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("name1",name);
        edit.putString("pass",add);
        edit.putString("pass1",payment);
        edit.putInt("pass2",quantity);
        edit.putInt("pass3",cost);
        edit.commit();

    }
}
