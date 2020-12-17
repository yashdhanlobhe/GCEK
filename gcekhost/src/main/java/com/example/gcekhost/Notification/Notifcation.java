package com.example.gcekhost.Notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gcekhost.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.Calendar;


public class Notifcation extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button send_btn;
    //
    EditText notification_title , notification_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifcation);
        // Write a message to the database

        database = FirebaseDatabase.getInstance();


        send_btn = (Button)findViewById(R.id.send_btn_notifiacaiton);
        notification_title = (EditText) findViewById(R.id.title_notification);
        notification_description=(EditText)findViewById(R.id.description_notification);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference().child("Notices").child(Calendar.getInstance().getTime().toString());

                myRef.child("title").setValue(notification_title.getText().toString());
                myRef.child("description").setValue(notification_description.getText().toString());
            }
        });




    }
}