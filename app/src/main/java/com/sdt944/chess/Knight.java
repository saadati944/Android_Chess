package com.sdt944.chess;

public class Knight extends Chessman {
    public Knight(playerColor color) {
        type = chessmanType.Knight;
        this.color = color;
    }
    @Override
    void generateMoves() {
        moves.clear();
        addLMovePoints();
    }
}
