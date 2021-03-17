package com.sdt944.chess;

import android.content.Context;

public class Knight extends Chessman {
    public Knight(Point p, playerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Knight;
        this.color = color;
        this.minDimension = minDimension;
    }
    public void createButton() {
        createButton(color == playerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.knightb,parent.ctx.getTheme()):parent.ctx.getResources().getDrawable(R.drawable.knightw,parent.ctx.getTheme()), minDimension, parent.ctx);
    }
    @Override
    void generateMoves() {
        moves.clear();
        addLMovePoints();
    }
}
