package com.example.overthetop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class FavouriteLibrary extends AppCompatActivity {
    ArrayList<Movies> collection;
    RecyclerView movielist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_library);
        movielist = findViewById(R.id.favourite_movie_list);
        collection = (ArrayList<Movies>) getIntent().getExtras().getSerializable("collection");
        ArrayList<Movies> temp = new ArrayList<>();
        for(Movies movie : collection){
            if(movie.isFavourite){
                temp.add(movie);
            }
        }
        SearchListAdapter list = new SearchListAdapter(collection,this);
        list.setCollect(temp);
        movielist.setAdapter(list);
        movielist.setLayoutManager(new LinearLayoutManager(this));
    }
}