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
import com.example.gcek.MainDrawer.SignOut.SignOut;
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
                SignOut.confirmsignout(v.getContext());
                break;
            case  R.id.notifationsetting:
                startActivity(new Intent(getActivity().getApplicationContext(), SettingTabExtraActivity.class));
                break;
            case R.id.changepasswordsetting :
                startActivity(new Intent(getActivity().getApplicationContext(), ChangePasswordActivity.class));
                break;
        }
    }
}