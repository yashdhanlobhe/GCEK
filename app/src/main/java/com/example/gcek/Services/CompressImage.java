package com.example.gcek.Services;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;


public class CompressImage {

    public static byte[] compressimage(Context context, Uri uri, ContentResolver contentResolver) throws IOException, URISyntaxException {

        Bitmap bmp = MediaStore.Images.Media.getBitmap(contentResolver, uri);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 10 , baos);
        byte[] data = baos.toByteArray();

        return  data;
    }
}
