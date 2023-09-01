package com.example.overthetop;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FetchByReference {
    Context context;
    private String references;
    private String[] Filtered_References;
    private ArrayList<Movies> collection = new ArrayList<>();

    public FetchByReference(String references, Context context) {
        this.references = references;
        this.context = context;
        Disintigrate();
        FetchFromDatabase();
    }

    private void Disintigrate() {
        Filtered_References = references.split("~");
    }

    public ArrayList<Movies> getCollection(){
        return collection;
    }

    private void FetchFromDatabase() {
        for (String ref : Filtered_References) {
            Log.d("Serial", ref);
            FirebaseDatabase.getInstance(context.getString(R.string.database_reference))
                    .getReference().child("Movies").child(ref)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            collection.add(snapshot.getValue(Movies.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }
    }


}
