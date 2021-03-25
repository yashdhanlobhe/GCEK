package com.example.gcek.Department;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gcek.MainDrawer.DiscoverCollegeTab.DepartmentActivity;
import com.example.gcek.R;

public class DepartmentMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_main);
        Context mContext = this;
        findViewById(R.id.ITCardViewDiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext , DepartmentActivity.class));
            }
        });
    }
}