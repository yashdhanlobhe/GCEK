package com.example.gcek.MainDrawer.FAQTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gcek.MainDrawer.MainHomeTab.HomeFragment;
import com.example.gcek.R;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);
        mcontext= this;

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
        findViewById(R.id.collegerelatedfaqcard).setOnClickListener(this);
        findViewById(R.id.examrelatedfaqcard).setOnClickListener(this);
        findViewById(R.id.hostelrelatedfaqcard).setOnClickListener(this);
        findViewById(R.id.admissionrelatedfaqcard).setOnClickListener(this);
        findViewById(R.id.scholarshiprelatedfaqcard).setOnClickListener(this);
        findViewById(R.id.tporelatedfaqcard).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collegerelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "college"));
                break;
            case R.id.examrelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "exam"));
                break;
            case R.id.hostelrelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "hostel"));
                break;
            case R.id.admissionrelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "admission"));
                break;
            case R.id.scholarshiprelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "scholarship"));
                break;
            case R.id.tporelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "tpo"));
                break;
        }
    }
}