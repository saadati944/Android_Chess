package com.sdt944.chess;

import java.util.ArrayList;

public class Chess {
    public ArrayList<Chessman> deadMen = new ArrayList<>();
    public Chessman[/*column - x*/][/*row - y*/] chessmen = new Chessman[8][8];
    public boolean whitePlayerTurn = true;

    public Chess() {
        /*          BOARD
         *    <  X  >
         *    RBkQKkBR    WBWBWBWB
         *  < PPPPPPPP    BWBWBWBW
         *    ..BLACK.    WBWBWBWB
         *  y ........    BWBWBWBW
         *    ........    WBWBWBWB
         *  > .WHITE..    BWBWBWBW
         *    PPPPPPPP    WBWBWBWB
         *    RBkQKkBR    BWBWBWBW
         * */

        //first row
        chessmen[0][0] = new Rook(Chessman.playerColor.Black);
        chessmen[1][0] = new Bishop(Chessman.playerColor.Black);
        chessmen[2][0] = new Knight(Chessman.playerColor.Black);
        chessmen[3][0] = new Queen(Chessman.playerColor.Black);
        chessmen[4][0] = new King(Chessman.playerColor.Black);
        chessmen[5][0] = new Knight(Chessman.playerColor.Black);
        chessmen[6][0] = new Bishop(Chessman.playerColor.Black);
        chessmen[7][0] = new Rook(Chessman.playerColor.Black);

        //second row
        chessmen[0][1] = new Pawn(Chessman.playerColor.Black);
        chessmen[1][1] = new Pawn(Chessman.playerColor.Black);
        chessmen[2][1] = new Pawn(Chessman.playerColor.Black);
        chessmen[3][1] = new Pawn(Chessman.playerColor.Black);
        chessmen[4][1] = new Pawn(Chessman.playerColor.Black);
        chessmen[5][1] = new Pawn(Chessman.playerColor.Black);
        chessmen[6][1] = new Pawn(Chessman.playerColor.Black);
        chessmen[7][1] = new Pawn(Chessman.playerColor.Black);

        //seventh row
        chessmen[0][6] = new Pawn(Chessman.playerColor.White);
        chessmen[1][6] = new Pawn(Chessman.playerColor.White);
        chessmen[2][6] = new Pawn(Chessman.playerColor.White);
        chessmen[3][6] = new Pawn(Chessman.playerColor.White);
        chessmen[4][6] = new Pawn(Chessman.playerColor.White);
        chessmen[5][6] = new Pawn(Chessman.playerColor.White);
        chessmen[6][6] = new Pawn(Chessman.playerColor.White);
        chessmen[7][6] = new Pawn(Chessman.playerColor.White);

        //eighth row
        chessmen[0][7] = new Rook(Chessman.playerColor.White);
        chessmen[1][7] = new Bishop(Chessman.playerColor.White);
        chessmen[2][7] = new Knight(Chessman.playerColor.White);
        chessmen[3][7] = new Queen(Chessman.playerColor.White);
        chessmen[4][7] = new King(Chessman.playerColor.White);
        chessmen[5][7] = new Knight(Chessman.playerColor.White);
        chessmen[6][7] = new Bishop(Chessman.playerColor.White);
        chessmen[7][7] = new Rook(Chessman.playerColor.White);

        for(int i=0;i<8;i++)
        for(int j=2; j<6; j++)
        {
            chessmen[i][j] = null;
        }

    }
    public boolean checkPoint(Point pt, Chessman man) {
        return checkPoint(pt.x, pt.y, man);
    }
    public boolean checkPoint(int x, int y, Chessman man) {
        if (chessmen[x][y] == null)
            return true;

        if (chessmen[x][y].color == man.color)
            return false;

        return true;
    }

    public void move(Point from, Point to) {
        move(from.x, from.y, to.x, to.y);
    }
    public void move(int xf, int yf, int xt, int yt) {
        deadMen.add(chessmen[xt][yt]);
        chessmen[xt][yt] = chessmen[xf][yf];
        chessmen[xf][yf] = null;
    }
}
