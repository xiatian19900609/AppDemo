package com.xinfu.demo4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/08
 *     desc   :
 * </pre>
 */
public class Demo4Activity extends AppCompatActivity {
    @InjectView(R.id.tv_unAudit)
    TextView tvUnAudit;
    @InjectView(R.id.v_unAudit)
    View vUnAudit;
    @InjectView(R.id.ll_unAudit)
    LinearLayout llUnAudit;
    @InjectView(R.id.tv_audit)
    TextView tvAudit;
    @InjectView(R.id.v_audit)
    View vAudit;
    @InjectView(R.id.ll_audit)
    LinearLayout llAudit;

    private FragmentManager mFm;
    private AuditFragment mAuditFragment;
    private AuditFragment mUnAuditFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4);
        mFm = getSupportFragmentManager();
        ButterKnife.inject(this);
        llUnAudit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTab(0);
            }
        });
        llAudit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTab(1);
            }
        });
        initDatas();
    }

    private void initDatas() {
        mAuditFragment = AuditFragment.newInstance(1);
        mUnAuditFragment = AuditFragment.newInstance(2);
        mFm.beginTransaction().add(R.id.framelayout, mAuditFragment)
                .add(R.id.framelayout, mUnAuditFragment).commitAllowingStateLoss();
        switchTab(0);
    }

    private void switchTab(int i) {
        switch (i) {
            case 0:
                llUnAudit.setEnabled(false);
                tvUnAudit.setSelected(true);
                vUnAudit.setSelected(true);
                llAudit.setEnabled(true);
                tvAudit.setSelected(false);
                vAudit.setSelected(false);
                mFm.beginTransaction().hide(mUnAuditFragment).show(mAuditFragment).commitAllowingStateLoss();
                break;
            case 1:
                llUnAudit.setEnabled(true);
                tvUnAudit.setSelected(false);
                vUnAudit.setSelected(false);
                llAudit.setEnabled(false);
                tvAudit.setSelected(true);
                vAudit.setSelected(true);
                mFm.beginTransaction().hide(mAuditFragment).show(mUnAuditFragment).commitAllowingStateLoss();
                break;
        }
    }
}
