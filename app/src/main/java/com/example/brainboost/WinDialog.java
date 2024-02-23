package com.example.brainboost;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class WinDialog extends Dialog {

    private final String message;
    private final TicTacToe ticTacToe;

    public WinDialog(@NonNull Context context, String message, TicTacToe ticTacToe) {
        super(context);
        this.message = message;
        this.ticTacToe = ticTacToe;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_dialog);

        final TextView messageTxt = findViewById(R.id.messageTxt);
        final Button startAgainBtn = findViewById(R.id.startAgainBtn);

        messageTxt.setText(message);
        startAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticTacToe.restartMatch();
                dismiss();
            }
        });
    }
}



























