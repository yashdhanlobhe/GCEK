package com.example.gcek.MainDrawer.DiscoverCollegeTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gcek.R;


public class DepartmentActivity extends AppCompatActivity {
TextView  name   , degree, vision ,about;
Toolbar toolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        toolbar = findViewById(R.id.DepartmentActivityToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String[] data =getIntent().getStringArrayExtra("data");

        toolbar.setTitle(data[0]);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        name = findViewById(R.id.DepartmentActivityHodName);
        degree = findViewById(R.id.DepartmentActivityHodDegree);
        vision = findViewById(R.id.DepartmentActivityVision);
        about = findViewById(R.id.DepartmentActivityAbout);

        name.setText(data[1]);
        degree.setText(data[2]);
        vision.setText(data[3]);
        about.setText(data[4]);


    }
}