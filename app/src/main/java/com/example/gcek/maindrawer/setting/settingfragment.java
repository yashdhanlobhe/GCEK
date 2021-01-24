package com.example.gcek.maindrawer.setting;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.FirstActivityWto;
import com.example.gcek.MainActivity;
import com.example.gcek.R;
import com.example.gcek.login.ChangePassword;
import com.example.gcek.login.login_page;
import com.google.firebase.auth.FirebaseAuth;

public class settingfragment extends Fragment implements View.OnClickListener {

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
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplicationContext() , FirstActivityWto.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
            case  R.id.notifationsetting:
                ((MainActivity) getActivity()).replaceFragment(new NotifiacationSubscribedTopic());
                break;
            case R.id.changepasswordsetting :
                startActivity(new Intent(getActivity().getApplicationContext(), ChangePassword.class));
                break;

        }
    }
}