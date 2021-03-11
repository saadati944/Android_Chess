package com.sdt944.chess;

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
    private Point[] moves;
    public chessmanType type;

    public Point[] getMoves()
    {
        return moves;
    }

    abstract void generateMoves();

    public void setPoint(Point p)
    {
        point = p;
        generateMoves();
    }
    public Point getPoint()
    {
        return point;
    }
}