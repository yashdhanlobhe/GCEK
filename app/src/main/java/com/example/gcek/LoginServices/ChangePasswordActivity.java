package com.example.gcek.LoginServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gcek.R;
import com.example.gcek.SplashScreenActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Context mcontext = this;
        EditText email = findViewById(R.id.chagepasswordemail);


        findViewById(R.id.chagepasswordresetbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailstring = email.getText().toString();
                emailstring = emailstring.replace(" " , "");
                Log.d("email" , emailstring);
                FirebaseAuth.getInstance().sendPasswordResetEmail(emailstring).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext() , "Email sent successfully" , Toast.LENGTH_LONG).show();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext() , SplashScreenActivity.class ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mcontext , e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
