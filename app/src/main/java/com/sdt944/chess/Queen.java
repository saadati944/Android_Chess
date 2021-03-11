package com.sdt944.chess;

public class Queen extends Chessman {

    public Queen(playerColor color) {
        type = chessmanType.Queen;
        this.color = color;
    }

    @Override
    void generateMoves() {
        moves.clear();
        addVerticalMovePoints();
        addHorizontalMovePoints();
        addAroundMovePoints();
        addObliqueNEtoSWMovePoints();
        addObliqueNWtoSEMovePoints();
    }
}
