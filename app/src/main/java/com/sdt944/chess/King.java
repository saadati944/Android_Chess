package com.sdt944.chess;

import java.util.ArrayList;

public class King extends Chessman {

    public King(playerColor color) {
        type = chessmanType.King;
        this.color = color;
    }

    @Override
    void generateMoves() {
        moves.clear();
        addAroundMovePoints();
    }
}