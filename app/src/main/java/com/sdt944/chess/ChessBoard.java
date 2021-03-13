package com.sdt944.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class ChessBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);
    }
    public void btnTmpClick(View view)
    {
        startActivityForResult(new Intent(this, PawnPromotion.class), 0);
        TableRow row1 = findViewById(R.id.boardRow1);

        Button btn = new Button(new ContextThemeWrapper(this, R.style.Widget_AppCompat_Button_Small), null, 0);
        btn.setLayoutParams(view.getLayoutParams());

        row1.addView(btn);

        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, PromotionResault.result.toString(), Toast.LENGTH_SHORT).show();

    }
}