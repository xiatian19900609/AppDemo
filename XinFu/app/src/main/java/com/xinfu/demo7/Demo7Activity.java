package com.xinfu.demo7;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
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
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;
    @InjectView(R.id.btn4)
    Button btn4;

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
    @InjectView(R.id.tabLayout)
    TabLinearLayout tabLayout;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private PopupOrderPriceDetail mPriceDetail;
    private int mWidth;
    private TextView mTvZyCouponTip;
    private Demo7Fragment mFragment1;
    private Demo7Fragment mFragment2;
    private Demo7Fragment mFragment3;
    private Demo7Fragment mFragment4;
    private Demo7Fragment mFragment5;
    private FragmentManager mFm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo7);
        ButterKnife.inject(this);
        mFm = getSupportFragmentManager();
        initFragment();


        for (int i = 0; i < mTabTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTabTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setData(mTabEntities);
        tabLayout.setOnTabSelectListener(new TabLinearLayout.OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                swtichFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        swtichFragment(0);


        tabLayout.showHot(1);
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

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void initFragment() {
        mFragment1 = Demo7Fragment.newInstance("大厅");
        mFragment2 = Demo7Fragment.newInstance("竞猜");
        mFragment3 = Demo7Fragment.newInstance("爆料");
        mFragment4 = Demo7Fragment.newInstance("比分");
        mFragment5 = Demo7Fragment.newInstance("我的");
        mFm.beginTransaction().add(R.id.framelayout, mFragment1)
                .add(R.id.framelayout, mFragment2).add(R.id.framelayout, mFragment3)
                .add(R.id.framelayout, mFragment4).add(R.id.framelayout, mFragment5).commitAllowingStateLoss();
    }


    private void swtichFragment(int position) {
        tabLayout.setCurrentTab(position);
        switch (position) {
            case 0:
                mFm.beginTransaction().hide(mFragment2).hide(mFragment3).hide(mFragment4).hide(mFragment5).show(mFragment1).commitAllowingStateLoss();
                break;
            case 1:
                mFm.beginTransaction().hide(mFragment1).hide(mFragment3).hide(mFragment4).hide(mFragment5).show(mFragment2).commitAllowingStateLoss();
                break;
            case 2:
                mFm.beginTransaction().hide(mFragment2).hide(mFragment1).hide(mFragment4).hide(mFragment5).show(mFragment3).commitAllowingStateLoss();
                break;
            case 3:
                mFm.beginTransaction().hide(mFragment2).hide(mFragment3).hide(mFragment1).hide(mFragment5).show(mFragment4).commitAllowingStateLoss();
                break;
            case 4:
                mFm.beginTransaction().hide(mFragment2).hide(mFragment3).hide(mFragment4).hide(mFragment1).show(mFragment5).commitAllowingStateLoss();
                break;

        }
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
        tabLayout.hideDot(4);
        mTvZyCouponTip = (TextView) View.inflate(this, R.layout.include_coupon_tip, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.bottomMargin = DensityUtils.dp2px(this, 50);
        params.rightMargin = DensityUtils.dp2px(this, 22);
        mTvZyCouponTip.setText("有2张卡券即将过期");
        rlContent.addView(mTvZyCouponTip, params);

        Animation animationScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        mTvZyCouponTip.setAnimation(animationScale);
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
        mWidth = mTvZyCouponTip.getWidth();
        ObjectAnimator cornerAnimation =
                ObjectAnimator.ofFloat(mTvZyCouponTip, "cornerRadius", 15.0f, 200.0f);
        android.animation.Animator scaleAnimation = AnimatorInflater.loadAnimator(this, R.animator.coupontipscale);
        scaleAnimation.setTarget(mTvZyCouponTip);
        ObjectAnimator shiftAnimation =
                ObjectAnimator.ofFloat(mTvZyCouponTip, "translationX", 0, mWidth / 2.8f);
        ObjectAnimator shiftYAnimation =
                ObjectAnimator.ofFloat(mTvZyCouponTip, "translationY", 0, 10);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(shiftAnimation, shiftYAnimation, scaleAnimation, cornerAnimation);
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
                ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(mTvZyCouponTip, i + width / 2,
                        j + width / 2 - statusBarHeight1, 30, Side.RIGHT)
                        .setDuration(1000);
                arcAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mTvZyCouponTip.clearAnimation();
                        mTvZyCouponTip.invalidate();
                        tabLayout.showDot(4);
                        rlContent.removeView(mTvZyCouponTip);
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
