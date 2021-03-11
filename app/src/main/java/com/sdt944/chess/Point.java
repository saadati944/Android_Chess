package com.sdt944.chess;

public class Point {
    public int x, y;

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
}
