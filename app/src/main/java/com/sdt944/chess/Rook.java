package com.sdt944.chess;

import android.content.Context;

public class Rook extends Chessman {
    public Rook(Point p, playerColor color, int minDimension, Context ctx, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Rook;
        this.color = color;
        createButton(color == playerColor.Black ? ctx.getResources().getDrawable(R.drawable.rookb,ctx.getTheme()):ctx.getResources().getDrawable(R.drawable.rookw,ctx.getTheme()), minDimension, ctx);
    }
    @Override
    void generateMoves() {
        moves.clear();
        addVerticalMovePoints();
        addHorizontalMovePoints();
    }
}
