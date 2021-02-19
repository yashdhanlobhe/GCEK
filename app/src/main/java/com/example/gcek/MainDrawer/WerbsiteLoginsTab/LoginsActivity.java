package com.example.gcek.MainDrawer.WerbsiteLoginsTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.gcek.R;

public class LoginsActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        toolbar = findViewById(R.id.Loginsactivitytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        context = this;
        findViewById(R.id.mislogincardview).setOnClickListener(this);
        findViewById(R.id.facultylogincardview).setOnClickListener(this);
        findViewById(R.id.moodlelogincardview).setOnClickListener(this);
        findViewById(R.id.downloadcentercardview).setOnClickListener(this);
        findViewById(R.id.internetgatewaylogincardview).setOnClickListener(this);
        findViewById(R.id.digitallibrarycardviewcenter).setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.mislogincardview:
                intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://mis.gcekarad.ac.in/GCEKMIS/iitmsv4eGq0RuNHb0G5WbhLmTKLmTO7YBcJ4RHuXxCNPvuIw=?enc=VkmxevzBcWWxrhMpzAa5iGbQRW+9E0bvJQWQr/ELfBc="));
                startActivity(intent);
                break;
            case R.id.facultylogincardview:
                intent = new Intent(Intent.ACTION_VIEW , Uri.parse("http://www.gcekarad.ac.in/Login.aspx"));
                startActivity(intent);
                break;
            case R.id.moodlelogincardview:
                intent = new Intent(Intent.ACTION_VIEW , Uri.parse("http://117.239.185.161/moodle"));
                startActivity(intent);
                break;
            case R.id.downloadcentercardview:
                showAlerDialog(new Intent(Intent.ACTION_VIEW , Uri.parse("http://172.16.1.39:8000/gcek")));
                break;
            case R.id.internetgatewaylogincardview:
                if(showAlerDialog(new Intent(Intent.ACTION_VIEW , Uri.parse("http://1.1.1.1:8090/httpclient.html"))));
                break;
            case R.id.digitallibrarycardviewcenter:
                if(showAlerDialog(new Intent(Intent.ACTION_VIEW , Uri.parse("http://172.16.1.39:81/"))));
                break;
        }
    }

    private boolean showAlerDialog(Intent intent) {
        new AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_baseline_signal_wifi_4_bar_lock_24)
                .setTitle("WIFI warning")
                .setMessage("Make sure you are connected to college WIFI to open this web.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
        return true;
    }
}