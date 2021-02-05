package com.example.gcek.LoginServices;

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

import com.example.gcek.MainActivityWithLogin;
import com.example.gcek.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageActivity extends AppCompatActivity {
    public  FirebaseAuth mAuth;
    public EditText email ,password;
    public Button loginbtn;
    public TextView register_yourself,forgoPassword;
    public ProgressDialog pb;
    Context mcontext ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!= null && mAuth.getCurrentUser().isEmailVerified()){
            Toast.makeText(getApplicationContext() , "Loged In "+ mAuth.getCurrentUser().getEmail() ,Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext() , MainActivityWithLogin.class).
                    putExtra("email" , mAuth.getCurrentUser().getEmail()).
                    setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            overridePendingTransition(R.anim.slide_out_to_left, R.anim.slide_in_from_right);


        }
        register_yourself = (TextView)findViewById(R.id.register_text);
        email =(EditText)findViewById(R.id.loginemail);
        password=(EditText)findViewById(R.id.login_password);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        pb = new ProgressDialog(this);
        forgoPassword = findViewById(R.id.forgotpasswordtext);
        mcontext = this;
        Context context = getApplicationContext();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.show();
                pb.setTitle("Logging In");
                mAuth.signInWithEmailAndPassword(email.getText().toString() , password.getText().toString()).
                        addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if(mAuth.getCurrentUser().isEmailVerified()){
                                    pb.dismiss();
                                    Toast.makeText(getApplicationContext() , "Loged in " +email.getText().toString() ,Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext() , MainActivityWithLogin.class)
                                            .putExtra("email" , mAuth.getCurrentUser().getEmail())
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    overridePendingTransition(R.anim.slide_out_to_left, R.anim.slide_in_from_right);

                                }

                                else {
                                    pb.dismiss();
                                    Toast.makeText(mcontext , "By using link sent on your email please verify your email" , Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(getApplicationContext() , RegisterPageActivity.class));
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
        forgoPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext() , ChangePasswordActivity.class));
            }
        });
    }


}