package ru.arvalon.mytraining.converters;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by arvalon on 06.07.2016.
 */
public class ImageConverter {
    public static byte[] convertImage(Bitmap inputImage){
        if (inputImage!=null){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            inputImage.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bArray = bos.toByteArray();
            return bArray;
        }
        return null;
    }
}