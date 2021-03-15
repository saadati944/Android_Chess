package com.sdt944.chess;

import android.content.Context;

public class Queen extends Chessman {

    public Queen(Point p, playerColor color, int minDimension, Context ctx, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Queen;
        this.color = color;
        createButton(color == playerColor.Black ? ctx.getResources().getDrawable(R.drawable.queenb,ctx.getTheme()):ctx.getResources().getDrawable(R.drawable.queenw,ctx.getTheme()), minDimension, ctx);
    }

    @Override
    void generateMoves() {
        moves.clear();
        addVerticalMovePoints();
        addHorizontalMovePoints();
        addObliqueNEtoSWMovePoints();
        addObliqueNWtoSEMovePoints();
    }
}
