package com.example.overthetop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.overthetop.databinding.ActivityInsiderBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Insider extends AppCompatActivity {
    ActivityInsiderBinding bundies;

    // Declare a variable to hold the current Fragment
    Fragment currentFragment;
    private ArrayList<Movies> memorizer = new ArrayList<>();
    private String timerString(long millis) {
        /*
         * It converts the milliseconds to hr:min:sec format as a string.
         */
        @SuppressLint("DefaultLocale") String format = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return format;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundies = ActivityInsiderBinding.inflate(getLayoutInflater());
        setContentView(bundies.getRoot());
        File f = new File(
                "/data/data/com.example.overthetop/shared_prefs/OFFLINE_FILE.xml");
        /*
         * If shared references are available in storage then it will fetch from there
         * else it download from server
         */
        if (f.exists()) {
            SharedPreferences sharedPreferences = this.getSharedPreferences("OFFLINE_FILE", MODE_PRIVATE);
            Gson gson = new Gson();
            Log.d("SharedPref", "Exist");
            for (int i = 1; i <= 7; i++) {
                String object = sharedPreferences.getString(Integer.toString(i),"null");
                memorizer.add(gson.fromJson(object, Movies.class));
            }
            replaceFragment(new MovieFeed(memorizer));
            bundies.menuNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                /*
                 * Navigation driver to switch fragments
                 */
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.movie_feed:
                            replaceFragment(new MovieFeed(memorizer));
                            break;
                        case R.id.library:
                            replaceFragment(new MovieLibrary(memorizer));
                            break;
                        case R.id.profile:
                            replaceFragment(new ProfileFragment());
                            break;
                    }
                    return true;
                }
            });
        } else {
            SharedPreferences sharedPreferences = this.getSharedPreferences("OFFLINE_FILE", MODE_PRIVATE);
            Gson gson = new Gson();
            Log.d("SharedPref", "Don't Exist");
            DatabaseReference database = FirebaseDatabase.getInstance(this.getString(R.string.database_reference))
                    .getReference().child("Movies");
            /*
             * It fetches the data from the firebase database according to the positions
             * that onBindViewHolder passes.
             */
            database.addValueEventListener(new ValueEventListener() {
                /*
                 * Whenever the data set is changed, onDataChange calls the new data and
                 * set into Movie.class.
                 */
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        Movies movie = snap.getValue(Movies.class);
                        movie.setSerial(snap.getKey());
                        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                        retriever.setDataSource(movie.getVideoLink(), new HashMap<String, String>());
                        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                        movie.setDuration(timerString(Long.parseLong(time)));
                        memorizer.add(movie);
                        String serializedObject = gson.toJson(movie);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(movie.getSerial(), serializedObject);
                        Log.d("movie[" + movie.getSerial() + "]", serializedObject);
                        editor.apply();
                        replaceFragment(new MovieFeed(memorizer));
                        bundies.menuNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                            /*
                             * Navigation driver to switch fragments
                             */
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.movie_feed:
                                        replaceFragment(new MovieFeed(memorizer));
                                        break;
                                    case R.id.library:
                                        replaceFragment(new MovieLibrary(memorizer));
                                        break;
                                    case R.id.profile:
                                        replaceFragment(new ProfileFragment());
                                        break;
                                }
                                return true;
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Insider.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void replaceFragment(Fragment frame){
        FragmentManager build = getSupportFragmentManager();
        FragmentTransaction replace = build.beginTransaction();
        replace.replace(R.id.frame_insider, frame);
        replace.commit();
    }
}