package com.example.gcek.resources;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GetBarcode {
    public static Bitmap getBarcodeFromString(String str){
        Bitmap barcodeBitmap ;

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(str , BarcodeFormat.CODE_39 , 1000,100 , null);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        barcodeBitmap = barcodeEncoder.createBitmap(bitMatrix);

        return barcodeBitmap;
    }
}
