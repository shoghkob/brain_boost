package com.example.brainboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class profilePage extends AppCompatActivity {

    ImageView backArrow, logoutIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        backArrow = (ImageView) findViewById(R.id.back_arrow);
        logoutIcon = findViewById(R.id.logoutIcon);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profilePage.this, mainPage.class));
            }
        });

        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profilePage.this, loginActivity.class));
            }
        });
    }
}