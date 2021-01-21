package com.example.gcek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.gcek.maindrawer.hometab.PosterData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.gcek.AppData.PosterBitmapList;
import static com.example.gcek.AppData.PosterList;
import static com.example.gcek.maindrawer.hometab.HomeFragment.setPosters;

public class SplashScreen extends AppCompatActivity {
    public static FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new GetStartingDataOfUser().execute();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext() , FirstActivityWto.class));
            }
        }, 2000);
    }

    private class GetStartingDataOfUser extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
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
        for(PosterData posterData : PosterList ){
            String DownloadingUri = posterData.getNoticeURI();
            new DownloadImage(DownloadingUri).execute();
            Log.d("YDCH" , "Downloaded" + posterData.getTitle() + "pic");
        }
        Log.d("YDCH" , "PICS");
    }
    private static class DownloadImage extends AsyncTask<Void, Void, Void> {
        String uri;
        public DownloadImage(String uri) {
            this.uri = uri;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                PosterBitmapList.add(Picasso.get().load(uri).get());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}