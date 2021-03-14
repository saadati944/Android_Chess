package com.sdt944.chess;

import android.content.Context;

import java.util.ArrayList;

public class King extends Chessman {

    public King(Point p, playerColor color, int minDimension, Context ctx) {
        setPoint(p);
        type = chessmanType.King;
        this.color = color;
        createButton(color == playerColor.Black ? ctx.getResources().getDrawable(R.drawable.kingb,ctx.getTheme()):ctx.getResources().getDrawable(R.drawable.kingw,ctx.getTheme()), minDimension, ctx);
    }

    @Override
    void generateMoves() {
        moves.clear();
        addAroundMovePoints();
    }
}