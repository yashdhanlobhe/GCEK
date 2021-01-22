package com.example.gcek.maindrawer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.R;

public class VariousCollegeLoginFragment extends Fragment implements View.OnClickListener {
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        context = container.getContext();

        root.findViewById(R.id.mislogincardview).setOnClickListener(this);
        root.findViewById(R.id.facultylogincardview).setOnClickListener(this);
        root.findViewById(R.id.moodlelogincardview).setOnClickListener(this);
        root.findViewById(R.id.downloadcentercardview).setOnClickListener(this);
        root.findViewById(R.id.internetgatewaylogincardview).setOnClickListener(this);
        root.findViewById(R.id.digitallibrarycardviewcenter).setOnClickListener(this);

        return root;
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