package com.example.gcek.MainDrawer.DiscoverCollegeTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.gcek.R;
import com.example.gcek.TPO.TPOMain;

import java.util.Locale;

public class DiscoverActivity extends AppCompatActivity {
    Toolbar toolbar;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        mcontext = this;

        toolbar = findViewById(R.id.discoveractivitytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        View.OnClickListener DepartmentClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch(v.getId()){
                    case R.id.ITCardViewDiscover:
                        intent = new Intent(mcontext , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.itDepartmentBasicInfo));
                        break;
                    case R.id.EntcCardViewDiscover:
                        intent = new Intent(mcontext, DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.entcDepartmentBasicInfo));
                        break;
                    case R.id.CIVILCardViewDiscover:
                        intent = new Intent(mcontext , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.civilDepartmentBasicInfo));
                        break;
                    case R.id.MECHCardViewDiscover:
                        intent = new Intent(mcontext, DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.mechDepartmentBasicInfo));
                        break;
                    case R.id.ElectricalCardViewDiscover:
                        intent = new Intent(mcontext, DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.electricalDepartmentBasicInfo));
                        break;
                    case R.id.LocaitonCardView:
                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 17.30966846043356, 74.18717468177438);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        break;
                    case R.id.officialWebsiteCardView:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gcekarad.ac.in/"));
                        break;
                    case R.id.TPOCardView:
                        intent = new Intent(mcontext, TPOMain.class);
                        break;
                }
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
       findViewById(R.id.ITCardViewDiscover).setOnClickListener(DepartmentClickListner);
       findViewById(R.id.EntcCardViewDiscover).setOnClickListener(DepartmentClickListner);
        findViewById(R.id.CIVILCardViewDiscover).setOnClickListener(DepartmentClickListner);
        findViewById(R.id.MECHCardViewDiscover).setOnClickListener(DepartmentClickListner);
        findViewById(R.id.ElectricalCardViewDiscover).setOnClickListener(DepartmentClickListner);
        findViewById(R.id.LocaitonCardView).setOnClickListener(DepartmentClickListner);
       findViewById(R.id.officialWebsiteCardView).setOnClickListener(DepartmentClickListner);
        findViewById(R.id.TPOCardView).setOnClickListener(DepartmentClickListner);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }
}