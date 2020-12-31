package com.example.gcekhost.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gcekhost.App;
import com.example.gcekhost.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.Calendar;


public class AddNotification extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button send_btn , seeNotifiacationBtn;
    NotificationManagerCompat notificationManagerCompat;
    //
    EditText notification_title , notification_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotifcation);
        // Write a message to the database

        database = FirebaseDatabase.getInstance();
        notificationManagerCompat = NotificationManagerCompat.from(this);

        send_btn = (Button)findViewById(R.id.send_btn_notifiacaiton);
        seeNotifiacationBtn = (Button)findViewById(R.id.see_notifiaction_btn);

        notification_title = (EditText) findViewById(R.id.title_notification);
        notification_description=(EditText)findViewById(R.id.description_notification);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =Calendar.getInstance().getTime().toString();

                myRef = database.getReference().child("Notices").child(id);

                myRef.child("title").setValue(notification_title.getText().toString());
                myRef.child("description").setValue(notification_description.getText().toString());
                myRef.child("id").setValue(id);
                android.app.Notification notification  = new NotificationCompat.Builder(getApplicationContext() , App.CHANALE1ID)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                        .setContentTitle(notification_title.getText().toString())
                        .setContentText(notification_description.getText().toString())
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                notificationManagerCompat.notify(1 , notification);


            }
        });
        seeNotifiacationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Notification.class));
            }
        });



    }
}