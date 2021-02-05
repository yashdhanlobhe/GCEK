package com.example.gcek.MainDrawer.WithouLogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gcek.MainActivityWithLogin;
import com.example.gcek.R;
import com.example.gcek.LoginServices.LoginPageActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragmentWTO extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login_w_t_o, container, false);
        Button btn =root.findViewById(R.id.LoginWTObtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser()!= null && FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()){
                    Toast.makeText(getActivity().getApplicationContext() , "Loged In "+ FirebaseAuth.getInstance().getCurrentUser().getEmail() ,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity().getApplicationContext() , MainActivityWithLogin.class).
                            putExtra("email" , FirebaseAuth.getInstance().getCurrentUser().getEmail()).
                            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    getActivity().finish();

                }
                else {
                    startActivity(new Intent(getActivity().getApplicationContext() , LoginPageActivity.class));
                    getActivity().finish();
                }

            }
        });
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

}