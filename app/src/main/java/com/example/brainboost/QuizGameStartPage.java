package com.example.brainboost;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class QuizGameStartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_game_start_page);

        LinearLayout level1 = findViewById(R.id.level1_layout);
        LinearLayout level2 = findViewById(R.id.level2_layout);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView backArrow = (ImageView) findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizGameStartPage.this, mainPage.class));
            }
        });
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizGameStartPage.this, QuizGame.class));
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizGameStartPage.this, BossLevel.class));
            }
        });
    }
}