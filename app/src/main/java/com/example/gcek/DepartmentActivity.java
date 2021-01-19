package com.example.gcek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DepartmentActivity extends AppCompatActivity {
TextView Departmentname, name   , degree, vision ,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        String[] data =getIntent().getStringArrayExtra("data");

        Departmentname = findViewById(R.id.DepartmentActivityTitle);
        name = findViewById(R.id.DepartmentActivityHodName);
        degree = findViewById(R.id.DepartmentActivityHodDegree);
        vision = findViewById(R.id.DepartmentActivityVision);
        about = findViewById(R.id.DepartmentActivityAbout);

        Departmentname.setText(data[0]);
        name.setText(data[1]);
        degree.setText(data[2]);
        vision.setText(data[3]);
        about.setText(data[4]);

        Button backbtn = findViewById(R.id.DepartmentActivityBackButton);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}