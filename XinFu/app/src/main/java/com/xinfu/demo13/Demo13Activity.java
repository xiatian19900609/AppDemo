package com.xinfu.demo13;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.xinfu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/08/21
 *     desc   :
 * </pre>
 */
public class Demo13Activity extends AppCompatActivity {
    @InjectView(R.id.btn)
    Button btn;
    @InjectView(R.id.rv)
    RecyclerView rv;
    private List<BeanInfo> newLists = new ArrayList<>();
    private List<BeanInfo> oldLists = new ArrayList<>();
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo13);
        ButterKnife.inject(this);
        initList();
        initData();
    }

    private void initList() {
        for (int i = 0; i < 10; i++) {
            if (i == 2 || i == 3) {
                continue;
            }
            BeanInfo info = new BeanInfo();
            info.name = "text-" + i;
            info.id = i + "";
            oldLists.add(info);
        }
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setSmoothScrollbarEnabled(true);
        rv.setLayoutManager(layoutManager);
        mMyAdapter = new MyAdapter(oldLists);
        rv.setAdapter(mMyAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 10; i++) {
                    BeanInfo info = new BeanInfo();
                    info.name = "text-" + i;
                    info.id = i + "";
                    newLists.add(info);
                }
                DiffUtil.DiffResult diffResult =
                        DiffUtil.calculateDiff(new DiffCallBack(newLists, oldLists), true);
                diffResult.dispatchUpdatesTo(mMyAdapter);
                oldLists = newLists;
                mMyAdapter.setData(oldLists);
            }
        });
    }
}
