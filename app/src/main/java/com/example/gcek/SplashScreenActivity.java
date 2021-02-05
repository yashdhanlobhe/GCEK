package com.example.gcek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.gcek.MainDrawer.MainHomeTab.PosterData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.gcek.AppDownloadedDataWhileRunning.PosterBitmapList;
import static com.example.gcek.AppDownloadedDataWhileRunning.PosterList;
import static com.example.gcek.MainDrawer.MainHomeTab.HomeFragment.setPosters;

public class SplashScreenActivity extends AppCompatActivity {
    public static FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new InitiateApp().execute();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext() , MainActivityWithoutLogin.class));
                finish();
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        }, 2000);
    }

    private class InitiateApp extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            InitiateAppData.startFirebaseServices();
            getHomePosterData();
            return null;
        }
    }
    private void getHomePosterData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference data = firebaseDatabase.getReference().child("HomePoster").getRef();
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PosterList = new ArrayList<>();
                for(DataSnapshot db : snapshot.getChildren()){
                    PosterData nd =db.getValue(PosterData.class);
                    PosterList.add(nd);
                    Log.d("YDCH" , "downloadedDATA");
                }
                try {
                    setPosters();
                }catch (Exception e){
                    Log.e("ERROR POSTER DATA" , e.getMessage());
                }
                DownloadPosterPics();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("YDCH" , error.getDetails()  );
            }
        });
    }
    private void DownloadPosterPics() {
        PosterBitmapList = new ArrayList<>();
        List<String> picsUri = new ArrayList<>();
        for(PosterData posterData : PosterList ){
            String DownloadingUri = posterData.getNoticeURI();
            picsUri.add(DownloadingUri);
            Log.d("YDCH" , "Downloaded" + posterData.getTitle() + "pic");
        }
        new DownloadImages(picsUri).execute();
        Log.d("YDCH" , "PICS");
    }
    private static class DownloadImages extends AsyncTask<Void, Void, Void> {
        List<String> uri;
        public DownloadImages(List uri) {
            this.uri = uri;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (String uriOfIMAGE : uri){
                    PosterBitmapList.add(Picasso.get().load(uriOfIMAGE).get());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                setPosters();
            }catch (Exception e){
                Log.e("ERROR POSTER DATA" , e.getMessage());
            }
        }
    }
}