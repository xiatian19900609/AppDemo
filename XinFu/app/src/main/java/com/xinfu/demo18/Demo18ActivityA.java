package com.xinfu.demo18;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;

import com.xinfu.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/29
 *     desc   :
 * </pre>
 */
public class Demo18ActivityA extends AppCompatActivity {
    @InjectView(R.id.rv)
    RecyclerView rv;
    private ArrayList<Info> datas = new ArrayList<Info>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo19_a);
        ButterKnife.inject(this);
        init();
        initRecyclerView();
        setupWindowAnimations();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(this, datas);
        rv.setAdapter(adapter);
    }

    private void init() {
        Info bk = new Info("http://pic.8win.com/mapi/icon/2017/7/10/252d0058-66aa-44ce-bc43-ca490fc0dedb.png", "竞彩篮球");
        Info fb = new Info("http://pic.8win.com/mapi/icon/2017/7/10/259d0110-cea9-43f6-afe3-bc1952c5b47b.png", "竞彩足球");
        Info dlt = new Info("http://pic.8win.com/mapi/icon/2017/6/21/02f8e8be-67a5-474a-a914-735acf49bbd2.png", "大乐透");
        Info scdh = new Info("http://pic.8win.com/mapi/icon/2017/7/19/0da119b1-7519-4908-90e4-6df7c557c1d7.png", "水产大亨");
        datas.add(bk);
        datas.add(fb);
        datas.add(dlt);
        datas.add(scdh);
    }

    private void setupWindowAnimations() {
        Fade fade1 = new Fade();
        fade1.setDuration(1000);
        getWindow().setEnterTransition(fade1);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }


}
