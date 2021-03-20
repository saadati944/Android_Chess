package com.sdt944.chess;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

public abstract class Chessman {
    public enum chessmanType {
        King,
        Queen,
        Rook,
        Bishop,
        Knight,
        Pawn
    }

    /*
     *  .BLACK..
     *  ........
     *  ........
     *  ...X....
     *  ........
     *  ........
     *  ........
     *  .WHITE..
     * */
    public enum playerColor {
        Black,
        White
    }

    private Point point;

    public ArrayList<Point> moves = new ArrayList<>();
    public chessmanType type;
    public playerColor color;
    public boolean isDead = false;
    public ImageButton button;
    public Chess parent;
    public int width;
    public int minDimension;



    public ArrayList<Point> getMoves() {
        return moves;
    }

    abstract void generateMoves();

    public void setPoint(Point p) {
        point = p;
    }

    public Point getPoint() {
        return point;
    }

    public abstract void createButton();

    public void createButton(Drawable icon, int minDimension, Context ctx) {
        ImageButton btn = new ImageButton(ctx);
        width = minDimension / 8;
        this.minDimension = minDimension;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);

        lp.setMargins(width * getPoint().x, width * getPoint().y, minDimension - (width * getPoint().x + width), minDimension - (width * getPoint().y + width));

        btn.setLayoutParams(lp);
        btn.setBackground(icon);

        btn.setOnClickListener(v -> {
            parent.onManClick(this);
        });

        this.button = btn;
    }

    public void moveButton(int x, int y) {
        button.animate()
                .x(width * getPoint().x)
                .y(width * getPoint().y)
                //todo : move this 200 to resources
                .setDuration(200)
                .start();
    }

    public boolean isPointSafe() {
        return isPointSafe(this.getPoint());
    }
    public boolean isPointSafe(Point point) {
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

    public void addVerticalMovePoints() {
        /*
         *  ...#....
         *  ...#....
         *  ...#....
         *  ...X....
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        Point p = new Point(point.x, point.y - 1);
        while (p.isValid() && isPointMovable(p)) {
            if (!moves.contains(p))
                moves.add(p);

            p = new Point(p.x, p.y - 1);
        }
        /*
         *  ........
         *  ........
         *  ........
         *  ...X....
         *  ...#....
         *  ...#....
         *  ...#....
         *  ...#....
         * */
        p = new Point(point.x, point.y + 1);
        while (p.isValid() && isPointMovable(p)) {
            if (!moves.contains(p))
                moves.add(p);

            p = new Point(p.x, p.y + 1);
        }
    }

    public void addHorizontalMovePoints() {
        /*
         *  ........
         *  ........
         *  ........
         *  ###X....
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        Point p = new Point(point.x - 1, point.y);
        while (p.isValid() && isPointMovable(p)) {
            if (!moves.contains(p))
                moves.add(p);

            p = new Point(p.x - 1, p.y);
        }
        /*
         *  ........
         *  ........
         *  ........
         *  ...X####
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        p = new Point(point.x + 1, point.y);
        while (p.isValid() && isPointMovable(p)) {
            if (!moves.contains(p))
                moves.add(p);

            p = new Point(p.x + 1, p.y);
        }
    }

    public void addAroundMovePoints() {
        /*
         *  ........
         *  ........
         *  ..###...
         *  ..#X#...
         *  ..###...
         *  ........
         *  ........
         *  ........
         * */
        for (int i = this.getPoint().x - 1; i < this.getPoint().x + 2; i++)
            for (int j = this.getPoint().y - 1; j < this.getPoint().y + 2; j++)
                if (Point.isValid(i, j) && !(point.x == i && point.y == j) && isPointMovable(i, j))
                    this.moves.add(new Point(i, j));
    }

    public void addObliqueNWtoSEMovePoints() {
        /*
         *  #.......
         *  .#......
         *  ..#.....
         *  ...X....
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        int i = point.x - 1, j = point.y - 1;
        while (Point.isValid(i, j) && isPointMovable(i, j)) {
            Point p = new Point(i, j);

            if (!moves.contains(p)) {
                moves.add(p);
            }

            i--;
            j--;
        }
        /*
         *  ........
         *  ........
         *  ........
         *  ...X....
         *  ....#...
         *  .....#..
         *  ......#.
         *  .......#
         * */
        i = point.x + 1;
        j = point.y + 1;
        while (Point.isValid(i, j) && isPointMovable(i, j)) {
            Point p = new Point(i, j);

            if (!moves.contains(p)) {
                moves.add(p);
            }

            i++;
            j++;
        }
    }

    public void addObliqueNEtoSWMovePoints() {
        /*
         *  ......#.
         *  .....#..
         *  ....#...
         *  ...X....
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        int i = point.x + 1, j = point.y - 1;
        while (Point.isValid(i, j) && isPointMovable(i, j)) {
            Point p = new Point(i, j);

            if (!moves.contains(p)) {
                moves.add(p);
            }

            i++;
            j--;
        }
        /*
         *  ........
         *  ........
         *  ........
         *  ...X....
         *  ..#.....
         *  .#......
         *  #.......
         *  ........
         * */
        i = point.x - 1;
        j = point.y + 1;
        while (Point.isValid(i, j) && isPointMovable(i, j)) {
            Point p = new Point(i, j);

            if (!moves.contains(p)) {
                moves.add(p);
            }

            i--;
            j++;
        }
    }

    public void add1StepForwardMovePoints() {
        if (color == playerColor.Black) {
            addForwardMovePoints(1);
            return;
        }
        addForwardMovePoints(-1);
    }

    public void add2StepForwardMovePoints() {
        if (color == playerColor.Black) {
            addForwardMovePoints(2);
            return;
        }
        addForwardMovePoints(-2);
    }

    private void addForwardMovePoints(int step) {
        Point p = new Point(point.x, point.y + step);
        if (p.isValid() && !moves.contains(p))
            moves.add(p);
    }

    public void addLMovePoints() {
        /*
         *  ........
         *  ..#.#...
         *  .#...#..
         *  ...X....
         *  .#...#..
         *  ..#.#...
         *  ........
         *  ........
         * */
        Point p = new Point(point.x - 1, point.y - 2);
        if (p.isValid() && isPointMovable(p) && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x - 1, point.y + 2);
        if (p.isValid() && isPointMovable(p) && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x - 2, point.y - 1);
        if (p.isValid() && isPointMovable(p) && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x - 2, point.y + 1);
        if (p.isValid() && isPointMovable(p) && !moves.contains(p))
            moves.add(p);

        p = new Point(point.x + 1, point.y - 2);
        if (p.isValid() && isPointMovable(p) && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x + 1, point.y + 2);
        if (p.isValid() && isPointMovable(p) && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x + 2, point.y - 1);
        if (p.isValid() && isPointMovable(p) && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x + 2, point.y + 1);
        if (p.isValid() && isPointMovable(p) && !moves.contains(p))
            moves.add(p);
    }

    private boolean isPointMovable(Point p) {
        return parent.chessmen[p.x][p.y] == null || parent.chessmen[p.x][p.y].color != color;
    }

    private boolean isPointMovable(int x, int y) {
        return parent.chessmen[x][y] == null || parent.chessmen[x][y].color != color;
    }
}