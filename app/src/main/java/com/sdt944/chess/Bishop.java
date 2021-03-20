package com.sdt944.chess;

import android.content.Context;

public class Bishop extends Chessman {
    public Bishop (Point p, playerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Bishop;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        super.createButton(color == playerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.bishopb,parent.ctx.getTheme()):parent.ctx.getResources().getDrawable(R.drawable.bishopw,parent.ctx.getTheme()), minDimension, parent.ctx);
    }

    @Override
    void generateMoves() {
        moves.clear();
        addObliqueNEtoSWMovePoints();
        addObliqueNWtoSEMovePoints();
    }
}
