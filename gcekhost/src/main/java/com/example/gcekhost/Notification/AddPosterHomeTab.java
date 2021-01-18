package com.example.gcekhost.Notification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.gcekhost.MainActivity;
import com.example.gcekhost.R;
import com.example.gcekhost.services.UploadImageToFireStore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.Time;
import java.util.Calendar;
import java.util.Locale;

public class AddPosterHomeTab extends AppCompatActivity {
    final int PICKimg =  200 ;
    Uri UploadinLocalgUri ;
    private static String DownloadURI ;
    private static DatabaseReference myRef;
    private static FirebaseDatabase database;
    ImageView imageView;
    EditText title ;
    Context mcontext;
    Button uploadBtn ;
    private static String UploadingID ;
    ProgressDialog pd ;
    private static StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poster_home_tab);
        database = FirebaseDatabase.getInstance();
        mcontext =this ;

        imageView = findViewById(R.id.imageViewtoaddPoster);
        title = findViewById(R.id.editTexttoPosterTitle);
        uploadBtn = findViewById(R.id.Addposterbtn);
        pd = new ProgressDialog(mcontext);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UploadinLocalgUri == null){
                    Toast.makeText(mcontext , "GIVE TITLE AND IMAGE" , Toast.LENGTH_LONG).show();
                }
                else {
                    pd.setCancelable(false);
                    pd.show();
                    UploadingID = Calendar.getInstance().getTime().toString();
                    UplaodPoster(UploadinLocalgUri , UploadingID);

                }
            }
        });

    }

    private void UplaodPoster(Uri uri , String uploadingID) {
        uploadImageToFirestore(uri  , "Home Poster" + uploadingID + ".jpg" );
    }

    private void OpenGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery , PICKimg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICKimg && requestCode == PICKimg){
            UploadinLocalgUri = data.getData();
            imageView.setImageURI(UploadinLocalgUri);
        }
    }
   void uploadImageToFirestore(Uri UploadLocalUri , String uploadPathInFireStorewithExtention ){
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference mStorage = mStorageRef.child(uploadPathInFireStorewithExtention);

        mStorage.putFile(UploadLocalUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DownloadURI  = uri.toString();
                                myRef = database.getReference().child("HomePoster").child(UploadingID);
                                myRef.child("title").setValue(title.getText().toString());
                                myRef.child("id").setValue(UploadingID);
                                myRef.child("noticeURI").setValue(DownloadURI);
                                pd.dismiss();
                                startActivity(new Intent(mcontext, MainActivity.class));
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        DownloadURI = null ;
                    }
                });
    }
}