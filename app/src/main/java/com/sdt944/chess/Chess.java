package com.sdt944.chess;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Chess {
    public ArrayList<Chessman> deadMen = new ArrayList<>();
    public Chessman[/*column - x*/][/*row - y*/] chessmen = new Chessman[8][8];
    public Chessman.playerColor whichPlayerTurn = Chessman.playerColor.Black;
    public Point lastManPoint = null;
    public Context ctx;
    public int minDimension=0;
    private Chessman manToPromote = null;
    private FrameLayout boardLayout = null;

    private boolean gameEnd = false;

    public King whiteKing = null;
    public King blackKing = null;



    public Chess(Context ctx, int minDimension, FrameLayout boardLayout) {
        setLayoutParams(ctx, minDimension, boardLayout);

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
        chessmen[0][0] = new Rook(new Point(0, 0), Chessman.playerColor.Black, minDimension, this);
        chessmen[1][0] = new Knight(new Point(1, 0), Chessman.playerColor.Black, minDimension, this);
        chessmen[2][0] = new Bishop(new Point(2, 0), Chessman.playerColor.Black, minDimension, this);
        chessmen[3][0] = new Queen(new Point(3, 0), Chessman.playerColor.Black, minDimension, this);
        chessmen[4][0] = blackKing = new King(new Point(4, 0), Chessman.playerColor.Black, minDimension, this);
        chessmen[5][0] = new Bishop(new Point(5, 0), Chessman.playerColor.Black, minDimension, this);
        chessmen[6][0] = new Knight(new Point(6, 0), Chessman.playerColor.Black, minDimension, this);
        chessmen[7][0] = new Rook(new Point(7, 0), Chessman.playerColor.Black, minDimension, this);

        //second row
        chessmen[0][1] = new Pawn(new Point(0, 1), Chessman.playerColor.Black, minDimension, this);
        chessmen[1][1] = new Pawn(new Point(1, 1), Chessman.playerColor.Black, minDimension, this);
        chessmen[2][1] = new Pawn(new Point(2, 1), Chessman.playerColor.Black, minDimension, this);
        chessmen[3][1] = new Pawn(new Point(3, 1), Chessman.playerColor.Black, minDimension, this);
        chessmen[4][1] = new Pawn(new Point(4, 1), Chessman.playerColor.Black, minDimension, this);
        chessmen[5][1] = new Pawn(new Point(5, 1), Chessman.playerColor.Black, minDimension, this);
        chessmen[6][1] = new Pawn(new Point(6, 1), Chessman.playerColor.Black, minDimension, this);
        chessmen[7][1] = new Pawn(new Point(7, 1), Chessman.playerColor.Black, minDimension, this);

        //seventh row
        chessmen[0][6] = new Pawn(new Point(0, 6), Chessman.playerColor.White, minDimension, this);
        chessmen[1][6] = new Pawn(new Point(1, 6), Chessman.playerColor.White, minDimension, this);
        chessmen[2][6] = new Pawn(new Point(2, 6), Chessman.playerColor.White, minDimension, this);
        chessmen[3][6] = new Pawn(new Point(3, 6), Chessman.playerColor.White, minDimension, this);
        chessmen[4][6] = new Pawn(new Point(4, 6), Chessman.playerColor.White, minDimension, this);
        chessmen[5][6] = new Pawn(new Point(5, 6), Chessman.playerColor.White, minDimension, this);
        chessmen[6][6] = new Pawn(new Point(6, 6), Chessman.playerColor.White, minDimension, this);
        chessmen[7][6] = new Pawn(new Point(7, 6), Chessman.playerColor.White, minDimension, this);

        //eighth row
        chessmen[0][7] = new Rook(new Point(0, 7), Chessman.playerColor.White, minDimension, this);
        chessmen[1][7] = new Knight(new Point(1, 7), Chessman.playerColor.White, minDimension, this);
        chessmen[2][7] = new Bishop(new Point(2, 7), Chessman.playerColor.White, minDimension, this);
        chessmen[3][7] = new Queen(new Point(3, 7), Chessman.playerColor.White, minDimension, this);
        chessmen[4][7] = whiteKing = new King(new Point(4, 7), Chessman.playerColor.White, minDimension, this);
        chessmen[5][7] = new Bishop(new Point(5, 7), Chessman.playerColor.White, minDimension, this);
        chessmen[6][7] = new Knight(new Point(6, 7), Chessman.playerColor.White, minDimension, this);
        chessmen[7][7] = new Rook(new Point(7, 7), Chessman.playerColor.White, minDimension, this);

        for (int i = 0; i < 8; i++)
            for (int j = 2; j < 6; j++) {
                chessmen[i][j] = null;
            }

        addMenToBoard(boardLayout);
        Chessman.playerColor whichPlayerTurn = Chessman.playerColor.Black;
        changeTurn();
    }
    public void changeLayout(Context ctx, int minDimension, FrameLayout boardLayout) {
        setLayoutParams(ctx, minDimension, boardLayout);
        addMenToBoard(boardLayout);
        if(whichPlayerTurn == Chessman.playerColor.White)
            whichPlayerTurn = Chessman.playerColor.Black;
        else
            whichPlayerTurn = Chessman.playerColor.White;
        changeTurn();
    }
    private void setLayoutParams(Context ctx, int minDimension, FrameLayout boardLayout) {
        this.ctx = ctx;
        this.minDimension = minDimension;
        this.boardLayout = boardLayout;
    }

    public void onManClick(Chessman man) {
        if(gameEnd)
            return;
        if (man.color == whichPlayerTurn) {
            lastManPoint = man.getPoint();

            chessmen[lastManPoint.x][lastManPoint.y].generateMoves();
        } else if (lastManPoint != null && chessmen[lastManPoint.x][lastManPoint.y].moves.contains(man.getPoint())) {
            onBoardClick(man.getPoint().x, man.getPoint().y);
        }
    }

    public void onBoardClick(int x, int y) {
        if(gameEnd)
            return;
        if (lastManPoint == null)
            return;
        Point clickPoint = new Point(x, y);
        if (chessmen[lastManPoint.x][lastManPoint.y].moves.contains(clickPoint)) {
            move(lastManPoint, clickPoint);
            changeTurn();
            lastManPoint = null;
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
        if (chessmen[xt][yt] != null)
            kill(new Point(xt, yt));

        chessmen[xt][yt] = chessmen[xf][yf];

        //todo : move this part to pawn class (in setpoint function)
        if(chessmen[xf][yf].type == Chessman.chessmanType.Pawn)
            ((Pawn)chessmen[xf][yf]).firstMove = false;
        chessmen[xf][yf] = null;

        chessmen[xt][yt].setPoint(new Point(xt, yt));
        chessmen[xt][yt].moveButton(xt, yt);
    }

    public void kill(Point p) {
        deadMen.add(chessmen[p.x][p.y]);
        chessmen[p.x][p.y].isDead = true;
        ((ViewGroup) chessmen[p.x][p.y].button.getParent()).removeView(chessmen[p.x][p.y].button);
        chessmen[p.x][p.y] = null;
    }

    public void changeTurn() {
        if (whichPlayerTurn == Chessman.playerColor.White)
            whichPlayerTurn = Chessman.playerColor.Black;
        else
            whichPlayerTurn = Chessman.playerColor.White;
        ((ChessBoard)ctx).animateTurnChange(whichPlayerTurn);
    }

    public void promote(Chessman man) {
        manToPromote = man;
        ((ChessBoard)ctx).showPromotionActivity();
    }
    public void promotionResault(Chessman.chessmanType toType) {

        Chessman newType = null;
        switch (toType)
        {
            case Pawn:
                return;
            case Queen:
                newType = new Queen(manToPromote.getPoint(), manToPromote.color, minDimension, this);
                break;
            case Rook:
                newType = new Rook(manToPromote.getPoint(), manToPromote.color, minDimension, this);
                break;
            case Bishop:
                newType = new Bishop(manToPromote.getPoint(), manToPromote.color, minDimension, this);
                break;
            case Knight:
                newType = new Knight(manToPromote.getPoint(), manToPromote.color, minDimension, this);
                break;
        }
        newType.createButton();
        ((ViewGroup)manToPromote.button.getParent()).removeView(manToPromote.button);
        chessmen[manToPromote.getPoint().x][manToPromote.getPoint().y] = newType;
        boardLayout.addView(newType.button);
    }

    private void addMenToBoard(FrameLayout boardLayout) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (chessmen[i][j] == null)
                    continue;
                chessmen[i][j].createButton();
                boardLayout.addView(chessmen[i][j].button);
            }
    }
}