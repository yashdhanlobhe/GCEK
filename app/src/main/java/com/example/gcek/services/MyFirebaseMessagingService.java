package com.example.gcek.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.gcek.MainActivity;
import com.example.gcek.R;
import com.example.gcek.ShowNoticeActivity;
import com.example.gcek.SplashScreen;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private final String ADMIN_CHANNEL_ID ="admin_channel";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("NotificationSubscribedTopic" , MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        List<String> subTopics = new ArrayList<>();
        if(preferences.getBoolean("College" , true)){subTopics.add("College");}
        if(preferences.getBoolean("TPO" , true)){subTopics.add("TPO");}
        if(preferences.getBoolean("FY" , true)){subTopics.add("FY");}
        if(preferences.getBoolean("SY" , true)){subTopics.add("SY");}
        if(preferences.getBoolean("TY" , true)){subTopics.add("TY");}
        if(preferences.getBoolean("FinalYear" , true)){subTopics.add("FinalYear");}
        for(String topic : subTopics){
            editor.putBoolean(topic , true);
            FirebaseMessaging.getInstance().subscribeToTopic(topic);
        }
        editor.apply();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d("sendnotifi" , "MyFirebaseMessagingServic");

        super.onMessageReceived(remoteMessage);

        String fromandtitle[] = remoteMessage.getData().get("fromandtitle").split("fromandtitle");

        final Intent intent = new Intent(this, ShowNoticeActivity.class)
                .putExtra("from" , fromandtitle[0])
                .putExtra("title" , fromandtitle[1])
                .putExtra("message" , remoteMessage.getData().get("message"));
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationID = new Random().nextInt(3000);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupChannels(notificationManager);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.gcekofficaiallogo)
                .setContentTitle("From "+fromandtitle[0])
                .setContentText( fromandtitle[1])
                .setAutoCancel(true)
                .setSound(notificationSoundUri)
                .setContentIntent(pendingIntent);
        notificationManager.notify(notificationID, notificationBuilder.build());
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels(NotificationManager notificationManager){
        CharSequence adminChannelName = "Notifications";
        String adminChannelDescription = "You can change priority of notification from app";
        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_HIGH);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }
    }
}
