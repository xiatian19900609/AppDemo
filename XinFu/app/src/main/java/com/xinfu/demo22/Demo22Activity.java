package com.xinfu.demo22;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinfu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.dkzwm.widget.srl.RefreshingListenerAdapter;
import me.dkzwm.widget.srl.SmoothRefreshLayout;
import me.dkzwm.widget.srl.extra.footer.ClassicFooter;
import me.dkzwm.widget.srl.extra.header.ClassicHeader;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/17
 *     desc   :
 * </pre>
 */
public class Demo22Activity extends AppCompatActivity {
    @InjectView(R.id.xExpandableListView)
    CustomExpandListview xExpandableListView;
    @InjectView(R.id.smoothRefreshLayout)
    SmoothRefreshLayout smoothRefreshLayout;
    private Handler mHandler = new Handler();
    private String[] parentSource = {"分类1", "分类2"};
    private ArrayList<String> parent = new ArrayList<>();
    private Map<String, ArrayList<String>> datas = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo22);
        ButterKnife.inject(this);
        initData();
        initView();
    }

    private void initView() {
        MyAdapter myAdapter = new MyAdapter(this, parent, datas, xExpandableListView);
        xExpandableListView.setAdapter(myAdapter);
        xExpandableListView.setHeaderView(getLayoutInflater().inflate(
                R.layout.indictor_layout, xExpandableListView, false));

        smoothRefreshLayout.setHeaderView(new ClassicHeader(this));
        smoothRefreshLayout.setFooterView(new ClassicFooter(this));
        smoothRefreshLayout.setOnRefreshListener(new RefreshingListenerAdapter() {
            @Override
            public void onRefreshBegin(final boolean isRefresh) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isRefresh) {
                            smoothRefreshLayout.setEnableLoadMoreNoMoreData(false);
                        } else {
                            smoothRefreshLayout.setEnableLoadMoreNoMoreData(true);
                        }
                        smoothRefreshLayout.refreshComplete(1200);
                    }
                }, 4000);
            }
        });
        // 默认全部展开
        for (int i = 0; i < parent.size(); i++) {
            xExpandableListView.expandGroup(i);
        }
    }

    private void initData() {
        //模拟数据
        for (int i = 0; i < parentSource.length; i++) {
            parent.add(parentSource[i]);
        }

        for (int i = 0; i < parent.size(); i++) {
            String str = parent.get(i);
            ArrayList<String> temp = new ArrayList<>();
            if (i == 1) {
                for (int j = 0; j < 2; j++) {
                    temp.add("" + j);
                }
            } else {

                for (int j = 0; j < 10; j++) {
                    temp.add("" + j);
                }
            }
            datas.put(str, temp);
        }
    }
}
