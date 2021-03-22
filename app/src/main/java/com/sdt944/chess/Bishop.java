package com.sdt944.chess;


public class Bishop extends Chessman {
    public Bishop(Point p, PlayerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = ChessmanType.Bishop;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        super.createButton(color == PlayerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.bishopb, parent.ctx.getTheme()) : parent.ctx.getResources().getDrawable(R.drawable.bishopw, parent.ctx.getTheme()), minDimension, parent.ctx);
    }

    @Override
    public void generateMoves() {
        moves.clear();
        addObliqueNEtoSWMovePoints();
        addObliqueNWtoSEMovePoints();
    }
}