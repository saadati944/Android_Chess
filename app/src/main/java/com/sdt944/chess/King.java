package com.sdt944.chess;


public class King extends Chessman {

    public enum kingRiskType {
        Check,
        CheckMate,
        Safe
    }

    public King(Point p, playerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.King;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        //todo : try to use ResourcesCombat.getDrawable()
        createButton(color == playerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.kingb, parent.ctx.getTheme()) : parent.ctx.getResources().getDrawable(R.drawable.kingw, parent.ctx.getTheme()), minDimension, parent.ctx);
    }

    @Override
    void generateMoves() {
        moves.clear();
        addAroundMovePoints();
    }
}