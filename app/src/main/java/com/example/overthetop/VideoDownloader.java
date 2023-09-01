package com.example.overthetop;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VideoDownloader {

    public static String downloadVideo(Context context, String videoUrl, String fileName) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_MOVIES), fileName);
        try {
            URL url = new URL(videoUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();

            // Create a file in the app's specific storage directory
            FileOutputStream outputStream = new FileOutputStream(file);

            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
            // Add the downloaded video to the media library
//            MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()},
//                    new String[]{"video/*"}, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}
