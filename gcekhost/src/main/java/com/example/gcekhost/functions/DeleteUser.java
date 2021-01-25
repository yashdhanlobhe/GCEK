package com.example.gcekhost.functions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gcekhost.R;
import com.example.gcekhost.login.Login_page;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DeleteUser extends AppCompatActivity {
    public EditText emaileditext , paswordedittext;
    public String email , password;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        emaileditext = findViewById(R.id.deleteuseremail);
        paswordedittext = findViewById(R.id.deleteuserpassword);
        pd = new ProgressDialog(this);

        findViewById(R.id.deleteemailuserbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setCancelable(false);
                pd.setTitle("Deleting");
                pd.show();
                email= emaileditext.getText().toString();
                password = paswordedittext.getText().toString();
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        authResult.getUser().delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                DeleteuserProfileData(authResult.getUser());
                                pd.dismiss();
                                Toast.makeText(getApplicationContext() , "Deleted user successfully",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext() , Login_page.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext() , "something went wrong" , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext() , e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void DeleteuserProfileData(FirebaseUser user) {
        String email = user.getEmail();
        FirebaseFirestore.getInstance().collection("StudentUsers").document(email).delete();
        FirebaseStorage.getInstance().getReference("/images/UsersProfile"+"/"+email+".jpg").delete();
    }
}