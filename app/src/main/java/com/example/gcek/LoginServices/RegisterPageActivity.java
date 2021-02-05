package com.example.gcek.LoginServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gcek.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.gcek.InitiateAppData.mfirebaseauth;
import static com.example.gcek.InitiateAppData.mfirebasefirestore;
import static com.example.gcek.InitiateAppData.mfirebasestorage;
import static com.example.gcek.Services.CompressImage.compressimage;

public class RegisterPageActivity extends AppCompatActivity  {
    //working yd
    EditText fullname , rollNo , email , phoneNO , password;
    ImageView imageView;
    Button sendmail;
    int PICKimg=100;
    Uri uri,profileImageUri ;
    Context mcontext;
    ProgressDialog pb;
    String ProfileImageStorageRef,tag = "RegisterPAGE" ,BRANCH  ,BATCH;
    Spinner batchSpinner , brachSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mcontext = this;
        intiUi();
        findViewById(R.id.haveAccount).setOnClickListener(v -> startActivity(new Intent(getApplicationContext() , LoginPageActivity.class)));
        imageView.setOnClickListener(v -> OpenGallery());
        sendmail.setOnClickListener(v -> {
            if(rollNo.getText().toString().equals("")|| phoneNO.getText().toString().equals("") || password.getText().toString().equals("") || uri == null){
                Toast.makeText(mcontext , "Enter All Data Correctly" ,Toast.LENGTH_LONG).show();
            }
            else {
                    registerUser();
                    pb.setCancelable(false);
                    pb.setTitle("Creating Profile");
                    pb.show();
                    pb.setMessage("Adding email");
            }
            });
    }

    private void registerUser() {
        Log.d("ydcheack" , "entering register User");
        mfirebaseauth = FirebaseAuth.getInstance();
        mfirebaseauth.createUserWithEmailAndPassword(email.getText().toString() , password.getText().toString())
                .addOnSuccessListener(authResult -> {
                    pb.setMessage("sending verification mail");
                    Objects.requireNonNull(mfirebaseauth.getCurrentUser()).sendEmailVerification().addOnSuccessListener(Void -> {
                        pb.setMessage("Compressing image");
                        Log.d("ydcheack" , "User Created and going to upload image");
                        try {
                            UploadImageToFireStore(uri);
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    });
                }).addOnFailureListener(e -> {
                        pb.dismiss();
                        Log.d(tag , e.getMessage());
                        Toast.makeText(mcontext , e.getMessage() , Toast.LENGTH_LONG).show();
                });
    }

    private void UploadImageToFireStore(Uri uri) throws IOException, URISyntaxException {

        ProfileImageStorageRef = "images/UsersProfile/"+email.getText().toString()+".jpg";
        StorageReference profileImageRef = mfirebasestorage.getReference().child("images/UsersProfile/"+email.getText().toString()+".jpg");
        byte[] uploadbytearray = compressimage(mcontext , uri , getContentResolver());
        pb.setMessage("Uploading Image");
        profileImageRef.putBytes(uploadbytearray)
                .addOnSuccessListener(taskSnapshot -> {
                    Log.d("ydcheack" , "uploaded image");

                    profileImageRef.getDownloadUrl().addOnSuccessListener(uri1 -> {
                        pb.setMessage("Uploading User Data");
                        profileImageUri = uri1;
                        Log.d(tag , uri1.toString());
                        uploadData();
                        Log.d("ydcheack" , "Updloaded data");
                    });

                })
                .addOnFailureListener(exception -> pb.dismiss());

    }


    private void uploadData() {
        Map<String, Object> user = new HashMap<>();
        user.put("Name", fullname.getText().toString());
        user.put("GCEKID", rollNo.getText().toString());
        user.put("PhoneNo", phoneNO.getText().toString());
        user.put("ProfileImage" , profileImageUri.toString());
        user.put("batch", batchSpinner.getSelectedItem().toString());
        user.put("branch" , brachSpinner.getSelectedItem().toString());

        mfirebasefirestore.collection("StudentUsers").document(email.getText().toString())
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Log.d("yd22", "DocumentSnapshot added with ID: ");
                    Log.d(tag , "ADDED USER SUCCESSFULLY");
                    pb.dismiss();
                    Toast.makeText(mcontext , "Registration successful plese Verify Your mail" , Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext() , LoginPageActivity.class));
                })
                .addOnFailureListener(e -> {
                    Log.w("yd22", "Error adding document", e);
                    pb.dismiss();
                });
    }

    private void intiUi() {
        fullname = findViewById(R.id.fullnameregister);
        email = findViewById(R.id.emailregister);
        rollNo =findViewById(R.id.idregister);
        phoneNO =findViewById(R.id.phoneregister);
        password =findViewById(R.id.passwordregister);
        imageView=findViewById(R.id.imgregisterlay);
        sendmail =findViewById(R.id.buttonRegister);
        pb = new ProgressDialog(mcontext);

        batchSpinner = findViewById(R.id.passoutspinnerregister);
        ArrayAdapter<CharSequence> batchSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.Batches, android.R.layout.simple_spinner_item);
        batchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        batchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BRANCH = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        batchSpinner.setAdapter(batchSpinnerAdapter);

        brachSpinner =findViewById(R.id.brachspinnerregister);
        ArrayAdapter<CharSequence> branchSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.Branches, android.R.layout.simple_spinner_item);
        branchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brachSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BATCH = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        brachSpinner.setAdapter(branchSpinnerAdapter);
        batchSpinner.setPrompt("Select your Brach");

    }

    private void OpenGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery , PICKimg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICKimg){
            assert data != null;
            uri = data.getData();
            imageView.setImageURI(uri);
        }
    }

}