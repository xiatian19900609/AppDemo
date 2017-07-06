package com.xinfu.demo7;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.nineoldandroids.animation.Animator;
import com.xinfu.R;
import com.xinfu.demo7.animator.ArcAnimator;
import com.xinfu.demo7.animator.Side;
import com.xinfu.demo9.TabLinearLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/06/12
 *     desc   :
 * </pre>
 */
public class Demo7Activity extends AppCompatActivity {
    private static float[] mCurrentPosition = new float[2];
    @InjectView(R.id.rl_content)
    RelativeLayout rlContent;
    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;

    private String[] mTabTitles = {"大厅", "竞猜", "爆料", "比分", "我的"};
    //
    private int[] mIconUnselectIds = {R.drawable.home_lobby_normal,
            R.drawable.home_guess_normal_hot, R.drawable.home_news_normal, R.drawable.home_score_normal,
            R.drawable.home_user_normal};

    private int[] mIconSelectIds = {R.drawable.home_lobby_selected,
            R.drawable.home_guess_selected_hot, R.drawable.home_news_selected, R.drawable.home_score_selected,
            R.drawable.home_user_selected};
    private static final String TAG = "Demo7Activity";
    @InjectView(R.id.btn)
    Button btn;
    @InjectView(R.id.tvZyCouponTip)
    TextView tvZyCouponTip;
    @InjectView(R.id.tabLayout)
    TabLinearLayout tabLayout;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private PopupOrderPriceDetail mPriceDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo7);
        ButterKnife.inject(this);

        for (int i = 0; i < mTabTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTabTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setData(mTabEntities);
        tabLayout.setOnTabSelectListener(new TabLinearLayout.OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                tv.setText(mTabTitles[position]);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        tabLayout.showHot(1);
        tabLayout.setCurrentTab(0);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCouponTip();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideCouponTip();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLayout.showHot(1);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLayout.hideHot(1);
            }
        });
    }

    private void init() {
        mPriceDetail = new PopupOrderPriceDetail(this);
        mPriceDetail.showUp(tabLayout);
        mPriceDetail.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Observable.timer(1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                tabLayout.showDot(mTabTitles.length - 1);
                            }
                        });

            }
        });
    }

    /**
     * 显示卡券气泡弹框
     */
    private void showCouponTip() {
        Animation animationScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        tvZyCouponTip.setVisibility(View.VISIBLE);
        tvZyCouponTip.setAnimation(animationScale);
        animationScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                Observable.timer(5, TimeUnit.SECONDS)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Action1<Long>() {
//                            @Override
//                            public void call(Long aLong) {
//                                hideCouponTip();
//                            }
//                        });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animationScale.start();
    }


    /**
     * 隐藏卡券气泡弹框
     */
    private void hideCouponTip() {
        int width = tvZyCouponTip.getWidth();
        ObjectAnimator shiftAnimation =
                ObjectAnimator.ofFloat(tvZyCouponTip, "translationX", 0, width / 2.8f);
        ObjectAnimator shiftYAnimation =
                ObjectAnimator.ofFloat(tvZyCouponTip, "translationY", 0, 10);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvZyCouponTip, "scaleX", 1f, 0.03f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(tvZyCouponTip, "scaleY", 1f, 0.17f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(shiftAnimation, shiftYAnimation, scaleX, scaleY);
        set.addListener(new android.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(android.animation.Animator animator) {

            }

            @Override
            public void onAnimationEnd(android.animation.Animator animator) {
                ImageView imageView = tabLayout.getPointLocation(4);
                int width = imageView.getWidth();
                int[] location1 = new int[2];
                imageView.getLocationOnScreen(location1);
                int i = location1[0];
                int j = location1[1];
                int statusBarHeight1 = -1;
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
                }
                ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(tvZyCouponTip, i + width / 2,
                        j + width / 2 - statusBarHeight1, 30, Side.RIGHT)
                        .setDuration(1000);
                arcAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        tvZyCouponTip.clearAnimation();
                        tvZyCouponTip.invalidate();
                        tvZyCouponTip.setVisibility(View.INVISIBLE);
                        tabLayout.showDot(4);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                arcAnimator.start();
            }

            @Override
            public void onAnimationCancel(android.animation.Animator animator) {

            }

            @Override
            public void onAnimationRepeat(android.animation.Animator animator) {

            }
        });
        set.start();
    }
}
