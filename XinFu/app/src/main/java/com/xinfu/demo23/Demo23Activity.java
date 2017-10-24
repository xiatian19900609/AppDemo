package com.xinfu.demo23;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/19
 *     desc   :
 * </pre>
 */
public class Demo23Activity extends AppCompatActivity {
    @InjectView(R.id.rv_date)
    RecyclerView rvDate;
    private String[] str = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo23);
        ButterKnife.inject(this);
        initRecycleView();
    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDate.setLayoutManager(layoutManager);
        MyAdapter myAdapter = new MyAdapter(this, str);
        rvDate.setAdapter(myAdapter);
    }
}
