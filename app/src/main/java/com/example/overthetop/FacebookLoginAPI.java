package com.example.overthetop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class FacebookLoginAPI extends AppCompatActivity {
    private FirebaseAuth mAuth;
    LoginButton loginButton;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";

    public FacebookLoginAPI(LoginButton loginButton) {
        mAuth = FirebaseAuth.getInstance();
        this.loginButton = loginButton;
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("Facebook Login", "Successful");
                startActivity(new Intent(FacebookLoginAPI.this, Insider.class));
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("Facebook Login", "Cancelled");

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("Facebook Login", exception.getMessage());

            }
        });
    }

}
