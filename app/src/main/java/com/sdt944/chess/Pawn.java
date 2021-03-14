package com.sdt944.chess;

import android.content.Context;

public class Pawn extends Chessman {
    public boolean firstMove = true;
    public Pawn(Point p, playerColor color, int minDimension, Context ctx, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Pawn;
        this.color = color;
        createButton(color == playerColor.Black ? ctx.getResources().getDrawable(R.drawable.pawnb,ctx.getTheme()):ctx.getResources().getDrawable(R.drawable.pawnw,ctx.getTheme()), minDimension, ctx);
    }

    @Override
    void generateMoves() {
        moves.clear();
        add1StepForwardMovePoints();
        if(firstMove) {
            add2StepForwardMovePoints();
            firstMove = false;
        }
    }
}
