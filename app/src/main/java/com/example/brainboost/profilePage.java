package com.example.brainboost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class profilePage extends AppCompatActivity {

    ImageView backArrow, logoutIcon;
    TextView nameTextView, emailTextView, logoutText;

    RelativeLayout logoutLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Initialize views
        backArrow = findViewById(R.id.back_arrow);
        logoutIcon = findViewById(R.id.logoutIcon);
        nameTextView = findViewById(R.id.userFullName);
        emailTextView = findViewById(R.id.userEmail);
        logoutText = findViewById(R.id.logout_text);
        logoutLayout = findViewById(R.id.logout_layout);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("userName", "");
        String userEmail = preferences.getString("userEmail", "");

        nameTextView.setText(userName);
        emailTextView.setText(userEmail);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profilePage.this, mainPage.class));
                finish();
            }
        });

        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                startActivity(new Intent(profilePage.this, loginActivity.class));
                finishAffinity();
            }
        });
    }
}
