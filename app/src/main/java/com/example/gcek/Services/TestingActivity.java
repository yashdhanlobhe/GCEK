package com.example.gcek.Services;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.gcek.R;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.example.gcek.Services.CompressImage.compressimage;

public class TestingActivity extends AppCompatActivity {
    public static final int PICKimg = 100;
    Uri uri;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        mcontext = this;
        OpenGallery();

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
//
//            try {
////                Uri by = compressimage(mcontext , uri , getContentResolver());
////                Log.d("TESTINGACTIVITY" , uri.toString());
//////                Log.d("TESTINGACTIVITY" , String.valueOf(by.length));
//            } catch (IOException | URISyntaxException e) {
//                e.printStackTrace();
//            }
        }
    }
}