package com.example.gcekhost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gcekhost.Attendace.ChooseClass;
import com.example.gcekhost.Attendace.TakeAttendaceActivity;
import com.example.gcekhost.FAQ.FAQunanswerd;
import com.example.gcekhost.Notification.AddNotification;
import com.example.gcekhost.Notification.AddPosterHomeTab;
import com.example.gcekhost.functions.DeleteUser;
import com.example.gcekhost.functions.SendPrivateNotifiaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.AddNotificationcardView).setOnClickListener(this);
        findViewById(R.id.AddHomePosterNoticecardView).setOnClickListener(this);
        findViewById(R.id.deleteDataFromUser).setOnClickListener(this);
        findViewById(R.id.sendNoficaionSpecificuser).setOnClickListener(this);
        findViewById(R.id.answerfaqmaintab).setOnClickListener(this);
        findViewById(R.id.takeAttendacecardView).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AddHomePosterNoticecardView:
                startActivity(new Intent(this , AddPosterHomeTab.class));
                break;
            case R.id.AddNotificationcardView:
                startActivity(new Intent(this , AddNotification.class));
                break;
            case R.id.deleteDataFromUser:
                startActivity(new Intent(this , DeleteUser.class));
                break;

            case R.id.sendNoficaionSpecificuser:
                startActivity(new Intent(this , SendPrivateNotifiaction.class));
                break;
            case R.id.answerfaqmaintab:
                startActivity(new Intent(this , FAQunanswerd.class));
                break;
            case R.id.takeAttendacecardView:
                startActivity(new Intent(this , ChooseClass.class));
                break;
        }
    }
}