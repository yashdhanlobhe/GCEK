package com.example.gcekhost;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANALE1ID = "channel_1";
    public static final String CHANALE2ID = "channel_2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
        NotificationChannel notificationChannel1  = new NotificationChannel(CHANALE1ID , "channel_1" , NotificationManager.IMPORTANCE_HIGH);
        NotificationChannel notificationChannel2  = new NotificationChannel(CHANALE2ID , "channel_2" , NotificationManager.IMPORTANCE_HIGH);
        notificationChannel1.setDescription("1st channel");
        notificationChannel2.setDescription("2nd channel");
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel1);
        notificationManager.createNotificationChannel(notificationChannel2);
    }
    }
}
