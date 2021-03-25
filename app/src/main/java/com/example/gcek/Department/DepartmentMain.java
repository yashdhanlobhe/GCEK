package com.example.gcek.Department;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
        Toolbar toolbar = findViewById(R.id.DepartmentToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Context mContext = this;
        findViewById(R.id.ITCardViewDiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext , DepartmentActivity.class));
            }
        });
        findViewById(R.id.EntcCardViewDiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext , DepartmentActivity.class));
            }
        });
        findViewById(R.id.MechCardViewDiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext , DepartmentActivity.class));
            }
        });
        findViewById(R.id.CivilCardViewDiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext , DepartmentActivity.class));
            }
        });
        findViewById(R.id.electicalCardViewDiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(   mContext , DepartmentActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_right , R.anim.slide_out_to_left);
    }
}