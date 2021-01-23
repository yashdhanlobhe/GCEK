package com.example.gcekhost.serviceNotifiaciton;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendNotifiaction {
    public static void SendNotifiacionToDevices(Context mcontext ,String NOTIFICATION_TITLE,String NOTIFICATION_MESSAGE ){
        String TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        FirebaseMessaging.getInstance().subscribeToTopic("userABC");
        try {
            notifcationBody.put("title", NOTIFICATION_TITLE);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
            Log.d("Notify", "1");
        } catch (JSONException e) {
            Log.e("SendNotifiToDevices", "onCreate: " + e.getMessage() );
            Log.d("Notify", "2");

        }
        SendNotificationToDevices(notification , mcontext);
    }

    public static void SendNotificationToDevices(JSONObject notification , Context mcontext) {
        String tag = "NotifiytoDevices";
        String FCM_API = "https://fcm.googleapis.com/fcm/send";
        String serverKey = "key=" + "AAAAok9YObw:APA91bHgUXyZVXACilf2LCI0qb7SrBz22HLmIK0dzkw-GkO4i8rGGady1FSHy1Tm9NpW2t3C1dOHg_pJsguwWYrXFiel2mBbxWo5r0ChOlcdK-w6bg2xd3GgARyxxYkKA9vkx8YqNHE3";
        String contentType = "application/json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Notify", "3");

                        Log.i(tag, "onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Notify", "4");

                        Log.i(tag, "onErrorResponse: Didn't work");
                    }

                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                Log.d("Notify", "5");

                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        Log.d("Notify", "7");
        MySingleton.getInstance(mcontext).addToRequestQueue(jsonObjectRequest);
    }
}