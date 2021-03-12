package com.sdt944.chess;

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

    public ArrayList<Point> moves;
    public chessmanType type;
    public playerColor color;
    public boolean isDead = false;

    public ArrayList<Point> getMoves()
    {
        return moves;
    }

    abstract void generateMoves();

    public void setPoint(Point p) { point = p; }
    public Point getPoint()
    {
        return point;
    }

    public void addVerticalMovePoints() {
        /*
         *  ...#....
         *  ...#....
         *  ...#....
         *  ...X....
         *  ...#....
         *  ...#....
         *  ...#....
         *  ...#....
         * */
        for(int j = 0; j <8; j++)
        {
            Point p = new Point(point.x, j);

            if(j == point.y || moves.contains(p))
                continue;

            moves.add(p);
        }
    }
    public void addHorizontalMovePoints() {
        /*
         *  ........
         *  ........
         *  ........
         *  ###X####
         *  ........
         *  ........
         *  ........
         *  ........
         * */
        for(int i=0; i<8; i++)
        {
            Point p = new Point(i, point.y);

            if(i == point.x || moves.contains(p))
                continue;

            moves.add(p);
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
        for(int i=this.getPoint().x-1; i<this.getPoint().x+2; i++)
            for(int j=this.getPoint().y-1; j<this.getPoint().y+2; j++)
                if(Point.isValid(i, j) && point.x != i && point.y != j)
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
        int i = point.x-1, j = point.y-1;
        while(Point.isValid(i, j))
        {
            Point p = new Point(i, j);

            if(!moves.contains(p))
            {
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
        i = point.x+1;
        j = point.y+1;
        while(Point.isValid(i, j))
        {
            Point p = new Point(i, j);

            if(!moves.contains(p))
            {
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
        int i = point.x+1, j = point.y-1;
        while(Point.isValid(i, j))
        {
            Point p = new Point(i, j);

            if(!moves.contains(p))
            {
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
        i = point.x-1;
        j = point.y+1;
        while(Point.isValid(i, j))
        {
            Point p = new Point(i, j);

            if(!moves.contains(p))
            {
                moves.add(p);
            }

            i--;
            j++;
        }
    }
    public void add1StepForwardMovePoints() {
        if(color == playerColor.Black)
        {
            addForwardMovePoints(1);
            return;
        }
        addForwardMovePoints(-1);
    }
    public void add2StepForwardMovePoints() {
        if(color == playerColor.Black)
        {
            addForwardMovePoints(2);
            return;
        }
        addForwardMovePoints(-2);
    }
    private void addForwardMovePoints(int step) {
        Point p = new Point(point.x+step, point.y);
        if(p.isValid() && !moves.contains(p))
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
        if (p.isValid() && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x - 1, point.y + 2);
        if (p.isValid() && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x - 2, point.y - 1);
        if (p.isValid() && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x - 2, point.y + 1);
        if (p.isValid() && !moves.contains(p))
            moves.add(p);

        p = new Point(point.x + 1, point.y - 2);
        if (p.isValid() && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x + 1, point.y + 2);
        if (p.isValid() && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x + 2, point.y - 1);
        if (p.isValid() && !moves.contains(p))
            moves.add(p);
        p = new Point(point.x + 2, point.y + 1);
        if (p.isValid() && !moves.contains(p))
            moves.add(p);
    }
}