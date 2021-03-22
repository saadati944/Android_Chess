package com.sdt944.chess;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

public abstract class Chessman {
    public enum ChessmanType {
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
    public enum PlayerColor {
        Black,
        White
    }

    private enum PathConditions {
        Increase,
        Decrease,
        Hold
    }

    private Point point;

    public ArrayList<Point> moves = new ArrayList<>();
    public ChessmanType type;
    public PlayerColor color;
    public boolean isDead = false;
    public ImageButton button;
    public Chess parent;
    public int width;
    public int minDimension;


    public ArrayList<Point> getMoves() {
        return moves;
    }

    public abstract void generateMoves();

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

        if (color == PlayerColor.White)
            MediaPlayer.create(parent.ctx, R.raw.chess1).start();
        else
            MediaPlayer.create(parent.ctx, R.raw.chess2).start();

        button.animate()
                .x(width * getPoint().x)
                .y(width * getPoint().y)
                //todo : move this 300 to resources
                .setDuration(300)
                .start();
    }

    public boolean isPointSafe() {
        return isPointSafe(this.getPoint());
    }

    public boolean isPointSafe(Point point) {
        //                vertical/horizontal checks
        if(!isPathSafe(point, PathConditions.Hold, PathConditions.Decrease))
             return false;
        if(!isPathSafe(point, PathConditions.Hold, PathConditions.Increase))
            return false;
        if(!isPathSafe(point, PathConditions.Decrease, PathConditions.Hold))
            return false;
        if(!isPathSafe(point, PathConditions.Increase, PathConditions.Hold))
            return false;

        //                oblique checks

        if(!isPathSafe(point, PathConditions.Decrease, PathConditions.Decrease))
            return false;
        if(!isPathSafe(point, PathConditions.Increase, PathConditions.Increase))
            return false;
        if(!isPathSafe(point, PathConditions.Decrease, PathConditions.Increase))
            return false;
        if(!isPathSafe(point, PathConditions.Increase, PathConditions.Decrease))
            return false;


        //                knight checks
        int x = point.x - 1;
        int y = point.y - 2;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Knight)
            return false;
        x = point.x - 1;
        y = point.y + 2;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Knight)
            return false;
        x = point.x - 2;
        y = point.y - 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Knight)
            return false;
        x = point.x - 2;
        y = point.y + 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Knight)
            return false;
        x = point.x + 1;
        y = point.y - 2;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Knight)
            return false;
        x = point.x + 1;
        y = point.y + 2;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Knight)
            return false;
        x = point.x - 2;
        y = point.y - 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Knight)
            return false;
        x = point.x + 2;
        y = point.y + 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Knight)
            return false;

        //                pawn checks
        if (color == PlayerColor.Black)
            y = point.y + 1;
        else
            y = point.y - 1;
        x = point.x - 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Pawn)
            return false;
        x = point.x + 1;
        if (Point.isValid(x, y) && parent.chessmen[x][y] != null
                && parent.chessmen[x][y].color != color
                && parent.chessmen[x][y].type == ChessmanType.Pawn)
            return false;

        //                king checks
        for (int i = point.x - 1; i < point.x + 2; i++)
            for (int j = point.y - 1; j < point.y + 2; j++)
                if (Point.isValid(i, j) && !(point.x == i && point.y == j) && parent.chessmen[i][j] != null && parent.chessmen[i][j].color != color && parent.chessmen[i][j].type == ChessmanType.King)
                    return false;

        return true;
    }

    private boolean isPathSafe(Point p, PathConditions xCondition, PathConditions yCondition) {
        int x = doCondition(p.x, xCondition);
        int y = doCondition(p.y, yCondition);
        while (Point.isValid(x, y)) {
            if (parent.chessmen[x][y] != null) {
                if (parent.chessmen[x][y].color == color)
                    break;
                if ((xCondition != PathConditions.Hold && yCondition != PathConditions.Hold) && isThereObliqueMover(x, y) || isThereDirectMover(x, y))
                    return false;
                else
                    break;
            }
            x = doCondition(x, xCondition);
            y = doCondition(y, yCondition);
        }
        return true;
    }
    private int doCondition(int v, PathConditions c) {
        if(c == PathConditions.Increase)
            return ++v;
        if(c == PathConditions.Decrease)
            return --v;
        return v;
    }

    private boolean isThereDirectMover(int x, int y) {
        return parent.chessmen[x][y] != null && (parent.chessmen[x][y].type == ChessmanType.Queen || parent.chessmen[x][y].type == ChessmanType.Rook);
    }

    private boolean isThereObliqueMover(int x, int y) {
        return parent.chessmen[x][y] != null && (parent.chessmen[x][y].type == ChessmanType.Queen || parent.chessmen[x][y].type == ChessmanType.Bishop);
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
        while (p.isValid()) {
            if (parent.chessmen[p.x][p.y] == null) {
                if (!moves.contains(p))
                    moves.add(p);
                p = new Point(p.x, p.y - 1);
                continue;
            }
            if (parent.chessmen[p.x][p.y].color != color) {
                if (!moves.contains(p))
                    moves.add(p);
                break;
            }
            break;
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
        while (p.isValid()) {
            if (parent.chessmen[p.x][p.y] == null) {
                if (!moves.contains(p))
                    moves.add(p);
                p = new Point(p.x, p.y + 1);
                continue;
            }
            if (parent.chessmen[p.x][p.y].color != color) {
                if (!moves.contains(p))
                    moves.add(p);
                break;
            }
            break;
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
        while (p.isValid()) {
            if (parent.chessmen[p.x][p.y] == null) {
                if (!moves.contains(p))
                    moves.add(p);
                p = new Point(p.x - 1, p.y);
                continue;
            }
            if (parent.chessmen[p.x][p.y].color != color) {
                if (!moves.contains(p))
                    moves.add(p);
                break;
            }
            break;
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
        while (p.isValid()) {
            if (parent.chessmen[p.x][p.y] == null) {
                if (!moves.contains(p))
                    moves.add(p);
                p = new Point(p.x + 1, p.y);
                continue;
            }
            if (parent.chessmen[p.x][p.y].color != color) {
                if (!moves.contains(p))
                    moves.add(p);
                break;
            }
            break;
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
        int i = point.x - 1;
        int j = point.y - 1;
        while (Point.isValid(i, j)) {
            Point p = new Point(i, j);
            i--;
            j--;
            if (parent.chessmen[p.x][p.y] == null) {
                if (!moves.contains(p))
                    moves.add(p);
                continue;
            }
            if (parent.chessmen[p.x][p.y].color != color) {
                if (!moves.contains(p))
                    moves.add(p);
                break;
            }
            break;
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
        while (Point.isValid(i, j)) {
            Point p = new Point(i, j);
            i++;
            j++;
            if (parent.chessmen[p.x][p.y] == null) {
                if (!moves.contains(p))
                    moves.add(p);
                continue;
            }
            if (parent.chessmen[p.x][p.y].color != color) {
                if (!moves.contains(p))
                    moves.add(p);
                break;
            }
            break;
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
        int i = point.x + 1;
        int j = point.y - 1;
        while (Point.isValid(i, j)) {
            Point p = new Point(i, j);
            i++;
            j--;
            if (parent.chessmen[p.x][p.y] == null) {
                if (!moves.contains(p))
                    moves.add(p);
                continue;
            }
            if (parent.chessmen[p.x][p.y].color != color) {
                if (!moves.contains(p))
                    moves.add(p);
                break;
            }
            break;
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
        while (Point.isValid(i, j)) {
            Point p = new Point(i, j);
            i--;
            j++;
            if (parent.chessmen[p.x][p.y] == null) {
                if (!moves.contains(p))
                    moves.add(p);
                continue;
            }
            if (parent.chessmen[p.x][p.y].color != color) {
                if (!moves.contains(p))
                    moves.add(p);
                break;
            }
            break;
        }
    }

    public void add1StepForwardMovePoints() {
        if (color == PlayerColor.Black) {
            addForwardMovePoints(1);
            return;
        }
        addForwardMovePoints(-1);
    }

    public void add2StepForwardMovePoints() {
        if (color == PlayerColor.Black) {
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