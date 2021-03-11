package com.sdt944.chess;

public class Rook extends Chessman {
    public Rook(playerColor color) {
        type = chessmanType.Rook;
        this.color = color;
    }
    @Override
    void generateMoves() {
        moves.clear();
        addVerticalMovePoints();
        addHorizontalMovePoints();
    }
}
