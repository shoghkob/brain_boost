package com.example.brainboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FirstCourse extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_course);

        CardView lesson1 = findViewById(R.id.lesson1);
        CardView secondLesson = findViewById(R.id.second_lesson);
        LinearLayout thirdLesson = findViewById(R.id.third_lesson);
        CardView forthLesson = findViewById(R.id.forth_lesson);
        CardView fifthLesson = findViewById(R.id.fifth_lesson);
        CardView sixthLesson = findViewById(R.id.sixth_lesson);
        CardView seventhLesson = findViewById(R.id.seventh_lesson);
        LinearLayout eighthLesson = findViewById(R.id.eight_lesson);
        CardView ninthLesson = findViewById(R.id.ninth_lesson);
        CardView tenthLesson = findViewById(R.id.tenth_lesson);

        lesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstCourse.this, LessonsQuiz.class));
            }
        });

        View.OnClickListener lockedLessonListener = v -> {
            Toast.makeText(FirstCourse.this, "You need to finish the first lesson with a score of 5/5 to unlock this lesson.", Toast.LENGTH_LONG).show();
        };

        secondLesson.setOnClickListener(lockedLessonListener);
        thirdLesson.setOnClickListener(lockedLessonListener);
        forthLesson.setOnClickListener(lockedLessonListener);
        fifthLesson.setOnClickListener(lockedLessonListener);
        sixthLesson.setOnClickListener(lockedLessonListener);
        seventhLesson.setOnClickListener(lockedLessonListener);
        eighthLesson.setOnClickListener(lockedLessonListener);
        ninthLesson.setOnClickListener(lockedLessonListener);
        tenthLesson.setOnClickListener(lockedLessonListener);
    }
}
