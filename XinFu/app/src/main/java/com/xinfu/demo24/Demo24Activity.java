package com.xinfu.demo24;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xinfu.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/23
 *     desc   :
 * </pre>
 */
public class Demo24Activity extends AppCompatActivity {
    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.indicator)
    V4_2TabPageIndicator indicator;
    private ArrayList<V4_2IndicatorBean> dates = new ArrayList<>();
    private ArrayList<MtachFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo24);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            MtachFragment fragment = new MtachFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text", i + "");
            fragment.setArguments(bundle);
            mFragments.add(fragment);

            V4_2IndicatorBean bean = new V4_2IndicatorBean();
            bean.date = i + "";
            dates.add(bean);
        }

        CalenderFragmentAdapter adapter = new CalenderFragmentAdapter(getSupportFragmentManager(), dates, mFragments);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
