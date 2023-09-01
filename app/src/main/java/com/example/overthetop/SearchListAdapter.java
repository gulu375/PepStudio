package com.example.overthetop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    ArrayList<Movies> collect = new ArrayList<>();
    Context context;
    View main;

    public SearchListAdapter(ArrayList<Movies> collect, Context context) {
        this.collect = collect;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_slides, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    /*
     * Bind the search result with the recycler view
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
        int position = index;
        holder.movie_name.setText(collect.get(position).getMovie_Name());
        Glide.with(context)
                .asBitmap()
                .load(collect.get(position).getPoster_Vertical())
                .into(holder.poster);
        holder.genre.setText("Genre: " + collect.get(position).getGenre());
        holder.duraton.setText("Duration: " + collect.get(position).getDuration());
        holder.imdb.setText("IMDB: "+Float.toString(collect.get(position).getIMDB()));
        holder.year.setText("Year: "+Integer.toString(collect.get(position).getYear()));
        holder.tomatoes.setText("Rotten Tomatoes: "+Integer.toString(collect.get(position).getRotten_Tomatoes()) + "%");
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent videoplayer = new Intent(context, MoviePlayer.class);
                videoplayer.putExtra("collection", collect.get(position));
                context.startActivity(videoplayer);
            }
        });
        holder.itemView.findViewById(R.id.slide_library_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDescription.class);
                intent.putExtra("collection", collect.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    public void setCollect(ArrayList<Movies> collect) {
        this.collect = collect;
    }

    @Override
    public int getItemCount() {
        return collect.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView movie_name, genre, imdb, tomatoes, year, duraton;
        ImageView poster;
        Button play;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_name = (itemView).findViewById(R.id.movie_name_searchslide);
            duraton = itemView.findViewById(R.id.duration_searchslide);
            genre = (itemView).findViewById(R.id.genre_searchslide);
            imdb = (itemView).findViewById(R.id.imdb_searchslide);
            tomatoes = (itemView).findViewById(R.id.rotten_tomato_searchslide);
            year = (itemView).findViewById(R.id.year_searchslide);
            poster = (itemView).findViewById(R.id.poster_searchslide);
            play = (itemView).findViewById(R.id.play_searchslide);
            main = (itemView).findViewById(R.id.search_feed);
        }
    }
}

