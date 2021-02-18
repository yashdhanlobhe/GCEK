package com.example.gcek.MainDrawer.SignOut;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.gcek.LoginServices.LoginPageActivity;
import com.example.gcek.MainActivityWithoutLogin;
import com.google.firebase.auth.FirebaseAuth;

public class SignOut {
    public static void confirmsignout(Context mcontext) {
        new android.app.AlertDialog.Builder(mcontext)
                .setMessage("Do you want to sign out ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        mcontext.startActivity(new Intent(mcontext, LoginPageActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }
}
