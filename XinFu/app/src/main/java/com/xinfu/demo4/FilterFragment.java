package com.xinfu.demo4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

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
public class FilterFragment extends Fragment implements View.OnClickListener {
    @InjectView(R.id.gv)
    GridView gv;
    @InjectView(R.id.btn_ok)
    Button btnOk;

    int selectorPosition = -1;
    private AuditFragment mParentFragment;
    private FilterAdapter mAuditAdapter;
    private FilterCallBack mFilterCallBack;

    public static FilterFragment newInstance(FilterCallBack mFilterCallBack) {
        FilterFragment fragment = new FilterFragment();
        fragment.mFilterCallBack = mFilterCallBack;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment() instanceof AuditFragment) {
            mParentFragment = (AuditFragment) getParentFragment();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mAuditAdapter.setData(mParentFragment.types);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        ButterKnife.inject(this, view);
        initViews();
        initDatas();
        return view;
    }

    private void initDatas() {
        mAuditAdapter = new FilterAdapter();
        gv.setAdapter(mAuditAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectorPosition == position) {
                    selectorPosition = -1;
                } else {
                    selectorPosition = position;
                }
                mAuditAdapter.changeState(selectorPosition);

            }
        });
    }

    private void initViews() {
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                mParentFragment.switchTab(0);
                if (selectorPosition != -1)
                    mFilterCallBack.onFilterFinish(mParentFragment.types.get(selectorPosition));
                break;
        }
    }
}
