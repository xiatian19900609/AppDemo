package com.xinfu.demo17;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.xinfu.R;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/21
 *     desc   :
 * </pre>
 */
public class TimingButton extends View {
    private static final int DEFAULT_WIDTH = dp2pxInt(58);
    private static final int DEFAULT_HEIGHT = dp2pxInt(36);
    /**
     * 动画状态：
     * 1.静止
     * 2.进入拖动
     * 3.处于拖动
     * 4.拖动-复位
     * 5.拖动-切换
     * 6.点击切换
     **/
    private final int ANIMATE_STATE_NONE = 0;
    private final int ANIMATE_STATE_PENDING_DRAG = 1;
    private final int ANIMATE_STATE_DRAGING = 2;
    private final int ANIMATE_STATE_PENDING_RESET = 3;
    private final int ANIMATE_STATE_PENDING_SETTLE = 4;
    private final int ANIMATE_STATE_SWITCH = 5;
    ;
    /**
     * 阴影半径
     */
    private int shadowRadius;
    /**
     * 阴影Y偏移px
     */
    private int shadowOffset;
    /**
     * 阴影颜色
     */
    private int shadowColor;

    /**
     * 背景半径
     */
    private float viewRadius;
    /**
     * 按钮半径
     */
    private float buttonRadius;

    /**
     * 背景高
     */
    private float height;
    /**
     * 背景宽
     */
    private float width;
    /**
     * 背景位置
     */
    private float left;
    private float top;
    private float right;
    private float bottom;
    private float centerX;
    private float centerY;

    /**
     * 背景底色
     */
    private int background;
    /**
     * 背景关闭颜色
     */
    private int uncheckColor;
    /**
     * 背景打开颜色
     */
    private int checkedColor;
    /**
     * 边框宽度px
     */
    private int borderWidth;

    /**
     * 打开指示线颜色
     */
    private int checkLineColor;
    /**
     * 打开指示线宽
     */
    private int checkLineWidth;
    /**
     * 打开指示线长
     */
    private float checkLineLength;
    /**
     * 关闭圆圈颜色
     */
    private int uncheckCircleColor;
    /**
     * 关闭圆圈线宽
     */
    private int uncheckCircleWidth;
    /**
     * 关闭圆圈位移X
     */
    private float uncheckCircleOffsetX;
    /**
     * 关闭圆圈半径
     */
    private float uncheckCircleRadius;
    /**
     * 打开指示线位移X
     */
    private float checkedLineOffsetX;
    /**
     * 打开指示线位移Y
     */
    private float checkedLineOffsetY;


    /**
     * 按钮最左边
     */
    private float buttonMinX;
    /**
     * 按钮最右边
     */
    private float buttonMaxX;

    /**
     * 按钮画笔
     */
    private Paint buttonPaint;
    /**
     * 背景画笔
     */
    private Paint paint;

    /**
     * 当前状态
     */
    private ViewState viewState;
    private ViewState beforeState;
    private ViewState afterState;

    private RectF rect = new RectF();
    /**
     * 动画状态
     */
    private int animateState = ANIMATE_STATE_NONE;


    /**
     *
     */
    private ValueAnimator valueAnimator;

    private final android.animation.ArgbEvaluator argbEvaluator
            = new android.animation.ArgbEvaluator();

    /**
     * 是否选中
     */
    private boolean isChecked;
    /**
     * 是否启用动画
     */
    private boolean enableEffect;
    /**
     * 是否启用阴影效果
     */
    private boolean shadowEffect;
    /**
     * 是否显示指示器
     */
    private boolean showIndicator;
    /**
     * 收拾是否按下
     */
    private boolean isTouchingDown = false;
    /**
     *
     */
    private boolean isUiInited = false;
    /**
     *
     */
    private boolean isEventBroadcast = false;

    private OnCheckedChangeListener onCheckedChangeListener;

    /**
     * 手势按下的时刻
     */
    private long touchDownTime;

    public TimingButton(Context context) {
        super(context);
        init(context, null);
    }

    public TimingButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TimingButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    public final void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(0, 0, 0, 0);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = null;
        if (attrs != null) {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);
        }

        int buttonColor = optColor(typedArray,
                R.styleable.SwitchButton_sb_button_color,

                Color.WHITE);//Color.WHITE;
        background = optColor(typedArray,
                R.styleable.SwitchButton_sb_background,
                Color.GRAY);//Color.WHITE;
        uncheckColor = optColor(typedArray,
                R.styleable.SwitchButton_sb_uncheck_color,
                0XffDDDDDD);//0XffDDDDDD;

        viewState = new ViewState();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonPaint.setColor(buttonColor);
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
        //绘制白色背景
        paint.setColor(background);
        drawRoundRect(canvas,
                left, top, right, bottom,

                viewRadius, paint);
        //绘制按钮
        drawButton(canvas, viewState.buttonX, centerY);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float viewPadding = Math.max(shadowRadius + shadowOffset, borderWidth);
        height = h - viewPadding - viewPadding;
        width = w - viewPadding - viewPadding;

        viewRadius = height * .5f;
        buttonRadius = viewRadius - borderWidth;

        left = viewPadding;
        top = viewPadding;
        right = w - viewPadding;
        bottom = h - viewPadding;

        centerX = (left + right) * .5f;
        centerY = (top + bottom) * .5f;

        buttonMinX = left + viewRadius;
        buttonMaxX = right - viewRadius;

        setUncheckViewState(viewState);

        postInvalidate();
    }


    public interface OnCheckedChangeListener {
        void onCheckedChanged(TimingButton view, boolean isChecked);
    }


    private static int optColor(TypedArray typedArray,
                                int index,
                                int def) {
        if (typedArray == null) {
            return def;
        }
        return typedArray.getColor(index, def);
    }

    /**
     * @param canvas
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param backgroundRadius
     * @param paint
     */
    private void drawRoundRect(Canvas canvas,
                               float left, float top,
                               float right, float bottom,
                               float backgroundRadius,
                               Paint paint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(left, top, right, bottom,
                    backgroundRadius, backgroundRadius, paint);
        } else {
            rect.set(left, top, right, bottom);
            canvas.drawRoundRect(rect,
                    backgroundRadius, backgroundRadius, paint);
        }
    }


    /**
     * @param canvas
     * @param x      px
     * @param y      px
     */
    private void drawButton(Canvas canvas, float x, float y) {
        canvas.drawCircle(x, y, buttonRadius, buttonPaint);
    }

    /*******************************************************/
    /**
     * 保存动画状态
     */
    private static class ViewState {
        /**
         * 按钮x位置[buttonMinX-buttonMaxX]
         */
        float buttonX;
        /**
         * 状态背景颜色
         */
        int checkStateColor;
        /**
         * 选中线的颜色
         */
        int checkedLineColor;
        /**
         * 状态背景的半径
         */
        float radius;

        ViewState() {
        }

        private void copy(ViewState source) {
            this.buttonX = source.buttonX;
            this.checkStateColor = source.checkStateColor;
            this.checkedLineColor = source.checkedLineColor;
            this.radius = source.radius;
        }
    }

    /**
     * @param viewState
     */
    private void setUncheckViewState(ViewState viewState) {
        viewState.radius = 0;
        viewState.checkStateColor = uncheckColor;
        viewState.checkedLineColor = Color.TRANSPARENT;
        viewState.buttonX = buttonMinX;
    }

    /**
     * 是否在进入拖动或离开拖动状态
     *
     * @return
     */
    private boolean isPendingDragState() {
        return animateState == ANIMATE_STATE_PENDING_DRAG
                || animateState == ANIMATE_STATE_PENDING_RESET;
    }

    /**
     * 是否在手指拖动状态
     *
     * @return
     */
    private boolean isDragState() {
        return animateState == ANIMATE_STATE_DRAGING;
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
