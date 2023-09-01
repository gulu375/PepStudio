package com.example.overthetop;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    FirebaseAuth mAuth;

    public boolean inRecord(String username, String password) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        return account != null;
    }

    /*
     * Function to check if the username or password is argumentable
     * TODO: Check if the account exist / find any other way to authenticate
     */
    public boolean validation(String username, String password) {
        if (username.contains(" ") || password.contains(" ")) {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (username.length() * password.length() == 0) {
                Toast.makeText(this, "Fill Up Both Key", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!inRecord(username, password)) {
                Toast.makeText(this, "No Account Exist", Toast.LENGTH_SHORT).show();
                return false;
            } else return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn){
            Log.d("Facebook", "Currently Signed In");
//            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
//            startActivity(new Intent(LoginActivity.this, Insider.class));
        }
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        /*
         * Listener to check if any account exist for
         * the username and password
         * TODO: Develop the authentication function
         *  (If needed)
         */
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct = new Intent(LoginActivity.this, Insider.class);
                startActivity(direct);
                if (!validation(email.getText().toString(), password.getText().toString())) {
                    email.setText("");
                    password.setText("");
                } else {
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Authentication", "Successful");
                                        Intent insider = new Intent(LoginActivity.this, Insider.class);
                                        startActivity(insider);
                                        finish();
                                    } else {
                                        Log.d("Authentication", "Failed");
                                    }
                                }
                            });
                }
            }
        });
        findViewById(R.id.fb_sign_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FacebookLoginAPI fb_login = new FacebookLoginAPI((LoginButton) findViewById(R.id.fb_sign));
                fb_login.loginButton.performClick();
            }
        });
        findViewById(R.id.google_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, GoogleLoginAPI.class));
            }
        });

    }
}