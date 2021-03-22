package com.sdt944.chess;


public class King extends Chessman {

    public enum KingRiskType {
        Check,
        CheckMate,
        Safe
    }

    public King(Point p, PlayerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = ChessmanType.King;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        //todo : try to use ResourcesCombat.getDrawable()
        createButton(color == PlayerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.kingb, parent.ctx.getTheme()) : parent.ctx.getResources().getDrawable(R.drawable.kingw, parent.ctx.getTheme()), minDimension, parent.ctx);
    }

    @Override
    public void generateMoves() {
        moves.clear();
        addAroundMovePoints();
    }
}