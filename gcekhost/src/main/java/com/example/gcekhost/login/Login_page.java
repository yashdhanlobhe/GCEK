package com.example.gcekhost.login;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gcekhost.Notification.Notifcation;
import com.example.gcekhost.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login_page extends AppCompatActivity {
    public EditText email, password;
    public Button loginbtn;

    public ProgressDialog pb;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email = (EditText) findViewById(R.id.loginemail);
        password = (EditText) findViewById(R.id.login_password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        pb = new ProgressDialog(this);
        Context context = getApplicationContext();


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
                } else {
                    pb.show();
                    String memail = email.getText().toString();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    db.collection("AdminLogin").document(memail).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        Log.d("yash", documentSnapshot.getData().toString());

                                        String a = documentSnapshot.get("Password").toString();
                                        Log.d("yash", a);
                                        if(a.equals(password.getText().toString())){
                                            startActivity(new Intent(getApplicationContext() , Notifcation.class));
                                        }else{
                                            Toast.makeText(getApplicationContext(),"incorrect password" , Toast.LENGTH_LONG).show();
                                        }

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Plese enter valid email", Toast.LENGTH_LONG).show();
                                    }
                                    pb.dismiss();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pb.dismiss();
                            Toast.makeText(getApplicationContext(), "Plese enter valid email", Toast.LENGTH_LONG);
                        }
                    });
                }
            }
        });
    }
}