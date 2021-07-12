package com.example.tictactoe;

public class GameBoard {

    public static final int MAT_SIZE = 3;
    public static final int PLAY = 0;
    public static final int WIN = 1;
    public static final int DRAW = 2;

    private int counter;
    private char mat_board[][];

    public GameBoard(){
        this.counter = 0;

        this.mat_board = new char[MAT_SIZE][MAT_SIZE];
        for(int i=0; i<MAT_SIZE; i++){
            for(int j=0; j<MAT_SIZE; j++){
                this.mat_board[i][j] = ' ';
            }
        }
    }

    public int[] setLocation(int i, int j, char turn){
        this.counter++;
        this.mat_board[i][j] = turn;
        return checkWin(turn);
    }

    private int[] checkWin(char turn){
        //win situation
        if(!(this.counter<3)) {
            //check winning in lines
            if (this.mat_board[0][0] == turn && this.mat_board[0][1] == turn && this.mat_board[0][2] == turn)
                return new int[]{WIN, 0, 1, 2};
            if (this.mat_board[1][0] == turn && this.mat_board[1][1] == turn && this.mat_board[1][2] == turn)
                return new int[]{WIN, 3, 4, 5};
            if (this.mat_board[2][0] == turn && this.mat_board[2][1] == turn && this.mat_board[2][2] == turn)
                return new int[]{WIN, 6, 7, 8};
            //check winning in columns
            if (this.mat_board[0][0] == turn && this.mat_board[1][0] == turn && this.mat_board[2][0] == turn)
                return new int[]{WIN, 0, 3, 6};
            if (this.mat_board[0][1] == turn && this.mat_board[1][1] == turn && this.mat_board[2][1] == turn)
                return new int[]{WIN, 1, 4, 7};
            if (this.mat_board[0][2] == turn && this.mat_board[1][2] == turn && this.mat_board[2][2] == turn)
                return new int[]{WIN, 2, 5, 8};
            //check winning in diagonals
            if (this.mat_board[0][0] == turn && this.mat_board[1][1] == turn && this.mat_board[2][2] == turn)
                return new int[]{WIN, 0, 4, 8};
            if (this.mat_board[0][2] == turn && this.mat_board[1][1] == turn && this.mat_board[2][0] == turn)
                return new int[]{WIN, 2, 4, 6};
        }

        //draw situation
        if(this.counter == MAT_SIZE*MAT_SIZE)
            return new int[]{DRAW,-1, -1, -1};

        //game on
        return new int[]{PLAY, -1, -1, -1};
    }

}
