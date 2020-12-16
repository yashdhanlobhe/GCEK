package com.example.gcekhost.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcekhost.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register_page extends AppCompatActivity {
    EditText email , password , confirmpassword ;TextView haveaccount;
    Button register_btn ;
    FirebaseAuth mAuth;
    ProgressDialog pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        haveaccount = (TextView)findViewById(R.id.haveaccounttext);
        register_btn = (Button) findViewById(R.id.register_btn);
        email = (EditText)findViewById(R.id.registering_email);
        password = (EditText)findViewById(R.id.registering_password);
        confirmpassword = (EditText)findViewById(R.id.confirm_registering_password);
        pb =new ProgressDialog(this );
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
//                if(password.getText().toString() == confirmpassword.getText().toString()){
                    pb.show();
                    mAuth.createUserWithEmailAndPassword(email.getText().toString() , password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            pb.dismiss();
                                            startActivity(new Intent(getApplicationContext() , Login_page.class));
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext() , e.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });
//                }
//                else {
//                    Toast.makeText(getApplicationContext() ,"password is not same" , Toast.LENGTH_SHORT).show();
//                }
            }
        });
    haveaccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext() , Login_page.class));
        }
    });
    }
}