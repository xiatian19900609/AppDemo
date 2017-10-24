package com.xinfu.demo21;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.xinfu.R;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/13
 *     desc   :
 * </pre>
 */
public class ThreeColorProgress extends View {
    private static final int DEFAULT_WIDTH = dp2pxInt(58);

    private int s = 1;
    private int p = 1;
    private int f = 1;
    private Paint mFPaint;
    private Paint mSPaint;
    private Paint mPPaint;
    private int sColor;
    private int pColor;
    private int fColor;
    private String strWin;
    private String strPing;
    private String strLose;
    private Paint mSTextPaint;
    private Paint mPTextPaint;
    private Paint mFTextPaint;
    private float mTextSize;
    private float progressHeight;
    private float progressPadding;

    public void setData(int s, int p, int f) {
        this.s = s;
        this.p = p;
        this.f = f;
        postInvalidate();
    }

    public ThreeColorProgress(Context context) {
        super(context);
        init(context, null);
    }

    public ThreeColorProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public ThreeColorProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (context == null || attrs == null) {
            return;
        }

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ThreeColorProgress);
        sColor = array.getColor(R.styleable.ThreeColorProgress_tcp_s_color, getResources().getColor(R.color.color_e82c2c));
        pColor = array.getColor(R.styleable.ThreeColorProgress_tcp_p_color, getResources().getColor(R.color.color_e82c2c));
        fColor = array.getColor(R.styleable.ThreeColorProgress_tcp_f_color, getResources().getColor(R.color.color_e82c2c));
        progressHeight = array.getDimension(R.styleable.ThreeColorProgress_progress_h, getResources().getDimension(R.dimen.zy_dimen_20));
        progressPadding = array.getDimension(R.styleable.ThreeColorProgress_tcp_padding, getResources().getDimension(R.dimen.zy_dimen_5));
        mTextSize = array.getDimension(R.styleable.ThreeColorProgress_text_size, getResources().getDimension(R.dimen.size_14));
        //胜
        mSPaint = new Paint();
        mSPaint.setColor(sColor);
        mSPaint.setAntiAlias(true);
        mSPaint.setStrokeWidth(progressHeight);
        mSPaint.setStrokeCap(Paint.Cap.ROUND);

        mSTextPaint = new Paint();
        mSTextPaint.setAntiAlias(true);
        mSTextPaint.setStrokeWidth(1F);
        mSTextPaint.setColor(sColor);
        mSTextPaint.setTextSize(mTextSize);

        //平
        mPPaint = new Paint();
        mPPaint.setColor(pColor);
        mPPaint.setAntiAlias(true);
        mPPaint.setStrokeWidth(progressHeight);

        mPTextPaint = new Paint();
        mPTextPaint.setAntiAlias(true);
        mPTextPaint.setStrokeWidth(1F);
        mPTextPaint.setColor(pColor);
        mPTextPaint.setTextSize(mTextSize);

        //负
        mFPaint = new Paint();
        mFPaint.setColor(fColor);
        mFPaint.setAntiAlias(true);
        mFPaint.setStrokeWidth(progressHeight);
        mFPaint.setStrokeCap(Paint.Cap.ROUND);

        mFTextPaint = new Paint();
        mFTextPaint.setAntiAlias(true);
        mFTextPaint.setStrokeWidth(1F);
        mFTextPaint.setColor(fColor);
        mFTextPaint.setTextSize(mTextSize);

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
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (progressHeight + progressPadding + mTextSize), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        float offsetL = progressHeight / 2;

        float all = s + p + f;
        float s1 = s / all;
        float p1 = p / all;
        float f1 = f / all;
        float i = width * s1;
        float j = width * p1;
        float k = width * f1;

        String strS = "主胜" + s + "场";
        String strP = "平局" + p + "场";
        String strF = "客胜" + f + "场";

        float textHeight = getTextWH(strS)[1];

        canvas.drawText(strS, 0, textHeight, mSTextPaint);
        canvas.drawText(strP, i + j / 2 - getTextWH(strP)[0] / 2, textHeight, mPTextPaint);
        canvas.drawText(strF, width - getTextWH(strF)[0], textHeight, mFTextPaint);
        canvas.drawLine(offsetL, textHeight + offsetL + progressPadding, i, textHeight + offsetL + progressPadding, mSPaint);
        canvas.drawLine(i + j, textHeight + offsetL + progressPadding, width - offsetL, textHeight + offsetL + progressPadding, mFPaint);
        canvas.drawLine(i, textHeight + offsetL + progressPadding, i + j, textHeight + offsetL + progressPadding, mPPaint);

    }

    private float[] getTextWH(String s) {
        float[] ints = new float[2];
        Rect bounds = new Rect();
        mSTextPaint.getTextBounds(s, 0, s.length(), bounds);
        ints[0] = bounds.width();
        ints[1] = bounds.height();
        return ints;
    }

    /*******************************************************/
    private static float dp2px(float dp) {
        Resources r = Resources.getSystem();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    private static int dp2pxInt(float dp) {
        return (int) dp2px(dp);
    }
}
