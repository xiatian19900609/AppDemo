package com.xinfu.demo17;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.xinfu.R;

/**
 * <pre>Zara
 *     author : xiatian
 *     time   : 2017/09/22
 *     desc   : 定时刷新View
 * </pre>
 */
public class TimingView extends View {
    private static final int DEFAULT_WIDTH = dp2pxInt(300);
    private static final int DEFAULT_HEIGHT = dp2pxInt(50);
    private int textMargin = dp2pxInt(20);
    private float width;
    private Paint paint;
    private Paint textSelectPaint;
    private Paint textUnSelectPaint;
    private float buttonX = 0;
    private float v;
    private int imageWidth;
    private int status = 0;

    public void setListener(OnTimeListener listener) {
        mListener = listener;
    }

    private OnTimeListener mListener;

    public TimingView(Context context) {
        super(context);
        init();
    }

    public TimingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5f);
        paint.setColor(getResources().getColor(R.color.color_999999));

        textUnSelectPaint = new Paint();
        textUnSelectPaint.setAntiAlias(true);
        textUnSelectPaint.setTextSize(18);
        textUnSelectPaint.setStrokeWidth(1f);
        textUnSelectPaint.setColor(getResources().getColor(R.color.color_666666));

        textSelectPaint = new Paint();
        textSelectPaint.setAntiAlias(true);
        textSelectPaint.setTextSize(18);
        textSelectPaint.setStrokeWidth(1f);
        textSelectPaint.setColor(getResources().getColor(R.color.color_e82c2c));


    }

    @Override
    public final void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(0, 0, 0, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.UNSPECIFIED
                || widthMode == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(DEFAULT_WIDTH, MeasureSpec.EXACTLY);
        }
        if (heightMode == MeasureSpec.UNSPECIFIED
                || heightMode == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(DEFAULT_HEIGHT, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float v = width / 3;
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.user_check_nomal);
        Bitmap bitmapSelect = BitmapFactory.decodeResource(this.getResources(), R.drawable.user_check_select);
        imageWidth = bitmap.getWidth();
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawLine(imageWidth, imageWidth / 2, width - 5, imageWidth / 2, paint);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawBitmap(bitmap, v - imageWidth / 3, 0, null);
        canvas.drawBitmap(bitmap, v * 2 - imageWidth / 2, 0, null);
        canvas.drawBitmap(bitmap, width - imageWidth, 0, null);
        canvas.drawBitmap(bitmapSelect, buttonX, 0, null);

        switch (status) {
            case 0:
                drawText(canvas, textSelectPaint, "10秒", 0, 0);
                drawText(canvas, textUnSelectPaint, "30秒", v, 1);
                drawText(canvas, textUnSelectPaint, "60秒", v * 2, 2);
                drawText(canvas, textUnSelectPaint, "300秒", v * 3, 3);
                break;
            case 1:
                drawText(canvas, textUnSelectPaint, "10秒", 0, 0);
                drawText(canvas, textSelectPaint, "30秒", v, 1);
                drawText(canvas, textUnSelectPaint, "60秒", v * 2, 2);
                drawText(canvas, textUnSelectPaint, "300秒", v * 3, 3);
                break;
            case 2:
                drawText(canvas, textUnSelectPaint, "10秒", 0, 0);
                drawText(canvas, textUnSelectPaint, "30秒", v, 1);
                drawText(canvas, textSelectPaint, "60秒", v * 2, 2);
                drawText(canvas, textUnSelectPaint, "300秒", v * 3, 3);
                break;
            case 3:
                drawText(canvas, textUnSelectPaint, "10秒", 0, 0);
                drawText(canvas, textUnSelectPaint, "30秒", v, 1);
                drawText(canvas, textUnSelectPaint, "60秒", v * 2, 2);
                drawText(canvas, textSelectPaint, "300秒", v * 3, 3);
                break;
        }
    }

    private void drawText(Canvas canvas, Paint paint, String s, float x, int index) {
        Rect bounds = new Rect();
        paint.getTextBounds(s, 0, s.length(), bounds);
        switch (index) {
            case 0:
                canvas.drawText(s, x, imageWidth + textMargin, paint);
                break;
            case 1:
                canvas.drawText(s, x - bounds.width() / 3, imageWidth + textMargin, paint);
                break;
            case 2:
                canvas.drawText(s, x - bounds.width() / 2, imageWidth + textMargin, paint);
                break;
            case 3:
                canvas.drawText(s, x - bounds.width(), imageWidth + textMargin, paint);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = event.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                float y = event.getY();
                if (y > imageWidth) {
                    return false;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                if (x > width - imageWidth) {
                    buttonX = width - imageWidth;
                } else if (x < 0) {
                    buttonX = 0;
                } else {
                    buttonX = x;
                }
                postInvalidate();
                break;

            case MotionEvent.ACTION_UP:
                float eventX = event.getX();
                if (eventX < v / 2) {
                    buttonX = 0;
                    status = 0;
                } else if (eventX >= v / 2 && eventX < v + v / 2) {
                    buttonX = v - imageWidth / 3;
                    status = 1;
                } else if (eventX >= v + v / 2 && eventX < v * 2 + v / 2) {
                    buttonX = v * 2 - imageWidth / 2;
                    status = 2;
                } else {
                    buttonX = width - imageWidth;
                    status = 3;
                }
                if (mListener != null) {
                    mListener.onTimeStateChange(status);
                }
                postInvalidate();
                break;
        }
        return true;

    }

    public interface OnTimeListener {
        void onTimeStateChange(int status);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        v = width / 3;
        postInvalidate();
    }

    /*****************************************************************************/
    private static int dp2pxInt(float dp) {
        return (int) dp2px(dp);
    }

    private static float dp2px(float dp) {
        Resources r = Resources.getSystem();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
