package com.sdt944.chess;

import android.content.Context;

public class Pawn extends Chessman {
    public boolean firstMove = true;
    public Pawn(Point p, playerColor color, int minDimension, Context ctx, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Pawn;
        this.color = color;
        createButton(color == playerColor.Black ? ctx.getResources().getDrawable(R.drawable.pawnb,ctx.getTheme()):ctx.getResources().getDrawable(R.drawable.pawnw,ctx.getTheme()), minDimension, ctx);
    }

    @Override
    void generateMoves() {
        moves.clear();
        add1StepForwardMovePoints();

        if(moves.size()==0)
            return;

        Point frontPoint = moves.get(0);

        Point left = new Point(frontPoint.x-1, frontPoint.y);
        Point right = new Point(frontPoint.x+1, frontPoint.y);

        if(left.isValid() && parent.chessmen[left.x][left.y] != null && parent.chessmen[left.x][left.y].color != color)
            moves.add(left);
        if(right.isValid() && parent.chessmen[right.x][right.y] != null && parent.chessmen[right.x][right.y].color != color)
            moves.add(right);

        if(firstMove) {
            add2StepForwardMovePoints();
            firstMove = false;
        }
    }
}
