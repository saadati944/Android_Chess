package com.sdt944.chess;

public class Pawn extends Chessman {
    public boolean firstMove = true;
    public Pawn(playerColor color){
        type = chessmanType.Pawn;
        this.color = color;
    }

    @Override
    void generateMoves() {
        moves.clear();
        add1StepForwardMovePoints();
        if(firstMove) {
            add2StepForwardMovePoints();
            firstMove = false;
        }
    }
}
