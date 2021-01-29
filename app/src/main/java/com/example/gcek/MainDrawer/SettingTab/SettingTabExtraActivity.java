package com.example.gcek.MainDrawer.SettingTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.example.gcek.MainDrawer.MainHomeTab.HomeFragment;
import com.example.gcek.R;

public class SettingTabExtraActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_tab_extra2);
        toolbar = findViewById(R.id.SettingTabActivityToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.SettingTabActivityFrameLayout , new NotifiacationSubscribedTopic()).commit();    }
}