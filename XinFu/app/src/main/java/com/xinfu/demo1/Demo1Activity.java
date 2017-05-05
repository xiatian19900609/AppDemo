package com.xinfu.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xinfu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/02
 *     desc   :
 * </pre>
 */
public class Demo1Activity extends AppCompatActivity {
    @InjectView(R.id.xRecylerView)
    XRecyclerView xRecylerView;
    ConvenientBanner mConvenientBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        ButterKnife.inject(this);
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        if (mConvenientBanner != null) {
            mConvenientBanner.startTurning(3000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        if (mConvenientBanner != null) {
            mConvenientBanner.stopTurning();
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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
        View headerView = View.inflate(this, R.layout.banner, null);
        mConvenientBanner = (ConvenientBanner) headerView.findViewById(R.id.convenientBanner);
        ArrayList<String> list = new ArrayList<>();
        list.add("http://8win-test.oss-cn-beijing.aliyuncs.com//cms/image/all/2016/11/18/5c2976ce-acec-410f-a27b-7056a68e5661.jpg");
        list.add("http://8win-test.oss-cn-beijing.aliyuncs.com//cms/image/all/2017/2/7/dbef47c3-4698-4d0f-8511-8907bdd875b5.png");
        list.add("http://8win-test.oss-cn-beijing.aliyuncs.com//cms/image/all/2017/1/20/32b5b26c-6c0d-458b-a79f-472ab372d052.png");
        initBanner(list);
        xRecylerView.addHeaderView(headerView);
        xRecylerView.setAdapter(new TestAdapter());
    }


    private void initBanner(final List<String> banners) {
        if (banners != null && banners.size() > 0) {

            mConvenientBanner.setPages(new CBViewHolderCreator<BannerImageHolderView>() {
                @Override
                public BannerImageHolderView createHolder() {
                    return new BannerImageHolderView();
                }
            }, banners)
                    .setPageIndicator(new int[]{R.drawable.play_hide, R.drawable.play_display})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {

                        }
                    });

        }
    }
}
