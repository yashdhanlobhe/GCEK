package com.example.gcek.login;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gcek.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import static com.example.gcek.resources.FileInfo.getFileSizeFromUriInKb;


public class register_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    EditText fullname , rollNo , email , phoneNO , password;
    FirebaseAuth mAuth;
    ImageView imageView;
    Button sendmail;
    int PICKimg=100;
    Uri uri ;
    Context mcontext;
    ProgressDialog pb;
    String ProfileImageStorageRef,tag = "RegisterPAGE";
    FirebaseFirestore db;
    StorageReference mStorageRef;
    Uri profileImageUri;
    Spinner batchSpinner , brachSpinner;
    String BRANCH  ,BATCH;
    int sizeOfUploadingImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mcontext = this;
        intiUi();
        findViewById(R.id.haveAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , login_page.class));
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullname.getText().toString() == null ||rollNo.getText().toString() == null ||email.getText().toString() == null ||
                        phoneNO.getText().toString() == null ||password.getText().toString() == null ||uri==null){
                    Toast.makeText(mcontext , "Enter All Data Correctly" ,Toast.LENGTH_LONG).show();
                }
                else {
                    if(sizeOfUploadingImage<100){
                    registerUser();
                        pb.setCancelable(false);
                        pb.setTitle("Creating Profile");
                        pb.show();

                    }
                    else {
                        Toast.makeText(mcontext , "Size Of Image Should be less Than 100kb" ,Toast.LENGTH_LONG).show();
                    }
                }
                }
        });
    }

    private void registerUser() {
        Log.d("ydcheack" , "entering register User");
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email.getText().toString() , password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void Void) {
                                Log.d("ydcheack" , "User Created and going to upload image");
                                UploadImageToFireStore(uri);
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
        ProfileImageStorageRef = "images/UsersProfile/"+email.getText().toString()+".jpg";
        StorageReference profileImageRef = mStorageRef.child("images/UsersProfile/"+email.getText().toString()+".jpg");
        profileImageRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Log.d("ydcheack" , "uploaded image");

                        profileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                profileImageUri = uri;
                                Log.d(tag , uri.toString());
                                uploadData();
                                Log.d("ydcheack" , "Updloaded data");

                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pb.dismiss();
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
        user.put("batch", batchSpinner.getSelectedItem().toString());
        user.put("branch" , brachSpinner.getSelectedItem().toString());

        db.collection("StudentUsers").document(email.getText().toString())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("yd22", "DocumentSnapshot added with ID: ");
                        Log.d(tag , "ADDED USER SUCCESSFULLY");
                        pb.dismiss();
                        Toast.makeText(mcontext , "Registration successful plese Verify Your mail" , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext() , login_page.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("yd22", "Error adding document", e);
                        pb.dismiss();
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
        imageView=(ImageView)findViewById(R.id.imgregisterlay);
        sendmail = (Button)findViewById(R.id.buttonRegister);
        mAuth =  FirebaseAuth.getInstance();
        pb = new ProgressDialog(mcontext);

        batchSpinner = (Spinner) findViewById(R.id.passoutspinnerregister);
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

        brachSpinner =(Spinner)findViewById(R.id.brachspinnerregister);
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
        if(requestCode==PICKimg && requestCode == PICKimg){
            uri = data.getData();
            imageView.setImageURI(uri);
            String sizeInString = getFileSizeFromUriInKb(mcontext , uri);
            sizeOfUploadingImage = Integer.parseInt(sizeInString);
            TextView sizeOfImage = findViewById(R.id.ImageSize);
            sizeOfImage.setText("" +sizeInString + " kb");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}