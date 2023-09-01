//package com.example.overthetop;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class Downloader {
//
//    public void saveImageFromOnlineLink(Context context, String imageUrl, String imageName) {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(imageUrl)
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//
//            if (response.isSuccessful()) {
//                InputStream inputStream = response.body().byteStream();
//                saveMediaToDirectory(context, inputStream, imageName);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // Method to retrieve an image from the app-specific directory
//    public Bitmap loadImageFromDirectory(Context context, String imageName) {
//        // Get the app-specific directory
//        File directory = context.getFilesDir();
//
//        // Create a file object with the image name
//        File imageFile = new File(directory, imageName);
//
//        try {
//            // Create a FileInputStream to read the image data
//            FileInputStream inputStream = new FileInputStream(imageFile);
//
//            // Decode the input stream into a Bitmap
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//
//            // Close the input stream
//            inputStream.close();
//
//            return bitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    // Method to retrieve a video from the app-specific directory
//    public InputStream loadVideoFromDirectory(Context context, String videoName) {
//        // Get the app-specific directory
//        File directory = context.getFilesDir();
//
//        // Create a file object with the video name
//        File videoFile = new File(directory, videoName);
//
//        try {
//            // Create a FileInputStream to read the video data
//            FileInputStream inputStream = new FileInputStream(videoFile);
//
//            return inputStream;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//}
//// Import the necessary classes