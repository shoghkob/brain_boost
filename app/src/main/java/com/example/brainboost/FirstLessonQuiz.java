package com.example.brainboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FirstLessonQuiz extends AppCompatActivity {

    // Define your Views
    private TextView timerTextView;
    private TextView questionCounterTextView;
    private TextView questionTextView;
    private TextView option1TextView;
    private TextView option2TextView;
    private TextView option3TextView;
    private TextView option4TextView;

    // Variables for quiz logic
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String[] questions = {"Who invented Android Studio?", "What is the capital of France?"};
    private String[][] options = {
            {"James Gosling", "Sergey Brin", "Larry Page", "JetBrains"},
            {"London", "Berlin", "Paris", "Rome"}
    };
    private int[] correctAnswerIndices = {3, 2}; // Correct answer indices for each question
    private boolean userAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_lesson_quiz);

        // Initialize your Views
        timerTextView = findViewById(R.id.timer);
        questionCounterTextView = findViewById(R.id.questionCounter);
        questionTextView = findViewById(R.id.question);
        option1TextView = findViewById(R.id.option_1);
        option2TextView = findViewById(R.id.option_2);
        option3TextView = findViewById(R.id.option_3);
        option4TextView = findViewById(R.id.option_4);

        // Set up the quiz
        setUpQuiz();
    }

    private void setUpQuiz() {
        // Set initial question and options
        displayQuestion();

        // Set onClick listeners for options
        option1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0);
            }
        });
        option2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });
        option3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });
        option4TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });

        // Reset userAnswered for each new question
        userAnswered = false;
    }

    private void displayQuestion() {
        // Reset all answer backgrounds to default
        option1TextView.setBackgroundResource(R.drawable.option_unselected);
        option2TextView.setBackgroundResource(R.drawable.option_unselected);
        option3TextView.setBackgroundResource(R.drawable.option_unselected);
        option4TextView.setBackgroundResource(R.drawable.option_unselected);

        // Display current question and options
        questionTextView.setText(questions[currentQuestionIndex]);
        option1TextView.setText(options[currentQuestionIndex][0]);
        option2TextView.setText(options[currentQuestionIndex][1]);
        option3TextView.setText(options[currentQuestionIndex][2]);
        option4TextView.setText(options[currentQuestionIndex][3]);

        // Update question counter
        questionCounterTextView.setText((currentQuestionIndex + 1) + "/" + questions.length);
    }

    private void checkAnswer(int selectedOptionIndex) {
        if (userAnswered) {
            // If the user has already answered, do nothing
            return;
        }

        int correctAnswerIndex = correctAnswerIndices[currentQuestionIndex];

        // Reset all answer backgrounds to default
        option1TextView.setBackgroundResource(R.drawable.option_unselected);
        option2TextView.setBackgroundResource(R.drawable.option_unselected);
        option3TextView.setBackgroundResource(R.drawable.option_unselected);
        option4TextView.setBackgroundResource(R.drawable.option_unselected);

        // Reset background of second answer choice if first choice is selected
        if (selectedOptionIndex == 0) {
            option2TextView.setBackgroundResource(R.drawable.option_unselected);
            option3TextView.setBackgroundResource(R.drawable.option_unselected);
            option4TextView.setBackgroundResource(R.drawable.option_unselected);
        }

        // Set background of selected answer choice
        switch (selectedOptionIndex) {
            case 0:
                option1TextView.setBackgroundResource(selectedOptionIndex == correctAnswerIndex ? R.drawable.button_2 : R.drawable.button_1);
                break;
            case 1:
                option2TextView.setBackgroundResource(selectedOptionIndex == correctAnswerIndex ? R.drawable.button_2 : R.drawable.button_1);
                break;
            case 2:
                option3TextView.setBackgroundResource(selectedOptionIndex == correctAnswerIndex ? R.drawable.button_2 : R.drawable.button_1);
                break;
            case 3:
                option4TextView.setBackgroundResource(selectedOptionIndex == correctAnswerIndex ? R.drawable.button_2 : R.drawable.button_1);
                break;
        }

        // Change background of correct answer to button_2.xml
        switch (correctAnswerIndex) {
            case 0:
                option1TextView.setBackgroundResource(R.drawable.button_2);
                break;
            case 1:
                option2TextView.setBackgroundResource(R.drawable.button_2);
                break;
            case 2:
                option3TextView.setBackgroundResource(R.drawable.button_2);
                break;
            case 3:
                option4TextView.setBackgroundResource(R.drawable.button_2);
                break;
        }

        userAnswered = true;

        // Move to the next question or finish the quiz
        if (currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            // Reset userAnswered for each new question
            userAnswered = false;
            displayQuestion();
        } else {
            // Finish the quiz
            finishQuiz();
        }
    }

    private void finishQuiz() {
        startActivity(new Intent(FirstLessonQuiz.this, QuizResult.class));
    }
}
