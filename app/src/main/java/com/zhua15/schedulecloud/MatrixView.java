package com.zhua15.schedulecloud;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MatrixView extends View {
    private Paint paint;
    private int rows;
    private int columns;
    public Person [][]persons;

    public MatrixView(Context context) {
        super(context);
        init(null);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        paint = new Paint();
        columns = 6;
        rows = 19;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int cellHeight = getHeight() / rows;
        int cellWidth = getWidth() / columns;

        for (int row = 1; row < 19; row++) {
            for (int col = 1; col < 6; col++) {
                if (persons[col-1][row-1] != null) {
                    paint.setColor(Color.BLACK);
                    canvas.drawRect(col * cellWidth, row *
                            cellHeight, (col + 1) * cellWidth, (row +
                            1) * cellHeight, paint);
                }
            }
        }
    }
}
