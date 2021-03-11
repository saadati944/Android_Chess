package com.sdt944.chess;

public class Point {
    public int x, y;

    /*
     *  <- X ->
     *  ........ ˄
     *  ........ |
     *  ........
     *  ........ Y
     *  ........
     *  ........ |
     *  ........ ˅
     *  ........
     * */

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean isValid()
    {
        return isValid(x, y);
    }
    public static boolean isValid(Point p)
    {
        return isValid(p.x, p.y);
    }
    public static boolean isValid(int x, int y)
    {
        return (x>=0 && x<8) && (y>=0 && y<8);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        return ((Point)obj).x == this.x && ((Point)obj).y == this.y;
    }
}
