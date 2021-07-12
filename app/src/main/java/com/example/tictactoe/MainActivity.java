package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInst;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInst = findViewById(R.id.btnInstID);
        btnStart = findViewById(R.id.btnStartID);

        btnInst.setOnClickListener(this);
        btnStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnInstID:
                Intent intent_inst = new Intent(MainActivity.this, InstructionsActivity.class);
                startActivity(intent_inst);
                break;

            case R.id.btnStartID:
                Intent intent_start = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent_start);
                break;
        }
    }
}
