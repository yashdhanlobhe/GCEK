package com.example.gcekhost.Attendace;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.gcekhost.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class TakeAttendaceActivity extends AppCompatActivity {
    ArrayList<Integer> attendace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initAttendacebtns();

        findViewById(R.id.sendEmailBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> emails = new ArrayList<>();
                emails.add("ydhanlobhe1930@gmail.com");
                String Data = String.format("mailto:%s?subject=%s&body=%s", "ydhanlobhe1930@gmail.com", "SY IT GCEK ATTENDACE", attendace.toString());
                Intent email= new Intent(Intent.ACTION_SENDTO);
                email.setData(Uri.parse(Data));

                startActivity(email);
            }
        });
    }

    private void initAttendacebtns() {
        attendace= new ArrayList<>();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LinearLayoutTakeAttendace);
        for (int i = 1; i <= 60; i++) {
            LayoutInflater inflater = getLayoutInflater();
            Button btnTag = (Button) inflater.inflate(R.layout.button, null, false);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag.setText(Integer.toString(i));
            btnTag.setClickable(true);
            btnTag.setGravity(Gravity.CENTER_HORIZONTAL);
            btnTag.setId(i);
            btnTag.setBackgroundColor(Color.BLACK);
            linearLayout.addView(btnTag);
            btnTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(attendace.contains(view.getId())){
                        btnTag.setBackgroundColor(Color.BLACK);
                        attendace.remove((Object)view.getId());
                    }
                    else {
                        btnTag.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.green));
                        attendace.add(view.getId());
                    }
                }
            });
        }
    }
}