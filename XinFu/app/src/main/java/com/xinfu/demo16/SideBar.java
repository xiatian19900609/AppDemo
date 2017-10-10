package com.xinfu.demo16;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.xinfu.R;

import java.util.ArrayList;

public class SideBar extends View {

    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    public static ArrayList<String> A_Z = new ArrayList<String>();
    private int choose = -1; //选中
    private Paint paint = new Paint();

    private TextView mTextDialog;

    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    public void setA_Z(ArrayList<String> a_z) {
        A_Z = a_z;
        postInvalidate();
    }

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // // 获取焦点改变背景颜色.
        int height = getHeight();//  获取对应高度
        int width = getWidth(); // 获取对应宽度
        int singleHeight = height / A_Z.size() - 2;//  获取每一个字母的高度  (这里-2仅仅是为了好看而已)

        for (int i = 0; i < A_Z.size(); i++) {
            paint.setColor(Color.rgb(33, 65, 98));  //设置字体颜色
            paint.setTypeface(Typeface.DEFAULT_BOLD);  // 设置字体
            paint.setAntiAlias(true);  //设置抗锯齿
            paint.setTextSize(30);  //设置字母字体大小
            // 选中的状态
            if (i == choose) {
                paint.setColor(Color.parseColor("#3399ff"));  //选中的字母改变颜色
                paint.setFakeBoldText(true);  //设置字体为粗体
            }
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width / 2 - paint.measureText(A_Z.get(i)) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(A_Z.get(i), xPos, yPos, paint);  //绘制所有的字母
            paint.reset();// 重置画笔
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();// 点击y坐标
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * A_Z.size());// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;//
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                setBackgroundResource(R.drawable.sidebar_background);
                if (oldChoose != c) {  //判断选中字母是否发生改变
                    if (c >= 0 && c < A_Z.size()) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(A_Z.get(c));
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(A_Z.get(c));
                            mTextDialog.setVisibility(View.VISIBLE);
                        }

                        choose = c;
                        invalidate();
                    }
                }

                break;
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

}
