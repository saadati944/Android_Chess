package com.sdt944.chess;


public class Queen extends Chessman {

    public Queen(Point p, PlayerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = ChessmanType.Queen;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        createButton(color == PlayerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.queenb, parent.ctx.getTheme()) : parent.ctx.getResources().getDrawable(R.drawable.queenw, parent.ctx.getTheme()), minDimension, parent.ctx);
    }

    @Override
    public void generateMoves() {
        moves.clear();
        addVerticalMovePoints();
        addHorizontalMovePoints();
        addObliqueNEtoSWMovePoints();
        addObliqueNWtoSEMovePoints();
    }
}