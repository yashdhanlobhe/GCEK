package com.example.gcek.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcek.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    EditText fullname , rollNo , email , phoneNO , password;
    FirebaseAuth mAuth;
    Spinner branch, passout;
    ImageView imageView;
    Button sendmail;
    int PICKimg=100;
    Uri uri ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        fullname = (EditText)findViewById(R.id.fullnameregister);
        email = (EditText)findViewById(R.id.emailregister);
        rollNo = (EditText)findViewById(R.id.idregister);
        phoneNO = (EditText)findViewById(R.id.phoneregister);
        password = (EditText)findViewById(R.id.passwordregister);
        branch=(Spinner)findViewById(R.id.registerspinner);
        passout=(Spinner)findViewById(R.id.passoutregister);
        imageView=(ImageView)findViewById(R.id.imgregisterlay);
        sendmail = (Button)findViewById(R.id.buttonRegister);

        mAuth =  FirebaseAuth.getInstance();
        ProgressDialog pb = new ProgressDialog(getApplicationContext());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
//                pb.show();
                mAuth.createUserWithEmailAndPassword(email.getText().toString() , password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
//                                        pb.dismiss();
                                        startActivity(new Intent(getApplicationContext() , login_page.class));
                                        Log.d("yd22" , "ADDED USER SUCCESSFULLY");

                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       Log.d("yd22" , e.getMessage());
                    }
                });
            }
        });
    }

    private void OpenGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery , PICKimg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICKimg && requestCode == PICKimg){
            uri = data.getData();
            imageView.setImageURI(uri);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}