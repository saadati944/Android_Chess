package com.sdt944.chess;

import android.content.Context;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class Chess {
    public ArrayList<Chessman> deadMen = new ArrayList<>();
    public Chessman[/*column - x*/][/*row - y*/] chessmen = new Chessman[8][8];
    public boolean whitePlayerTurn = true;

    public Chess(Context ctx, int minDimension, FrameLayout boardLayout) {
        /*          BOARD
         *    <  X  >
         *    RkbQKBkR    WBWBWBWB
         *  < PPPPPPPP    BWBWBWBW
         *    ..BLACK.    WBWBWBWB
         *  y ........    BWBWBWBW
         *    ........    WBWBWBWB
         *  > .WHITE..    BWBWBWBW
         *    PPPPPPPP    WBWBWBWB
         *    RkBQKBkR    BWBWBWBW
         * */

        //first row
        chessmen[0][0] = new Rook(new Point(0, 0),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[1][0] = new Knight(new Point(1, 0),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[2][0] = new Bishop(new Point(2, 0),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[3][0] = new Queen(new Point(3, 0),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[4][0] = new King(new Point(4, 0),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[5][0] = new Bishop(new Point(5, 0),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[6][0] = new Knight(new Point(6, 0),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[7][0] = new Rook(new Point(7, 0),Chessman.playerColor.Black, minDimension, ctx, this);

        //second row
        chessmen[0][1] = new Pawn(new Point(0, 1),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[1][1] = new Pawn(new Point(1, 1),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[2][1] = new Pawn(new Point(2, 1),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[3][1] = new Pawn(new Point(3, 1),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[4][1] = new Pawn(new Point(4, 1),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[5][1] = new Pawn(new Point(5, 1),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[6][1] = new Pawn(new Point(6, 1),Chessman.playerColor.Black, minDimension, ctx, this);
        chessmen[7][1] = new Pawn(new Point(7, 1),Chessman.playerColor.Black, minDimension, ctx, this);

        //seventh row
        chessmen[0][6] = new Pawn(new Point(0, 6),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[1][6] = new Pawn(new Point(1, 6),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[2][6] = new Pawn(new Point(2, 6),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[3][6] = new Pawn(new Point(3, 6),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[4][6] = new Pawn(new Point(4, 6),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[5][6] = new Pawn(new Point(5, 6),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[6][6] = new Pawn(new Point(6, 6),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[7][6] = new Pawn(new Point(7, 6),Chessman.playerColor.White, minDimension, ctx, this);

        //eighth row
        chessmen[0][7] = new Rook(new Point(0, 7),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[1][7] = new Knight(new Point(1, 7),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[2][7] = new Bishop(new Point(2, 7),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[3][7] = new Queen(new Point(3, 7),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[4][7] = new King(new Point(4, 7),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[5][7] = new Bishop(new Point(5, 7),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[6][7] = new Knight(new Point(6, 7),Chessman.playerColor.White, minDimension, ctx, this);
        chessmen[7][7] = new Rook(new Point(7, 7),Chessman.playerColor.White, minDimension, ctx, this);

        for(int i=0;i<8;i++)
        for(int j=2; j<6; j++)
        {
            chessmen[i][j] = null;
        }

        addMenToBoard(boardLayout);
    }

    public void onManClick(Chessman man)
    {

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

    private void addMenToBoard(FrameLayout boardLayout)
    {
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(chessmen[i][j] == null)
                    continue;

                boardLayout.addView(chessmen[i][j].button);
            }
    }
}
