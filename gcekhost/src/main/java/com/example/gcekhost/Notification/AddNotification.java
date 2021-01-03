package com.example.gcekhost.Notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


public class AddNotification extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button send_btn , seeNotifiacationBtn;

    EditText notification_title , notification_description;

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAok9YObw:APA91bHgUXyZVXACilf2LCI0qb7SrBz22HLmIK0dzkw-GkO4i8rGGady1FSHy1Tm9NpW2t3C1dOHg_pJsguwWYrXFiel2mBbxWo5r0ChOlcdK-w6bg2xd3GgARyxxYkKA9vkx8YqNHE3";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;

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
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =Calendar.getInstance().getTime().toString();
                myRef = database.getReference().child("Notices").child(id);
                myRef.child("title").setValue(notification_title.getText().toString());
                myRef.child("description").setValue(notification_description.getText().toString());
                myRef.child("id").setValue(id);

                messenging(notification_title.getText().toString() , notification_description.getText().toString() );
            }
        });
        seeNotifiacationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Notification.class));
            }
        });
    }

    private void messenging(String title , String message) {
        TOPIC = "/topics/userABC";

        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", NOTIFICATION_TITLE);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }
        sendNotification(notification);
    }

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                Log.i("Authorization", serverKey);
                return params;

            }
        };
        Log.i("debugyd","started");
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}


class MySingleton {
    private  static MySingleton instance;
    private RequestQueue requestQueue;
    private Context ctx;

    private MySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new MySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {

            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        Log.i("getRequestQueue" , "getRequestQueue");
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {

        Log.i("added" , "Request queue");
        getRequestQueue().add(req);
    }
}
