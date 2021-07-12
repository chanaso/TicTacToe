package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class InstructionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        btnStart1 = findViewById(R.id.btnStart1ID);

        btnStart1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent_start = new Intent(InstructionsActivity.this, GameActivity.class);
        startActivity(intent_start);
    }
}
