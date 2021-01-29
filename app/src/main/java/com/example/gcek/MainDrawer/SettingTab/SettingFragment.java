package com.example.gcek.MainDrawer.SettingTab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.MainActivityWithoutLogin;
import com.example.gcek.MainActivityWithLogin;
import com.example.gcek.R;
import com.example.gcek.LoginServices.ChangePasswordActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SettingFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settingfragment, container, false);
        root.findViewById(R.id.SignoutSettingbtn).setOnClickListener(this);
        root.findViewById(R.id.changepasswordsetting).setOnClickListener(this);
        root.findViewById(R.id.notifationsetting).setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SignoutSettingbtn:
                confirmsignout();
                break;
            case  R.id.notifationsetting:
                startActivity(new Intent(getActivity().getApplicationContext(), SettingTabExtraActivity.class));
                break;
            case R.id.changepasswordsetting :
                startActivity(new Intent(getActivity().getApplicationContext(), ChangePasswordActivity.class));
                break;
        }
    }

    private void confirmsignout() {
        new AlertDialog.Builder(getContext())
                .setMessage("Do you want to sign out ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getActivity().getApplicationContext() , MainActivityWithoutLogin.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }
}