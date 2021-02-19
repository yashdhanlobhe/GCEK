package com.example.gcek.MainDrawer.SettingTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gcek.LoginServices.ChangePasswordActivity;
import com.example.gcek.MainDrawer.SignOut.SignOut;
import com.example.gcek.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
Context mcontext;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mcontext = this;


        toolbar = findViewById(R.id.settingactivitytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.SignoutSettingbtn).setOnClickListener(this);
        findViewById(R.id.changepasswordsetting).setOnClickListener(this);
        findViewById(R.id.notifationsetting).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SignoutSettingbtn:
                SignOut.confirmsignout(v.getContext());
                break;
            case  R.id.notifationsetting:
                startActivity(new Intent(mcontext, SettingTabExtraActivity.class));
                break;
            case R.id.changepasswordsetting :
                startActivity(new Intent(mcontext, ChangePasswordActivity.class));
                break;
        }
    }
}