package com.example.brainboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class mainPage extends AppCompatActivity {

    ImageView profile_icon, first_course, second_course, third_course, games, game1, game2, game3, courseIcon, leaderboard_icon;
    TextView seeAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        profile_icon = findViewById(R.id.profile);
        first_course = findViewById(R.id.first_course);
        second_course = findViewById(R.id.second_course);
        third_course = findViewById(R.id.third_course);

        games = findViewById(R.id.games);
        game1 = findViewById(R.id.game1);
        game2 = findViewById(R.id.game2);
        game3 = findViewById(R.id.game3);
        courseIcon = findViewById(R.id.course);

        seeAll = findViewById(R.id.seeAllText);

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

        second_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainPage.this, "You must finish the first module to start the second!", Toast.LENGTH_LONG).show();
            }
        });

        third_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainPage.this, "You must finish the first and course to start the third!", Toast.LENGTH_LONG).show();
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

        game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainPage.this, PuzzleGameImage.class));
            }
        });

        courseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainPage.this, CoursePage.class));
            }
        });

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainPage.this, CoursePage.class));
            }
        });
    }

}