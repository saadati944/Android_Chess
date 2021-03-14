package com.sdt944.chess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class ChessBoard extends AppCompatActivity {

    public View sampleCell;
    public View[][] board = new View[8][8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);
        sampleCell = findViewById(R.id.sampleCell);

        createCells();
    }

    public void btnTmpClick(View view) {
        startActivityForResult(new Intent(this, PawnPromotion.class), 0);
        TableRow row1 = findViewById(R.id.boardRow1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, PromotionResault.result.toString(), Toast.LENGTH_SHORT).show();

    }

    void createCells() {
        TableRow[] rows = new TableRow[8];
        rows[0] = findViewById(R.id.boardRow1);
        rows[1] = findViewById(R.id.boardRow2);
        rows[2] = findViewById(R.id.boardRow3);
        rows[3] = findViewById(R.id.boardRow4);
        rows[4] = findViewById(R.id.boardRow5);
        rows[5] = findViewById(R.id.boardRow6);
        rows[6] = findViewById(R.id.boardRow7);
        rows[7] = findViewById(R.id.boardRow8);

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                rows[j].addView(createNewCell((i + (j % 2)) % 2 == 0));

        removeSampleCell();
    }

    View createNewCell(boolean white) {
        //ImageView cell = new ImageView(new ContextThemeWrapper(this, R.style.Widget_AppCompat_Button_Borderless), null, 0);
        ImageView cell = new ImageView(this);
        cell.setLayoutParams(sampleCell.getLayoutParams());
        if (!white)
            cell.setBackgroundColor(getResources().getColor(R.color.brown, getTheme()));
        else
            cell.setBackgroundColor(getResources().getColor(R.color.white, getTheme()));
        return cell;
    }

    void removeSampleCell() {
        ((ViewManager) sampleCell.getParent()).removeView(sampleCell);
    }
}