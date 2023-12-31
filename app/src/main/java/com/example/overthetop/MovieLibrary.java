package com.example.overthetop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieLibrary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieLibrary extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static ArrayList<Movies> memorizer = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieLibrary(ArrayList<Movies> memorizer) {
        // Required empty public constructor
        this.memorizer = memorizer;
    }

    /*
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieLibrary.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieLibrary newInstance(String param1, String param2) {
        MovieLibrary fragment = new MovieLibrary(memorizer);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_library, container, false);
        view.findViewById(R.id.favourite_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fav = new Intent(getContext(), FavouriteLibrary.class);
                fav.putExtra("collection", memorizer);
                startActivity(fav);
            }
        });
        view.findViewById(R.id.to_watch_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent watch = new Intent(getContext(), WatchLibrary.class);
                watch.putExtra("collection", memorizer);
                startActivity(watch);
            }
        });
        return view;
    }
}