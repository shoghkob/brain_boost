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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizGame extends AppCompatActivity {
    private int presCounter = 0;
    private int maxPresCounter = 4;
    private String[] keys_first_line = {"A", "O", "N", "D", "R"};
    private String[] keys_second_line = {"I", "K", "T", "E", "S"};
    private String textAnswer = "DRONES";
    private List<TextView> addedTextViews = new ArrayList<>();
    TextView textScreen, textQuestion, textTitle;
    Animation smallbigforth;

    EditText inputAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_game);

        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smallbigforth);

        keys_first_line = shuffleArray(keys_first_line);
        keys_second_line = shuffleArray(keys_second_line);
        inputAnswer = findViewById(R.id.editText);

        inputAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTextViews();
            }
        });
        for (String key : keys_first_line) {
            addView(((LinearLayout) findViewById(R.id.layoutParent1)), key, ((EditText) findViewById(R.id.editText)));
        }

        for (String key : keys_second_line) {
            addView(((LinearLayout) findViewById(R.id.layoutParent2)), key, ((EditText) findViewById(R.id.editText)));
        }

        maxPresCounter = 6;
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

        keys_first_line = shuffleArray(keys_first_line);
        linearLayout1.removeAllViews();
        for (String key : keys_first_line) {
            addView(linearLayout1, key, editText);
        }

        keys_second_line = shuffleArray(keys_second_line);
        linearLayout2.removeAllViews();
        for (String key : keys_second_line) {
            addView(linearLayout2, key, editText);
        }
    }


    private String[] shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    private void addView(LinearLayout viewParent, final String text, final EditText editText) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        linearLayoutParams.rightMargin = 30;

        final TextView textView = new TextView(this);

        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.pink_bg));
        textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textScreen = (TextView) findViewById(R.id.textScreen);
        textTitle = (TextView) findViewById(R.id.textTitle);

        textQuestion.setTypeface(typeface);
        textScreen.setTypeface(typeface);
        textTitle.setTypeface(typeface);
        editText.setTypeface(typeface);
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
                }
            }
        });


        viewParent.addView(textView);


    }


    private void doValidate() {
        presCounter = 0;

        EditText editText = findViewById(R.id.editText);
        LinearLayout linearLayout1 = findViewById(R.id.layoutParent1);
        LinearLayout linearLayout2 = findViewById(R.id.layoutParent2);

        if (editText.getText().toString().equals(textAnswer)) {
            Toast.makeText(QuizGame.this, "Correct", Toast.LENGTH_SHORT).show();

            Intent a = new Intent(QuizGame.this, BossLevel.class);
            startActivity(a);

            editText.setText("");
        } else {
            Toast.makeText(QuizGame.this, "Wrong", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }

        keys_first_line = shuffleArray(keys_first_line);
        linearLayout1.removeAllViews();
        for (String key : keys_first_line) {
            addView(linearLayout1, key, editText);
        }

        keys_second_line = shuffleArray(keys_second_line);
        linearLayout2.removeAllViews();
        for (String key : keys_second_line) {
            addView(linearLayout2, key, editText);
        }

    }
}