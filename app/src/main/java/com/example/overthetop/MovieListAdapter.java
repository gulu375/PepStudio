package com.example.overthetop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.viewHolder> {
    private Context context;
    private ArrayList<Movies> memorizer = new ArrayList<>();

    public MovieListAdapter(Context context, ArrayList<Movies> memorizer) {
        this.memorizer = memorizer;
        this.context = context;
    }


    /*
     * It hold the current view group for the
     * user that is currently present in the screen.
     */
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_layout, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    /*
     * It binds a new view group when the current view group is changed.
     * It recycles the eliminated view into a new view and binds new data.
     */
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        /*
         * Retrieves the video duration by the fetching
         * the metadata from the video_link.
         */
        Movies movie = memorizer.get(position);
        Glide.with(context)
                .asBitmap()
                .load(movie.Poster_Vertical)
                .into(holder.img_url);
        /*
         * Redirects to the respective movie description of the
         * movie_card clicked on.
         */
        holder.itemView.findViewById(R.id.slide_movie_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDescription.class);
                intent.putExtra("collection", movie);
                view.getContext().startActivity(intent);
            }
        });
    }

    /*
     * It returns the number children in present in the database.
     * TODO: Make it automatically Detect the number of objects are present
     *  in the Movies branch.
     */
    @Override
    public int getItemCount() {
        Log.d("memorizer.size()", Integer.toString(memorizer.size()));
        return memorizer.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img_url;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img_url = itemView.findViewById(R.id.poster);
        }
    }
}

