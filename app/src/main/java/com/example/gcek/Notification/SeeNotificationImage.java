package com.example.gcek.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.gcek.R;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeeNotificationImage extends AppCompatActivity {
    String downloadImageUri;
    Bitmap NotificationImage;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_notification_image);
        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.show();
        downloadImageUri = getIntent().getStringExtra("NoticeUrl");
        new DownloadFilesTask().execute();

    }
    private class DownloadFilesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("UserImage" , "Starting Downloading");
            try {
                NotificationImage=getBitmapFromURL(downloadImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("UserImage" , "Downloaded");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pd.dismiss();
            ZoomageView imageView =findViewById(R.id.NotificationImageView);
            imageView.setPadding(0 ,0 ,0 ,0);
            imageView.setZoomable(true);
            imageView.setRestrictBounds(false);
            imageView.setImageBitmap(NotificationImage);
        }
    }


    public static Bitmap getBitmapFromURL(String src) throws IOException {
       return Picasso.get().load(src).get();
    }
}