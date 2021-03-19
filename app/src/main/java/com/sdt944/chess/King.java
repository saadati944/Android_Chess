package com.sdt944.chess;

import android.content.Context;

import java.util.ArrayList;

public class King extends Chessman {

    public King(Point p, playerColor color, int minDimension, Chess parent) {
        this.parent = parent;
        setPoint(p);
        type = chessmanType.King;
        this.color = color;
        this.minDimension = minDimension;
    }

    @Override
    public void createButton() {
        //todo : try to use ResourcesCombat.getDrawable()
        createButton(color == playerColor.Black ? parent.ctx.getResources().getDrawable(R.drawable.kingb, parent.ctx.getTheme()) : parent.ctx.getResources().getDrawable(R.drawable.kingw, parent.ctx.getTheme()), minDimension, parent.ctx);
    }

    @Override
    void generateMoves() {
        shouldGenerateMoves = false;
        moves.clear();
        addAroundMovePoints();
    }


    public boolean isPointSafeForKing() {
        return isPointSafeForKing(this.getPoint());
    }
    public boolean isPointSafeForKing(Point point) {
        //todo : move the same codes to a function and don't write a code more than one time

        //                vertical/horizontal checks
        /*
         *  ........
         *  ...?....
         *  ...#....
         *  ...X....
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        int x = point.x, y = point.y - 1;
        while (Point.isValid(x, y)) {
            if (parent.chessmen[x][y] != null) {
                if (parent.chessmen[x][y].color == color)
                    break;
                if (isThereDirectMover(x, y))
                    return false;
            }
            y--;
        }
        /*
         *  ........
         *  ........
         *  ........
         *  ...X....
         *  ...#....
         *  ...#....
         *  ...?....
         *  ........
         * */
        x = point.x;
        y = point.y + 1;
        while (Point.isValid(x, y)) {
            if (parent.chessmen[x][y] != null) {
                if (parent.chessmen[x][y].color == color)
                    break;
                if (isThereDirectMover(x, y))
                    return false;
            }
            y++;
        }

        /*
         *  ........
         *  ........
         *  ........
         *  .?#X....
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        x = point.x - 1;
        y = point.y;
        while (Point.isValid(x, y)) {
            if (parent.chessmen[x][y] != null) {
                if (parent.chessmen[x][y].color == color)
                    break;
                if (isThereDirectMover(x, y))
                    return false;
            }
            x--;
        }

        /*
         *  ........
         *  ........
         *  ........
         *  ...X##?.
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        x = point.x + 1;
        y = point.y;
        while (Point.isValid(x, y)) {
            if (parent.chessmen[x][y] != null) {
                if (parent.chessmen[x][y].color == color)
                    break;
                if (isThereDirectMover(x, y))
                    return false;
            }
            x++;
        }

        //                oblique checks

        /*
         *  ........
         *  .?......
         *  ..#.....
         *  ...X....
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        x = point.x - 1;
        y = point.y - 1;
        while (Point.isValid(x, y)) {
            if (parent.chessmen[x][y] != null) {
                if (parent.chessmen[x][y].color == color)
                    break;
                if (isThereObliqueMover(x, y))
                    return false;
            }
            x--;
            y--;
        }

        /*
         *  ........
         *  ........
         *  ........
         *  ...X....
         *  ....#...
         *  .....#..
         *  ......?.
         *  ........
         * */
        x = point.x + 1;
        y = point.y + 1;
        while (Point.isValid(x, y)) {
            if (parent.chessmen[x][y] != null) {
                if (parent.chessmen[x][y].color == color)
                    break;
                if (isThereObliqueMover(x, y))
                    return false;
            }
            x++;
            y++;
        }

        /*
         *  ........
         *  ........
         *  ........
         *  ...X....
         *  ..#.....
         *  .?......
         *  ........
         *  ........
         * */
        x = point.x - 1;
        y = point.y + 1;
        while (Point.isValid(x, y)) {
            if (parent.chessmen[x][y] != null) {
                if (parent.chessmen[x][y].color == color)
                    break;
                if (isThereObliqueMover(x, y))
                    return false;
            }
            x--;
            y++;
        }

        /*
         *  ........
         *  .....?..
         *  ....#...
         *  ...X....
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        x = point.x + 1;
        y = point.y - 1;
        while (Point.isValid(x, y)) {
            if (parent.chessmen[x][y] != null) {
                if (parent.chessmen[x][y].color == color)
                     break;
                if (isThereObliqueMover(x, y))
                    return false;
            }
            x++;
            y--;
        }


        //                knight checks
        x = point.x - 1;
        y = point.y - 2;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Knight)
            return false;
        x = point.x - 1;
        y = point.y + 2;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Knight)
            return false;
        x = point.x - 2;
        y = point.y - 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Knight)
            return false;
        x = point.x - 2;
        y = point.y + 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Knight)
            return false;
        x = point.x + 1;
        y = point.y - 2;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Knight)
            return false;
        x = point.x + 1;
        y = point.y + 2;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Knight)
            return false;
        x = point.x - 2;
        y = point.y - 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Knight)
            return false;
        x = point.x + 2;
        y = point.y + 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Knight)
            return false;

        //                pawn checks
        if (color == playerColor.Black)
            y = point.y + 1;
        else
            y = point.y - 1;
        x = point.x - 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Pawn)
            return false;
        x = point.x + 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == chessmanType.Pawn)
            return false;
        return true;
    }

    private boolean isThereDirectMover(int x, int y) {
        return parent.chessmen[x][y] != null && (parent.chessmen[x][y].type == chessmanType.Queen || parent.chessmen[x][y].type == chessmanType.Rook);
    }

    private boolean isThereObliqueMover(int x, int y) {
        return parent.chessmen[x][y] != null && (parent.chessmen[x][y].type == chessmanType.Queen || parent.chessmen[x][y].type == chessmanType.Bishop);
    }
}