package com.sdt944.chess;

import android.content.Context;

public class Queen extends Chessman {

    public Queen(Point p, playerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Queen;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        createButton(color == playerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.queenb,parent.ctx.getTheme()):parent.ctx.getResources().getDrawable(R.drawable.queenw,parent.ctx.getTheme()), minDimension, parent.ctx);
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
