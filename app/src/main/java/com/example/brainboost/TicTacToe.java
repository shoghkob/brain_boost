package com.example.brainboost;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe extends AppCompatActivity {

    private final List<int[]> combinationsList = new ArrayList<>();

    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    private int currentPlayer = 1;

    private int totalSelectedBoxes = 0;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerNameTextView;
    private ImageView[] boxes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        playerNameTextView = findViewById(R.id.playerOneName);
        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerTwoLayout = findViewById(R.id.playerTwoLayout);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("userName", "");
        playerNameTextView.setText(userName);

        boxes = new ImageView[9];
        boxes[0] = findViewById(R.id.image1);
        boxes[1] = findViewById(R.id.image2);
        boxes[2] = findViewById(R.id.image3);
        boxes[3] = findViewById(R.id.image4);
        boxes[4] = findViewById(R.id.image5);
        boxes[5] = findViewById(R.id.image6);
        boxes[6] = findViewById(R.id.image7);
        boxes[7] = findViewById(R.id.image8);
        boxes[8] = findViewById(R.id.image9);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{0, 4, 8});
        combinationsList.add(new int[]{2, 4, 6});

        for (int i = 0; i < 9; i++) {
            final int position = i;
            boxes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (boxPositions[position] == 0) {
                        boxPositions[position] = currentPlayer;
                        totalSelectedBoxes++;
                        updateUI(position);
                        if (checkWin(currentPlayer)) {
                            showResult("You win!");
                        } else if (totalSelectedBoxes == 9) {
                            showResult("It's a draw!");
                        } else {
                            currentPlayer = 2;
                            playerNameTextView.setText("Robot");
                            makeAiMove();
                        }
                    }
                }
            });
        }
    }

    private void makeAiMove() {
        int aiMove;
        int winningMove = findWinningMove();
        if (winningMove != -1) {
            aiMove = winningMove;
        } else {
            int blockingMove = findBlockingMove();
            if (blockingMove != -1) {
                aiMove = blockingMove;
            } else {
                Random random = new Random();
                do {
                    aiMove = random.nextInt(9);
                } while (boxPositions[aiMove] != 0);
            }
        }
        boxPositions[aiMove] = 2;
        totalSelectedBoxes++;
        updateUI(aiMove);
        if (checkWin(2)) {
            showResult("AI wins!");
        } else if (totalSelectedBoxes == 9) {
            showResult("It's a draw!");
        } else {
            currentPlayer = 1;
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            String name = sharedPreferences.getString("username", "Your Name");
            playerNameTextView.setText(name);
        }
    }

    private int findWinningMove() {
        for (int[] combination : combinationsList) {
            int count = 0;
            int emptyPosition = -1;
            for (int position : combination) {
                if (boxPositions[position] == 2) {
                    count++;
                } else if (boxPositions[position] == 0) {
                    emptyPosition = position;
                }
            }
            if (count == 2 && emptyPosition != -1) {
                return emptyPosition;
            }
        }
        return -1;
    }

    private int findBlockingMove() {
        for (int[] combination : combinationsList) {
            int count = 0;
            int emptyPosition = -1;
            for (int position : combination) {
                if (boxPositions[position] == 1) {
                    count++;
                } else if (boxPositions[position] == 0) {
                    emptyPosition = position;
                }
            }
            if (count == 2 && emptyPosition != -1) {
                return emptyPosition;
            }
        }
        return -1;
    }

    private void updateUI(int position) {
        if (boxPositions[position] == 1) {
            boxes[position].setImageResource(R.drawable.human);
        } else {
            boxes[position].setImageResource(R.drawable.robot);
        }
    }

    private boolean checkWin(int player) {
        for (int[] combination : combinationsList) {
            if (boxPositions[combination[0]] == player && boxPositions[combination[1]] == player && boxPositions[combination[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private void showResult(String message) {
        ResultDialog resultDialog = new ResultDialog(TicTacToe.this, message, TicTacToe.this);
        resultDialog.setCancelable(false);
        resultDialog.show();
    }

    void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        currentPlayer = 1;
        totalSelectedBoxes = 0;

        for (ImageView box : boxes) {
            box.setImageResource(R.drawable.white_box);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "Your Name");
        playerNameTextView.setText(name);
    }
}
