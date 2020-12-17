package com.example.gcekhost.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gcekhost.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login_page extends AppCompatActivity {
    public EditText email ,password;
    public Button loginbtn;
//    public TextView register_yourself;
    public ProgressDialog pb;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
//        register_yourself = (TextView)findViewById(R.id.register_text);
        email =(EditText)findViewById(R.id.loginemail);
        password=(EditText)findViewById(R.id.login_password);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        pb = new ProgressDialog(this);
        Context context = getApplicationContext();
//        mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext() , "Please enter valid email" , Toast.LENGTH_LONG).show();
                }
                else {
                    pb.show();
                    String memail = email.getText().toString();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                        db.collection("AdminLogin").document(memail).get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists()){
                                            Log.d("yash", documentSnapshot.getData().toString());

                                            String a = documentSnapshot.get("Password").toString();
                                            Log.d("yash", a);
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext() , "Plese enter valid email" , Toast.LENGTH_LONG).show();
                                        }
                                        pb.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pb.dismiss();
                                Toast.makeText(getApplicationContext() , "Plese enter valid email" , Toast.LENGTH_LONG);
                            }
                        });
                }
            }
        });







//        mAuth = FirebaseAuth.getInstance();
//        loginbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pb.show();
//                mAuth.signInWithEmailAndPassword(email.getText().toString() , password.getText().toString()).
//                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if(mAuth.getCurrentUser().isEmailVerified()){
//                                    pb.dismiss();
//                                    startActivity(new Intent(getApplicationContext() , MainActivity.class));
//                                }
//
//                            else {
//                                pb.dismiss();
//                                startActivity(new Intent(getApplicationContext() , Register_page.class));
//                            }}
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getApplicationContext() ,e.getMessage() ,Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(getApplicationContext() , Register_page.class));
//                    }
//                });
//
//
//            }
//        });
//        register_yourself.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext() , Register_page.class));
//            }
//        });
    }
}