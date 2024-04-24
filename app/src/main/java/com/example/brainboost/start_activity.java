package com.example.brainboost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button start_btn;
        start_btn = findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the user is registered
                if (isUserRegistered()) {
                    // User is registered, navigate to MainActivity
                    startActivity(new Intent(start_activity.this, mainPage.class));
                } else {
                    // User is not registered, navigate to loginActivity
                    startActivity(new Intent(start_activity.this, loginActivity.class));
                }
            }
        });
    }

    // Method to check if the user is registered
    private boolean isUserRegistered() {
        // Retrieve user data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLogged", false); // Assuming "isLogged" flag indicates user registration status
    }
}
