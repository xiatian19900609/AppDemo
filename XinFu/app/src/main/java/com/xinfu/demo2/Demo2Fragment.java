package com.xinfu.demo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/02
 *     desc   :
 * </pre>
 */
public class Demo2Fragment extends Fragment {
    @InjectView(R.id.ivQR)
    ImageView ivQR;
    @InjectView(R.id.tvDes)
    TextView tvDes;

    private static final String INFO = "info";
    private UseInfo mInfo;

    public static Demo2Fragment newInstance(UseInfo info) {
        Bundle args = new Bundle();
        args.putParcelable(INFO, info);
        Demo2Fragment fragment = new Demo2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        mInfo = (UseInfo) arg.get("info");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo2, container, false);
        ButterKnife.inject(this, view);
        initDatas();
        return view;
    }

    private void initDatas() {
        Glide.with(this)
                .load(mInfo.url)
                .placeholder(R.drawable.v4_banner_nonet)
                .error(R.drawable.v4_banner_nonet)
                .into(ivQR);

        tvDes.setText(mInfo.des);
    }
}
