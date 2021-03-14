package com.sdt944.chess;

import android.content.Context;

public class Bishop extends Chessman {
    public Bishop (Point p, playerColor color, int minDimension, Context ctx) {
        setPoint(p);
        type = chessmanType.Bishop;
        this.color = color;
        createButton(color == playerColor.Black ? ctx.getResources().getDrawable(R.drawable.bishopb,ctx.getTheme()):ctx.getResources().getDrawable(R.drawable.bishopw,ctx.getTheme()), minDimension, ctx);
    }

    @Override
    void generateMoves() {
        moves.clear();
        addObliqueNEtoSWMovePoints();
        addObliqueNWtoSEMovePoints();
    }
}
