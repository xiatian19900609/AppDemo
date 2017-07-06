package com.xinfu.demo9;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.xinfu.R;

import java.util.ArrayList;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/07/03
 *     desc   :
 * </pre>
 */
public class TabLinearLayout extends LinearLayout {
    private Context mContext;
    private ArrayList<CustomTabEntity> mTabEntitys = new ArrayList<>();
    private int mTabCount;
    private int mCurrentTab;
    private float mRedPointX;
    private float mRedPointY;

    private OnTabSelectListener mListener;

    public void setOnTabSelectListener(OnTabSelectListener listener) {
        this.mListener = listener;
    }

    public TabLinearLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public TabLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public TabLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void setData(ArrayList<CustomTabEntity> mTabEntitys) {
        this.mTabEntitys = mTabEntitys;
        this.mTabCount = mTabEntitys.size();
        init();
    }

    private void init() {
        for (int i = 0; i < mTabCount; i++) {
            View tabView = View.inflate(mContext, R.layout.layout_tab_item, null);
            tabView.setTag(i);
            addTab(i, tabView);
        }
    }

    /**
     * 创建并添加tab
     */
    private void addTab(final int position, View tabView) {
        TextView tv_tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
        tv_tab_title.setText(mTabEntitys.get(position).getTabTitle());
        ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
        ImageView iv_point = (ImageView) tabView.findViewById(R.id.iv_point);
        ImageView iv_hot = (ImageView) tabView.findViewById(R.id.iv_hot);
        iv_tab_icon.setImageResource(mTabEntitys.get(position).getTabUnselectedIcon());

        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (mCurrentTab != position) {
                    setCurrentTab(position);
                    if (mListener != null) {
                        mListener.onTabSelect(position);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onTabReselect(position);
                    }
                }
            }
        });

        /** 每一个Tab的布局参数 */
        LinearLayout.LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
        addView(tabView, position, params);
    }

    public void setCurrentTab(int currentTab) {
        this.mCurrentTab = currentTab;
        for (int i = 0; i < mTabCount; ++i) {
            View tabView = getChildAt(i);
            final boolean isSelect = i == currentTab;
            TextView tab_title = (TextView) tabView.findViewById(R.id.tv_tab_title);
            tab_title.setTextColor(isSelect ? Color.parseColor("#e82c2c") : Color.parseColor("#666666"));
            ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
            CustomTabEntity tabEntity = mTabEntitys.get(i);
            iv_tab_icon.setImageResource(isSelect ? tabEntity.getTabSelectedIcon() : tabEntity.getTabUnselectedIcon());
        }
        invalidate();
    }

    public ImageView getPointLocation(int position) {
        View tabView = getChildAt(position);
        ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_point);
        return iv_tab_icon;
    }

    public interface OnTabSelectListener {
        void onTabSelect(int position);

        void onTabReselect(int position);
    }

    public void showDot(int position) {
        View tabView = getChildAt(position);
        ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_point);
        iv_tab_icon.setVisibility(View.VISIBLE);
    }

    public void hideDot(int position) {
        View tabView = getChildAt(position);
        ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_point);
        iv_tab_icon.setVisibility(View.INVISIBLE);
    }

    public void showHot(int position) {
        View tabView = getChildAt(position);
        ImageView iv_hot = (ImageView) tabView.findViewById(R.id.iv_hot);
        iv_hot.setVisibility(View.VISIBLE);
    }

    public void hideHot(int position) {
        View tabView = getChildAt(position);
        ImageView iv_hot = (ImageView) tabView.findViewById(R.id.iv_hot);
        iv_hot.setVisibility(View.GONE);
    }
}
