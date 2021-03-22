package com.sdt944.chess;


public class Rook extends Chessman {
    public Rook(Point p, PlayerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = ChessmanType.Rook;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        createButton(color == PlayerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.rookb, parent.ctx.getTheme()) : parent.ctx.getResources().getDrawable(R.drawable.rookw, parent.ctx.getTheme()), minDimension, parent.ctx);
    }

    @Override
    public void generateMoves() {
        moves.clear();
        addVerticalMovePoints();
        addHorizontalMovePoints();
    }
}