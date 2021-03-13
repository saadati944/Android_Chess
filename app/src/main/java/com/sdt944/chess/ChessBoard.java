package com.sdt944.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class ChessBoard extends AppCompatActivity {

    public View sampleCell;
    public View[][] board=new View[8][8];

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

        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                rows[j].addView(createNewCell());

        removeSampleCell();
    }
    View createNewCell() {
        //todo : change button with a better view like imageView.
        Button cell = new Button(new ContextThemeWrapper(this, R.style.Widget_AppCompat_Button_Small), null, 0);
        cell.setLayoutParams(sampleCell.getLayoutParams());

        return cell;
    }
    void removeSampleCell() {
        ((ViewManager)sampleCell.getParent()).removeView(sampleCell);
    }
}