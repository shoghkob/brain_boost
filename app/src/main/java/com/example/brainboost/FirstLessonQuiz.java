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
    private TextView quitButton;
    private TextView nextButton;

    // Variables for quiz logic
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String[] questions = {
            "What is AI?",
            "Which of this is not the branch of AI?",
            "What is NLP using?",
            "What are Expert Systems doing?",
            "How does Fuzzy logic work?"
    };
    private String[][] options = {
            {"Advanced Internet", "Artificial Invention", "Automated Intelligence", "Artificial Intelligence"},
            {"Game playing", "Computer vision", "Expert systems", "Video editing"},
            {"Algorithms", "Formulas", "AI", "Machines"},
            {"Generating insights", "Automating tasks", "Providing expert knowledge", "Processing data"},
            {"Crunching numbers", "Simplifying complex problems", "Randomizing outcomes", "Logical imitation"},
    };
    private int[] correctAnswerIndices = {3, 3, 0, 2, 3};
    private boolean userAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_lesson_quiz);

        timerTextView = findViewById(R.id.timer);
        questionCounterTextView = findViewById(R.id.questionCounter);
        questionTextView = findViewById(R.id.question);
        option1TextView = findViewById(R.id.option_1);
        option2TextView = findViewById(R.id.option_2);
        option3TextView = findViewById(R.id.option_3);
        option4TextView = findViewById(R.id.option_4);
        quitButton = findViewById(R.id.textView7);
        nextButton = findViewById(R.id.textView8);

        setUpQuiz();

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitQuiz();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userAnswered) {
                    loadNextQuestion();
                }
            }
        });
    }

    private void setUpQuiz() {
        displayQuestion();

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
    }

    private void displayQuestion() {
        option1TextView.setBackgroundResource(R.drawable.option_unselected);
        option2TextView.setBackgroundResource(R.drawable.option_unselected);
        option3TextView.setBackgroundResource(R.drawable.option_unselected);
        option4TextView.setBackgroundResource(R.drawable.option_unselected);

        questionTextView.setText(questions[currentQuestionIndex]);
        option1TextView.setText(options[currentQuestionIndex][0]);
        option2TextView.setText(options[currentQuestionIndex][1]);
        option3TextView.setText(options[currentQuestionIndex][2]);
        option4TextView.setText(options[currentQuestionIndex][3]);

        questionCounterTextView.setText((currentQuestionIndex + 1) + "/" + questions.length);

        userAnswered = false;
    }

    private void checkAnswer(int selectedOptionIndex) {
        if (userAnswered) {
            return;
        }

        int correctAnswerIndex = correctAnswerIndices[currentQuestionIndex];

        if (selectedOptionIndex == correctAnswerIndex) {
            score++;
            switch (selectedOptionIndex) {
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
        } else {
            // Wrong answer
            switch (selectedOptionIndex) {
                case 0:
                    option1TextView.setBackgroundResource(R.drawable.button_1);
                    break;
                case 1:
                    option2TextView.setBackgroundResource(R.drawable.button_1);
                    break;
                case 2:
                    option3TextView.setBackgroundResource(R.drawable.button_1);
                    break;
                case 3:
                    option4TextView.setBackgroundResource(R.drawable.button_1);
                    break;
            }

            // Highlight correct answer
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
        }

        userAnswered = true;
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            displayQuestion();
        } else {
            finishQuiz();
        }
    }

    private void quitQuiz() {
        Intent intent = new Intent(FirstLessonQuiz.this, mainPage.class);
        startActivity(intent);
        finish();
    }

    private void finishQuiz() {
        Intent intent = new Intent(FirstLessonQuiz.this, QuizResult.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.length);
        startActivity(intent);
        finish();
    }
}
