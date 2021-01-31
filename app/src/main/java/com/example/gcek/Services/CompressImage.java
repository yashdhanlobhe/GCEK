package com.example.gcek.Services;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.tinify.Options;
import com.tinify.Source;
import com.tinify.Tinify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import id.zelory.compressor.Compressor;

public class CompressImage {

    public static String compressimage(Context context , File file) throws IOException {

        File compressedImage = new Compressor(context)
                .setMaxWidth(640)
                .setMaxHeight(480)
                .setQuality(75)
                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                .compressToFile(file);

        return compressedImage.getAbsolutePath();
    }
}
