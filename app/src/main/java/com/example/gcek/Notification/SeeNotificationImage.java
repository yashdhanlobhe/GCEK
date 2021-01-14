package com.example.gcek.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.gcek.R;
import com.jsibbold.zoomage.ZoomageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeeNotificationImage extends AppCompatActivity {
    String downloadImageUri;
    Bitmap NotificationImage;
    Toolbar toolbarIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_notification_image);

        toolbarIMG = findViewById(R.id.ShowImageNotice);
        toolbarIMG.setTitle("Notice");
        setSupportActionBar(toolbarIMG);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbarIMG.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        downloadImageUri = getIntent().getStringExtra("NoticeUrl");
        new DownloadFilesTask().execute();

    }
    private class DownloadFilesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("UserImage" , "Starting Downloading");
            NotificationImage=getBitmapFromURL(downloadImageUri);
            Log.d("UserImage" , "Downloaded");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ZoomageView imageView =findViewById(R.id.NotificationImageView);
            imageView.setPadding(0 ,0 ,0 ,0);
            imageView.setZoomable(true);
            imageView.setRestrictBounds(false);
            imageView.setImageBitmap(NotificationImage);
        }
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            return null;
        }
    }

}