package com.xinfu.demo7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/07/10
 *     desc   :
 * </pre>
 */
public class Demo7Fragment extends Fragment {
    private static final String TAG = "Demo7Fragment";
    private static final String TYPE = "type";
    @InjectView(R.id.tv)
    TextView tv;
    private String mS;

    public static Demo7Fragment newInstance(String s) {
        Bundle args = new Bundle();
        args.putString(TYPE, s);
        Demo7Fragment fragment = new Demo7Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mS = args.getString(TYPE);
        Log.d(TAG, "onCreate: " + mS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo7, container, false);
        ButterKnife.inject(this, view);
        Log.d(TAG, "onCreateView: " + mS);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Log.d(TAG, "onHiddenChanged: " + mS);
            tv.setText(mS);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
