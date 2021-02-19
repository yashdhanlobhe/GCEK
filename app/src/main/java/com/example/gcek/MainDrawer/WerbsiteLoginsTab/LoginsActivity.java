package com.example.gcek.MainDrawer.WerbsiteLoginsTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.gcek.MainDrawer.FAQTab.MainFAQFragment;
import com.example.gcek.R;

public class LoginsActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);

        toolbar = findViewById(R.id.loginsactivitytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Fragment temp = new VariousCollegeLoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.LoginsframeLayout, temp).commit();
    }
}