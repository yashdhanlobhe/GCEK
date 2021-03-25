package com.example.gcekhost.AddEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.gcekhost.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class AddEvent extends AppCompatActivity  {
    Uri mainPoster , subPoster;
    ImageView mainPosterIMG , subPosterIMG;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            mainPoster = data.getData();
            mainPosterIMG.setImageURI(mainPoster);
        }
        if (requestCode==200){
            subPoster = data.getData();
            subPosterIMG.setImageURI(subPoster);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        findViewById(R.id.mainEventPoster).setOnClickListener(v -> {
            startActivityForResult(new Intent(Intent.ACTION_PICK  , MediaStore.Images.Media.INTERNAL_CONTENT_URI) , 100);
        });
        findViewById(R.id.subDetailPoster).setOnClickListener(v -> {
            startActivityForResult(new Intent(Intent.ACTION_PICK  , MediaStore.Images.Media.INTERNAL_CONTENT_URI) , 200);
        });


        mainPosterIMG = findViewById(R.id.mainEventPoster);
        subPosterIMG = findViewById(R.id.subDetailPoster);
        EditText title = findViewById(R.id.eventAddTitle);
        EditText description = findViewById(R.id.AddEventDetails);

        findViewById(R.id.uploadEventDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainPoster == null || subPosterIMG == null){

                }
                else {
                    findViewById(R.id.eventAddProgressBar).setVisibility(View.VISIBLE);
                    String path = "Events" + "/"+ title.getText().toString() + "/" + title.getText().toString();
                    StorageReference mStorageMain = FirebaseStorage.getInstance().getReference().child(path+ "_mainPoster.jpg");
                    mStorageMain.putFile(mainPoster)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    StorageReference mStorageSub = FirebaseStorage.getInstance().getReference().child(path + "_subPoster.jpg");
                                    mStorageSub.putFile(subPoster).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Map<String , String> eventDetails = new HashMap<>();
                                            eventDetails.put("title" , title.getText().toString());
                                            eventDetails.put("des" , description.getText().toString());

                                             FirebaseFirestore.getInstance().collection("Events")
                                                    .document(title.getText().toString())
                                                    .set(eventDetails)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            title.setText("");
                                                            description.setText("");
                                                            findViewById(R.id.eventAddProgressBar).setVisibility(View.INVISIBLE);
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {

                                                        }
                                                    });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            findViewById(R.id.eventAddProgressBar).setVisibility(View.INVISIBLE);
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            findViewById(R.id.eventAddProgressBar).setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });
    }
}