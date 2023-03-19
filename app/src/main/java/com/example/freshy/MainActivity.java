package com.example.freshy;

import android.content.Intent;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        SystemClock.sleep(3000);
        onStart();

    }
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser == null)
        {
            Intent registerintent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(registerintent);
            finish();
        }
        else
        {
            Intent mainintent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(mainintent);
            finish();
        }
    }

}