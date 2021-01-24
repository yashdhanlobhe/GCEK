package com.example.gcek.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.gcek.MainActivity;
import com.example.gcek.Notification.Notification;
import com.example.gcek.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class login_page extends AppCompatActivity {
    public  FirebaseAuth mAuth;
    public EditText email ,password;
    public Button loginbtn;
    public TextView register_yourself;
    public ProgressDialog pb;
    Context mcontext ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        FirebaseMessaging.getInstance().subscribeToTopic("FY");
        if(mAuth.getCurrentUser()!= null && mAuth.getCurrentUser().isEmailVerified()){
            Toast.makeText(getApplicationContext() , mAuth.getCurrentUser().getEmail() + "  Loged In",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext() , MainActivity.class).
                    putExtra("email" , mAuth.getCurrentUser().getEmail()).
                    setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

        }
        register_yourself = (TextView)findViewById(R.id.register_text);
        email =(EditText)findViewById(R.id.loginemail);
        password=(EditText)findViewById(R.id.login_password);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        pb = new ProgressDialog(this);
        mcontext = this;
        Context context = getApplicationContext();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.show();
                mAuth.signInWithEmailAndPassword(email.getText().toString() , password.getText().toString()).
                        addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if(mAuth.getCurrentUser().isEmailVerified()){
                                    pb.dismiss();
                                    Toast.makeText(getApplicationContext() , email.getText().toString() + "  Loged In",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext() , MainActivity.class)
                                            .putExtra("email" , mAuth.getCurrentUser().getEmail())
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                }

                                else {
                                    pb.dismiss();
                                    Toast.makeText(mcontext , "Email is Not Verified" , Toast.LENGTH_LONG).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pb.dismiss();
                        Toast.makeText(getApplicationContext() ,e.getMessage() ,Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
        register_yourself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , register_page.class));
            }
        });
        email.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //make the view scroll down to the bottom
                findViewById(R.id.LoginscreenScrollview).scrollTo(0, findViewById(R.id.LoginscreenScrollview).getBottom());

            }
        });
        password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //make the view scroll down to the bottom
                findViewById(R.id.LoginscreenScrollview).scrollTo(0, findViewById(R.id.LoginscreenScrollview).getBottom());

            }
        });
    }

}