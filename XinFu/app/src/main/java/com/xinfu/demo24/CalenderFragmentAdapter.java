package com.xinfu.demo24;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/23
 *     desc   :
 * </pre>
 */
public class CalenderFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<V4_2IndicatorBean> dates;
    private ArrayList<MtachFragment> mFragments;

    public CalenderFragmentAdapter(FragmentManager fm, ArrayList<V4_2IndicatorBean> dates, ArrayList<MtachFragment> fragments) {
        super(fm);
        this.dates = dates;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments == null) {
            return null;
        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if (mFragments == null) {
            return 0;
        }
        return mFragments.size();
    }

    public V4_2IndicatorBean getTitle(int position) {
        if (dates == null) {
            return null;
        }
        return dates.get(position);
    }
}
