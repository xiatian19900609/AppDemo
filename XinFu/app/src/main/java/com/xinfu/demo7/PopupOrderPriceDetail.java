package com.xinfu.demo7;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.xinfu.R;

public class PopupOrderPriceDetail extends PopupWindow {

    private int popupWidth;
    private int popupHeight;
    private Context mContext;

    public PopupOrderPriceDetail(Activity context) {
        super(context);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow, null);
        // 设置可以获得焦点
        setFocusable(false);
        // 设置弹窗内可点击
        setTouchable(false);
        // 设置弹窗外可点击
        setOutsideTouchable(false);
        setAnimationStyle(R.style.popwin_anim_style);
        setBackgroundDrawable(new BitmapDrawable());
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(view);
        //获取自身的长宽高
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = view.getMeasuredHeight();
        popupWidth = view.getMeasuredWidth();
    }

    public void showUp(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //在控件上方显示
        showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() - dp2px(mContext, 20)) - popupWidth, location[1] - popupHeight);
    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}