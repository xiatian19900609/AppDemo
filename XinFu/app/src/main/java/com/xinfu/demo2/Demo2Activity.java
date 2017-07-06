package com.xinfu.demo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pageindicatorview.Orientation;
import com.pageindicatorview.PageIndicatorView;
import com.xinfu.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/02
 *     desc   :
 * </pre>
 */
public class Demo2Activity extends AppCompatActivity {
    @InjectView(R.id.btnSave)
    Button btnSave;
    @InjectView(R.id.vp)
    ViewPager vp;
    @InjectView(R.id.pageindicatorview)
    PageIndicatorView pageindicatorview;

    private ArrayList<UseInfo> infos = new ArrayList<UseInfo>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        ButterKnife.inject(this);
        initDatas();
    }

    private void initDatas() {
        UseInfo info1 = new UseInfo();
        info1.url = "http://8win-test.oss-cn-beijing.aliyuncs.com//cms/image/all/2016/11/18/5c2976ce-acec-410f-a27b-7056a68e5661.jpg";
        info1.des = "text1";
        UseInfo info2 = new UseInfo();
        info2.url = "http://8win-test.oss-cn-beijing.aliyuncs.com//cms/image/all/2017/2/7/dbef47c3-4698-4d0f-8511-8907bdd875b5.png";
        info2.des = "text2";
        UseInfo info3 = new UseInfo();
        info3.url = "http://8win-test.oss-cn-beijing.aliyuncs.com//cms/image/all/2017/1/20/32b5b26c-6c0d-458b-a79f-472ab372d052.png";
        info3.des = "text3";
        infos.add(info1);
        infos.add(info2);
        infos.add(info3);


        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < infos.size(); i++) {
            switch (i) {
                case 0:
                    fragments.add(Demo2Fragment.newInstance(infos.get(0)));
                    break;
                case 1:
                    fragments.add(Demo2Fragment.newInstance(infos.get(1)));
                    break;
                case 2:
                    fragments.add(Demo2Fragment.newInstance(infos.get(2)));
                    break;
            }

        }

        Demo2Adapter adapter = new Demo2Adapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);

        PageIndicatorView indicatorView = (PageIndicatorView) findViewById(R.id.pageindicatorview);
        indicatorView.setViewPager(vp);
        indicatorView.setOrientation(Orientation.HORIZONTAL);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BitmapUtil.saveImageToGallery(Demo2Activity.this, infos.get(vp.getCurrentItem()).url);
                    }
                }).start();

            }
        });
    }
}
