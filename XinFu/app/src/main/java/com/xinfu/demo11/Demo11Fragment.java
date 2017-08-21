package com.xinfu.demo11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xinfu.R;
import com.xinfu.demo7.TabEntity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/07/12
 *     desc   :
 * </pre>
 */
public class Demo11Fragment extends Fragment {
    private static final String TAG = "Demo11Fragment";
    @InjectView(R.id.tabLayout)
    CommonTabLayout tabLayout;
    @InjectView(R.id.vp)
    ViewPager vp;
    private NewsFragment mFragment1;
    private Demo11ChildFragment mFragment2;
    private Demo11ChildFragment mFragment3;
    private Demo11ChildFragment mFragment4;
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    private ArrayList<String> mTitles = new ArrayList<String>();
    private MyPagerAdapter mMyPagerAdapter;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    public int tabIndex = 0;
    public int newsTabIndex = 0;

    public static Demo11Fragment newInstance() {
        Bundle args = new Bundle();
        Demo11Fragment fragment = new Demo11Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 爆料");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo11, container, false);
        ButterKnife.inject(this, view);
        initFragment();
        initTab();
        Log.d(TAG, "onCreateView: 爆料");
        return view;

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Log.d(TAG, "onHiddenChanged: 爆料");
            tabLayout.setCurrentTab(tabIndex);
            vp.setCurrentItem(tabIndex);
            switch (tabIndex) {
                case 0:
                    mFragment1.tabIndex = newsTabIndex;
                    mFragment1.setUserVisibleHint(mFragment1.getUserVisibleHint());
                    break;
                case 1:
                    mFragment2.setUserVisibleHint(mFragment2.getUserVisibleHint());
                    break;
                case 2:
                    mFragment3.setUserVisibleHint(mFragment3.getUserVisibleHint());
                    break;
                case 3:
                    mFragment4.setUserVisibleHint(mFragment4.getUserVisibleHint());
                    break;

            }
        }
    }

    private void initTab() {
        mMyPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        vp.setAdapter(mMyPagerAdapter);
        vp.setOffscreenPageLimit(4);
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                tabIndex = position;
                vp.setCurrentItem(position);
                Log.d("tabLayout", "onTabSelect: " + position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("ViewPager", "onPageSelected: " + position);
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initFragment() {
        mFragment1 = NewsFragment.newInstance();
        mFragment2 = Demo11ChildFragment.newInstance("分析师推荐");
        mFragment3 = Demo11ChildFragment.newInstance("章鱼眼");
        mFragment4 = Demo11ChildFragment.newInstance("章鱼日报");
        mTitles.add("章鱼爆料");
        mTitles.add("分析师推荐");
        mTitles.add("章鱼眼");
        mTitles.add("章鱼日报");
        mFragments.add(mFragment1);
        mFragments.add(mFragment2);
        mFragments.add(mFragment3);
        mFragments.add(mFragment4);
        for (int i = 0; i < mFragments.size(); i++) {
            mTabEntities.add(new TabEntity(mTitles.get(i)));
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
