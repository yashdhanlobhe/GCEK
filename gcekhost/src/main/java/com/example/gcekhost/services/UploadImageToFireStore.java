package com.example.gcekhost.services;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadImageToFireStore {
    private static String DownloadURI;
    private static StorageReference mStorageRef;
    public static String uploadImageToFirestore(Uri UploadLocalUri , String uploadPathInFireStorewithExtention ){
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
        return DownloadURI;
    }
}
