package com.example.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private GameBoard game;
    private char turn;
    private int[] is_game_over;
    private Button btn_arr[], playAgain;
    private TextView setState, xScore, oScore, drawsScore;
    private  int c_xScore, c_oScore, c_drawsScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new GameBoard();
        turn = 'X';
        btn_arr = new Button[GameBoard.MAT_SIZE*GameBoard.MAT_SIZE];

        btn_arr[0]=findViewById(R.id.btn1ID); btn_arr[1]=findViewById(R.id.btn2ID); btn_arr[2]=findViewById(R.id.btn3ID);
        btn_arr[3]=findViewById(R.id.btn4ID); btn_arr[4]=findViewById(R.id.btn5ID); btn_arr[5]=findViewById(R.id.btn6ID);
        btn_arr[6]=findViewById(R.id.btn7ID); btn_arr[7]=findViewById(R.id.btn8ID); btn_arr[8]=findViewById(R.id.btn9ID);

        playAgain = findViewById(R.id.playAgainID);
        playAgain.setEnabled(false);

        setState = findViewById(R.id.setStateID);
        xScore = findViewById(R.id.xScoreID);
        oScore = findViewById(R.id.oScoreID);
        drawsScore = findViewById(R.id.drawsScoreID);

        SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
        c_xScore = sp.getInt("xScore", 0);
        xScore.setText(c_xScore+"");
        c_oScore = sp.getInt("oScore", 0);
        oScore.setText(c_oScore+"");
        c_drawsScore = sp.getInt("drawsScore", 0);
        drawsScore.setText(c_drawsScore+"");

        for(int i=0; i < btn_arr.length; i++) {
                btn_arr[i].setOnClickListener(this);
        }
        playAgain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn1ID:
                btn_arr[0].setEnabled(false);
                btn_arr[0].setText(turn + "");
                update(0,0);
                break;
            case R.id.btn2ID:
                btn_arr[1].setEnabled(false);
                btn_arr[1].setText(turn + "");
                update(0,1);
                break;
            case R.id.btn3ID:
                btn_arr[2].setEnabled(false);
                btn_arr[2].setText(turn + "");
                update(0,2);
                break;
            case R.id.btn4ID:
                btn_arr[3].setEnabled(false);
                btn_arr[3].setText(turn + "");
                update(1,0);
                break;
            case R.id.btn5ID:
                btn_arr[4].setEnabled(false);
                btn_arr[4].setText(turn + "");
                update(1,1);
                break;
            case R.id.btn6ID:
                btn_arr[5].setEnabled(false);
                btn_arr[5].setText(turn + "");
                update(1,2);
                break;
            case R.id.btn7ID:
                btn_arr[6].setEnabled(false);
                btn_arr[6].setText(turn + "");
                update(2,0);
                break;
            case R.id.btn8ID:
                btn_arr[7].setEnabled(false);
                btn_arr[7].setText(turn + "");
                update(2,1);
                break;
            case R.id.btn9ID:
                btn_arr[8].setEnabled(false);
                btn_arr[8].setText(turn + "");
                update(2,2);
                break;
            case R.id.playAgainID:
                game = new GameBoard();
                for(int i=0; i < btn_arr.length; i++) {
                    btn_arr[i].setEnabled(true);    //enabled all the buttons
                    btn_arr[i].setText(""); //delete text from all the buttons
                    btn_arr[i].setTextColor(Color.rgb(0, 0,0 ));    //change color back to black
                }
                setState.setText("Turn X");
                playAgain.setEnabled(false);

        }
    }

    private void gameOver(int[] is_game_over)
    {
        if(is_game_over[0] == GameBoard.WIN)    //win situation
        {
            setState.setText(turn + " won!");
            if(turn == 'X') {
                c_xScore++;
                xScore.setText(c_xScore + "");
            }
            else{
                c_oScore++;
                oScore.setText(c_oScore + "");
            }
            for(int i=0; i < btn_arr.length; i++) { //disabled all the buttons
                btn_arr[i].setEnabled(false);
            }
            for(int i=0; i < GameBoard.MAT_SIZE; i++) { //color the winning row
                btn_arr[is_game_over[i+1]].setTextColor(Color.rgb(0, 0,255 ));
            }
            MediaPlayer mp = MediaPlayer.create(this,R.raw.claps);  //play claps
            mp.start();
        }
        else    //draw situation
        {
            setState.setText("Draw - No Winner!");
            c_drawsScore++;
            drawsScore.setText(c_drawsScore +"");
        }
    }

    private void update(int i, int j)
    {
        is_game_over = game.setLocation(i,j,turn);
        if(is_game_over[0] != GameBoard.PLAY)   //win or draw
        {
            gameOver(is_game_over);
            Toast.makeText(GameActivity.this, "Game Over!", Toast.LENGTH_LONG).show();
            playAgain.setEnabled(true);
        }
        else    //keep playing
        {
            turn = (turn == 'X') ? 'O' : 'X';
            setState.setText("Turn " + turn);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("debug", "onStart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("debug", "onResume()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("debug", "onPause()");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("debug", "onStop()");

        // save scores in the file
        SharedPreferences sp = getSharedPreferences("file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("xScore", c_xScore);
        editor.putInt("oScore", c_oScore);
        editor.putInt("drawsScore", c_drawsScore);
        editor.commit();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d("debug", "onRestart()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("debug", "onDestroy()");
    }

}
