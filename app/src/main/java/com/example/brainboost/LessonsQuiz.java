package com.example.brainboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class LessonsQuiz extends AppCompatActivity {

    private TextView lesson_title, what_is_ai_text, textView9, textView10;
    private ImageView imageView2;
    private Button nextButton, backButton;
    private int currentPage = 1;
    private int totalPages = 5; // Assuming there are 5 pages

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_quiz);

        lesson_title = findViewById(R.id.lesson_title);
        what_is_ai_text = findViewById(R.id.what_is_ai_text);
        textView9 = findViewById(R.id.learning_material_text);
        imageView2 = findViewById(R.id.imageView2);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage < totalPages) {
                    currentPage++;
                    updateUI();
                } else {
                    // Start quiz
                    startQuiz();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage > 1) {
                    currentPage--;
                    updateUI();
                }
            }
        });

        updateUI();
    }

    private void updateUI() {
        switch (currentPage) {
            case 1:
                lesson_title.setText(getString(R.string.lesson1_name));
                what_is_ai_text.setText(getString(R.string.what_is_ai_text));
                textView9.setText(getString(R.string.what_is_ai));
                imageView2.setImageResource(R.drawable.lesson1_img1);
                break;
            case 2:
                lesson_title.setText(getString(R.string.lesson1_name));
                what_is_ai_text.setText(getString(R.string.lesson2_subname));
                textView9.setText(getString(R.string.lesson2_desc));
                imageView2.setImageResource(R.drawable.course1_lesson2_img);
                break;
            case 3:
                lesson_title.setText(getString(R.string.lesson1_name));
                what_is_ai_text.setText(getString(R.string.lesson3_subname));
                textView9.setText(getString(R.string.lesson3_desc));
                imageView2.setImageResource(R.drawable. course1_lesson3_img);
                break;
            case 4:
                lesson_title.setText(getString(R.string.lesson1_name));
                what_is_ai_text.setText(getString(R.string.lesson4_subname));
                textView9.setText(getString(R.string.lesson4_desc));
                imageView2.setImageResource(R.drawable.course1_lesson4_img);
                break;
            case 5:
                lesson_title.setText(getString(R.string.lesson1_name));
                what_is_ai_text.setText(getString(R.string.lesson5_subname));
                textView9.setText(getString(R.string.lesson5_desc));
                imageView2.setImageResource(R.drawable.course1_lesson5_img);
                break;
            default:
                break;
        }

        if (currentPage == 1) {
            backButton.setVisibility(View.INVISIBLE);
        } else {
            backButton.setVisibility(View.VISIBLE);
        }

        if (currentPage == totalPages) {
            nextButton.setText("Start Quiz");
        } else {
            nextButton.setText("Next");
        }
    }

    private void startQuiz() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LessonsQuiz.this, FirstLessonQuiz.class));
            }
        });
    }
}
