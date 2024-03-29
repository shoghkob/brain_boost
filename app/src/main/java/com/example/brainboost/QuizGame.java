package com.example.brainboost;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizGame extends AppCompatActivity {
    private int presCounter = 0;
    private int maxPresCounter;
    private String textAnswer;
    private List<TextView> addedTextViews = new ArrayList<>();
    private Map<TextView, Integer> originalPositions = new HashMap<>();
    private List<String> questions = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private int currentQuestionIndex = 0;

    TextView textScreen, textQuestion, textTitle;
    Animation smallbigforth;

    EditText inputAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smallbigforth);

        textScreen = findViewById(R.id.textScreen);
        textTitle = findViewById(R.id.textTitle);
        textQuestion = findViewById(R.id.textQuestion);
        inputAnswer = findViewById(R.id.editText);

        initializeQuestions();

        textTitle.setText("Letters");

        inputAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTextViews();
            }
        });

        loadQuestion();
    }

    private void initializeQuestions() {
        questions.add("What is the difference between narrow AI and general AI?");
        answers.add(getResources().getString(R.string.level1_answer1));

        questions.add("Define 'algorithm'.");
        answers.add(getResources().getString(R.string.level1_answer2));

        questions.add("What are some examples of narrow AI?");
        answers.add(getResources().getString(R.string.level1_answer3));

        questions.add("What is the purpose of AI in autonomous vehicles?");
        answers.add(getResources().getString(R.string.level1_answer4));

        questions.add("What does RNN stand for?");
        answers.add(getResources().getString(R.string.level1_answer5));

        questions.add("What is neural network?");
        answers.add(getResources().getString(R.string.level1_answer6));

        questions.add("Name one AI programming language.");
        answers.add(getResources().getString(R.string.level1_answer7));

        questions.add("How does AI work?");
        answers.add(getResources().getString(R.string.level1_answer8));

        questions.add("What is the Turing Test?");
        answers.add(getResources().getString(R.string.level1_answer9));

        questions.add("Risks of AI?");
        answers.add(getResources().getString(R.string.level1_answer10));
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            String questionNumber = "Question " + (currentQuestionIndex + 1);
            textScreen.setText(questionNumber);

            textQuestion.setText(questions.get(currentQuestionIndex));
            textAnswer = answers.get(currentQuestionIndex);
            maxPresCounter = textAnswer.length();
            setupQuestion(textAnswer);
        } else {
            openNewActivity();
        }
    }
    private void openNewActivity() {
        Intent intent = new Intent(this, QuizGameStartPage.class);
        startActivity(intent);
        finish();
    }


    private void setupQuestion(String answer) {
        String[] keys_first_line = answer.substring(0, 5).split("");
        String[] keys_second_line = answer.substring(5).split("");

        int extraLettersCount = 10 - answer.length();
        List<String> allLetters = new ArrayList<>();
        Collections.addAll(allLetters, keys_first_line);
        Collections.addAll(allLetters, keys_second_line);

        List<String> allPossibleLetters = new ArrayList<>();
        Collections.addAll(allPossibleLetters, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        allPossibleLetters.removeAll(allLetters);

        Collections.shuffle(allPossibleLetters);
        List<String> extraLetters = allPossibleLetters.subList(0, extraLettersCount);

        List<String> allKeys = new ArrayList<>();
        Collections.addAll(allKeys, keys_first_line);
        Collections.addAll(allKeys, keys_second_line);
        allKeys.addAll(extraLetters);

        Collections.shuffle(allKeys);

        LinearLayout layoutParent1 = findViewById(R.id.layoutParent1);
        LinearLayout layoutParent2 = findViewById(R.id.layoutParent2);
        layoutParent1.removeAllViews();
        layoutParent2.removeAllViews();

        for (int i = 0; i < 5; i++) {
            TextView textView = addView(layoutParent1, allKeys.get(i), inputAnswer);
            originalPositions.put(textView, i);
        }

        for (int i = 5; i < 10; i++) {
            TextView textView = addView(layoutParent2, allKeys.get(i), inputAnswer);
            originalPositions.put(textView, i);
        }
    }

    private TextView addView(LinearLayout viewParent, final String text, final EditText editText) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        linearLayoutParams.rightMargin = 30;

        final TextView textView = new TextView(this);

        textView.setLayoutParams(linearLayoutParams);
        textView.setBackgroundResource(R.drawable.pink_bg);
        textView.setTextColor(getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");
        textView.setTypeface(typeface);

        textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (presCounter < maxPresCounter) {
                    if (presCounter == 0)
                        editText.setText("");

                    editText.setText(editText.getText().toString() + text);
                    textView.startAnimation(smallbigforth);
                    textView.animate().alpha(0).setDuration(300);
                    presCounter++;

                    if (presCounter == maxPresCounter)
                        doValidate();
                } else {
                    if (addedTextViews.contains(textView)) {
                        moveTextViewToOriginalPosition(textView);
                        addedTextViews.remove(textView);
                    } else {
                        addedTextViews.add(textView);
                        moveTextViewToEditText(textView, text);
                    }
                }
            }
        });

        viewParent.addView(textView);
        addedTextViews.add(textView);
        return textView;
    }

    private void moveTextViewToOriginalPosition(TextView textView) {
        LinearLayout parentLayout = (LinearLayout) textView.getParent();
        int originalIndex = originalPositions.get(textView);
        parentLayout.removeView(textView);
        parentLayout.addView(textView, originalIndex);
    }

    private void moveTextViewToEditText(TextView textView, String text) {
        EditText editText = findViewById(R.id.editText);
        editText.setText(editText.getText().toString() + text);
        textView.startAnimation(smallbigforth);
        textView.animate().alpha(0).setDuration(300);
    }

    private void resetTextViews() {
        presCounter = 0;
        EditText editText = findViewById(R.id.editText);
        LinearLayout linearLayout1 = findViewById(R.id.layoutParent1);
        LinearLayout linearLayout2 = findViewById(R.id.layoutParent2);

        editText.setText("");

        for (TextView textView : addedTextViews) {
            textView.setVisibility(View.VISIBLE);
        }

        addedTextViews.clear();

        setupQuestion(textAnswer);
    }

    private void doValidate() {
        presCounter = 0;

        EditText editText = findViewById(R.id.editText);
        String input = editText.getText().toString().toUpperCase();

        if (input.equals(textAnswer)) {
            Toast.makeText(QuizGame.this, "Correct", Toast.LENGTH_SHORT).show();
            editText.setText("");
            currentQuestionIndex++;
            loadQuestion();
        } else {
            resetTextViews();
            Toast.makeText(QuizGame.this, "Wrong", Toast.LENGTH_SHORT).show();
        }
    }
}



