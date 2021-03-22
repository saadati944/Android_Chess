package com.sdt944.chess;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Chess {
    public ArrayList<Chessman> deadMen = new ArrayList<>();
    public Chessman[/*column - x*/][/*row - y*/] chessmen = new Chessman[8][8];
    public Chessman.PlayerColor whichPlayerTurn = Chessman.PlayerColor.Black;
    public Point lastManPoint = null;
    public Context ctx;
    public int minDimension = 0;
    private Chessman manToPromote = null;
    private FrameLayout boardLayout = null;

    public ArrayList<View> validMoveButtons = new ArrayList<>();

    //todo : try to remove this property if not needed
    private boolean gameEnd = false;

    public King whiteKing = null;
    public King blackKing = null;


    public Chess(Context ctx, int minDimension, FrameLayout boardLayout) {
        setLayoutParams(ctx, minDimension, boardLayout);

        /*          BOARD
         *    <  X  >
         *    RkbQKBkR    WBWBWBWB
         *  < PPPPPPPP    BWBWBWBW
         *    ..BLACK.    WBWBWBWB
         *  y ........    BWBWBWBW
         *    ........    WBWBWBWB
         *  > .WHITE..    BWBWBWBW
         *    PPPPPPPP    WBWBWBWB
         *    RkBQKBkR    BWBWBWBW
         * */

        //first row
        chessmen[0][0] = new Rook(new Point(0, 0), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[1][0] = new Knight(new Point(1, 0), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[2][0] = new Bishop(new Point(2, 0), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[3][0] = new Queen(new Point(3, 0), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[4][0] = blackKing = new King(new Point(4, 0), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[5][0] = new Bishop(new Point(5, 0), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[6][0] = new Knight(new Point(6, 0), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[7][0] = new Rook(new Point(7, 0), Chessman.PlayerColor.Black, minDimension, this);

        //second row
        chessmen[0][1] = new Pawn(new Point(0, 1), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[1][1] = new Pawn(new Point(1, 1), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[2][1] = new Pawn(new Point(2, 1), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[3][1] = new Pawn(new Point(3, 1), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[4][1] = new Pawn(new Point(4, 1), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[5][1] = new Pawn(new Point(5, 1), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[6][1] = new Pawn(new Point(6, 1), Chessman.PlayerColor.Black, minDimension, this);
        chessmen[7][1] = new Pawn(new Point(7, 1), Chessman.PlayerColor.Black, minDimension, this);

        //seventh row
        chessmen[0][6] = new Pawn(new Point(0, 6), Chessman.PlayerColor.White, minDimension, this);
        chessmen[1][6] = new Pawn(new Point(1, 6), Chessman.PlayerColor.White, minDimension, this);
        chessmen[2][6] = new Pawn(new Point(2, 6), Chessman.PlayerColor.White, minDimension, this);
        chessmen[3][6] = new Pawn(new Point(3, 6), Chessman.PlayerColor.White, minDimension, this);
        chessmen[4][6] = new Pawn(new Point(4, 6), Chessman.PlayerColor.White, minDimension, this);
        chessmen[5][6] = new Pawn(new Point(5, 6), Chessman.PlayerColor.White, minDimension, this);
        chessmen[6][6] = new Pawn(new Point(6, 6), Chessman.PlayerColor.White, minDimension, this);
        chessmen[7][6] = new Pawn(new Point(7, 6), Chessman.PlayerColor.White, minDimension, this);

        //eighth row
        chessmen[0][7] = new Rook(new Point(0, 7), Chessman.PlayerColor.White, minDimension, this);
        chessmen[1][7] = new Knight(new Point(1, 7), Chessman.PlayerColor.White, minDimension, this);
        chessmen[2][7] = new Bishop(new Point(2, 7), Chessman.PlayerColor.White, minDimension, this);
        chessmen[3][7] = new Queen(new Point(3, 7), Chessman.PlayerColor.White, minDimension, this);
        chessmen[4][7] = whiteKing = new King(new Point(4, 7), Chessman.PlayerColor.White, minDimension, this);
        chessmen[5][7] = new Bishop(new Point(5, 7), Chessman.PlayerColor.White, minDimension, this);
        chessmen[6][7] = new Knight(new Point(6, 7), Chessman.PlayerColor.White, minDimension, this);
        chessmen[7][7] = new Rook(new Point(7, 7), Chessman.PlayerColor.White, minDimension, this);

        for (int i = 0; i < 8; i++)
            for (int j = 2; j < 6; j++) {
                chessmen[i][j] = null;
            }

        addMenToBoard(boardLayout);
        whichPlayerTurn = Chessman.PlayerColor.Black;
        changeTurn();
    }

    public void changeLayout(Context ctx, int minDimension, FrameLayout boardLayout) {
        setLayoutParams(ctx, minDimension, boardLayout);
        addMenToBoard(boardLayout);
        if (whichPlayerTurn == Chessman.PlayerColor.White)
            whichPlayerTurn = Chessman.PlayerColor.Black;
        else
            whichPlayerTurn = Chessman.PlayerColor.White;
        changeTurn();
    }

    private void setLayoutParams(Context ctx, int minDimension, FrameLayout boardLayout) {
        this.ctx = ctx;
        this.minDimension = minDimension;
        this.boardLayout = boardLayout;
    }

    public void onManClick(Chessman man) {
        if (gameEnd)
            return;
        if (man.color == whichPlayerTurn) {
            lastManPoint = man.getPoint();

            resetValidMoveButtons();
            chessmen[lastManPoint.x][lastManPoint.y].generateMoves();
            addValidMoveButtons(chessmen[lastManPoint.x][lastManPoint.y].moves);
        } else if (lastManPoint != null && chessmen[lastManPoint.x][lastManPoint.y].moves.contains(man.getPoint())) {
            onBoardClick(man.getPoint().x, man.getPoint().y);
        }
    }

    public void onBoardClick(int x, int y) {
        if (gameEnd)
            return;
        if (lastManPoint == null)
            return;
        Point clickPoint = new Point(x, y);
        if (chessmen[lastManPoint.x][lastManPoint.y].moves.contains(clickPoint)) {
            doMove(lastManPoint, clickPoint);
        }
    }

    public void doMove(Point from, Point to) {
        if (move(from.x, from.y, to.x, to.y)) {
            resetValidMoveButtons();
            if (chessmen[to.x][to.y].type == Chessman.ChessmanType.Pawn &&
                    (chessmen[to.x][to.y].color == Chessman.PlayerColor.White && chessmen[to.x][to.y].getPoint().y == 0 //white reaches end
                            || chessmen[to.x][to.y].color == Chessman.PlayerColor.Black && chessmen[to.x][to.y].getPoint().y == 7)) //black reaches end
                promote(chessmen[to.x][to.y]);
            changeTurn();
            lastManPoint = null;
        }
    }

    public boolean move(int xf, int yf, int xt, int yt) {
        Chessman tempMan = chessmen[xt][yt];
        chessmen[xt][yt] = chessmen[xf][yf];
        chessmen[xt][yt].setPoint(new Point(xt, yt));
        chessmen[xf][yf] = null;

        King.KingRiskType relatedKingStatus;
        if (chessmen[xt][yt].color == Chessman.PlayerColor.White)
            relatedKingStatus = validateKing(whiteKing);
        else
            relatedKingStatus = validateKing(blackKing);

        if (relatedKingStatus == King.KingRiskType.Safe) {
            if (tempMan != null)
                kill(tempMan);
            chessmen[xt][yt].setPoint(new Point(xt, yt));
            chessmen[xt][yt].moveButton(xt, yt);
            if (chessmen[xt][yt].type == Chessman.ChessmanType.Pawn)
                ((Pawn) chessmen[xt][yt]).firstMove = false;
            return true;
        }

        chessmen[xt][yt].setPoint(new Point(xf, yf));
        chessmen[xf][yf] = chessmen[xt][yt];
        chessmen[xt][yt] = tempMan;
        Toast.makeText(ctx, "Illegal movement !", Toast.LENGTH_SHORT).show();
        return false;
    }

    private King.KingRiskType validateKing(King k) {
        k.generateMoves();

        boolean check = !k.isPointSafe();
        boolean mate = true;

        for (Point p : k.moves) {
            if (k.isPointSafe(p)) {
                mate = false;
                break;
            }
        }

        if (check && mate)
            return King.KingRiskType.CheckMate;
        if (check)
            return King.KingRiskType.Check;
        return King.KingRiskType.Safe;
    }

    public void kill(Chessman m) {
        deadMen.add(m);
        m.isDead = true;
        ((ViewGroup) m.button.getParent()).removeView(m.button);
    }

    public void changeTurn() {
        if (whichPlayerTurn == Chessman.PlayerColor.White)
            whichPlayerTurn = Chessman.PlayerColor.Black;
        else
            whichPlayerTurn = Chessman.PlayerColor.White;
        ((ChessBoard) ctx).animateTurnChange(whichPlayerTurn);
    }

    public void promote(Chessman man) {
        manToPromote = man;
        ((ChessBoard) ctx).showPromotionActivity();
    }

    public void promotionResault(Chessman.ChessmanType toType) {

        Chessman newType = null;
        switch (toType) {
            case Queen:
                newType = new Queen(manToPromote.getPoint(), manToPromote.color, minDimension, this);
                break;
            case Rook:
                newType = new Rook(manToPromote.getPoint(), manToPromote.color, minDimension, this);
                break;
            case Bishop:
                newType = new Bishop(manToPromote.getPoint(), manToPromote.color, minDimension, this);
                break;
            case Knight:
                newType = new Knight(manToPromote.getPoint(), manToPromote.color, minDimension, this);
                break;
            default:
                return;
        }
        newType.createButton();
        ((ViewGroup) manToPromote.button.getParent()).removeView(manToPromote.button);
        chessmen[manToPromote.getPoint().x][manToPromote.getPoint().y] = newType;
        boardLayout.addView(newType.button);
    }

    private void addMenToBoard(FrameLayout boardLayout) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (chessmen[i][j] == null)
                    continue;
                chessmen[i][j].createButton();
                boardLayout.addView(chessmen[i][j].button);
            }
    }


    public void createValidMoveButton(Point p) {
        ImageButton btn = new ImageButton(ctx);
        int width = minDimension / 8;
        this.minDimension = minDimension;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);

        lp.setMargins(width * p.x, width * p.y, minDimension - (width * p.x + width), minDimension - (width * p.y + width));

        btn.setLayoutParams(lp);
        btn.setBackground(ctx.getResources().getDrawable(R.drawable.point, ctx.getTheme()));

        btn.setOnClickListener(v -> {
            onBoardClick(p.x, p.y);
        });

        validMoveButtons.add(btn);
        boardLayout.addView(btn);
    }

    public void addValidMoveButtons(ArrayList<Point> validMoves) {
        for (Point p : validMoves) {
            createValidMoveButton(p);
        }
    }

    public void resetValidMoveButtons() {
        for (View v : validMoveButtons)
            ((ViewGroup) v.getParent()).removeView(v);

        validMoveButtons.clear();
    }
}