package com.sdt944.chess;

import android.content.Context;

public class Knight extends Chessman {
    public Knight(Point p, playerColor color, int minDimension, Context ctx, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Knight;
        this.color = color;
        createButton(color == playerColor.Black ? ctx.getResources().getDrawable(R.drawable.knightb,ctx.getTheme()):ctx.getResources().getDrawable(R.drawable.knightw,ctx.getTheme()), minDimension, ctx);
    }
    @Override
    void generateMoves() {
        moves.clear();
        addLMovePoints();
    }
}
