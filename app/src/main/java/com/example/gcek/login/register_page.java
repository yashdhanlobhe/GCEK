package com.example.gcek.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.nfc.Tag;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class register_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    EditText fullname , rollNo , email , phoneNO , password;
    FirebaseAuth mAuth;
    Spinner branch, passout;
    ImageView imageView;
    Button sendmail;
    int PICKimg=100;
    Uri uri ;
    Context mcontext;
    ProgressDialog pb;
    String tag = "RegisterPAGE";
    FirebaseFirestore db;
    StorageReference mStorageRef;
    Uri profileImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mcontext = this;
        intiUi();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        mAuth = FirebaseAuth.getInstance();
        pb.show();
        mAuth.createUserWithEmailAndPassword(email.getText().toString() , password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                UploadImageToFireStore(uri);
                                pb.dismiss();
                                Log.d(tag , "ADDED USER SUCCESSFULLY");
                                Toast.makeText(mcontext , "Registration successful plese Verify Your mail" , Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    pb.dismiss();
                    Log.d(tag , e.getMessage());
                    Toast.makeText(mcontext , e.getMessage() , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void UploadImageToFireStore(Uri uri) {

        StorageReference profileImageRef = mStorageRef.child("images/UsersProfile/"+email.getText().toString()+".jpg");

        profileImageRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        profileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                profileImageUri = uri;
                                Log.d(tag , uri.toString());
                                uploadData();
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private void uploadData() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("Name", fullname.getText().toString());
        user.put("GCEKID", rollNo.getText().toString());
        user.put("PhoneNo", phoneNO.getText().toString());
        user.put("ProfileImage" , profileImageUri.toString());

        db.collection("StudentUsers").document(email.getText().toString())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("yd22", "DocumentSnapshot added with ID: ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("yd22", "Error adding document", e);
                    }
                });
    }

    private void intiUi() {
        mStorageRef = FirebaseStorage.getInstance().getReference();
        db = FirebaseFirestore.getInstance();
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
        pb = new ProgressDialog(mcontext);
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