package com.example.gcekhost.Notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gcekhost.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddNotification extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button send_btn , seeNotifiacationBtn;
    Spinner spinner;

    EditText notification_title , notification_description;

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAok9YObw:APA91bHgUXyZVXACilf2LCI0qb7SrBz22HLmIK0dzkw-GkO4i8rGGady1FSHy1Tm9NpW2t3C1dOHg_pJsguwWYrXFiel2mBbxWo5r0ChOlcdK-w6bg2xd3GgARyxxYkKA9vkx8YqNHE3";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;
    String NoticeClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotifcation);
        Log.i("add notification" , "add");
        database = FirebaseDatabase.getInstance();
        send_btn = (Button)findViewById(R.id.send_btn_notifiacaiton);
        seeNotifiacationBtn = (Button)findViewById(R.id.see_notifiaction_btn);

        notification_title = (EditText) findViewById(R.id.title_notification);
        notification_description=(EditText)findViewById(R.id.description_notification);
        spinner = (Spinner) findViewById(R.id.spinnernotices);
        ArrayAdapter<CharSequence> spinneradapter = ArrayAdapter.createFromResource(this, R.array.notices, android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);

        spinner.setAdapter(spinneradapter);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =Calendar.getInstance().getTime().toString();
                myRef = database.getReference().child("Notices").child(NoticeClass).child(id);
                myRef.child("title").setValue(notification_title.getText().toString());
                myRef.child("description").setValue(notification_description.getText().toString());
                myRef.child("id").setValue(id);
            }
        });
        seeNotifiacationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Notification.class));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        NoticeClass = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        NoticeClass = "college";
    }
}


