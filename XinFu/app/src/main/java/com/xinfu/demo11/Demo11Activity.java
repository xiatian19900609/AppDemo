package com.xinfu.demo11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.xinfu.R;
import com.xinfu.demo7.Demo7Fragment;
import com.xinfu.demo7.TabEntity;
import com.xinfu.demo9.TabLinearLayout;

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
public class Demo11Activity extends AppCompatActivity {
    public static final String TAB_INDEX = "index";

    @InjectView(R.id.tabLayout)
    TabLinearLayout tabLayout;
    @InjectView(R.id.btn_bk)
    Button btnBk;
    @InjectView(R.id.btn_fb)
    Button btnFb;
    @InjectView(R.id.btn_fenxis)
    Button btnFenxis;
    private String[] mTabTitles = {"大厅", "竞猜", "爆料", "比分", "我的"};
    //
    private int[] mIconUnselectIds = {R.drawable.home_lobby_normal,
            R.drawable.home_guess_normal_hot, R.drawable.home_news_normal, R.drawable.home_score_normal,
            R.drawable.home_user_normal};

    private int[] mIconSelectIds = {R.drawable.home_lobby_selected,
            R.drawable.home_guess_selected_hot, R.drawable.home_news_selected, R.drawable.home_score_selected,
            R.drawable.home_user_selected};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private Demo7Fragment mFragment1;
    private Demo7Fragment mFragment2;
    private Demo11Fragment mFragment3;
    private Demo7Fragment mFragment4;
    private Demo7Fragment mFragment5;
    private FragmentManager mFm;

    private int tabIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo11);
        ButterKnife.inject(this);
        mFm = getSupportFragmentManager();
        tabIndex = getIntent().getIntExtra(TAB_INDEX, 0);
        initView();
        initFragment();
        initTab();
    }

    private void initView() {
        btnBk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCms(2, 0, 1);
            }
        });
        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCms(2, 0, 0);
            }
        });
        btnFenxis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCms(2, 1, 0);
            }
        });
    }

    private void initTab() {
        for (int i = 0; i < mTabTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTabTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setData(mTabEntities);
        tabLayout.setOnTabSelectListener(new TabLinearLayout.OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                swtichFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        swtichFragment(tabIndex);
//        gotoCms(2, 0, 1);
    }

    private void initFragment() {
        mFragment1 = Demo7Fragment.newInstance("大厅");
        mFragment2 = Demo7Fragment.newInstance("竞猜");
        mFragment3 = Demo11Fragment.newInstance();
        mFragment4 = Demo7Fragment.newInstance("比分");
        mFragment5 = Demo7Fragment.newInstance("我的");
        mFm.beginTransaction().add(R.id.framelayout, mFragment1)
                .add(R.id.framelayout, mFragment2).add(R.id.framelayout, mFragment3)
                .add(R.id.framelayout, mFragment4).add(R.id.framelayout, mFragment5)
                .hide(mFragment2).hide(mFragment3).hide(mFragment4).hide(mFragment5).hide(mFragment1).commitAllowingStateLoss();
    }

    private void swtichFragment(int position) {
        tabLayout.setCurrentTab(position);
        switch (position) {
            case 0:
                tabIndex = position;
                mFm.beginTransaction().hide(mFragment2).hide(mFragment3).hide(mFragment4).hide(mFragment5).show(mFragment1).commitAllowingStateLoss();
                break;
            case 1:
                tabIndex = position;
                mFm.beginTransaction().hide(mFragment1).hide(mFragment3).hide(mFragment4).hide(mFragment5).show(mFragment2).commitAllowingStateLoss();
                break;
            case 2:
                tabIndex = position;
                mFm.beginTransaction().hide(mFragment2).hide(mFragment1).hide(mFragment4).hide(mFragment5).show(mFragment3).commitAllowingStateLoss();
                break;
            case 3:
                tabIndex = position;
                mFm.beginTransaction().hide(mFragment2).hide(mFragment3).hide(mFragment1).hide(mFragment5).show(mFragment4).commitAllowingStateLoss();
                break;
            case 4:
                tabIndex = position;
                mFm.beginTransaction().hide(mFragment2).hide(mFragment3).hide(mFragment4).hide(mFragment1).show(mFragment5).commitAllowingStateLoss();
                break;

        }
    }

    public void gotoCms(int i, int tabIndex, int newsTabIndex) {
        if (mFragment3 != null) {
            mFragment3.tabIndex = tabIndex;
            mFragment3.newsTabIndex = newsTabIndex;
        }
        swtichFragment(i);
    }
}
