package com.example.brainboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizResult extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView restartBtn;
    private TextView quitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        // Initialize the TextView
        scoreTextView = findViewById(R.id.score);

        // Retrieve the score from the intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        int totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0);

        // Display the score
        String scoreText = score + "/" + totalQuestions;
        scoreTextView.setText(scoreText);

        // Initialize the buttons
        restartBtn = findViewById(R.id.restartBtn);
        quitBtn = findViewById(R.id.shareBtn);

        // Set onClick listeners for the buttons
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartQuiz();
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitQuiz();
            }
        });
    }

    private void restartQuiz() {
        Intent intent = new Intent(QuizResult.this, FirstLessonQuiz.class);
        startActivity(intent);
        finish(); // Close the QuizResult activity
    }

    private void quitQuiz() {
        Intent intent = new Intent(QuizResult.this, mainPage.class);
        startActivity(intent);
        finish(); // Close the QuizResult activity
    }
}
