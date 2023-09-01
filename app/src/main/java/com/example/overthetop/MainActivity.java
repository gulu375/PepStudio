package com.example.overthetop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button getting_started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getting_started = findViewById(R.id.getting_started);
        /*
         * For now it will start direct to the insider activity
         * as authentication is not necessary now
         */
        /*
         * Check if the user is signed in or not
         * if not:
         *    Redirects to the login page
         * else:
         *    Redirects to the home page
         * TODO: Make the authentication working
         */
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
        /*
         * If no account not exist then redirects to
         * the registration page
         */
        getting_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(acc != null){
                if(true){
                    Intent inside = new Intent(MainActivity.this, Insider.class);
                    startActivity(inside);
                    finish();
                } else {
                    Intent login_page = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(login_page);
                    finish();
                }

            }
        });

    }
}
