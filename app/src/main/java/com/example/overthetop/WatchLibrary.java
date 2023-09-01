package com.example.overthetop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class WatchLibrary extends AppCompatActivity {
    RecyclerView movielist;
    ArrayList<Movies> collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_library);
        movielist = findViewById(R.id.watch_movie_list);
        collection = (ArrayList<Movies>) getIntent().getExtras().getSerializable("collection");
        ArrayList<Movies> temp = new ArrayList<>();
        for(Movies movie : collection){
            if(movie.toWatch){
                temp.add(movie);
            }
        }
        SearchListAdapter list = new SearchListAdapter(collection,this);
        list.setCollect(temp);
        movielist.setAdapter(list);
        movielist.setLayoutManager(new LinearLayoutManager(this));
    }
}