package com.example.overthetop;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MovieDescription extends AppCompatActivity {
    private ImageView poster;
    private TextView year, imdb, genre, rotten_tomatoes, director, story, cast, movie_name, metacritic, distributor, duration;
    private FloatingActionButton play_button;
    private Movies object;
    Uri videoUri;
    ScrollView sv;
    private ImageButton favourite, download, watch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);
        favourite = findViewById(R.id.favourite);
        download = findViewById(R.id.downloads);
        sv = findViewById(R.id.scorll_description);
        watch = findViewById(R.id.to_watch);
        genre = findViewById(R.id.genre);
        poster = findViewById(R.id.horizontal_poster);
        movie_name = findViewById(R.id.movie_name);
        play_button = findViewById(R.id.play_button);
        duration = findViewById(R.id.duration);
        year = findViewById(R.id.year);
        imdb = findViewById(R.id.imdb);
        metacritic = findViewById(R.id.metacritic);
        rotten_tomatoes = findViewById(R.id.rotten_tomatoes);
        director = findViewById(R.id.director);
        story = findViewById(R.id.story);
        cast = findViewById(R.id.cast);
        distributor = findViewById(R.id.Distributor);
        Intent list = getIntent();
        object = (Movies) list.getExtras().getSerializable("collection");
        Glide.with(this)
                .asBitmap()
                .load(object.Poster_Horizontal)
                .into(poster);
        genre.setText(object.getGenre());
        duration.setText(object.getDuration());
        movie_name.setText(object.getMovie_Name());
        year.setText(Integer.toString(object.getYear()));
        imdb.setText(Float.toString(object.getIMDB()));
        metacritic.setText(Integer.toString(object.getMetacritic()));
        rotten_tomatoes.setText(Integer.toString(object.getRotten_Tomatoes()) + "%");
        director.setText(object.Director);
        story.setText(object.Story);
        distributor.setText(object.Distributer);
        cast.setText(object.Cast);
        /*
         * On click of play_button, redirects to the video player
         * and plays the video
         */
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent videoplayer = new Intent(MovieDescription.this, MoviePlayer.class);
                videoplayer.putExtra("collection", object);
                startActivity(videoplayer);
            }
        });
        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("OFFLINE_FILE", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                if (object.toWatch) {
                    object.toWatch = false;
//                      TODO: Remove from watchlist
//                    sharedPreferences.edit().remove(object.getSerial()).commit();
                    String serializedObject = gson.toJson(object);
                    editor.putString(object.getSerial(), serializedObject);
                    editor.apply();
                    watch.setImageDrawable(getDrawable(R.drawable.baseline_remove_red_eye_24));
                } else {
                    object.toWatch = true;
//                      TODO: Add to watchlist
                    /* Insertion in watch list */
                    String serializedObject = gson.toJson(object);
                    editor.putString(object.getSerial(), serializedObject);
                    editor.apply();
                    watch.setImageDrawable(getDrawable(R.drawable.baseline_remove_red_eye_24_active));
                }
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("OFFLINE_FILE", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                if (object.isFavourite) {
                    object.isFavourite = false;
//                  TODO: Remove from favourite list (Done)
                    /* Delete from favourite list */
                    String serializedObject = gson.toJson(object);
                    editor.putString(object.getSerial(), serializedObject);
                    editor.apply();
                    favourite.setImageDrawable(getDrawable(R.drawable.baseline_favorite_24));
                } else {
                    object.isFavourite = true;
//                  TODO: Add to favourite list (Done)
                    /* Insertion in favourite list*/
                    String serializedObject = gson.toJson(object);
                    editor.putString(object.getSerial(), serializedObject);
                    editor.apply();
                    favourite.setImageDrawable(getDrawable(R.drawable.baseline_favorite_24_active));
                }
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPreferences = getSharedPreferences("OFFLINE_FILE", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            @Override
            public void onClick(View view) {
                if (object.toDownload) {
                    object.toDownload = false;
//                  TODO: Remove from Download list
                    download.setImageDrawable(getDrawable(R.drawable.baseline_arrow_circle_down_24));
                } else {
                    object.toDownload = true;
//                  TODO: Add to Download list
                    download.setImageDrawable(getDrawable(R.drawable.baseline_arrow_circle_down_27_active));
//
                }
            }
        });
        if (!object.isFavourite) {
            favourite.setImageDrawable(getDrawable(R.drawable.baseline_favorite_24));
        } else {
            favourite.setImageDrawable(getDrawable(R.drawable.baseline_favorite_24_active));
        }
        if (!object.toWatch) {
            watch.setImageDrawable(getDrawable(R.drawable.baseline_remove_red_eye_24));
        } else {
            watch.setImageDrawable(getDrawable(R.drawable.baseline_remove_red_eye_24_active));
        }
    }

}