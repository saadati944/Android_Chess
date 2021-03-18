package com.sdt944.chess;

import android.content.Context;

public class Pawn extends Chessman {
    public boolean firstMove = true;
    public boolean promoted = false;
    public Pawn(Point p, playerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.Pawn;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        createButton(color == playerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.pawnb,parent.ctx.getTheme()):parent.ctx.getResources().getDrawable(R.drawable.pawnw,parent.ctx.getTheme()), minDimension, parent.ctx);
    }

    @Override
    public void setPoint(Point p)
    {
        super.setPoint(p);
        if (color == playerColor.White && p.y == 0 //white reaches end
            || color == playerColor.Black && p.y == 7) //black reaches end
            parent.promote(this);
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

        //cant move forward if there is anyone there
        if(parent.chessmen[frontPoint.x][frontPoint.y] != null) {
            moves.remove(frontPoint);

            //if there is a man in front of the pawn it can't jump over it
            //so there is no need to check (and add) second front cell
            return;
        }

        if(firstMove) {
            add2StepForwardMovePoints();
            Point twoFront = moves.get(moves.size()-1);
            //cant move 2xforward if there is anyone there
            if(parent.chessmen[twoFront.x][twoFront.y] != null)
                moves.remove(twoFront);
        }
    }
}
