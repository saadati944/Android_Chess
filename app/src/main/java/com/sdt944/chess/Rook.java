package com.sdt944.chess;

import android.content.Context;

public class Rook extends Chessman {
    public Rook(Point p, playerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Rook;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        createButton(color == playerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.rookb,parent.ctx.getTheme()):parent.ctx.getResources().getDrawable(R.drawable.rookw,parent.ctx.getTheme()), minDimension, parent.ctx);
    }

    @Override
    void generateMoves() {
        shouldGenerateMoves = false;
        moves.clear();
        addVerticalMovePoints();
        addHorizontalMovePoints();
    }
}
