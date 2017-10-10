package com.xinfu.demo14;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/12
 *     desc   :
 * </pre>
 */
public class Demo14Activity extends AppCompatActivity {

    @InjectView(R.id.tv_unevaluate)
    TextView tvUnevaluate;
    @InjectView(R.id.v_unevaluate)
    View vUnevaluate;
    @InjectView(R.id.ll_unevaluate)
    LinearLayout llUnevaluate;
    @InjectView(R.id.tv_evaluated)
    TextView tvEvaluated;
    @InjectView(R.id.v_evaluated)
    View vEvaluated;
    @InjectView(R.id.ll_evaluated)
    LinearLayout llEvaluated;
    @InjectView(R.id.btn)
    Button btn;
    @InjectView(R.id.framelayout)
    FrameLayout framelayout;
    private FragmentManager mFm;
    private EvaluateFragment mFragment1;
    private EvaluateFragment mFragment2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo14);
        ButterKnife.inject(this);
        mFm = getSupportFragmentManager();
        initViews();
        initDatas();
    }

    private void initViews() {
        llUnevaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTab(0);
            }
        });

        llEvaluated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTab(1);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String size = DataCleanManager.getTotalCacheSize(Demo14Activity.this);
                    Log.d("=======TAG=====", "" + size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initDatas() {
        mFragment1 = EvaluateFragment.newInstance(0);
        mFragment2 = EvaluateFragment.newInstance(1);
        mFm.beginTransaction().add(R.id.framelayout, mFragment1).add(R.id.framelayout, mFragment2).commitAllowingStateLoss();
        switchTab(0);
    }

    private void switchTab(int i) {
        switch (i) {
            case 0:
                llUnevaluate.setEnabled(false);
                llEvaluated.setEnabled(true);
                tvEvaluated.setSelected(false);
                vEvaluated.setSelected(false);
                tvUnevaluate.setSelected(true);
                vUnevaluate.setSelected(true);
                mFm.beginTransaction().hide(mFragment2).show(mFragment1).commitAllowingStateLoss();
                break;
            case 1:
                llEvaluated.setEnabled(false);
                llUnevaluate.setEnabled(true);
                tvUnevaluate.setSelected(false);
                vUnevaluate.setSelected(false);
                tvEvaluated.setSelected(true);
                vEvaluated.setSelected(true);
                mFm.beginTransaction().hide(mFragment1).show(mFragment2).commitAllowingStateLoss();
                break;
        }
    }


    public void setTabCount(int status, int count) {
        switch (status) {
            case 0:
                tvUnevaluate.setText("待评价(" + count + ")");
                break;
            case 1:
                tvEvaluated.setText("已评价(" + count + ")");
                break;
        }
    }
}
