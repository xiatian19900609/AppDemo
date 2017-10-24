package com.xinfu.demo17;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.xinfu.R;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/18
 *     desc   :
 * </pre>
 */
public class ScoreSettingPopupWindow extends PopupWindow {
    TimingView timeview;
    SwitchButton sbtn;
    View v;
    private Context mContext;

    public ScoreSettingPopupWindow(Context context) {
        super(context);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow_score_setting, null);
        sbtn = (SwitchButton) view.findViewById(R.id.sbtn);
        timeview = (TimingView) view.findViewById(R.id.timeview);
        v = view.findViewById(R.id.v);
        setBackgroundDrawable(new BitmapDrawable());
        // 设置弹窗外可点击
        setOutsideTouchable(true);
        setTouchable(true);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(view);
    }

    public void showDown(View v) {
        showAsDropDown(v);
    }
}
