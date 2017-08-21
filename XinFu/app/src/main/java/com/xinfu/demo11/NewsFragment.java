package com.xinfu.demo11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xinfu.R;
import com.xinfu.demo7.Demo7Fragment;
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
public class NewsFragment extends Fragment {
    private static final String TAG = "NewsFragment";
    @InjectView(R.id.tabLayout)
    CommonTabLayout tabLayout;
    private String[] mTabTitles = {"足球", "篮球"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private FragmentManager mFm;
    private Demo7Fragment mFbFragment;
    private Demo7Fragment mBkFragment;
    private boolean isOnCreate;
    public int tabIndex = 0;

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getChildFragmentManager();
        Log.d(TAG, "onCreate: 章鱼爆料");
        isOnCreate = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        Log.d(TAG, "onCreateView: 章鱼爆料");
        ButterKnife.inject(this, view);
        initFragment();
        initTab();
        setUserVisibleHint(getUserVisibleHint());
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isOnCreate) {
            Log.d(TAG, "setUserVisibleHint: 章鱼爆料");
            switchFragment(tabIndex);
        }
    }

    private void initFragment() {
        mFbFragment = Demo7Fragment.newInstance("足球");
        mBkFragment = Demo7Fragment.newInstance("篮球");
        mFm.beginTransaction().add(R.id.framelayout, mFbFragment).add(R.id.framelayout, mBkFragment)
                .hide(mFbFragment).hide(mBkFragment).commitAllowingStateLoss();
    }

    private void initTab() {
        for (int i = 0; i < mTabTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTabTitles[i]));
        }
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    private void switchFragment(int position) {
        tabIndex = position;
        tabLayout.setCurrentTab(position);
        if (position == 0) {
            mFm.beginTransaction().hide(mBkFragment).show(mFbFragment).commitAllowingStateLoss();
        } else {
            mFm.beginTransaction().hide(mFbFragment).show(mBkFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
