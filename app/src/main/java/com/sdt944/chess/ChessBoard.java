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
}