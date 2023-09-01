package com.example.overthetop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class SearchFeed extends AppCompatActivity {
    EditText search_bar;
    RecyclerView movielist;
    ArrayList<Movies> collect = new ArrayList<>();
    Map<String, ?> collection;
    ImageButton search_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_feed);
        search_button = findViewById(R.id.search_button);
        search_bar = findViewById(R.id.search_bar);
        movielist = findViewById(R.id.movie_search_list);
        /*
         * SearchListAdapter manages the layout of
         * search feed
         * TODO: Setup the search list (Done)
         */
        SharedPreferences sharedPreferences = SearchFeed.this.getSharedPreferences("OFFLINE_FILE", MODE_PRIVATE);
        Gson gson = new Gson();
        collection = sharedPreferences.getAll();

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collect.clear();
                for (String key : collection.keySet()) {
                    Movies movie = gson.fromJson(collection.get(key).toString(), Movies.class);
                    if(movie.CheckMatch(search_bar.getText().toString())) {
                        collect.add(movie);
                    }
                }
                SearchListAdapter list = new SearchListAdapter(collect,SearchFeed.this);
                list.setCollect(collect);
                movielist.setAdapter(list);
                movielist.setLayoutManager(new LinearLayoutManager(SearchFeed.this));
            }
        });
    }
}