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

    private Point point;
    public ArrayList<Point> moves;
    public chessmanType type;

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
}