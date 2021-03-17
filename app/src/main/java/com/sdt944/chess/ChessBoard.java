package com.sdt944.chess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class ChessBoard extends AppCompatActivity {
    public ConstraintLayout backgroundLayout;
    public FrameLayout boardLayout;

    public Chess chess = null;
    public int displayWidth, displayHeight, displayMinDimensions;

    public int blackColor, whiteColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);

        //hiding actionbar
        this.getSupportActionBar().hide();

        //change background
        backgroundLayout = findViewById(R.id.backgroundLayout);

        //initiate black and white colors
        blackColor = getResources().getColor(R.color.white, getTheme());
        whiteColor = getResources().getColor(R.color.black, getTheme());


        //set display params
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayHeight = displayMetrics.heightPixels;
        displayWidth = displayMetrics.widthPixels;
        displayMinDimensions = Math.min(displayWidth, displayHeight);

        boardLayout = (FrameLayout)findViewById(R.id.boardLayout);

        boardLayout.getLayoutParams().height = displayMinDimensions;
        boardLayout.getLayoutParams().width = displayMinDimensions;

        if (Storage.chess == null) {
            Storage.chess = chess = new Chess(this, displayMinDimensions, boardLayout);
        }
        else {
            chess = Storage.chess;
            chess.changeLayout(this, displayMinDimensions, boardLayout);
        }

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
    public void showPromotionActivity() {
        startActivityForResult(new Intent(this, PawnPromotion.class), 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Toast.makeText(this, Storage.result.toString(), Toast.LENGTH_SHORT).show();
        chess.promotionResault(Storage.result);
    }
    public void animateTurnChange(Chessman.playerColor turn) {
        ValueAnimator colorAnimation;
        if (turn == Chessman.playerColor.White)
            colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), whiteColor, blackColor);
        else
            colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), blackColor, whiteColor);

        //todo : move this 100 to resources
        colorAnimation.setDuration(100); // milliseconds
        colorAnimation.addUpdateListener(animator -> backgroundLayout.setBackgroundColor((int) animator.getAnimatedValue()));
        colorAnimation.start();
    }

}