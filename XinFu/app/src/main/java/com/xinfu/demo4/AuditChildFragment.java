package com.xinfu.demo4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xinfu.R;
import com.xinfu.demo3.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/09
 *     desc   :
 * </pre>
 */
public class AuditChildFragment extends Fragment implements View.OnClickListener {
    private static final String AUDIT_STATUS = "audit_status";
    @InjectView(R.id.btn_search)
    Button btnSearch;
    @InjectView(R.id.btn_filter)
    Button btnFilter;
    @InjectView(R.id.xRecylerView)
    XRecyclerView xRecylerView;


    private String json = "{\"data\" : [{\"T_name\":\"设备\",\"U_name\":\"ttf \",\"face\":\"31494058963.jpg\",\"Id\":\"9\",\"Addtime\":\"1493883809\",\"Title\":\"FCC\",\"Content\":\"Shh\",\"time\":\"2017-05-04 15:43:29\",\"Pic\":\"/data/face/31494058963.jpg\"},{\"T_name\":\"设备\",\"U_name\":\"ttf \",\"face\":\"31494058963.jpg\",\"Id\":\"8\",\"Addtime\":\"1493883798\",\"Title\":\"Urdu\",\"Content\":\"Tfff\",\"time\":\"2017-05-04 15:43:18\",\"Pic\":\"/data/face/31494058963.jpg\"},{\"T_name\":\"设备\",\"U_name\":\"ttf \",\"face\":\"31494058963.jpg\",\"Id\":\"7\",\"Addtime\":\"1493883764\",\"Title\":\"fight\",\"Content\":\"Dejuan\",\"time\":\"2017-05-04 15:42:44\",\"Pic\":\"/data/face/31494058963.jpg\"},{\"T_name\":\"项目\",\"U_name\":\"ttf \",\"face\":\"31494058963.jpg\",\"Id\":\"5\",\"Addtime\":\"1493881668\",\"Title\":\"云南七日游\",\"Content\":\"云南七天旅游，考察当地项目，把本地没有的项目带回来，大家一起发财\",\"time\":\"2017-05-04 15:07:48\",\"Pic\":\"/data/face/31494058963.jpg\"},{\"T_name\":\"设备\",\"U_name\":\"ttf \",\"face\":\"31494058963.jpg\",\"Id\":\"3\",\"Addtime\":\"1493881223\",\"Title\":\"出售一台挖掘机\",\"Content\":\"出售一台大型挖掘机，七成新，价位面谈\",\"time\":\"2017-05-04 15:00:23\",\"Pic\":\"/data/face/31494058963.jpg\"}]}";
    private int mAuditStatus = 1;
    private List<DataBean> mLists;
    private AuditAdapter mAuditAdapter;
    private AuditFragment mParentFragment;

    public static AuditChildFragment newInstance(int mStatus) {
        AuditChildFragment fragment = new AuditChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AUDIT_STATUS, mStatus);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment() instanceof AuditFragment) {
            mParentFragment = (AuditFragment) getParentFragment();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audit_child, container, false);
        ButterKnife.inject(this, view);
        initViews();
        initRecyclerView();
        initDatas();
        return view;
    }

    private void initViews() {
        btnFilter.setOnClickListener(this);
    }

    private void initDatas() {
        MyFviewYesBean mData = JsonUtil.getInstance().convertString2Bean(json, MyFviewYesBean.class);
        mParentFragment.types = mData.getTypes();
        mLists = mData.data;
        mAuditAdapter.setData(mLists);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecylerView.setLayoutManager(layoutManager);
        xRecylerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecylerView.setLoadingMoreEnabled(false);
        xRecylerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //TODO
                xRecylerView.refreshComplete();
                xRecylerView.loadMoreComplete();
            }

            @Override
            public void onLoadMore() {
            }
        });
        mAuditAdapter = new AuditAdapter();
        xRecylerView.setAdapter(mAuditAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_filter:
                mParentFragment.switchTab(1);
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
    public void filterCallBack(String type) {
        ArrayList<DataBean> lists = new ArrayList<>();
        for (DataBean obj : mLists) {
            if (obj.T_name.equals(type)) {
                lists.add(obj);
            }
        }
        mAuditAdapter.setData(lists);
    }
}
