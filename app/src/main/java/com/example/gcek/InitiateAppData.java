package com.example.gcek;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;

public  class InitiateAppData {
    public  static FirebaseAuth mfirebaseauth;
//    public  static FirebaseDatabase mfirebaseDatabase;
    public  static FirebaseFirestore mfirebasefirestore;
    public  static FirebaseStorage mfirebasestorage;
    public static FirebaseMessaging mfirebaseMessaging;

    public static  void startFirebaseServices(){
        mfirebaseauth = FirebaseAuth.getInstance();
        mfirebasefirestore = FirebaseFirestore.getInstance();
        mfirebasestorage = FirebaseStorage.getInstance();
        mfirebaseMessaging = FirebaseMessaging.getInstance();
    }
}
