package com.example.gcek.maindrawer.setting;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.gcek.R;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class NotifiacationSubscribedTopic extends Fragment implements CompoundButton.OnCheckedChangeListener {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Switch swclg,fyclg,syclg,tyclg,lyclg,tpoclg,otherclg;
    List<String> subTopics;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifiacation_subscribed_topic, container, false);
        initUI(root);
        swclg.setOnCheckedChangeListener(this);
        fyclg.setOnCheckedChangeListener(this);
        syclg.setOnCheckedChangeListener(this);
        tyclg.setOnCheckedChangeListener(this);
        lyclg.setOnCheckedChangeListener(this);
        tpoclg.setOnCheckedChangeListener(this);
        otherclg.setOnCheckedChangeListener(this);


        return root;
    }

    private void initUI(View root) {
        preferences = getActivity().getApplicationContext().getSharedPreferences("NotificationSubscribedTopic" , MODE_PRIVATE);
        editor = preferences.edit();
        swclg= root.findViewById(R.id.collegeNotifiacionradiobtn);
        fyclg= root.findViewById(R.id.fyNotifiacionradiobtn);
        syclg= root.findViewById(R.id.syNotifiacionradiobtn);
        tyclg= root.findViewById(R.id.tyNotifiacionradiobtn);
        lyclg= root.findViewById(R.id.finalyearNotifiacionradiobtn);
        tpoclg= root.findViewById(R.id.tpoNotifiacionradiobtn);
        otherclg= root.findViewById(R.id.otherNotifiacionradiobtn);

        getSubTopics();
        for(String topic : subTopics){
            cheakTopicbtn(topic);
        }
    }

    private void cheakTopicbtn(String topic) {
        switch(topic){
            case "College":
                swclg.setChecked(true);
                break;
            case "TPO":
                tpoclg.setChecked(true);
                break;
            case "FY":
                fyclg.setChecked(true);
                break;
            case "SY":
                syclg.setChecked(true);
                break;
            case "TY":
                tyclg.setChecked(true);
                break;
            case "FinalYear":
                lyclg.setChecked(true);
                break;
            case "Other":
                otherclg.setChecked(true);
                break;

        }
    }

    private void getSubTopics() {
        subTopics = new ArrayList<>();
        if(preferences.getBoolean("College" , true)){subTopics.add("College");}
        if(preferences.getBoolean("TPO" , true)){subTopics.add("TPO");}
        if(preferences.getBoolean("FY" , true)){subTopics.add("FY");}
        if(preferences.getBoolean("SY" , true)){subTopics.add("SY");}
        if(preferences.getBoolean("TY" , true)){subTopics.add("TY");}
        if(preferences.getBoolean("FinalYear" , true)){subTopics.add("FinalYear");}
        if(preferences.getBoolean("Other" , true)){subTopics.add("Other");}

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String topic = null;
        switch (buttonView.getId()){
            case  R.id.collegeNotifiacionradiobtn:
                topic = "College";
                editor.putBoolean("College", isChecked);
                break;
            case  R.id.fyNotifiacionradiobtn:
                topic = "FY";
                editor.putBoolean("FY", isChecked);
                break;
            case  R.id.syNotifiacionradiobtn:
                topic = "SY";
                editor.putBoolean("SY",isChecked);
                break;
            case  R.id.tyNotifiacionradiobtn:
                topic = "TY";
                editor.putBoolean("TY",isChecked);
                break;
            case  R.id.finalyearNotifiacionradiobtn:
                topic = "FinalYear";
                editor.putBoolean("FinalYear", isChecked);
                break;
            case  R.id.tpoNotifiacionradiobtn:
                topic = "TPO";
                editor.putBoolean("TPO",isChecked);
                break;
            case  R.id.otherNotifiacionradiobtn:
                topic = "Other";
                editor.putBoolean("Other",isChecked);
                break;
        }
        if(isChecked){
            FirebaseMessaging.getInstance().subscribeToTopic(topic);
            Toast.makeText(getActivity().getBaseContext() , "Subscribed to "+ topic , Toast.LENGTH_SHORT ).show();
        }
        else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
            Toast.makeText(getActivity().getBaseContext() , "Unubscribed From "+ topic , Toast.LENGTH_SHORT ).show();

        }
        editor.apply();
    }
}