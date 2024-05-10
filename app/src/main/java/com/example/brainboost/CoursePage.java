package com.example.brainboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CoursePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_page);

        LinearLayout course1 = findViewById(R.id.course1_layout);
        LinearLayout course2 = findViewById(R.id.course2_layout);
        CardView course3 = findViewById(R.id.course3_cardview);

        course1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoursePage.this, FirstCourse.class));
            }
        });

        course2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CoursePage.this, "You must finish the first module to start the second!", Toast.LENGTH_LONG).show();
            }
        });

        course3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CoursePage.this, "You must finish the first and course to start the third!", Toast.LENGTH_LONG).show();
            }
        });

    }
}