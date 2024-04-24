package com.example.brainboost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profilePage extends AppCompatActivity {

    ImageView backArrow, logoutIcon;
    TextView nameTextView, emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Initialize views
        backArrow = findViewById(R.id.back_arrow);
        logoutIcon = findViewById(R.id.logoutIcon);
        nameTextView = findViewById(R.id.userFullName);
        emailTextView = findViewById(R.id.userEmail);

        // Set onClickListener for back arrow
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profilePage.this, mainPage.class));
                finish(); // Finish the current activity to prevent returning to it using the back button
            }
        });

        // Set onClickListener for logout icon
        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear user data (logout)
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Redirect to login page
                startActivity(new Intent(profilePage.this, loginActivity.class));
                finishAffinity(); // Finish all activities in the stack
            }
        });

        // Display user's name and email
        displayUserData();
    }

    private void displayUserData() {
        // Retrieve user data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");

        // Set the retrieved data to the corresponding TextViews
        nameTextView.setText(name);
        emailTextView.setText(email);
    }
}
