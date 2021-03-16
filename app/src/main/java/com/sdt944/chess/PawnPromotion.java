package com.sdt944.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PawnPromotion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pawn_promotion);

        getSupportActionBar().hide();
    }

    public void selectNewType(View view)
    {
        if(view.getId() == R.id.btnQueen)
            select(Chessman.chessmanType.Queen);
        else if(view.getId() == R.id.btnRook)
            select(Chessman.chessmanType.Rook);
        else if(view.getId() == R.id.btnBishop)
            select(Chessman.chessmanType.Bishop);
        else if(view.getId() == R.id.btnKnight)
            select(Chessman.chessmanType.Knight);
        else
            select(Chessman.chessmanType.Queen);
    }
    void select(Chessman.chessmanType newType)
    {
        PromotionResault.result = newType;
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
}