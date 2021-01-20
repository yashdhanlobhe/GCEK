package com.example.gcek.maindrawer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.FirstActivityWto;
import com.example.gcek.R;
import com.google.firebase.auth.FirebaseAuth;

public class settingfragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settingfragment, container, false);
        root.findViewById(R.id.SignoutSettingbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplicationContext() , FirstActivityWto.class));
            }
        });
        return root;
    }
}