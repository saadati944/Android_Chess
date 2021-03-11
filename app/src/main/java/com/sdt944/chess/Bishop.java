package com.sdt944.chess;

public class Bishop extends Chessman {
    public Bishop (playerColor color) {
        type = chessmanType.Bishop;
        this.color = color;
    }

    @Override
    void generateMoves() {
        moves.clear();
        addObliqueNEtoSWMovePoints();
        addObliqueNWtoSEMovePoints();
    }
}
