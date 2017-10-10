package com.xinfu.demo19;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xinfu.R;

import java.util.ArrayList;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/09
 *     desc   :
 * </pre>
 */
public class HistoryLineView extends View {
    private ArrayList<HistoryInfo> data;
    private Paint xyLinePaint;
    private Paint linePaint;
    private Paint textPaint;

    public HistoryLineView(Context context) {
        super(context);
        init(context);
    }

    public HistoryLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HistoryLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        xyLinePaint = new Paint();
        xyLinePaint.setAntiAlias(true);
        xyLinePaint.setStrokeWidth(1f);
        xyLinePaint.setColor(getResources().getColor(R.color.color_666666));

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(1f);
        linePaint.setColor(getResources().getColor(R.color.color_e82c2c));

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(1f);
        textPaint.setColor(getResources().getColor(R.color.color_333333));
        textPaint.setTextSize(18f);
    }

    public void upDateData(ArrayList<HistoryInfo> data) {
        this.data = data;
        this.postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int margin = 20;
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.dc_host_point)).getBitmap();
        int width = getWidth();
        int height = getHeight() - 50;
        int i = width / 7;
        //绘制横向线
        canvas.drawLine(0, 0 + margin, width, 0 + margin, xyLinePaint);
        canvas.drawLine(0, height + margin, width, height + margin, xyLinePaint);
        //绘制纵向线
        for (int x = 0; x < 8; x++) {
            canvas.drawLine(x * i, 0 + margin, i * x, height + margin, xyLinePaint);
        }
        //绘制线
        if (data != null) {
            for (int x = 0; x < data.size() - 1; x++) {
                float money = data.get(x).money;
                float nextMoney = data.get(x + 1).money;
                float v = 100.0f / money;
                float nextV = 100.0f / nextMoney;
                float startX = i / 2 + i * x;
                float startY = height - (height / v);
                float endX = i / 2 + i * (x + 1);
                float endY = height - (height / nextV);
                canvas.drawLine(startX, startY + margin, endX, endY + margin, linePaint);
            }
        }

        for (int x = 0; x < data.size(); x++) {
            float money = data.get(x).money;
            String date = data.get(x).date;
            float v = 100.0f / money;
            float startX = i / 2 + i * x;
            float startY = height - (height / v);
            //绘制点
            canvas.drawBitmap(bitmap, startX - bitmap.getWidth() / 2, startY - bitmap.getWidth() / 2 + margin, null);
            //绘制文字
            float textWidth = textPaint.measureText(String.valueOf(money));
            canvas.drawText(String.valueOf(money), startX - textWidth / 2, startY + 30 + margin, textPaint);
            float dateTextWidth = textPaint.measureText(date);
            canvas.drawText(date, startX - dateTextWidth / 2, height + 40, textPaint);
        }

    }
}
