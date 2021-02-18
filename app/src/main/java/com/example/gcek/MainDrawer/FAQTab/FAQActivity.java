package com.example.gcek.MainDrawer.FAQTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.gcek.MainDrawer.MainHomeTab.HomeFragment;
import com.example.gcek.R;

public class FAQActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);

        toolbar = findViewById(R.id.faqactivitytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Fragment temp = new MainFAQFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.FAQframeLayout, temp).commit();
    }
}