package com.xinfu.demo2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/02
 *     desc   :
 * </pre>
 */
public class Demo2Adapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> lists;

    public Demo2Adapter(FragmentManager fm, ArrayList<Fragment> lists) {
        super(fm);
        this.lists = lists;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
