package com.example.brainboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class mainPage extends AppCompatActivity {

    ImageView profile_icon;
    ImageView first_course;
    ImageView games;
    ImageView game1;
    ImageView game2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        profile_icon = findViewById(R.id.profile);
        first_course = findViewById(R.id.first_course);
        games = findViewById(R.id.games);
        game1 = findViewById(R.id.game1);
        game2 = findViewById(R.id.game2);
        ImageView game3 = findViewById(R.id.game3);
        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainPage.this, profilePage.class));
            }
        });

        first_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainPage.this, FirstCourse.class));
            }
        });
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainPage.this, GamesPage.class));
            }
        });
        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainPage.this, AddPlayers.class));
            }
        });

        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainPage.this, QuizGameStartPage.class));
            }
        });

    }

}