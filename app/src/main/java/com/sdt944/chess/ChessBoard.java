package com.sdt944.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class ChessBoard extends AppCompatActivity {
    public ImageButton[/*column*/][/*row*/] board = new ImageButton[8][8];
    public FrameLayout boardLayout;

    public Chess chess = null;
    public int displayWidth, displayHeight, displayMinDimensions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);

        //set display params
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayHeight = displayMetrics.heightPixels;
        displayWidth = displayMetrics.widthPixels;
        displayMinDimensions = Math.min(displayWidth, displayHeight);

        boardLayout = (FrameLayout)findViewById(R.id.boardLayout);

        boardLayout.getLayoutParams().height = displayMinDimensions;
        boardLayout.getLayoutParams().width = displayMinDimensions;

        chess = new Chess(this, displayMinDimensions, boardLayout);

        findViewById(R.id.boardImage).setOnTouchListener(this::onTouch);
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return false;
        int t = displayMinDimensions/8;
        //chess.onBoardClick((int)event.getX() % (displayMinDimensions/8), (int)event.getY()%(displayMinDimensions/8));
        chess.onBoardClick(((int)event.getX())/t, ((int)event.getY())/t);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, PromotionResault.result.toString(), Toast.LENGTH_SHORT).show();

    }

    void setCell(int x, int y) {
        if(chess.chessmen[x][y] != null)
            board[x][y].setImageDrawable(getDrawable(x, y, chess.chessmen[x][y].type, chess.chessmen[x][y].color));
        else
            board[x][y].setImageDrawable(getDrawableColorOnly(x, y));
        board[x][y].setScaleType(ImageButton.ScaleType.FIT_XY);
    }

    ImageButton createNewCell(int i, int j, boolean white) {
        //ImageButton cell = new ImageButton(new ContextThemeWrapper(this, R.style.Widget_AppCompat_Button_Borderless), null, 0);
        ImageButton cell = new ImageButton(this);

        cell.setImageDrawable(getDrawable(i, j, Chessman.chessmanType.King, Chessman.playerColor.Black));
        cell.setScaleType(ImageButton.ScaleType.FIT_XY);

        cell.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        cell.getLayoutParams().width = displayMinDimensions /8;
        cell.getLayoutParams().height = displayMinDimensions /8;
        return cell;
    }

    LayerDrawable getDrawable(int x, int y, Chessman.chessmanType type, Chessman.playerColor playerColor) {
        Drawable image=null, color;
        switch (playerColor)
        {
            case Black:
                switch (type) {
                    case King:
                        image = getResources().getDrawable(R.drawable.kingb, getTheme());
                        break;
                    case Queen:
                        image = getResources().getDrawable(R.drawable.queenb, getTheme());
                        break;
                    case Rook:
                        image = getResources().getDrawable(R.drawable.rookb, getTheme());
                        break;
                    case Bishop:
                        image = getResources().getDrawable(R.drawable.bishopb, getTheme());
                        break;
                    case Knight:
                        image = getResources().getDrawable(R.drawable.knightb, getTheme());
                        break;
                    case Pawn:
                        image = getResources().getDrawable(R.drawable.pawnb, getTheme());
                        break;
                }
                break;
            case White:
                switch (type) {
                    case King:
                        image = getResources().getDrawable(R.drawable.kingw, getTheme());
                        break;
                    case Queen:
                        image = getResources().getDrawable(R.drawable.queenw, getTheme());
                        break;
                    case Rook:
                        image = getResources().getDrawable(R.drawable.rookw, getTheme());
                        break;
                    case Bishop:
                        image = getResources().getDrawable(R.drawable.bishopw, getTheme());
                        break;
                    case Knight:
                        image = getResources().getDrawable(R.drawable.knightw, getTheme());
                        break;
                    case Pawn:
                        image = getResources().getDrawable(R.drawable.pawnw, getTheme());
                        break;
                }
                break;
        }
        if ((x + (y % 2)) % 2 == 0)
            color = new ColorDrawable(getResources().getColor(R.color.white, getTheme()));
        else
            color = new ColorDrawable(getResources().getColor(R.color.brown, getTheme()));

        return new LayerDrawable(new Drawable[]{color, image});
    }
    LayerDrawable getDrawableColorOnly(int x, int y) {
        Drawable color;
        if ((x + (y % 2)) % 2 == 0)
            color = new ColorDrawable(getResources().getColor(R.color.white, getTheme()));
        else
            color = new ColorDrawable(getResources().getColor(R.color.brown, getTheme()));

        return new LayerDrawable(new Drawable[]{color});
    }


}