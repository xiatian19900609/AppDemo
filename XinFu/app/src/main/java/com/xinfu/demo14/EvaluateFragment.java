package com.xinfu.demo14;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/14
 *     desc   :
 * </pre>
 */
public class EvaluateFragment extends Fragment {
    private static final String STATUS = "status";
    @InjectView(R.id.rv)
    RecyclerView rv;
    private Demo14Activity mActivity;
    private int mStatus = 0;  // 0待评价 1已评价

    public static EvaluateFragment newInstance(int mCouponStatus) {
        EvaluateFragment fragment = new EvaluateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(STATUS, mCouponStatus);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (Demo14Activity) getActivity();
        if (getArguments() != null) {
            mStatus = getArguments().getInt(STATUS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        ButterKnife.inject(this, view);
        initDatas();
        return view;

    }

    private void initDatas() {
        initRecycleView();
        mActivity.setTabCount(mStatus, 10);
    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        EvaluateAdapter adapter = new EvaluateAdapter();
        rv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
