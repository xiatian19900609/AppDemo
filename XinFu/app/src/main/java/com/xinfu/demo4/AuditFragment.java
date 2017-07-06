package com.xinfu.demo4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinfu.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/08
 *     desc   :
 * </pre>
 */
public class AuditFragment extends Fragment implements FilterCallBack {
    private static final String AUDIT_STATUS = "audit_status";


    private int mAuditStatus = 1;
    private FragmentManager mFm;
    private AuditChildFragment mAuditChildFragment;
    private FilterFragment mFilterFragment;

    public ArrayList<String> types;

    public static AuditFragment newInstance(int mStatus) {
        AuditFragment fragment = new AuditFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AUDIT_STATUS, mStatus);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAuditStatus = getArguments().getInt(AUDIT_STATUS);
        }
        mFm = getChildFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audit, container, false);
        ButterKnife.inject(this, view);
        initDatas();
        return view;

    }

    private void initDatas() {
        mAuditChildFragment = AuditChildFragment.newInstance(mAuditStatus);
        mFilterFragment = FilterFragment.newInstance(this);
        mFm.beginTransaction().add(R.id.framelayout, mAuditChildFragment)
                .add(R.id.framelayout, mFilterFragment).commitAllowingStateLoss();
        switchTab(0);
    }

    public void switchTab(int i) {
        switch (i) {
            case 0:
                mFm.beginTransaction().hide(mFilterFragment).show(mAuditChildFragment).commitAllowingStateLoss();
                break;
            case 1:
                mFm.beginTransaction().hide(mAuditChildFragment).show(mFilterFragment).commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * 筛选回调
     *
     * @param type
     */
    @Override
    public void onFilterFinish(String type) {
        if (!TextUtils.isEmpty(type)) {
            mAuditChildFragment.filterCallBack(type);
        }
    }
}
